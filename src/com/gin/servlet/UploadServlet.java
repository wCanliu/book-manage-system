package com.gin.servlet;

import com.gin.util.Uploader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/upload.do")
public class UploadServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = request.getContextPath();

        /**
         * 使用Uploader 工具类处理上传信息
         */
        Uploader uploader = new Uploader(request);
        uploader.setMaxSize(100 * 1024);
        String saveurl = "upload";
        uploader.setSavePath(saveurl);
        try {
            uploader.upload();
            String url = uploader.getUrl();
            // 将保存的路径放入前台
            request.setAttribute("url", url);
            view("/upload.jsp");
        } catch (Exception e) {
            showError(e.getMessage());
            return;
        }
    }
}
