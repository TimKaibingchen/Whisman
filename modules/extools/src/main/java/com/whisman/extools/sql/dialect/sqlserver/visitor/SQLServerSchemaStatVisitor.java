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
package com.whisman.extools.sql.dialect.sqlserver.visitor;

import java.util.Map;

import com.whisman.extools.sql.ast.statement.SQLInsertStatement;
import com.whisman.extools.sql.ast.statement.SQLSelectQueryBlock;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerSelectQueryBlock;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerTop;
import com.whisman.extools.sql.dialect.sqlserver.ast.expr.SQLServerObjectReferenceExpr;
import com.whisman.extools.sql.dialect.sqlserver.ast.stmt.SQLServerInsertStatement;
import com.whisman.extools.sql.dialect.sqlserver.ast.stmt.SQLServerUpdateStatement;
import com.whisman.extools.sql.visitor.SchemaStatVisitor;
import com.whisman.extools.stat.TableStat;
import com.whisman.extools.util.JdbcUtils;


public class SQLServerSchemaStatVisitor extends SchemaStatVisitor implements SQLServerASTVisitor {

    @Override
    public String getDbType() {
        return JdbcUtils.SQL_SERVER;
    }
    
    @Override
    public boolean visit(SQLServerSelectQueryBlock x) {
        return visit((SQLSelectQueryBlock) x);
    }

    @Override
    public void endVisit(SQLServerSelectQueryBlock x) {
        endVisit((SQLSelectQueryBlock) x);
    }

    @Override
    public boolean visit(SQLServerTop x) {
        return false;
    }

    @Override
    public void endVisit(SQLServerTop x) {
        
    }

    @Override
    public boolean visit(SQLServerObjectReferenceExpr x) {
        return false;
    }

    @Override
    public void endVisit(SQLServerObjectReferenceExpr x) {
        
    }

    @Override
    public boolean visit(SQLServerInsertStatement x) {
        this.visit((SQLInsertStatement) x);
        return false;
    }

    @Override
    public void endVisit(SQLServerInsertStatement x) {
        this.endVisit((SQLInsertStatement) x);        
    }

    @Override
    public boolean visit(SQLServerUpdateStatement x) {
        setAliasMap();

        String ident = x.getTableName().toString();
        setCurrentTable(ident);

        TableStat stat = getTableStat(ident);
        stat.incrementUpdateCount();

        Map<String, String> aliasMap = getAliasMap();
        aliasMap.put(ident, ident);

        accept(x.getItems());
        accept(x.getFrom());
        accept(x.getWhere());

        return false;
    }

    @Override
    public void endVisit(SQLServerUpdateStatement x) {
        
    }


}
