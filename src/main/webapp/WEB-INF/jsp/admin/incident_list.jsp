<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
    <title><fmt:message key="admin.incident.list.headline"/></title>
</head>
<body>
<c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.22/datatables.min.css"/>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.22/datatables.min.js"></script>
<script>
    $(document).ready( function () {
        $('#incidentTable').DataTable();
    } );
</script>

<div style="margin-bottom: 30px">
    <table class="display" id="incidentTable" style="margin-top: 30px">
        <thead>
        <tr>
            <th>id</th>
            <th>Car id</th>
            <th>User id</th>
            <th>Type</th>
            <th>Description</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <ctg:incident_list/>
        </tbody>
    </table>
</div>
<c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
