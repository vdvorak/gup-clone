var imagesIds = {};

$(document).ready(function () {
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

                success: function (data, textStatus, request) {
                    var id = data.id;
                    var isImage = f.type.substring(0, 5) === 'image';
                    if (isImage) {
                        imagesIds[id] = "image";
                        $(".li-defaultIMG").css("display", "none");
                        var cloneImg = $(".li-defaultIMG").clone()
                            .removeClass('li-defaultIMG')
                            .css("display", "inline-block");
                        cloneImg.find('img')
                            .attr("alt", "")
                            .attr("src", '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + id)
                            .attr("id", id);
                        cloneImg.find('span')
                            .click(deleteImgFromDB);
                        cloneImg.appendTo('.defaultIMG ul');

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

    $('button.blogCreationSubmit').click(function(){
        $('#photoInput').trigger('click');
    });

});

$('#photoInput').change(function (event) {
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

            success: function (data, textStatus, request) {
                var id = data.id;
                var isImage = f.type.substring(0, 5) === 'image';
                if (isImage) {
                    imagesIds[id] = "image";
                    $(".li-defaultIMG").css("display", "none");
                    var cloneImg = $(".li-defaultIMG").clone()
                        .removeClass('li-defaultIMG')
                        .css("display", "inline-block");
                    cloneImg.find('img')
                        .attr("alt", "")
                        .attr("src", '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + id)
                        .attr("id", id);
                    cloneImg.find('span')
                        .click(deleteImgFromDB);
                    cloneImg.appendTo('.defaultIMG ul');
                }
            }
        });
    }
    event.currentTarget.form.reset();
});

function deleteImgFromDB(event) {

    var picId = $(event.currentTarget).parent()
        .find('img')
        .attr('id');
    delete imagesIds[picId];
    $.ajax({
        url: '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/delete/id/' + picId,
        method: 'POST',
        success: function (response) {
            $('#' + picId).parent().remove();

            var numberImg = $(".defaultIMG").find('img').length;
            if(numberImg < 2) {
                $(".li-defaultIMG").css("display", "inline-block");
            }
        },
        error: function (response) {
        }
    });
}

$(document).on('click', 'button.info-submit', function (event) {
    //var type = "";
    //if($('input[class="greenCheckbox"]:checked').length) type =
    //var title =
    //var description =
    //var amountRequested =
    //var categoriesOfIndustry = "";
    //if($('#categoriesOfIndustry').length) categoriesOfIndustry =

    var incorrectValuesMsg = '';
    var newProject = {};
    newProject.moderationStatus = "COMPLETE";

    newProject.type =  $('input[class="greenCheckbox"]:checked').val();
    if (!newProject.type) { incorrectValuesMsg += "Выбрете тип проекта \n";}

    newProject.title = $('#main-title-info').val();
    if (newProject.title.length < 4 || newProject.title.length > 70) {incorrectValuesMsg += "Введите заголовок \n";}

    newProject.description = tinymce.activeEditor.getContent({format : 'raw'});
    alert("[" + newProject.description + "]");
    if (newProject.description.length < 50 || newProject.description.length > 5000) {incorrectValuesMsg += "Добавьте описание \n";}

    newProject.amountRequested = +$('#sum').val();
    if (newProject.amountRequested < 1) {incorrectValuesMsg += "Укажите нужную сумму \n";}

    //newProject.categoriesOfIndustry = $('#categoriesOfIndustry').val();
    //if (!categoriesOfIndustry) {incorrectValues += "Добавьте категории индустрии<br>";}

    for(var key in imagesIds) {
        imagesIds[key] = '1';
        break;
    }
    newProject.imagesIds = imagesIds;

    if (!incorrectValuesMsg.length) {
        $.ajax({
            type: "POST",
            url: "/api/rest/projectsAndInvestmentsService/project/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(newProject),
            statusCode: {
                201: function (createdProjectId) {
                    window.location.href = '/project?id=' + createdProjectId.id;
                }
            }
        });
    } else {
        alert(incorrectValuesMsg);
    }
});

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