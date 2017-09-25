<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head lang="ru">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Объявления</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-modal.css" rel="stylesheet">
    <link href="/resources/css/com.css" rel="stylesheet">
    <link href="/resources/css/pgwslideshow.css" rel="stylesheet" type="text/css">
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">
<jsp:include page="/WEB-INF/templates/header.jsp"/>
<div class="container-fluid">

    <!--category-->
    <div class="row">
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Объявления
            </div>
        </div>
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Работа
            </div>
        </div>
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Блог
            </div>
        </div>
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Новости
            </div>
        </div>
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Тендеры
            </div>
        </div>
        <div class="col-xs-2" style="padding-left: 5px; padding-right: 5px;">
            <div class="btn btn-info btn-block">
                Проекты
            </div>
        </div>
    </div>
    <!--category-->

    <!--search-->
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">
        <div class="col-xs-4" style="padding-left: 5px; padding-right: 5px;">
            <a href="/create-offer">
                <button class="btn btn-info btn-sm" id="btn-chat"> ПОДАТЬ ОБЪЯВЛЕНИЕ</button>
            </a>
        </div>
    </div>
    <!--search-->

    <!--gallery-->
    <center><h1>Мои объявления</h1></center>
    <div class="row" style="background-color: #bcd6d9; padding: 15px; margin-top: 25px;">

        <div class="col-xs-9" style="padding-left: 5px; padding-right: 5px;">

            <c:choose>
                <c:when test="${offers.entities.size()==0}">
                    <h2>По вашему запросу ничего не найдено</h2>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${offers.entities}" var="offer">
                        <ul style="display: inline-table; list-style-type: none">

                            <li style="background-color: white">
                                <a rel="example_group"
                                   href="/offer/${offer.id}"
                                   title="${offer.title}">
                                    <c:choose>
                                        <c:when test="${not empty offer.imagesIds.keySet()}">
                                            <c:forEach items="${offer.imagesIds.keySet()}" var="id" varStatus="status">
                                                <c:if test="${status.first}">
                                                    <img alt="" width="150" height="150"
                                                         src="/api/rest/fileStorage/OFFERS/file/read/id/${id}">
                                                </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <img alt="" width="150" height="150" src="/resources/images/no_photo.jpg">
                                        </c:otherwise>
                                    </c:choose>
                                    <div>${offer.title}</div>
                                    <div>${offer.price}</div>
                                    <div>${offer.views}</div>
                                    <div><a href="/edit-offer/${offer.id}" style="color: #087aff;">Редактировать</a>
                                    </div>

                                    <c:choose>
                                        <c:when test="${offer.active}">
                                            <button type="button" class="btn btn-default btn-md deactivateOffer"
                                                    style="margin-left: 25px" value="${offer.id}">
                                                Деактевировать
                                            </button>
                                        </c:when>
                                        <c:otherwise>
                                            <button type="button" class="btn btn-default btn-md activeOffer"
                                                    style="margin-left: 25px" value="${offer.id}">
                                                Активировать
                                            </button>
                                        </c:otherwise>
                                    </c:choose>

                                    <button type="button" class="btn btn-default btn-md" data-toggle="modal"
                                            data-target="#deleteModal" style="margin-left: 25px">
                                        Удалить
                                    </button>


                                    <!-- Modal window for delete offer -->
                                    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
                                         aria-labelledby="myModalLabel">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close"><span aria-hidden="true">&times;</span>
                                                    </button>
                                                    <h4 class="modal-title" id="myModalLabel">Подтвердите удаление</h4>
                                                </div>
                                                <div class="modal-body">
                                                    Вы действительно желаете удалить объявление?
                                                    После удаление восстановить объявление будет невозможно.
                                                </div>
                                                <div class="modal-footer">
                                                    <button id="${offer.id}" type="button"
                                                            class="btn btn-danger deleteOffer" data-dismiss="modal">
                                                        Удалить
                                                    </button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        Отмена
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- Modal window for delete offer -->


                                </a>
                            </li>
                        </ul>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div id="pages">

    </div>
    <!--gallery-->

</div>

<!-- script references -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/bootstrap-modalmanager.js"></script>
<script src="/resources/js/bootstrap-modal.js"></script>
<script src="/resources/js/oauth2.js"></script>
<script src="/resources/js/cookie.js"></script>
<script src="/resources/js/user.js"></script>

<script>
    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });

    $('#deleteModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });

    $('#amount').change(function () {
        $.ajax({
            url: '/account/getLiqPayParam',
            method: 'POST',
            data: {'amount': $('#amount').val()},
            success: function (response) {
                $('#liq-pay-data').val(response[0]);
                $('#liq-pay-signature').val(response[1]);
            }
        });
    });

    $('.deleteOffer').click(function () {
        var id = $(this).attr("id");
        $.ajax({
            url: '/offer/id/' + id + '/delete',
            method: 'POST',
            success: function (response) {
                window.location.href = '/account';
            },
            error: function (response) {
                window.location.href = '/account';
            }
        });
    });

    $('.deactivateOffer').click(function () {
        var id = $(this).attr("value");
        $.ajax({
            url: '/swagger/rest/offersService/offer/id/' + id + '/setActive/false',
            method: 'POST',
            success: function (response) {
                window.location.href = '/account';
            },
            error: function (response) {
                window.location.href = '/account';
            }
        });
    });

    $('.activeOffer').click(function () {
        var id = $(this).attr("value");
        $.ajax({
            url: '/swagger/rest/offersService/offer/id/' + id + '/setActive/true',
            method: 'POST',
            success: function (response) {
                window.location.href = '/account';
            },
            error: function (response) {
                window.location.href = '/account';
            }
        });
    });

    $('a').on('click', function (event) {
        event.preventDefault();
        var url = this.href;
        oauth2.get(url);
    });
</script>

</body>
</html>

