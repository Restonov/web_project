<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="error.headline"/></title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<section id="wrapper" class="container-fluid" style="margin-top: 100px; margin-bottom: 20px">
    <div class="error-box">
        <div class="error-body text-center">
            <span style="font-size: 100px; color: #0e79c2"><strong>Runtime error</strong></span>
            <h3> <fmt:message key="error.text.servlet.error"/></h3>
            <a href="${pageContext.request.contextPath}/" class="btn btn-danger btn-rounded m-b-40"><fmt:message key="error.button.home"/></a>
        </div>
    </div>
</section>
<hr/>
<div>
    Request from ${pageContext.errorData.requestURI} is failed
    <br>
    Servlet name or type: ${pageContext.errorData.servletName}
    <br>
    Status code: ${pageContext.errorData.statusCode}
    <br>
    Exception: ${pageContext.errorData.throwable}
</div>
</body>
</html>
