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

        <jsp:include page="userHeader.jsp"/>

        <div class="header">
            <h2><spring:message code="topicpage.messages"/></h2>
        </div>

        <div class="card">
            <div class="card-header">${topic.author}</div>
            <div class="card-body">
                <h4 class="card-title">${topic.title}</h4>
                <p class="card-text">${topic.initialMessage}</p>
            </div>
        </div>
        <c:forEach items="${messages}" var="message">
            <br/>
            <div class="card">
                <div class="card-header">${message.author}</div>
                <div class="card-body">
                    <p class="card-text">${message.content}</p>
                </div>
            </div>
        </c:forEach>

        <div>
            <form name='createMessageForm' action="" method='POST' class="form-text">
                <h4 class="form-signin-heading align-center"><spring:message code="projectpage.create.message"/></h4>
                <div class="form-group">
                    <spring:message code="label.topic.message" var="message"/>
                    <textarea name="message" class="form-control" id="exampleFormControlTextarea1" rows="3"
                              placeholder="${message}"></textarea>
                </div>
                <input name="author" type="hidden" value="${pageContext.request.userPrincipal.name}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message
                        code="button.create"/></button>
            </form>
        </div>
    </c:if>
</div>
</body>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/popper-1.13.0.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</html>