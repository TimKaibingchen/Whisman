/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.whisman.extools.sql;

import java.util.List;


import com.whisman.extools.sql.ast.SQLExpr;
import com.whisman.extools.sql.ast.SQLObject;
import com.whisman.extools.sql.ast.SQLStatement;
import com.whisman.extools.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.whisman.extools.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.whisman.extools.sql.dialect.oracle.visitor.OracleOutputVisitor;
import com.whisman.extools.sql.dialect.oracle.visitor.OracleSchemaStatVisitor;
import com.whisman.extools.sql.dialect.postgresql.visitor.PGOutputVisitor;
import com.whisman.extools.sql.dialect.postgresql.visitor.PGSchemaStatVisitor;
import com.whisman.extools.sql.dialect.sqlserver.visitor.SQLServerOutputVisitor;
import com.whisman.extools.sql.dialect.sqlserver.visitor.SQLServerSchemaStatVisitor;
import com.whisman.extools.sql.parser.ParserException;
import com.whisman.extools.sql.parser.SQLExprParser;
import com.whisman.extools.sql.parser.SQLParseException;
import com.whisman.extools.sql.parser.SQLParserUtils;
import com.whisman.extools.sql.parser.SQLStatementParser;
import com.whisman.extools.sql.parser.Token;
import com.whisman.extools.sql.visitor.SQLASTOutputVisitor;
import com.whisman.extools.sql.visitor.SchemaStatVisitor;
import com.whisman.extools.util.JdbcUtils;

public class SQLUtils {

    public static String toSQLString(SQLObject sqlObject, String dbType) {
        if (JdbcUtils.MYSQL.equals(dbType)) {
            return toMySqlString(sqlObject);
        }

        if (JdbcUtils.H2.equals(dbType)) {
            return toMySqlString(sqlObject);
        }

        if (JdbcUtils.ORACLE.equals(dbType) || JdbcUtils.ALI_ORACLE.equals(dbType)) {
            return toOracleString(sqlObject);
        }

        if (JdbcUtils.POSTGRESQL.equals(dbType)) {
            return toPGString(sqlObject);
        }

        return toSQLServerString(sqlObject);
    }

    public static String toSQLString(SQLObject sqlObject) {
        StringBuilder out = new StringBuilder();
        sqlObject.accept(new SQLASTOutputVisitor(out));

        String sql = out.toString();
        return sql;
    }

    public static String toMySqlString(SQLObject sqlObject) {
        StringBuilder out = new StringBuilder();
        sqlObject.accept(new MySqlOutputVisitor(out));

        String sql = out.toString();
        return sql;
    }

    public static SQLExpr toMySqlExpr(String sql) {
        return toSQLExpr(sql, JdbcUtils.MYSQL);
    }

    public static String formatMySql(String sql) {
        return format(sql, JdbcUtils.MYSQL);
    }

    public static String formatOracle(String sql) {
        return format(sql, JdbcUtils.ORACLE);
    }

    public static String toOracleString(SQLObject sqlObject) {
        StringBuilder out = new StringBuilder();
        sqlObject.accept(new OracleOutputVisitor(out, false));

        String sql = out.toString();
        return sql;
    }

    public static String toPGString(SQLObject sqlObject) {
        StringBuilder out = new StringBuilder();
        sqlObject.accept(new PGOutputVisitor(out));

        String sql = out.toString();
        return sql;
    }

    public static String toSQLServerString(SQLObject sqlObject) {
        StringBuilder out = new StringBuilder();
        sqlObject.accept(new SQLServerOutputVisitor(out));

        String sql = out.toString();
        return sql;
    }

    public static String formatPGSql(String sql) {
        return format(sql, JdbcUtils.POSTGRESQL);
    }

    public static SQLExpr toSQLExpr(String sql, String dbType) {
        SQLExprParser parser = SQLParserUtils.createExprParser(sql, dbType);
        SQLExpr expr = parser.expr();

        if (parser.getLexer().token() != Token.EOF) {
            throw new ParserException("illegal sql expr : " + sql);
        }

        return expr;
    }

    public static List<SQLStatement> toStatementList(String sql, String dbType) {
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
        return parser.parseStatementList();
    }

    public static SQLExpr toSQLExpr(String sql) {
        return toSQLExpr(sql, null);
    }
    
    public static String format(String sql, String dbType) {
        return format(sql, dbType, null);
    }

    public static String format(String sql, String dbType, List<Object> parameters) {
        try {
            List<SQLStatement> statementList = toStatementList(sql, dbType);

            return toSQLString(statementList, dbType, parameters);
        } catch (SQLParseException ex) {
            return sql;
        } catch (ParserException ex) {
            return sql;
        }
    }

    public static String toSQLString(List<SQLStatement> statementList, String dbType) {
        return toSQLString(statementList, dbType, null);
    }
    
    public static String toSQLString(List<SQLStatement> statementList, String dbType, List<Object> parameters) {
        StringBuilder out = new StringBuilder();
        SQLASTOutputVisitor visitor = createFormatOutputVisitor(out, statementList, dbType);
        if (parameters != null) {
            visitor.setParameters(parameters);
        }

        for (SQLStatement stmt : statementList) {
            stmt.accept(visitor);
        }

        return out.toString();
    }

    public static SQLASTOutputVisitor createFormatOutputVisitor(Appendable out, List<SQLStatement> statementList,
                                                                String dbType) {
        if (JdbcUtils.ORACLE.equals(dbType) || JdbcUtils.ALI_ORACLE.equals(dbType)) {
            if (statementList.size() == 1) {
                return new OracleOutputVisitor(out, false);
            } else {
                return new OracleOutputVisitor(out, true);
            }
        }

        if (JdbcUtils.MYSQL.equals(dbType)) {
            return new MySqlOutputVisitor(out);
        }

        if (JdbcUtils.POSTGRESQL.equals(dbType)) {
            return new PGOutputVisitor(out);
        }

        if (JdbcUtils.SQL_SERVER.equals(dbType)) {
            return new SQLServerOutputVisitor(out);
        }
        
        if (JdbcUtils.JTDS.equals(dbType)) {
            return new SQLServerOutputVisitor(out);
        }

        if (JdbcUtils.H2.equals(dbType)) {
            return new MySqlOutputVisitor(out);
        }

        return new SQLASTOutputVisitor(out);
    }

    public static SchemaStatVisitor createSchemaStatVisitor(List<SQLStatement> statementList, String dbType) {
        if (JdbcUtils.ORACLE.equals(dbType) || JdbcUtils.ALI_ORACLE.equals(dbType)) {
            if (statementList.size() == 1) {
                return new OracleSchemaStatVisitor();
            } else {
                return new OracleSchemaStatVisitor();
            }
        }

        if (JdbcUtils.MYSQL.equals(dbType)) {
            return new MySqlSchemaStatVisitor();
        }

        if (JdbcUtils.POSTGRESQL.equals(dbType)) {
            return new PGSchemaStatVisitor();
        }

        if (JdbcUtils.SQL_SERVER.equals(dbType)) {
            return new SQLServerSchemaStatVisitor();
        }
        
        if (JdbcUtils.JTDS.equals(dbType)) {
            return new SQLServerSchemaStatVisitor();
        }

        if (JdbcUtils.H2.equals(dbType)) {
            return new MySqlSchemaStatVisitor();
        }

        return new SchemaStatVisitor();
    }

    public static List<SQLStatement> parseStatements(String sql, String dbType) {
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
        List<SQLStatement> stmtList = parser.parseStatementList();
        if (parser.getLexer().token() != Token.EOF) {
            throw new RuntimeException("syntax error : " + sql);
        }
        return stmtList;
    }
}
