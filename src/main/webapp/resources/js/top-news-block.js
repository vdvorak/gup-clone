var blogPostFO = {};
blogPostFO.createdDateSortDirection = "DESC";
blogPostFO.skip = 0;
blogPostFO.limit = 10;

loadTopNews(blogPostFO);

function loadTopNews(blogPostFO) {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/newsService/blogPost/read/all",
        data: JSON.stringify(blogPostFO),
        success: function (response) {
            $(document).ready(function () {
                var blogPosts = response.entities;
                for (var i = 0; i < blogPosts.length; i++) {
                    var blogPostURL = '/blog-post/view/' + blogPosts[i].id;

                    var createdDate = new Date(blogPosts[i].createdDate);
                    blogPosts[i].createdDate = createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear();

                    var  imagePreviewTag = '';
                    if (blogPosts[i].imagesIds !== null) {
                        for (var key in blogPosts[i].imagesIds) {
                            if (blogPosts[i].imagesIds[key] === "1") {
                                imagePreviewTag = '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" alt="tidi"  width="169" height="126">';
                            }
                        }
                    } else {
                        imagePreviewTag = '<img src="/resources/images/no_photo.jpg" alt="tidi" width="169" height="126">';
                    }

                    if (i % 2 === 0) {
                        $('#nextUserNews').append(
                            '<div class="tidi-drop">' +
                                '<div class="tidi4">' +
                                    '<p class="data">' +
                                        'Опубликовано: ' + blogPosts[i].createdDate +
                                    '</p>' +
                                    '<a href="' + blogPostURL +'">' +
                                        imagePreviewTag +
                                    '</a>' +
                                    '<a class="tidi4-a" href="' + blogPostURL +'">' +
                                        blogPosts[i].title +
                                    '</a>' +
                                    '<p class="tidi4-p">' +
                                        blogPosts[i].text.substring(0, 136) +
                                        '<a class="tidi4-a2" href="' + blogPostURL +'">' +
                                            'Продолжение ->' +
                                        '</a>' +
                                    '</p>' +
                                '</div>' +
                            '</div>');
                    } else {

                    }
                }
            });
        }
    });
}



//    <div class="tidi4">
//    <p class="data-tidi5">Опубликовано: 22.10.16</p>
//<a class="tidi4-ar" href="#">MONARCHY</a>
//    <a class="tidi5-img" href="#"><img src="/resources/images/tidi5.png" alt="tidi"></a>
//    <p class="tidi4-pr">Monarchy — английский музыкальный электронный дуэт из Лондона, состоящий из Эндрю Армстронга и Ра Блэка. Ранее были известны как Milke.. <a class="tidi4-a2" href="#">Продолжение -></a></p>
//    </div>