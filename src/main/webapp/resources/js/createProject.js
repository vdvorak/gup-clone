if (typeof loggedInProfile == 'undefined') {
    $('#project-create-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания объявления вам необходимо зарегистрироваться</i></p></div>')
}else{

    var imagesIds = {};

    $(document).ready(function () {
        $(".chosen").chosen();

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
                                appendImg(id);
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
                statusCode: {
                    201: function (data, textStatus, request) {
                        var id = data.id;
                        if (f.type.substring(0, 5) === 'image') {
                            imagesIds[id] = "image";
                            appendImg(id);
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

    function appendImg(id) {
        $("#project-img-block > .li-defaultIMG").css("display", "none");
        var cloneImg = $("#project-img-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/' + id)
            .attr("id", id)
            .click(onClickSetMainImg);
        cloneImg.find('span')
            .click(deleteImgFromDB);
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
            .click(deleteImgFromDB);
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

    function deleteImgFromDB(event) {

        var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
        delete imagesIds[idImg];

        var block = $(event.currentTarget).parent().parent();

        $.ajax({
            url: '/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/delete/id/' + picId,
            method: 'POST',
            success: function (response) {
                $('#' + idImg).parent().remove();

                var numberImg = block.find('img').length;
                if(numberImg < 2) {
                    block.find(".li-defaultIMG").css("display", "inline-block");
                }
            },
            error: function (response) {
            }
        });
    }

    $(document).on('click', 'button.info-submit', function (event) {
        var incorrectValuesMsg = '';
        var newProject = {
            'categoriesOfIndustry' : []
        };

        newProject.type =  $('input[class="greenCheckbox"]:checked').val();
        newProject.title = $('#main-title-info').val();
        newProject.description = tinymce.activeEditor.getContent({format : 'raw'});
        newProject.amountRequested = +$('#sum').val();

        $('#categoriesOfIndustry').find('option:selected').each(function() {
            newProject.categoriesOfIndustry.push($(this).val());
        });

        if (!newProject.type) { incorrectValuesMsg += "Выбрете тип проекта \n";}
        if (newProject.title.length < 4 || newProject.title.length > 70) {incorrectValuesMsg += "Введите заголовок \n";}
        if (newProject.description.length < 50 || newProject.description.length > 5000) {incorrectValuesMsg += "Добавьте описание \n";}
        if (newProject.amountRequested < 1) {incorrectValuesMsg += "Укажите нужную сумму \n";}
        if (newProject.categoriesOfIndustry.length < 1) {incorrectValuesMsg += "Добавьте категории индустрии \n";}

        checkMainImg();
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

}




