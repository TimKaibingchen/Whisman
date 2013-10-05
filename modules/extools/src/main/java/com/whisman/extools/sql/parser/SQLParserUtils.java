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
package com.whisman.extools.sql.parser;

import com.whisman.extools.sql.dialect.mysql.parser.MySqlExprParser;
import com.whisman.extools.sql.dialect.mysql.parser.MySqlStatementParser;
import com.whisman.extools.sql.dialect.oracle.parser.OracleExprParser;
import com.whisman.extools.sql.dialect.oracle.parser.OracleStatementParser;
import com.whisman.extools.sql.dialect.postgresql.parser.PGExprParser;
import com.whisman.extools.sql.dialect.postgresql.parser.PGSQLStatementParser;
import com.whisman.extools.sql.dialect.sqlserver.parser.SQLServerExprParser;
import com.whisman.extools.sql.dialect.sqlserver.parser.SQLServerStatementParser;
import com.whisman.extools.util.JdbcUtils;

public class SQLParserUtils {

    public static SQLStatementParser createSQLStatementParser(String sql, String dbType) {
        if (JdbcUtils.ORACLE.equals(dbType) || JdbcUtils.ALI_ORACLE.equals(dbType)) {
            return new OracleStatementParser(sql);
        }

        if (JdbcUtils.MYSQL.equals(dbType)) {
            return new MySqlStatementParser(sql);
        }

        if (JdbcUtils.POSTGRESQL.equals(dbType)) {
            return new PGSQLStatementParser(sql);
        }

        if (JdbcUtils.SQL_SERVER.equals(dbType)) {
            return new SQLServerStatementParser(sql);
        }
        
        if (JdbcUtils.JTDS.equals(dbType)) {
            return new SQLServerStatementParser(sql);
        }

        if (JdbcUtils.H2.equals(dbType)) {
            return new MySqlStatementParser(sql);
        }

        return new SQLStatementParser(sql);
    }

    public static SQLExprParser createExprParser(String sql, String dbType) {
        if (JdbcUtils.ORACLE.equals(dbType) || JdbcUtils.ALI_ORACLE.equals(dbType)) {
            return new OracleExprParser(sql);
        }

        if (JdbcUtils.H2.equals(dbType)) {
            return new MySqlExprParser(sql);
        }

        if (JdbcUtils.MYSQL.equals(dbType)) {
            return new MySqlExprParser(sql);
        }

        if (JdbcUtils.POSTGRESQL.equals(dbType)) {
            return new PGExprParser(sql);
        }

        if (JdbcUtils.SQL_SERVER.equals(dbType)) {
            return new SQLServerExprParser(sql);
        }
        
        if (JdbcUtils.JTDS.equals(dbType)) {
            return new SQLServerExprParser(sql);
        }

        return new SQLExprParser(sql);
    }
}
