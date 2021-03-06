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

import java.util.ArrayList;
import java.util.List;

import com.whisman.extools.sql.ast.SQLHint;
import com.whisman.extools.sql.ast.SQLObjectImpl;

public abstract class SQLTableSourceImpl extends SQLObjectImpl implements SQLTableSource {

    private static final long serialVersionUID = 1L;

    protected String          alias;

    protected List<SQLHint>   hints            = new ArrayList<SQLHint>(2);

    public SQLTableSourceImpl(){

    }

    public SQLTableSourceImpl(String alias){

        this.alias = alias;
    }

    public String getAlias() {
        return this.alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<SQLHint> getHints() {
        return hints;
    }

    public void setHints(List<SQLHint> hints) {
        this.hints = hints;
    }

}
