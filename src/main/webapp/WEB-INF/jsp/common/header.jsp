<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="locale" type="java.lang.String"--%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent" scope="session"/>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
    <title></title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="height: 60px;">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/" style="margin: 0; padding: 0">TyRent</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/" style="margin: 0; padding-left: 50px"><fmt:message key="button.home"/> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/" >
                </a>
            </li>
            <li class="nav-item">

            </li>
        </ul>
        <form class="form-inline mt-2 mt-md-0">
        </form>
    </div>

    <h6 style="display: inline; float: none; position: relative; left: auto; right: 30px;"><ctg:current_user/></h6>
    <form style="margin: 0" method="POST" action="controller">
        <ctg:login_button/>
    </form>
</nav>
<%--@elvariable id="activate_admin_panel" type="java.lang.Boolean"--%>
<c:if test="${activate_admin_panel}">
    <c:import url="/WEB-INF/jsp/admin/admin_panel.jsp"/>
</c:if>
</body>
</html>
