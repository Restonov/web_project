<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
    <title><fmt:message key="admin.car.list"/></title>
    <script type="text/javascript">
        <c:import url="/js/noRefresh.js"/>
    </script>
</head>
<body>
<c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.22/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.22/datatables.min.js"></script>
<script>
    $(document).ready( function () {
        $('#carTable').DataTable();
    } );
</script>

<table class="display" id="carTable" style="margin-top: 30px; width: 100%">
    <thead>
    <tr>
        <th>id</th>
        <th>Car model</th>
        <th>Cost per day</th>
        <th>Eng Description</th>
        <th>Rus Description</th>
        <th>Current state</th>
    </tr>
    </thead>
    <tbody>
    <ctg:car_list/>
    </tbody>
</table>
<c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
