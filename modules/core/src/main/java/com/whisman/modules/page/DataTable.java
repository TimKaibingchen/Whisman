package com.whisman.modules.page;

import java.util.List;

/**
 * User: Tim
 * Date: 8/29/13
 */
public interface DataTable<T> {

    public int getsEcho() ;

    public long getiTotalRecords() ;

    public long getiTotalDisplayRecords() ;

    public List<T> getAaData() ;

    public int getNumberOfElements();

}
