var firstBlockBlog = $('#startBlockOfBlogs').html();
var firstBlockNews = $('#startBlockOfNews').html();

var urlGetBlog = '/api/rest/newsService/blog/read/all';
var urlGetNews = '/api/rest/newsService/blogPost/read/all';


// ------------------- Create default block blogs and news -------------------------------------------------------

$(document).ready(function () {

    $('#newsTab').on('click', function () {
        $('[name="getNextImg"]').attr('id', 'nextPageNews');
    });

    $('#blogsTab').on('click', function () {
        $('[name="getNextImg"]').attr('id', 'nextPageBlog');
    });

    var blogsFO = {};
    blogsFO.skip = 0;
    blogsFO.limit = 5;
    blogsFO.searchField = getUrlParam('name');

    function findFirstImgBlog(pic) {
        var url = '/resources/images/no_photo.jpg';
        if (pic.length > 0) {
            return url = '/api/rest/fileStorage/NEWS/file/read/id/' + pic;
        }
        return url
    }

    function findFirstImgNews(arr) {
        var url = '/resources/images/no_photo.jpg';
        var imgId = '';

        for (var i in arr) {
            if (arr[i] === 'pic1') {
                imgId = i;
                url = '/api/rest/fileStorage/NEWS/file/read/id/' + imgId;
                break;
            }
        }
        return url;
    }

    function doAjax(filterOptions, url, whatDraw) {

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: url,
            data: JSON.stringify(filterOptions),
            success: function (response) {
                if (response) {
                    if (whatDraw === 'blogs') {
                        drawBlog(response.entities);
                    }
                    if (whatDraw === 'news') {
                        drawNews(response.entities);
                    }
                }
            }
        });
    }

    function drawBlog(data) {
        for (var i in data) {
            $('.blogs').last().attr('style', 'display:;');
            $(".blogs-img").last()
                .attr('src', findFirstImgBlog(data[i].imageId))
                .attr('alt', data[i].title)
                .parent()
                .attr('href', '/blog/' + data[i].seoUrl);
            $(".blogs a").last().attr('href', '/blog/' + data[i].seoUrl);
            $(".text-blogs").last().text(data[i].description);
            $(".DateOfCreation-blogs-num").last().append(localDateTime(data[i].createdDate));
            $(".nameBlogs").last().text(data[i].title);
            $('#startBlockOfBlogs').append(firstBlockBlog);
        }
        $('.blogs').last().attr('style', 'display: none;');
    }

    doAjax(blogsFO, urlGetBlog, 'blogs');

    //---------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------

    var newsFO = {};
    var address = {};
    newsFO.skip = 0;
    newsFO.limit = 5;
    newsFO.searchField = getUrlParam('name');
    newsFO.blogId = getUrlParam('blogId');
    newsFO.createdDateSortDirection = "DESC";
    if (getUrlParam('category')) newsFO.categories = [getUrlParam('category')];

    $(".NewsTabsFilterItem").on('click', function () {
        $('.intro').removeClass("intro");
        $(this).addClass("intro");
        address.city = $('.intro').text();
        newsFO.address = address;
        $("div.normalNews").remove();
        newsFO.skip = 0;
        newsFO.limit = 5;
        doAjax(newsFO, urlGetNews, 'news');
//            setTimeout(function() {alert("timout gone");doAjax(newsFO, urlGetNews, 'news');}, 1000);
    });

    function drawNews(data) {
        for (var i = 0; i < data.length; i++) {
            data[i].text = data[i].text.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', "").replace(/&nbsp;/g, ' ');
            if ($('.normalNews').length == 0) {
                $('#startBlockOfNews').append(firstBlockNews);
            }
            $('.normalNews').last().attr('style', 'display:;');
            $(".news-img").last()
                .attr('src', findFirstImgNews(data[i].imagesIds))
                .attr('alt', data[i].title)
                .parent()
                .attr('href', '/blog-post/' + data[i].seoUrl);
            $(".normalNews a").last().attr('href', '/blog-post/' + data[i].seoUrl);
            $(".descriptionNormalNews2").last().text(data[i].text);  // - описание
            $(".normalNews-p2").last().append(localDateTime(data[i].createdDate)); // - дата создания
            $(".descriptionNormalNews").last().text(data[i].title);  // - заголовок
            $(".normalNews-p").last().append(data[i].views);  // - просмотры
            $(".normalNews-p3").last().append(data[i].totalComments);  // - комментарии
            $('#startBlockOfNews').append(firstBlockNews);
        }
        $('.normalNews').last().attr('style', 'display: none;');
    }

    doAjax(newsFO, urlGetNews, 'news');

    var waypoints = $('#nextPageNews').waypoint(function (direction) {
        if (direction == 'up') {
            return
        }
        if ($('#tabs1-news').is(':visible')) {
            newsFO.skip += 5;
            doAjax(newsFO, urlGetNews, 'news');
        }
        if ($('#tabs1-blogs').is(':visible')) {
            blogsFO.skip += 5;
            doAjax(blogsFO, urlGetBlog, 'blogs');
        }
    }, {
        offset: '90%'
    })

});
// ------------------- End create default block of blogs and news -------------------------------------------------------