<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>


<li class="office current">
    <div class="nav-header">
        <a href="JavaScript:;" class="ue-clear">
            <span>账号管理</span>
            <i class="icon hasChild"></i>
        </a>
    </div>
    <ul class="subnav" style="display: block">
        <li>
            <a target="right" href="yonghu.do?ac=list"> 用户信息 </a>
        </li>
        <li>
            <a target="right" href="yonghu.do?ac=add"> 用户添加 </a>
        </li>
        <li>
            <a target="right" href="mod.jsp"> 密码修改 </a>
        </li>
    </ul>
</li>
<li class="office">
    <div class="nav-header">
        <a href="JavaScript:;" class="ue-clear">
            <span>分类管理</span>
            <i class="icon hasChild"></i>
        </a>
    </div>
    <ul class="subnav">
        <li>
            <a target="right" href="fenlei.do?ac=add"> 分类添加 </a>
        </li>
        <li>
            <a target="right" href="fenlei.do?ac=list"> 分类查询 </a>
        </li>
    </ul>
</li>
<li class="office">
    <div class="nav-header">
        <a href="JavaScript:;" class="ue-clear">
            <span>书籍管理</span>
            <i class="icon hasChild"></i>
        </a>
    </div>
    <ul class="subnav">
        <li>
            <a target="right" href="shuji.do?ac=add"> 书籍添加 </a>
        </li>
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
            <a target="right" href="jieyue.do?ac=list"> 借阅查询 </a>
        </li>
        <li>
            <a target="right" href="guihuan.do?ac=list"> 归还查询 </a>
        </li>
    </ul>
</li>
