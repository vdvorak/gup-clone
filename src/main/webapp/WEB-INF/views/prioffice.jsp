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
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/bxslider/jquery.bxslider.css">
    <link rel="stylesheet" type="text/css" href="/resources/libs/magnific-popup.css">

</head>
<body>

<!-- BEGIN Common general header-->
<jsp:include page="/WEB-INF/templates/common-header.jsp"/>
<!-- END common general header-->

<!--BEGIN 1nd section with logo, apps button and organization button-->
<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>
<!-- END 1st section -->

<!--BEGIN section with search bar-->
<jsp:include page="/WEB-INF/templates/main-search-bar.jsp"/>
<!-- END search bar -->

<!--2nd section menu+slider -->
<jsp:include page="/WEB-INF/templates/main-menu-slider-bar.jsp"/>
<!--END 2nd section -->


<section>
    <div class="prioffice-wrap">
        <!--LEFT_SIDE-->
        <!-- ЧЕРТИ ЧТО ДОПИСАТЬ И ДОПОЛНИТЬ ИНФОРМАЦИЕЙ-->
        <div class="prioffice-left">
            <div class="prioffice-user">петров василий</div>
            <div class="prioffice-userpic">
                <img src="/resources/img/defaultlogo.png">
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
                                <div class="prioffice-tabs-items-pic">
                                    <img src="/resources/img/unknownuser-pic.png">
                                </div>
                                <p class="prioffice-tabs-items-text">Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>

                                <p class="prioffice-tabs-items-status">Непрочитано</p>
                            </div>
                            <div class="prioffice-tabs-items">
                                <div class="prioffice-tabs-items-pic">
                                    <img src="/resources/img/unknownuser-pic.png">
                                </div>
                                <p class="prioffice-tabs-items-text">Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>

                                <p class="prioffice-tabs-items-status">Непрочитано</p>
                            </div>
                            <div class="prioffice-tabs-items">
                                <div class="prioffice-tabs-items-pic">
                                    <img src="/resources/img/tendersmall-icon.png">
                                </div>
                                <p class="prioffice-tabs-items-text">Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение
                                    Сообщение Сообщение Сообщение Сообщение Сообщение Сообщение</p>

                                <p class="prioffice-tabs-items-status">Непрочитано</p>
                            </div>
                            <div class="prioffice-tabs-items-return">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                        <div>Второе содержимое</div>
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
                                <p>Заголовок тендера 1</p>

                                <p>Заголовок тендера 1</p>

                                <p>Заголовок тендера 1</p>
                            </div>

                            <div class="myitems-tenders-footer">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
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
                                <p>Заголовок проекта 1</p>

                                <p>Заголовок проекта 1</p>

                                <p>Заголовок проекта 1</p>
                            </div>
                            <div class="myitems-projects-footer">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
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
                                <p>Новость 1</p>

                                <p>Новость 2</p>

                                <p>Новость 3</p>
                            </div>
                            <div class="myitems-news-footer">
                                <a href=""><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>
                    </div>
                    <div class="myitems-founds">
                        <div class="myitems-founds-wrap">
                            <div class="myitems-tenders-header">
                                <img class="myitems-founds-wrap-icon" src="/resources/img/ballancesmall-icon.png">

                                <div class="myitems-item-wrap-title">
                                    <p class="myitems-item-wrap-titletext"> Мой баланс</p>
                                </div>
                                <img class="prioffice-close-founds1-ico" src="/resources/img/closesmall-icon.png">
                            </div>
                            <div class="myitems-founds-items">
                                <p>Новость 1</p>

                                <p>Новость 2</p>

                                <p>Новость 3</p>
                            </div>
                            <div class="myitems-founds-footer">
                                <a href="#"><img src="/resources/img/strippeddownbuttonsmall.png"></a>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>

    </div>
</section>
<!-- hiden stuff-->

<!--END hiden stuff-->
<!-- libs starts here-->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script src="/resources/js/prioffice.js"></script>
<script src="/resources/libs/bxslider/jquery.bxslider.min.js"></script>
<script src="/resources/libs/jquery.magnific-popup.min.js"></script>
<script src="/resources/js/common.js"></script>


<!--END of libs-->

</body>
</html>