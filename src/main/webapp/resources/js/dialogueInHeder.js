/**
 * Created by Комп2 on 02.03.2016.
 */
$(document).ready(function () {
    var dialogInit = $('#dialogStart').html();
    $('.mailDrop-message').last().hide();
    $.ajax({
        type: "POST",
        url: "/api/rest/dialogueService/unread-msg/for-user-id/" + loggedInProfile.id,
        success: function (response) {
            var data = JSON.parse(response)
            for (var i in data) {
                $('#dialogStart').append($('.mailDrop-message').last().clone());
                $('.mailDrop-message-p').last().text(data[i]['message']);
                $('.mailDrop-message img').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data[i]['authorId']).attr('width', '44').attr('height', '44');
            }
            $('.mailDrop-message').first().remove();
        }
    });
});