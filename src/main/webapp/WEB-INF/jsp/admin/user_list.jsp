<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

<div style="margin-bottom: 30px">
<table class="table" style="margin-top: 30px">
    <thead>
    <tr>
        <th>id</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Username</th>
        <th>Email</th>
        <th>Phone number</th>
        <th>User Role</th>
        <th>State</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <ctg:user_list/>
    </tbody>
</table>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
