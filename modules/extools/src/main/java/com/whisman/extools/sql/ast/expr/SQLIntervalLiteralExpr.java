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
package com.whisman.extools.sql.ast.expr;

import com.whisman.extools.sql.ast.SQLExprImpl;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

/**
 * TODO
 * 
 * @author WENSHAO
 */
public class SQLIntervalLiteralExpr extends SQLExprImpl implements SQLLiteralExpr {

    private static final long serialVersionUID = 1L;

    private Character         sign             = null;

    public Character getSign() {
        return sign;
    }

    public void setSign(Character sign) {
        this.sign = sign;
    }

    public SQLIntervalLiteralExpr(){

    }

    @Override
    public void output(StringBuffer buf) {
        buf.append("INTERVAL");
        if (sign != null) {
            buf.append(sign.charValue());
        }
        throw new RuntimeException("TODO");
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        visitor.visit(this);

        visitor.endVisit(this);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sign == null) ? 0 : sign.hashCode());
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
        SQLIntervalLiteralExpr other = (SQLIntervalLiteralExpr) obj;
        if (sign == null) {
            if (other.sign != null) {
                return false;
            }
        } else if (!sign.equals(other.sign)) {
            return false;
        }
        return true;
    }

}
