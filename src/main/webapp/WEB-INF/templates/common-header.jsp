<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 20.01.2016
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Header for authorized person-->

<sec:authorize access="isAuthenticated()" var="isAuthenticated">
    <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
</sec:authorize>

<!-- END header for authorized person -->


<!--Header for anonym person-->

<c:if test="${!isAuthenticated}">
    <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</c:if>

<!-- END Header for anonym -->