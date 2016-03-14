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
    <title>Главная страница | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

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
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
    your browser</a> to improve your experience.</p>
<![endif]-->

<jsp:include page="/WEB-INF/templates/common-header.jsp"/>

<jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

<jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

<jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<div class="container2">

    <div class="news"> <!-- когда чувак вип надо добавить класс vip-color-border -->
        <p class="newsVisits" id="bpViewsNum">Просмотров: </p>

        <p class="newsPublished" id="bpCreatedDate">Опубликовано: </p>

        <p class="newsName" id="bpTitle"></p> <!-- когда чувак вип надо добавить класс vip-color -->
        <div id="bpText" class="descriptionNormalNews2">
            <%--<img class="newsIMG1" src="/resources/images/newsImages.png" alt="images">--%>
            <%--<p class="newsText">Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейского.--%>
            <%--Уложили его аккуратно между ямами, чтоб водитель уж точно никуда не делся.--%>
            <%--Жители двора новшевство комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?". Перед выборами забота о гражданах максимально зашкаливает. Чинятся подъезды, ремонтируются дороги, ну в общем кому как повезет. Жителям одного из дворов на Березняках повезло не особо: не хватило асфальта. Но хоть что-то ради электората сделать надо. И коммунальщики оторвали от сердца дорогое: лежачего полицейского.--%>
            <%--Уложили его аккуратно между ямами, чтоб водитель уж точно никуда не делся.--%>
            <%--Жители двора новшевство комментируют ярко, но по понятным причинам привести цитаты мы не можем, в основном они сводятся к экспрессивным формам вопроса "Зачем?".</p>--%>
            <%--<img class="newsIMG2" src="/resources/images/newsIMG2.png" alt="images">--%>
        </div>

        <div class="clearfix"></div>

        <div class="newsRating">
            <a id="newsLike" class="newsLike"></a>

            <p class="newsLikeNum" id="bpLikeNum"></p>
            <a id="newsDislike" class="newsDislike"></a>

            <p class="newsDislikeNum" id="bpDislikeNum"></p>
        </div>



        <div class="downComments"><p>Комментировать</p></div>

        <div class="clearfix"></div>

        <div class="colNewsComments">
            <div class="newsComments">
                <div class="clearfix"></div>
                <p class="newsCommentsHeader">Комментарий</p>

                <form id="newsCommentsForm">
                    <textarea name="newsFormComments" id="newsFormComments" placeholder="Введите свой комментарий"
                              maxlength="1000" required></textarea>
                    <button id="sendNewsComment" class="newsFormSubmit">Отправить</button>
                </form>
                <p id="chars"></p>
            </div>
            <div class="replyToComment">
                <a href="#"><img src="/resources/images/logoComment.png" alt="logo"></a>
                <a class="NameUser" href="#">Вася Петров</a>
                <p class="commentUser">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Magnam repellat earum aliquid explicabo nesciunt! Quidem sit facilis sunt odit quis repellendus quasi consectetur maiores, blanditiis omnis hic labore, veritatis atque?</p>
            </div>
        </div>
        <div class="colComments" id="commentsBlock">

        </div>
    </div>
</div>

<sec:authorize access="isAuthenticated()">
    <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
</sec:authorize>

<jsp:include page="/WEB-INF/templates/footer.jsp"/>

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>
<script src="/resources/js/main.js"></script>
<script src="/resources/js/logo-section.js"></script>
<script src="/resources/js/search-bar.js"></script>

<script>
    var blogPostId = "${blogPostId}";
    var loadedBlogPost = {};

    $.ajax({
        type: "POST",
        url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/read",
        statusCode: {
            200: function (blogPost) {
                loadedBlogPost = blogPost;
//                        alert(JSON.stringify(blogPost));
                $('#bpViewsNum').append(blogPost.views);
                var createdDate = new Date(blogPost.createdDate);
                $('#bpCreatedDate').append(createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear());
                $('#bpTitle').append(blogPost.title);
                $('#bpLikeNum').append(blogPost.totalLikes);
                $('#bpDislikeNum').append(blogPost.totalDislikes);
                $('#bpText').append(blogPost.text);

                if(loggedInProfile){
                    if (loggedInProfile.id === blogPost.authorId){
                        $("<a href='/blog-post/edit/" + blogPost.id +"'><button>Редактировать статью</button></a>").insertAfter($('.newsRating'))
                    }
                }

                blogPost.comments.forEach(function (comment) {
                    $.ajax({
                        type: "POST",
                        url: "/api/rest/profilesService/profile/read/id/" + comment.fromId,
                        statusCode: {
                            200: function (profile) {
                                var profileImgTag = '<img ';
                                if (profile.imgId) {
                                    profileImgTag += 'src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId + '?cachedImage=1"';
                                } else {
                                    profileImgTag += 'src="/resources/images/no_photo.jpg"';
                                }
                                profileImgTag += ' width="52px" height="52px" alt="logo">';

                                $('#commentsBlock').append(
                                        '<div class="comments">' +
                                        '<a href="/profile/id/' + profile.id + '">' + profileImgTag + '</a>' +
                                        '<a class="NameUser" href="/profile/id/' + profile.id + '">' + profile.username + '</a>' +
                                        '<p class="commentUser">' + comment.comment + '</p>' +
                                        '</div>');
                            }
                        }
                    });
                });
            }
        }
    });

    $('#newsLike').on('click', function () {
        if (typeof loggedInProfile != 'undefined') {
            if ($.inArray(loggedInProfile.id, loadedBlogPost.likedIds) == -1) {
                if ($.inArray(loggedInProfile.id, loadedBlogPost.dislikedIds) != -1 && loadedBlogPost.totalDislikes > 0) {
                    $('#bpDislikeNum').text(--loadedBlogPost.totalDislikes);
                    loadedBlogPost.dislikedIds = $.grep(loadedBlogPost.dislikedIds, function (value) {
                        return value != loggedInProfile.id;
                    });
                }

                $.ajax({
                    type: "POST",
                    url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/like",
                    statusCode: {
                        200: function () {
                            $('#bpLikeNum').text(++loadedBlogPost.totalLikes);
                            loadedBlogPost.likedIds.push(loggedInProfile.id);
                        },
                        409: function () {
                        }
                    }
                });
            }
        } else {
            alert("Чтобы проголосовать сначала нужно авторизироваться.")
        }
    });

    $('#newsDislike').on('click', function () {
        if (typeof loggedInProfile != 'undefined') {
            if ($.inArray(loggedInProfile.id, loadedBlogPost.dislikedIds) == -1) {
                if ($.inArray(loggedInProfile.id, loadedBlogPost.likedIds) != 1 && loadedBlogPost.totalLikes > 0) {
                    $('#bpLikeNum').text(--loadedBlogPost.totalLikes);
                    loadedBlogPost.likedIds = $.grep(loadedBlogPost.likedIds, function (value) {
                        return value != loggedInProfile.id;
                    });
                }

                $.ajax({
                    type: "POST",
                    url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/dislike",
                    statusCode: {
                        200: function () {
                            $('#bpDislikeNum').text(++loadedBlogPost.totalDislikes);
                            loadedBlogPost.dislikedIds.push(loggedInProfile.id);
                        },
                        409: function () {
                        }
                    }
                });
            }
        } else {
            alert("Чтобы проголосовать сначала нужно авторизироваться.")
        }
    });

    $('#sendNewsComment').on('click', function () {
        var comment = {
            'comment': $('#newsFormComments').val(),
            'toId': ""
        };

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/comment/create",
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

    $(".NewsTabsFilterItem").on('click', function () {
        $('.intro').removeClass("intro");
        $(this).addClass("intro");
    });

    $('#newsFormComments').keyup(function () {
        var maxLength = 1000;
        var length = maxLength - $(this).val().length;
        $('#chars').text(length + ' символов осталось');
    });

    $(".downComments").click(function () {
        if (typeof loggedInProfile != 'undefined') {
            $(".downComments").hide('slow');
            $(".colNewsComments").show('slow');
            $(".colComments").css("width", "50%");
        } else {
            alert("Чтобы оставить комментарий сначала нужно авторизироваться.")
        }
    });

    $(".comments").click(function () {
        if ($('.backgroundColorComment').is(':visible')) {
            return $('.backgroundColorComment').removeClass("backgroundColorComment");
        } else {
            $(this).addClass("backgroundColorComment");
        }
    });
</script>
</body>
</html>
