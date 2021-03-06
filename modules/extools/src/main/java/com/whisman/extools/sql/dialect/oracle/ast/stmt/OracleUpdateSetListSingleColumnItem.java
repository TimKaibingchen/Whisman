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
package com.whisman.extools.sql.dialect.oracle.ast.stmt;

import com.whisman.extools.sql.ast.SQLExpr;
import com.whisman.extools.sql.dialect.oracle.visitor.OracleASTVisitor;

public class OracleUpdateSetListSingleColumnItem extends OracleUpdateSetListItem {

    private static final long serialVersionUID = 1L;

    private SQLExpr           column;
    private SQLExpr           value;

    public OracleUpdateSetListSingleColumnItem(){

    }

    public void accept0(OracleASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.column);
            acceptChild(visitor, this.value);
        }

        visitor.endVisit(this);
    }

    public SQLExpr getColumn() {
        return this.column;
    }

    public void setColumn(SQLExpr column) {
        this.column = column;
    }

    public SQLExpr getValue() {
        return this.value;
    }

    public void setValue(SQLExpr value) {
        this.value = value;
    }
}
