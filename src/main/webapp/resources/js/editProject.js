$("#selectedService option[value='project']").attr("selected","selected");

var projectId = getUrlParam('id');
var updatedProject = {};
var loadedProject = {};
var imagesIds = {};
var imagesIdsResult = {};
var picArrDel = [];
var picArrNew = [];

// Setup the dnd listeners.
var dropZone = document.getElementById('drop_zone');
dropZone.addEventListener('dragover', handleDragOver, false);
dropZone.addEventListener('drop', handleFileSelect, false);

function handleFileSelect(evt) {
    evt.stopPropagation();
    evt.preventDefault();

    var files = evt.dataTransfer.files; // FileList object.

    // files is a FileList of File objects. List some properties.
    for (var i = 0, f; f = files[i]; i++) {
        var fd = new FormData();
        fd.append('file', f);
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
            data: fd,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            statusCode: {
                201: function (data, textStatus, request) {
                    var id = data.id;
                    if (f.type.substring(0, 5) === 'image') {
                        imagesIds[id] = "image";
                        appendProjectImage(id);
                    } else {
                        imagesIds[id] = "doc";
                        appendDoc(id, f.name);
                    }
                }
            }
        });
    }
}

function handleDragOver(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
}

loadAndAppendProjectInfo(projectId);

function loadAndAppendProjectInfo(projectId) {
    $.ajax({
        type: "GET",
        url: "/api/rest/projectsAndInvestmentsService/project/id/" + projectId + "/read",
        statusCode: {
            200: function (project) {
                loadedProject = project;
                imagesIds = project.imagesIds;
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

    for (var imgId in imagesIds) {
        if (imagesIds[imgId] === "image" ||imagesIds[imgId] === "pic1") {
            appendProjectImage(imgId);
        } else {
            appendDoc(imgId, "");
        }
    }
}

function appendProjectImage(imageId) {
    $("#project-img-block > .li-defaultIMG").css("display", "none");
    var cloneImg = $("#project-img-block > .li-defaultIMG").clone()
        .removeClass('li-defaultIMG')
        .css("display", "inline-block");
    cloneImg.find('img')
        .attr("alt", "")
        .attr("src", '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + imageId)
        .attr("id", imageId)
        .click(onClickSetMainImg);
    cloneImg.find('span')
        .click(deleteImg);
    cloneImg.appendTo('#project-img-block');
}

function appendDoc(id, name) {
    $("#project-doc-block > .li-defaultIMG").css("display", "none");
    var cloneDoc = $("#project-doc-block > .li-defaultIMG").clone()
        .removeClass('li-defaultIMG')
        .css("display", "inline-block");
    cloneDoc.find('img')
        .attr("id", id);
    cloneDoc.find('div')
        .text(name);
    cloneDoc.find('span')
        .click(deleteImg);
    cloneDoc.appendTo('#project-doc-block');
}

function onClickSetMainImg() {
    var img = $(event.currentTarget);
    var id = img.attr("id");
    var isMain = img.hasClass("mainImg");
    var allImgs = $("#project-img-block").find("img");
    for (var i = 0; i < allImgs.length; i++) {
        var curImg = $(allImgs[i]);
        if (curImg.hasClass("mainImg")) {
            curImg.removeClass("mainImg");
        }
    }
    if (!isMain) img.addClass("mainImg");

    for (var key in imagesIds) {
        if (imagesIds[key] === "pic1") {
            imagesIds[key] = "image";
        }
    }

    if (img.hasClass("mainImg")) {
        imagesIds[id] = "pic1";
    }
}

function checkMainImg() {
    var hasMainImg = false;

    for(var key in imagesIds) {
        if(imagesIds[key] === 'pic1') {
            hasMainImg = true;
            break;
        }
    }

    if(!hasMainImg) {
        for(var key in imagesIds) {
            imagesIds[key] = 'pic1';
            break;
        }
    }
}

function deleteImgFromDB(idImg) {
    $.ajax({
        type: "POST",
        url: '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/delete/id/' + idImg,
        success: function (data, textStatus, request) {
        }
    });
}

function deleteImg() {
    var idImg = $(event.currentTarget).parent()
        .find('img')
        .attr('id');
    var block = $(event.currentTarget).parent().parent();
    $('#' + idImg).parent().remove();

    var numberImg = block.find('img').length;
    if(numberImg < 2) {
        block.find(".li-defaultIMG").css("display", "inline-block");
    }
    picArrDel.push(idImg);
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

$('#uploadProjectPhotoInput').change(function (event) {
    event.preventDefault();

    var files = event.currentTarget.files;
    for (var i = 0, f; f = files[i]; i++) {
        var fd = new FormData();
        fd.append('file', f);
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/upload/",
            data: fd,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            statusCode: {
                201: function (data, textStatus, request) {
                    var id = data.id;
                    if (f.type.substring(0, 5) === 'image') {
                        imagesIds[id] = "image";
                        appendProjectImage(id);
                    } else {
                        imagesIds[id] = "doc";
                        appendDoc(id, f.name);
                    }
                }
            }
        });
    }
    event.currentTarget.form.reset();
});

function initializeProjectEntityForUpdate() {
    for(var key in imagesIds) {
        if(picArrDel.indexOf(key) === -1) picArrNew.push(key);
    }

    for (var i = 0; i < picArrNew.length; i++) {
        imagesIdsResult[picArrNew[i]] = imagesIds[picArrNew[i]];
    }

    for(var i = 0; i < picArrDel.length; i++) {
        deleteImgFromDB(picArrDel[i]);
    }

    checkMainImg();

    updatedProject.id = projectId;
    updatedProject.title = $('#main-title-info').val();
    updatedProject.type = $('input:radio[name="type"]:checked').val();
    updatedProject.description = $('#description').val();
    updatedProject.imagesIds = imagesIdsResult;
    updatedProject.amountRequested = $('#sum').val();
}

tinymce.init({
    selector: 'textarea',
    height: 300,
    theme: 'modern',
    plugins: [
        'advlist autolink lists link image charmap print preview hr anchor pagebreak',
        'searchreplace wordcount visualblocks visualchars code fullscreen',
        'insertdatetime media nonbreaking save table contextmenu directionality',
        'emoticons template paste textcolor colorpicker textpattern imagetools'
    ],
    toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    toolbar2: 'print preview media | forecolor backcolor emoticons',
    image_advtab: true,
    templates: [
        {title: 'Test template 1', content: 'Test 1'},
        {title: 'Test template 2', content: 'Test 2'}
    ],
    content_css: [
        '//fast.fonts.net/cssapi/e6dc9b99-64fe-4292-ad98-6974f93cd2a2.css',
        '//www.tinymce.com/css/codepen.min.css'
    ]
});