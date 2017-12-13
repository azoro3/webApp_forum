<%-- 
    Document   : login
    Created on : 4 déc. 2017, 10:15:38
    Author     : Arthur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<form name='loginForm' action="<c:url value='/login' />" method='POST'>
    <table>
        <tr>
            <td>Nom:</td>
            <td><input type='text'
                       name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password'
                       name='password'/></td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit"
                       type="submit" value="submit"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
