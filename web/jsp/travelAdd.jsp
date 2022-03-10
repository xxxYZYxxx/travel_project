<%--
  Created by IntelliJ IDEA.
  User: xxxYZYxxx
  Date: 2022/3/3
  Time: 15:24
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
    <title>报名页面</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="<%=path%>/css/style.css">
<%--导入jquery控件--%>
<script src="<%=path%>/jquery/jquery-1.7.2.js"></script>
<script>

</script>
<body>
<h1 align="center" style="font-size: 30px">报名</h1>
<hr>
<form action="http://localhost:8080/web/travel?flag=toAdd" method="post">
    <%--隐藏域用于存放tid--%>
    <input type="hidden" name="tid" value="<%=tid%>">
    <table align="center">
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>

        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="sex" value="1">男
                <input type="radio" name="sex" value="2">女
            </td>
        </tr>

        <tr>
            <td>出生日期</td>
            <td>
                <input type="date" name="birthday">
            </td>
        </tr>

        <tr>
            <td>手机号码</td>
            <td>
                <input type="text" name="phone">
            </td>
        </tr>

        <tr>
            <td>身份证号</td>
            <td>
                <input type="text" name="idCard">
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