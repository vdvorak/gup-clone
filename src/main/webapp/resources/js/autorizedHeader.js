var loggedInProfile = {};

$(document).ready(function() {
    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/loggedInProfile",
        success: function (profile) {
            loggedInProfile = profile;
            if (profile.contact != null && profile.contact.member == true) {
                $('#joinToGupBtn').hide();
            }

            if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
                $('#headerProfileImg').attr('src','/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic);
            } else {
                $('#headerProfileImg').attr('src','/resources/images/no_avatar.jpg');
            }

            if (profile.username == null) {
                $('#headerProfileName').text("Безымянный");
            } else {
                $('#headerProfileName').text(profile.username);
            }
        }
    });

    //$("#notificationBellImg").click(function () {
    //    $("#notificationContainer").empty();
    //
    //    var eventFO = {};
    //    eventFO.skip = 0;
    //    eventFO.limit = 20;
    //
    //    $.ajax({
    //        type: "POST",
    //        contentType: "application/json; charset=utf-8",
    //        url: "/api/rest/activityFeed/event/read/all",
    //        data: JSON.stringify(eventFO),
    //        success: function (response) {
    //            $(document).ready(function () {
    //                response.entities.forEach(function(event) {
    //                    $.ajax({
    //                        type: "POST",
    //                        url: "/api/rest/profilesService/profile/read/id/" + event.creatorEventId,
    //                        success: function (profile) {
    //                            var imgLinkTag = '<a href="/profile/id/'+ profile.id +'">';
    //                            if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
    //                                imgLinkTag +=  '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '" class="notifimage"/>';
    //                            } else {
    //                                imgLinkTag +=  '<img src="/resources/images/no_photo.jpg" class="notifimage"/>';
    //                            }
    //                            imgLinkTag += '</a>';
    //
    //                            $('#notificationContainer').append(
    //                                '<li class=" notif unread">' +
    //                                '<a href="#">' +
    //                                '<div class="imageblock">' +
    //                                imgLinkTag +
    //                                '</div>' +
    //                                '<div class="messageblock">' +
    //                                '<div class="messageinfo">' +
    //                                '<i class="icon-flag"></i>' + event.createdDate.hour + ':' + event.createdDate.minute + '    ' +
    //                                event.createdDate.dayOfMonth + '/' + event.createdDate.monthValue + '/' + event.createdDate.year +
    //                                '</div>' +
    //                                '<div class="message">' +
    //                                '<a href="/profile/id/'+ profile.id +'">' + profile.username + '</a>' +
    //                                '<p>' + event.type + '</p>' +
    //                                '</div>' +
    //                                '</div>' +
    //                                '</a>' +
    //                                '</li>');
    //                        }});
    //
    //
    //                });
    //
    //            });
    //        }
    //    });
    //
    //    $(this).toggleClass("open");
    //    $("#notificationMenu").toggleClass("open");
    //});
    //
    //$("#contactListImg").click(function () {
    //    $("#contactListContainer").empty();
    //
    //    if (loggedInProfile.contactList != null && loggedInProfile.contactList.length > 0) {
    //        loggedInProfile.contactList.forEach(function(contactId){
    //            $.ajax({
    //                type: "POST",
    //                url: "/api/rest/profilesService/profile/read/id/" + contactId,
    //                success: function (profile) {
    //                    var imgLinkTag = '<a href="/profile/id/'+ profile.id +'">';
    //                    if (profile.contact != null && profile.contact.pic != null && profile.contact.pic != '') {
    //                        imgLinkTag +=  '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '" class="notifimage"/>';
    //                    } else {
    //                        imgLinkTag +=  '<img src="/resources/images/no_photo.jpg" class="notifimage"/>';
    //                    }
    //                    imgLinkTag += '</a>';
    //
    //                    $('#contactListContainer').append(
    //                        '<li class="notif unread">' +
    //                        '<a href="#">' +
    //                        '<div class="imageblock">' +
    //                        imgLinkTag +
    //                        '</div>' +
    //                        '<div class="messageblock">' +
    //                        '<div class="message">' +
    //                        '<a href="/profile/id/'+ contactId +'">' + profile.username + '</a>' +
    //                        '</div>' +
    //                        '<div class="messageaction">' +
    //                        '<button onclick="createDialog('+ '\'' + contactId +'\'' +')">Написать сообщение</button>' +
    //                        '</div>' +
    //                        '</div>' +
    //                        '</a>' +
    //                        '</li>');
    //                }});
    //        });
    //    } else {
    //        $('#contactListContainer').append(
    //            '<li class="notif unread">' +
    //            '<div class="messageblock">' +
    //            '<div class="message">' +
    //            '<p>У вас нет еще контактов.</p>' +
    //            '<a href="/profile/list">Добавить контакты</a>' +
    //            '</div>' +
    //            '</div>' +
    //            '</li>');
    //    }
    //
    //    $(this).toggleClass("open");
    //    $("#contactListMenu").toggleClass("open");
    //});
});

//function createDialog(uId) {
//    window.location.href = "/dialogue/create/with/" + uId;
//}

