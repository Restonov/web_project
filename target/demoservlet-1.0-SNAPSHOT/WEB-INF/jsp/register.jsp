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
</head>
<body>
    <c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

    <div class="main-content">
    <form class="form-register" method="post" action="controller">
     <div class="form-register-with-email">
                <div class="form-white-background">
                    <div class="form-title-row">
                        <h1>Register</h1>
                    </div>
				   
					<p style="color:red">
                        <c:if test="${data_validation_error}">
                        <span>
                        Invalid registration data
                        </span>
                        </c:if>
					</p>
                    <p style="color:red">
                        <c:if test="${user_already_exists}">
                        <span>
                        User with such data already existing
                        </span>
                        </c:if>
                    </p>
                    <p style="color:red">
                        <c:if test="${registration_error}">
                        <span>
                        Registration error
                        </span>
                        </c:if>
                    </p>
				   
				   </br>
                    <div class="form-row">
                        <label>
                            <span>First name</span>
                            <input type="text" name="first_name" id="first_name" placeholder="enter first name" onblur="validateFirstName()" onchange="validateFirstName()">
                            <h2 id="firstname_error"></h2>
                        </label>
                    </div>
					<div class="form-row">
                        <label>
                            <span>Last name</span>
                            <input type="text" name="last_name" id="last_name" placeholder="enter last name" onblur="validateLastName()" onchange="validateLastName()">
                            <h2 id="lastname_error"></h2>
                        </label>
                    </div>

                    <div class="form-row">
                        <label>
                            <span>Email</span>
                            <input type="text" name="email" id="email" placeholder="enter email" onblur="validateEmail()" onchange="validateEmail()">
                            <h2 id="email_error"></h2>
                        </label>
                    </div>

                    <div class="form-row">
                        <label>
                            <span>Login</span>
                            <input type="text" name="login" id="login" placeholder="enter login"  onblur="validateLogin()" onchange="validateLogin()">
                            <h2 id="login_error"></h2>
                        </label>
                    </div>

                    <div class="form-row">
                        <label>
                            <span>Password</span>
                            <input type="password" name="password" id="password" placeholder="enter password"  onblur="validatePassword()" onchange="validatePassword()">
                            <h2 id="password_error"></h2>
                        </label>
                    </div>

					<input type="submit" class="btn_register" value="Register">
                    <input type="hidden" name="command" value="register" />
                </div>

            </div>

        </form>

        <div class="row-sign-up">
            <form method="POST" action="controller">
                <button class="form-sign-up" type="submit" name="path" value="path.page.login">Already have an account?<strong>Login here</strong></button>
                <input type="hidden" name="command" value="redirect" />
            </form>
        </div>
    </div>

    <c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>
