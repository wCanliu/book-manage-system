<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<script src="js/jquery.validate.js"></script>
<link rel="stylesheet" href="js/layer/theme/default/layer.css" />
<script src="js/layer/layer.js"></script>
<link rel="stylesheet" href="js/umeditor/themes/default/css/umeditor.css" />
<script src="js/umeditor/umeditor.config.js"></script>
<script src="js/umeditor/umeditor.min.js"></script>

<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 添加书籍 </span>
        </div>
        <div class="panel-body">
            <form action="shuji.do?ac=update" method="post" name="form1" id="form1">
                <!-- form 标签开始 -->

                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">名称<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                type="text"
                                class="form-control"
                                placeholder="输入名称"
                                style="width: 250px"
                                data-rule-required="true"
                                data-msg-required="请填写名称"
                                id="mingcheng"
                                name="mingcheng"
                                value="${Info.html(mmm.mingcheng)}"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">分类</label>
                        <div class="col-sm-10">
                            <select class="form-control class_fenlei8" data-value="${Info.html(mmm.fenlei)}" id="fenlei" name="fenlei" style="width: 250px">
                                <ssm:sql var="select" type="select">SELECT * FROM fenlei ORDER BY id desc</ssm:sql>
                                <c:forEach items="${select}" var="m">
                                    <option value="${m.id}">${m.fenleiming}</option>
                                </c:forEach>
                            </select>
                            <script>
                                $(".class_fenlei8").val($(".class_fenlei8").attr("data-value"));
                            </script>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">作者</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" placeholder="输入作者" style="width: 150px" id="zuozhe" name="zuozhe" value="${Info.html(mmm.zuozhe)}" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">出版社</label>
                        <div class="col-sm-10">
                            <input
                                type="text"
                                class="form-control"
                                placeholder="输入出版社"
                                style="width: 250px"
                                id="chubanshe"
                                name="chubanshe"
                                value="${Info.html(mmm.chubanshe)}"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">国际标准书号ISBN<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                type="text"
                                class="form-control"
                                placeholder="输入国际标准书号ISBN"
                                style="width: 150px"
                                data-rule-required="true"
                                data-msg-required="请填写国际标准书号ISBN"
                                remote="common.do?ac=checkno&checktype=update&id=${mmm.id}&table=shuji&col=isbn"
                                data-msg-remote="内容重复了"
                                id="isbn"
                                name="isbn"
                                value="${Info.html(mmm.isbn)}"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">数量<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                type="number"
                                class="form-control"
                                placeholder="输入数量"
                                style="width: 150px"
                                data-rule-required="true"
                                data-msg-required="请填写数量"
                                number="true"
                                data-msg-number="输入一个有效数字"
                                id="shuliang"
                                name="shuliang"
                                value="${Info.html(mmm.shuliang)}"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">图片</label>
                        <div class="col-sm-10">
                            <div class="input-group" style="width: 250px">
                                <input type="text" class="form-control" id="tupian" name="tupian" value="${Info.html(mmm.tupian)}" />

                                <span class="input-group-btn"
                                    ><button
                                        type="button"
                                        class="btn btn-default"
                                        onclick="layer.open({type:2,title:'上传图片',fixed:true,shadeClose:true,shade:0.5,area:['320px','150px'],content:'upload.html?result=tupian'})"
                                    >
                                        上传图片
                                    </button></span
                                >
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">简介</label>
                        <div class="col-sm-10">
                            <textarea id="jianjie" name="jianjie" style="max-width: 750px; width: 100%; height: 300px">${Info.html(mmm.jianjie)}</textarea>
                            <script>
                                (function () {
                                    var um = UM.getEditor("jianjie");
                                })();
                            </script>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs"> </label>
                        <div class="col-sm-10">
                            <input name="id" value="${mmm.id}" type="hidden" />
                            <input name="referer" value="<%=request.getHeader("referer") %>" type="hidden" />
                            <input name="updtself" value="${updtself}" type="hidden" />

                            <button type="submit" class="btn btn-primary" name="Submit">提交</button>
                            <button type="reset" class="btn btn-default" name="Submit2">重置</button>
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

