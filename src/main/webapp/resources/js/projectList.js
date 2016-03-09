var projectType = (getUrlParam('type') != null ? getUrlParam('type').toUpperCase() : null);
var projectFO = {type: projectType, searchField: getUrlParam('name'), skip: 0, limit: 10};
var projectIdForInvest;

$('[name="' + projectType + '"]').addClass('selected');
appendProjects(projectFO);

function appendProjects(projectFO) {
    loadProjectsWithFO(projectFO).statusCode({
        200: function (responseEntity) {
            responseEntity.entities.forEach(function (project) {
                var score = checkProjectBalance(project.id);
                var balance;
                $.when(score).done(function (response) {
                    balance = response;
                    appendProjectBlock(project, balance);
                }).fail(function (response) {
                    balance = 0;
                    appendProjectBlock(project, balance);
                });
            });
        }
    });
}

function appendProjectBlock(project, balance) {
    project.description = project.description.replace(/<\/?[^>]+(>|$)/g, "").replace('\\n', ""); // Clear description from HTML tags
    $('#projectsBlock').append(
        '<div class="feedItem">' +
            <!--Add class "vip" to vip-tialize project-->
        '<div class="preview">' +
        '<a href="' + getProjectUrl(project.id) + '">' +
        '<img src="' + getUrlForProjectMainPic(project.imagesIds) + '" alt="project photo" />' +
        '</a>' +
        '<div class="likes">' +
        '<div class="hearthPlace">' +
        '<div class="hearth"></div>' +
        '</div>' +
        '<div class="number">' + getProjectScore(project.totalScore, project.totalVoters) + '</div>' +
        '</div>' +
        '</div>' +
        '<div class="content">' +
        '<a href="' + getProjectUrl(project.id) + '">' +
        '<div class="publishDate">Опубликовано: ' + getReadableDate(project.createdDate) + '</div>' +
        '<div class="title">' + project.title + '</div>' +
        '</a>' +
        '<div class="text">' + project.description + '</div>' +
        '</div>' +
        '<div class="bottomContent">' +
        '<button id="' + project.id + '" class="abutton invest make-invest">Инвестировать</button>' +
        '<div class="projectProgressBlock">' +
        '<div class="current elem cash">' + balance + ' ₴ </div>' +
        '<div class="bar elem">' +
        '<div class="colored"></div>' +
        '<div class="empty" style="width: ' +
        getInvertedProgressNum(balance, project.amountRequested) + '%;"></div>' +
            <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
        '</div>' +
        '<div class="todo elem cash">' + project.amountRequested + ' ₴ </div>' +
        '</div>' +
        '</div>' +
        '</div>');
}

$('.catContainer').on('click', function () {
    $('.catContainer').removeClass('selected');
    $(this).addClass('selected');
    $('#projectsBlock').empty();
    projectFO.type = $(this).attr('name');
    appendProjects(projectFO);
});

$('#createProject').on('click', function () {
    window.location.href = "/project/create"
});

$('#createInvestorPost').on('click', function () {
    window.location.href = "/investorPost/create"
});


// ---------------------------------------------------- Invest in project modal window -------------------------------

$(".cropper-btn-cancel").click(function () {
    $('#cropperModal').css('display', "none");
});

$(window).click(function (event) {
    var modal = document.getElementById('cropperModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
});

$(document).on("click", '.make-invest', function () {
    projectIdForInvest = $(this).attr('id');
    $('#cropperModal').css('display', "block");
});

$('#confirmInvest').on('click', function () {

    var investAmount = $('#investInput').val();
    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/make-invest",
        data: {"projectId": projectIdForInvest, "investAmount": investAmount},
        cache: false,
        statusCode: {
            200: function (response) {
                alert("Операция прошла успешно");
                $('#cropperModal').css('display', "none");
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

