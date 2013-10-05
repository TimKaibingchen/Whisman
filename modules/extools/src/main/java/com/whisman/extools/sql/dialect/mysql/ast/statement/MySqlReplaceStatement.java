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
package com.whisman.extools.sql.dialect.mysql.ast.statement;

import java.util.ArrayList;
import java.util.List;

import com.whisman.extools.sql.ast.SQLExpr;
import com.whisman.extools.sql.ast.SQLName;
import com.whisman.extools.sql.ast.expr.SQLQueryExpr;
import com.whisman.extools.sql.ast.statement.SQLInsertStatement.ValuesClause;
import com.whisman.extools.sql.dialect.mysql.visitor.MySqlASTVisitor;

public class MySqlReplaceStatement extends MySqlStatementImpl {

    private static final long      serialVersionUID = 1L;

    private boolean                lowPriority      = false;
    private boolean                delayed          = false;

    private SQLName                tableName;
    private final List<SQLExpr>    columns          = new ArrayList<SQLExpr>();
    private List<ValuesClause>     valuesList       = new ArrayList<ValuesClause>();
    private SQLQueryExpr           query;

    public SQLName getTableName() {
        return tableName;
    }

    public void setTableName(SQLName tableName) {
        this.tableName = tableName;
    }

    public List<SQLExpr> getColumns() {
        return columns;
    }

    public boolean isLowPriority() {
        return lowPriority;
    }

    public void setLowPriority(boolean lowPriority) {
        this.lowPriority = lowPriority;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }

    public SQLQueryExpr getQuery() {
        return query;
    }

    public void setQuery(SQLQueryExpr query) {
        query.setParent(this);
        this.query = query;
    }

    public List<ValuesClause> getValuesList() {
        return valuesList;
    }

    public void accept0(MySqlASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, tableName);
            acceptChild(visitor, columns);
            acceptChild(visitor, valuesList);
            acceptChild(visitor, query);
        }
        visitor.endVisit(this);
    }
}
