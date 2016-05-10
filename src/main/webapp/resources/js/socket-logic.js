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

        stompClient.subscribe('/topic/socket-response/' + loggedInProfile.id, function(response){
            var dialog = JSON.parse(response.body).dialogue;
            subscribeToOneDialogue(dialog);
        })
    });
    stompClient.ws.onclose = function(){
        isConnected = false;
    }
}

function subscribeToOneDialogue (dialogue){
    if(subscribedTo.indexOf(dialogue.id) < 0){
        stompClient.subscribe('/topic/socket-response/' + dialogue.id, function(response){
            subscribedTo.push(dialogue.id);
            var privateMessage = JSON.parse(response.body).content;
            var senderName = "";
            for(var i = 0; i < dialogue.members.length; i++){
                if(dialogue.members[i].id === privateMessage.authorId){
                    senderName = dialogue.members[i].name;
                    break;
                }
            }

            if(privateMessage.authorId === loggedInProfile.id){
                $("#" + dialogue.id + "_messages").append(
                    "<div class='message-to'>" + "<div class='message'>" + privateMessage.message + "</div></div>"
                )
            }else{
                $("#" + dialogue.id + "_messages").append(
                    "<div class='message-from'>" + "<div class='message'>" + privateMessage.message + "</div></div>"
                )
            }
        });
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

//additional function by VS
$(document).on('click', '#openDialog', function () {
    $.ajax({
        type: "GET",
        url: 'dialogue/init/' + profileId ,
        success: function (dialogue) {
            window.dialogue = dialogue;
            subscribeProfile(dialogue.id, profileId);
            openDialog(dialogue.id);
            subscribeToOneDialogue(dialogue);
        }
    });
})