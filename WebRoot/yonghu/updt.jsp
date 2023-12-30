<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<script src="js/jquery.validate.js"></script>

<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 编辑资料 </span>
        </div>
        <div class="panel-body">
            <form action="yonghu.do?ac=update" method="post" name="form1" id="form1">
                <!-- form 标签开始 -->

                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">权限<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            ${Info.html(mmm.cx)}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">帐号<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            ${Info.html(mmm.username)}
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">姓名<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                type="text"
                                class="form-control"
                                placeholder="输入姓名"
                                style="width: 150px"
                                data-rule-required="true"
                                data-msg-required="请填写姓名"
                                id="xingming"
                                name="xingming"
                                value="${Info.html(mmm.xingming)}"
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">性别<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <select
                                class="form-control class_xingbie5"
                                data-value="${Info.html(mmm.xingbie)}"
                                data-rule-required="true"
                                data-msg-required="请填写性别"
                                id="xingbie"
                                name="xingbie"
                                style="width: 250px"
                            >
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                            <script>
                                $(".class_xingbie5").val($(".class_xingbie5").attr("data-value"));
                            </script>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">手机<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                type="text"
                                class="form-control"
                                placeholder="输入手机"
                                style="width: 150px"
                                data-rule-required="true"
                                data-msg-required="请填写手机"
                                phone="true"
                                data-msg-phone="请输入正确手机号码"
                                id="shouji"
                                name="shouji"
                                value="${Info.html(mmm.shouji)}"
                            />
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

