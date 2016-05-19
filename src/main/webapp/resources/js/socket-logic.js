/**
 * Created by Lili on 06.05.2016.
 */
var stompClient = null;
var isConnected = false;
var socket;
var msg = {};
var dialogues;
var dialogue;
var subscribedTo = [];
var senders = [];

/*function connect(){
    socket = new SockJS('/socket-request');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        isConnected = true;

    });
    stompClient.ws.onclose = function(){
        isConnected = false;
    }
}*/
/*connect();*/

function subscribeToAllDialogues(){
    socket = new SockJS('/socket-request');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        isConnected = true;
        if(dialogues){
            dialogues.forEach(function(dialogue){
                subscribeToOneDialogue(dialogue);
            });
        }

        stompClient.subscribe('/topic/socket-response/' + loggedInProfile.id, function(response){ // letting subscribing user from the opposite side
            var dialog = JSON.parse(response.body).dialogue;
            subscribeToOneDialogue(dialog);
        })
    });
    stompClient.ws.onclose = function(){
        isConnected = false;
    }
}

function subscribeToOneDialogue (dialogue){
    if(subscribedTo.indexOf(dialogue.id) === -1){
        for(var k = 0; k < dialogue.members.length; k++){
            if(dialogue.members[k].id === loggedInProfile.id){
                dialogue.members[k].name = loggedInProfile.username;
                dialogue.members[k].userPicId = loggedInProfile.imgId;
                break;
            }
        }

        stompClient.subscribe('/topic/socket-response/' + dialogue.id, function(response){
                var privateMessage = JSON.parse(response.body).content;
                dialogues.forEach(function(e){
                    if(e.id === dialogue.id && e.sender === loggedInProfile.id){
                        addMessage(dialogue.id, privateMessage);
                        var messagesField =  $("#" + dialogue.id + "_messages");
                        var height = messagesField[0].scrollHeight;
                        messagesField.scrollTop(height);
                        return false;
                    }else if(e.id === dialogue.id && e.sender !== loggedInProfile.id){
                        if(e.isOpenedOnce){
                            addMessage(dialogue.id, privateMessage);
                            var messagesField =  $("#" + dialogue.id + "_messages");
                            var height = messagesField[0].scrollHeight;
                            messagesField.scrollTop(height);
                            return false;
                        }else{
                            var senderName = "";
                            for(var i = 0; i < dialogue.members.length; i++){
                                if(dialogue.members[i].id === privateMessage.authorId){
                                    senderName = dialogue.members[i].name;
                                    break;
                                }
                            }
                            privateMessage.senderName = senderName;
                            appendToIncoming(privateMessage, dialogue);
                        }
                    }
                })
        });
        subscribedTo.push(dialogue.id);
    }
}

function appendToIncoming(message, dialogue) {
    if (senders.indexOf(message.authorId) === -1) {
        senders.push(message.authorId);
        $('#unreadMessages').css('display', 'block');
        $('.dropDownMail').append("<div><button" + " id='"+ message.authorId + "_sender' style='width: 100%; height: 30px;'>" +
            message.senderName + "</button></div>");
        $("#"+ message.authorId + "_sender").on('click', {dialogue: dialogue}, function(event){
            openDialog(event.data.dialogue, message.authorId);
        });
    }
}

function addMessage (dialogueId, message){
    if(message.authorId === loggedInProfile.id){
        $("#" + dialogueId + "_messages").append(
            "<div class='message-to'>" + "<div class='message'>" + message.message + "</div></div>"
        )
    }else{
        $("#" + dialogueId + "_messages").append(
            "<div class='message-from'>" + "<div class='message'>" + message.message + "</div></div>"
        )
    }
}

function subscribeProfile (dialogueId, profileId){
    stompClient.send("/app/socket-request/profile/" + profileId, {}, JSON.stringify({ 'message': dialogueId }));
}

function initDialogues(dialogues){
    this.dialogues = dialogues;
    for(var i =0; i < dialogues.length; i++){
        this.dialogues[i].sender = "";
        this.dialogues[i].isOpenedOnce = false;
    }
    subscribeToAllDialogues();
}

setInterval(function(){
    if(!isConnected){
        subscribeToAllDialogues();
    }
},5000);

function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}

function showResponse(message) {
    console.log(message);
}

/*$(document).on('click', '#addMsg', function (event) {
    msg.message = $('#newMsg').val();
    var url = "/app/socket-request/dialogue/" + dialogue.id;
    stompClient.send(url, {}, JSON.stringify({ 'message': msg.message }));
    $('#newMsg').val("");

});*/

function keyCodeAnalyse(event){
    //var evt = window.event || event;
    var key = event.keyCode ;
    var dialogueId = $(event.target).attr('dialogueId')
    msg.message = $('#'+ dialogueId + "_newMsg").val().trim();
    if(key === 13 && msg.message !==""){
        event.preventDefault();
        var url = "/app/socket-request/dialogue/" + dialogueId;

        for(var i =0; i < dialogues.length; i++){
            if(dialogues[i].id === dialogueId){
                dialogues[i].sender = loggedInProfile.id;
                break;
            }
        }

        stompClient.send(url, {}, JSON.stringify({ 'message': msg.message }));
        $('#'+ dialogueId + "_newMsg").val("");
    }else if(key === 13){
        event.preventDefault();
    }

    /*msg.message = $('#newMsg').val();
     var url = "/app/socket-request/" + dialogue.id;
     stompClient.send(url, {}, JSON.stringify({ 'message': msg.message }));
     $('#newMsg').val("");*/
}

function showRecentMessages (dialogue, sender){
    $.ajax({
        type: "POST",
        url: "/dialogue/id/" + dialogue.id,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (dialogue) {
            var messages = dialogue.messages;
            for(var i = 0; i < messages.length; i++){
                addMessage(dialogue.id, messages[i]);
            }
            var messagesField =  $("#" + dialogue.id + "_messages");
            var height = messagesField[0].scrollHeight;
            messagesField.scrollTop(height);

            removeFromDropdown(dialogue.id, sender);
        }

    });
}

function removeFromDropdown(dialogueId, sender){
    var appendedSenders = [];

    var arr = $('.dropDownMail').find('button');
    for(var j=0; j < arr.length; j++){
        appendedSenders.push($(arr[j]).attr('id').split('_')[0]);
    }

    var out = false;

    for(var i =0; i < dialogues.length; i++){
        if(dialogues[i].id === dialogueId){
            dialogues[i].isOpenedOnce = true;
            break;
        }
    }
    if(sender !== ""){
        $('#' + sender + '_sender').remove();
        var z = senders.indexOf(sender);
        senders.splice(z, 1) ;
        if(senders.length === 0){
            $('#unreadMessages').css('display', 'none');
        }
    }

    $('.dropDownMail').css('display', 'none');
}

function setTitle(dialogue){
    var title = "";
    var members = dialogue.members;
    for(var i = 0; i < members.length; i++){
        if(members[i].id !== loggedInProfile.id){
            title += members[i].name;
            if(i > 0 && i < members.length -1){
                title += ', ';
            }
        }
    }
    $('#' + dialogue.id + '_title').text(title);
}

function updateDialogues(dialogue){
    var exist = false;
    dialogues.forEach(function(e){
        if(e.id === dialogue.id){
            e.sender = loggedInProfile.id;
            e.isOpenedOnce = true;
            exist = true;
            return false;
        }
    });

    if(!exist){
        dialogues.push(dialogue);
    }
}

//additional function by VS
$(document).on('click', '#openDialog', function (){
    $.ajax({
        type: "GET",
        url: 'dialogue/init/' + profileId ,
        success: function (dialogue) {
            dialogue.initialSender = loggedInProfile.id;
            /*window.dialogue = dialogue;*/
            subscribeProfile(dialogue.id, profileId);   // subscribing receiver
            openDialog(dialogue, "");
            subscribeToOneDialogue(dialogue);           // subscribing sender
            dialogue.sender = loggedInProfile.id ;
            dialogue.isOpenedOnce = true;
            updateDialogues(dialogue);
        }
    });
})