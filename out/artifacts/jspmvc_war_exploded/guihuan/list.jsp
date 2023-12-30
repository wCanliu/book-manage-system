<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 查询 </span>
        </div>
        <div class="panel-body">
            <div class="form-search pa10 bg-warning">
                <form class="form-inline" action="?" size="small" id="formSearch">
                    <!-- form 标签开始 -->

                    名称： <input type="text" class="form-control" style="" name="mingcheng" value="${param.mingcheng}" />

                    分类：
                    <select class="form-control class_fenlei11" data-value="${param.fenlei}" id="fenlei" name="fenlei">
                        <option value="">请选择</option>
                        <ssm:sql var="select" type="select">SELECT * FROM fenlei ORDER BY id desc</ssm:sql>
                        <c:forEach items="${select}" var="m">
                            <option value="${m.id}">${m.fenleiming}</option>
                        </c:forEach>
                    </select>
                    <script>
                        $(".class_fenlei11").val($(".class_fenlei11").attr("data-value"));
                    </script>


                    <button type="submit" class="btn btn-default">搜索</button>

                    <!--form标签结束-->
                </form>
            </div>

            <div class="list-table">
                <table width="100%" border="1" class="table table-list table-bordered table-hover">
                    <thead>
                        <tr align="center">
                            <th width="60" data-field="item">序号</th>
                            <th>名称</th>
                            <th>分类</th>
                            <th>作者</th>
                            <th>用户</th>
                            <th>归还时间</th>

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
                                <td>${map.mingcheng}</td>
                                <td><ssm:sql var="mapfenlei11" type="find">SELECT fenleiming,id FROM fenlei where id='${map.fenlei}'</ssm:sql>${mapfenlei11.fenleiming}</td>
                                <td>${map.zuozhe}</td>
                                <td>${map.yonghu}</td>
                                <td>${Info.subStr(map.addtime , 19 , "")}</td>

                                <td align="center">
                                    <a class="btn btn-success btn-xs" href="guihuan.do?ac=updt&id=${map.id}" title="编辑"> 编辑 </a>

                                    <a class="btn btn-danger btn-xs" href="guihuan.do?ac=delete&id=${map.id}" onclick="return confirm('确定删除？此操作不可恢复')" title="删除">
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

