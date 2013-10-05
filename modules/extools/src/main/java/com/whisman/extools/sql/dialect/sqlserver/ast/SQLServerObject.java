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
package com.whisman.extools.sql.dialect.sqlserver.ast;

import com.whisman.extools.sql.dialect.sqlserver.visitor.SQLServerASTVisitor;
import com.whisman.extools.sql.dialect.transact.ast.TransactSQLObject;

public interface SQLServerObject extends TransactSQLObject {
    public void accept0(SQLServerASTVisitor visitor);
}