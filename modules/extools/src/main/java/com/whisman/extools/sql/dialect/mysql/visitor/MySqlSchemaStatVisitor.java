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

import java.util.Map;

import com.whisman.extools.sql.ast.SQLName;
import com.whisman.extools.sql.ast.expr.SQLIdentifierExpr;
import com.whisman.extools.sql.ast.statement.SQLCreateTableStatement;
import com.whisman.extools.sql.ast.statement.SQLDropTableStatement;
import com.whisman.extools.sql.ast.statement.SQLExprTableSource;
import com.whisman.extools.sql.ast.statement.SQLSelectQueryBlock;
import com.whisman.extools.sql.ast.statement.SQLSelectStatement;
import com.whisman.extools.sql.ast.statement.SQLUnionQuery;
import com.whisman.extools.sql.ast.statement.SQLUpdateStatement;
import com.whisman.extools.sql.dialect.mysql.ast.MySqlForceIndexHint;
import com.whisman.extools.sql.dialect.mysql.ast.MySqlIgnoreIndexHint;
import com.whisman.extools.sql.dialect.mysql.ast.MySqlKey;
import com.whisman.extools.sql.dialect.mysql.ast.MySqlPrimaryKey;
import com.whisman.extools.sql.dialect.mysql.ast.MySqlUseIndexHint;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlBinaryExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlBooleanExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlCharExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlExtractExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlIntervalExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlMatchAgainstExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlOutFileExpr;
import com.whisman.extools.sql.dialect.mysql.ast.expr.MySqlUserName;
import com.whisman.extools.sql.dialect.mysql.ast.statement.CobarShowStatus;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableAddColumn;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableAddIndex;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableAddUnique;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableChangeColumn;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableCharacter;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableOption;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlAlterTableStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlBinlogStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlCommitStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlCreateIndexStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlCreateUserStatement.UserSpecification;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlDeleteStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlDescribeStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlDropTableStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlDropUser;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlDropViewStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlExecuteStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlHelpStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlKillStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlLoadDataInFileStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlLoadXmlStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlLockTableStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlPartitionByKey;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlPrepareStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlRenameTableStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlReplaceStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlResetStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlRollbackStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlSelectGroupBy;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock.Limit;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlSetCharSetStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlSetNamesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlSetTransactionIsolationLevelStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowAuthorsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowBinLogEventsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowBinaryLogsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCharacterSetStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCollationStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowColumnsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowContributorsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateDatabaseStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateEventStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateFunctionStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateProcedureStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateTableStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateTriggerStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowCreateViewStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowDatabasesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowEngineStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowEnginesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowErrorsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowEventsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowFunctionCodeStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowFunctionStatusStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowGrantsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowIndexesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowKeysStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowMasterLogsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowMasterStatusStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowOpenTablesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowPluginsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowPrivilegesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowProcedureCodeStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowProcedureStatusStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowProcessListStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowProfileStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowProfilesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowRelayLogEventsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowSlaveHostsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowSlaveStatusStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowStatusStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowTableStatusStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowTablesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowTriggersStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowVariantsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlShowWarningsStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlStartTransactionStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlTableIndex;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlUnionQuery;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlUnlockTablesStatement;
import com.whisman.extools.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.whisman.extools.sql.visitor.SchemaStatVisitor;
import com.whisman.extools.stat.TableStat;
import com.whisman.extools.stat.TableStat.Mode;
import com.whisman.extools.util.JdbcUtils;

public class MySqlSchemaStatVisitor extends SchemaStatVisitor implements MySqlASTVisitor {

    public boolean visit(SQLSelectStatement x) {
        setAliasMap();
        getAliasMap().put("DUAL", null);

        return true;
    }
    
    @Override
    public String getDbType() {
        return JdbcUtils.MYSQL;
    }

    // DUAL
    public boolean visit(MySqlDeleteStatement x) {
        setAliasMap();

        setMode(x, Mode.Delete);

        accept(x.getFrom());
        accept(x.getUsing());
        x.getTableSource().accept(this);

        if (x.getTableSource() instanceof SQLExprTableSource) {
            SQLName tableName = (SQLName) ((SQLExprTableSource) x.getTableSource()).getExpr();
            String ident = tableName.toString();
            setCurrentTable(x, ident);

            TableStat stat = this.getTableStat(ident);
            stat.incrementDeleteCount();
        }

        accept(x.getWhere());

        accept(x.getOrderBy());
        accept(x.getLimit());

        return false;
    }

    public void endVisit(MySqlDeleteStatement x) {
        setAliasMap(null);
    }

    @Override
    public void endVisit(MySqlInsertStatement x) {
        setModeOrigin(x);
    }

    @Override
    public boolean visit(MySqlInsertStatement x) {
        setMode(x, Mode.Insert);

        setAliasMap();

        if (x.getTableName() instanceof SQLIdentifierExpr) {
            String ident = ((SQLIdentifierExpr) x.getTableName()).getName();
            setCurrentTable(x, ident);

            TableStat stat = getTableStat(ident);
            stat.incrementInsertCount();

            Map<String, String> aliasMap = getAliasMap();
            if (aliasMap != null) {
                if (x.getAlias() != null) {
                    aliasMap.put(x.getAlias(), ident);
                }
                aliasMap.put(ident, ident);
            }
        }

        accept(x.getColumns());
        accept(x.getValuesList());
        accept(x.getQuery());
        accept(x.getDuplicateKeyUpdate());

        return false;
    }

    @Override
    public boolean visit(MySqlBooleanExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlBooleanExpr x) {

    }

    @Override
    public boolean visit(Limit x) {

        return true;
    }

    @Override
    public void endVisit(Limit x) {

    }

    @Override
    public boolean visit(MySqlTableIndex x) {

        return true;
    }

    @Override
    public void endVisit(MySqlTableIndex x) {

    }

    @Override
    public boolean visit(MySqlKey x) {

        return true;
    }

    @Override
    public void endVisit(MySqlKey x) {

    }

    @Override
    public boolean visit(MySqlPrimaryKey x) {

        return true;
    }

    @Override
    public void endVisit(MySqlPrimaryKey x) {

    }

    @Override
    public void endVisit(MySqlIntervalExpr x) {

    }

    @Override
    public boolean visit(MySqlIntervalExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlExtractExpr x) {

    }

    @Override
    public boolean visit(MySqlExtractExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlMatchAgainstExpr x) {

    }

    @Override
    public boolean visit(MySqlMatchAgainstExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlBinaryExpr x) {

    }

    @Override
    public boolean visit(MySqlBinaryExpr x) {

        return true;
    }

    @Override
    public void endVisit(MySqlPrepareStatement x) {

    }

    @Override
    public boolean visit(MySqlPrepareStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlExecuteStatement x) {

    }

    @Override
    public boolean visit(MySqlExecuteStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlLoadDataInFileStatement x) {

    }

    @Override
    public boolean visit(MySqlLoadDataInFileStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlLoadXmlStatement x) {

    }

    @Override
    public boolean visit(MySqlLoadXmlStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlReplaceStatement x) {

    }

    @Override
    public boolean visit(MySqlReplaceStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlSelectGroupBy x) {

    }

    @Override
    public boolean visit(MySqlSelectGroupBy x) {

        return true;
    }

    @Override
    public void endVisit(MySqlStartTransactionStatement x) {

    }

    @Override
    public boolean visit(MySqlStartTransactionStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlCommitStatement x) {

    }

    @Override
    public boolean visit(MySqlCommitStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlRollbackStatement x) {

    }

    @Override
    public boolean visit(MySqlRollbackStatement x) {

        return true;
    }

    @Override
    public void endVisit(MySqlShowColumnsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowColumnsStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlShowTablesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowTablesStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlShowDatabasesStatement x) {

    }

    @Override
    public boolean visit(MySqlShowDatabasesStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlShowWarningsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowWarningsStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlShowStatusStatement x) {

    }

    @Override
    public boolean visit(MySqlShowStatusStatement x) {
        return true;
    }

    @Override
    public void endVisit(CobarShowStatus x) {

    }

    @Override
    public boolean visit(CobarShowStatus x) {
        return true;
    }

    @Override
    public void endVisit(MySqlKillStatement x) {

    }

    @Override
    public boolean visit(MySqlKillStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlBinlogStatement x) {

    }

    @Override
    public boolean visit(MySqlBinlogStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlResetStatement x) {

    }

    @Override
    public boolean visit(MySqlResetStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlCreateUserStatement x) {

    }

    @Override
    public boolean visit(MySqlCreateUserStatement x) {
        return true;
    }

    @Override
    public void endVisit(UserSpecification x) {

    }

    @Override
    public boolean visit(UserSpecification x) {
        return true;
    }

    @Override
    public void endVisit(MySqlDropUser x) {

    }

    @Override
    public boolean visit(MySqlDropUser x) {
        return true;
    }

    @Override
    public void endVisit(MySqlDropTableStatement x) {

    }

    @Override
    public boolean visit(MySqlDropTableStatement x) {
        return visit((SQLDropTableStatement) x);
    }

    @Override
    public void endVisit(MySqlPartitionByKey x) {

    }

    @Override
    public boolean visit(MySqlPartitionByKey x) {
        accept(x.getColumns());
        return false;
    }

    @Override
    public boolean visit(MySqlSelectQueryBlock x) {
        return this.visit((SQLSelectQueryBlock) x);
    }

    @Override
    public void endVisit(MySqlSelectQueryBlock x) {

    }

    @Override
    public boolean visit(MySqlOutFileExpr x) {
        return false;
    }

    @Override
    public void endVisit(MySqlOutFileExpr x) {

    }

    @Override
    public boolean visit(MySqlDescribeStatement x) {
        getTableStat(x.getObject().toString());
        return false;
    }

    @Override
    public void endVisit(MySqlDescribeStatement x) {

    }

    @Override
    public boolean visit(MySqlUpdateStatement x) {
        return visit((SQLUpdateStatement) x);
    }

    @Override
    public void endVisit(MySqlUpdateStatement x) {

    }

    @Override
    public boolean visit(MySqlSetTransactionIsolationLevelStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetTransactionIsolationLevelStatement x) {

    }

    @Override
    public boolean visit(MySqlSetNamesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetNamesStatement x) {

    }

    @Override
    public boolean visit(MySqlSetCharSetStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlSetCharSetStatement x) {

    }

    @Override
    public boolean visit(MySqlShowAuthorsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowAuthorsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowBinaryLogsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowBinaryLogsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowMasterLogsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowMasterLogsStatement x) {

    }

    @Override
    public boolean visit(MySqlShowCollationStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCollationStatement x) {

    }
    
    @Override
    public boolean visit(MySqlShowBinLogEventsStatement x) {
        return false;
    }
    
    @Override
    public void endVisit(MySqlShowBinLogEventsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCharacterSetStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCharacterSetStatement x) {
        
    }
    
    @Override
    public boolean visit(MySqlShowContributorsStatement x) {
        return false;
    }
    
    @Override
    public void endVisit(MySqlShowContributorsStatement x) {
        
    }
    
    @Override
    public boolean visit(MySqlShowCreateDatabaseStatement x) {
        return false;
    }
    
    @Override
    public void endVisit(MySqlShowCreateDatabaseStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCreateEventStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateEventStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCreateFunctionStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateFunctionStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCreateProcedureStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateProcedureStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCreateTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateTableStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCreateTriggerStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateTriggerStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowCreateViewStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowCreateViewStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowEngineStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowEngineStatement x) {
        
    }
    
    @Override
    public boolean visit(MySqlShowEnginesStatement x) {
        return false;
    }
    
    @Override
    public void endVisit(MySqlShowEnginesStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowErrorsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowErrorsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowEventsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowEventsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowFunctionCodeStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowFunctionCodeStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowFunctionStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowFunctionStatusStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowGrantsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowGrantsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlUserName x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUserName x) {
        
    }

    @Override
    public boolean visit(MySqlShowIndexesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowIndexesStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowKeysStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowKeysStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowMasterStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowMasterStatusStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowOpenTablesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowOpenTablesStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowPluginsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowPluginsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowPrivilegesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowPrivilegesStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowProcedureCodeStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProcedureCodeStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowProcedureStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProcedureStatusStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowProcessListStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProcessListStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowProfileStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProfileStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowProfilesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowProfilesStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowRelayLogEventsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowRelayLogEventsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowSlaveHostsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowSlaveHostsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowSlaveStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowSlaveStatusStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowTableStatusStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowTableStatusStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowTriggersStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowTriggersStatement x) {
        
    }

    @Override
    public boolean visit(MySqlShowVariantsStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlShowVariantsStatement x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableStatement x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableAddColumn x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableAddColumn x) {
        
    }
    
    @Override
    public boolean visit(MySqlCreateIndexStatement x) {
        return false;
    }
    
    @Override
    public void endVisit(MySqlCreateIndexStatement x) {
        
    }

    @Override
    public boolean visit(MySqlRenameTableStatement.Item x) {
        return false;
    }

    @Override
    public void endVisit(MySqlRenameTableStatement.Item x) {
        
    }

    @Override
    public boolean visit(MySqlRenameTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlRenameTableStatement x) {
        
    }

    @Override
    public boolean visit(MySqlDropViewStatement x) {
        return true;
    }

    @Override
    public void endVisit(MySqlDropViewStatement x) {
        
    }

    @Override
    public boolean visit(MySqlUnionQuery x) {
        return visit((SQLUnionQuery) x);
    }

    @Override
    public void endVisit(MySqlUnionQuery x) {
        
    }

    @Override
    public boolean visit(MySqlUseIndexHint x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUseIndexHint x) {
        
    }

    @Override
    public boolean visit(MySqlIgnoreIndexHint x) {
        return false;
    }

    @Override
    public void endVisit(MySqlIgnoreIndexHint x) {
        
    }

    @Override
    public boolean visit(MySqlLockTableStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlLockTableStatement x) {
        
    }

    @Override
    public boolean visit(MySqlUnlockTablesStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlUnlockTablesStatement x) {
        
    }

    @Override
    public boolean visit(MySqlForceIndexHint x) {
        return false;
    }

    @Override
    public void endVisit(MySqlForceIndexHint x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableChangeColumn x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableChangeColumn x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableCharacter x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableCharacter x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableAddIndex x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableAddIndex x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableOption x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableOption x) {
        
    }

    @Override
    public boolean visit(MySqlCreateTableStatement x) {
        return super.visit((SQLCreateTableStatement) x);
    }

    @Override
    public void endVisit(MySqlCreateTableStatement x) {
        
    }

    @Override
    public boolean visit(MySqlHelpStatement x) {
        return false;
    }

    @Override
    public void endVisit(MySqlHelpStatement x) {
        
    }

    @Override
    public boolean visit(MySqlCharExpr x) {
        return false;
    }

    @Override
    public void endVisit(MySqlCharExpr x) {
        
    }

    @Override
    public boolean visit(MySqlAlterTableAddUnique x) {
        return false;
    }

    @Override
    public void endVisit(MySqlAlterTableAddUnique x) {
        
    }
}
