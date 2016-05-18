var firstTenderBlock = $('#tenders-start-block').html();
var firstDoerBlock = $('#doers-start-block').html();

var doersFO = {};
var tendersFO = {};
// ------------------- Create container of tenders -------------------------------------------------------

$(document).ready(function () {

    tendersFO.skip = 0;
    tendersFO.limit = 5;
    tendersFO.searchField = getUrlParam('name');

    setTenderStatus();

    function findFirstImg(arr) {
        var url = '/resources/images/no_photo.jpg';
        var imgId = '';
        if (arr) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].fileType === 'MAINIMAGE') {
                    imgId = arr[i].id;
                    url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
                    break;
                }
            }
        }
        return url;
    }

    function doAjax(filterOptions) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/tenderService/tender/read/all/",
            data: JSON.stringify(filterOptions),
            statusCode: {
                200: function (response) {
                    draw(response.entities);
                },
                204: function (response) {
                    alert('Тендеров больше нет');
                }
            }
        });
    }

    function filterFinishedTenders(data) {
        var arr = [];
        for (var i = 0; i < data.length; i++) {
            if (data[i].winnerId !== null) arr.push(data[i]);
        }
        refillArrayWithTenders(data, arr);
    }

    function filterOutdatedTenders(data) {
        var arr = [];
        for (var i = 0; i < data.length; i++) {
            if (data[i].winnerId === null && data[i].end < Date.now() / 1000) arr.push(data[i]);
        }
        refillArrayWithTenders(data, arr);
    }

    function filterActiveTenders(data) {
        var arr = [];
        for (var i = 0; i < data.length; i++) {
            if (data[i].winnerId === null && data[i].end > Date.now() / 1000) arr.push(data[i]);
        }
        refillArrayWithTenders(data, arr);
    }

    function refillArrayWithTenders(data, arr) {
        data.splice(0, data.length);

        for (var i = 0; i < arr.length; i++) {
            data.push(arr[i]);
        }
    }

    function getTenderStatus(tender) {
        var status = '';
        if(tender.winnerId === null && tender.end < Date.now() / 1000) {
            status = 'Не состоялся';
        } else if(tender.winnerId !== null) {
            status = 'Завершен';
        } else if(tender.winnerId === null && tender.end > Date.now() / 1000) {
            status = 'Приём предложений';
        }
        return status;
    }

    function filterTenders(data) {
        var status = $('#select-tender-status').val();
        if (status === 'finished') {
            filterFinishedTenders(data);
        } else if (status === 'outdated') {
            filterOutdatedTenders(data);
        } else if (status === 'active') {
            filterActiveTenders(data);
        }
    }

    function setTenderStatus() {
        if ($('#tabs1-tenders').hasClass('active')) {
            var tenderStatus = getUrlParam('status');
            if (tenderStatus) {
                $('#select-tender-status option[value="' + tenderStatus + '"]').prop("selected", true);
            } else {
                $('#select-tender-status option[value="all"]').prop("selected", true);
            }
        } else {
            $('#select-tender-status option[value="all"]').prop("selected", true);
        }
    }

    function onChangeTenderStatus() {

        tendersFO.skip = 0;
        tendersFO.limit = 5;
        tendersFO.searchField = getUrlParam('name');

        $('#tenders-start-block').empty().append(firstTenderBlock);

        doAjax(tendersFO);
    }

    $('#select-tender-status').change(onChangeTenderStatus);

    function draw(data) {
        filterTenders(data);

        if (!data.length) alert('Тендеров больше нет');
        for (var i = 0; i < data.length; i++) {
            var url = '/tender/' + data[i].id;
            data[i].body = data[i].body.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', ""); // Clear description from HTML tags
            $('.build-item-wrap').last().attr('style', 'display:;');
            $(".build-pic-wrap img").last().attr('src', findFirstImg(data[i].files));
            $(".build-pic-wrap").last().attr('href', url);
            $(".build-link-wrap").last().attr('href', url);
            $(".build-item-text").last().html(data[i].body);
            if(!data[i].tenderNumber) $(".number.visible").removeClass('visible');
            $(".build-number").last().text(data[i].tenderNumber);
            $(".build-publish-date span").last().text(localDateTime(data[i].publishDate));
            $(".build-veiws span").last().text(data[i].visited);
            $(".build-proposal-count span").last().text(data[i].proposeNumber);
            $(".tender-status span").last().text(getTenderStatus(data[i]));

            if (data[i].expectedPrice != null) {
                $(".sum").last().text(data[i].expectedPrice + "₴");
            } else {
                $(".sum").last().text("Ожидаемая сумма не указана");
            }

            $(".build-name-wrap").last().attr('href', url);
            $(".build-name").last().text(data[i].title);
            var dateEnd = localDateTimeUTC(data[i].end);
            $(".build-end").last().text((dateEnd === 'Invalid date') ? 'Дата не указана' : dateEnd);
            $('#tenders-start-block').append(firstTenderBlock);
        }

        $('.build-item-wrap').last().attr('style', 'display: none;');
    }

    doAjax(tendersFO);

    $('#tenderNextPage').on('click', function () {
        tendersFO.skip += 5;
        doAjax(tendersFO);
    })

// ------------------- End create default block of tenders -------------------------------------------------------
});

// ------------------- Create container of doers -------------------------------------------------------
$(document).ready(function () {

    doersFO.skip = 0;
    doersFO.limit = 5;
    doersFO.searchField = getUrlParam('name');

    function doAjax(filterOptions) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/doerService/doer/read/all/",
            data: JSON.stringify(filterOptions),
            statusCode: {
                200: function (response) {
                    draw(response.entities);
                }
            }
        });
    }

    function draw(data) {
        for (var i in data) {
            $('.build-item-wrap-2').last().attr('style', 'display:;');

            if (data[i].imageId && data[i].imageId !== '') {
                $(".build-pic-wrap-2 img").last().attr('src', '/api/rest/fileStorage/DOER/file/read/id/' + data[i].imageId);
            } else {
                $(".build-pic-wrap-2 img").last().attr('src', '/resources/images/no_photo.jpg');
            }

            $(".build-pic-wrap-2 a").last().attr('href', '/doer/' + data[i].id);
            $(".build-item-text-2").last().html(data[i].body);
            $(".build-publish-date-2").last().text(localDateTime(data[i].dateOfCreate));
            $(".build-publish-date-update-2 span").last().text(localDateTime(data[i].dateOfUpdate));
            $(".build-veiws-2").last().text(data[i].countVisit);
            $(".build-name-2").last().text(data[i].title);
            $('#doers-start-block').append(firstDoerBlock);
        }

        $('.build-item-wrap-2').last().attr('style', 'display: none;');
    }

    doAjax(doersFO);

    $('#doerNextPage').on('click', function () {
        doersFO.skip += 5;
        doAjax(doersFO);
    })

});
// ------------------- End create default block of doers -------------------------------------------------------

$(document).ready(function () {

    $(window).on('scroll', function() {
        var x = location.href;
        if($(window).scrollTop() >= $('.footer').offset().top + $('.footer').outerHeight() - window.innerHeight) {
            if (x === 'http://localhost:8080/tenders' || x === 'http://localhost:8080/tenders#tabs1-tenders') {
                tendersFO.skip += 5;
                doAjax(tendersFO);
            } if (x === 'http://localhost:8080/tenders#tabs1-investment') {
                doersFO.skip += 5;
                doAjax(doersFO);
            }
        }
    });
});