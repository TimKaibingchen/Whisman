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
package com.whisman.extools.sql.dialect.sqlserver.parser;

import com.whisman.extools.sql.ast.SQLSetQuantifier;
import com.whisman.extools.sql.ast.statement.SQLExprTableSource;
import com.whisman.extools.sql.ast.statement.SQLSelect;
import com.whisman.extools.sql.ast.statement.SQLSelectQuery;
import com.whisman.extools.sql.ast.statement.SQLTableSource;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerSelect;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerSelectQueryBlock;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerTop;
import com.whisman.extools.sql.parser.SQLExprParser;
import com.whisman.extools.sql.parser.SQLSelectParser;
import com.whisman.extools.sql.parser.Token;

public class SQLServerSelectParser extends SQLSelectParser {

    public SQLServerSelectParser(String sql){
        super(new SQLServerExprParser(sql));
    }

    public SQLServerSelectParser(SQLExprParser exprParser){
        super(exprParser);
    }

    public SQLSelect select()  {
        SQLServerSelect select = new SQLServerSelect();
        
        withSubquery(select);

        select.setQuery(query());
        select.setOrderBy(parseOrderBy());

        if (select.getOrderBy() == null) {
            select.setOrderBy(parseOrderBy());
        }

        return select;
    }

    public SQLSelectQuery query()  {
        if (lexer.token() == Token.LPAREN) {
            lexer.nextToken();

            SQLSelectQuery select = query();
            accept(Token.RPAREN);

            return queryRest(select);
        }

        SQLServerSelectQueryBlock queryBlock = new SQLServerSelectQueryBlock();

        if (lexer.token() == Token.SELECT) {
            lexer.nextToken();

            if (lexer.token() == Token.DISTINCT) {
                queryBlock.setDistionOption(SQLSetQuantifier.DISTINCT);
                lexer.nextToken();
            } else if (lexer.token() == Token.ALL) {
                queryBlock.setDistionOption(SQLSetQuantifier.ALL);
                lexer.nextToken();
            }

            if (lexer.token() == Token.TOP) {
                SQLServerTop top = new SQLServerTop();
                lexer.nextToken();
                top.setExpr(createExprParser().primary());
                queryBlock.setTop(top);
            }

            parseSelectList(queryBlock);
        }
        
        if (lexer.token() == Token.INTO) {
            lexer.nextToken();
            
            SQLTableSource into = this.parseTableSource();
            queryBlock.setInto((SQLExprTableSource) into);
        }

        parseFrom(queryBlock);

        parseWhere(queryBlock);

        parseGroupBy(queryBlock);

        return queryRest(queryBlock);
    }
    
    protected SQLExprParser createExprParser() {
        return new SQLServerExprParser(lexer);
    }
}
