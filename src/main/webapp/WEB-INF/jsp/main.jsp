<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
    <title>TyRent</title>
</head>
<body>
    <%--Pages header--%>
    <c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

    <%--Page alerts--%>
    <%--@elvariable id="car_not_available_alert" type="java.lang.Boolean"--%>
    <c:if test="${car_not_available_alert}">
    <ctg:page_alert type="danger" hide="true">
            <fmt:message key="alert.car.not.available"/>
    </ctg:page_alert>
    </c:if>

    <%--@elvariable id="welcome_message" type="java.lang.Boolean"--%>
    <c:if test="${welcome_message}">
    <ctg:page_alert type="success" hide="true">
            <fmt:message key="message.welcome"/>
    </ctg:page_alert>
    </c:if>

    <%--Adds carousel--%>
    <main role="main"><nav aria-label="Page navigation example">
    </nav>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="1" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="2" class=""></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item">
                    <img style="object-fit:cover; height: 45vh" class="first-slide" src="${pageContext.request.contextPath}/images/carousel_sport.jpg" alt="First slide"/>
                    <div class="container">
                        <div class="carousel-caption text-left">
                            <h1><strong>TyRent</strong></h1>
                            <p>
                                <fmt:message key="main.tyrent.info.sign"/>
                            </p>
                            <p>
                                <%--@elvariable id="user" type="by.restonov.tyrent.model.entity.User"--%>
                                    <c:if test="${user == null}">
                                        <form action="controller" method="get">
                                            <input type="submit" class="btn btn-lg btn-primary" value="<fmt:message key="main.button.sign.up"/>"/>
                                            <input type="hidden" name="path" value="register_page">
                                            <input type="hidden" name="command" value="forward">
                                        </form>
                                    </c:if>
                            </p>
                        </div>
                    </div>
                </div>
                <div class="carousel-item active">
                    <img style="object-fit:cover; height: 45vh" class="second-slide" src="${pageContext.request.contextPath}/images/carousel_bmw.jpg" alt="Second slide">
                    <div class="container">
                        <div class="carousel-caption">
                            <h1><strong>TyRent</strong></h1>
                            <p><fmt:message key="main.tyrent.info"/></p>
                        </div>
                    </div>
                </div>
            </div>
            <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>

        <div class="album py-5 bg-light">
            <div class="container">
                <div class="row">
                    <ctg:main_page_car_list/>
                </div>
            </div>
        </div>

    <c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>

</body>
</html>
