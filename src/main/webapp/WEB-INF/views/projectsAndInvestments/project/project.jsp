<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

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

    <!--PAGE CONTENT START-->

    <div class="container2">
        <!-- Add "vip" class for "vip" users :3 -->
        <div class="contentContainer">
            <%--<ul class="projectsVSInvestments-block">--%>
                <%--<li class="projectsVSInvestments-btn projects" data-atab="projects"><a href="#tabs1-project">ПРОЕКТЫ</a></li>--%>
                <%--<!--Add "active" class to show this element as selected-->--%>
                <%--<li class="projectsVSInvestments-btn investments" data-atab="investments"><a href="#tabs1-investment">ИНВЕСТИЦИИ</a></li>--%>
            <%--</ul>--%>

            <%--<div class="projectsVSInvestmentsCats">--%>
                <%--<div class="catContainer selected">--%>
                    <%--<!--Add "selected" class to show this element as selected-->--%>
                    <%--<div class="catLogo restruct"></div>--%>
                    <%--<div class="catName">Реструктуризация</div>--%>
                <%--</div>--%>
                <%--<div class="catContainer">--%>
                    <%--<div class="catLogo prototype"></div>--%>
                    <%--<div class="catName">Готовый прототип</div>--%>
                <%--</div>--%>
                <%--<div class="catContainer">--%>
                    <%--<div class="catLogo paper"></div>--%>
                    <%--<div class="catName">Проект на бумаге</div>--%>
                <%--</div>--%>
                <%--<div class="catContainer">--%>
                    <%--<div class="catLogo nouHau"></div>--%>
                    <%--<div class="catName">Ноу-Хау</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="projectContent">
                <div class="topSection">
                    <div class="statInfo">
                        <div class="publish" id="projCreatedDate">Опубликовано: </div>
                        <div class="views" id="projViewsNum">Просмотров: </div>
                    </div>
                </div>
                <div class="contentHeader">
                    <div class="leftSec">
                        <div class="author" id="authorName"></div>
                        <img class="mainPhoto" id="projCreatorPhoto" src="" alt="mainPhoto">
                    </div>
                    <div class="rightSec">
                        <div class="name" id="projName"></div>
                        <div class="additionalPhotos" id="projImages">
                            <%--<img class="photo full" src="/resources/css/images/sample/projectView0.png" alt="">--%>
                            <%--<img class="photo" src="/resources/css/images/sample/projectView1.png" alt="">--%>
                            <%--<img class="photo" src="/resources/css/images/sample/projectView2.png" alt="">--%>
                            <%--<img class="photo" src="/resources/css/images/sample/projectView3.png" alt="">--%>
                        </div>
                    </div>
                </div>
                <div id="investBlock" class="bottomSection">
                    <button type="button" class="abutton blue invest">Инвестировать</button>
                    <div class="projectProgressBlock">
                        <div class="current elem cash" id="investedAmount"></div>
                        <div class="bar elem">
                            <div class="colored"></div>
                            <div class="empty" id="projProgress"></div>
                            <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
                        </div>
                        <div class="todo elem cash" id ="requestedAmount"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="description">
                    <div class="title">Описание</div>
                    <div class="text" id="projText">
                        <%--<p>--%>
                            <%--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed massa lobortis, tristique mauris pulvinar, bibendum risus. Cras consectetur convallis mauris vel aliquam. Maecenas molestie, lacus in aliquam commodo, risus ex consectetur libero, vitae euismod nisi nulla commodo libero. In fringilla nibh et eros interdum pharetra. Nulla condimentum nulla eget pulvinar posuere. Etiam aliquet fermentum lectus, sed feugiat nibh mattis quis. Curabitur justo urna, laoreet sed pellentesque vitae, volutpat sed est. Etiam sollicitudin tincidunt tortor vitae euismod. Vestibulum ut accumsan ante, ac mollis felis. Pellentesque id metus consectetur, maximus ex at, luctus sapien. Sed non nibh id urna cursus ultrices blandit id arcu. Nunc facilisis eu lacus a consequat. Nunc convallis nec lacus id convallis. Morbi aliquet commodo nisi, sit amet pellentesque mi imperdiet non. Morbi a arcu nisi.--%>
                        <%--</p>--%>
                        <%--<p>--%>
                            <%--Vivamus pulvinar faucibus eros, et ultricies est ultrices eu. Praesent commodo odio id urna bibendum, eu vestibulum augue sodales. Nullam eget ornare arcu. Aenean eget tincidunt lectus. Nam finibus ex nec quam porttitor viverra. Quisque sed euismod quam, ut tempor justo. Donec finibus, felis et cursus commodo, ligula tellus tincidunt ex, a ullamcorper enim risus maximus arcu. Aliquam euismod mollis sollicitudin.--%>
                        <%--</p>--%>
                        <%--<p>--%>
                            <%--Sed vel luctus nisi. Fusce laoreet vitae lorem eget gravida. Morbi et consectetur nisi. In ac purus non dui fringilla rutrum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Etiam porta eu libero id iaculis. Suspendisse velit orci, euismod nec leo non, auctor laoreet eros. Maecenas a dolor magna. Duis efficitur elit in vulputate mattis. Donec turpis odio, bibendum ut consectetur a, ultrices non lectus. Maecenas vitae lacus at sem vulputate pulvinar.--%>
                        <%--</p>--%>
                        <%--<p>--%>
                            <%--Proin vitae erat condimentum, commodo lacus in, euismod nulla. Aenean vehicula risus eu lacus elementum dignissim. Praesent dapibus diam non lacinia efficitur. Donec vel neque quis orci mattis auctor sit amet quis tellus. Phasellus auctor viverra convallis. Cras ac elementum velit, quis imperdiet tortor. Suspendisse metus magna, congue in ipsum sit amet, varius efficitur orci. Vivamus urna tellus, vulputate a faucibus sit amet, accumsan at velit. Sed auctor sem quis est imperdiet dignissim. Nam a tortor sit amet quam finibus tincidunt eu sit amet nunc. Phasellus ultricies hendrerit rhoncus.--%>
                        <%--</p>--%>
                        <%--<p>--%>
                            <%--Morbi pellentesque mauris at porttitor malesuada. Nulla facilisi. Nulla feugiat semper ipsum et rhoncus. Cras rhoncus arcu at massa suscipit lobortis. Sed convallis, ex eu auctor laoreet, odio neque hendrerit nisl, at porta odio turpis a magna. Etiam in eleifend neque. Aliquam fringilla ut neque sed tristique. Vestibulum porttitor urna eget nulla placerat gravida. Aliquam erat volutpat. Quisque nec nibh metus. Mauris vitae felis vitae nisl facilisis pharetra. Phasellus quis enim pulvinar, iaculis lectus eu, rhoncus ex. Cras sed ipsum vel mi euismod condimentum.--%>
                    </div>
                </div>
                <div class="commentsSection">
                    <div class="count" id="commentsNum">Комментарии: </div>
                </div>
                <div class="clearfix"></div>
                <div class="downComments"><p>КОММЕНТАРИИ</p></div>

                <div class="colNewsComments">
                    <div class="newsComments">
                        <div class="clearfix"></div>
                        <p class="newsCommentsHeader">КОММЕНТАРИИ</p>
                        <form action="#" role="form" id="newsCommentsForm">
                            <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий" maxlength="2000" required></textarea>
                            <button type="submit" class="newsFormSubmit">Отправить</button>
                        </form>
                        <p id="chars">2000 символов осталось</p>
                    </div>
                </div>
                <div class="colComments">
                    <div class="comments">
                        <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                        <a class="NameUser" href="#">Вася Петров</a>
                        <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Qui quisquam, voluptate at magni neque. Ab illum hic asperiores voluptate voluptatem. Optio alias, numquam sint delectus quod recusandae dolores tempora. Aliquam!</p>
                    </div>
                    <div class="comments">
                        <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                        <a class="NameUser" href="#">Вася Петров</a>
                        <p class="commentUser">Интересно было узнать, история повторяется циклично!</p>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div style="height: 15px;"></div>
            </div>
        </div>
    </div>

    <!--PAGE CONTENT END-->

    <sec:authorize access="isAuthenticated()">
        <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
    </sec:authorize>

    <jsp:include page="/WEB-INF/templates/footer.jsp"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
    <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
    <script src="/resources/js/vendor/bootstrap.js"></script>
    <script src="/resources/js/jquery.bxslider.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
    <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

    <sec:authorize var="loggedIn" access="isAuthenticated()"/>
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

    <script>

        $("#selectedService option[value='project']").attr("selected","selected");

        $(document).ready(function(){
            var selector = '.projectContent .contentHeader .additionalPhotos .photo';
            $(selector).on('click', function(){
                $(selector + '.full').attr('src', $(this).attr('src'))
            })
        });

        var projectId = getUrlParam('id');

        loadAndAppendProject(projectId);

        function loadAndAppendProject(projectId) {
            $.ajax({
                type: "GET",
                url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/read",
                statusCode: {
                    200: function (project) {
                        appendProjectBlock(project);
                    }
                }
            });
        }

        function appendProjectBlock(project) {
            $('#projCreatedDate').append(getReadableCreatedDate(project.createdDate));
            $('#projViewsNum').append(project.views);
            $('#projName').append(project.title);

            for (var imgId in project.imagesIds) {
                appendProjectImage(imgId, project.imagesIds[imgId]);
            }
            $('#investedAmount').append(project.investedAmount + ' ₴ ');
            $('#requestedAmount').append(project.amountRequested + ' ₴ ');
            $('#projProgress').css('width', getInvertedProgressNum(project.investedAmount, project.amountRequested) + '%');
            $('#projText').append(project.description);
            $('#commentsNum').append(project.totalComments);
            setAuthorContent(project.authorId);

        }

        function setAuthorContent(profileId) {
            $.ajax({
                type: "POST",
                url: "/api/rest/profilesService/profile/read/id/" + profileId,
                statusCode: {
                    200: function (profile) {
                        if (profile.contact.pic != null && profile.contact.pic != '') {
                            $('#projCreatorPhoto').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic);
                        } else {
                            $('#projCreatorPhoto').attr('src', '/resources/images/no_photo.jpg');
                        }

                        if (profile.username != null) {
                            $('#authorName').text(profile.username);
                        }
                    }
                }
            });
        }

        function appendProjectImage(imgId, imgKey) {
            var imgTag = '<img class="photo ';
            imgTag += (imgKey === "1") ? 'full" ' : '" ';
            imgTag += 'src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + imgId + '" >';

            $('#projImages').append(imgTag);
        }

        function getReadableCreatedDate(timestamp) {
            var createdDate = new Date(timestamp);
            return createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear();
        }

        function getInvertedProgressNum(investedAmount, amountRequested) {
            var invertedProgressNum = (1 -(investedAmount/amountRequested))*100;
            return 5 * Math.ceil(invertedProgressNum/5);
        }
//        if (getUrlParam('projectId') == null) {
//            alert('null');
//        }
//            alert(getUrlParam('projectId'));

        <%--var projectId = '';--%>
        <%--var comment = {};--%>

        <%--$(document).ready(function () {--%>
            <%--$.ajax({--%>
                <%--type: "GET",--%>
                <%--url: "/api/rest/projectsAndInvestmentsService/project/id/${projectId}/read",--%>
                <%--success: function (projectData) {--%>
                    <%--projectId = projectData.id;--%>
                    <%--if (projectData.imagesIds !== null && projectData.imagesIds != '') {--%>
                        <%--for (var key in projectData.imagesIds) {--%>
                            <%--if (projectData.imagesIds[key] === "pic1") {--%>
                                <%--$('#projectImg').attr('src','/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key);--%>
                                <%--break;--%>
                            <%--}--%>
                        <%--}--%>
                    <%--} else {--%>
                        <%--$('#projectImg').attr('src','/resources/images/no_photo.jpg');--%>
                    <%--}--%>
                    <%--$('#title').text(projectData.title);--%>
                    <%--$('#projectType').text(projectData.type);--%>
                    <%--$('#description').html(projectData.description);--%>
                    <%--$('#amountRequested').text(projectData.amountRequested);--%>
                    <%--$('#investedAmount').text(projectData.investedAmount);--%>
                    <%--$('#totalScore').text(projectData.totalScore);--%>

                    <%--var createdDate = new Date(projectData.createdDate);--%>
                    <%--$('#projectCreatedDate').text(createdDate.getDate() + '/'--%>
                            <%--+ (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear());--%>

                    <%--if (projectData.comments == null || projectData.comments.length == 0) {--%>
                        <%--$('#commentsLabel').append('Еще нет комментариев');--%>
                        <%--$('#commentsTable').hide();--%>
                    <%--} else {--%>
                        <%--for (var i = 0; i < projectData.comments.length; i++) {--%>
                            <%--var createdDate = new Date(projectData.comments[i].createdDate);--%>
                            <%--projectData.comments[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();--%>

                            <%--var row = $('<tr>');--%>
                            <%--row.append($('<td>').html(projectData.comments[i].comment));--%>
                            <%--row.append($('<td>').html(projectData.comments[i].fromId));--%>
                            <%--row.append($('<td>').html(projectData.comments[i].createdDate));--%>

                            <%--$('#commentsTable').append(row);--%>
                        <%--}--%>
                    <%--}--%>
                <%--},--%>
                <%--statusCode: {--%>
                    <%--404: function() {--%>
                        <%--alert('Такого проекта нет');--%>
                        <%--window.location.href = "/project/list";--%>
                    <%--}--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>

        <%--$(document).on('click', '#voteButton', function (event) {--%>
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/vote/" + $('#projectScore').val(),--%>
                <%--success: function () {--%>
                    <%--alert('Вы проголосовали за проект');--%>
                    <%--window.location.reload();--%>
                <%--},--%>
                <%--error: function (response) {--%>
                    <%--alert("Внутренняя ошибка сервера");--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>

        <%--$(document).on('click', '#commentButton', function (event) {--%>

            <%--comment.toId = projectId;--%>
            <%--comment.comment = $('#comment').val();--%>
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/comment/create",--%>
                <%--contentType: "application/json; charset=utf-8",--%>
                <%--dataType: "json",--%>
                <%--data: JSON.stringify(comment),--%>
                <%--success: function () {--%>
                    <%--alert('Вы прокомментировали проект');--%>
                    <%--window.location.reload();--%>
                <%--},--%>
                <%--statusCode: {--%>
                    <%--409: function() {--%>
                        <%--alert('Сначала нужно проголосовать');--%>
                    <%--}--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>

    </script>
</body>
</html>

