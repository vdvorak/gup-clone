/**
 * Created by Комп2 on 02.03.2016.
 */
//mailMessage=mailDrop-message
//dropDownMail=dialogStart
$(document).ready(function () {
    var dialogInit = $('#dropDownMail').html();
    $('.mailMessage').last().hide();
    $.ajax({
        type: "POST",
        url: "/api/rest/dialogueService/unread-msg/for-user-id/" + loggedInProfile.id,
        success: function (response) {
            var data = JSON.parse(response)
            for (var i in data) {
                $('#dropDownMail').append($('.mailMessage').last().clone());
                $('.mailMessage p').last().text(data[i]['message']);
                $('.mailMessage img').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data[i]['authorId']).attr('width', '44').attr('height', '44');
            }
            $('.mailMessage').first().remove();
        }
    });

    $(".mailMessage").click(function(){
        $(".mailMessage").hide('slow');
        $(".answer").show('slow');
        $("#overlay").show();
    });
});