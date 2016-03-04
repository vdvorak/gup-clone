$("#selectedService option[value='project']").attr("selected","selected");

var projectId = getUrlParam('id');
var updatedProject = {};
var loadedProject = {};

loadAndAppendProjectInfo(projectId);

function loadAndAppendProjectInfo(projectId) {
    $.ajax({
        type: "GET",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/read",
        statusCode: {
            200: function (project) {
                loadedProject = project;
                updatedProject.imagesIds = project.imagesIds;
                appendProjectInfo(project);
            }
        }
    });
}

function appendProjectInfo(project) {
    $('#main-title-info').val(project.title);
    $('#sum').val(project.amountRequested);
    $('#description').val(project.description);
    $('input:radio').filter('[value="' + project.type + '"]').attr('checked', true);

    for (var imgId in project.imagesIds) {
        appendProjectImage(imgId);
    }
}

function appendProjectImage(imageId) {
    var imgTag = '<div class="defaultIMG">' +
        '<img src="/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + imageId + '" width="60" height="60">' +
        '</div>';

    $('#IMGBlock').append(imgTag);
}

$('#editProjectBtn').on('click', function () {
    initializeProjectEntityForUpdate();
    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/project/edit",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(updatedProject),
        statusCode: {
            200: function () {
                window.location.href = '/project?id=' + projectId;
            }
        }
    });
});

$('#deleteProjectBtn').on('click', function () {
    $("#confirmProjDelete").show();
});

$('#cancelProjDelBtn').on('click', function () {
    $("#confirmProjDelete").hide();
});

$('#confirmProjDelBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + loadedProject.id + "/delete",
        statusCode: {
            204: function () {
                window.location.href = '/project/list';
            }
        }
    });
});

$('#addProjPhoto').on('click', function () {
    $("#uploadProjectPhotoInput").click();
});

$('#uploadProjectPhotoInput').on('change', function () {
    $.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/project/file/upload", //
        data: new FormData($("#uploadProjectPhotoForm")[0]),
        enctype: 'multipart/form-data',
//                async: false,
        cache: false,
        contentType: false,
        processData: false,
        statusCode: {
            201: function (data) {
                updatedProject.imagesIds.push(data.id);
                appendProjectImage(data.id);
            },
            400: function () {
                alert('400');
            }
        }
    });
});

function initializeProjectEntityForUpdate() {
    updatedProject.id = projectId;
    updatedProject.title = $('#main-title-info').val();
    updatedProject.type = $('input:radio[name="type"]:checked').val()
    updatedProject.description = $('#description').val();
    updatedProject.amountRequested = $('#sum').val();
}