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
                        <div class="additionalPhotos" id="projImages"></div>
                    </div>
                </div>
                <div id="investBlock" class="bottomSection">
                    <button type="button" class="abutton blue invest">Инвестировать</button>
                    <div class="projectProgressBlock">
                        <div class="current elem cash" id="investedAmount"></div>
                        <div class="bar elem">
                            <div class="colored"></div>
                            <div class="empty" id="projProgress" style="width: 100%;"></div>
                            <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
                        </div>
                        <div class="todo elem cash" id ="requestedAmount"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="description">
                    <div class="title">Описание</div>
                    <div class="text" id="projText"></div>
                </div>
                <div class="commentsSection">
                    <div class="count" id="commentsNum">Комментарии: </div>
                </div>
                <div class="clearfix"></div>

                <div class="downComments"><p>Комментировать</p></div>

                <div class="clearfix"></div>

                <div class="colNewsComments">
                    <div class="newsComments">
                        <div class="clearfix"></div>
                        <p class="newsCommentsHeader">Комментарий</p>
                        <form id="projectsCommentsForm" class="projectsCommentsForm">
                            <textarea name="projectsFormComments" id="projectsFormComments" class="projectsFormComments" placeholder="Введите свой комментарий" maxlength="1000" required></textarea>
                            <button id="sendProjComment" class="newsFormSubmit">Отправить</button>
                        </form>
                        <p id="chars"></p>
                    </div>
                </div>
                <div class="colComments" id="commentsBlock"></div>
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
            $('#projText').append(project.description);
            $('#projProgress').css('width', getInvertedProgressNum(project.investedAmount, project.amountRequested) + '%');
            $('#investedAmount').append(project.investedAmount + ' ₴ ');
            $('#requestedAmount').append(project.amountRequested + ' ₴ ');
            $('#commentsNum').append(project.totalComments);

            for (var imgId in project.imagesIds) {
                appendProjectImage(imgId, project.imagesIds[imgId]);
            }
            setAuthorContent(project.authorId);
            setProjectCommentsBlock(project.comments);
        }

        function setProjectCommentsBlock(projectComments) {
            projectComments.forEach(function(comment) {
                $.ajax({
                    type: "POST",
                    url: "/api/rest/profilesService/profile/read/id/" + comment.fromId,
                    statusCode: {
                        200: function (profile) {
                            var profileImgTag = '<img ';
                            if (profile.contact.pic != null && profile.contact.pic != '') {
                                profileImgTag += 'src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '?cachedImage=1"';
                            } else {
                                profileImgTag += 'src="/resources/images/no_photo.jpg"';
                            }
                            profileImgTag += ' width="52px" height="52px" alt="logo">';

                            $('#commentsBlock').append(
                                    '<div class="comments">' +
                                        '<a href="/profile/id/' + profile.id + '">' + profileImgTag + '</a>' +
                                        '<a class="NameUser" href="/profile/id/' + profile.id + '">' + profile.username + '</a>' +
                                        '<p class="commentUser">' +  comment.comment + '</p>' +
                                    '</div>');
                        }
                    }
                });
            });
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

        $('#sendProjComment').on('click', function () {
            var comment = {
                'comment' : $('#projectsFormComments').val(),
                'toId' : ""
            };

            $.ajax({
                type: "POST",
                url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/comment/create",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(comment),
                statusCode: {
                    201: function () {
                        location.reload();
                    }
                }
            });
        });

        $('#projectsFormComments').keyup(function() {
            var maxLength = 1000;
            var length = maxLength - $(this).val().length;
            $('#chars').text(length + ' символов осталось');
        });

        $(".downComments").click(function(){
            if (typeof loggedInProfile != 'undefined') {
                $(".downComments").hide('slow');
                $(".colNewsComments").show('slow');
                $(".colComments").css("width", "50%");
            } else {
                alert("Чтобы оставить комментарий сначала нужно авторизироваться.")
            }
        });

        $(".comments").click(function(){
            if ($('.backgroundColorComment').is(':visible') ) {
                return $('.backgroundColorComment').removeClass("backgroundColorComment");;
            } else {
                $(this).addClass("backgroundColorComment");
            }
        });
    </script>
</body>
</html>

