<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fairy
  Date: 01.12.2015
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Страница диалога</title>
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%--<link href="../pages/css/bootstrap.css" rel="stylesheet">--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body class="center-block" style="padding-top: 70px; max-width: 1200px;">

<!--Header-->
<jsp:include page="/WEB-INF/templates/header.jsp"/>
<!--Header-->

    <h2 id="title"  style="color: #ff8e35;"></h2>
    <div id="last"></div>
    <br>
    <div id="members"></div>
    <div id="size"></div>
    <div class="panel panel-default" style="background-color: transparent; border-color: transparent;">
        <div class="panel-body">
            <table id="dialogues" border="5"></table>
        </div>
    </div>
    <div id="photo"></div>

    <div>
        <textarea id="newMsg" minlength="5" maxlength="5000" required
                  placeholder="Введите текст сообщения"></textarea>
    </div>
<!--/container-fluid-->
<button id="addMsg">Отправить сообщение</button>

<!-- script references -->
<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

<script>

    var msg = {};
    var dialogue = {};
    $(document).ready(function(){
        getNewPosts();
    });
    ///------------------------- Add msg -----------------------------------------------
    $(document).on('click', '#addMsg', function (event) {

        msg.message = $('#newMsg').val();

        $.ajax({
            type: "POST",
//      url: "/api/rest/doerService/doer/create",
            url: "/api/rest/dialogueService/dialogue/id/${dialogue.id}/message/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(msg),
            success: function (response) {
                $('#newMsg').val("");
                getNewPosts();
            }
        });
    });
    ///------------------------- Add msg -----------------------------------------------

    var getNewPosts = function(){
        $.ajax({
            type: "POST",
            url: "/dialogue/id/${dialogue.id}",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                dialogue = response;
                var members = $('#members');
                var newMembers = "";
                for(var j in dialogue.members){
                    var span = '<span style="display: inline-block;">';
                    var memberId = '<div>'+dialogue.members[j].id +'</div>';
                    var button = '<button id =' + dialogue.members[j].id + 'onclick="deleteMember(id)">Убрать из диалога</button>';
                    var img = '<img src="/api/rest/fileStorage/NEWS/file/read/id/'+dialogue.members[j].userPicId+'" width="200px" height="200px">';
                    var spanClose = '</span>';
                    newMembers += (span+memberId+button+img+spanClose);
                }
                if(members.html() !== newMembers){
                    members.html("");
                    members.append(newMembers);
                }

                var dialog = $('#dialogues');
                dialog.html("");
                dialog.append("<tr><td>От кого:</td><td>Текст:</td><td>Дата:</td></tr>");
                $("#title").html("").append("Тема диалога: "+response.subject);
                $('#size').html("").append("количество сообщений:"+response.messages.length);
                var lastMsg = response.messages[response.messages.length-1];
                if(lastMsg.date.minute<10){
                    lastMsg.date.minute = '0'+lastMsg.date.minute;
                }
                $('#last').html("").append("последнее сообщение: "
                +lastMsg.date.month+' '
                +lastMsg.date.dayOfMonth+' '
                +lastMsg.date.hour+':'
                +lastMsg.date.minute);

                for (var i in response.messages){
                    var minute = response.messages[i].date.minute;
                    if(minute < 10){
                        minute = '0'+minute;
                    }
                    var tr = '<tr>';
                    var tdMsg = '<td>'+response.messages[i].message+'</td>';
                    var tdDate = '<td>'+response.messages[i].date.month+' '
                            +response.messages[i].date.dayOfMonth+' '
                            +response.messages[i].date.hour+':'
                            +minute+ '</td>';

                    var name = 'Anonymous';
                    for(var m = 0; m < response.members.length; m ++){
                        if(response.members[m].id.toString() === response.messages[i].authorId.toString()){
                            name = response.members[m].name.toString();
                            break;
                        }
                    }

                    var tdId = '<td>'+ name +'</td>';
                    var trClose = '</tr>';
                    dialog.append(tr+tdId+tdMsg+tdDate+trClose);
                }
            }
        });
    };

    var getNewPostsAfter2sec = setInterval(function() {
        getNewPosts();
    }, 2000);



</script>

</body>
</html>

