package com.gin.servlet;

import com.gin.dao.*;
import com.gin.entity.*;
import com.gin.service.*;
import com.gin.service.impl.*;
import com.gin.util.*;
import com.jntoo.db.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *   书籍 模块控制器
 */
@WebServlet(value = { "/shuji.do" })
public class ShujiServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ac = request.getParameter("ac");
        if (ac == null) {
            ac = "list";
        }
        ShujiService service = new ShujiServiceImpl();

        // 查询数据
        if (ac.indexOf("list") != -1) {
            String orderby = Request.get("orderby", "id"); // 获取浏览器地址中的orderby   参数  默认按 发布时间 排序方式
            String sort = Request.get("sort", "DESC"); // 获取浏览器地址中的sort参数   默认按  倒序排序
            int pagesize = Request.getInt("pagesize", 12); // 获取浏览器地址中的sort参数   默认按  12行每页显示
            int page = Math.max(1, Request.getInt("page", 1)); // 获取当前页   默认 第一页

            String where = "1=1"; // 防止sql 语句中where and 错误
            // 以下是判断搜索框中是否有输入内容，判断是否前台是否有填写相关条件，符合则写入sql搜索语句
            if (!Request.get("mingcheng").equals("")) {
                where += " AND mingcheng LIKE '%" + Request.get("mingcheng") + "%' ";
            }
            if (!Request.get("fenlei").equals("")) {
                where += " AND fenlei ='" + Request.get("fenlei") + "' ";
            }

            // 获取行数
            long count = service.count(where);
            // 创建分页信息
            new Collect(count, pagesize, page);
            // 分页并查询数据
            List list = service.selectPages(pagesize, page, where, orderby + " " + sort);

            request.setAttribute("lists", list);

            request.setAttribute("orderby", orderby);
            request.setAttribute("sort", sort);
            request.setAttribute("pagesize", pagesize);

            view("/shuji/" + ac + ".jsp");
            return;
        } else if (ac.equals("add")) { // 书籍添加页面视图
            if (ac.equals("add") && !checkLogin()) {
                showError("尚未登录");
                return;
            }

            view("/shuji/" + ac + ".jsp");
            return;
        } else if (ac.equals("insert")) {
            // 插入
            Shuji post = new Shuji(); // 创建实体类
            // 设置前台提交上来的数据到实体类中
            post.setMingcheng(Request.get("mingcheng"));

            post.setFenlei(Request.getInt("fenlei"));

            post.setZuozhe(Request.get("zuozhe"));

            post.setChubanshe(Request.get("chubanshe"));

            post.setIsbn(Request.get("isbn"));

            post.setShuliang(Request.getInt("shuliang"));

            post.setTupian(Request.get("tupian"));

            post.setJianjie(DownloadRemoteImage.run(Request.get("jianjie")));

            service.insert(post); // 插入数据
            int charuid = post.getId().intValue();

            showSuccess("保存成功", Request.get("referer").equals("") ? request.getHeader("referer") : Request.get("referer"));
        } else if (ac.equals("updt")) {
            String id = Request.get("id");

            Shuji mmm = service.findWhere("id=" + id);
            assign("mmm", mmm);
            view("/shuji/updt.jsp");
        } else if (ac.equals("update")) {
            // 创建实体类
            String charuid = request.getParameter("id");
            Shuji post = service.findWhere("id=" + charuid);
            if (request.getParameter("mingcheng") != null) post.setMingcheng(Request.get("mingcheng"));

            if (request.getParameter("fenlei") != null) post.setFenlei(Request.getInt("fenlei"));

            if (request.getParameter("zuozhe") != null) post.setZuozhe(Request.get("zuozhe"));

            if (request.getParameter("chubanshe") != null) post.setChubanshe(Request.get("chubanshe"));

            if (request.getParameter("isbn") != null) post.setIsbn(Request.get("isbn"));

            if (request.getParameter("shuliang") != null) post.setShuliang(Request.getInt("shuliang"));

            if (request.getParameter("tupian") != null) post.setTupian(Request.get("tupian"));

            if (request.getParameter("jianjie") != null) post.setJianjie(DownloadRemoteImage.run(Request.get("jianjie")));

            service.update(post);

            showSuccess("保存成功", Request.get("referer"));
            // 更新
        } else if (ac.equals("detail")) {
            int id = Request.getInt("id");
            Shuji map = service.find(id); // 根据前台url 参数中的id获取行数据

            request.setAttribute("map", map); // 把数据写到前台
            view("/shuji/" + ac + ".jsp");
        } else if (ac.equals("delete")) {
            if (!checkLogin()) {
                showError("尚未登录");
                return;
            }
            int id = Request.getInt("id");

            service.delete(id);
            showSuccess("删除成功", request.getHeader("referer"));
        } else {
            response.sendError(404);
        }
    }
}
