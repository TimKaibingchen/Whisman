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
package com.whisman.extools.sql.dialect.oracle.visitor;

import com.whisman.extools.sql.ast.expr.SQLBinaryOpExpr;
import com.whisman.extools.sql.ast.expr.SQLCharExpr;
import com.whisman.extools.sql.ast.expr.SQLInListExpr;
import com.whisman.extools.sql.ast.expr.SQLIntegerExpr;
import com.whisman.extools.sql.ast.expr.SQLNCharExpr;
import com.whisman.extools.sql.ast.expr.SQLNullExpr;
import com.whisman.extools.sql.ast.expr.SQLNumberExpr;
import com.whisman.extools.sql.visitor.ParameterizedOutputVisitorUtils;

public class OracleParameterizedOutputVisitor extends OracleOutputVisitor {

    public OracleParameterizedOutputVisitor() {
        this (new StringBuilder());
    }

    public OracleParameterizedOutputVisitor(Appendable appender){
        super(appender);
    }
    
    public OracleParameterizedOutputVisitor(Appendable appender, boolean printPostSemi){
        super (appender, printPostSemi);
    }

    public boolean visit(SQLInListExpr x) {
        return ParameterizedOutputVisitorUtils.visit(this, x);
    }

    public boolean visit(SQLBinaryOpExpr x) {
        x = ParameterizedOutputVisitorUtils.merge(x);

        return super.visit(x);
    }

    public boolean visit(SQLNullExpr x) {
        print('?');
        return false;
    }

    public boolean visit(SQLIntegerExpr x) {
        if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
            return super.visit(x);
        }

        print('?');
        return false;
    }

    public boolean visit(SQLNumberExpr x) {
        if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
            return super.visit(x);
        }

        print('?');
        return false;
    }

    public boolean visit(SQLCharExpr x) {
        if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
            return super.visit(x);
        }

        print('?');
        return false;
    }

    public boolean visit(SQLNCharExpr x) {
        if (Boolean.TRUE.equals(x.getAttribute(ParameterizedOutputVisitorUtils.ATTR_PARAMS_SKIP))) {
            return super.visit(x);
        }

        print('?');
        return false;
    }

}
