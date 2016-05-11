var loggedInProfile = {};
var isNeedDrawAllHeader = true;

//  <js for header>

$("body").prepend("<div class='fadeScreen'></div>");

$(".fadeScreen").click(function () {
    $('.selecionado').removeClass("selecionado");
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

$(".user").click(function () {
    $('.user > div').slideToggle('fast', function () {
        $('.selecionado').removeClass("selecionado");
        $(".dropDownMail").slideUp("fast");
        $(".answer").slideUp('fast');
        $(".mailMessage").slideDown('fast');
        $(".dropDownBell").slideUp('fast');
        $(".book div").slideUp('fast');
        $(".dropDownMoney").slideUp('fast');
        $(this).toggleClass('selecionado');
        if ($('.selecionado').is(':visible')) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".user > div").mouseleave(function () {
    setTimeout(function () {
        if (!$('.user > div:hover').length) {
            $('.selecionado').removeClass('selecionado');
            $('.user > div').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".user > div").click(function (event) {
    event.stopPropagation();
});

//


//$(".mailMessage").click(function (event) {
//    event.stopPropagation();
//    $(".mailMessage").hide('slow');
//    $(".answer").show('slow');
//});

$(".mailMessage, .answer").mouseleave(function () {
    setTimeout(function () {
        if (!$('.mailMessage:hover, .answer:hover').length) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownMail').slideUp('fast');
            $('.answer').slideUp('fast');
            $('.mailMessage').slideDown('fast');
            $('.fadeScreen').hide('fast');
            $('#text-message-answer').val('');
        }
    }, 1000);
});

//

$(".bell").click(function () {
    $('.dropDownBell').slideToggle('fast', function () {
        $('.selecionado').removeClass("selecionado");
        $(".user > div").slideUp("fast");
        $(".dropDownMail").slideUp("fast");
        $(".answer").slideUp('fast');
        $(".mailMessage").slideDown('fast');
        $(".book div").slideUp('fast');
        $(".dropDownMoney").slideUp('fast');
        $(this).toggleClass('selecionado');
        if ($('.selecionado').is(':visible')) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
        if ($('.bellMessage').is(':visible')) {
            $('.dropDownBell > i').show();
        } else {
            $('.dropDownBell > i').hide();
        }
    });
});


$(".bell").mouseleave(function () {
    setTimeout(function () {
        if (!$('.dropDownBell:hover').length) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownBell').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".dropDownBell").click(function (event) {
    event.stopPropagation();
});


$(".book").click(function () {
    $('.book div').slideToggle('fast', function () {
        $('.selecionado').removeClass("selecionado");
        $(".user > div").slideUp("fast");
        $(".dropDownMail").slideUp("fast");
        $(".answer").slideUp('fast');
        $(".mailMessage").slideDown('fast');
        $(".dropDownBell").slideUp('fast');
        $(".dropDownMoney").slideUp('fast');
        $(this).toggleClass('selecionado');
        if ($('.selecionado').is(':visible')) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".book div").mouseleave(function () {
    setTimeout(function () {
        if (!$('.book div:hover').length) {
            $('.selecionado').removeClass('selecionado');
            $('.book div').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".book div").click(function (event) {
    event.stopPropagation();
});

//

$(".money").click(function () {
    $('.dropDownMoney').slideToggle('fast', function () {
        $('.selecionado').removeClass("selecionado");
        $(".user > div").slideUp("fast");
        $(".dropDownMail").slideUp("fast");
        $(".answer").slideUp('fast');
        $(".mailMessage").slideDown('fast');
        $(".dropDownBell").slideUp('fast');
        $(".book div").slideUp('fast');
        $(this).toggleClass('selecionado');
        if ($('.selecionado').is(':visible')) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});

$(".dropDownMoney").mouseleave(function () {
    setTimeout(function () {
        if (!$('.dropDownMoney:hover').length) {
            $('.selecionado').removeClass('selecionado');
            $('.dropDownMoney').slideUp('fast');
            $('.fadeScreen').hide('fast');
        }
    }, 1000);
});

$(".dropDownMoney").click(function (event) {
    event.stopPropagation();
});

//

$('.dropDownBook, .dropDownBell').enscroll({
    verticalTrackClass: 'track4',
    verticalHandleClass: 'handle4',
    minScrollbarLength: 28,
    zIndex: 10
});

//  </js for header>


var mailMessage = $('.mailMessage').first();


getLoggedInProfileAjax();


setTimeout(function run() {
    getLoggedInProfileAjax();
    setTimeout(run, 500000);
}, 500000);


function getLoggedInProfileAjax() {
    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/loggedInProfile",
        async: false,
        success: function (profile) {

            loggedInProfile = profile;

            if (isNeedDrawAllHeader) { // - do it only after first page loading
                if (profile.imgId) {
                    $('#headerProfileImg').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId + '?cachedImage=1');
                } else {
                    $('#headerProfileImg').attr('src', '/resources/images/no_avatar.jpg');
                }

                var profileName = '';
                if (profile.contact.type === 'LEGAL_ENTITY') {
                    profileName = profile.contact.companyName;
                } else if (profile.username) {
                    profileName = profile.username;
                } else {
                    profileName = 'Безымянный';
                }

                if (profileName != '') {
                    $('#headerProfileName').text(profileName);
                } else {
                    $('#headerProfileName').text('Безимянная фирма');
                }

                fillNotificationListBlock();
                fillContactListBlock(profile.contactList);

                if (profile.contact.member == true) {
                    $('#socialBtn').hide();
                }
            }

            if (profile.unreadMessages > 0) {
                $('#unreadMessagesNum').show();
                $('#unreadMessagesNum').text(profile.unreadMessages);
            } else {
                $('#unreadMessagesNum').hide();
            }
        }
    });

    $.ajax({
        type: "POST",
        contentType: "text/plain; charset=utf-8",
        url: "/api/rest/dialogueService/unread-msg/for-user-id/" + loggedInProfile.id,
        success: function (response) {

            if (!isNeedDrawAllHeader) {
                $('.mailMessage').remove(); // delete old messages - prepare for adding new
                $('.dropDownMail').prepend(mailMessage.clone())
            }

            if (response) {
                var data = JSON.parse(response);

                if ($(".answer").css('display') == 'none') {
                    for (var i in data) {
                        $('.dropDownMail').append($('.mailMessage').last().clone());
                        $('.mailMessage p').last().text(data[i]['message']);
                        $('.mailMessage img').last().attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data[i]['authorId']).attr('width', '44').attr('height', '44').show();
                        $('.mailMessage').last().attr('id', i);
                    }
                }

                if (Object.keys(data).length > 0) {
                    $('.mailMessage').first().remove();
                }
            }
        }
    });
    isNeedDrawAllHeader = false;
}


function fillNotificationListBlock() {
    var notification = {};
    var eventFO = {};


    function getNotification(event) {
        notification.targetText = '';
        switch (event.type) {
            case 'BLOG_SUBSCRIPTION':
                notification.type = 'У вашего блога новый подписчик';
                break;
            case 'BLOG_POST_LIKE':
                notification.type = 'Пользователю понравилась ваша запись';
                notification.contentStoreId = '/blog-post/view/id/' + event.contentStoreId;
                notification.targetText = 'Посмотреть новость';
                break;
            case 'BLOG_POST_DISLIKE':
                notification.type = 'Пользователю не нравится ваша запись';
                notification.contentStoreId = '/blog-post/view/id/' + event.contentStoreId;
                notification.targetText = 'Посмотреть новость';
                break;
            case 'BLOG_POST_COMMENT':
                notification.type = 'Новый комментарий к новости';
                notification.contentStoreId = '/blog-post/view/id/' + event.contentStoreId;
                notification.targetText = 'Посмотреть новость';
                break;
            case 'BLOG_POST_COMMENT_REPLY':
                notification.type = 'На ваш комментарий ответили';
                break;
            case 'BLOG_POST_COMMENT_LIKE':
                notification.type = 'Лайк вашего комментария';
                break;
            case 'PROJECT_COMMENT':
                notification.type = 'Новый комментарий к проекту';
                notification.contentStoreId = '/project?id=' + event.contentStoreId;
                notification.targetText = 'Посмотреть проект';
                break;
            case 'PROJECT_COMMENT_REPLY':
                notification.type = 'На ваш комментарий ответили';
                break;
            case 'MONEY_TRANSFER_TO_USER':
                notification.type = 'Вам зачислены средства';
                break;
            case 'MONEY_TRANSFER_TO_PROJECT':
                notification.type = 'Вы инвестировали в проект';
                break;
            case 'PROJECT_BRING_BACK_MONEY':
                notification.type = 'Проект вернул' + content + ' грн.';
                break;
            case 'NEW_CLIENT_WANT_CONFIRM':
                notification.type = 'Новый клиент ожидает подтверждения';
                break;
            case 'USER_ADD_TO_DOER_CLIENT_LIST':
                notification.type = 'Пользователь добавил исполнителя';
                break;
            case 'TENDER_END_DAY_NEED_CHOOSE_WINNER':
                notification.type = 'Тендер закончился, выберите победителя';
                notification.contentStoreId = '/tender/' + event.contentStoreId;
                notification.targetText = 'Посмотреть тендер';
                break;
            case 'YOU_HAVE_BEEN_ADDED_TO_CLOSE_TENDER':
                notification.type = 'Вас добавили в закрытый тендер';
                notification.contentStoreId = '/tender/' + event.contentStoreId;
                notification.targetText = 'Посмотреть тендер';
                break;
            case 'NEW_PROPOSE_IN_YOUR_TENDER':
                notification.type = 'Новое предложение в тендере';
                notification.contentStoreId = '/tender/' + event.contentStoreId;
                notification.targetText = 'Посмотреть тендер';
                break;
            case 'YOU_WON_IN_TENDER':
                notification.type = 'Вы выиграли в тендере!';
                notification.contentStoreId = '/tender/' + event.contentStoreId;
                notification.targetText = 'Посмотреть тендер';
                break;
            case 'OFFER_RESERVATION':
                notification.type = 'Объявление забронировано';
                break;
            case 'OFFER_RENT':
                notification.type = 'OFFER_RENT';
                break;
            default:
                notification.type = type;
        }
    }

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/activityFeed/event/read/all",
        data: JSON.stringify(eventFO),
        statusCode: {
            200: function (responseEntity) {
                responseEntity.entities.forEach(function (event) {
                    //alert(JSON.stringify(event));
                    getNotification(event);
                    $('.dropDownBell').append('<div class="bellMessage">' +
                    '<img src="' + getImgSrcForNotification(event.makerImgId) + '" alt="logo">' +
                    '<p>' +
                    '<a href="/profile?id=' + event.makerId + '">' + event.makerName + '</a> ' + notification.type + ' ' +
                    '<a href="' + notification.contentStoreId + '">' + notification.targetText + '</a> ' +
                    '</p>' +
                    '</div>');

                });
            },
            204: function () {
                $('#delete-all-events').remove();
                $('.dropDownBell').append(
                    '<div class="bellMessage">' +
                    '<p>Нет новых уведомлений</p>' +
                    '</div>');
            }
        }
    });
}

$('#read-all-events').on('click', function () {
    $.ajax({
        type: "POST",
        url: "/api/rest/activityFeed/event/set/viewed/all",
        statusCode: {
            200: function (response) {
                $(".dropDownBell").empty();
            },
            204: function () {
                alert("Внутренняя ошибка сервера")
            }
        }
    });
});

function getImgSrcForNotification(imgId) {
    if (imgId) {
        return '/api/rest/fileStorage/PROFILE/file/read/id/' + imgId + '?cachedImage=1';
    } else {
        return '/resources/images/no_avatar.jpg';
    }
}

function fillContactListBlock(contactList) {
    if (contactList.length > 0) {
        $('.defaultP').remove();
        contactList.forEach(function (contactId) {
            $.ajax({
                type: "POST",
                url: "/api/rest/profilesService/profile/read/id/" + contactId,
                success: function (profile) {
                    $('.dropDownBook').append(
                        '<div class="friend">' +
                        getContactProfileImgTagHtml(profile.imgId) +
                        '<a href="/profile?id=' + contactId + '">' + profile.username + '</a>' +
                        '<a href="/dialogue/create/with/' + contactId + '">' +
                        '<img src="/resources/images/userMessage.png" alt="Message">' +
                        '</a>' +
                        '</div>');
                }
            });
        });
    } else {
        $('.dropDownBook').append(
            '<div class="friend">' +
            '<p>Вы еще никого не добавили к себе в контакты.</p>' +
            '<a href="/profile/list">Найти знакомых</a>' +
            '</div>');
    }
}

function getContactProfileImgTagHtml(imgId) {
    var imgTag = '<img ';
    if (imgId) {
        imgTag += 'src="/resources/images/no_photo.jpg"';
    } else {
        imgTag += 'src="/api/rest/fileStorage/PROFILE/file/read/id/' + imgId + '?cachedImage=1"';
    }
    imgTag += ' />';

    return imgTag;
}


$(".mail > img").click(function () {
    $('.dropDownMail').slideToggle('fast', function () {
        $('.selecionado').removeClass("selecionado");
        $(".user > div").slideUp("fast");
        $(".dropDownBell").slideUp('fast');
        $(".book div").slideUp('fast');
        $(".dropDownMoney").slideUp('fast');
        $(this).toggleClass('selecionado');
        if ($('.selecionado').is(':visible')) {
            $(".fadeScreen").show()
        } else {
            $(".fadeScreen").hide()
        }
    });
});


$(document).on('click', '.mailMessage', function () {
    if ($(this).attr('id')) {
        var dialogueId = $(this).attr('id');
        dialogueMakeRead(dialogueId);
        $('#unread-msg-in-answer').text($(this).find('p').text());
        event.stopPropagation();
        $(".mailMessage").hide('slow');
        $(".answer img").attr('src', $(this).find('img').attr('src'));
        $(".answer").show('slow');
        $('#dialogue-answer-btn').addClass(dialogueId);
    }
});

function dialogueMakeRead(id) {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: 'api/rest/dialogueService/dialogue/updateRead/' + id,
        success: function (response) {
            $('#unreadMessagesNum').hide();
        }
    });
}

$(".answer").click(function () {

});

function sendMessageAjax(privateMessage, dialogueId) {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/dialogueService/dialogue/id/" + dialogueId + "/message/create",
        data: JSON.stringify(privateMessage),
        success: function (response) {
        }
    });
}

$('#dialogue-answer-btn').on('click', function () {
    var dialogueId = $(this).attr('class');
    var privateMessage = {};
    privateMessage.message = $('#text-message-answer').val();
    sendMessageAjax(privateMessage, dialogueId);

    $('#text-message-answer').val('');
    $('#' + dialogueId).remove();
    $('#dialogue-answer-btn').removeClass();
    $(".dropDownMail").slideUp("fast");

});

$(document).ready(function() {
    $.ajax({
        type: "GET",
        url: '/init_dialogues/all' ,
        success: function (dialogues) {
            initDialogues(dialogues);
        }
    });
})

/*$(window).keypress(function (e) {
    var textarea = document.getElementById('text-message-answer');
    if ((e.target == textarea) && (e.which == 13)) {
        var dialogueId = $('#dialogue-answer-btn').attr('class');
        var privateMessage = {};
        privateMessage.message = $('#text-message-answer').val();
        sendMessageAjax(privateMessage, dialogueId);

        $('#text-message-answer').val('');
        $('#' + dialogueId).remove();
        $('#dialogue-answer-btn').removeClass();
        $(".dropDownMail").slideUp("fast");
    }
});*/
