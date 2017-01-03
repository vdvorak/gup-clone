<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Арендатор | Панель управления</title>
    <meta charset='utf-8' />

    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-top-links.jsp"/>
    <link href='/resources/css/admin/admin-tenant-rents.css' rel='stylesheet'/>
    <!-- Links -->
</head>
<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-left-bar.jsp"/>
    <!-- Navigation -->

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header"><u>Арендатор</u> &rarr; <a href="admin-landlord-rents" style="font-size:40px;">Арендодатель</a></h1>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">

                <table>
                    <tr>
                        <td> &nbsp;&nbsp; Объявление: &nbsp;&nbsp; </td>
                        <td> <select id="offers-selector" name="offers-selector"></select> </td>
                        <td> &nbsp;&nbsp; | &nbsp;&nbsp; </td>
                        <td> Арендодатель: &nbsp;&nbsp; </td>
                        <td> <legend id="offers-result3"></legend> </td>
                    </tr>
                </table>

                <br>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <!--<div id='external-events'></div>-->
                            <div id='calendar'></div>
                        </div>
                    </div>
                </div>
                <br>

                <!--<fieldset>
                    <legend id="offers-result2"></legend>
                    <div id='monthOfPrices'></div>
                    --><!--<font color="#2980b9" id="offers-result41"></font>-->
                <!--<font color="#FF5733" id="offers-result42"></font>-->
                <!--<font color="gray" id="offers-result1"></font>--><!--
                </fieldset>-->
            </div>
        </div>
    </div>
</div>

</body>

<!-- Bottom Links -->
<jsp:include page="/WEB-INF/templates/admin-panel/admin-bottom-links2.jsp"/>
<script src="/resources/js/admin/admin-rents.js"></script>
<script src="/resources/js/admin/admin-tenant-rents.js"></script>
<!-- Bottom Links -->
</html>
