var blogPostFO = {};
blogPostFO.createdDateSortDirection = "DESC";
blogPostFO.skip = 0;
blogPostFO.limit = 10;

$.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    url: "/api/rest/newsService/blogPost/read/all",
    data: JSON.stringify(blogPostFO),
    success: function (response) {
        $(document).ready(function () {
            var data = response.entities;
            for (var i = 0; i < data.length; i++) {
                var createdDate = new Date(data[i].createdDate);
                data[i].createdDate = createdDate.getDate() + '/' + (createdDate.getMonth() + 1) + '/' + createdDate.getFullYear();

                var  imagePreviewTag = '';
                if (data[i].imagesIds !== null) {
                    for (var key in data[i].imagesIds) {
                        if (data[i].imagesIds[key] === "1") {
                            imagePreviewTag = '<img src="/api/rest/fileStorage/NEWS/file/read/id/' + key + '" width="200" height="200">';
                        }
                    }
                } else {
                    imagePreviewTag = '<img src="/resources/images/no_photo.jpg" width="200" height="200">';
                }

                $('#newsContainer').append(
                    '<div class="main-news-item-wrap">' +
                    imagePreviewTag +
                    '<div class="main-news-text">' +
                    '<span class="main-news-title">' + data[i].title + '</span>' +
                    data[i].text +
                    '</div>' +
                    '<div class="main-news-subtitles">' +
                    '<div class="main-news-view">' + 'Просмотров: ' + data[i].views + '<span></span></div>' +
                    '<div class="main-news-date">' + 'Дата публикации:' + data[i].createdDate + '<span></span></div>' +
                    '</div>' +
                    '</div>');
            }
        });
    }
});