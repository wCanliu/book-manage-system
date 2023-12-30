package com.gin.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gin.util.Query;
import com.gin.util.Request;
import com.jntoo.db.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/common.do")
public class CommonServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ac = request.getParameter("ac");
        if (ac == null) ac = "";
        PrintWriter out = resp.getWriter();

        // 检测是否重复
        if (ac.equals("checkno")) { //使用ajax 检测某表中某个字段是否已存在，已存在则无法提交
            String table = request.getParameter("table");
            String col = request.getParameter("col");
            String checktype = request.getParameter("checktype");
            String value = request.getParameter(col);

            if (checktype.equals("insert")) {
                if (Query.make(table).where(col, value).count() > 0) {
                    out.print("false");
                } else {
                    out.print("true");
                }
                // 检测更新的
            } else if (checktype.equals("update")) {
                String id = request.getParameter("id") == null ? "" : request.getParameter("id");
                if (Query.make(table).where(col, value).where("id", "neq", id).count() > 0) {
                    out.print("false");
                } else {
                    out.print("true");
                }
            }
        } else if (ac.equals("sh")) { //审核数据，将是否审核改为已审核状态，点击一下 是 则变否， 点击一下 否 变为是
            String yuan = request.getParameter("yuan");
            String id = request.getParameter("id");
            String tablename = request.getParameter("tablename");
            String sql = "";
            if (yuan.equals("是")) {
                sql = "update " + tablename + " set issh='否' where id=" + id;
            } else {
                sql = "update " + tablename + " set issh='是' where id=" + id;
            }
            DB.execute(sql);
            response.sendRedirect(request.getHeader("Referer"));
            return;
        } else if (ac.equals("tableAjax")) { // 通过ajax获取表的某行数据
            String table = request.getParameter("table");
            String id = request.getParameter("id");
            Map map = Query.make(table).where("id", id).find();
            out.print(JSON.toJSONString(map));
        } else if (ac.equals("selectUpdateSearch")) {
            // 根据ajax提交得信息条件搜索数据库中得数据
            String table = Request.get("table");
            Query query = Query.make(table);
            String limit = "50";
            JSONObject where = JSON.parseObject(Request.get("where"));
            for (Map.Entry entry : where.entrySet()) {
                String key = (String) entry.getKey();
                Object value = entry.getValue();
                if ("limit".equals(key)) {
                    limit = String.valueOf(value);
                } else {
                    if (value instanceof JSONObject) {
                        JSONObject w = (JSONObject) value;
                        query.where(key, w.getString("exp"), w.getString("value"));
                    } else if (value instanceof JSONArray) {
                        JSONArray w = (JSONArray) value;
                        query.where(key, (String) w.get(0), w.get(1));
                    } else {
                        query.where(key, value);
                    }
                }
            }
            List list = query.order("id desc").limit(limit).select();
            out.print(JSON.toJSONString(list));
        }
        out.flush();
    }
}
