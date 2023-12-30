<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ssm" uri="http://ssm" %>


<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>登录</title>
    <link href="htstyle/css/login.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<div class="div_top">图书管理系统</div>
<div class="login">
    <div class="message">系统登录</div>
    <div id="darkbannerwrap"></div>

    <form action="user.do?ac=login&a=a&admin=1" method="post">
        <input name="username" placeholder="用户名" required="" type="text" id="user"/>
        <hr class="hr15"/>
        <input name="pwd" placeholder="密码" required="" type="password" id="pass"/>
        <hr class="hr15"/>
        <div class="pagerandom">
            <input name="pagerandom" placeholder="验证码" required="" type="text" id="pagerandom"/>
            <img src="captch.do" onClick="this.src='captch.do?time='+new Date().getTime();"
                 style="width: 60px; height: 30px; min-width: 60px; min-height: 30px" alt=""/>
        </div>
        <hr class="hr15"/>
        <div style="display: none">
            <select name="cx">
                <option value="用户">用户</option>
            </select>
        </div>
        <hr class="hr15"/>
        <input value="登录" style="width: 49%" type="submit" id="login_btn"/>
        <input value="注册" style="width: 49%" type="button" onclick="location.href='yonghu.do?ac=addweb'"/>
        <hr class="hr20"/>
    </form>
</div>

<div class="copyright">版权所有@图书管理系统</div>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
    $(function () {
        $("#login_btn").click(function () {
            var selectuser = $("#user").val();
            var pword = $("#pass").val();
            var pagerandom = $("#pagerandom").val();

            if (selectuser == "" || selectuser.length <= 1) {
                alert("用户名不能为空");
                $("#user").focus();
                return false;
            }
            if (pword == "" || pword.length < 1) {
                alert("密码不能为空");
                $("#pass").focus();
                return false;
            }
            if (pagerandom == "" || pagerandom.length < 4) {
                alert("验证码长度为4位数字");
                $("#pass").focus();
                return false;
            }
            return true;
        });
    });
</script>
</body>
</html>
