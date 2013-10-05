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

import com.whisman.extools.sql.ast.SQLExpr;
import com.whisman.extools.sql.ast.SQLName;
import com.whisman.extools.sql.ast.expr.SQLPropertyExpr;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerTop;
import com.whisman.extools.sql.dialect.sqlserver.ast.expr.SQLServerObjectReferenceExpr;
import com.whisman.extools.sql.parser.Lexer;
import com.whisman.extools.sql.parser.SQLExprParser;
import com.whisman.extools.sql.parser.Token;

public class SQLServerExprParser extends SQLExprParser {

    public SQLServerExprParser(Lexer lexer){
        super(lexer);
    }

    public SQLServerExprParser(String sql) {
        super(new SQLServerLexer(sql));
        this.lexer.nextToken();
    }
    
    public SQLExpr primary() {
        
        
        if (lexer.token() == Token.LBRACKET) {
            lexer.nextToken();
            SQLExpr name = this.name();
            accept(Token.RBRACKET);
            return primaryRest(name);
        }
        
        return super.primary();
    }

    public SQLExpr primaryRest(SQLExpr expr) {
        if (lexer.token() == Token.DOTDOT) {
            expr = nameRest((SQLName) expr);
        }
        
        return super.primaryRest(expr);
    }
    
    protected SQLExpr dotRest(SQLExpr expr) {
        boolean backet = false;
        
        if (lexer.token() == Token.LBRACKET) {
            lexer.nextToken();
            backet = true;
        }
        
        expr = super.dotRest(expr);

        if (backet) {
            accept(Token.RBRACKET);
        }
        
        return expr;
    }
    
    public SQLName nameRest(SQLName expr) {
        if (lexer.token() == Token.DOTDOT) {
            lexer.nextToken();
            
            boolean backet = false;
            if (lexer.token() == Token.LBRACKET) {
                lexer.nextToken();
                backet = true;
            }
            String text = lexer.stringVal();
            lexer.nextToken();
            
            if (backet) {
                accept(Token.RBRACKET);
            }

            SQLServerObjectReferenceExpr owner = new SQLServerObjectReferenceExpr(expr);
            expr = new SQLPropertyExpr(owner, text);
        }

        return super.nameRest(expr);
    }
    
    public boolean isAggreateFunction(String word) {
        String[] aggregateFunctions = { "AVG", "COUNT", "MAX", "MIN", "ROW_NUMBER", "STDDEV", "SUM" };

        for (int i = 0; i < aggregateFunctions.length; ++i) {
            if (aggregateFunctions[i].compareToIgnoreCase(word) == 0) {
                return true;
            }
        }

        return false;
    }
    
    public SQLServerTop parseTop() {
        if (lexer.token() == Token.TOP) {
            SQLServerTop top = new SQLServerTop();
            lexer.nextToken();
            
            boolean paren = false;
            if (lexer.token() == Token.LPAREN) {
                paren = true;
                lexer.nextToken();
            }
            
            top.setExpr(primary());
            
            if (paren) {
                accept(Token.RPAREN);
            }
            
            return top;
        }
        
        return null;
    }
}