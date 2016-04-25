var blogPostFO = {};
blogPostFO.createdDateSortDirection = "DESC";
blogPostFO.skip = 0;
blogPostFO.limit = 5;

var isLeftImgLocation = true;

loadAndAppendTopUserNews(blogPostFO);


$("#tidiDown").click(function(){
    loadAndAppendNextUserNews(blogPostFO);
});

function getNewsResponse(blogPostFO) {
    return $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/newsService/blogPost/read/all",
        data: JSON.stringify(blogPostFO)
    })
}

function getBlogPostUrl(blogPost) {
    return '/blog-post/view/id/' + blogPost.id;
}

function getBlogPostImagePreviewTag(blogPost) {
   for (var imgId in blogPost.imagesIds) {
       if (blogPost.imagesIds[imgId] === "pic1") {
           return '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + imgId + '"  width="169" height="126">'
       }
   }

   return '<img src="/resources/images/no_photo.jpg" width="169" height="126">';
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
            var imagePreviewTag = getBlogPostImagePreviewTag(blogPosts[i]);

            blogPosts[i].text = blogPosts[i].text.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', "");

            if (isLeftImgLocation) {
                appendNewsWithLeftImg('topUserNews', blogPostURL, createdDate, imagePreviewTag, blogPosts[i].title, blogPosts[i].text);
                isLeftImgLocation = false;
            } else {
                isLeftImgLocation = true;
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

    loadAndAppendTopUserNews();
}