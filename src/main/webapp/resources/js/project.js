$("#selectedService option[value='project']").attr("selected","selected");

$(document).ready(function(){
    var selector = '.projectContent .contentHeader .additionalPhotos .photo';
    $(selector).on('click', function(){
        $(selector + '.full').attr('src', $(this).attr('src'))
    })
});

var projectId = getUrlParam('id');

loadAndAppendProject(projectId);

function loadAndAppendProject(projectId) {
    loadProjectById(projectId).statusCode({
        200: function (project) {
            appendProjectBlock(project);
        }
    });
}

function appendProjectBlock(project) {
    $('#projCreatedDate').append(getReadableDate(project.createdDate));
    $('#projViewsNum').append(project.views);
    $('#projName').append(project.title);
    $('#projText').append(project.description);
    $('#projProgress').css('width', getInvertedProgressNum(project.investedAmount, project.amountRequested) + '%');
    $('#investedAmount').append(project.investedAmount + ' ₴ ');
    $('#requestedAmount').append(project.amountRequested + ' ₴ ');
    $('#commentsNum').append(project.totalComments);

    for (var imgId in project.imagesIds) {
        appendProjectImage(imgId, project.imagesIds[imgId]);
    }
    setAuthorContent(project.authorId);
    setProjectCommentsBlock(project.comments);
}

function setProjectCommentsBlock(projectComments) {
    projectComments.forEach(function(comment) {
        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/read/id/" + comment.fromId,
            statusCode: {
                200: function (profile) {
                    var profileImgTag = '<img ';
                    if (profile.contact.pic != null && profile.contact.pic != '') {
                        profileImgTag += 'src="/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic + '?cachedImage=1"';
                    } else {
                        profileImgTag += 'src="/resources/images/no_photo.jpg"';
                    }
                    profileImgTag += ' width="52px" height="52px" alt="logo">';

                    $('#commentsBlock').append(
                        '<div class="comments">' +
                        '<a href="/profile/id/' + profile.id + '">' + profileImgTag + '</a>' +
                        '<a class="NameUser" href="/profile/id/' + profile.id + '">' + profile.username + '</a>' +
                        '<p class="commentUser">' +  comment.comment + '</p>' +
                        '</div>');
                }
            }
        });
    });
}

function setAuthorContent(profileId) {
    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/id/" + profileId,
        statusCode: {
            200: function (profile) {
                if (profile.contact.pic != null && profile.contact.pic != '') {
                    $('#projCreatorPhoto').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.contact.pic);
                } else {
                    $('#projCreatorPhoto').attr('src', '/resources/images/no_photo.jpg');
                }

                if (profile.username != null) {
                    $('#authorName').text(profile.username);
                }
            }
        }
    });
}

function appendProjectImage(imgId, imgKey) {
    var imgTag = '<img class="photo ';
    imgTag += (imgKey === "1") ? 'full" ' : '" ';
    imgTag += 'src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + imgId + '" >';

    $('#projImages').append(imgTag);
}

$('#sendProjComment').on('click', function () {
    var comment = {
        'comment' : $('#projectsFormComments').val(),
        'toId' : ""
    };

    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/comment/create",
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

$('#projectsFormComments').keyup(function() {
    var maxLength = 1000;
    var length = maxLength - $(this).val().length;
    $('#chars').text(length + ' символов осталось');
});

$(".downComments").click(function(){
    if (typeof loggedInProfile != 'undefined') {
        $(".downComments").hide('slow');
        $(".colNewsComments").show('slow');
        $(".colComments").css("width", "50%");
    } else {
        alert("Чтобы оставить комментарий сначала нужно авторизироваться.")
    }
});

$(".comments").click(function(){
    if ($('.backgroundColorComment').is(':visible') ) {
        return $('.backgroundColorComment').removeClass("backgroundColorComment");;
    } else {
        $(this).addClass("backgroundColorComment");
    }
});