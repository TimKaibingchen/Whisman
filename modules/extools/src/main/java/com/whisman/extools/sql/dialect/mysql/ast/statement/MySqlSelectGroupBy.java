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

import com.whisman.extools.sql.ast.statement.SQLSelectGroupByClause;
import com.whisman.extools.sql.dialect.mysql.visitor.MySqlASTVisitor;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

public class MySqlSelectGroupBy extends SQLSelectGroupByClause {

    private static final long serialVersionUID = 1L;

    private boolean           rollUp           = false;

    public boolean isRollUp() {
        return rollUp;
    }

    public void setRollUp(boolean rollUp) {
        this.rollUp = rollUp;
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor instanceof MySqlASTVisitor) {
            accept0((MySqlASTVisitor) visitor);
        } else {
            if (visitor.visit(this)) {
                acceptChild(visitor, this.getItems());
                acceptChild(visitor, this.getHaving());
            }

            visitor.endVisit(this);
        }
    }

    protected void accept0(MySqlASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.getItems());
            acceptChild(visitor, this.getHaving());
        }

        visitor.endVisit(this);
    }
}
