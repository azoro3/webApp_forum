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
<div>
    <center><h2>Créer votre projet</h2></center>
    <form name='createProjectForm' action="<c:url value='/createProject'/>" method='POST' class="form-signin">
        <div class="form-group">
            <input name="subject" type="text" class="form-control" placeholder="Subject"
                   autofocus="true"/>
            <span>${errorSubject}</span>
            <input name="author" type="text" class="form-control" placeholder="Author"
                   autofocus="true"/>
            <span>${errorAuthor}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Créer le projet</button>
        </div>
    </form>
</div>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>