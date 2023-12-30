<%@ page language="java" import="com.gin.util.*" pageEncoding="UTF-8" %>
 <%@ page language="java" import="java.util.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="ssm" uri="http://ssm" %>
 <%@ include file="/head.jsp" %>


<div>
    <div class="panel panel-default">
        <div class="panel-heading">
            <span class="titles"> 书籍详情 </span>
        </div>
        <div class="panel-body">
            <div class="pa10">
                <div class="pa10 bg-white">
                    <table class="new-detail">
                        <tbody>
                            <tr>
                                <td class="detail-title">名称</td>
                                <td class="detail-value">${map.mingcheng}</td>
                                <td class="detail-title">分类</td>
                                <td class="detail-value">
                                    <ssm:sql var="mapfenlei3" type="find">SELECT fenleiming,id FROM fenlei where id='${map.fenlei}'</ssm:sql>${mapfenlei3.fenleiming}
                                </td>
                                <td class="detail-title">作者</td>
                                <td class="detail-value">${map.zuozhe}</td>
                            </tr>
                            <tr>
                                <td class="detail-title">出版社</td>
                                <td class="detail-value">${map.chubanshe}</td>
                                <td class="detail-title">国际标准书号ISBN</td>
                                <td class="detail-value">${map.isbn}</td>
                                <td class="detail-title">数量</td>
                                <td class="detail-value">${map.shuliang}</td>
                            </tr>
                            <tr>
                                <td class="detail-title">图片</td>
                                <td class="detail-value">
                                    <img src="${map.tupian}" style="width: 350px" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="mt10 pa10 bg-white">
                    <div class="new-detail-line">
                        <div class="detail-title">简介</div>
                        <td class="detail-value">${map.jianjie}</td>
                    </div>
                </div>
                <div class="mt10 not-print">
                    <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                    <button type="button" class="btn btn-default" onclick="window.print()">打印本页</button>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/foot.jsp" %>

