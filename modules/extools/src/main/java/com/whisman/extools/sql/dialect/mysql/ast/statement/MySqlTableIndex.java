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
import com.whisman.extools.sql.ast.SQLObjectImpl;
import com.whisman.extools.sql.ast.statement.SQLTableElement;
import com.whisman.extools.sql.dialect.mysql.visitor.MySqlASTVisitor;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

@SuppressWarnings("serial")
public class MySqlTableIndex extends SQLObjectImpl implements SQLTableElement {

    private SQLName       name;
    private String        indexType;
    private List<SQLExpr> columns = new ArrayList<SQLExpr>();

    public MySqlTableIndex(){

    }

    public SQLName getName() {
        return name;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public void setName(SQLName name) {
        this.name = name;
    }

    public List<SQLExpr> getColumns() {
        return columns;
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor instanceof MySqlASTVisitor) {
            accept0((MySqlASTVisitor) visitor);
            return;
        }

        if (visitor.visit(this)) {
            acceptChild(visitor, name);
            acceptChild(visitor, columns);
        }
        visitor.endVisit(this);
    }

    protected void accept0(MySqlASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, name);
            acceptChild(visitor, columns);
        }
        visitor.endVisit(this);
    }
}
