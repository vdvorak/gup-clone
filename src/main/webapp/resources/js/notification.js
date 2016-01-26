$(".headerNotificationIcon").click(function () {
    var eventFO = {};
    eventFO.skip = 0;
    eventFO.limit = 20;

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/activityFeed/event/read/all",
        data: JSON.stringify(eventFO),
        success: function (response) {
            $(document).ready(function () {
                //alert("(document).ready(function (): " + JSON.stringify(response));

                var data = response.entities;

                for (var i = 0; i < data.length; i++) {
                    //var createdDate = new Date(data[i].createdDate);
                    //data[i].createdDate = createdDate.getHours() + ':' + createdDate.getMinutes() + '  ' + createdDate.getDate() + '/' + (createdDate.getMonth() + 1);

                    $('#notificationContainer').append(
                        '<li class=" notif unread">' +
                            '<a href="#">' +
                                '<div class="imageblock">' +
                                    '<img src="https://si0.twimg.com/sticky/default_profile_images/default_profile_2_bigger.png" class="notifimage"  />' +
                                '</div>' +
                                '<div class="messageblock">' +
                                    '<div class="message">' +
                                    '<strong>creatorId:' + data[i].creatorEventId + '</strong> Type: ' + data[i].type +
                                    '</div>' +
                                    //'<div class="messageaction">' +
                                    //    '<a class="button tiny success">Type: ' + data[i].type + '</a>' +
                                    //'</div>' +
                                    '<div class="messageinfo">' +
                                        '<i class="icon-flag"></i>' + data[i].createdDate +
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

//$(".notificationicon").click(function () {
//
//    var eventFO = {};
//    eventFO.skip = 0;
//    eventFO.limit = 10;
//
//    $.ajax({
//        type: "POST",
//        contentType: "application/json; charset=utf-8",
//        url: "/api/rest/activityFeed/event/read/all",
//        data: JSON.stringify(eventFO),
//        success: function (response) {
//            $(document).ready(function () {
//                var data = response.entities;
//
//                for (var i = 0; i < data.length; i++) {
//                    //var createdDate = new Date(data[i].createdDate);
//                    //data[i].createdDate = createdDate.getHours() + ':' + createdDate.getMinutes() + '  ' + createdDate.getDate() + '/' + (createdDate.getMonth() + 1);
//
//                    $('#notificationContainer').append(
//                        '<li class=" notif unread">' +
//                            '<a href="#">' +
//                                '<div class="imageblock">' +
//                                    '<img src="https://si0.twimg.com/sticky/default_profile_images/default_profile_2_bigger.png" class="notifimage"  />' +
//                                '</div>' +
//                            '<div class="messageblock">' +
//                                '<div class="message">' +
//                                    '<strong>creatorId:' + data[i].creatorEventId + '</strong> Type: ' + data[i].type +
//                                '</div>' +
//                                //'<div class="messageaction">' +
//                                //    '<a class="button tiny success">Type: ' + data[i].type + '</a>' +
//                                //'</div>' +
//                                '<div class="messageinfo">' +
//                                    '<i class="icon-flag"></i>' + data[i].createdDate +
//                                '</div>' +
//                                '</div>' +
//                            '</a>' +
//                        '</li>');
//                }
//            });
//        }
//    });
//
//    $(this).toggleClass("open");
//    $("#notificationMenu").toggleClass("open");
//});