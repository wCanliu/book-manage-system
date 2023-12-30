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
 *   归还 模块控制器
 */
@WebServlet(value = { "/guihuan.do" })
public class GuihuanServlet extends BaseServlet {

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
        GuihuanService service = new GuihuanServiceImpl();

        // 查询数据
        if (ac.indexOf("list") != -1) {
            String orderby = Request.get("orderby", "id"); // 获取浏览器地址中的orderby   参数  默认按 发布时间 排序方式
            String sort = Request.get("sort", "DESC"); // 获取浏览器地址中的sort参数   默认按  倒序排序
            int pagesize = Request.getInt("pagesize", 12); // 获取浏览器地址中的sort参数   默认按  12行每页显示
            int page = Math.max(1, Request.getInt("page", 1)); // 获取当前页   默认 第一页

            String where = "1=1"; // 防止sql 语句中where and 错误

            // 判断URL 参数jieyueid是否大于0
            if (Request.getInt("jieyueid") > 0) {
                // 大于0 则写入条件
                where += " AND jieyueid='" + Request.getInt("jieyueid") + "' ";
            }
            // 以下是判断搜索框中是否有输入内容，判断是否前台是否有填写相关条件，符合则写入sql搜索语句
            if (!Request.get("mingcheng").equals("")) {
                where += " AND mingcheng LIKE '%" + Request.get("mingcheng") + "%' ";
            }
            if (!Request.get("fenlei").equals("")) {
                where += " AND fenlei ='" + Request.get("fenlei") + "' ";
            }
            if (ac.equals("list_yonghu")) {
                where += " AND yonghu='" + session.getAttribute("username") + "' ";
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

            view("/guihuan/" + ac + ".jsp");
            return;
        } else if (ac.equals("add")) { // 归还添加页面视图
            if (ac.equals("add") && !checkLogin()) {
                showError("尚未登录");
                return;
            }

            int id = Request.getInt("id"); // 根据id 获取借阅模块中的数据
            Jieyue readMap = new JieyueServiceImpl().find(id);
            // 将数据行写入给前台jsp页面
            request.setAttribute("readMap", readMap);
            view("/guihuan/" + ac + ".jsp");
            return;
        } else if (ac.equals("insert")) {
            // 插入
            Guihuan post = new Guihuan(); // 创建实体类
            // 设置前台提交上来的数据到实体类中
            post.setJieyueid(Request.getInt("jieyueid"));

            post.setShujiid(Request.getInt("shujiid"));

            post.setMingcheng(Request.get("mingcheng"));

            post.setFenlei(Request.getInt("fenlei"));

            post.setZuozhe(Request.get("zuozhe"));

            post.setYonghu(Request.get("yonghu"));

            post.setAddtime(Info.getDateStr());

            post.setJieyueid(Request.getInt("jieyueid"));

            service.insert(post); // 插入数据
            int charuid = post.getId().intValue();
            DB.execute("UPDATE shuji SET shuliang=shuliang+1 WHERE id='" + request.getParameter("shujiid") + "'");

            DB.execute("UPDATE jieyue SET zhuangtai='已归还' WHERE id='" + request.getParameter("jieyueid") + "'");

            showSuccess("保存成功", Request.get("referer").equals("") ? request.getHeader("referer") : Request.get("referer"));
        } else if (ac.equals("updt")) {
            String id = Request.get("id");

            Guihuan mmm = service.findWhere("id=" + id);
            // 根据mmm.Jieyueid 获取借阅模块中的数据
            Jieyue readMap = new JieyueServiceImpl().find(mmm.getJieyueid());
            // 将数据行写入给前台jsp页面
            request.setAttribute("readMap", readMap);
            assign("mmm", mmm);
            view("/guihuan/updt.jsp");
        } else if (ac.equals("update")) {
            // 创建实体类
            String charuid = request.getParameter("id");
            Guihuan post = service.findWhere("id=" + charuid);
            if (request.getParameter("jieyueid") != null) post.setJieyueid(Request.getInt("jieyueid"));

            if (request.getParameter("shujiid") != null) post.setShujiid(Request.getInt("shujiid"));

            if (request.getParameter("mingcheng") != null) post.setMingcheng(Request.get("mingcheng"));

            if (request.getParameter("fenlei") != null) post.setFenlei(Request.getInt("fenlei"));

            if (request.getParameter("zuozhe") != null) post.setZuozhe(Request.get("zuozhe"));

            if (request.getParameter("yonghu") != null) post.setYonghu(Request.get("yonghu"));

            if (request.getParameter("addtime") != null) post.setAddtime(Request.get("addtime"));

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
