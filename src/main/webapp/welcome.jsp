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
        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h2>
        <br />
        <a href="/createProject" class="btn btn-primary" >Create new project</a>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Project</th> <!-- project name -->
                    <th>Topics</th> <!-- number of topics -->
                    <th>Author</th> <!-- project author -->
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${projects}" var="project">
                    <tr>
                        <td> <a class="btn-block" href="projects/${project.id}"> ${project.subject} </a></td>
                        <td>${project.topics.size()}</td>
                        <td>${project.author}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</html>