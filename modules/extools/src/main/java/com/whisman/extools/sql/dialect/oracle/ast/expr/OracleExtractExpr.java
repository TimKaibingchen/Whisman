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
package com.whisman.extools.sql.dialect.oracle.ast.expr;

import com.whisman.extools.sql.ast.SQLExpr;
import com.whisman.extools.sql.ast.SQLExprImpl;
import com.whisman.extools.sql.dialect.oracle.visitor.OracleASTVisitor;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

public class OracleExtractExpr extends SQLExprImpl implements OracleExpr {

    private static final long  serialVersionUID = 1L;
    private OracleDateTimeUnit unit;
    private SQLExpr            from;

    public OracleExtractExpr(){

    }

    public OracleDateTimeUnit getUnit() {
        return this.unit;
    }

    public void setUnit(OracleDateTimeUnit unit) {
        this.unit = unit;
    }

    public SQLExpr getFrom() {
        return this.from;
    }

    public void setFrom(SQLExpr from) {
        this.from = from;
    }

    public void output(StringBuffer buf) {
        buf.append("EXTRACT(");
        buf.append(this.unit.name());
        buf.append(" FROM ");
        this.from.output(buf);
        buf.append(")");
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        this.accept0((OracleASTVisitor) visitor);
    }

    public void accept0(OracleASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, this.from);
        }

        visitor.endVisit(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((from == null) ? 0 : from.hashCode());
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OracleExtractExpr other = (OracleExtractExpr) obj;
        if (from == null) {
            if (other.from != null) {
                return false;
            }
        } else if (!from.equals(other.from)) {
            return false;
        }
        if (unit != other.unit) {
            return false;
        }
        return true;
    }

}
