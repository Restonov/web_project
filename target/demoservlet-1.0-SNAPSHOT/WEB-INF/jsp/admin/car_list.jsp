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
<c:import url="/WEB-INF/jsp/admin/admin_panel.jsp" charEncoding="utf-8"/>

<div style="height: 500px">
<table class="table" style="margin-top: 30px">
    <thead>
    <tr>
        <th>id</th>
        <th>Car model</th>
        <th>Cost per day</th>
        <th>Car type</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <ctg:car_list/>
    </tbody>
</table>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
