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
    <div id="emails">
    <input id="memberEmail" type="text" name="memberId" minlength="2" maxlength="70" required
           placeholder="email кому">
    </div>
    <button id="add">добавить собеседника</button>
    <br>
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
<script src="/resources/libs/sockjs-0.3.4.js"></script>
<script src="/resources/libs/stomp.js"></script>
<script type="text/javascript">
    var stompClient = null;
    function connect() {
        var socket = new SockJS('/socket-request');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/socket-response', function(response){
                showResponse(JSON.parse(response.body).content);
            });
        });
    }

    function disconnect() {
        stompClient.disconnect();
        console.log("Disconnected");
    }

    function sendMessage() {
        var message = document.getElementById('name').value;
        stompClient.send("/app/socket-request", {}, JSON.stringify({ 'message': message }));
    }

    function showResponse(message) {
        console.log(message);
    }

</script>

<script>

    var msg = {};
    var dialogue = {};
    $(document).ready(function(){
        getNewPosts();
    });
    ///------------------------- Add msg -----------------------------------------------
    $(document).on('click', '#addMsg', function (event) {

        msg.message = $('#newMsg').val();


        //!!!! should add sockets functionality here, instead of ajax. for test reason add getNewPosts at the end.
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
                    var memberId = '<div>'+dialogue.members[j].name +'</div>';
                    var button = '<div onclick=\"deleteMember(' + '\'' + dialogue.members[j].id+ '\'' + ')">Удалить из диалога</div>'.replace(" ","");
                    if(dialogue.members[j].userPicId != null) {
                        var img = '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + dialogue.members[j].userPicId + '" width="200px" height="200px">';
                    }else{
                        var img = '<img src="/resources/images/no_photo.jpg" width="200px" height="200px">';
                    }
                    var spanClose = '</span>';
                    newMembers += (span+memberId+button+img+spanClose);
//                    newMembers += (span+memberId+img+spanClose);
                }
                if(members.html() !== newMembers){
                    members.html("");
                    members.append(newMembers);
                }
                var dialog = $('#dialogues');
                dialog.html("");
                dialog.append("<tr><td>От кого:</td><td>Текст:</td><td>Дата:</td></tr>");
                $("#title").html("").append("Тема диалога: "+response.subject);
                if (response.messages === null) response.messages = [];
                $('#size').html("").append("количество сообщений:"+response.messages.length);
                var lastMsg = response.messages[response.messages.length-1];
                if (lastMsg !== undefined){
                    $('#last').html("").append("последнее сообщение: "+lastMsg.date);
                }else{
                    $('#last').html("").append("нет сообщений");
                }

                for (var i in response.messages){
                    var tr = '<tr>';
                    var tdMsg = '<td>'+response.messages[i].message+'</td>';
                    var tdDate = '<td>'+response.messages[i].date+'</td>';
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

    var updateDialog = function(updateDialog){
        deleteNamesAndPics(updateDialog);
        $.ajax({
            type: "POST",
            url: "/api/rest/dialogueService/dialogue/update/id/${dialogue.id}",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(updateDialog),
            dataType: "json",
            success: function (response) {
                getNewPosts();
            }
        });
    };

    var getNewPostsAfter2sec = setInterval(function() {
        getNewPosts();
    }, 2000);

    var deleteMember = function(id){
        var newMembers = dialogue.members;
        for (var i in dialogue.members){
            if (dialogue.members[i].id === id){
                dialogue.members.splice(i,1);
            }
        }
        dialogue.members = newMembers;
        updateDialog(dialogue);
    };

    $('#add').click(function(){
        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/email-check",
            data: {"email": $('input[name="memberId"]').val()},
            success: function (data) {

                if (data === 'NOT FOUND'){
                    alert("Нет пользователя с таким e-mail");
                }else{
                    var member = {};
                    member.id = data;
                    var flag = true;
                    for(var i = 0; i< dialogue.members.length; i++){
                        if (dialogue.members[i].id === data) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        dialogue.members.push(member);
                        updateDialog(dialogue);
                    } else{
                        alert("Собеседник уже добавлен!");
                    }
                }
            }
        });
    });

    var deleteNamesAndPics = function(dialogue){
        for (var i in dialogue.members){
            delete dialogue.members[i].name;
            delete dialogue.members[i].userPicId;
        }
    }

</script>

<div>
    <button id="connect" onclick="connect();">Connect</button>
    <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
</div>
<div id="conversationDiv">
    <label>What is your name?</label><input type="text" id="name" />
    <button id="sendMessage" onclick="sendMessage();">Send</button>
    <p id="response"></p>
</div>

</body>
</html>

