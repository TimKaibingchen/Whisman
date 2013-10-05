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
package com.whisman.extools.sql.visitor;

import com.whisman.extools.sql.ast.SQLCommentHint;
import com.whisman.extools.sql.ast.SQLDataType;
import com.whisman.extools.sql.ast.SQLObject;
import com.whisman.extools.sql.ast.SQLOrderBy;
import com.whisman.extools.sql.ast.SQLOver;
import com.whisman.extools.sql.ast.expr.SQLAggregateExpr;
import com.whisman.extools.sql.ast.expr.SQLAllColumnExpr;
import com.whisman.extools.sql.ast.expr.SQLAllExpr;
import com.whisman.extools.sql.ast.expr.SQLAnyExpr;
import com.whisman.extools.sql.ast.expr.SQLBetweenExpr;
import com.whisman.extools.sql.ast.expr.SQLBinaryOpExpr;
import com.whisman.extools.sql.ast.expr.SQLBitStringLiteralExpr;
import com.whisman.extools.sql.ast.expr.SQLCaseExpr;
import com.whisman.extools.sql.ast.expr.SQLCastExpr;
import com.whisman.extools.sql.ast.expr.SQLCharExpr;
import com.whisman.extools.sql.ast.expr.SQLCurrentOfCursorExpr;
import com.whisman.extools.sql.ast.expr.SQLDateLiteralExpr;
import com.whisman.extools.sql.ast.expr.SQLDefaultExpr;
import com.whisman.extools.sql.ast.expr.SQLExistsExpr;
import com.whisman.extools.sql.ast.expr.SQLHexExpr;
import com.whisman.extools.sql.ast.expr.SQLHexStringLiteralExpr;
import com.whisman.extools.sql.ast.expr.SQLIdentifierExpr;
import com.whisman.extools.sql.ast.expr.SQLInListExpr;
import com.whisman.extools.sql.ast.expr.SQLInSubQueryExpr;
import com.whisman.extools.sql.ast.expr.SQLIntegerExpr;
import com.whisman.extools.sql.ast.expr.SQLIntervalLiteralExpr;
import com.whisman.extools.sql.ast.expr.SQLListExpr;
import com.whisman.extools.sql.ast.expr.SQLMethodInvokeExpr;
import com.whisman.extools.sql.ast.expr.SQLNCharExpr;
import com.whisman.extools.sql.ast.expr.SQLNotExpr;
import com.whisman.extools.sql.ast.expr.SQLNullExpr;
import com.whisman.extools.sql.ast.expr.SQLNumberExpr;
import com.whisman.extools.sql.ast.expr.SQLPropertyExpr;
import com.whisman.extools.sql.ast.expr.SQLQueryExpr;
import com.whisman.extools.sql.ast.expr.SQLSomeExpr;
import com.whisman.extools.sql.ast.expr.SQLUnaryExpr;
import com.whisman.extools.sql.ast.expr.SQLVariantRefExpr;
import com.whisman.extools.sql.ast.statement.NotNullConstraint;
import com.whisman.extools.sql.ast.statement.SQLAlterTableAddColumn;
import com.whisman.extools.sql.ast.statement.SQLAlterTableAddPrimaryKey;
import com.whisman.extools.sql.ast.statement.SQLAlterTableDropColumnItem;
import com.whisman.extools.sql.ast.statement.SQLAlterTableDropIndex;
import com.whisman.extools.sql.ast.statement.SQLAssignItem;
import com.whisman.extools.sql.ast.statement.SQLCallStatement;
import com.whisman.extools.sql.ast.statement.SQLCharactorDataType;
import com.whisman.extools.sql.ast.statement.SQLColumnDefinition;
import com.whisman.extools.sql.ast.statement.SQLColumnPrimaryKey;
import com.whisman.extools.sql.ast.statement.SQLColumnUniqueIndex;
import com.whisman.extools.sql.ast.statement.SQLCommentStatement;
import com.whisman.extools.sql.ast.statement.SQLCreateDatabaseStatement;
import com.whisman.extools.sql.ast.statement.SQLCreateTableStatement;
import com.whisman.extools.sql.ast.statement.SQLCreateViewStatement;
import com.whisman.extools.sql.ast.statement.SQLDeleteStatement;
import com.whisman.extools.sql.ast.statement.SQLDropIndexStatement;
import com.whisman.extools.sql.ast.statement.SQLDropTableStatement;
import com.whisman.extools.sql.ast.statement.SQLDropViewStatement;
import com.whisman.extools.sql.ast.statement.SQLExprTableSource;
import com.whisman.extools.sql.ast.statement.SQLInsertStatement;
import com.whisman.extools.sql.ast.statement.SQLJoinTableSource;
import com.whisman.extools.sql.ast.statement.SQLReleaseSavePointStatement;
import com.whisman.extools.sql.ast.statement.SQLRollbackStatement;
import com.whisman.extools.sql.ast.statement.SQLSavePointStatement;
import com.whisman.extools.sql.ast.statement.SQLSelect;
import com.whisman.extools.sql.ast.statement.SQLSelectGroupByClause;
import com.whisman.extools.sql.ast.statement.SQLSelectItem;
import com.whisman.extools.sql.ast.statement.SQLSelectOrderByItem;
import com.whisman.extools.sql.ast.statement.SQLSelectQueryBlock;
import com.whisman.extools.sql.ast.statement.SQLSelectStatement;
import com.whisman.extools.sql.ast.statement.SQLSetStatement;
import com.whisman.extools.sql.ast.statement.SQLSubqueryTableSource;
import com.whisman.extools.sql.ast.statement.SQLTableElement;
import com.whisman.extools.sql.ast.statement.SQLTruncateStatement;
import com.whisman.extools.sql.ast.statement.SQLUnionQuery;
import com.whisman.extools.sql.ast.statement.SQLUniqueConstraint;
import com.whisman.extools.sql.ast.statement.SQLUpdateSetItem;
import com.whisman.extools.sql.ast.statement.SQLUpdateStatement;
import com.whisman.extools.sql.ast.statement.SQLUseStatement;
import com.whisman.extools.sql.ast.statement.SQLWithSubqueryClause;

public interface SQLASTVisitor {

    void endVisit(SQLAllColumnExpr x);

    void endVisit(SQLBetweenExpr x);

    void endVisit(SQLBinaryOpExpr x);

    void endVisit(SQLCaseExpr x);

    void endVisit(SQLCaseExpr.Item x);

    void endVisit(SQLCharExpr x);

    void endVisit(SQLIdentifierExpr x);

    void endVisit(SQLInListExpr x);

    void endVisit(SQLIntegerExpr x);

    void endVisit(SQLExistsExpr x);

    void endVisit(SQLNCharExpr x);

    void endVisit(SQLNotExpr x);

    void endVisit(SQLNullExpr x);

    void endVisit(SQLNumberExpr x);

    void endVisit(SQLPropertyExpr x);

    void endVisit(SQLSelectGroupByClause x);

    void endVisit(SQLSelectItem x);

    void endVisit(SQLSelectStatement selectStatement);

    void postVisit(SQLObject astNode);

    void preVisit(SQLObject astNode);

    boolean visit(SQLAllColumnExpr x);

    boolean visit(SQLBetweenExpr x);

    boolean visit(SQLBinaryOpExpr x);

    boolean visit(SQLCaseExpr x);

    boolean visit(SQLCaseExpr.Item x);

    boolean visit(SQLCastExpr x);

    boolean visit(SQLCharExpr x);

    boolean visit(SQLExistsExpr x);

    boolean visit(SQLIdentifierExpr x);

    boolean visit(SQLInListExpr x);

    boolean visit(SQLIntegerExpr x);

    boolean visit(SQLNCharExpr x);

    boolean visit(SQLNotExpr x);

    boolean visit(SQLNullExpr x);

    boolean visit(SQLNumberExpr x);

    boolean visit(SQLPropertyExpr x);

    boolean visit(SQLSelectGroupByClause x);

    boolean visit(SQLSelectItem x);

    void endVisit(SQLCastExpr x);

    boolean visit(SQLSelectStatement astNode);

    void endVisit(SQLAggregateExpr astNode);

    boolean visit(SQLAggregateExpr astNode);

    boolean visit(SQLVariantRefExpr x);

    void endVisit(SQLVariantRefExpr x);

    boolean visit(SQLQueryExpr x);

    void endVisit(SQLQueryExpr x);

    boolean visit(SQLUnaryExpr x);

    void endVisit(SQLUnaryExpr x);

    boolean visit(SQLHexExpr x);

    void endVisit(SQLHexExpr x);

    boolean visit(SQLBitStringLiteralExpr x);

    void endVisit(SQLBitStringLiteralExpr x);

    boolean visit(SQLHexStringLiteralExpr x);

    void endVisit(SQLHexStringLiteralExpr x);

    boolean visit(SQLDateLiteralExpr x);

    void endVisit(SQLDateLiteralExpr x);

    boolean visit(SQLSelect x);

    void endVisit(SQLSelect select);

    boolean visit(SQLSelectQueryBlock x);

    void endVisit(SQLSelectQueryBlock x);

    boolean visit(SQLExprTableSource x);

    void endVisit(SQLExprTableSource x);

    boolean visit(SQLIntervalLiteralExpr x);

    void endVisit(SQLIntervalLiteralExpr x);

    boolean visit(SQLOrderBy x);

    void endVisit(SQLOrderBy x);

    boolean visit(SQLSelectOrderByItem x);

    void endVisit(SQLSelectOrderByItem x);

    boolean visit(SQLDropTableStatement x);

    void endVisit(SQLDropTableStatement x);

    boolean visit(SQLCreateTableStatement x);

    void endVisit(SQLCreateTableStatement x);

    boolean visit(SQLTableElement x);

    void endVisit(SQLTableElement x);

    boolean visit(SQLColumnDefinition x);

    void endVisit(SQLColumnDefinition x);

    boolean visit(SQLDataType x);

    void endVisit(SQLDataType x);
    
    boolean visit(SQLCharactorDataType x);

    void endVisit(SQLCharactorDataType x);

    boolean visit(SQLDeleteStatement x);

    void endVisit(SQLDeleteStatement x);

    boolean visit(SQLCurrentOfCursorExpr x);

    void endVisit(SQLCurrentOfCursorExpr x);

    boolean visit(SQLInsertStatement x);

    void endVisit(SQLInsertStatement x);

    boolean visit(SQLInsertStatement.ValuesClause x);

    void endVisit(SQLInsertStatement.ValuesClause x);

    boolean visit(SQLUpdateSetItem x);

    void endVisit(SQLUpdateSetItem x);

    boolean visit(SQLUpdateStatement x);

    void endVisit(SQLUpdateStatement x);

    boolean visit(SQLCreateViewStatement x);

    void endVisit(SQLCreateViewStatement x);

    boolean visit(SQLUniqueConstraint x);

    void endVisit(SQLUniqueConstraint x);

    boolean visit(NotNullConstraint x);

    void endVisit(NotNullConstraint x);

    void endVisit(SQLMethodInvokeExpr x);

    boolean visit(SQLMethodInvokeExpr x);

    void endVisit(SQLUnionQuery x);

    boolean visit(SQLUnionQuery x);

    void endVisit(SQLSetStatement x);

    boolean visit(SQLSetStatement x);

    void endVisit(SQLAssignItem x);

    boolean visit(SQLAssignItem x);

    void endVisit(SQLCallStatement x);

    boolean visit(SQLCallStatement x);

    void endVisit(SQLJoinTableSource x);

    boolean visit(SQLJoinTableSource x);

    void endVisit(SQLSomeExpr x);

    boolean visit(SQLSomeExpr x);

    void endVisit(SQLAnyExpr x);

    boolean visit(SQLAnyExpr x);

    void endVisit(SQLAllExpr x);

    boolean visit(SQLAllExpr x);

    void endVisit(SQLInSubQueryExpr x);

    boolean visit(SQLInSubQueryExpr x);

    void endVisit(SQLListExpr x);

    boolean visit(SQLListExpr x);

    void endVisit(SQLSubqueryTableSource x);

    boolean visit(SQLSubqueryTableSource x);

    void endVisit(SQLTruncateStatement x);

    boolean visit(SQLTruncateStatement x);

    void endVisit(SQLDefaultExpr x);

    boolean visit(SQLDefaultExpr x);

    void endVisit(SQLCommentStatement x);

    boolean visit(SQLCommentStatement x);

    void endVisit(SQLUseStatement x);

    boolean visit(SQLUseStatement x);

    boolean visit(SQLAlterTableAddColumn x);

    void endVisit(SQLAlterTableAddColumn x);

    boolean visit(SQLAlterTableDropColumnItem x);

    void endVisit(SQLAlterTableDropColumnItem x);
    
    boolean visit(SQLAlterTableDropIndex x);
    
    void endVisit(SQLAlterTableDropIndex x);
    
    boolean visit(SQLAlterTableAddPrimaryKey x);
    
    void endVisit(SQLAlterTableAddPrimaryKey x);

    boolean visit(SQLDropIndexStatement x);

    void endVisit(SQLDropIndexStatement x);

    boolean visit(SQLDropViewStatement x);

    void endVisit(SQLDropViewStatement x);

    boolean visit(SQLSavePointStatement x);

    void endVisit(SQLSavePointStatement x);

    boolean visit(SQLRollbackStatement x);

    void endVisit(SQLRollbackStatement x);

    boolean visit(SQLReleaseSavePointStatement x);

    void endVisit(SQLReleaseSavePointStatement x);

    void endVisit(SQLCommentHint x);

    boolean visit(SQLCommentHint x);

    void endVisit(SQLCreateDatabaseStatement x);

    boolean visit(SQLCreateDatabaseStatement x);
    
    void endVisit(SQLOver x);
    
    boolean visit(SQLOver x);
    
    void endVisit(SQLColumnPrimaryKey x);
    
    boolean visit(SQLColumnPrimaryKey x);
    
    void endVisit(SQLColumnUniqueIndex x);
    
    boolean visit(SQLColumnUniqueIndex x);
    
    void endVisit(SQLWithSubqueryClause x);
    
    boolean visit(SQLWithSubqueryClause x);
    
    void endVisit(SQLWithSubqueryClause.Entry x);
    
    boolean visit(SQLWithSubqueryClause.Entry x);
}
