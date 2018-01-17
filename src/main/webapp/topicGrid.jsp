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
            <h2><spring:message code="projectpage.subject"/> : ${project.subject}</h2>
        </div>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th><spring:message code="projectpage.title"/></th> <!-- topic name -->
                <th><spring:message code="projectpage.posts"/></th> <!-- number of posts -->
                <th><spring:message code="projectpage.author"/></th> <!-- project author -->
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${topics}" var="topic">
                <tr>
                    <td class="align-middle"><a class="btn-block"
                                                href="${topic.projectName}/${topic.id}"> ${topic.title} </a></td>
                    <td class="align-middle">${topic.messages.size()}</td>
                    <td class="align-middle">${topic.author}</td>
                    <td>
                        <spring:message code="button.follow" var="follow"/>
                        <form id="subscribe" method="POST" action="${topic.projectName}/${topic.id}/subscribe">
                            <input class="align-center btn btn-primary" type="submit" value="${follow}"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div>
            <form name='createTopicForm' action="#" method='POST' class="form-signin">
                <h4 class="form-signin-heading align-center"><spring:message code="projectpage.create.topic"/></h4>
                <div class="form-group">
                    <spring:message code="label.topic.title" var="title"/>
                    <input name="title" type="text" class="form-control" id="exampleFormControlInput1"
                           placeholder="${title}">
                    <span>${errorTitre}</span>
                </div>
                <div class="form-group">
                    <spring:message code="label.topic.message" var="message"/>
                    <textarea name="message" class="form-control" id="exampleFormControlTextarea1" rows="3"
                              placeholder="${message}"></textarea>
                </div>
                <input name="projectName" type="hidden" value="${project.subject}"/>
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