<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div>
    <form:form method="POST" modelAttribute="createTopicForm">
        <h2>Créer votre Topic</h2>
        <spring:bind path="projectName">
            <div>
                <form:input type="text" path="projectName" placeholder="Project Name"
                            autofocus="true"></form:input>
                <form:errors path="projectName"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="title">
            <div>
                <form:input type="text" path="title" placeholder="Title"
                            autofocus="true"></form:input>
                <form:errors path="title"></form:errors>
            </div>
        </spring:bind>
        <spring:bind path="author">
            <div>
                <form:input type="text" path="author" placeholder="Author"
                            autofocus="true"></form:input>
                <form:errors path="author"></form:errors>
            </div>
        </spring:bind>
        <button type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>