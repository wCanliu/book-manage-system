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
 *   用户 模块控制器
 */
@WebServlet(value = { "/yonghu.do" })
public class YonghuServlet extends BaseServlet {

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
        YonghuService service = new YonghuServiceImpl();

        // 查询数据
        if (ac.indexOf("list") != -1) {
            String orderby = Request.get("orderby", "id"); // 获取浏览器地址中的orderby   参数  默认按 发布时间 排序方式
            String sort = Request.get("sort", "DESC"); // 获取浏览器地址中的sort参数   默认按  倒序排序
            int pagesize = Request.getInt("pagesize", 12); // 获取浏览器地址中的sort参数   默认按  12行每页显示
            int page = Math.max(1, Request.getInt("page", 1)); // 获取当前页   默认 第一页

            String where = "1=1"; // 防止sql 语句中where and 错误
            // 以下是判断搜索框中是否有输入内容，判断是否前台是否有填写相关条件，符合则写入sql搜索语句
            if (!Request.get("cx").equals("")) {
                where += " AND cx ='" + Request.get("cx") + "' ";
            }
            if (!Request.get("username").equals("")) {
                where += " AND username LIKE '%" + Request.get("username") + "%' ";
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

            view("/yonghu/" + ac + ".jsp");
            return;
        } else if (ac.equals("add") || ac.equals("addweb")) { // 用户添加页面视图
            if (ac.equals("add") && !checkLogin()) {
                showError("尚未登录");
                return;
            }

            view("/yonghu/" + ac + ".jsp");
            return;
        } else if (ac.equals("insert")) {
            // 插入
            Yonghu post = new Yonghu(); // 创建实体类
            // 设置前台提交上来的数据到实体类中
            post.setCx(Request.get("cx"));

            post.setUsername(Request.get("username"));

            post.setPwd(Request.get("pwd"));

            post.setXingming(Request.get("xingming"));

            post.setXingbie(Request.get("xingbie"));

            post.setShouji(Request.get("shouji"));

            service.insert(post); // 插入数据
            int charuid = post.getId().intValue();

            showSuccess("保存成功", Request.get("referer").equals("") ? request.getHeader("referer") : Request.get("referer"));
        } else if (ac.equals("updt") || ac.equals("updtself")) {
            Object id;
            if (ac.equals("updt")) {
                id = Request.get("id");
                assign("updtself", 0);
            } else {
                id = session.getAttribute("id");
                assign("updtself", 1);
            }
            Yonghu mmm = service.findWhere("id=" + id);
            assign("mmm", mmm);
            view("/yonghu/updt.jsp");
        } else if (ac.equals("update")) {
            // 创建实体类
            String charuid = request.getParameter("id");
            Yonghu post = service.findWhere("id=" + charuid);
            if (request.getParameter("cx") != null) post.setCx(Request.get("cx"));

            if (request.getParameter("username") != null) post.setUsername(Request.get("username"));

            if (request.getParameter("pwd") != null) post.setPwd(Request.get("pwd"));

            if (request.getParameter("xingming") != null) post.setXingming(Request.get("xingming"));

            if (request.getParameter("xingbie") != null) post.setXingbie(Request.get("xingbie"));

            if (request.getParameter("shouji") != null) post.setShouji(Request.get("shouji"));

            service.update(post);

            if (Request.getInt("updtself") == 1) {
                showSuccess("保存成功", "yonghu.do?ac=updtself");
                return;
            }
            showSuccess("保存成功", Request.get("referer"));
            // 更新
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
