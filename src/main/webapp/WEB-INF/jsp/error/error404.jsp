<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Error page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
<section id="wrapper" class="container-fluid" style="margin-top: 100px">
    <div class="error-box">
        <div class="error-body text-center">
            <span style="font-size: 150px; color: #0e79c2"><strong>404</strong></span>
            <h3> <fmt:message key="error.text.not.found"/></h3>
            <a href="${pageContext.request.contextPath}/" class="btn btn-danger btn-rounded m-b-40"><fmt:message key="error.button.home"/></a>
            <hr/>
            <%--@elvariable id="error_404" type="java.lang.String"--%>
            ${error_404}
        </div>
    </div>
</section>
</body>
</html>