package com.gin.util;

import com.gin.util.threadlocal.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 继承自ArrayList 的实现，并实现分页代码的展示
 * @param <E>
 */
public class Collect<E> extends ArrayList<E> {

    // 总数
    protected long count = 0;
    // 当前分页
    protected int page = -1;
    // 分页总数
    protected int pageCount = 0;
    // 第一行起始位置 limit firstRow,listRows 即可
    public int firstRow = 0;
    // 取数据库的行数
    public int listRows = 15;
    // URL 规则
    protected String urlRule = "";

    /**
     * 分页信息
     */
    protected PageInfo pageInfo;
    // 表现层代码
    private String info;

    private Collect() {}

    public String getUrlRule() {
        return urlRule;
    }

    public String getInfo() {
        return info;
    }

    /**
     *
     * @param count 总行数
     * @param pagesize  一页展示多少条数据
     */
    public Collect(long count, int pagesize) {
        this.listRows = pagesize;
        this.count = count;
        this.initLimit();
    }

    /**
     *
     * @param count 总行数
     * @param pagesize 一页展示多少条数据
     * @param page  当前页
     */
    public Collect(long count, int pagesize, int page) {
        this.listRows = pagesize;
        this.count = count;
        this.page = page;
        this.initLimit();
    }

    /**
     * 初始化
     */
    protected void initLimit() {
        // 取分页数有多少
        double ceil = (double) this.count / (double) this.listRows;
        // 分页页面总数
        this.pageCount = this.count == 0 ? 0 : new Double(Math.ceil(ceil)).intValue();
        // 取URL 规则
        this.urlRule = getRequestUrlPath();
        // 获取第一行的位置
        firstRow = listRows * (page - 1);
        // 渲染分页代码
        pageInfo = new PageInfo();
        pageInfo.setTotalRows(count); // 设置总行数
        pageInfo.setTotalPages(pageCount); // 设置总页数
        pageInfo.setUrlRule(urlRule);
        pageInfo.setCurrentPage(this.page);
        pageInfo.setPageSize(listRows);

        LocalRequestContext content = LocalRequestContextHolder.getLocalRequestContext();
        HttpServletRequest res = content.getRequest();
        res.setAttribute("page", pageInfo);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long c) {
        this.count = c;
    }

    /**
     * 获取替换成功的页码
     * @param page
     * @return
     */
    protected String getUrlPath(int page) {
        return this.urlRule.replace("{page}", String.valueOf(page));
    }

    /**
     * 根据当前页面生成URL规则，
     * @return
     */
    protected String getRequestUrlPath() {
        // 获取当前线程的Request
        LocalRequestContext context = LocalRequestContextHolder.getLocalRequestContext();

        // 取 URL 后面的参数如： a=b&b=c&d=e
        String queryString = context.getRequest().getQueryString();
        if (queryString == null) {
            queryString = "";
        }
        // 创建缓冲区
        StringBuffer buffer = new StringBuffer(queryString.length() + 16);
        // 获取URL path 参数如： /index.jsp
        String requestURI = context.getRequest().getRequestURI();

        // 开始写入参数
        buffer.append(requestURI).append("?");
        // 获取URL提交的参数
        Map<String, String[]> param = context.getRequest().getParameterMap();
        String name = "";
        String value = "";

        // 是否搜索page 参数
        boolean isSearchPage = false;
        int page = -1;

        for (Map.Entry<String, String[]> entry : param.entrySet()) {
            try {
                name = entry.getKey();
                String[] values = entry.getValue();

                // 当前参数等于=page
                if (name.equals("page")) {
                    page = Integer.valueOf(values[0]).intValue();

                    // 写入url 规则的是：page={page} 使用时替换{page}即可
                    buffer.append(name).append("=").append("{page}").append("&");
                    isSearchPage = true;
                } else if (null == values) {
                    // 值等于null,所以也写入
                    buffer.append(name).append("=").append("&");
                } else if (values.length > 1) {
                    // 同名参数，多个
                    for (int i = 0; i < values.length; i++) { //用于请求参数中有多个相同名称
                        value = URLEncoder.encode(values[i], "UTF-8");
                        buffer.append(name).append("=").append(value).append("&");
                    }
                    //value = value.substring(0, value.length() - 1);
                } else {
                    value = URLEncoder.encode(values[0], "UTF-8");
                    buffer.append(name).append("=").append(value).append("&"); //用于请求参数中请求参数名唯一
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        // 写入当前页码
        if (this.page == -1) {
            this.page = page;
        }
        // 防止page 小于1
        this.page = Math.max(this.page, 1);

        // 没有搜索到页码直接写入
        if (!isSearchPage) {
            buffer.append("page={page}&");
        }
        String result = buffer.toString();
        return result.substring(0, result.length() - 1);
    }

    public int getPage() {
        return page;
    }
}
