var projectFO = {};
projectFO.createdDateSortDirection = "DESC";
projectFO.skip = 0;
projectFO.limit = 3;

loadAndAppendTopProjects(projectFO);

$("#proj-caret").click(function(){
    loadAndAppendNextProjects(projectFO);
});

function getProjectUrl(project) {
    return '/project/id/' + project.id;
}

function getProjectImagePreviewTag(project) {
    if (project.imagesIds !== null) {
        for (var key in project.imagesIds) {
            if (project.imagesIds[key] === "1") {
                return '<img src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + key + '" class="img-responsive" >';
            }
        }
    }
    return '<img src="/resources/images/no_photo.jpg" class="img-responsive" >';
}


function appendProject(elementId, projectURL, imagePreviewTag, title) {
    $('#' + elementId).append(
    '<div class="proj-top1">' +
        '<a href="' + projectURL + '">' +
            imagePreviewTag +
        '</a>' +
        '<a href="' + projectURL + '" class="ad-a1">' + title + '</a>' +
    '</div>');
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
                    var imagePreviewTag = getProjectImagePreviewTag(projects[i]);

                    appendProject('topProjectsBlock', projectURl, imagePreviewTag, projects[i].title);
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
