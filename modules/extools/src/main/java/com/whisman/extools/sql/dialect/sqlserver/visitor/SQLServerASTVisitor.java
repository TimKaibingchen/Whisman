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
package com.whisman.extools.sql.dialect.sqlserver.visitor;

import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerSelectQueryBlock;
import com.whisman.extools.sql.dialect.sqlserver.ast.SQLServerTop;
import com.whisman.extools.sql.dialect.sqlserver.ast.expr.SQLServerObjectReferenceExpr;
import com.whisman.extools.sql.dialect.sqlserver.ast.stmt.SQLServerInsertStatement;
import com.whisman.extools.sql.dialect.sqlserver.ast.stmt.SQLServerUpdateStatement;
import com.whisman.extools.sql.visitor.SQLASTVisitor;

public interface SQLServerASTVisitor extends SQLASTVisitor {

    boolean visit(SQLServerSelectQueryBlock x);

    void endVisit(SQLServerSelectQueryBlock x);

    boolean visit(SQLServerTop x);

    void endVisit(SQLServerTop x);
    
    boolean visit(SQLServerObjectReferenceExpr x);
    
    void endVisit(SQLServerObjectReferenceExpr x);
    
    boolean visit(SQLServerInsertStatement x);
    
    void endVisit(SQLServerInsertStatement x);

    boolean visit(SQLServerUpdateStatement x);
    
    void endVisit(SQLServerUpdateStatement x);
}
