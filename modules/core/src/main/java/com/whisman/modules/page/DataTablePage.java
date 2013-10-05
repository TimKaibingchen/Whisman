package com.whisman.modules.page;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * User: Tim
 * Date: 8/29/13
 */
public class DataTablePage<T> implements DataTable<T> {

    private Page<T> page ;
    private int sEcho;

    public DataTablePage(Page<T> page) {
        this.page = page;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public long getiTotalRecords() {
        return page.getTotalElements();
    }

    public long getiTotalDisplayRecords() {
        return page.getTotalElements();
    }

    public List<T> getAaData() {
        return page.getContent();
    }

    @Override
    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }
}
