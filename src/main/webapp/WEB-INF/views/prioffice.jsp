<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Optical Illusion
  Date: 30.12.2015
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>GUP</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>


<section>
    <div class="prioffice-wrap">
        <!--LEFT_SIDE-->
        <!-- ЧЕРТИ ЧТО ДОПИСАТЬ И ДОПОЛНИТЬ ИНФОРМАЦИЕЙ-->
        <div class="prioffice-left">
            <div class="prioffice-user">${profile.username}</div>
            <div class="prioffice-userpic">
                <c:choose>
                    <c:when test="${not empty profile.contact.pic}">
                        <img src="/api/rest/fileStorage/PROFILE/file/read/id/${profile.contact.pic}" width="100" ,
                             hight="100">
                    </c:when>
                    <c:otherwise>
                        <img src="/resources/images/no_photo.jpg" width="100" , hight="100">
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="prioffice-mysettings">
                <div class="prioffice-mysettings-title">Мои настройки</div>
                <div class="prioffice-mysettings-menu-wrap">
                    <img class="prioffice-mysettings_open" src="/resources/img/strippeddownbutton.png">
                </div>

                <div id="accordion">
                    <p>Кому видна моя страница</p>

                    <div>
                        Список 1
                        <input type="checkbox"><br>
                        Список 2
                        <input type="checkbox"><br>
                        Список 3
                        <input type="checkbox"><br>
                    </div>
                    <p>Параметры настройки</p>

                    <div>
                        <p>что-то </p>
                    </div>
                    <p>Параметры настройки</p>

                    <div>
                        <p>что-то </p>
                    </div>
                    <p>Параметры настройки</p>

                    <div>
                        <p>что-то </p>
                    </div>
                </div>
                <div class="prioffice-mysettings-submit">
                    <a href="#">Сохранить</a>
                </div>
                <div class="prioffice-mysettings-menu-wrap">
                    <img class="prioffice-mysettings_close" src="/resources/img/strippedupbutton.png">
                </div>
            </div>
        </div>


        <!-- RIGHT_SIDE-->

        <div class="prioffice-right">
            <div class="prioffice-tabs-wrap">
                <div class="tabs">
                    <ul>
                        <li class="prioffice-tabs-title">Сообщения</li>
                        <li class="prioffice-tabs-title">Уведомления</li>
                    </ul>
                    <div class="prioffice-tabs-items-wrap">
                        <div>
                            <div class="prioffice-tabs-items">
                                <c:if test="${not empty dialogues}">
                                    <c:forEach items="${dialogues}" var="dialogue">
                                        <c:forEach items="${dialogue.members}" var="member">
                                            <c:choose>
                                                <c:when test="${not empty profile.contact.pic}">
                                                    <div class="prioffice-tabs-items-pic">
                                                        <img src="/api/rest/fileStorage/PROFILE/file/read/id/${member.userPicId}"
                                                             width="80" , hight="80">
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="prioffice-tabs-items-pic">
                                                        <img src="/resources/images/no_photo.jpg" width="80" ,
                                                             hight="80">
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <p class="prioffice-tabs-items-text"><a
                                                href="/dialogue/id/${dialogue.id}">${dialogue.messages.get(dialogue.messages.size()-1).message}</a>
                                        </p>

                                        <p class="prioffice-tabs-items-status">
                                            непрочитанных: ${dialogue.unreadMsgCounter.get(profile.id)}</p>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty dialogues}">
                                    <p><a href="/dialogue/create">создать диалог</a></p>
                                </c:if>
                            </div>
                            <div class="prioffice-tabs-items-return">
                                <a href="/dialogues"><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                        <div class="prioffice-tabs-items-wrap">
                            <div>
                                <div class="prioffice-tabs-items">
                                    <c:if test="${not empty events}">
                                        <c:forEach items="${events}" var="event">
                                            <p>${event.toString()} </p>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${empty events}">
                                        <p>у вас нет новых уведомлений</p>
                                    </c:if>
                                </div>
                                <div class="prioffice-tabs-items-return">
                                    <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="myitems-wrapper">
                <div class="myitems-row">
                    <div class="myitems-tenders">
                        <div class="myitems-tenders-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-tenders-wrap-icon" src="/resources/img/tendersmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мои тендеры</p>
                                </div>
                                <img class="prioffice-close-tenders-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-tenders-items">
                                <c:if test="${not empty tenders}">
                                    <c:forEach items="${tenders}" var="tender">
                                        <p><a href="/tender/${tender.id}">${tender.title}</a></p>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty tenders}">
                                    <p><a href="/tender-make">создать тендер</a></p>
                                </c:if>
                            </div>

                            <div class="myitems-tenders-footer">
                                <a href="/tenders"><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                    <div class="myitems-projects">
                        <div class="myitems-projects-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-projects-wrap-icon" src="/resources/img/projectsmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мои проекты</p>
                                </div>
                                <img class="prioffice-close-projects-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-projects-items">
                                <c:if test="${not empty projects}">
                                    <c:forEach items="${projects}" var="project">
                                        <p><a href="/project/id/${project.id}">${project.title}</a></p>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty projects}">
                                    <p><a href="/createProject">создать проект</a></p>
                                </c:if>
                            </div>
                            <div class="myitems-projects-footer">
                                <a href="/project/list"><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="myitems-row">
                    <div class="myitems-news">
                        <div class="myitems-news-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-news-wrap-icon" src="/resources/img/newssmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мои новости</p>
                                </div>
                                <img class="prioffice-close-news-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-news-items">
                                <c:if test="${not empty blogposts}">
                                    <c:forEach items="${blogposts}" var="n">
                                        <p><a href="/blog/${n.id}">${n.title}</a></p>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty blogposts}">
                                    <p><a href="/blog-create">создать новостной блог</a></p>
                                </c:if>
                            </div>
                            <div class="myitems-news-footer">
                                <a href="/blogs"><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                    <div class="myitems-founds">
                        <div class="myitems-founds-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-founds-wrap-icon" src="/resources/img/ballancesmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext">Мой баланс: ${curentBalance} грн.</p>
                                </div>
                                <img class="prioffice-close-founds1-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-founds-items">
                                <c:forEach items="${balance}" var="b">
                                    <p><span class="date-create">${b.dateTime}</span> пополнено на: ${b.amount} грн.</p>
                                </c:forEach>
                                <p>

                                <form method="post" action="https://www.liqpay.com/api/checkout" accept-charset="utf-8">
                                    <input id="liq-pay-data" type="hidden" name="data"
                                           value="eyJhbW91bnQiOjEwMCwiY3VycmVuY3kiOiJVQUgiLCJkZXNjcmlwdGlvbiI6ItCf0L7Qv9C+0LvQvdC10L3QuNC1INCx0LDQu9Cw0L3RgdCwIiwib3JkZXJfaWQiOiJsM2Q2d1VvR3Rlc3QiLCJwdWJsaWNfa2V5IjoiaTc0MDQ0MTgyODM5Iiwic2FuZGJveCI6IjEiLCJzZXJ2ZXJfdXJsIjoiaHR0cDpcL1wvYmFuay10a2FuaS5yaGNsb3VkLmNvbVwvY2FsbGJhY2siLCJ2ZXJzaW9uIjoiMyJ9"/>
                                    <input id="liq-pay-signature" type="hidden" name="signature"
                                           value="DxXg8vXCVuw39G1Qvk8hmLyad6o="/>
                                    <button type="submit" class="btn btn-primary">Пополнить</button>
                                </form>
                                </p>
                            </div>
                            <div class="myitems-founds-footer">
                            </div>
                        </div>
                    </div>
                </div>


                <div>Объявления</div>
                <c:forEach items="${offers}" var="offer">
                    <a href="/offer/${offer.id}">${offer.title}</a>

                    <c:if test="${offer.moderationStatus == 'NOT_PASSED'}"><span style="color: deeppink;">Ожидает проверки модератором</span></c:if>
                    <c:if test="${offer.moderationStatus == 'COMPLETE'}"><span style="color: deeppink;">Одобрено модератором</span></c:if>
                    <c:if test="${offer.moderationStatus == 'FAIL'}"><span style="color: deeppink;">Отклонено модератором</span></c:if>

                    <a href="/edit-offer/${offer.id}" style="color: green;"><button>Редактировать</button> </a>

                    <c:choose>
                        <c:when test="${offer.active}">
                            <button type="button" class="btn btn-default btn-md deactivateOffer"
                                    style="margin-left: 25px" value="${offer.id}">
                                Деактевировать
                            </button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-default btn-md activeOffer" style="margin-left: 25px"
                                    value="${offer.id}">
                                Активировать
                            </button>
                        </c:otherwise>
                    </c:choose>

                    <button type="button" class="btn btn-default btn-md" data-toggle="modal" data-target="#deleteModal" style="margin-left: 25px">
                        Удалить
                    </button>


                    <!-- Modal window for delete offer -->
                    <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Подтвердите удаление</h4>
                                </div>
                                <div class="modal-body">
                                    Вы действительно желаете удалить объявление?
                                    После удаление восстановить объявление будет невозможно.
                                </div>
                                <div class="modal-footer">
                                    <button id="${offer.id}" type="button" class="btn btn-danger deleteOffer" data-dismiss="modal">Удалить</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Modal window for delete offer -->
                    <br>
                    <hr>
                </c:forEach>
            </div>
        </div>
    </div>
</section>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
<script src="/resources/js/vendor/bootstrap.js"></script>
<script src="/resources/js/jquery.bxslider.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>

<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<sec:authorize var="loggedIn" access="isAuthenticated()" />
<c:choose>
    <c:when test="${loggedIn}">
        <script src="/resources/js/autorizedHeader.js"></script>
    </c:when>
    <c:otherwise>
        <script src="/resources/js/anonymHeader.js"></script>
    </c:otherwise>
</c:choose>

<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>







<!-- libs starts here-->
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
<!--END of libs-->



<script>

    var firstBlock = $('#startBlock').html();

    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });

    $('#deleteModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });


    // ------------------- Create default block of tenders -------------------------------------------------------



        var projectFO = {};
        projectFO.skip = 0;
        projectFO.limit = 3;

        function findFirstImg(arr) {
            var url = '';
            var imgId = '';
            for (var i in arr) {
                if (arr[i] === 'image') {
                    imgId = i;
                    break;
                }
            }
            url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
            return url;
        }

        function localDateTime(long) {
            long = new Date(parseInt(long));
            long = moment(long).locale("ru").format('LLL');
            return long;
        }

        function draw(data) {
            for (var i in data) {
                $('.tender-tabs-items-wrap').last().attr('style', 'display:;');
                $(".tender-pic-wrap img").last().attr('src', findFirstImg(data[i].uploadFilesIds));
                $(".tender-pic-wrap a").last().attr('href', '/tender/' + data[i].id);
                $(".tender-item-text p").last().html(data[i].body);
                $(".tender-number").last().text(data[i].tenderNumber);
                $(".tender-publish-date span").last().text(localDateTime(data[i].begin));
                $(".tender-veiws").last().text(data[i].visited);
                $(".tender-proposal-count").last().text(data[i].proposeNumber);
                $(".tender-name p").last().text(data[i].title);
                $(".date-create").last().text(localDateTime(data[i].end));
                $('#startBlock').append(firstBlock);
            }

            $('.tender-tabs-items-wrap').last().attr('style', 'display: none;');
        }

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/tenderService/tender/read/all/",
            data: JSON.stringify(projectFO),
            success: function (response) {
                draw(response.entities);
            }
        });

        $('#nextPage').on('click', function () {
            projectFO.skip += 3;

            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/tenderService/tender/read/all/",
                data: JSON.stringify(projectFO),
                success: function (response) {
                    draw(response.entities);
                }
            });
        });


        // ------------------- Start activate --------------------------------------------------
        function changeActiveStatus(id, value) {
            $.ajax({
                url: '/api/rest/offersService/offer/id/' + id + '/setActive/' + value,
                method: 'POST',
                success: function (response) {
                    window.location.href = '/prioffice';
                },
                error: function (response) {
                    window.location.href = '/prioffice';
                }
            });
        }

        $('.deactivateOffer').click(function () {
            var id = $(this).attr("value");
            var value = false;
            changeActiveStatus(id, value);
        });

        $('.activeOffer').click(function () {
            var id = $(this).attr("value");
            var value = true;
            changeActiveStatus(id, value);
        });
        // ------------------- End create default block of tenders --------------------------------


        // ------------------- Start delete offer section --------------------------------------------------
        $('.deleteOffer').click(function () {
            var id = $(this).attr("id");
            $.ajax({
                url: '/api/rest/offersService/offer/id/'+ id +'/delete',
                method: 'POST',
                success: function(response) {
                    window.location.href = '/prioffice';
                },
                error: function (response) {
                    window.location.href = '/prioffice';
                }
            });
        });
        // ------------------- End delete offer section --------------------------------------------------

</script>


</body>
</html>