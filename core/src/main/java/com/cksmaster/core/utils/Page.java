package com.cksmaster.core.utils;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * PageHelper分页方法封装
 *
 * @author cks
 * @Date 2017/8/14.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class Page<T> implements Serializable {

    /**
     * 默认每页大小
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 排序 - 升序
     */
    public static final String ASC = "asc";
    /**
     * 排序 - 升序
     */
    public static final String DESC = "desc";

    /**
     * 过滤条件集合
     */
    private LinkedHashMap<String, Object> filter = new LinkedHashMap<>();


    private static final long serialVersionUID = 1L;
    /**
     * 第几页，默认从第一页开始
     */
    private int pageNum = 1;
    /**
     * 每页条数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;
    /**
     * 分页从0开始
     */
    private long limitStart = 0l;
    /**
     * 排序的顺序
     */
    private String orderBy;
    /**
     * 排序的属性
     */
    private String order = null;
    /**
     * 总条数
     */
    private long total = -1;
    /**
     * 结果集
     */
    private List<T> result = Collections.emptyList();

    public Page() {
    }

    /**
     * 分页对象
     *
     * @param pageSize 每页条数
     */
    public Page(int pageSize) {
        this.pageSize = pageSize;
    }


    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNum(final int pageNum) {
        setLimitStart((long) ((pageNum - 1) * this.getPageSize()));
        this.pageNum = pageNum;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize) {
        setLimitStart((long) (pageSize * (this.getPageNum() - 1)));
        this.pageSize = pageSize;
    }

    /**
     * 返回Page对象自身的setPageSize函数,可用于连续设置。
     */
    public Page<T> pageSize(final int thePageSize) {
        setPageSize(thePageSize);
        return this;
    }

    public long getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(final Long limitStart) {
        this.limitStart = limitStart;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotal() {
        return total;
    }

    /**
     * 设置总记录数.
     */
    public void setTotal(long total) {
        this.total = total;
    }


    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPages() {
        if (total < 0) {
            return -1;
        }

        long count = total / pageSize;
        if (total % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext() {
        return (pageNum + 1 <= getTotalPages());
    }

    /**
     * 取得下页的页号, 序号从1开始.
     * 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNext()) {
            return pageNum + 1;
        } else {
            return pageNum;
        }
    }

    /**
     * 是否还有上一页.
     */
    public boolean isHasPre() {
        return (pageNum - 1 >= 1);
    }

    /**
     * 取得上页的页号, 序号从1开始.
     * 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (isHasPre()) {
            return pageNum - 1;
        } else {
            return pageNum;
        }
    }

    /**
     * 获得页内的记录列表.
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * @return 过滤条件集合
     */
    public LinkedHashMap<String, Object> getFilter() {
        return filter;
    }

    /**
     * 设置过滤条件集合
     *
     * @param filter 过滤条件集合
     */
    public void setFilter(LinkedHashMap<String, Object> filter) {
        this.filter = filter;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 返回Page对象自身的setOrderBy函数,可用于连续设置。
     */
    public Page<T> orderBy(final String theOrderBy) {
        setOrderBy(theOrderBy);
        return this;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Page{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", total=").append(total);
        sb.append(", totalPages=").append(getTotalPages());
        sb.append(", result=").append(result);
        sb.append(", navigatepageNums=");
        sb.append('}');
        return sb.toString();
    }
}
