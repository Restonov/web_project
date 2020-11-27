<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Footer</title>
    <link href="${pageContext.request.contextPath}/css/main.css" type="text/css" rel="stylesheet">
</head>
<body>
<hr>
<form class="container" action="controller" method="get">
    <p class="float-right"></p>
    <p><button type="submit" class="btn btn-dark btn-sm" style="position: relative; float: right; left: auto;" name="page_language" value="ru">RU</button>
        <button type="submit" class="btn btn-dark btn-sm" style="position: static; float: right; left: auto; display: inline-block; margin-right: 10px; margin-top: auto; right: auto;" name="page_language" value="en">EN</button>
        <input type="hidden" name="command" value="change_language" />
        © 2020 Nyrolep Group<br>
    </p>
</form>
</body>
</html>