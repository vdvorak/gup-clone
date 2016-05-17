var loadedBlogPost = {};

$.ajax({
    type: "POST",
    url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/read",
    statusCode: {
        200: function (blogPost) {
            loadedBlogPost = blogPost;
            $('#bpViewsNum').append(blogPost.views);
            var createdDate = new Date(blogPost.createdDate);
            $('#bpCreatedDate').append(createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear());
            $('#bpTitle').append(blogPost.title);
            $('#bpLikeNum').append(blogPost.totalLikes);
            $('#bpDislikeNum').append(blogPost.totalDislikes);
            $('#bpText').append(blogPost.text);

            if (loggedInProfile) {
                if (loggedInProfile.id === blogPost.authorId) {
                    $("<a class='editBlogPost' href='/blog-post/edit/" + blogPost.id + "'><span>Редактировать статью </span></a>").insertAfter($('.newsRating'))
                }
            }

            blogPost.comments.forEach(function (comment) {
                $.ajax({
                    type: "POST",
                    url: "/api/rest/profilesService/profile/read/id/" + comment.fromId,
                    statusCode: {
                        200: function (profile) {
                            var profileImgTag = '<img ';
                            if (profile.imgId) {
                                profileImgTag += 'src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId + '?cachedImage=1"';
                            } else {
                                profileImgTag += 'src="/resources/images/no_photo.jpg"';
                            }
                            profileImgTag += ' width="52px" height="52px" alt="logo">';

                            $('#commentsBlock').append(
                                '<div class="comments">' +
                                '<a href="/profile?id=' + profile.id + '">' + profileImgTag + '</a>' +
                                '<a class="NameUser" href="/profile?id=' + profile.id + '">' + profile.username + '</a>' +
                                '<span> ' + localDateTime(comment.createdDate) + '</span> <p class="commentUser">' + comment.comment + '</p>' +
                                '</div>');
                        }
                    }
                });
            });
        }
    }
});

$('#newsLike').on('click', function () {
    if (typeof loggedInProfile != 'undefined') {
        if ($.inArray(loggedInProfile.id, loadedBlogPost.likedIds) == -1) {
            if ($.inArray(loggedInProfile.id, loadedBlogPost.dislikedIds) != -1 && loadedBlogPost.totalDislikes > 0) {
                $('#bpDislikeNum').text(--loadedBlogPost.totalDislikes);
                loadedBlogPost.dislikedIds = $.grep(loadedBlogPost.dislikedIds, function (value) {
                    return value != loggedInProfile.id;
                });
            }

            $.ajax({
                type: "POST",
                url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/like",
                statusCode: {
                    200: function () {
                        $('#bpLikeNum').text(++loadedBlogPost.totalLikes);
                        loadedBlogPost.likedIds.push(loggedInProfile.id);
                    },
                    409: function () {
                    }
                }
            });
        }
    } else {
        alert("Чтобы проголосовать сначала нужно авторизироваться.")
    }
});

$('#newsDislike').on('click', function () {
    if (typeof loggedInProfile != 'undefined') {
        if ($.inArray(loggedInProfile.id, loadedBlogPost.dislikedIds) == -1) {
            if ($.inArray(loggedInProfile.id, loadedBlogPost.likedIds) != 1 && loadedBlogPost.totalLikes > 0) {
                $('#bpLikeNum').text(--loadedBlogPost.totalLikes);
                loadedBlogPost.likedIds = $.grep(loadedBlogPost.likedIds, function (value) {
                    return value != loggedInProfile.id;
                });
            }

            $.ajax({
                type: "POST",
                url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/dislike",
                statusCode: {
                    200: function () {
                        $('#bpDislikeNum').text(++loadedBlogPost.totalDislikes);
                        loadedBlogPost.dislikedIds.push(loggedInProfile.id);
                    },
                    409: function () {
                    }
                }
            });
        }
    } else {
        alert("Чтобы проголосовать сначала нужно авторизироваться.")
    }
});

$('#sendNewsComment').on('click', function () {
    var comment = {
        'comment': $('#newsFormComments').val(),
        'toId': blogPostId
    };

    $.ajax({
        type: "POST",
        url: "/api/rest/newsService/blogPost/id/" + blogPostId + "/comment/create",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(comment),
        statusCode: {
            201: function () {
                location.reload();
            }
        }
    });
});

$(".NewsTabsFilterItem").on('click', function () {
    $('.intro').removeClass("intro");
    $(this).addClass("intro");
});

$('#newsFormComments').keyup(function () {
    var maxLength = 1000;
    var length = maxLength - $(this).val().length;
    $('#chars').text(length + ' символов осталось');
});

$(".downComments").click(function () {
    if (typeof loggedInProfile != 'undefined') {
        $(".downComments").hide('slow');
        $(".colNewsComments").show('slow');
        $(".colComments").css("width", "50%");
    } else {
        alert("Чтобы оставить комментарий сначала нужно авторизироваться.")
    }
});

$(".comments").click(function () {
    if ($('.backgroundColorComment').is(':visible')) {
        return $('.backgroundColorComment').removeClass("backgroundColorComment");
    } else {
        $(this).addClass("backgroundColorComment");
    }
});
