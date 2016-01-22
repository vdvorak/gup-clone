<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: RAYANT
  Date: 14.01.2016
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>${blogPost.title}</title>
    <script src="/resources/libs/jquery-1.11.3.min.js"></script>
    <script src="/resources/js/common/request.js"></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
<br>
<a href="/blog-post/view-all/${blogPost.blogId}"><button>Назад в блог</button></a>

<h1>${blogPost.title}</h1>

<h2> Категория:
    <c:forEach var="cat" items="${blogPost.categories}">
        ${cat},
    </c:forEach>
</h2>

<h2> Страна: ${blogPost.address.country}</h2>

<h2> Область: ${blogPost.address.area}</h2>

<h2> Город: ${blogPost.address.city}</h2>
<br>

<h3>${blogPost.text}</h3>

<c:forEach var="id" items="${blogPost.imagesIds.keySet()}">
    <img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">
</c:forEach>

<br>
<button id="dislikeBtn">Дизлайк</button> ${blogPost.totalDislikes}
<button id="likeBtn">Лайк</button> ${blogPost.totalLikes}
<br>
<a href="/blog-post/edit/${blogPost.id}">редактировать</a>

<div></div>

Комментарии:
<input type="button" value="Сортировать" onclick="sort()">
<div class="comments">
    <c:choose>
        <c:when test="${blogPost.comments.size() > 0}">
            <c:forEach var="comment" items="${blogPost.comments}">
                <div class="comment" id="${comment.cId}" data-replyId="${comment.toId}" data-rating="${comment.totalLikes}">
                    <div class="author" data-id="${comment.fromId}"></div>
                    <div>${comment.comment}</div>
                    <div class="rating">Рейтинг: ${comment.totalLikes}</div>
                    <input type="button" class="reply" value="Ответить" onclick="Reply('${comment.cId}')">
                    <input type="button" class="like" value="Лайк" onclick="Like('${comment.cId}')">
                    <c:choose>
                        <c:when test="${blogPost.authorId} == ${comment.fromId}">
                            <input type="button" class="delete" value="Удалить" onclick="CommentDelete('${comment.cId}')">
                        </c:when>
                    </c:choose>
                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            Комментариев нет. Будьте первым!
        </c:otherwise>
    </c:choose>
</div>
<br>
Написать комментарий:
<div id="commentCreate">
    <textarea id="text" required></textarea>
    <input type="button" id="submit" value="Ок">
</div>

<style>
    .comment {
        color: #7c7c7c;
        margin: 10px 20px 0px 20px;
    }
    .comment:hover {
        color: #000000;
        background-color: #fafafa;
    }
    .comment .author {
        font-weight: bold;
    }
    .comment > .comment{
        margin-left: 30px;
    }
</style>

<script>
    var blogPostId = '${blogPost.id}';

    //----------------------------------------------------- Like and dislike --------------------------------------
    $(document).on('click', '#dislikeBtn', function (e) {

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/" + blogPostId +"/dislike",
            success: function (data, textStatus, request) {
                // Верстальщик - сделай тут красоту по возвращению "success"
            }
        });
    });

    $(document).on('click', '#likeBtn' +
    '', function (e) {
        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/" + blogPostId +"/like",
            success: function (data, textStatus, request) {
                // Верстальщик - сделай тут красоту по возвращению "success"
            }
        });
    });

    //----------------------------------------------------- Like and dislike --------------------------------------

    function sort(){
        var comroot = $('.comments');
        var direction = comroot.attr('direction');
        if (!direction){
            direction = 'date';
        }
        var roots = [];
        $('.comment').each(function(){
            if (!$(this).attr('data-replyId')){
                $(this).detach();
                roots.push($(this));
            }
        });
        var sortFunc;
        if (direction === 'rating'){
            comroot.attr('direction', 'date');
            commentsQueueByDate.forEach(function(q){
                roots.forEach(function(e){
                    if (e.attr('id') === q){
                        comroot.append(e);
                    }
                });
            });
        }
        else {
            comroot.attr('direction', 'rating');
            roots.sort(function(a, b){
                return parseInt(a.attr('data-rating')) < parseInt(b.attr('data-rating'));
            });
            roots.forEach(function(e){
                comroot.append(e);
            });
        }
    }
    var commentsQueueByDate = [];
    $(document).ready(function(){
        $('.comment').each(function(){
            commentsQueueByDate.push($(this).attr('id'));
            if ($(this).attr('data-replyId')){
                $(this).detach().appendTo('.comment#'+$(this).attr('data-replyId'));
            }
            var userHandle = $(this).find('.author');
//            GetUser(userHandle.attr('data-id'), function(res){
//                userHandle.html(res.username);
//            })
        });
    });
    function RefreshPage(){
        location.reload();
    }
    function OnError(msg){
        alert('error: ' + JSON.stringify(msg));
        //alert("Внутренняя ошибка сервера");
    }
    var replyIdAttr = 'replyId';
    function Reply(id){
        $('#commentCreate').attr(replyIdAttr, id);
    }
    var RComment = R.Libra().newsService().blogPost().id("${blogPost.id}").comment();
    function Like(id){
        RComment.id(id).like(null, RefreshPage);
    }
    function CommentDelete(id){
        RComment.id(id).delete(null, RefreshPage);
    }
    $('#submit').click(function () {
        var comment = {};
        var handle = $('#commentCreate');
        comment.comment = handle.find('#text').val();
        comment.toId = handle.attr(replyIdAttr);
        RComment.create(JSON.stringify(comment), RefreshPage);
    });
    function GetUser(id, callback){
        R.Libra().profilesService().profile().read().id(id, null, callback);
    }
</script>

</body>
</html>
