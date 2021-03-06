<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="menu">
    <div class="container2">
        <sec:authorize var="loggedIn" access="isAuthenticated()" />
        <c:choose>
            <c:when test="${loggedIn}">
                <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="/WEB-INF/templates/anonymHeader.jsp"/>
            </c:otherwise>
        </c:choose>

        <div id="errorRaiser">
            <p class="messagePlace"></p>
        </div>

    </div>
</header>

