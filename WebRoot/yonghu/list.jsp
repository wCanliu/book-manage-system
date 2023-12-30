<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 管理员查询 </span>
        </div>
        <div class="panel-body">
            <div class="form-search pa10 bg-warning">
                <form class="form-inline" action="?" size="small" id="formSearch">
                    <!-- form 标签开始 -->

                    权限：
                    <select class="form-control class_cx1" data-value="${param.cx}" data-rule-required="true" data-msg-required="请填写权限" id="cx" name="cx">
                        <option value="">请选择</option>
                        <option value="管理员">管理员</option>
                        <option value="用户">用户</option>
                    </select>
                    <script>
                        $(".class_cx1").val($(".class_cx1").attr("data-value"));
                    </script>

                    帐号： <input type="text" class="form-control" style="" name="username" value="${param.username}" />

                    <button type="submit" class="btn btn-default">搜索</button>

                    <!--form标签结束-->
                </form>
            </div>

            <div class="list-table">
                <table width="100%" border="1" class="table table-list table-bordered table-hover">
                    <thead>
                        <tr align="center">
                            <th width="60" data-field="item">序号</th>
                            <th>权限</th>
                            <th>帐号</th>
                            <th>密码</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>手机</th>

                            <th width="220" data-field="handler">操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="i" value="0" /><c:forEach items="${lists}" var="map"
                            ><c:set var="i" value="${i+1}" />
                            <tr id="${map.id}" pid="">
                                <td width="30" align="center">
                                    <label> ${i} </label>
                                </td>
                                <td>${map.cx}</td>
                                <td>${map.username}</td>
                                <td>${map.pwd}</td>
                                <td>${map.xingming}</td>
                                <td>${map.xingbie}</td>
                                <td>${map.shouji}</td>

                                <td align="center">
                                    <a class="btn btn-success btn-xs" href="yonghu.do?ac=updt&id=${map.id}" title="编辑"> 编辑 </a>

                                    <a class="btn btn-danger btn-xs" href="yonghu.do?ac=delete&id=${map.id}" onclick="return confirm('确定删除？此操作不可恢复')" title="删除">
                                        删除
                                    </a>

                                    <!--qiatnalijne-->
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <%@include file="/page.jsp" %>

        </div>
    </div>
</div>

<%@ include file="/foot.jsp" %>

