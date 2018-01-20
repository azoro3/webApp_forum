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
    <h2><spring:message code="errorpage.error"/></h2>
    <div class="row">
        <div class="col-sm-12">
            <p><spring:message code="errorpage.message"/></p>
        </div>
    </div>

    <div class="row">
        <label class="col-sm-10">
            <a href="${contextPath}/"><spring:message code="homepage.goback"/></a>
        </label>
    </div>
</div>
</body>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/popper-1.13.0.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</html>