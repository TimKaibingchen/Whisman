package com.whisman.modules.page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * User: Tim
 * Date: 8/29/13
 */
public class DataTablePageable implements Pageable {

    private int iDisplayLength = 100 ;
    private int iDisplayStart;
    private int sEcho;

    @Override
    public int getPageNumber() {
        return this.iDisplayLength ==0 ? 0 : this.iDisplayStart/this.iDisplayLength;
    }

    @Override
    public int getPageSize() {
        return iDisplayLength;
    }

    @Override
    public int getOffset() {
        return this.iDisplayStart;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getsEcho() {
        return sEcho;
    }
}
