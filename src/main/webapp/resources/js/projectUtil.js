function loadProjectById(projectId) {
    return $.ajax({
        type: "GET",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/read",
    });
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