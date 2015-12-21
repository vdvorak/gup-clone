<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Nova Era</title>
    <!-- Bootstrap CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <style type="text/css">
        .myrow-container {
            margin: 20px;
        }
    </style>
</head>
<body class=".container-fluid">
<c:redirect url="/"/>
<div class="container myrow-container">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <div>
                    <form class="form-inline" action="<c:url value="/logout" />" method="post">
                        <div class="form-group" align="left">
                            <p class="form-control-static">Admin page</p>
                        </div>
                        <a class="btn btn-default" href="${context}/adminPage"> Your email: <sec:authentication
                                property="principal.username"/></a>
                        <button type="submit" class="btn btn-default pull-right">Log out</button>
                        <input type="hidden" title="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </div>
            </h3>
        </div>
        <div class="panel-body">
            Admin Page
        </div>
    </div>
    <script src="<c:url value="/resources/js/jquery-2.1.4.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</div>
</body>
</html>

