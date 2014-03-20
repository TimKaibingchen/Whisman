package com.whisman.modules.page.intercepter;

import com.whisman.extools.sql.PagerUtils;
import com.whisman.modules.page.MybatisPage;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * User: Tim
 * Date: 8/27/13
 */
@Intercepts({
        @Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class})})
public class MybatisPageInterceptor implements Interceptor {

    private static Logger logger = LoggerFactory.getLogger(MybatisPageInterceptor.class);
    private String databaseType;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        BoundSql boundSql = delegate.getBoundSql();
        Object obj = boundSql.getParameterObject();
        if (obj instanceof MybatisPage<?>) {
            MybatisPage<?> page = (MybatisPage<?>) obj;
            MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");
            Connection connection = (Connection) invocation.getArgs()[0];
            String sql = boundSql.getSql();
            this.setTotalRecord(page, mappedStatement, connection);
            String pageSql = this.getPageSql(page, sql);
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
    }


    private String getPageSql(MybatisPage<?> page, String sql) {
        return PagerUtils.limit(sql, databaseType, page.getOffset(), page.getSize());
    }

    private void setTotalRecord(MybatisPage<?> page,
                                MappedStatement mappedStatement, Connection connection) {
        BoundSql boundSql = mappedStatement.getBoundSql(page);
        String sql = boundSql.getSql();
        String countSql = this.getCountSql(sql);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, page);
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, page, countBoundSql);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                page.setTotal(totalRecord);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private String getCountSql(String sql) {
        return PagerUtils.count(sql, databaseType);
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        //this.databaseType = properties.getProperty("databaseType");
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    private static class ReflectUtil {
        /**
         * get Field value  from Object by Reflection
         *
         * @param obj       target object
         * @param fieldName target field Name
         * @return target field Value
         */
        public static Object getFieldValue(Object obj, String fieldName) {
            Object result = null;
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    result = field.get(obj);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
            return result;
        }

        /**
         * get Field from Object by Reflection
         *
         * @param obj       target object
         * @param fieldName target field Name
         * @return target field
         */
        private static Field getField(Object obj, String fieldName) {
            Field field = null;
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException e) {
                    //just return null if there is no such field, no need to handle the exception。
                }
            }
            return field;
        }

        /**
         * Set value of Field by Reflection
         *
         * @param obj        target object
         * @param fieldName  target field Name
         * @param fieldValue target field
         */
        public static void setFieldValue(Object obj, String fieldName,
                                         String fieldValue) {
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                try {
                    field.setAccessible(true);
                    field.set(obj, fieldValue);
                } catch (IllegalArgumentException e) {
                    logger.error(e.getMessage());
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

}
