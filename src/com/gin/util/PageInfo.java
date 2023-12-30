package com.gin.util;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {

    /**
     * 总页数
     */
    private long totalPages;
    /**
     * 总行数
     */
    private long totalRows;
    /**
     * 当前页
     */
    private long currentPage;

    /**
     * 每页长度
     */
    private long pageSize;

    /**
     * url 规则， 可以自定义 如：userlist/{page}
     */
    private String urlRule;

    public PageInfo() {}

    /**
     * 写入分页信息
     * @param totalPages  总页数
     * @param totalRows   总行数
     * @param currentPage 当前页
     * @param urlRule     url 规则 可以自定义 如：userlist/{page}  系统默认使用 参数形式写page={page}
     */
    public PageInfo(long totalPages, long totalRows, long currentPage, String urlRule) {
        this.totalPages = totalPages;
        this.totalRows = totalRows;
        this.currentPage = currentPage;
        this.urlRule = urlRule;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 判断是否有上一页
     * @return
     */
    public boolean IsPrev() {
        return currentPage > 1;
    }

    /**
     * 获取上一页的Url
     * @return String
     */
    public String getPrevPage() {
        if (IsPrev()) {
            return getUrlPath(currentPage - 1);
        }
        return "";
    }

    /**
     * 是否有下一页
     * @return
     */
    public boolean IsNext() {
        return currentPage < totalPages;
    }

    /**
     * 获取下一页的URL 地址
     * @return
     */
    public String getNextPage() {
        if (IsNext()) {
            return getUrlPath(currentPage + 1);
        }
        return "";
    }

    /**
     * 获取第一页URL
     * @return
     */
    public String getFirstPage() {
        return getUrlPath(1);
    }

    /**
     * 获取最后一页URL
     * @return
     */
    public String getLastPage() {
        return getUrlPath(totalPages);
    }

    /**
     * 获取页码列表：实体类为 PageNumsPojo
     * @return List<PageNumsPojo>
     */
    public List<PageNumsPojo> getPageNums() {
        // 取回页码
        long rollPage = 2;
        long show_nums = rollPage * 2 + 1;
        long i = 0;
        long start = 0, end = 0;
        List<PageNumsPojo> result = new ArrayList();
        if (totalPages < show_nums) {
            start = 1;
            end = totalPages;
        } else if (currentPage < (1 + rollPage)) {
            start = 1;
            end = show_nums;
        } else if (currentPage >= (totalPages - rollPage)) {
            start = totalPages - show_nums;
            end = totalPages;
        } else {
            start = currentPage - rollPage;
            end = currentPage + rollPage;
        }
        for (i = start; i <= end; i++) {
            PageNumsPojo pojo = new PageNumsPojo();
            pojo.page = i;
            pojo.url = getUrlPath(i);
            result.add(pojo);
        }
        return result;
    }

    public class PageNumsPojo {

        private long page;
        private String url;

        public long getPage() {
            return page;
        }

        public void setPage(long page) {
            this.page = page;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    /**
     * 获取总页数
     * @return
     */
    public long getTotalPages() {
        return totalPages;
    }

    /**
     * 设置总页数
     * @param totalPages
     */
    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 获取总行数
     * @return
     */
    public long getTotalRows() {
        return totalRows;
    }

    /**
     * 设置总行数
     * @param totalRows
     */
    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * 获取当前页
     * @return
     */
    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页
     * @param currentPage
     */
    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取URL 规则
     * @return
     */
    public String getUrlRule() {
        return urlRule;
    }

    /**
     * 设置URL 规则
     * @param urlRule
     */
    public void setUrlRule(String urlRule) {
        this.urlRule = urlRule;
    }

    /**
     * 获取替换成功的页码
     * @param page
     * @return
     */
    protected String getUrlPath(long page) {
        return urlRule.replace("{page}", String.valueOf(page));
    }
}
