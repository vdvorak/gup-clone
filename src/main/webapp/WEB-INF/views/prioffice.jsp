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
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/prioffice.css">
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


<div class="container2" ng-app="routerApp">
    <div class="sideBlock">
        <a href="#" class="buttonBack" onclick="window.history.back()">&lt; Назад</a>
        <div class="mainInfo" data-id="${profile.id}">
            <div class="name">${profile.username}</div>
            <div class="photo greenBox">
                <c:choose>
                    <c:when test="${not empty profile.contact.pic}">
                        <img src="/api/rest/fileStorage/PROFILE/file/read/id/${profile.contact.pic}">
                    </c:when>
                    <c:otherwise>
                        <img src="/resources/images/no_photo.jpg">
                    </c:otherwise>
                </c:choose>
                <div class="rating">Рейтинг</div>
                <div class="uploadButton"></div>
            </div>
        </div>
        <div class="greenBox settings">
            <div class="titleMain big">Мои настройки</div>

            <form action="" class="panel-group" id="userSettingsSet" style="display: none;">
                <div class="panel">
                    <a class="" data-toggle="collapse" data-parent="#userSettingsSet" href="#collapseOne">Права</a>
                    <div id="collapseOne" class="collapse in">
                        <div class="panel-body">
                            <div class="radioGroup">
                                <div class="row">
                                    <label for="type-restruct">Администратор</label>
                                    <label class="label-checkbox">
                                        <input type="checkbox" class="greenCheckbox" id="type-restruct" value="restruct" name="type" /><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="type-paper">Боженька</label>
                                    <label class="label-checkbox">
                                        <input type="checkbox" class="greenCheckbox" id="type-paper" value="paper" name="type" /><span></span></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel">
                    <a class="collapsed" data-toggle="collapse" data-parent="#userSettingsSet" href="#collapseTwo">Показать</a>
                    <div id="collapseTwo" class="collapse">
                        <div class="panel-body">
                            <div class="radioGroup" id="gboxTumblers">
                                <div class="row">
                                    <label for="tenders">Мои тендеры</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myTenders" type="checkbox" class="greenCheckbox" id="tenders" value="tenders" name="gboxes" checked="true" /><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="projects">Мои проекты</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myProjects" type="checkbox" class="greenCheckbox" id="projects" value="projects" name="gboxes" checked="true" /><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="offers">Мои обьявления</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myOffers" type="checkbox" class="greenCheckbox" id="offers" value="offers" name="gboxes" checked="true" /><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="balance">Мой баланс</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myBalance" type="checkbox" class="greenCheckbox" id="balance" value="balance" name="gboxes" checked="true" /><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="news">Мои новости</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myNews" type="checkbox" class="greenCheckbox" id="news" value="news" name="gboxes" checked="true" /><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="investments">Мои инвестиции</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myInvestments" type="checkbox" class="greenCheckbox" id="investments" value="investments" name="gboxes" checked="true" /><span></span></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel">
                    <a class="collapsed" data-toggle="collapse" data-parent="#userSettingsSet" href="#collapseThree">Уведомления</a>
                    <div id="collapseThree" class="collapse">
                        <div class="panel-body">
                            Хочу все получать на мыло!
                        </div>
                    </div>
                </div>
                <button type="submit" class="info-submit">Сохранить</button>
            </form>
            <div class="settingsToggle" onclick="toggleSettingsBox(this);"></div>

        </div>
    </div>
    <div class="contentBlock">
        <div class="contactsMain">
            <div class="topBar">
                <div class="searchContactsF">
                    <input class="text" type="text" placeholder="Введите ФИО или ID">
                    <input class="startSearch" type="button" value="Найти">
                </div>
                <div class="count" onclick="contactToggle();">Контакты: ${profile.contactList.size()}</div>
            </div>
            <c:forEach items="${profile.contactList}" var="contact">
                <div class="_contact" data-id="${contact}"></div>
            </c:forEach>
            <div class="contactsContainer greenBox" toggler="" ng-controller="contacts">
                <div class="persona {{vip}}" ng-repeat="contact in contacts" data-id="{{contact.id}}">
                    <a href="{{contact.homepage}}" class="photo border-color">
                        <img src="{{contact.pic}}" alt="profile avatar">
                        <span class="sendMessage"></span>
                        <span class="name">{{contact.name}}</span>
                    </a>
                </div>

                <div class="noFinded" style="display: none;">
                    Не найдено
                </div>

                <div class="arrow toggler"></div>
                <div class="closeBox"></div>
            </div>
        </div>
        <div class="greenBox msAndNt" id="tab-container-msAndNt">
            <ul class="ptabs">
                <li class="ptab border-color active" messagesTab>
                    <%--add "show" class to show counter--%>
                    <div class="count">3</div><a href="#tab-messages">Сообщения</a></li>
                <li class="ptab border-color" notificationsTab>
                    <div class="count">3</div><a href="#tab-notifications">Уведомления</a></li>
            </ul>
            <div class="messages" id="tab-messages">
                <form class="messageForm" data-id="0">
                    <textarea class="text border-color"></textarea>
                    <input type="submit" value="Отправить (Ctrl+Enter)" class="messageSubmit">
                    <div class="clearfix"></div>
                    <div class="arrowHide"></div>
                </form>
                <%--<div class="scrollPreffered"></div>--%>
            </div>
            <div class="notifications" id="tab-notifications">
                <%--<a href="#" class="notify" ng-repeat="notify in notifies">--%>
                    <%--<div class="persona">--%>
                        <%--<img src="/resources/css/images/rupor.png" alt="" class="avatar">--%>
                        <%--<div class="date">25.10.15</div>--%>
                    <%--</div>--%>
                    <%--<div class="text">{{getText(notify.type)}}</div>--%>
                    <%--<div class="clearfix"></div>--%>
                <%--</a>--%>
                <c:if test="${not empty events}">
                    <c:forEach items="${events}" var="event">
                        <a href="#" class="notify" data-uid="${event.uId}" data-contentId="${event.contentId}" data-creatorEventId="${event.creatorEventId}">
                            <div class="persona">
                                <img src="" alt="" class="avatar">
                                <%--<div class="date">${event.createdDate}</div>--%>
                            </div>
                            <div class="text">${event.type.toString()}</div>
                            <div class="clearfix"></div>
                        </a>
                    </c:forEach>
                </c:if>
                <c:if test="${empty events}">
                    <p>у вас нет новых уведомлений</p>
                </c:if>
            </div>
        </div>
        <div class="historyContainer">
            <div class="greenBox historyBox inlineBox optional" id="myTenders" toggler="">
                <div class="historyIcon toggler"></div>
                <div class="titleMain toggler">Мои тендеры</div>
                <div class="historyContent">
                    <c:if test="${not empty tenders}">
                        <c:forEach items="${tenders}" var="tender">
                            <a class="historyItem" href="/tender/${tender.id}">${tender.title}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty tenders}">
                        <p><a href="/tender-make">создать тендер</a></p>
                    </c:if>
                </div>
                <div class="closeBox"></div>
                <div class="arrow toggler"></div>
            </div>
            <div class="greenBox historyBox inlineBox optional" id="myProjects" toggler="">
                <div class="historyIcon toggler"></div>
                <div class="titleMain toggler">Мои проекты</div>
                <div class="historyContent">
                    <c:if test="${not empty projects}">
                        <c:forEach items="${projects}" var="project">
                            <a class="historyItem" href="/project?id=${project.id}">${project.title}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty projects}">
                        <p><a href="/createProject">создать проект</a></p>
                    </c:if>
                </div>
                <div class="closeBox"></div>
                <div class="arrow toggler"></div>
            </div>
            <div class="greenBox historyBox inlineBox optional" id="myNews" toggler="">
                <div class="historyIcon toggler"></div>
                <div class="titleMain toggler">Мои новости</div>
                <div class="historyContent">
                    <c:if test="${not empty blogposts}">
                        <c:forEach items="${blogposts}" var="n">
                            <a class="historyItem" href="/blog/${n.id}">${n.title}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty blogposts}">
                        <p><a href="/blog-create">создать новостной блог</a></p>
                    </c:if>
                </div>
                <div class="closeBox"></div>
                <div class="arrow toggler"></div>
            </div>
            <div class="greenBox historyBox inlineBox optional" id="myOffers" toggler="">
                <div class="historyIcon toggler"></div>
                <div class="titleMain toggler">Мои обьявления</div>
                <div class="historyContent">
                    <c:if test="${not empty offers}">
                        <c:forEach items="${offers}" var="offer">
                            <a class="historyItem" href="/offer/${offer.id}">${offer.title}</a>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty offers}">
                        <p><a href="/blog-create">создать новостной блог</a></p>
                    </c:if>
                </div>
                <div class="closeBox"></div>
                <div class="arrow toggler"></div>
            </div>
            <div class="greenBox historyBox inlineBox optional" id="myInvestments" toggler="">
                <div class="historyIcon toggler"></div>
                <div class="titleMain toggler">Мои инвестиции</div>
                <div class="historyContent">
                </div>
                <div class="closeBox"></div>
                <div class="arrow toggler"></div>
            </div>
            <div class="greenBox historyBox inlineBox optional" id="myBalance" toggler="">
                <div class="historyIcon toggler"></div>
                <div class="titleMain toggler">Мой баланс</div>
                <div class="historyContent">
                    <c:forEach items="${balance}" var="b">
                        <span class="historyItem"><span class="time">${b.dateTime}</span>: пополнено на ${b.amount} грн.</span>
                    </c:forEach>
                </div>
                <div class="closeBox"></div>
                <div class="arrow toggler"></div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

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
<script src="/resources/js/masonry.pkgd.min.js"></script>
<script src="/resources/js/enscroll-0.6.1.min.js"></script>
<script src="/resources/js/angular.min.js"></script>
<!--END of libs-->

<script src="/resources/js/api-generator/api-request.js"></script>
<script src="/resources/js/api-generator/api-generated.js"></script>
<script src="/resources/js/prioffice.js"></script>

<script>
</script>


</body>
</html>