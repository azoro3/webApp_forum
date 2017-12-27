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
        <h3>Project : ${project.subject}</h3>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>Title</th> <!-- topic name -->
                    <th>Posts</th> <!-- number of posts -->
                    <th>Author</th> <!-- project author -->
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${topics}" var="topic">
                    <tr>
                        <td> <a class="btn-block" href="${topic.projectName}/${topic.id}"> ${topic.title} </a></td>
                        <td>${topic.messages.size()}</td>
                        <td>${topic.author}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div>
            <center><h4>Créer votre topic</h4></center>
            <form name='createTopicForm' action="#" method='POST' class="form-signin">
                <div class="form-group">
                    <label for="exampleFormControlInput1">Title</label>
                    <input name="title" type="text" class="form-control" id="exampleFormControlInput1" placeholder="Title">
                    <span>${errorTitre}</span>
                </div>
                <div class="form-group">
                    <label for="exampleFormControlTextarea1">Message</label>
                    <textarea name="message" class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                </div>
                <input name="projectName" type="hidden" value="${project.subject}"/>
                <input name="author" type="hidden" value="${pageContext.request.userPrincipal.name}"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Créer le topic</button>
            </form>
        </div>
    </c:if>
</div>
</body>
<script src="/resources/js/jquery-3.2.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</html>