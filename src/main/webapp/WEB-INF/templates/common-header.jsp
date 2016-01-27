<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authorize access="isAuthenticated()" var="isAuthenticated">
    <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
</sec:authorize>

<sec:authorize access="isAnonymous()" var="isAuthenticated">
    <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</sec:authorize>
