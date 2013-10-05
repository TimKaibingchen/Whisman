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

import com.whisman.extools.sql.ast.statement.SQLCreateIndexStatement;
import com.whisman.extools.sql.dialect.mysql.visitor.MySqlASTVisitor;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

public class MySqlCreateIndexStatement extends SQLCreateIndexStatement implements MySqlStatement {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String            using;

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    protected void accept0(SQLASTVisitor visitor) {
        accept0((MySqlASTVisitor) visitor);
    }

    @Override
    public void accept0(MySqlASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, getName());
            acceptChild(visitor, getTable());
            acceptChild(visitor, getItems());
        }
        visitor.endVisit(this);
    }
}
