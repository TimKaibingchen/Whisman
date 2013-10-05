package com.whisman.modules.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * User: Tim
 * Date: 8/27/13
 */
public class MybatisPage<T> implements Page<T>, Serializable {

    private  List<T> content = new ArrayList<T>();
    private  Pageable pageable;
    private  long total;

    public MybatisPage(Pageable pageable) {
        this.pageable = pageable;
    }

    /*
         * (non-Javadoc)
         * @see org.springframework.data.domain.Page#getNumber()
         */
    public int getNumber() {
        return pageable == null ? 0 : pageable.getPageNumber();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getSize()
     */
    public int getSize() {
        return pageable == null ? 0 : pageable.getPageSize();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getTotalPages()
     */
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getNumberOfElements()
     */
    public int getNumberOfElements() {
        return content.size();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getTotalElements()
     */
    public long getTotalElements() {
        return total;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#hasPreviousPage()
     */
    public boolean hasPreviousPage() {
        return getNumber() > 0;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#isFirstPage()
     */
    public boolean isFirstPage() {
        return !hasPreviousPage();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#hasNextPage()
     */
    public boolean hasNextPage() {
        return getNumber() + 1 < getTotalPages();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#isLastPage()
     */
    public boolean isLastPage() {
        return !hasNextPage();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#iterator()
     */
    public Iterator<T> iterator() {
        return content.iterator();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getContent()
     */
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#hasContent()
     */
    public boolean hasContent() {
        return !content.isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getSort()
     */
    public Sort getSort() {
        return pageable == null ? null : pageable.getSort();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String contentType = "UNKNOWN";

        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", getNumber(), getTotalPages(), contentType);
    }


    /*Add by Tim */
    public int getOffset(){
        return  pageable.getOffset();
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    /*
             * (non-Javadoc)
             * @see java.lang.Object#equals(java.lang.Object)
             */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageImpl<?>)) {
            return false;
        }

        MybatisPage<?> that = (MybatisPage<?>) obj;

        boolean totalEqual = this.total == that.total;
        boolean contentEqual = this.content.equals(that.content);
        boolean pageableEqual = this.pageable == null ? that.pageable == null : this.pageable.equals(that.pageable);

        return totalEqual && contentEqual && pageableEqual;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + (int) (total ^ total >>> 32);
        result = 31 * result + (pageable == null ? 0 : pageable.hashCode());
        result = 31 * result + content.hashCode();

        return result;
    }
}
