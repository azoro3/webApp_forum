<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link type="text/css" href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="/resources/css/common.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form name='loginForm' action="<c:url value='/login'/>" method='POST' class="form-signin">
        <div class="form-group ${error != null ? 'has-error' : ''}">
            <spring:message code="label.user.username" var="username"/>
            <spring:message code="label.user.password" var="password"/>
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="${username}"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="${password}"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                    code="button.login"/></button>
            <p class="text-center"><a href="<c:url value='/registration'/>"><spring:message
                    code="login.create.account"/></a></p>
        </div>
    </form>
</div>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/popper-1.13.0.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
