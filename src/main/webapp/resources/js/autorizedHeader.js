var loggedInProfile = {};

$(document).ready(function() {
    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/loggedInProfile",
        success: function (profile) {
            loggedInProfile = profile;
            if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
                $('#profileImg').attr('src','/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic);
            } else {
                $('#profileImg').attr('src','/resources/images/no_photo.jpg');
            }

            if (profile.username == null) {
                $('#profileName').text("Безымянный");
            } else {
                $('#profileName').text(profile.username);
            }
        }
    });

    $("#contactListImg").click(function(){
        alert(JSON.stringify(loggedInProfile));
    });
});
