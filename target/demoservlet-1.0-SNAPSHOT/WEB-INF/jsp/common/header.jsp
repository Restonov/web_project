<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="pagecontent" scope="session"/>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark" style="height: 60px;">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">TyRent</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
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
        <ctg:action_button/>
    </form>
</nav>
</body>
</html>
