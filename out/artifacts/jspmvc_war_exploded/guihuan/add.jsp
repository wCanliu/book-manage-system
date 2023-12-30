<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<script src="js/jquery.validate.js"></script>

<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 添加 </span>
        </div>
        <div class="panel-body">
            <form action="guihuan.do?ac=insert" method="post" name="form1" id="form1">
                <!-- form 标签开始 -->

                <input type="hidden" name="jieyueid" value="${param.id}" /> <input type="hidden" name="shujiid" value="${readMap.shujiid}" />
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">名称</label>
                        <div class="col-sm-10">${readMap.mingcheng}<input type="hidden" id="mingcheng" name="mingcheng" value="${Info.html(readMap.mingcheng)}" /></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">分类</label>
                        <div class="col-sm-10">
                            <ssm:sql var="mapfenlei14" type="find">SELECT fenleiming,id FROM fenlei where id='${readMap.fenlei}'</ssm:sql>${mapfenlei14.fenleiming}<input
                                type="hidden"
                                id="fenlei"
                                name="fenlei"
                                value="${Info.html(readMap.fenlei)}"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">作者</label>
                        <div class="col-sm-10">${readMap.zuozhe}<input type="hidden" id="zuozhe" name="zuozhe" value="${Info.html(readMap.zuozhe)}" /></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">用户</label>
                        <div class="col-sm-10">${readMap.yonghu}<input type="hidden" id="yonghu" name="yonghu" value="${Info.html(readMap.yonghu)}" /></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">归还时间</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control"
                                   style="width:180px;"
                                   readonly="readonly" id="kaishi"
                                   name="kaishi" value="<%=Info.getDateStr()%>"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs"> </label>
                        <div class="col-sm-10">
                            <input name="referer" id="referers" class="referers" value="<%=request.getHeader("referer") %>" type="hidden" />
                            <script>
                                $(function () {
                                    $("input.referers").val(document.referrer);
                                });
                            </script>

                            <button type="submit" class="btn btn-primary" name="Submit">提交</button>
                            <button type="reset" class="btn btn-default" name="Submit2">重置</button>
                            <button type="button" class="btn btn-default" onclick="history.back()">返回</button>
                        </div>
                    </div>
                </div>

                <!--form标签结束-->
            </form>

            <script>
                $(function () {
                    $("#form1").validate();
                });
            </script>
        </div>
    </div>
</div>

<%@ include file="/foot.jsp" %>

