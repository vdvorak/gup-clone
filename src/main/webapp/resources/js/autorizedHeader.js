var loggedInProfile = {};

//  <js for header>

$("body").prepend("<div class='fadeScreen'></div>");

$(".fadeScreen").click(function() {
    $('.selecionado').removeClass( "selecionado" );
    $(".fadeScreen").hide();
    $(".user > div").slideUp("fast");
    $(".dropDownMail").slideUp("fast");
    $(".answer").slideUp('fast');
    $(".mailMessage").slideDown('fast');
    $(".dropDownBell").slideUp('fast');
    $(".book div").slideUp('fast');
    $(".dropDownMoney").slideUp('fast');
});

//ls

$(".user").click(function() {
    $('.user > div').slideToggle('fast', function() {
        $(this).toggleClass('selecionado');
        if( $('.selecionado').is(':visible') ) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".user > div").mouseleave(function() {
    setTimeout( function () {
        if ( !$('.user > div:hover').length ) {
            $('.selecionado').removeClass('selecionado');
            $('.user > div').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".user > div").click(function(event) {
    event.stopPropagation();
});

//



$(".mailMessage").click(function(event){
    event.stopPropagation();
    $(".mailMessage").hide('slow');
    $(".answer").show('slow');
});

$(".mailMessage, .answer").mouseleave(function() {
    setTimeout( function () {
        if ( !$('.mailMessage:hover, .answer:hover').length ) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownMail').slideUp('fast');
            $('.answer').slideUp('fast');
            $('.mailMessage').slideDown('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

//

$(".bell").click(function() {
    $('.dropDownBell').slideToggle('fast', function() {
        $(this).toggleClass('selecionado');
        if( $('.selecionado').is(':visible') ) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".dropDownBell").mouseleave(function() {
    setTimeout( function () {
        if ( !$('.dropDownBell:hover').length ) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownBell').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".dropDownBell").click(function(event) {
    event.stopPropagation();
});

//

$(".book").click(function() {
    $('.book div').slideToggle('fast', function() {
        $(this).toggleClass('selecionado');
        if( $('.selecionado').is(':visible') ) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".book div").mouseleave(function() {
    setTimeout( function () {
        if ( !$('.book div:hover').length ) {
            $('.selecionado').removeClass('selecionado');
            $('.book div').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".book div").click(function(event) {
    event.stopPropagation();
});

//

$(".money").click(function() {
    $('.dropDownMoney').slideToggle('fast', function() {
        $(this).toggleClass('selecionado');
        if( $('.selecionado').is(':visible') ) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".dropDownMoney").mouseleave(function() {
    setTimeout( function () {
        if ( !$('.dropDownMoney:hover').length ) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownMoney').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".dropDownMoney").click(function(event) {
    event.stopPropagation();
});

//

$('.dropDownBook').enscroll({
    verticalTrackClass: 'track4',
    verticalHandleClass: 'handle4',
    minScrollbarLength: 28
});

//  </js for header>

    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/loggedInProfile",
        async: false,
        success: function (profile) {
            loggedInProfile = profile;

            if (profile.imgId) {
                $('#headerProfileImg').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId + '?cachedImage=1');
            } else {
                $('#headerProfileImg').attr('src', '/resources/images/no_avatar.jpg');
            }

            if (profile.username) {
                $('#headerProfileName').text(profile.username);
            } else {
                $('#headerProfileName').text("Безымянный");
            }

            if (profile.unreadMessages > 0) {
                $('#unreadMessagesNum').text(profile.unreadMessages);
            }

            fillNotificationListBlock();
            fillContactListBlock(profile.contactList);

            if (profile.contact.member == true) {
                $('#socialBtn').hide();
            }
        }
    });

    function fillNotificationListBlock() {
        var eventFO = {};

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/activityFeed/event/read/all",
            data: JSON.stringify(eventFO),
            statusCode: {
                200: function (responseEntity) {
                    responseEntity.entities.forEach(function (event) {
                        $('.dropDownBell').append('<div class="bellMessage">' +
                            '<img src="' + getImgSrcForNotification(event.makerImgId) + '" alt="logo">' +
                            '<p>' +
                                '<a href="/profile/id/' + event.makerId + '">' + event.makerName + '</a> ' + event.type + ' ' +
                                '<a href="">' + event.contentStoreId + '</a> ' +
                            '</p>' +
                        '</div>');
                    });
                },
                204: function() {
                    $('.dropDownBell').append(
                        '<div class="bellMessage">' +
                            '<p>Нет новых уведомлений</p>' +
                        '</div>');
                }
            }
        });
    }

    function getImgSrcForNotification(imgId) {
        if (imgId) {
            return '/api/rest/fileStorage/PROFILE/file/read/id/' + imgId + '?cachedImage=1';
        } else {
            return '/resources/images/no_avatar.jpg';
        }
    }

    function fillContactListBlock(contactList) {
        if (contactList.length > 0) {
            contactList.forEach(function (contactId) {
                $.ajax({
                    type: "POST",
                    url: "/api/rest/profilesService/profile/read/id/" + contactId,
                    success: function (profile) {
                        $('.dropDownBook').append(
                            '<div class="friend">' +
                                getContactProfileImgTagHtml(profile.imgId) +
                                '<a href="/profile/id/' + contactId + '">' + profile.username + '</a>' +
                                '<a href="/dialogue/create/with/' + contactId + '">' +
                                    '<img src="/resources/images/userMessage.png" alt="Message">' +
                                '</a>' +
                            '</div>');
                    }
                });
            });
        } else {
            $('#dropDownBook').append(
                '<div class="friend">' +
                '<p>Вы еще никого не добавили к себе в контакты.</p>' +
                '<a href="/profile/list">Найти знакомых</a>' +
                '</div>');
        }
    }

    function getContactProfileImgTagHtml(imgId){
        var imgTag = '<img ';
        if (imgId) {
            imgTag += 'src="/resources/images/no_photo.jpg"';
        } else {
            imgTag += 'src="/api/rest/fileStorage/PROFILE/file/read/id/' + imgId + '?cachedImage=1"';
        }
        imgTag += ' />';

        return imgTag;
    }



    $(".mail").click(function(){
        $('.dropDownMail').slideToggle('fast', function() {
            $(this).toggleClass('selecionado');
            if( $('.selecionado').is(':visible') ) {
                $(".fadeScreen").show()
            } else {
                $(".fadeScreen").hide()
            }
        });
    });

$(".mailMessage").click(function(event){
    event.stopPropagation();
    $(".mailMessage").hide('slow');
    $(".answer").show('slow');
});

$(".mailMessage, .answer").mouseleave(function() {
    setTimeout( function () {
        if ( !$('.mailMessage:hover, .answer:hover').length ) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownMail').slideUp('fast');
            $('.answer').slideUp('fast');
            $('.mailMessage').slideDown('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

    //$("#overlay").click(function(){
    //    $(".mailMessage").show('slow');
    //    $(".answer").hide('slow');
    //    $("#overlay").hide();
    //    $(".mailDrop").hide();
    //});

//mailMessage=mailDrop-message
//dropDownMail=dialogStart
//alert("zazaza");
var dialogInit = $('#dropDownMail').html();
$('.mailMessage').last().hide();
$.ajax({
    type: "POST",
    url: "/api/rest/dialogueService/unread-msg/for-user-id/" + loggedInProfile.id,
    success: function (response) {
        //alert("ololo = " + response);
        var data = JSON.parse(response)
        for (var i in data) {
            $('#dropDownMail').append($('.mailMessage').last().clone());
            $('.mailMessage p').last().text(data[i]['message']);
            $('.mailMessage img').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data[i]['authorId']).attr('width', '44').attr('height', '44');
        }
        $('.mailMessage').first().remove();
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
    //                            if (profile.contact != null && profile.contact.imgId != null && profile.imgId != '') {
    //                                imgLinkTag +=  '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId + '" class="notifimage"/>';
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
    //                    if (profile.contact != null && profile.imgId != null && profile.imgId != '') {
    //                        imgLinkTag +=  '<img src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId + '" class="notifimage"/>';
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

//function createDialog(uId) {
//    window.location.href = "/dialogue/create/with/" + uId;
//}

