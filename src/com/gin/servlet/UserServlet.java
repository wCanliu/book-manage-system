package com.gin.servlet;

import com.gin.dao.*;
import com.gin.entity.*;
import com.gin.service.*;
import com.gin.service.impl.*;
import com.gin.util.*;
import com.jntoo.db.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user.do")
public class UserServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ac = request.getParameter("ac");
        if (ac == null) ac = "login";

        if (ac.equals("login")) {
            doLogin(request, response); // 调用登录方法
        } else if (ac.equals("updatePassword")) {
            doUpdatePassword(request, response); // 调用修改密码方法
        } else if (ac.equals("logout")) {
            session.invalidate(); // 注销登录
            showSuccess("退出登录成功", "./");
        } else {
            response.sendError(500);
        }
    }

    /**
     * 登录系统验证代码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // 获取用户名
        String password = request.getParameter("pwd"); // 获取密码
        String cx = request.getParameter("cx"); // 获取当前登录类型
        boolean isAdmin = request.getParameter("admin") != null; // 是否为后台登录
        /**
         * 验证码
         */
        String pagerandom = request.getParameter("pagerandom") == null ? "" : request.getParameter("pagerandom");

        if (username == null || "".equals(username)) {
            showError("账号不允许为空");
            return;
        }
        if (password == null || "".equals(password)) {
            showError("密码不允许为空");
            return;
        }

        /**
         * 获取保存在session 中得验证码
         */
        String random = (String) request.getSession().getAttribute("random");

        if (!pagerandom.equals(random) && request.getParameter("a") != null) {
            request.setAttribute("error", "验证码错误");
            showError("验证码错误");
            return;
        }
        if (cx.equals("用户")) {
            YonghuService service = new YonghuServiceImpl();
            Yonghu yonghu = service.login(username, password);
            if (yonghu == null) {
                showError("用户名或密码错误");
                return;
            }
            // 设置当前登录会话
            session.setAttribute("id", yonghu.getId());
            session.setAttribute("login", cx);
            session.setAttribute("cx", yonghu.getCx());
            session.setAttribute("username", yonghu.getUsername());
            session.setAttribute("pwd", yonghu.getPwd());
            session.setAttribute("xingming", yonghu.getXingming());
            session.setAttribute("xingbie", yonghu.getXingbie());
            session.setAttribute("shouji", yonghu.getShouji());
            session.setAttribute("username", username);
        }

        String referer = request.getParameter("referer"); // 指定跳转位置
        if (referer == null) {
            if (isAdmin) {
                referer = "./main.jsp";
            } else {
                referer = "./";
            }
        }
        showSuccess("登录成功", referer);
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getSession().getAttribute("username").toString();
        String cx = request.getSession().getAttribute("login").toString();
        String oldPassword = Request.get("oldPassword");
        String newPwd = Request.get("newPwd");
        String newPwd2 = Request.get("newPwd2");

        if (oldPassword.equals("")) {
            showError("请输入原密码");
            return;
        }

        if (newPwd.equals("")) {
            showError("请输入新密码");
            return;
        }

        if (!newPwd.equals(newPwd2)) {
            showError("两次密码不一致");
            return;
        }
        if (cx.equals("用户")) {
            YonghuService service = new YonghuServiceImpl();
            Yonghu yonghu = service.login(username, oldPassword);
            if (yonghu == null) {
                showError("原密码不正确");
                return;
            }
            service.editPassword(yonghu.getId(), newPwd);
        }
        showSuccess("修改密码成功");
    }
}
