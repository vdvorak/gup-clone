$(".notificationicon").click(function () {

    var eventFO = {};
    eventFO.skip = 0;
    eventFO.limit = 10;

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/activityFeed/event/read/all",
        data: JSON.stringify(eventFO),
        success: function (response) {
            $(document).ready(function () {
                alert("(document).ready(function (): " + JSON.stringify(response));

                var data = response.entities;
                for (var i = 0; i < 5; i++) {
                //for (var i = 0; i < data.length; i++) {
                    //var createdDate = new Date(data[i].createdDate);
                    //data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();
                    //
                    //var  imagePreviewTag = '';
                    //if (data[i].imagesIds !== null) {
                    //    for (var key in data[i].imagesIds) {
                    //        if (data[i].imagesIds[key] === "1") {
                    //            imagePreviewTag = '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" width="200" height="200">';
                    //        }
                    //    }
                    //} else {
                    //    imagePreviewTag = '<img src="/resources/images/no_photo.jpg" width="200" height="200">';
                    //}

                    $('#notificationContainer').append(
                        '<li class=" notif unread">' +
                            '<a href="#">' +
                                '<div class="imageblock">' +
                                    '<img src="https://si0.twimg.com/sticky/default_profile_images/default_profile_2_bigger.png" class="notifimage"  />' +
                                '</div>' +
                            '<div class="messageblock">' +
                                '<div class="message">' +
                                    '<strong>Pete Nawara</strong>' + "want's to drink beer with you" +
                                '</div>' +
                                '<div class="messageaction">' +
                                    '<a class="button tiny success">accept</a>' +
                                    '<a class="button tiny alert">decline</a>' +
                                '</div>' +
                                '<div class="messageinfo">' +
                                    '<i class="icon-flag"></i>' + "3 hour ago" +
                                '</div>' +
                                '</div>' +
                            '</a>' +
                        '</li>');
                }
            });
        }
    });

    $(this).toggleClass("open");
    $("#notificationMenu").toggleClass("open");
});

$(".headerNotificationicon").click(function () {
    $(this).toggleClass("open");
    $("#notificationMenu").toggleClass("open");
});