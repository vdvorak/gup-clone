<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Создание диалога</title>
  <script src="/resources/libs/jquery-1.11.3.min.js"></script>
  <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
  <script src="/resources/libs/sockjs-0.3.4.js"></script>
  <script src="/resources/libs/stomp.js"></script>
  <script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
      document.getElementById('connect').disabled = connected;
      document.getElementById('disconnect').disabled = !connected;
      document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
      document.getElementById('response').innerHTML = '';
    }

    function connect() {
      var socket = new SockJS('/hello');
      stompClient = Stomp.over(socket);
      stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
          showGreeting(JSON.parse(greeting.body).content);
        });
      });
    }

    function disconnect() {
      stompClient.disconnect();
      setConnected(false);
      console.log("Disconnected");
    }

    function sendName() {
      var name = document.getElementById('name').value;
      stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
    }

    function showGreeting(message) {
      var response = document.getElementById('response');
      var p = document.createElement('p');
      p.style.wordWrap = 'break-word';
      p.appendChild(document.createTextNode(message));
      response.appendChild(p);
    }
  </script>
</head>
<body>
<div>
  <input id="dialogueSubject" type="text" name="dialogueSubject" minlength="2" maxlength="70" required
         placeholder="Тема диалога">
</div>
<div id="emails">
  <input id="memberEmail" type="text" name="memberId" minlength="2" maxlength="70" required
         placeholder="email кому">
</div>
<button id="add">добавить собеседника</button>

<div>
    <textarea id="msg"  minlength="1" maxlength="5000" required
              placeholder="Введите сообщение"></textarea>
</div>
<button id="createDialogue">Создать диалог</button>



<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
  Javascript and reload this page!</h2></noscript>
<div>
  <div>
    <button id="connect" onclick="connect();">Connect</button>
    <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
  </div>
  <div id="conversationDiv">
    <label>What is your name?</label><input type="text" id="name" />
    <button id="sendName" onclick="sendName();">Send</button>
    <p id="response"></p>
  </div>
</div>



</body>
</html>



<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Создание диалога</title>
</head>
<body>
<div>
  <input id="dialogueSubject" type="text" name="dialogueSubject" minlength="2" maxlength="70" required
         placeholder="Тема диалога">
</div>
<div id="emails">
  <input id="memberEmail" type="text" name="memberId" minlength="2" maxlength="70" required
         placeholder="email кому">
</div>
<button id="add">добавить собеседника</button>

<div>
    <textarea id="msg"  minlength="1" maxlength="5000" required
              placeholder="Введите сообщение"></textarea>
</div>
<button id="createDialogue">Создать диалог</button>

<script src="/resources/libs/jquery-1.11.3.min.js"></script>
<script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script>

  var dialogue = {};
  var msgs = [];
  var members = [];
  var msg = {};

  //----------------------------------------------------- Image form -----------------------------------------------

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
          for(var i = 0; i< members.length; i++){
            if (members[i].id === data) {
              flag = false;
              break;
            }
          }
          if(flag) {
            members.push(member);
            $('input[name="memberId"]').attr('readonly', true).css({'background-color': '#808080'}).attr('name', 'test');
            $('#emails').append('<input type="text" name="memberId" minlength="2" maxlength="70" required placeholder="email кому">');
          } else{
            alert("Собеседник уже добавлен!");
          }
        }
      }
    });
  });





  $(document).on('click', '#createDialogue', function (e) {
    dialogue.subject = $('#dialogueSubject').val();
    msg.message = $('#msg').val();
    msgs.push(msg);
    dialogue.messages = msgs;
    var member2 = {};


    alert("Отправляем почту:" + $('#memberEmail').val());
//    alert("Отправляем почту:" + $('#memberEmail').val());


    $.ajax({
      type: "POST",
      url: "/api/rest/profilesService/profile/email-check",
      data: {"email": $('input[name="memberId"]').val()},
      success: function (data) {
        if (data === 'NOT FOUND'){
          alert("Нет пользователя с таким e-mail");
        }else{
          member2.id = data;
          members.push(member2);
          dialogue.members = members;
//            alert(JSON.stringify(dialogue));

          $.ajax({
            type: "POST",
            url: "/api/rest/dialogueService/dialogue/create",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(dialogue),
            success: function (data, status, xhr) {
              var path = xhr.getResponseHeader("Location").split("/");
              var id = path[path.length-1];
              window.location.href = "/dialogue/id/" + id;
            },
            error: function(){
              alert("error");
            }
          })
        }
      }
    });
  });

</script>
</body>
</html>
--%>
