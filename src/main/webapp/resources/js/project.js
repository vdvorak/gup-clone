$("#selectedService option[value='project']").attr("selected", "selected");


$('#createProject').on('click', function () {
    window.location.href = "/project/create"
});

$('#createInvestorPost').on('click', function () {
    window.location.href = "/investorPost/create"
});

$(document).ready(function () {
    var selector = '.projectContent .contentHeader .additionalPhotos .photo';
    $(selector).on('click', function () {
        $(selector + '.full').attr('src', $(this).attr('src'))
    })
});

var projectId = getUrlParam('id');

loadAndAppendProject(projectId);

function loadAndAppendProject(projectId) {
    loadProjectById(projectId).statusCode({
        200: function (project) {
            var score = checkProjectBalance(project.id);
            var balance;
            $.when(score).done(function (response) {
                balance = response;
                appendProjectBlock(project, balance);
            }).fail(function (response) {
                alert("Проблемы с балансом проекта");
                appendProjectBlock(project, balance);
            });
        }
    });
}

function appendProjectBlock(project, balance) {
    if (typeof loggedInProfile != 'undefined') {
        if (project.authorId === loggedInProfile.id) {
            $('#editProjectBtn').show();
        }
        $('#makeInvest').show();
    } else {
        $('.downComments').replaceWith('<div style="margin-top: 20px; display: inline-block">Пожалуйста, авторизируйтесь, чтобы оставлять комментарии</div>')
    }

    if (balance || balance == 0) {
        $('#projProgress').css('width', getInvertedProgressNum(balance, project.amountRequested) + '%');
        $('#investedAmount').append(balance + ' ₴ ');
    }

    $('#projCreatedDate').append(getReadableDate(project.createdDate));
    $('#projViewsNum').append(project.views);
    $('#projName').append(project.title);
    $('#projText').append(project.description);

    $('#requestedAmount').append(project.amountRequested + ' ₴ ');
    $('#commentsNum').append(project.totalComments);
    if (project.totalVoters > 0) {
        $('#totalScore').append(Math.ceil((project.totalScore / project.totalVoters) * 10) / 10);
    }

    for (var imgId in project.imagesIds) {
        appendProjectImage(imgId, project.imagesIds[imgId]);
    }
    setAuthorContent(project.authorId);
    setProjectCommentsBlock(project.comments);
}

function setProjectCommentsBlock(projectComments) {
    projectComments.forEach(function (comment) {
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
                        '<p class="commentUser">' + comment.comment + '</p>' +
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
                if (profile.imgId) {
                    $('#projCreatorPhoto').attr('src', '/api/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId);
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
    imgTag += (imgKey === "pic1") ? 'full" ' : '" ';
    imgTag += 'src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + imgId + '" >';

    $('#projImages').append(imgTag);
}

$('#sendProjComment').on('click', function () {
    var comment = {
        'comment': $('#projectsFormComments').val(),
        'toId': projectId
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

$('#projectsFormComments').keyup(function () {
    var maxLength = 1000;
    var length = maxLength - $(this).val().length;
    $('#chars').text(length + ' символов осталось');
});

$("#voteBtn").click(function () {
    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/vote/" + $('#projVoteSelect').find(":selected").text(),
        statusCode: {
            200: function () {
                alert('Ваш голос будет учтен');
            },
            409: function () {
                alert('Чтобы проголосовать сначала нужно написать комментарий');
            }
        }
    });
});

$("#editProjectBtn").click(function () {
    window.location.href = getProjectEditUrl(projectId);
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

// ----------------------------------- Check project balance ----------------------------------------------
function checkProjectBalance(projectId) {
    return $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/check-project-balance",
        data: {"projectId": projectId},
        cache: false
    });
}

// ----------------------------------- Modal invest window ----------------------------------------------
//$('#makeInvest').click( function(event){
//    event.preventDefault();
//    $('#overlay').fadeIn(400,
//        function(){
//            $('#investModal')
//                .css('display', 'block')
//                .animate({opacity: 1, top: '50%'}, 200);
//        });
//});
//
//
//$('#overlay, .richAss > form > #close').click( function(){
//    $('#investModal')
//        .animate({opacity: 0, top: '45%'}, 200,
//        function(){
//            $(this).css('display', 'none');
//            $('#overlay').fadeOut(400);
//        }
//    );
//});
//
//
//    $.ajax({
//        type: "POST",
//        url: "/api/rest/projectsAndInvestmentsService/check-project-balance",
//        data: {"projectId": projectId},
//        cache: false
//    });
$(".cropper-btn-cancel").click(function () {
    $('#cropperModal').fadeOut(400);
});

$(window).click(function (event) {
    var modal = document.getElementById('cropperModal');
    if (event.target == modal) {
        $('#cropperModal').fadeOut(400);
    }
});

$('#makeInvest').on('click', function () {
    $('#cropperModal').fadeIn(400);
});

$('#confirmInvest').on('click', function () {
    var investAmount = $('#investInput').val();
    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/make-invest",
        data: {"projectId": projectId, "investAmount": investAmount},
        cache: false,
        statusCode: {
            200: function (response) {
                alert("Операция прошла успешно");
                $('#cropperModal').fadeOut(400);
            },
            403: function (response) {
                alert("Недостаточно денег на счету для совершения операции")
            },
            404: function (response) {
                alert("Внутрення ошибка серера - обратитесь к администратору!")
            }
        }
    });
});
// ----------------------------------- End Modal invest window ----------------------------------------------
