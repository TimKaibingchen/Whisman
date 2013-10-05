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

import com.whisman.extools.sql.ast.SQLExpr;
import com.whisman.extools.sql.ast.SQLObjectImpl;
import com.whisman.extools.sql.ast.SQLStatement;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

public class SQLCommentStatement extends SQLObjectImpl implements SQLStatement {

    private static final long serialVersionUID = 1L;

    public static enum Type {
        TABLE, COLUMN
    }

    private SQLExpr on;
    private Type    type;
    private SQLExpr comment;

    public SQLExpr getComment() {
        return comment;
    }

    public void setComment(SQLExpr comment) {
        this.comment = comment;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public SQLExpr getOn() {
        return on;
    }

    public void setOn(SQLExpr on) {
        this.on = on;
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, on);
            acceptChild(visitor, comment);
        }
        visitor.endVisit(this);
    }

}
