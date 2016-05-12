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
        dialogues.forEach(function(dialogue){
            subscribeToOneDialogue(dialogue);
        });

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
            openDialog(dialogue);
            var privateMessage = JSON.parse(response.body).content;
            var senderName = "";
            for(var i = 0; i < dialogue.members.length; i++){
                if(dialogue.members[i].id === privateMessage.authorId){
                    senderName = dialogue.members[i].name;
                    break;
                }
            }
            addMessage(dialogue.id, privateMessage);

            var messagesField =  $("#" + dialogue.id + "_messages");
            var height = messagesField[0].scrollHeight;
            messagesField.scrollTop(height);
        });
        subscribedTo.push(dialogue.id);
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

function showRecentMessages (dialogue){
    var messages = dialogue.messages;
    for(var i = 0; i < messages.length; i++){
        addMessage(dialogue.id, messages[i]);
    }

    var messagesField =  $("#" + dialogue.id + "_messages");
    var height = messagesField[0].scrollHeight;
    messagesField.scrollTop(height);
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

//additional function by VS
$(document).on('click', '#openDialog', function () {
    $.ajax({
        type: "GET",
        url: 'dialogue/init/' + profileId ,
        success: function (dialogue) {
            /*window.dialogue = dialogue;*/
            subscribeProfile(dialogue.id, profileId);   // subscribing receiver
            openDialog(dialogue);
            subscribeToOneDialogue(dialogue);           // subscribing sender
        }
    });
})