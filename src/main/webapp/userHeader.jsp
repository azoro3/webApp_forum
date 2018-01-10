<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="user">
    <span class="align-right">
        Welcome <a
            href="${contextPath}/users/${pageContext.request.userPrincipal.name}"> ${pageContext.request.userPrincipal.name}</a>
        <br/>
        <a class="text-primary" onclick="document.forms['logoutForm'].submit()">Logout</a>
    </span>
</div>