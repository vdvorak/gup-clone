<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Редактирование тарифов | Панель управления</title>

    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-top-links.jsp"/>
    <!-- Links -->
</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-left-bar.jsp"/>
    <!-- Navigation -->

    <form action="#" method="post" class="customForm">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Тарифы</h1>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table id="tariff" class="table table-bordered table-hover" cellspacing="0" width="100%">
                                    <thead>
                                    <tr>
                                        <th>Тривалість, міс.</th>
                                        <th>Вартість, грн</th>
                                        <th>Вартість на olx.ua</th>
                                        <th>Переваги, характеристики</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                    <tfoot>

                                    <tr>
                                        <td colspan="2" align="center">
                                            <a href="admin-edit-tariffs"><button class="btn btn-primary disabled" id="saveOfferButton">Сохранить</button></a>
                                        </td>
                                        <td colspan="2" align="center">
                                            <a href="admin-tariffs"><button class="btn btn-primary disabled" id="viewOfferButton">Просмотр</button></a>
                                        </td>
                                    </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

</div>

<!-- Bottom Links -->
<jsp:include page="/WEB-INF/templates/admin-panel/admin-bottom-links.jsp"/>
<!-- Bottom Links -->

<script src="/resources/js/admin/admin-edit-tariffs.js"></script>
<script src="/resources/js/admin/admin_script.js"></script>

</body>
</html>