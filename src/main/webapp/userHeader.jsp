<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="user">
    <span class="align-right">
        <spring:message code="homepage.welcome"/> <a
            href="${contextPath}/users/${pageContext.request.userPrincipal.name}">${pageContext.request.userPrincipal.name}</a>
        <br/>
        <a href="?lang=en"><spring:message code="homepage.english"/></a> | <a href="?lang=fr"><spring:message code="homepage.french"/></a>
        <br/>
        <a class="text-primary" onclick="document.forms['logoutForm'].submit()"><spring:message code="homepage.logout"/></a>
    </span>
</div>