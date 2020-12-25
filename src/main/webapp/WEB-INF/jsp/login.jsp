<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <link href="${pageContext.request.contextPath}/css/login-register.css" type="text/css" rel="stylesheet">
    <script>
        <c:import url="/js/validation.js" charEncoding="utf-8"/>
    </script>
    <script type="text/javascript">
        <c:import url="/js/noRefresh.js"/>
    </script>
</head>
<body>
<c:out value="print"/>

    <c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

    <%--@elvariable id="user_blocked" type="java.lang.Boolean"--%>
    <c:if test="${user_blocked}">
        <ctg:page_alert type="danger" hide="false">
            <fmt:message key="error.user.blocked"/>
        </ctg:page_alert>
    </c:if>

    <%--@elvariable id="user_activated" type="java.lang.Boolean"--%>
    <c:if test="${user_activated}">
        <ctg:page_alert type="success" hide="true">
            <fmt:message key="message.activated"/>
        </ctg:page_alert>
    </c:if>

    <form class="form-login" name="form-login" method="POST" action="controller">
        <div class="form-input-data">
            <div class="form-white-background">
                <div class="form-title-row">
                    <h1><fmt:message key="login.title"/></h1>
                </div>

                <p style="color: red">
                    <br>
                    <%--@elvariable id="login_pass_message_error" type="java.lang.String"--%>
                    <c:if test="${login_pass_message_error}">
                        <span>
                        Wrong login or password
                        </span>
                        </c:if>
                    <%--@elvariable id="wrongAction" type="java.lang.String"--%>
                    ${wrongAction}
                    <%--@elvariable id="nullPage" type="java.lang.String"--%>
                    ${nullPage}
                </p>

                <div class="form-row">
                    <label>
                        <span><fmt:message key="login.label.login"/></span>
                        <input type="text" name="user_login" id="login" value="" onblur="validateLogin()" onchange="validateLogin()">
                        <h2 id="login_error" style="color: #f5f5f5; font-size: 12px; margin-top: 2px; text-align: center">
                            <fmt:message key="hint.login.error"/>
                        </h2>
                    </label>
                </div>

                <div class="form-row">
                    <label>
                        <span><fmt:message key="login.label.password"/></span>
                        <input type="password" name="user_password" id="password" value=""  onblur="validatePassword()" onchange="validatePassword()">
                        <h2 id="password_error" style="color: #f5f5f5; font-size: 12px; margin-top: 2px; text-align: center">
                            <fmt:message key="hint.password.error"/>
                        </h2>
                    </label>
                </div>

                <input type="submit" class="button-signin" value="<fmt:message key="button.label.login"/>">
                <input type="hidden" name="command" value="login" />
            </div>
        </div>
    </form>

            <div class="row-sign-up">
            <form method="GET" action="controller">
                <button class="form-sign-up" type="submit" name="path" value="register_page"><fmt:message key="login.button.sign.up"/> </button>
                <input type="hidden" name="command" value="forward" />
            </form>
            </div>

        <c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
