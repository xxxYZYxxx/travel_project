<%--
  Created by IntelliJ IDEA.
  User: xxxYZYxxx
  Date: 2022/3/3
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入jstl标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path=request.getContextPath();
    String tid = request.getParameter("tid");
%>
<html>
<head>
    <title>线路信息修改</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="<%=path%>/css/style.css">
<%--导入jquery控件--%>
<script src="<%=path%>/jquery/jquery-1.7.2.js"></script>
<script>

</script>
<body>
<h1 align="center" style="font-size: 30px">线路信息修改</h1>
<hr>
<form action="http://localhost:8080/web/travel?flag=toUpdateById" method="post">
    <%--隐藏域用于存放tid--%>
    <input type="hidden" name="tid" value="${travel.tid}">
    <table align="center">

        <tr>
            <td>线程名称</td>
            <td>
                <input type="text" name="travelName" value="${travel.travelName}">
            </td>
        </tr>

        <tr>
            <td>描述</td>
            <td>
                <input type="text" name="travelDesc" value="${travel.travelDesc}">
            </td>
        </tr>

        <tr>
            <td>报名开始日期</td>
            <td>
                <input type="date" name="startDate" value="${travel.startDate}">
            </td>
        </tr>

        <tr>
            <td>报名截止日期</td>
            <td>
                <input type="date" name="endDate" value="${travel.endDate}">
            </td>
        </tr>

        <tr>
            <td>行程开始日期</td>
            <td>
                <input type="date" name="tripStart" value="${travel.tripStart}">
            </td>
        </tr>

        <tr>
            <td>行程结束日期</td>
            <td>
                <input type="date" name="tripEnd" value="${travel.tripEnd}">
            </td>
        </tr>

        <tr>
            <td>价格(元/人)</td>
            <td>
                <input type="text" name="price" value="${travel.price}">
            </td>
        </tr>

        <tr>
            <td colspan="11" align="cnter">
                <input type="submit" value="保存">
            </td>
        </tr>
    </table>
</form>


</body>
</html>