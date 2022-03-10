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
    <title>查看报名旅客</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="<%=path%>/css/style.css">
<%--导入jquery控件--%>
<script src="<%=path%>/jquery/jquery-1.7.2.js"></script>
<script>

</script>
<body>
<h1 align="center" style="font-size: 30px">查看报名旅客</h1>
<hr>

    线路名称：${passengerList[0].travel.travelName};&nbsp&nbsp&nbsp报名人数：${passengerList[0].travel.num}
<table align="center">

    <tr>
        <th>姓名</th>
        <th>性别</th>
        <th>出生日期</th>
        <th>手机号码</th>
        <th>身份证</th>
        <th>报名时间</th>

    </tr>

    <c:forEach items="${passengerList}" var="passenger">
        <tr>
            <td>${passenger.name}</td>
            <td>${passenger.sex==1?'男':'女'}</td>
            <td>${passenger.birthday}</td>
            <td>${passenger.phone}</td>
            <td>${passenger.idCard}</td>
            <td>${passenger.signDate}</td>
        </tr>
    </c:forEach>
</table>



</body>
</html>