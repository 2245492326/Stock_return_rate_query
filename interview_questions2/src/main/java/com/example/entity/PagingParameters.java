package com.example.entity;

public class PagingParameters {
    private Integer pageNum;
    private Integer pageSize;
    private String sortField;
    private String sortDirection;

    public PagingParameters() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    @Override
    public String toString() {
        return "PagingParameters{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", sortField='" + sortField + '\'' +
                ", sortDirection='" + sortDirection + '\'' +
                '}';
    }
}
