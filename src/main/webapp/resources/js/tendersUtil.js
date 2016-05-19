/**
 * Created by Юля on 18.05.2016.
 */

(function (namespace) {

    'use strict';
    var flag = window.flag || "",
        util = new TenderFilter(),
        param = {},
        firstTenderBlock = $('#tenders-start-block').html();

    function TenderFilter() {
        this.skip = 0;
        this.limit = 5;
        this.searchField = getUrlParam('name');
        this.createdDateSortDirection = "DESC";
    }

    function init() {
        $('#select-tender-status').change(onChangeTenderStatus);

        $('#tenderNextPage').on('click', function () {
            util.skip += 5;
            loadTenders();
        });

        setTenderStatus();
        loadTenders();
    }

    function loadTenders() {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/tenderService/tender/read/all/",
            data: JSON.stringify(util),
            statusCode: {
                200: function (response) {
                    drawTenders(response.entities);
                },
                204: function (response) {
                    alert('Тендеров больше нет');
                }
            }
        });
    }

    function drawTenders(data) {
        filterTenders(data);

        if (!data.length) alert('Тендеров больше нет');

        for (var i = 0; i < data.length; i++) {
            drawTenderInfo(data[i]);
        }

        $('.build-item-wrap').last().attr('style', 'display: none;');
    }

    function drawTenderInfo(tender) {
        var tenderBlock = $('.build-item-wrap').last().attr('style', 'display:;');
        console.log(tenderBlock);

        tender.body = tender.body.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', ""); // Clear description from HTML tags

        fillTenderImage(tender, tenderBlock);
        fillTenderContent(tender, tenderBlock);
        fillTenderBottom(tender, tenderBlock);

        $('#tenders-start-block').append(firstTenderBlock);
    }

    function fillTenderContent(tender, tenderBlock) {
        var content = tenderBlock.children('.content'),
             url = '/tender/' + tender.id;

        content.find('.build-publish-date span').text(localDateTime(tender.publishDate));
        content.find('.tender-status span').text(getTenderStatus(tender));
        if (!tender.tenderNumber) {
            content.children('.number').removeClass('visible');
        } else {
            content.find('.build-number').text(tender.tenderNumber);
        }
        content.children(".build-item-text").html(tender.body);
        content.children(".build-name-wrap").attr('href', url)
            .children(".build-name").text(tender.title);
    }

    function fillTenderImage(tender, tenderBlock) {
        var picBox = tenderBlock.children('.build-pic-wrap').attr('href', url),
            url = '/tender/' + tender.id;

        picBox.children('img').attr('src', findFirstImg(tender.files));
        picBox.find('.build-veiws span').text(tender.visited);
        picBox.find('.build-proposal-count span').text(tender.proposeNumber);
    }

    function fillTenderBottom(tender, tenderBlock) {
        var bottom = tenderBlock.children('.bottomContent'),
            url = '/tender/' + tender.id,
            dateEnd = localDateTimeUTC(tender.end);

        bottom.children(".sum")
            .text((tender.expectedPrice !== null) ? tender.expectedPrice + "₴" : "Ожидаемая сумма не указана");
        bottom.find(".build-link-wrap").attr('href', url);
        bottom.find(".build-end").text((dateEnd === 'Invalid date') ? 'Дата не указана' : dateEnd);
    }

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

    function redirectToTenderList() {
        var url = getUrlWithParameters();
        $.get(url, function () {
            window.location.href = url;
        });
    }

    function getUrlWithParameters() {
        var url = '/tenders?';

        for(var key in param) {
            url += key + '=' + param[key] + '&';
        }
        url = url.substring(0, url.length - 1);
        return url;
    }

    function setParamsToFilter() {
        $.extend(true, util, param);
        return util;
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
        console.log(data);
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
        reload();
        loadTenders();
    }

    function reload() {
        util.skip = 0;
        util.limit = 5;
        $('#tenders-start-block').empty().append(firstTenderBlock);
    }

    namespace.filter = util;
    namespace.init = init;

})(window.tenders = window.tenders || {});


