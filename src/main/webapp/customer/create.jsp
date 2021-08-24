<%@ page import="com.c05.customer.service.CustomerServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 23/08/2021
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    CustomerServiceImpl customerService = new CustomerServiceImpl();
%>>
<body>
<c:if test='${message != null}'>
    <span>${message}</span>
</c:if>
<form method="post">
    <input name="name" placeholder="name"><br>
    <input name="add" placeholder="add"><br>
    <input name="mail" placeholder="mail"><br>
    <button>create</button>
</form>
</body>
</html>
