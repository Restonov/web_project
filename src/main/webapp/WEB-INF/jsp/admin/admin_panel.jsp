<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
</head>
<body>
<div class="container">
    <div class="nav-scroller py-1 mb-2" style="position: static;">
        <nav class="nav d-flex justify-content-between" style="position: static; display: block; left: auto; height: 30px;">
            <form method="POST" action="controller">
                <input class="p-2 text-muted" type="submit" value="<fmt:message key="admin.user.list"/>" style="outline: 0; border: 0; background: 0;">
                <input type="hidden" name="command" value="show_user_list" />
            </form>
            <form method="POST" action="controller">
                <input class="p-2 text-muted" type="submit" value="<fmt:message key="admin.car.list"/>" style="outline: 0; border: 0; background: 0;">
                <input type="hidden" name="command" value="show_car_list" />
            </form>
            <form method="POST" action="controller">
                <input class="p-2 text-muted" type="submit" value="<fmt:message key="admin.order.list"/>" style="outline: 0; border: 0; background: 0;">
                <input type="hidden" name="command" value="show_order_list" />
            </form>
        </nav>
    </div>
</div>
<hr>
</body>
</html>
