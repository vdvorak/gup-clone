(function (namespace) {

    'use strict';
    var flag = window.flag || "",
        util = new TenderFilter(),
        firstTenderBlock = $('#tenders-start-block').html();

    init();

    function TenderFilter() {
        this.skip = 0;
        this.limit = 5;
        this.searchField = getUrlParam('name');
        this.createdDateSortDirection = "DESC";
    }

    function init() {
        tenderFilter.parseURLParameters();
        setParamsToFilter(tenderFilter.parametersURI.filter);
        tenderFilter.fillParametersOnPage();

        $('#tenderNextPage').on('click', function () {
            util.skip += 5;
            loadTenders();
        });

        $('#btn-tenders-search').click(function(event) {
            reload();
            tenderFilter.searchTenders(event);
            setParamsToFilter(tenderFilter.parametersURI.filter);
            loadTenders();
        });

        loadTenders();
    }

    function loadTenders() {
        console.log(util);
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

    function setParamsToFilter(param) {
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
    }

    function reload() {
        util = new TenderFilter();
        $('#tenders-start-block').empty().append(firstTenderBlock);
    }

    namespace.filter = util;
    namespace.loadTenders = loadTenders;

})(window.tenders = window.tenders || {});

(function (namespace) {

    'use strict';
    var flag = window.flag || "",
        util = new DoerFilter(),
        firstDoersBlock = $('#doers-start-block').html();

    init();

    function DoerFilter() {
        this.skip = 0;
        this.limit = 5;
        this.searchField = getUrlParam('name');
        this.createdDateSortDirection = "DESC";
    }

    function init() {

        $('#doerNextPage').on('click', function () {
            util.skip += 5;
            loadDoers();
        })

        loadDoers();
    }

    function loadDoers() {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/doerService/doer/read/all/",
            data: JSON.stringify(util),
            statusCode: {
                200: function (response) {
                    drawDoers(response.entities);
                },
                204: function (response) {
                    alert('Исполнителей больше нет');
                }
            }
        });
    }

    function drawDoers(data) {

        for (var i = 0; i < data.length; i++) {
            drawDoerInfo(data[i]);
        }
        $('.build-item-wrap-2').last().attr('style', 'display: none;');
    }

    function drawDoerInfo(doer) {
        var doersBlock = $('.build-item-wrap-2').last().attr('style', 'display:;');

        var imgBlock = doersBlock.children(".build-pic-wrap-2").attr('href', '/doer/' + doer.id).first();
        imgBlock.find('img').attr('src', (doer.imageId && doer.imageId !== '')
            ? '/api/rest/fileStorage/DOER/file/read/id/' + doer.imageId
            : '/resources/images/doersLogo.png');
        imgBlock.find('a').attr('href', '/doer/' + doer.id);

        doersBlock.find(".build-publish-date-2").text(localDateTime(doer.dateOfCreate));
        doersBlock.find(".build-veiws-2").text(doer.countVisit);
        doersBlock.children(".build-item-text-2").html(doer.body);
        doersBlock.find(".build-name-2").text(doer.title);

        $('#doers-start-block').append(firstDoersBlock);
    }

    function reload() {
        util = new DoerFilter();
        $('#doers-start-block').empty().append(firstDoersBlock);
    }

    namespace.filter = util;
    namespace.loadDoers = loadDoers;

})(window.doers = window.doers || {});

// ------------------- End create default block of doers -------------------------------------------------------

$(document).ready(function () {

    $(window).on('scroll', function() {
        var x = location.href;
        if($(window).scrollTop() >= $('.footer').offset().top + $('.footer').outerHeight() - window.innerHeight) {
            if (x === 'http://localhost:8080/tenders' || x === 'http://localhost:8080/tenders#tabs1-tenders') {
                tenders.filter.skip += 5;
                tenders.loadTenders();
            } if (x === 'http://localhost:8080/tenders#tabs1-investment') {
                doers.filter.skip += 5;
                doers.loadDoers();
            }
        }
    });
});
