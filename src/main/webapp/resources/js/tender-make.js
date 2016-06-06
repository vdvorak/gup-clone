
(function() {

    var filesArr = [];

    var gupValidator = new window.GupValidator.Constructor('tender').init();

    initTender();

    function initTender() {
        if (typeof loggedInProfile === 'undefined') {
            $('#tender-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания тендера вам необходимо зарегистрироваться</i></p></div>');
            return;
        }
        initTinymce();
        initChosenParticipants();
        initChosenNace();
        initEventHandlers();
    }

    function initEventHandlers() {
        $('.input-tenderRadio').change(onChangeOpenCloseType);
        $('#tender-btn-save').click(createTender);

        initDropZoneEventHandlers();
        initFilesEventHandlers();
    }

    //---------------------------- BEGIN DROPZONE -------------------------------------------------//
    function initDropZoneEventHandlers() {
        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);
        dropZone.addEventListener('dragleave', handleDragLeave, false);
    }

    function handleFileSelect(evt) {
        evt.stopPropagation();
        evt.preventDefault();

        dropZone.classList.remove("good");
        uploadFiles(evt.dataTransfer.files);
    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
        dropZone.classList.add("good");
    }

    function handleDragLeave(evt) {
        dropZone.classList.remove("good");
    }
    //---------------------------- END DROPZONE -------------------------------------------------//

    //---------------------------- BEGIN IMAGES AND DOCS -------------------------------------------------//
    function initFilesEventHandlers() {
        $('.blogCreationSubmit').click(function () {
            $('#photoInput').trigger('click');
        });
        $('#tender-btn-addDoc').click(function () {
            $('#photoInput').trigger('click');
        });
        $('#photoInput').change(function (event) {
            event.preventDefault();
            uploadFiles(event.currentTarget.files);
        });
    }

    function appendImg(img) {
        $("#tender-img-block > .li-defaultIMG").css("display", "none");
        var cloneImg = $("#tender-img-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/TENDER/file/read/id/' + img.id)
            .attr("id", img.id)
            .click(onClickSetMainImg);
        cloneImg.find('span')
            .click(deleteImg);
        cloneImg.appendTo('#tender-img-block');
    }

    function appendDoc(doc) {
        $("#tender-doc-block > .li-defaultIMG").css("display", "none");
        var cloneDoc = $("#tender-doc-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneDoc.find('img')
            .attr("id", doc.id);
        cloneDoc.find('div')
            .text(doc.name);
        cloneDoc.find('span')
            .click(deleteImg);
        cloneDoc.appendTo('#tender-doc-block');
    }

    function uploadFiles(files) {
        for (var i = 0, f; f = files[i]; i++) {
            var fd = new FormData();
            fd.append('file', f);
            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/TENDER/file/upload/",
                data: fd,
                async: false,
                cache: false,
                contentType: false,
                processData: false,

                success: function (data, textStatus, request) {
                    if (filesArr.length < 15) {
                        var file, id = data.id;

                        if (f.type.substring(0, 5) === 'image') {
                            file = {id: id, name: f.name, fileType: 'IMAGE', size: f.size};
                            appendImg(file);
                        } else {
                            file = {id: id, name: f.name, fileType: 'DOCUMENT', size: f.size};
                            appendDoc(file);
                        }

                        filesArr.push(file);
                    }
                }
            });
        }
    }

    function deleteImg(event) {
        var elem = $(event.currentTarget),
            block = elem.parent().parent(),
            idImg = elem.parent().find('img').attr('id');
        filesArr.splice(findFileIndexById(idImg), 1);
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/TENDER/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).parent().remove();

                var numberImg = block.find('img').length;
                if (numberImg < 2) block.find(".li-defaultIMG").css("display", "inline-block");
            }
        });
    }

    function onClickSetMainImg(event) {
        var img = $(event.currentTarget),
            id = img.attr("id"),
            isMain = img.hasClass("mainImg"),
            allImgs = $("#tender-img-block").find("img");

        for (var i = 0; i < allImgs.length; i++) {
            var curImg = $(allImgs[i]);
            if (curImg.hasClass("mainImg")) {
                curImg.removeClass("mainImg");
            }
        }
        if (!isMain) img.addClass("mainImg");

        for (var i = 0; i < filesArr.length; i++) {
            if (filesArr[i].fileType === "MAINIMAGE") {
                filesArr[i].fileType = 'IMAGE';
            }
        }

        if (img.hasClass("mainImg")) {
            filesArr[findFileIndexById(id)].fileType = "MAINIMAGE";
        }
    }

    function checkMainImg() {
        var hasMainImg = false;

        for (var i = 0; i < filesArr.length; i++) {
            if (filesArr[i].fileType === "MAINIMAGE") {
                hasMainImg = true;
                break;
            }
        }

        if (!hasMainImg) {
            for (var i = 0; i < filesArr.length; i++) {
                if (filesArr[i].fileType === "DOCUMENT") continue;
                filesArr[i].fileType = "MAINIMAGE";
                break;
            }
        }
    }

    function findFileIndexById(fileId) {
        for (var i = 0; i < filesArr.length; i++) {
            if (filesArr[i].id === fileId) return i;
        }
    }
    //---------------------------- END IMAGES AND DOCS -------------------------------------------------//

//----------------------  HTML EDITOR-------------------------------------//
    function initTinymce() {
        tinymce.init({
            selector: '#Description',
            height: 500,
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

//---------------------- END  HTML EDITOR-------------------------------------//

//--------------------  RADIO CHECK ------------------------------------//

    function onChangeOpenCloseType() {
        var display = $('.input-tenderRadio[data-type="CLOSE"]').prop('checked') ? "" : "none";

        $('#selectParticipants_chosen').css("display", display);
        $('label[for="selectParticipants"]').css("display", display);
    }

//--------------------   END RADIO CHECK ------------------------------------//

// ---------------------------- BEGIN CHOSEN -------------------------------------------------//

    function drawOptionsParticipantsAutocomplete(response) {
        var input = $("#selectParticipants_chosen ul.chosen-choices li.search-field input"),
            select = $('#selectParticipants'),
            $search_param = input.val();

        select.children('option:not(:selected)').remove();
        for (var key in response) {
            if (!select.children('option[value="' + response[key].id + '"]').length) {
                select.append('<option value="' + response[key].id + '">' + response[key].username + ' </option>');
            }
        }
        select.trigger("chosen:updated");
        input.val($search_param);
    }

    function initChosenParticipants(){
        var select = $('#selectParticipants'),
            input = $("#selectParticipants_chosen ul.chosen-choices li.search-field input");

        select.chosen({width: '545px', display_selected_options: false});
        $('#selectParticipants_chosen').css("display", "none");

        select.on('change', function () {
            select.children('option:not(:selected)').remove();
            select.trigger("chosen:updated");
        });

        input.autocomplete({
            source: function (request, response) {
                $.getJSON("/search/autocomplete/profile/ids", {
                    term: request.term
                }, drawOptionsParticipantsAutocomplete);
            }
        });
    }

    function initChosenNace() {
        $.when(loadNace).done(function (response) {
            var select = $('#selectKved');
            for (var i = 0; i < response.length; i++) {
                var option = $('<option id="' + response[i].id + '" value="' + response[i].id + '">' + response[i].id + ": " + response[i].name + '</option>');
                select.append(option);
            }
            $("#selectKved").chosen();
        });
    }
//---------------------------- END CHOSEN -------------------------------------------------//

//---------------------------- SUBMIT -----------------------------------------------------//
    function Tender() {}

    Tender.prototype.sendCreatedTender = sendCreatedTender;
    Tender.prototype.setTenderAddress = setTenderAddress;
    Tender.prototype.setTenderImages = setTenderImages;
    Tender.prototype.setTenderDates = setTenderDates;
    Tender.prototype.setTenderMembers = setTenderMembers;
    Tender.prototype.setTenderNace = setTenderNace;
    Tender.prototype.setTenderOtherProperties = setTenderOtherProperties;

    function setTenderAddress() {
        this.address = {};
        this.address.area = $('#SelectArea').val();
        this.address.city = $('#SelectCity').val();
        return this;
    }

    function setTenderImages() {
        checkMainImg();

        this.files = filesArr;
        return this;
    }

    function setTenderDates() {
        var dateBegin = $('#tender-datepicker1').datepicker('getDate'),
            dateEnd = $('#tender-datepicker2').datepicker('getDate');
        this.begin = (dateBegin) ? dateBegin.getTime() : null;
        this.end = (dateEnd) ? dateEnd.setHours(23, 59, 59, 999) : null;
        this.publishDate = Date.now() - (new Date()).getTimezoneOffset() * 60000;
        return this;
    }

    function setTenderMembers() {
        this.type = $('.input-tenderRadio:checked').attr("data-type");
        if (this.type === 'CLOSE') {
            var arrOpt = $('#selectParticipants').children();
            this.members = [];
            for (var i = 0; i < arrOpt.length; i++) {
                this.members.push({
                    id: $(arrOpt[i]).attr('value'),
                    name: $(arrOpt[i]).text()
                })
            }
        }
        return this;
    }

    function setTenderNace() {
        var naceIds = $('#selectKved').val();
        if (naceIds) this.naceIds = naceIds;
        return this;
    }

    function setTenderOtherProperties() {
        this.title = $('#EnterTheTitle').val();
        this.body = tinymce.activeEditor.getContent();
        this.tenderNumber = $('#TenderNumber').val();
        this.expectedPrice = $('#ExpectedValue').val();
        this.hidePropose = $('#HideBidders').prop('checked');
        this.hideContact = $('#HideContacts').prop('checked');
        return this;
    }

    function sendCreatedTender() {
        return $.ajax({
            type: "POST",
            url: "/api/rest/tenderService/tender/create/",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(this),
            dataType: "json",
            statusCode: {
                201: function (data, textStatus, request) {
                    window.location.href = '/tender/' + data.id;
                }
            }
        });
    }

    function createTender(event){
        event.preventDefault();

        var tender = new Tender()
            .setTenderAddress()
            .setTenderDates()
            .setTenderImages()
            .setTenderMembers()
            .setTenderNace()
            .setTenderOtherProperties();

        gupValidator.validate(tender);
        if (!gupValidator.isValid) return;

        tender.sendCreatedTender();
    }
//---------------------------- END SUBMIT -------------------------------------------------//
})()

