<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="custom_tags" %>
<html>
<head>
    <title><fmt:message key="profile.title"/></title>
    <link href="${pageContext.request.contextPath}/css/modal.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        <c:import url="/js/noRefresh.js"/>
    </script>
</head>
<body>
<jsp:useBean id="user" scope="request" type="by.restonov.tyrent.model.entity.impl.User"/>
<c:import url="/WEB-INF/jsp/common/header.jsp" charEncoding="utf-8"/>

<div class="container" style="margin-top: 40px">
    <div class="main-body">
        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="${pageContext.request.contextPath}/images/avatar.png" alt="Admin" class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${user.login}</h4>
                                <p class="text-secondary mb-1">${user.role.toString().toLowerCase()}</p>
                                <p class="text-secondary mb-1">${user.state.toString().toLowerCase()}</p>
                                <a style="color: red; cursor: pointer" id="btn_modal_window"><fmt:message key="profile.button.modal"/></a>
                                <div id="modal" class="modal">
                                    <div class="modal_content">
                                        <span class="close_modal_window">×</span>
                                        <p><fmt:message key="profile.modal.headline"/></p>
                                        <form class="modal_form" action="controller" method="post">
                                            <label>
                                                <select name="incident_type_id" style="height: 30px; padding-left: 10px">
                                                    <option value=101><fmt:message key="profile.modal.speed.limit"/></option>
                                                    <option value=201><fmt:message key="profile.modal.traffic"/></option>
                                                    <option value=301><fmt:message key="profile.modal.contract"/></option>
                                                </select>
                                            </label>
                                            <label>
                                                <input name="car_id" type="text" placeholder="<fmt:message key="profile.modal.car.id"/>" style="padding-left: 10px; height: 30px" required/>
                                            </label>
                                            <label>
                                                <textarea rows="4" cols="50" name="incident_description" placeholder="<fmt:message key="profile.modal.textarea"/>" class="message" required></textarea>
                                            </label>
                                            <input name="command" type="hidden" value="report_incident" />
                                            <input name="user_id" type="hidden" value="${user.id}" />
                                            <input name="submit" class="button" type="submit" value="<fmt:message key="profile.modal.save"/>" />
                                        </form>
                                    </div>
                                </div>
                                <script>
                                    <c:import url="/js/modal.js" charEncoding="UTF-8"/>
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-github mr-2 icon-inline"><path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path></svg>Github</h6>
                            <span class="text-secondary">Restonov</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-instagram mr-2 icon-inline text-danger"><rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line></svg>Instagram</h6>
                            <span class="text-secondary">Restonov</span>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.text.name"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.firstName} ${user.lastName}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.text.email"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.email}
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.text.phone"/></h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                ${user.phone}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <h6 class="mb-0"><fmt:message key="profile.text.orders"/></h6>
                            </div>
                        </div>
                        <table class="table" style="margin-top: 15px">
                            <thead>
                            <tr>
                                <th><fmt:message key="profile.text.order.id"/></th>
                                <th><fmt:message key="profile.text.car.model"/></th>
                                <th><fmt:message key="profile.text.car.cost"/></th>
                                <th><fmt:message key="profile.text.order.date"/></th>
                                <th><fmt:message key="profile.text.order.state"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <ctg:current_user_order user="${user}"/>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" charEncoding="utf-8"/>
</body>
</html>