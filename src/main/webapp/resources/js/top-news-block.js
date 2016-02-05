var blogPostFO = {};
blogPostFO.createdDateSortDirection = "DESC";
blogPostFO.skip = 0;
blogPostFO.limit = 5;

loadAndAppendTopUserNews(blogPostFO);
loadAndAppendNextUserNews(blogPostFO);
loadAndAppendNextUserNews(blogPostFO);
loadAndAppendNextUserNews(blogPostFO);


function getNewsResponse(blogPostFO) {
    return $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/newsService/blogPost/read/all",
        data: JSON.stringify(blogPostFO)
    })
}

function getBlogPostUrl(blogPost) {
    return '/blog-post/view/' + blogPost.id;
}

function getImagePreviewTag(blogPost) {
    if (blogPost.imagesIds !== null) {
        for (var key in blogPost.imagesIds) {
            if (blogPost.imagesIds[key] === "1") {
                return '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" alt="tidi"  width="169" height="126">';
            }
        }
    } else {
        return '<img src="/resources/images/no_photo.jpg" alt="tidi" width="169" height="126">';
    }
}

function getReadableDate(blogPost) {
    var createdDate = new Date(blogPost.createdDate);
    return createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear();
}

function appendNewsWithLeftImg(elementId, blogPostURL, createdDate, imagePreviewTag, title, text) {
    $('#' + elementId).append(
        '<div class="tidi4">' +
        '<p class="data">' +
        'Опубликовано: ' + createdDate +
        '</p>' +
        '<a href="' + blogPostURL +'">' +
        imagePreviewTag +
        '</a>' +
        '<a class="tidi4-a" href="' + blogPostURL +'">' +
        title +
        '</a>' +
        '<p class="tidi4-p">' +
        text.substring(0, 136) +
        '<a class="tidi4-a2" href="' + blogPostURL +'">' +
        'Продолжение ->' +
        '</a>' +
        '</p>' +
        '</div>');
}

function appendNewsWithRightImg(elementId, blogPostURL, createdDate, imagePreviewTag, title, text) {
    $('#' + elementId).append(
        '<div class="tidi4">' +
        '<p class="data-tidi5">' +
        'Опубликовано: ' + createdDate +
        '</p>' +
        '<a class="tidi5-img" href="' + blogPostURL +'">' +
        imagePreviewTag +
        '</a>' +
        '<a class="tidi4-ar" href="' + blogPostURL +'">' +
        title +
        '</a>' +
        '<p class="tidi4-pr">' +
        text.substring(0, 136) +
        '<a class="tidi4-a2" href="' + blogPostURL +'">' +
        'Продолжение ->' +
        '</a>' +
        '</p>' +
        '</div>');
}

function loadAndAppendTopUserNews() {
    var promise = getNewsResponse(blogPostFO);

    promise.success(function (data) {
        var blogPosts = data.entities;

        for (var i = 0; i < blogPosts.length; i++) {
            var blogPostURL = getBlogPostUrl(blogPosts[i]);
            var createdDate = getReadableDate(blogPosts[i]);
            var imagePreviewTag = getImagePreviewTag(blogPosts[i]);

            if (i % 2 === 0) {
                appendNewsWithLeftImg('topUserNews', blogPostURL, createdDate, imagePreviewTag, blogPosts[i].title, blogPosts[i].text);
            } else {
                appendNewsWithRightImg('topUserNews', blogPostURL, createdDate, imagePreviewTag, blogPosts[i].title, blogPosts[i].text);
            }
        }
    });
}

function loadAndAppendNextUserNews() {

    if (blogPostFO.skip == 0) {
        blogPostFO.skip = blogPostFO.limit;
        blogPostFO.limit = 1;
    } else {
        blogPostFO.skip += 1;
    }

    var promise = getNewsResponse(blogPostFO);

    promise.success(function (data) {
        var blogPosts = data.entities;

        for (var i = 0; i < blogPosts.length; i++) {
            var blogPostURL = getBlogPostUrl(blogPosts[i]);
            var createdDate = getReadableDate(blogPosts[i]);
            var imagePreviewTag = getImagePreviewTag(blogPosts[i]);

            if (i % 2 === 0) {
                appendNewsWithLeftImg('nextUserNews', blogPostURL, createdDate, imagePreviewTag, blogPosts[i].title, blogPosts[i].text);
            } else {
                appendNewsWithRightImg('nextUserNews', blogPostURL, createdDate, imagePreviewTag, blogPosts[i].title, blogPosts[i].text);
            }
        }
    });
}

function loadAndAppendAdNews() {
    //oskdskdkkk
    //oskdskdkkk
    //oskdskdkkk
}