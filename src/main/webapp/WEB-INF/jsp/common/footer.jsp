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
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</body>
</html>