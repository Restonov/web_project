<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="register.title"/></title>
    <link href="${pageContext.request.contextPath}/css/login-register.css" type="text/css" rel="stylesheet">
		<script>
            <c:import url="/js/validation.js" charEncoding="utf-8"/>
        </script>
    <script type="text/javascript">
        <c:import url="/js/noRefresh.js"/>
    </script>
</head>
<body>
    <c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

    <div class="main-content">
    <form class="form-register" method="post" action="controller">
     <div class="form-register-with-email">
                <div class="form-white-background">
                    <div class="form-title-row">
                        <h1 id="headline"><fmt:message key="text.registration.form"/></h1>
                    </div>
				   
					<p style="color:red">
                        <%--@elvariable id="data_validation_error" type="java.lang.Boolean"--%>
                        <c:if test="${data_validation_error}">
                        <span>
                        <fmt:message key="alert.invalid"/>
                        </span>
                        </c:if>
					</p>
                    <p style="color:red">
                        <%--@elvariable id="user_already_exists" type="java.lang.Boolean"--%>
                        <c:if test="${user_already_exists}">
                        <span>
                        <fmt:message key="alert.user"/>
                        </span>
                        </c:if>
                    </p>
                    <p style="color:red">
                        <%--@elvariable id="registration_error" type="java.lang.Boolean"--%>
                        <c:if test="${registration_error}">
                        <span>
                        <fmt:message key="alert.registration.error"/>
                        </span>
                        </c:if>
                    </p>
				   
				   </br>
                    <div class="form-row">
                        <label>
                            <span><fmt:message key="text.firstname"/><a style="color: red">*</a></span>
                            <input type="text" name="user_first_name" id="first_name" placeholder="enter first name" onblur="validateFirstName()" onchange="validateFirstName()">
                            <h2 id="firstname_error" style="color: white">
                                <fmt:message key="hint.firstname.error"/>
                            </h2>
                        </label>
                    </div>
					<div class="form-row">
                        <label>
                            <span><fmt:message key="text.lastname"/><a style="color: red">*</a></span>
                            <input type="text" name="user_last_name" id="last_name" placeholder="enter last name" onblur="validateLastName()" onchange="validateLastName()">
                            <h2 id="lastname_error" style="color: white">
                                <fmt:message key="hint.lastname.error"/>
                            </h2>
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span><fmt:message key="text.email"/><a style="color: red">*</a></span>
                            <input type="text" name="user_email" id="email" placeholder="enter email" onblur="validateEmail()" onchange="validateEmail()">
                            <h2 id="email_error" style="color: white">
                                <fmt:message key="hint.email.error"/>
                            </h2>
                        </label>
                    </div>
                    <div class="form-row">
                        <label>
                            <span><fmt:message key="text.phone"/><a style="color: red">*</a></span>
                            <input type="text" name="user_phone" id="phone" placeholder="enter phone number" onblur="validatePhone()" onchange="validatePhone()">
                            <h2 id="phone_error" style="color: white">
                                <fmt:message key="hint.phone.error"/>
                            </h2>
                        </label>
                    </div>

                    <div class="form-row">
                        <label>
                            <span><fmt:message key="text.login"/><a style="color: red">*</a></span>
                            <input type="text" name="user_login" id="login" placeholder="enter login"  onblur="validateLogin()" onchange="validateLogin()">
                            <h2 id="login_error" style="color: white">
                                <fmt:message key="hint.login.error"/>
                            </h2>
                        </label>
                    </div>

                    <div class="form-row">
                        <label>
                            <span><fmt:message key="text.password"/><a style="color: red">*</a></span>
                            <input type="password" name="user_password" id="password" placeholder="enter password"  onblur="validatePassword()" onchange="validatePassword()">
                            <h2 id="password_error" style="color: white">
                                <fmt:message key="hint.password.error"/>
                            </h2>
                        </label>
                    </div>

					<input type="submit" class="btn_register" value="Register">
                    <input type="hidden" name="command" value="register" />
                </div>

            </div>

        </form>

        <div class="row-sign-up">
            <form method="GET" action="controller">
                <button class="form-sign-up" type="submit" name="path" value="login_page"><fmt:message key="button.signup"/> </button>
                <input type="hidden" name="command" value="forward" />
            </form>
        </div>
    </div>

    <c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
