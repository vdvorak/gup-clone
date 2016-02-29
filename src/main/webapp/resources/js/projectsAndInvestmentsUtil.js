function loadProjectById(projectId) {
    return $.ajax({
        type: "GET",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/read",
    });
}

function loadInvestorPostsWithFO(investorPostFO) {
    return $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/projectsAndInvestmentsService/investorPost/read/all",
        data: JSON.stringify(investorPostFO)
    })
}

function loadProjectsWithFO(projectFO) {
    return $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/projectsAndInvestmentsService/project/read/all",
        data: JSON.stringify(projectFO)
    })
}

function getReadableDate(timestamp) {
    var createdDate = new Date(timestamp);
    return createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear();
}

function getProjectScore(totalScore, totalVoters) {
    return Math.ceil((totalScore/totalVoters) * 10) / 10;
}

function getUrlForProjectMainPic(imagesIds) {
    for (var id in imagesIds) {
        if (imagesIds[id] === "1") {
            return "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/" + id;
        }
    }
    return "/resources/images/no_photo.jpg";
}

function getProjectUrl(id) {
    return "/project?id=" + id;
}

function getInvertedProgressNum(investedAmount, amountRequested) {
    var invertedProgressNum = (1 -(investedAmount/amountRequested))*100;
    return 5 * Math.ceil(invertedProgressNum/5);
}

$("#selectedService option[value='project']").attr("selected","selected");

$('#showNext').on('click',function () {
    if($('#projectsTab').hasClass('active')) {
        projectFO.skip += projectFO.limit;
        appendProjects(projectFO);
    } else {
        investorPostFO.skip += investorPostFO.limit;
        appendInvestorPosts(investorPostFO);
    }
});

$('#projectsTab').on('click',function () {
    $('#investmentsContainer').hide();
    $('#projectsContainer').show();
    $('.catContainer').removeClass('selected');
    $('#projectsBlock').empty();

    projectFO.type = null;
    projectFO.skip = 0;
    appendProjects(projectFO);
});

$('#investmentsTab').on('click',function () {
    $('#projectsContainer').hide();
    $('#investorPostsBlock').empty();
    $('#investmentsContainer').show();

    investorPostFO.skip = 0;
    appendInvestorPosts(investorPostFO);
});

$('.projectsVSInvestments-btn').on('click',function () {
    $('.projectsVSInvestments-btn').removeClass('active');
    $(this).addClass('active');
});