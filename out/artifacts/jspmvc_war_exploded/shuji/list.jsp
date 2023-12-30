<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 书籍查询 </span>
        </div>
        <div class="panel-body">
            <div class="form-search pa10 bg-warning">
                <form class="form-inline" action="?" size="small" id="formSearch">
                    <!-- form 标签开始 -->

                    名称： <input type="text" class="form-control" style="" name="mingcheng" value="${param.mingcheng}" />

                    分类：
                    <select class="form-control class_fenlei6" data-value="${param.fenlei}" id="fenlei" name="fenlei">
                        <option value="">请选择</option>
                        <ssm:sql var="select" type="select">SELECT * FROM fenlei ORDER BY id desc</ssm:sql>
                        <c:forEach items="${select}" var="m">
                            <option value="${m.id}">${m.fenleiming}</option>
                        </c:forEach>
                    </select>
                    <script>
                        $(".class_fenlei6").val($(".class_fenlei6").attr("data-value"));
                    </script>

                    <select class="form-control" name="orderby" id="orderby">
                        <option value="id">按发布时间</option>
                        <option value="shuliang">按数量</option>
                    </select>
                    <select class="form-control" name="sort" id="sort">
                        <option value="desc">倒序</option>
                        <option value="asc">升序</option>
                    </select>
                    <script>
                        $("#orderby").val("${orderby}");
                        $("#sort").val("${sort}".toLocaleLowerCase());
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
                            <th>出版社</th>
                            <th>数量</th>
                            <th>图片</th>

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
                                <td><ssm:sql var="mapfenlei2" type="find">SELECT fenleiming,id FROM fenlei where id='${map.fenlei}'</ssm:sql>${mapfenlei2.fenleiming}</td>
                                <td>${map.zuozhe}</td>
                                <td>${map.chubanshe}</td>
                                <td>${map.shuliang}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${'' == map.tupian }"> -</c:when><c:otherwise><img width="100" src="${map.tupian}" /></c:otherwise
                                    ></c:choose>
                                </td>

                                <td align="center">

                                    <c:choose><c:when test="${sessionScope.cx =='用户'}">
                                        <a class="btn btn-primary btn-xs" href="jieyue.do?ac=add&id=${map.id}"> 借阅 </a>
                                    </c:when><c:otherwise>
                                        <a class="btn btn-success btn-xs" href="shuji.do?ac=updt&id=${map.id}" title="编辑"> 编辑 </a>

                                        <a class="btn btn-danger btn-xs" href="shuji.do?ac=delete&id=${map.id}" onclick="return confirm('确定删除？此操作不可恢复')" title="删除">
                                            删除
                                        </a>
                                    </c:otherwise></c:choose>


                                    <a class="btn btn-info btn-xs" href="shuji.do?ac=detail&id=${map.id}" title="详情"> 详情 </a>



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

