$("#notificationBellImg").click(function () {
    $("#notificationContainer").empty();

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
                alert("(document).ready(function (): " + JSON.stringify(response.entities));

                response.entities.forEach(function(event) {
                    alert('JSON.stringify(event): ' + JSON.stringify(event));

                    $.ajax({
                        type: "POST",
                        url: "/api/rest/profilesService/profile/read/id/" + event.creatorEventId,
                        success: function (profile) {
                            alert('profile: ' + JSON.stringify(profile));


                            var imgLinkTag = '<a href="/profile/id/'+ profile.id +'">';
                            if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
                                imgLinkTag +=  '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '" class="notifimage"/>';
                            } else {
                                imgLinkTag +=  '<img src="/resources/images/no_photo.jpg" class="notifimage"/>';
                            }
                            imgLinkTag += '</a>';

                            $('#notificationContainer').append(
                                '<li class=" notif unread">' +
                                    '<a href="#">' +
                                        '<div class="imageblock">' +
                                            imgLinkTag +
                                        '</div>' +
                                        '<div class="messageblock">' +
                                            '<div class="messageinfo">' +
                                                '<i class="icon-flag"></i>' + event.createdDate.hour + ':' + event.createdDate.minute + '    ' +
                                                event.createdDate.dayOfMonth + '/' + event.createdDate.monthValue + '/' + event.createdDate.year +
                                            '</div>' +
                                            '<div class="message">' +
                                                '<a href="/profile/id/'+ profile.id +'">' + profile.username + '</a>' +
                                                '<p>' + event.type + '</p>' +
                                            '</div>' +
                                                //'<div class="messageaction">' +
                                                //    '<a class="button tiny success">Type: ' + data[i].type + '</a>' +
                                                //'</div>' +

                                        '</div>' +
                                    '</a>' +
                                '</li>');
                        }});


                });

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