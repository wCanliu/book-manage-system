<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ssm" uri="http://ssm" %>
<%@ include file="/head.jsp" %>


<script src="js/jquery.validate.js"></script>

<div style="padding: 250px">
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 注册 </span>
        </div>
        <div class="panel-body">
            <form action="yonghu.do?ac=insert" method="post" name="form1" id="form1">
                <!-- form 标签开始 -->
                <input type="hidden" name="referer" value="./">
                <div class="form-group" style="display: none">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">权限<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <select class="form-control class_cx2" data-value="" data-rule-required="true"
                                    data-msg-required="请填写权限" id="cx" name="cx" style="width: 250px">
                                <option value="用户">用户</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">帐号<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                    type="text"
                                    class="form-control"
                                    placeholder="输入帐号"
                                    style="width: 150px"
                                    data-rule-required="true"
                                    data-msg-required="请填写帐号"
                                    remote="common.do?ac=checkno&checktype=insert&table=yonghu&col=username"
                                    data-msg-remote="内容重复了"
                                    id="username"
                                    name="username"
                                    value=""
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">密码<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <input
                                    type="password"
                                    class="form-control"
                                    placeholder="输入密码"
                                    style="width: 150px"
                                    data-rule-required="true"
                                    data-msg-required="请填写密码"
                                    id="pwd"
                                    name="pwd"
                                    value=""
                            />
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
                                    value=""
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs">性别<span style="color: red">*</span></label>
                        <div class="col-sm-10">
                            <select
                                    class="form-control class_xingbie3"
                                    data-value=""
                                    data-rule-required="true"
                                    data-msg-required="请填写性别"
                                    id="xingbie"
                                    name="xingbie"
                                    style="width: 250px"
                            >
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
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
                                    value=""
                            />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="row">
                        <label style="text-align: right" class="col-sm-2 hiddex-xs"> </label>
                        <div class="col-sm-10">
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

