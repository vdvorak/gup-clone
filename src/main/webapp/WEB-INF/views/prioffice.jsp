<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <title>GUP</title>
    <link rel="shortcut icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/libs/chosen/chosen.min.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/alster.css">
    <link rel="stylesheet" href="/resources/css/prioffice.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/cropper.css">
    <link rel="stylesheet" href="/resources/css/gup-custom-modal-window.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>
<body>

<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>


<div class="container2" ng-app="routerApp">
    <div class="sideBlock">
        <%--<a href="#" class="buttonBack" onclick="window.history.back()">&lt; Назад</a>--%>
        <div class="mainInfo" data-id="${profile.id}">
            <div class="name">${profile.username}</div>
            <div class="photo greenBox">
                <c:choose>
                    <c:when test="${not empty profile.imgId}">
                        <img src="/api/rest/fileStorage/PROFILE/file/read/id/${profile.imgId}">
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

            <form action="" class="panel-group" id="userSettingsSet">
                <div class="panel">
                    <a class="" data-toggle="collapse" data-parent="#userSettingsSet" href="#collapseOne">Права</a>

                    <div id="collapseOne" class="collapse in">
                        <div class="panel-body">
                            <%--<div class="radioGroup">--%>
                            <%--<div class="row">--%>
                            <%--<label for="type-restruct">Администратор</label>--%>
                            <%--<label class="label-checkbox">--%>
                            <%--<input type="checkbox" class="greenCheckbox" id="type-restruct" value="restruct" name="type" /><span></span></label>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                            <%--<label for="type-paper">Боженька</label>--%>
                            <%--<label class="label-checkbox">--%>
                            <%--<input type="checkbox" class="greenCheckbox" id="type-paper" value="paper" name="type" /><span></span></label>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            Нет данных
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
                                        <input gbox-id="myTenders" type="checkbox" class="greenCheckbox" id="tenders"
                                               value="TENDER" name="gboxes"/><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="projects">Мои проекты</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myProjects" type="checkbox" class="greenCheckbox" id="projects"
                                               value="PROJECT" name="gboxes"/><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="offers">Мои обьявления</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myOffers" type="checkbox" class="greenCheckbox" id="offers"
                                               value="OFFER" name="gboxes"/><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="balance">Мой баланс</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myBalance" type="checkbox" class="greenCheckbox" id="balance"
                                               value="BALANCE" name="gboxes"/><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="news">Мои новости</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myNews" type="checkbox" class="greenCheckbox" id="news"
                                               value="NEWS" name="gboxes"/><span></span></label>
                                </div>
                                <div class="row">
                                    <label for="investments">Мои инвестиции</label>
                                    <label class="label-checkbox">
                                        <input gbox-id="myInvestments" type="checkbox" class="greenCheckbox"
                                               id="investments" value="INVESTMENT" name="gboxes"/><span></span></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="panel">
                    <a class="collapsed" data-toggle="collapse" data-parent="#userSettingsSet" href="#collapseThree">Уведомления</a>

                    <div id="collapseThree" class="collapse">
                        <div class="panel-body">
                            Нет данных
                        </div>
                    </div>
                </div>
                <button type="button" class="info-submit">Сохранить</button>
            </form>

        </div>
    </div>
    <div class="contentBlock">
        <div class="contactsMain">
            <div class="topBar">
                <div class="searchContactsF">
                    <input class="text" type="text" placeholder="Введите ФИО или ID">
                </div>
                <div class="count" onclick="contactToggle();">Контакты: ${profile.contactList.size()}</div>
            </div>
            <div class="contactsContainer greenBox" toggler="" ng-controller="contacts">
                <c:if test="${not empty profile.contactList}">
                    <c:forEach items="${profile.contactList}" var="contact">
                        <div class="_contact" data-id="${contact}"></div>
                    </c:forEach>
                    <div class="persona {{contact.vip}}" ng-repeat="contact in contacts" data-id="{{contact.id}}">
                        <a href="{{contact.homepage}}" class="photo border-color">
                            <img src="{{contact.pic}}" alt="profile avatar">
                            <span class="sendMessage"></span>
                            <span class="name">{{contact.name}}</span>
                        </a>
                    </div>
                    <div class="arrow toggler"></div>
                </c:if>
                <c:if test="${empty profile.contactList}">
                    <div class="noContent">Пусто</div>
                </c:if>
            </div>
        </div>
        <div class="greenBox msAndNt" id="tab-container-msAndNt">
            <ul class="ptabs">
                <li class="ptab border-color active" messagesTab>
                    <%--add "show" class to show counter--%>
                    <div class="count">3</div>
                    <a href="#tab-messages">Сообщения</a></li>
                <li class="ptab border-color" notificationsTab>
                    <div class="count">3</div>
                    <a href="#tab-notifications">Уведомления</a></li>
            </ul>
            <div class="messages" id="tab-messages">
                <form class="messageForm" data-id="0">
                    <textarea class="text border-color"></textarea>
                    <input type="submit" value="Отправить (Ctrl+Enter)" class="messageSubmit">

                    <div class="clearfix"></div>
                    <div class="arrowHide"></div>
                </form>
                <div class="noContent">
                    Тихо
                </div>
                <%--<div class="scrollPreffered"></div>--%>
            </div>
            <div class="notifications" id="tab-notifications" stype="display: none;">
                <c:if test="${not empty events}">
                    <c:forEach items="${events}" var="event">
                        <a href="#" class="notify" data-targetUId="${event.targetUId}"
                           data-contentId="${event.contentId}" data-makerId="${event.makerId}"
                           data-contentStoreId="${event.contentStoreId}">
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
                    <div class="noContent">
                        Ничего не случилось
                    </div>
                </c:if>
            </div>
        </div>
        <div class="historyContainer">
            <div class="greenBox historyBox inlineBox optional closed" id="myTenders" toggler="" style="display: none;">
                <div class="historyIcon"></div>
                <div class="titleMain">Мои тендеры</div>
                <div class="historyContent">
                    <c:if test="${not empty tenders}">
                        <c:forEach items="${tenders}" var="tender">
                            <a class="historyItem" href="/tender/${tender.id}" target="_blank">${tender.title}</a>
                        </c:forEach>
                        <div class="arrow loader"></div>
                    </c:if>
                    <p><a href="/tender-make" class="proposeToCreate" target="_blank">Обьявить тендер</a></p>
                </div>
                <i class="fa fa-times-circle closeBox"></i>
            </div>
            <div class="greenBox historyBox inlineBox optional closed" id="myProjects" toggler=""
                 style="display: none;">
                <div class="historyIcon"></div>
                <div class="titleMain">Мои проекты</div>
                <div class="historyContent">
                    <c:if test="${not empty projects}">
                        <c:forEach items="${projects}" var="project">
                            <a class="historyItem" href="/project?id=${project.id}" target="_blank">${project.title}</a>
                        </c:forEach>
                        <div class="arrow loader"></div>
                    </c:if>
                    <p><a href="/project/create" class="proposeToCreate" target="_blank">Рассказать о проекте</a></p>
                </div>
                <i class="fa fa-times-circle closeBox"></i>
            </div>
            <%--<div class="greenBox historyBox inlineBox optional closed" id="myNews" toggler="" style="display: none;">--%>
            <%--<div class="historyIcon toggler"></div>--%>
            <%--<div class="titleMain toggler">Мои новости</div>--%>
            <%--<div class="historyContent">--%>
            <%--<c:if test="${not empty blogposts}">--%>
            <%--<c:forEach items="${blogposts}" var="n">--%>
            <%--<a class="historyItem" href="/blog-post/view/id/${n.id}" target="_blank">>${n.title}</a>--%>
            <%--</c:forEach>--%>
            <%--<div class="arrow toggler"></div>--%>
            <%--</c:if>--%>
            <%--<p><a href="/blog-create" class="proposeToCreate" target="_blank">Начать новостной блог</a></p>--%>
            <%--</div>--%>
            <%--<i class="fa fa-times-circle closeBox"></i>--%>
            <%--</div>--%>
            <div class="greenBox historyBox inlineBox optional closed" id="myNews" toggler="" style="display: none;">
                <div class="historyIcon"></div>
                <div class="titleMain">Мои блоги</div>
                <div class="historyContent">
                    <c:if test="${not empty blogs}">
                        <c:forEach items="${blogs}" var="n">
                            <a class="historyItem" href="/blog/${n.id}" target="_blank">${n.title}</a>
                        </c:forEach>
                        <div class="arrow loader"></div>
                    </c:if>
                    <p><a href="/blog-create" class="proposeToCreate" target="_blank">Начать новостной блог</a></p>
                </div>
                <i class="fa fa-times-circle closeBox"></i>
            </div>
            <div class="greenBox historyBox inlineBox optional closed" id="myOffers" toggler="" style="display: none;">
                <div class="historyIcon"></div>
                <div class="titleMain">Мои обьявления</div>
                <div class="historyContent">
                    <c:if test="${not empty offers}">
                        <c:forEach items="${offers}" var="offer">
                            <a class="historyItem" href="/obyavlenie/${offer.seoUrl}" target="_blank">${offer.title}</a>
                        </c:forEach>
                        <div class="arrow loader"></div>
                    </c:if>
                    <p><a href="/create-offer" class="proposeToCreate" target="_blank">Разместить обьявление</a></p>
                </div>
                <i class="fa fa-times-circle closeBox"></i>
            </div>
            <div class="greenBox historyBox inlineBox optional closed" id="myInvestments" toggler=""
                 style="display: none;">
                <div class="historyIcon"></div>
                <div class="titleMain">Мои инвестиции</div>
                <div class="historyContent">
                    <c:if test="${not empty investments}">
                        <c:forEach items="${investments}" var="invest">
                            <a class="historyItem" href="/investorPost/edit?id=${invest.id}"
                               target="_blank">${invest.description}</a>
                        </c:forEach>
                        <div class="arrow loader"></div>
                    </c:if>
                    <p><a href="/investorPost/create" class="proposeToCreate" target="_blank">Инвестировать</a></p>
                </div>
                <i class="fa fa-times-circle closeBox"></i>
            </div>
            <div class="greenBox historyBox inlineBox optional closed" id="myBalance" toggler="" style="display: none;">
                <div class="historyIcon"></div>
                <div class="titleMain">Мой баланс</div>
                <div class="historyContent">
                    <c:forEach items="${balance}" var="b">
                        <span class="historyItem"><span class="time">${b.dateTime}</span>: пополнено на ${b.amount} грн.</span>
                    </c:forEach>
                </div>
                <i class="fa fa-times-circle closeBox"></i>

                <div class="arrow loader"></div>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<form id="uploadProfilePhotoForm">
    <input id="uploadProfilePhotoInput" type="file" name="file" accept="image/*,image/jpeg"
           style="visibility: hidden;width:0px;height:0px;">
</form>

<!-- The Modal -->
<div id="cropperModal" class="cropper-modal">
    <!-- Modal content -->
    <div class="cropper-modal-content">
        <div class="cropper-modal-header">
            <span>Редактирование фотографии</span>
        </div>
        <div class="cropper-modal-body drop_zone">
            <img id="cropper-image" src="/resources/images/no_photo.jpg" style="max-width: 100%">
        </div>
        <div class="cropper-modal-footer">
            <button class="cropper-btn cropper-btn-success">Готово</button>
            <button class="cropper-btn cropper-btn-cancel">Отмена</button>

        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<!-- libs starts here-->
<script src="/resources/js/moment-with-locales.js"></script>
<script src="/resources/js/service.js"></script>
<script src="/resources/js/masonry.pkgd.min.js"></script>
<script src="/resources/js/enscroll-0.6.1.min.js"></script>
<script src="/resources/js/angular.min.js"></script>
<script src="/resources/js/cropper.js"></script>
<!--END of libs-->

<script>
    var profileId = '${profile.id}';
</script>
<script src="/resources/js/api-generator/api-request.js"></script>
<script src="/resources/js/api-generator/api-generated.js"></script>
<script src="/resources/js/prioffice.js"></script>

</body>
</html>