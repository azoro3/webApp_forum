<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="text/css" href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <jsp:include page="userHeader.jsp"/>

        <div class="header">
            <h2><spring:message code="homepage.forum"/></h2>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th><spring:message code="homepage.project"/></th> <!-- project name -->
                <th><spring:message code="homepage.topic"/></th> <!-- number of topics -->
                <th><spring:message code="homepage.author"/></th> <!-- project author -->
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${projects}" var="project">
                <tr>
                    <td><a class="btn-block" href="projects/${project.subject}"> ${project.subject} </a></td>
                    <td>${project.topics.size()}</td>
                    <td>${project.author}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div>
            <form name='createProjectForm' action="" method='POST' class="form-signin">
                <h4 class="form-signin-heading align-center"><spring:message code="homepage.create.project"/></h4>
                <div class="form-group">
                    <spring:message code="label.project.subject" var="subject"/>
                    <input name="subject" type="text" class="form-control" placeholder="${subject}"
                           autofocus="true"/>
                    <span>${errorSubject}</span>
                </div>
                <input type="hidden" name="author" value="${pageContext.request.userPrincipal.name}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                        code="button.create.project"/></button>
            </form>
        </div>
    </c:if>
</div>
</body>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/popper-1.13.0.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</html>