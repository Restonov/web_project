<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>
    <jsp:useBean id="car" scope="request" type="by.restonov.tyrent.entity.Car"/>
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="background: url(${pageContext.request.contextPath}${car.imageBackground}) no-repeat">
        <div class="col-md-5 p-lg-5 mx-auto my-5" style="left: -420px">
        <h1 class="display-4 font-weight-normal" style="left: auto; position: relative; right: 0; float: none; text-align: center; margin-right: auto;">${car.name}<br></h1>
            <p class="lead font-weight-normal" style="position: relative; right: 0;"><fmt:message key="text.offer"/>${car.cost} <fmt:message key="text.cost"/><br></p>
            <form action="controller" method="post">
                <c:choose>
                    <c:when test="${car.state.toString() eq 'AVAILABLE'}">
                        <ctg:book_car_button/>
                    </c:when>
                    <c:otherwise>
                        <a style="color: red">
                            <fmt:message key="text.ifnot"/>
                        </a>
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="chosen_car_id" value="${car.id}"/>
            </form>
    </div>
    </div>
        <div class="d-md-flex flex-md-equal w-100 my-md-3 pl-md-3" style="margin-left: 25px">
            <div class="bg-dark mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center text-white overflow-hidden">
                <div class="my-3 py-3">
                    <h2 class="display-5"><fmt:message key="text.cabin"/></h2>
                    <p class="lead">${car.description}</p>
                </div>
                <img src="${pageContext.request.contextPath}/images/performance.jpg" style="" width="500" height="250">
            </div>

            <div class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
                <div class="my-3 p-3">
                    <h2 class="display-5"><fmt:message key="text.about.headline"/></h2>
                    <p class="lead"><fmt:message key="text.about"/></p>
                </div>
                <img src="${pageContext.request.contextPath}${car.imageCabin}" style="" width="500" height="250">
            </div>
            <div class="bg-light mr-md-3 pt-3 px-3 pt-md-5 px-md-5 text-center overflow-hidden">
                <div class="my-3 p-3">
                    <h2 class="display-5"><fmt:message key="text.location.headline"/></h2>
                    <p class="lead"><fmt:message key="text.location"/></p>
                </div>
                <img src="${pageContext.request.contextPath}/images/location.jpg" style="" width="500" height="250">
            </div>
        </div>
    <c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>