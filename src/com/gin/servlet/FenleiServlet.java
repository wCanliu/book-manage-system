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
 *   分类 模块控制器
 */
@WebServlet(value = { "/fenlei.do" })
public class FenleiServlet extends BaseServlet {

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
        FenleiService service = new FenleiServiceImpl();

        // 查询数据
        if (ac.indexOf("list") != -1) {
            String orderby = Request.get("orderby", "id"); // 获取浏览器地址中的orderby   参数  默认按 发布时间 排序方式
            String sort = Request.get("sort", "DESC"); // 获取浏览器地址中的sort参数   默认按  倒序排序
            int pagesize = Request.getInt("pagesize", 12); // 获取浏览器地址中的sort参数   默认按  12行每页显示
            int page = Math.max(1, Request.getInt("page", 1)); // 获取当前页   默认 第一页

            String where = "1=1"; // 防止sql 语句中where and 错误
            // 以下是判断搜索框中是否有输入内容，判断是否前台是否有填写相关条件，符合则写入sql搜索语句
            if (!Request.get("fenleiming").equals("")) {
                where += " AND fenleiming LIKE '%" + Request.get("fenleiming") + "%' ";
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

            view("/fenlei/" + ac + ".jsp");
            return;
        } else if (ac.equals("add")) { // 分类添加页面视图
            if (ac.equals("add") && !checkLogin()) {
                showError("尚未登录");
                return;
            }

            view("/fenlei/" + ac + ".jsp");
            return;
        } else if (ac.equals("insert")) {
            // 插入
            Fenlei post = new Fenlei(); // 创建实体类
            // 设置前台提交上来的数据到实体类中
            post.setFenleiming(Request.get("fenleiming"));

            service.insert(post); // 插入数据
            int charuid = post.getId().intValue();

            showSuccess("保存成功", Request.get("referer").equals("") ? request.getHeader("referer") : Request.get("referer"));
        } else if (ac.equals("updt")) {
            String id = Request.get("id");

            Fenlei mmm = service.findWhere("id=" + id);
            assign("mmm", mmm);
            view("/fenlei/updt.jsp");
        } else if (ac.equals("update")) {
            // 创建实体类
            String charuid = request.getParameter("id");
            Fenlei post = service.findWhere("id=" + charuid);
            if (request.getParameter("fenleiming") != null) post.setFenleiming(Request.get("fenleiming"));

            service.update(post);

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
