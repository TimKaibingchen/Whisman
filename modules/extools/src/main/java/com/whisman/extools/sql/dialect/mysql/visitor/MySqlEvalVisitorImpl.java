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
package com.whisman.extools.sql.dialect.mysql.visitor;

import java.util.ArrayList;
import java.util.List;

import com.whisman.extools.sql.ast.expr.SQLBetweenExpr;
import com.whisman.extools.sql.ast.expr.SQLBinaryOpExpr;
import com.whisman.extools.sql.ast.expr.SQLCaseExpr;
import com.whisman.extools.sql.ast.expr.SQLCharExpr;
import com.whisman.extools.sql.ast.expr.SQLInListExpr;
import com.whisman.extools.sql.ast.expr.SQLIntegerExpr;
import com.whisman.extools.sql.ast.expr.SQLMethodInvokeExpr;
import com.whisman.extools.sql.ast.expr.SQLNullExpr;
import com.whisman.extools.sql.ast.expr.SQLNumberExpr;
import com.whisman.extools.sql.ast.expr.SQLQueryExpr;
import com.whisman.extools.sql.ast.expr.SQLVariantRefExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlBooleanExpr;
import com.whisman.extools.sql.visitor.SQLEvalVisitor;
import com.whisman.extools.sql.visitor.SQLEvalVisitorUtils;

public class MySqlEvalVisitorImpl extends MySqlASTVisitorAdapter implements SQLEvalVisitor {

    private List<Object> parameters       = new ArrayList<Object>();

    private int          variantIndex     = -1;

    private boolean      markVariantIndex = true;

    public MySqlEvalVisitorImpl(){
        this(new ArrayList<Object>(1));
    }

    public MySqlEvalVisitorImpl(List<Object> parameters){
        this.parameters = parameters;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }

    public boolean visit(SQLCharExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    public int incrementAndGetVariantIndex() {
        return ++variantIndex;
    }

    public int getVariantIndex() {
        return variantIndex;
    }

    public boolean visit(SQLVariantRefExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    public boolean visit(SQLBinaryOpExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    public boolean visit(SQLIntegerExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    public boolean visit(SQLNumberExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(SQLCaseExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(SQLBetweenExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(SQLInListExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(SQLNullExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(SQLMethodInvokeExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(SQLQueryExpr x) {
        return SQLEvalVisitorUtils.visit(this, x);
    }

    @Override
    public boolean visit(MySqlBooleanExpr x) {
        x.getAttributes().put(EVAL_VALUE, x.getValue());
        return false;
    }

    public boolean isMarkVariantIndex() {
        return markVariantIndex;
    }

    public void setMarkVariantIndex(boolean markVariantIndex) {
        this.markVariantIndex = markVariantIndex;
    }

}
