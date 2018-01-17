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
    <h2><spring:message code="homepage.userdetails"/></h2>
    <br/>
    <span>${message}</span>

    <div class="row">
        <label class="col-sm-10">
            <a href="${contextPath}/"><spring:message code="homepage.goback"/></a>
        </label>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.user.username"/></label>
        <div class="col-sm-10">${user.username}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.user.email"/></label>
        <div class="col-sm-10">${user.email}</div>
    </div>

    <div class="row">
        <label class="col-sm-2"><spring:message code="label.user.topics"/></label>
        <div class="col-sm-10">
            <table class="table table-striped table-hover">
                <c:forEach items="${user.topics}" var="topics">
                    <tr>
                        <td><a class="btn-block"
                               href="${contextPath}/projects/${topics.projectName}/${topics.id}">${topics.title}</a>
                        </td>
                        <td>${topics.author}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
</body>