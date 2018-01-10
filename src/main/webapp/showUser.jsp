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
    <h2>User Detail</h2>
    <br />
    <span>${message}</span>

    <div class="row">
        <label class="col-sm-2">Name</label>
        <div class="col-sm-10">${user.username}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Email</label>
        <div class="col-sm-10">${user.email}</div>
    </div>

</div>
</body>