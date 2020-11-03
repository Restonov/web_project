<%--
  Created by IntelliJ IDEA.
  User: Restonov
  Date: 01-Nov-20
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- select form --%>
<div style="display: inline-block">
    <form action="controller" method="post">
        <p style="float: left">
            <select>
                <option selected disabled value="administrator">Крокодил Гена</option>
                <option value="Чебурашка">Чебурашка</option>
                <option value="Шапокляк">Шапокляк</option>
                <option value="Крыса Лариса">Крыса Лариса</option>
            </select>
        </p>
        <p style="float: right; margin-left: 5px"><input type="submit" value="OK"></p>
        <input type="hidden" name="command" value="change_role" />
    </form>
</div>


</body>
</html>
