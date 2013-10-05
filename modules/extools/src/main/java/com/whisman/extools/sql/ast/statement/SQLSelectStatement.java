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
package com.whisman.extools.sql.ast.statement;

import com.whisman.extools.sql.ast.SQLStatementImpl;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

public class SQLSelectStatement extends SQLStatementImpl {

    private static final long serialVersionUID = 1L;

    protected SQLSelect         select;

    public SQLSelectStatement(){

    }

    public SQLSelectStatement(SQLSelect select){

        this.select = select;
    }

    public SQLSelect getSelect() {
        return this.select;
    }

    public void setSelect(SQLSelect select) {
        this.select = select;
    }

    public void output(StringBuffer buf) {
        this.select.output(buf);
    }

    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.select);
        }
        visitor.endVisit(this);
    }
}
