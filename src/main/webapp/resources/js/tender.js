
var proposes;
var firstBlock = $('#start').html();

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
    for (var i in arr) {
        if (arr[i] === 'image') {
            imgId = i;
            url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
            var element = '<li><img src="' + url + '" /></li>';
            $('.bxsliderTender').append(element)
        }
    }
}

function localDateTime(long) {
    long = (new Date(parseInt(long)));
    long = moment(long).locale("ru").format('LLL');
    return long;
}

$.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    url: "/api/rest/tenderService/tender/read/id/" + tenderId,
    success: function (response) {
        var data = response;

        sliderImg(data.uploadFilesIds);
        $(".tender-item-text").last().html(data.body);
        $(".tender-number").last().text(data.tenderNumber);
        $(".tender-publish-date span").last().text(localDateTime(data.begin));
        $(".tender-veiws span").last().text(data.visited);
        $(".tender-proposal-count span").last().text(data.proposeNumber);
        $(".tender-expectedPrice span").last().text(data.expectedPrice);
        $(".tender-name").last().text(data.title);


        $(".date-finish").last().text(localDateTime(data.end));

// ------------------------- Propose bulid block ---------------------------------------------------------------------
        for (var i in data.proposes) {
            $('#commentStart').append($('.comments').last().clone());
            $(".propose-author").last().text(data.proposes[i].authorId);
            $(".propose-date span").last().text(localDateTime(data.proposes[i].time));
            console.log()
            $(".poropse-text").last().text(data.proposes[i].body);
            $(".chooseWinner").last().attr('id', data.proposes[i].authorId);
        }
        $('.comments').first().remove();
// ------------------------- Propose bulid block ---------------------------------------------------------------------




        $(".chooseWinner").on('click', function () {
            alert($(this).attr('id'));

            var Tender = {};
            Tender.winnerId = $(this).attr('id');
            Tender.id = tenderId;
            alert(Tender.winnerId);
            $.ajax({
                type: "POST",
                url: "/api/rest/tenderService/tender/chooseWinner",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(Tender),
                statusCode: {
                    200: function () {
                        alert("Победитель выбран!")
                    }
                }
            });
        });


        $('.proposes-wraper').last().attr('style', 'display: none;');

        for (var j in data.members) {
            $('.propose-author').each(function (index) {
                if ($(this).text() === data.members[j].id) {
                    $(this).text(data.members[j].name);
                    $(this).next('.member-pic').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + data.members[j].userPic)
                }
            });
        }
    },
    statusCode: {
        403: function () {
            $('.tender-tabs-items-wrap').detach();
            $('.tender-wrap').text("Войдите в систему, чтобы просмотреть информацию о тендере.");
        }
    }
});


$(document).ready(function () {
    //$('.slider1').bxSlider({
    //    slideWidth: 200,
    //    minSlides: 2,
    //    maxSlides: 3,
    //    slideMargin: 5
    //});

    $('.bxsliderTender').bxSlider({
        slideWidth: 141,
        minSlides: 5,
        maxSlides: 5,
        slideMargin: 20
    });


});

// ----------------- BEGIN Propose sent -------------------------------------------------
$('#makePropose').on('click', function () {
    var Propose = {};
    Propose.body = $('#newsFormComments').val();
    Propose.hidden = $('#visionSelect').prop('checked');

   alert(JSON.stringify(Propose));



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

$('#wantToComment').click(function(){
    $("html, body").animate({ scrollTop: 900 }, 600);
    return false;
});

//----- scrolup ----
