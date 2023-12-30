package com.gin.servlet;

import com.gin.util.Request;
import com.gin.util.StringUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class BaseServlet extends HttpServlet {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        this.session = req.getSession();
        super.service(req, resp);
    }

    /**
     * 输出jsp 视图页面
     * @param url
     * @throws ServletException
     * @throws IOException
     */
    public void view(String url) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void assign(String name, Object value) {
        request.setAttribute(name, value);
    }

    /**
     * 实现弹出窗，主要代码，需要更改弹出窗样式请在WebRoot\message.jsp 文件中修改
     * @param message
     * @param code
     * @param jumpUrl
     * @param jumpTime
     * @return
     */
    protected String showMessage(String message, int code, String jumpUrl, int jumpTime) throws ServletException, IOException {
        assign("message", message);
        assign("code", code);
        assign("jumpUrl", jumpUrl);
        assign("jumpTime", jumpTime);
        view("/message.jsp");

        return "";
    }

    /**
     * 检测是否都来
     * @return
     */
    protected boolean checkLogin() {
        if (request.getSession().getAttribute("username") == null || "".equals(request.getSession().getAttribute("username"))) {
            return false;
        }
        return true;
    }

    /**
     * 弹出错误窗口
     * @param message
     * @return
     */
    protected String showError(String message) throws ServletException, IOException {
        return showMessage(message, 1, "javascript:history(-1);", 2250);
    }

    /**
     * 弹出错误窗口
     * @param message
     * @param code
     * @return
     */
    protected String showError(String message, int code) throws ServletException, IOException {
        return showMessage(message, code, "javascript:history(-1);", 2250);
    }

    /**
     * 弹出错误窗口
     * @param message
     * @param url
     * @return
     */
    protected String showError(String message, String url) throws ServletException, IOException {
        return showMessage(message, 1, url, 2250);
    }

    /**
     * 弹出成功窗口
     * @param message
     * @return
     */
    protected String showSuccess(String message) throws ServletException, IOException {
        return showMessage(message, 0, request.getHeader("referer"), 2250);
    }

    /**
     * 弹出成功窗口
     * @param message
     * @param url
     * @return
     */
    protected String showSuccess(String message, String url) throws ServletException, IOException {
        return showMessage(message, 0, url, 2250);
    }
}
