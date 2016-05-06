/**
 * Created by Lili on 06.05.2016.
 */
var stompClient = null;
var isConnected = false;
var socket;

function connect(){
    socket = new SockJS('/socket-request');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        isConnected = true;
        stompClient.subscribe('/topic/socket-response/' + dialogue.id, function(response){
            var privateMessage = JSON.parse(response.body).content;
            var senderName = "";
            for(var i = 0; i < dialogue.members.length; i++){
                if(dialogue.members[i].id === privateMessage.authorId){
                    senderName = dialogue.members[i].name;
                    break;
                }
            }
            var dialog = $('#dialogues');
            var tdId = '<td>'+ senderName +'</td>';
            var tr = '<tr>';
            var tdMsg = '<td>'+privateMessage.message+'</td>';
            var tdDate = '<td>'+ privateMessage.date +'</td>';
            var trClose = '</tr>';
            dialog.append(tr+tdId+tdMsg+tdDate+trClose);
        });
    });
    stompClient.ws.onclose = function(){
        isConnected = false;
    }
}

connect();

setInterval(function(){
    if(!isConnected){
        connect();
    }
},5000);

function disconnect() {
    stompClient.disconnect();
    console.log("Disconnected");
}

function sendMessage() {
    var message = document.getElementById('name').value;
    stompClient.send("/app/socket-request/" + dialogue.id, {}, JSON.stringify({ 'message': message }));
}

function showResponse(message) {
    console.log(message);
}

$(document).on('click', '#addMsg', function (event) {

    msg.message = $('#newMsg').val();
    var url = "/app/socket-request/" + dialogue.id;
    stompClient.send(url, {}, JSON.stringify({ 'message': msg.message }));

    $('#newMsg').val("");

});