package com.changes.common.model;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author LiuJunJie
 * @since 2019/11/20 18:03
 */

@SuppressWarnings("all")
public class CommonPage<T> {

    private Integer pageIndex;

    private Integer pageSize;

    private Integer totalPage;

    private Long total;

    private List<T> list;


    public static <T> CommonPage<T> restPage(List<T> list){
        CommonPage<T> result = new CommonPage<T>();
        //设置进pageInfo中
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setList(pageInfo.getList());
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPrePage());
        return result;
    }


    public static <T> CommonPage<T> restPage(PageInfo<T> pageInfo){
        CommonPage<T> result = new CommonPage<T>();
        result.setList(pageInfo.getList());
        result.setPageIndex(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setTotalPage(pageInfo.getPrePage());
        return result;
    }


    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
