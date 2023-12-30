<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="htstyle/css/base.css" />
        <link rel="stylesheet" type="text/css" href="htstyle/css/jquery.dialog.css" />
        <link rel="stylesheet" href="htstyle/css/index.css" />
        <style>
            .layui-layer-title {
                background: url(images/righttitlebig.png) repeat-x;
                font-weight: bold;
                color: #46647e;
                border: 1px solid #c1d3de;
                height: 33px;
                line-height: 33px;
            }
        </style>
        <title>图书管理系统</title>
    </head>
    <body>
        <div id="container">
            <div id="hd">
                <div class="hd-wrap ue-clear">
                    <div class="top-light"></div>
                    <h1 class="logo"></h1>
                    <div class="login-info ue-clear">
                        <div class="welcome ue-clear"><span>欢迎您,</span><a href="javascript:void(0)" class="user-name">${sessionScope.username}（${sessionScope.cx}）</a></div>
                        <!--<div class="login-msg ue-clear">
                    <a href="javascript:void(0)" class="msg-txt">消息</a>
                    <a href="javascript:void(0)" class="msg-num">10</a>
                </div>-->
                    </div>
                    <div class="toolbar ue-clear">
                        <a href="mod.jsp" class="home-btn1" target="right">修改密码</a>
                        <a href="javascript:void(0)" class="quit-btn exit home-btn">退出</a>
                    </div>
                </div>
            </div>
            <div id="bd">
                <div class="wrap ue-clear">
                    <div class="sidebar">
                        <h2 class="sidebar-header"><p>功能导航</p></h2>
                        <ul class="nav">
                            <c:choose> <c:when test="${'管理员' == sessionScope.cx }"> <%@ include file="/left_guanliyuan.jsp" %>
 </c:when></c:choose>
                            <c:choose> <c:when test="${'用户' == sessionScope.cx }"> <%@ include file="/left_yonghu.jsp" %>
 </c:when></c:choose>
                        </ul>
                    </div>
                    <div class="content">
                        <iframe src="sy.jsp" id="iframe" width="100%" height="100%" frameborder="0" name="right" style="min-width: 1100px"></iframe>
                    </div>
                </div>
            </div>
            <div id="ft" class="foot_div">
                <span></span>
                <em>版权所有：图书管理系统</em>
            </div>
        </div>
        <div class="exitDialog">
            <div class="dialog-content">
                <div class="ui-dialog-icon"></div>
                <div class="ui-dialog-text">
                    <p class="dialog-content">你确定要退出系统？</p>
                    <p class="tips">如果是请点击“确定”，否则点“取消”</p>
                    <div class="buttons">
                        <input type="button" class="button long2 ok" onclick="location.href='user.do?ac=logout'" value="确定" />
                        <input type="button" class="button long2 normal" value="取消" />
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="htstyle/js/jquery.js"></script>
        <script type="text/javascript" src="htstyle/js/core.js"></script>
        <script type="text/javascript" src="htstyle/js/jquery.dialog.js"></script>
        <script type="text/javascript" src="htstyle/js/index.js"></script>
    </body>
</html>
