<%--
  Created by IntelliJ IDEA.
  User: xxxYZYxxx
  Date: 2022/3/3
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--导入jstl标签库--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path=request.getContextPath();
%>
<html>
<head>
    <title>携程-欧洲跟团游管理</title>
</head>
<%--导入css样式--%>
<link rel="stylesheet" href="<%=path%>/css/style.css">
<%--导入jquery控件--%>
<script src="<%=path%>/jquery/jquery-1.7.2.js"></script>
<script>
    //报名，添加人数
    function toAdd(){
        //获取被选中状态下的多选框，且只能选一个
        if($(".cks:checked").length==1){
            let tid=$(".cks:checked").val();
            location.href="http://localhost:8080/web/jsp/travelAdd.jsp?tid="+tid;
        }
        else{
            alert("只能选择一条线路进行报名！")
        }
    }

    //回显数据
    function findTravelById(){
        //获取被选中状态下的多选框，且只能选一个
        if($(".cks:checked").length==1){
            let tid=$(".cks:checked").val();
            location.href="http://localhost:8080/web/travel?flag=findTravelById&tid="+tid;
        }
        else{
            alert("只能选择一条线路进行修改！")
        }
    }

    //文本就绪函数
    $(function (){
        <c:if test="${not empty deleteSuccess}">
            alert("删除成功！")
            location.href="http://localhost:8080/web/travel?flag=list";
        </c:if>
        <c:if test="${not empty passenger}">
            alert("线路已有游客报名，不能删除！")
            location.href="http://localhost:8080/web/travel?flag=list";
        </c:if>

        $("#checkbox").onclick(function (){
            $(".cks").prop("checked",this.checked);
        })
    })

    //批量删除
    function deleteTravelByIds(){
        //获取被选中多选框
        if($(".cks:checked").length>0){
            if(confirm("确定要删除指定线路吗？")){
                let ids="";
                $(".cks:checked").each(function (){
                    ids+=","+$(this).val();
                })
                ids=ids.substring(1);
                location.href="http://localhost:8080/web/travel?flag=deleteTravelByIds&ids="+ids;
            }
        }else{
            alert("请至少选择一条数据！")
        }
    }

    function findTravelAndPassenger(){
        //获取被选中状态下的多选框，且只能选一个
        if($(".cks:checked").length==1){
            let tid=$(".cks:checked").val();
            location.href="http://localhost:8080/web/travel?flag=findTravelAndPassenger&tid="+tid;
        }
        else{
            alert("只能选择一条线路进行修改！")
        }
    }
</script>
<body>
    <h1 align="center" style="font-size: 30px">携程-欧洲跟团游管理</h1>
    <tr align="center">
        <td colspan="button" align="center">
            <input type="button" value="报名" onclick="toAdd()">
            <input type="button" value="修改" onclick="findTravelById()">
            <input type="button" value="删除路线" onclick="deleteTravelByIds()">
            <input type="button" value="查看报名信息" onclick="findTravelAndPassenger()">
        </td>
    </tr>
    <table align="center">

        <tr>
            <th>全选/全不选<input type="checkbox" id="checkbox"> </th>
            <th>线程名称</th>
            <th>描述</th>
            <th>报名开始日期</th>
            <th>报名截止日期</th>
            <th>行程开始日期</th>
            <th>行程结束日期</th>
            <th>价格(元/人)</th>
            <th>已报名人数</th>
        </tr>

        <c:forEach items="${travelList}" var="travel">
            <tr>
                <td><input type="checkbox" class="cks" value="${travel.tid}"></td>
                <td>${travel.travelName}</td>
                <td>${travel.travelDesc}</td>
                <td>${travel.startDate}</td>
                <td>${travel.endDate}</td>
                <td>${travel.tripStart}</td>
                <td>${travel.tripEnd}</td>
                <td>${travel.price}起</td>
                <td>${travel.num}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
