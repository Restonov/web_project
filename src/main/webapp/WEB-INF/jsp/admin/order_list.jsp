<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
    <title><fmt:message key="admin.order.list"/></title>
</head>
<body>
<c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

<div style="margin-bottom: 30px">
<table class="table" style="margin-top: 30px">
    <thead>
    <tr>
        <th>Order ID</th>
        <th>Car model</th>
        <th>User ID</th>
        <th>Creation date</th>
        <th>Current state</th>
    </tr>
    </thead>
    <tbody>
    <ctg:order_list/>
    </tbody>
</table>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
