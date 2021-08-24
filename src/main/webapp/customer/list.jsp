<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 23/08/2021
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh sach khach hang</h1>
<table border="1px">
    <tr>
        <td>name</td>
        <td>address</td>
        <td>email</td>
        <td>chi tiet</td>
    </tr>
    <c:forEach items="${customers}" var="c">
        <tr>
            <td>${c.name}</td>
            <td>${c.address}</td>
            <td>${c.email}</td>
            <td><a href="/customers?page=detail&id=${c.id}">chi tiet</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
