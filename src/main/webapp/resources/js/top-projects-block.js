var projectFO = {};
projectFO.createdDateSortDirection = "DESC";
projectFO.skip = 0;
projectFO.limit = 3;

loadAndAppendTopProjects(projectFO);

$("#proj-caret").click(function(){
    loadAndAppendNextProjects(projectFO);
});

function getProjectUrl(project) {
    return '/project?id=' + project.id;
}

function getProjectImageUrl(imagesIds) {
    if (imagesIds) {
        for (var imgId in imagesIds) {
            if (imagesIds[imgId] === "1") {
                return "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/" + imgId;
            }
        }
    }

    return "/resources/images/no_photo.jpg";
}

function appendProject(elementId, projectURL, imageUrl, title) {
    $('#' + elementId).append(
    '<div class="proj-top1">' +
        '<a href="' + projectURL + '" class="ad-a1">' + title + '</a>' +
    '</div>');

    $('.proj-top1').last()
        .css('background', 'url(' + imageUrl + ')  no-repeat center center')
        .css('background-size', 'cover');
}

function loadAndAppendTopProjects() {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/api/rest/projectsAndInvestmentsService/project/read/all",
        data: JSON.stringify(projectFO),
        statusCode: {
            200: function(data) {
                var projects = data.entities;

                for (var i = 0; i < projects.length; i++) {
                    var projectURl = getProjectUrl(projects[i]);

                    appendProject('topProjectsBlock', projectURl, getProjectImageUrl(projects[i].imagesIds), projects[i].title);
                }
            }
            //,
            //204 : function() {
            //}
        }
    });
}

function loadAndAppendNextProjects() {

    if (projectFO.skip == 0) {
        projectFO.skip = projectFO.limit;
        projectFO.limit = 3;
    } else {
        projectFO.skip += 3;
    }

    loadAndAppendTopProjects();
}
