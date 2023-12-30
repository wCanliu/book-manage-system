<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>


<li class="office current">
    <div class="nav-header">
        <a href="JavaScript:;" class="ue-clear">
            <span>书籍管理</span>
            <i class="icon hasChild"></i>
        </a>
    </div>
    <ul class="subnav" style="display: block">
        <li>
            <a target="right" href="shuji.do?ac=list"> 书籍查询 </a>
        </li>
    </ul>
</li>
<li class="office">
    <div class="nav-header">
        <a href="JavaScript:;" class="ue-clear">
            <span>借阅管理</span>
            <i class="icon hasChild"></i>
        </a>
    </div>
    <ul class="subnav">
        <li>
            <a target="right" href="jieyue.do?ac=list_yonghu"> 借阅记录 </a>
        </li>
        <li>
            <a target="right" href="guihuan.do?ac=list_yonghu"> 归还记录 </a>
        </li>
    </ul>
</li>
<li class="office">
    <div class="nav-header">
        <a href="JavaScript:;" class="ue-clear">
            <span>个人中心</span>
            <i class="icon hasChild"></i>
        </a>
    </div>
    <ul class="subnav">
        <li>
            <a target="right" href="yonghu.do?ac=updtself"> 修改个人资料 </a>
        </li>
        <li>
            <a target="right" href="mod.jsp"> 修改密码 </a>
        </li>
    </ul>
</li>
