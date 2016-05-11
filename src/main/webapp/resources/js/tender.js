var proposes;
var isTenderHasWinner = false;

// ----------------------- Begin Tender propose text length counter ------------------------------
$("#tenderPropose").on('keyup', function (event) {
    var button = $('#makePropose');
    var counter = $("#textLength");

    var currentString = $("#tenderPropose").val();
    counter.html(currentString.length);
    if (currentString.length <= 50) {  /*or whatever your number is*/
        button.attr("disabled", true);
        counter.css("color", "red");
    } else {
        if (currentString.length > 500) {
            button.attr("disabled", true);
            counter.css("color", "red");
        } else {
            button.attr("disabled", false);
            counter.css("color", "green");
        }
    }
});
// ----------------------- End Tender propose text length counter ------------------------------

function sliderImg(arr) {
    var url = '';
    var imgId = '';
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].fileType === 'IMAGE' || arr[i].fileType === 'MAINIMAGE') {
            imgId = arr[i].id;
            url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
            var element = '<li><img class="test1" src="' + url + '" /></li>';
            $('.bxsliderTender').append(element)
        }
    }
    sliderInit();
}

function localDateTime(long) {
    long = new Date(parseInt(long)) * 1000;
    long = moment(long).locale("ru").format('LLL');
    return long;
}

$.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    url: "/api/rest/tenderService/tender/read/id/" + tenderId,
    success: function (response) {
        var data = response;

        if (data.authorId === loggedInProfile.id) {
            $('#wantToComment').replaceWith('<a class="abutton blue" href="/tender/id/' + data.id + '/update">Редактировать</a>');
        }

        sliderImg(data.files);

        for (var i = 0; i < data.files.length; i++) {
            if (data.files[i].fileType === 'DOCUMENT') {
                $('<a href="/api/rest/fileStorage/TENDER/file/read/id/' + data.files[i].id + '">' + data.files[i].name + '</a>').appendTo('.tenderFils');
            }
        }

        $(".tender-item-text").last().html(data.body);
        $(".tender-number").last().text(data.tenderNumber);
        $(".tender-publish-date span").last().text(localDateTime(data.publishDate));
        $(".tender-veiws span").last().text(data.visited);
        $(".tender-proposal-count span").last().text(data.proposeNumber);
        if (!data.hideContact) {
            var xhr = findUser(data.authorId);
            $.when(xhr).done(function (resp) {
                $(".tender-author-contact a").last().text((resp.username === null) ? 'Безымянный' : resp.username).attr('href', '/profile?id=' + data.authorId);
            });
        } else {
            $(".tender-author-contact a").replaceWith('<span>данные скрыты автором</span>')
        }

        if (data.expectedPrice != null) {
            $(".tender-expectedPrice").last().text(data.expectedPrice + "₴");
        } else {
            $(".tender-expectedPrice").last().text("Ожидаемая сумма не указана");
        }


        $(".tender-name").last().text(data.title);

        var dateEnd = localDateTime(data.end);
        $(".date-finish").last().text((dateEnd === 'Invalid date') ? 'Дата не указана' : dateEnd);


        // delete button if user is not an author of tender
        if (typeof loggedInProfile != 'undefined') {
            if (data.authorId != loggedInProfile.id) {
                $('.chooseWinner').remove();
            }
        }

        // delete button if winner is already chosen
        if (typeof data.winnerId != 'undefined' && data.winnerId != null) {
            if (data.winnerId.length > 0) {
                isTenderHasWinner = true;
                $('.chooseWinner').remove();
            }
        }


// ------------------------- Propose build block ---------------------------------------------------------------------
        for (var i in data.proposes) {
            $('#commentStart').append($('.comments').last().clone());
            $(".propose-author").last().text(data.proposes[i].authorId).attr('href', '/profile?id=' + data.proposes[i].authorId);

            if (isTenderHasWinner && data.winnerId == data.proposes[i].authorId) {
                $(".propose-author").last().after('<p id="winner">Победитель!</p>')
            } else {
                $('#winner').remove();
            }

            $(".propose-date span").last().text(localDateTime(data.proposes[i].time));
            $(".poropse-text").last().text(data.proposes[i].body);
            $(".chooseWinner").last().attr('id', data.proposes[i].authorId);
        }
        $('.comments').first().remove();
// ------------------------- Propose build block ---------------------------------------------------------------------


        $(".chooseWinner").on('click', function () {
            var Tender = {};
            Tender.winnerId = $(this).attr('id');
            Tender.id = tenderId;
            $.ajax({
                type: "POST",
                url: "/api/rest/tenderService/tender/chooseWinner",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(Tender),
                statusCode: {
                    200: function () {
                        alert("Победитель выбран!");
                        window.location.href = '/tender/' + tenderId;
                    }
                }
            });
        });


        $('.proposes-wraper').last().attr('style', 'display: none;');

        for (var j in data.members) {
            $('.comments').each(function (index) {
                if ($(this).find('.propose-author').text() === data.members[j].id) {
                    $(this).find('.propose-author').text(data.members[j].name);

                    if (data.members[j].userPic != null) {
                        $(this).find('.member-pic').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data.members[j].userPic)
                    }

                }
            });
        }
    },
    statusCode: {
        401: function () {
            $('#tender-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для просмотра тендера вам необходимо войти в систему</i></p></div>')
        },
        403: function () {
            $('#tender-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Просмотр открытых тендеров доступен только для юридических лиц и физических лиц предпринимателей, или по индивидуальному приглашению в закрытый тендер (для лиц любой формы) .</i></p></div>')

        }
    }
});

function sliderInit() {
    $('.bxsliderTender').bxSlider({
        slideWidth: 141,
        minSlides: 5,
        maxSlides: 5,
        slideMargin: 20
    });
}


// ----------------- BEGIN Propose sent -------------------------------------------------
$('#makePropose').on('click', function () {
    var Propose = {};
    Propose.body = $('#newsFormComments').val();
    Propose.hidden = $('#visionSelect').prop('checked');


    $.ajax({
        type: "POST",
        url: "/api/rest/tenderService/tender/id/" + tenderId + "/propose/create/",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(Propose),
        statusCode: {
            201: function () {
                window.location.href = '/tender/' + tenderId;
            }
        }
    });
});

// ----------------- END Propose sent -------------------------------------------------


// --------------------------------------------- Propose -------------------------------------------

var canBeMember;

function canBeMemberAjax() {
    $.ajax({
        type: "POST",
        url: "/api/rest/tenderService/tender/" + tenderId + "/user-check",
        async: false,
        success: function (data) {
            canBeMember = data;
        }
    });
}

$(".downComments").click(function () {

    if (isTenderHasWinner){
        alert("Тендер окончен. Победитель уже выбран.")
        return
    }

    canBeMemberAjax();
    if (canBeMember == true) {
        $(".downComments").hide('slow');
        $(".colNewsComments").show('slow');
        $(".colComments").css("width", "50%");
    } else {
        alert("Оставить предложение может только подтверждённы администратором пользователь типа 'юридическое лицо', у которого совпадает хотябы один КВЕД")
    }
});


//----- scrolup ----

$('#wantToComment').click(function () {
    $("html, body").animate({scrollTop: 900}, 600);
    return false;
});

//----- scrolup ----

