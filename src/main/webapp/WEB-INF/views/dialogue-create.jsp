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
<div>
  <input id="memberEmail" type="text" name="memberId" minlength="2" maxlength="70" required
         placeholder="email кому">
</div>

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

  $(document).on('click', '#createDialogue', function (e) {
    dialogue.subject = $('#dialogueSubject').val();
    msg.message = $('#msg').val();
    msgs.push(msg);
    dialogue.messages = msgs;
    var member2 = {};


//    alert("Отправляем почту:" + $('#memberEmail').val());


    $.ajax({
      type: "POST",
      url: "/api/rest/profilesService/profile/email-check",
      data: {"email": $('#memberEmail').val()},
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