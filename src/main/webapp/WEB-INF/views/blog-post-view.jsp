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
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">
</head>
<body>
<sec:authorize access="isAuthenticated()" var="isAuthenticated">
    <jsp:include page="/WEB-INF/templates/authorizedHeader.jsp"/>
</sec:authorize>

<c:if test="${!isAuthenticated}">
    <jsp:include page="/WEB-INF/templates/headerAnonym.jsp"/>
</c:if>

<h1>${blogPost.title}</h1>

<h2> Категория: ${blogPost.text}</h2>

<h2> Страна: ${blogPost.address.country}</h2>

<h2> Область: ${blogPost.address.area}</h2>

<h2> Город: ${blogPost.address.city}</h2>
<br>

<h3>${blogPost.text}</h3>

<c:forEach var="id" items="${blogPost.imagesIds.keySet()}">
    <img src="/api/rest/fileStorage/NEWS/file/read/id/${id}" width="200px" height="200px">
</c:forEach>

<br>
<a href="/blog-post/edit/${blogPost.id}">редактировать</a>

<div></div>

Комментарии:
<div class="comments">
    <c:choose>
        <c:when test="${blogPost.comments.size() > 0}">
            <c:forEach var="comment" items="${blogPost.comments}">
                <div class="comment" data-id="${comment.cId}">
                    <p class="author">Автор: ${comment.fromId}</p>

                    <div>${comment.comment}</div>
                    <div class="rating">Лайки: ${comment.totalLikes}</div>
                    <div class="date">Дата написания: ${comment.createdDate}</div>
                    <input type="button" class="reply" value="Ответить" onclick="Reply('${comment.cId}')">
                    <input type="button" class="like" value="Лайк" onclick="Like('${comment.cId}')">
                    <input type="button" class="delete" value="Удалить" onclick="CommentDelete('${comment.cId}')">
                </div>
                <br>
            </c:forEach>
        </c:when>
        <c:otherwise>
            нету
        </c:otherwise>
    </c:choose>
</div>

Написать комментарий:
<div id="commentCreate">
    <textarea id="text" required></textarea>
    <input type="button" id="submit" value="Сохранить">
</div>

<style>
    .comment {
        border: 2px solid gray;;
    }
</style>

<script>
    function RefreshPage(){
        window.location.href = '/blog-post/view/${blogPost.id}';
    }
    function OnError(msg){
        alert(JSON.stringify(msg));
        //alert("Внутренняя ошибка сервера");

    }
    var replyIdAttr = 'replyId';
    function Reply(id){
        $('#commentCreate').attr(replyIdAttr, id);
    }
    function Like(id){
        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/${blogPost.id}/comment/id/" + id + "/like",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            complete: function(e, xhr, settings){
                if (e.status === 200) {
                    RefreshPage();
                }
                else{
                    OnError(response);
                }
            }
        });
    }
    function CommentDelete(id){
        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/${blogPost.id}/comment/id/" + id + "/delete",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                RefreshPage();
            },
            error: function (response) {
                OnError(response);
            }
        });
    }
    $('#submit').click(function () {

        var comment = {};
        var handle = $('#commentCreate');
        comment.comment = handle.find('#text').val();
        comment.toId = handle.attr(replyIdAttr);

        alert(JSON.stringify(comment));

        $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/id/${blogPost.id}/comment/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(comment),
            success: function (response) {
                RefreshPage();
            },
            error: function (response) {
                OnError(response);
            }
        });
    });
</script>

</body>
</html>
