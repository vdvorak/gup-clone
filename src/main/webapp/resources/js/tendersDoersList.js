var firstTenderBlock = $('#tenders-start-block').html();
var firstDoerBlock = $('#doers-start-block').html();
// ------------------- Create container of tenders -------------------------------------------------------

$(document).ready(function () {

    var tendersFO = {};
    tendersFO.skip = 0;
    tendersFO.limit = 5;

    function findFirstImg(arr) {
        var url = '/resources/images/no_photo.jpg';
        var imgId = '';
        for (var i in arr) {
            if (arr[i] === 'pic1') {
                imgId = i;
                url = '/api/rest/fileStorage/TENDER/file/read/id/' + imgId;
                break;
            }
        }
        return url;
    }

    function localDateTime(long) {
        long = new Date(parseInt(long));
        long = moment(long).locale("ru").format('LLL');
        return long;
    }

    function doAjax(filterOptions) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/tenderService/tender/read/all/",
            data: JSON.stringify(filterOptions),
            success: function (response) {
                draw(response.entities);
            }
        });
    }

    function draw(data) {
        for (var i in data) {
            var url = '/tender/' + data[i].id;
            data[i].body = data[i].body.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', ""); // Clear description from HTML tags
            $('.build-item-wrap').last().attr('style', 'display:;');
            $(".build-pic-wrap img").last().attr('src', findFirstImg(data[i].uploadFilesIds));
            $(".build-pic-wrap").last().attr('href', url);
            $(".build-link-wrap").last().attr('href', url);
            $(".build-item-text").last().html(data[i].body);
            $(".build-number").last().text(data[i].tenderNumber);
            $(".build-publish-date span").last().text(localDateTime(data[i].begin));
            $(".build-veiws span").last().text(data[i].visited);
            $(".build-proposal-count span").last().text(data[i].proposeNumber);
            $(".build-sum").last().text(data[i].expectedPrice);
            $(".build-name-wrap").last().attr('href', url);
            $(".build-name").last().text(data[i].title);
            $(".build-end").last().text(localDateTime(data[i].end));
            $('#tenders-start-block').append(firstTenderBlock);
        }

        $('.build-item-wrap').last().attr('style', 'display: none;');
    }

    doAjax(tendersFO);

    $('#tenderNextPage').on('click', function () {
        tendersFO.skip += 5;
        doAjax(tendersFO);
    })

});
// ------------------- End create default block of tenders -------------------------------------------------------



// ------------------- Create container of doers -------------------------------------------------------

$(document).ready(function () {

    var doersFO = {};
    doersFO.skip = 0;
    doersFO.limit = 5;

    function localDateTime(long) {
        long = new Date(parseInt(long));
        long = moment(long).locale("ru").format('LLL');
        return long;
    }

    function doAjax(filterOptions) {
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "/api/rest/doerService/doer/read/all/",
            data: JSON.stringify(filterOptions),
            success: function (response) {
                draw(response.entities);
            }
        });
    }

    function draw(data) {
        for (var i in data) {
            $('.build-item-wrap-2').last().attr('style', 'display:;');

            if (data[i].imageId && data[i].imageId !== ''){
                $(".build-pic-wrap-2 img").last().attr('src', '/api/rest/fileStorage/DOER/file/read/id/' + data[i].imageId);
            }else{
                $(".build-pic-wrap-2 img").last().attr('src', '/resources/images/no_photo.jpg' );
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