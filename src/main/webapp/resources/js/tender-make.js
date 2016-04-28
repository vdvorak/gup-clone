
if (typeof loggedInProfile == 'undefined') {
    $('#tender-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания тендера вам необходимо зарегистрироваться</i></p></div>')
}else{
    var imgsArr = {};
    var members = [];
    var placeKey = '';

    $.when(loadNace).done(function(response){
        var select = $('#selectKved');
        for(var i = 0; i < response.length; i++) {
            var option = $('<option id="'+ response[i].id +'" value="'+ response[i].id +'">'+ response[i].id + ": " +response[i].name +'</option>');
            select.append(option);
        }
        $("#selectKved").chosen();
    });

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
                    url: "/api/rest/fileStorage/TENDER/file/upload/",
                    data: fd,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,

                    success: function (data, textStatus, request) {
                        if (Object.keys(imgsArr).length < 15) {
                            var id = data.id;
                            if (f.type.substring(0, 5) === 'image') {
                                imgsArr[id] = "image";
                                appendImg(id);
                            } else {
                                imgsArr[id] = "doc";
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

    });

    $('.blogCreationSubmit').click(function(){
        $('#photoInput').trigger('click');
    });
    $('#tender-btn-addDoc').click(function(){
        $('#photoInput').trigger('click');
    });

    function appendImg(id) {

        $("#tender-img-block > .li-defaultIMG").css("display", "none");
        var cloneImg = $("#tender-img-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/TENDER/file/read/id/' + id)
            .attr("id", id)
            .click(onClickSetMainImg);
        cloneImg.find('span')
            .click(deleteImg);
        cloneImg.appendTo('#tender-img-block');
    }

    function appendDoc(id, name) {
        $("#tender-doc-block > .li-defaultIMG").css("display", "none");
        var cloneDoc = $("#tender-doc-block > .li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneDoc.find('img')
            .attr("id", id);
        cloneDoc.find('div')
            .text(name);
        cloneDoc.find('span')
            .click(deleteImg);
        cloneDoc.appendTo('#tender-doc-block');
    }


//----------------------  HTML EDITOR-------------------------------------//
    tinymce.init({
        selector: 'textarea',
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

//---------------------- END  HTML EDITOR-------------------------------------//

//--------------------  RADIO CHECK ------------------------------------//

    $('.input-tenderRadio').change(function () {
        var display = $('.input-tenderRadio[data-type="CLOSE"]').prop('checked') ? "" : "none";

        $('#selectParticipants_chosen').css("display", display);
        $('label[for="selectParticipants"]').css("display", display);
    });

//--------------------   END RADIO CHECK ------------------------------------//


//-------------------- ADD MEMBER ------------------------------------------//

    $('#add').click(function () {
        var email = $('input[name="memberId"]').val();
        $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/email-check",
            data: {"email": email},
            success: function (data) {
                if (data === 'NOT FOUND') {
                    alert("Нет пользователя с таким e-mail");
                } else {
                    var member = {};
                    member.id = data;
                    member.name = email;
                    var flag = true;
                    for (var i = 0; i < members.length; i++) {
                        if (members[i].id === data) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        members.push(member);
                        $('#membersList').append('<p name="' + email + '">' + email + '    <i name="' + email + '"class="icon-remove-sign"></i></p>');
                        $('i[name="' + email + '"]').click(function () {
                            $('p[name="' + email + '"]').detach();
                            for (var i in members) {
                                if (members[i].name === email) {
                                    members.splice(i);
                                }
                            }
                        });
                    } else {
                        alert("Собеседник уже добавлен!");
                    }
                }
            }
        });
    });

//-------------------- END ADD MEMBER ------------------------------------------//

    $('#photoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;
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
                    if (Object.keys(imgsArr).length < 15) {
                        var id = data.id;
                        if (f.type.substring(0, 5) === 'image') {
                            imgsArr[id] = "image";
                            appendImg(id);
                        } else {
                            imgsArr[id] = "doc";
                            appendDoc(id, f.name);
                        }
                    }
                }
            });
        }
    });

    /*function deleteImg(idImg) {
     delete imgsArr[idImg];
     $.ajax({
     type: "POST",
     url: "/api/rest/fileStorage/NEWS/file/delete/id/" + idImg,
     success: function (data, textStatus, request) {
     $('#' + idImg).remove();
     }
     });
     }*/

    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
        delete imgsArr[idImg];
        var block = $(event.currentTarget).parent().parent();
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/TENDER/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).parent().remove();

                var numberImg = block.find('img').length;
                if(numberImg < 2) {
                    block.find(".li-defaultIMG").css("display", "inline-block");
                }
            }
        });
    }

    function onClickSetMainImg(event) {
        var img = $(event.currentTarget);
        var id = img.attr("id");
        var isMain = img.hasClass("mainImg");
        var allImgs = $("#tender-img-block").find("img");
        for (var i =0; i < allImgs.length; i++) {
            var curImg = $(allImgs[i]);
            if (curImg.hasClass("mainImg")) {
                curImg.removeClass("mainImg");
            }
        }
        if(!isMain) img.addClass("mainImg");

        for(var key in imgsArr) {
            if(imgsArr[key] === "pic1") {
                imgsArr[key] = "image";
            }
        }

        if(img.hasClass("mainImg")) {
            imgsArr[id] = "pic1";
        }
    }

    function checkMainImg() {
        var hasMainImg = false;

        for(var key in imgsArr) {
            if(imgsArr[key] === 'pic1') {
                hasMainImg = true;
                break;
            }
        }

        if(!hasMainImg) {
            for(var key in imgsArr) {
                imgsArr[key] = 'pic1';
                break;
            }
        }
    }

// -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


//--------------------------- REGIONS LIST --------------------------------------------//

    $('#regions').find('li').click(function (){
        var region = $(this).text();
        $('#chosenRegion').text(region);
        $('#areaInp').val(region);
        if (region !== 'Вся Украина') {
            $('#bs-example-navbar-collapse-2').attr("style", "visibility: visible");
        } else {
            $('#bs-example-navbar-collapse-2').attr("style", "visibility: hidden");
        }
        $('#chosenCity').text("Выберите город");

        $('#bs-example-navbar-collapse-2').find('#cities1, #cities2').empty();
        $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#"><b>' + 'Все города' + '</b></a></li>');
        for (var i = 0; i < Math.floor(cities[region].length / 2); i++) {
            $('#bs-example-navbar-collapse-2').find('#cities1').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][i] + '</a></li>');
        }
        for (var j = Math.floor(cities[region].length / 2); j < cities[region].length; j++) {
            $('#bs-example-navbar-collapse-2').find('#cities2').append('<li><a role="menuitem" tabindex="-1" href="#">' + cities[region][j] + '</a></li>');
        }

        $('#cities').find('li').click(function () {
                var city = $(this).text();
                $('#chosenCity').text(city);
                $('#cityInp').val(city);
            }
        );
    });

//--------------------------- END REGIONS LIST --------------------------------------------//


//---------------------------- SUBMIT -----------------------------------------------------//

    function checkDateInDatepicker() {
        var dateFrom = $('#tender-datepicker1').datepicker('getDate');
        var dateTo = $('#tender-datepicker2').datepicker('getDate');
        if (dateFrom && dateTo) {
            dateFrom = new Date(dateFrom).getTime() / 1000;
            dateTo = new Date(dateTo).getTime() / 1000;
            if (dateFrom > dateTo) {
                $('#tender-date span').addClass('tender-active-tooltip');
                $("html,body").animate({ scrollTop: $('span.tender-active-tooltip').offset().top }, "fast");
                setTimeout(function () {
                    $('#tender-date span').removeClass('tender-active-tooltip');
                }, 6000);
                return false;
            }
        }
        return true;
    }

    $('#tender-make-form').submit(function (event) {

        if(!checkDateInDatepicker()) return false;

        var body = tinymce.activeEditor.getContent();
        if(!body) return false;

        checkMainImg();

        var tender = {};
        tender.uploadFilesIds = imgsArr;
        tender.title = $('#EnterTheTitle').val();
        tender.body = tinymce.activeEditor.getContent();
        tender.tenderNumber = $('#TenderNumber').val();
        var dateBegin = $('#tender-datepicker1').datepicker( 'getDate' );
        var dateEnd = $('#tender-datepicker1').datepicker( 'getDate' );
        if(dateBegin) tender.begin = dateBegin.getTime() / 1000;
        if(dateEnd) tender.end = dateEnd.getTime() / 1000;
        tender.type = $('.input-tenderRadio:checked').attr("data-type");
        tender.expectedPrice = $('#ExpectedValue').val();
        tender.hidePropose =  $('#HideBidders').prop('checked');
        tender.hideContact =  $('#HideContacts').prop('checked');
        if (tender.type === 'CLOSE') {
            tender.members = [];
            var arrOpt = $('#selectParticipants').children();
            for(var i = 0; i < arrOpt.length; i++) {
                tender.members.push({
                    id: $(arrOpt[i]).attr('value'),
                    name: $(arrOpt[i]).text()
                })
            }
        }

        var naceIds = $('#selectKved').val();
        if(naceIds) tender.naceIds = naceIds;

        tender.address = {};
//        tender.address.googleMapKey = placeKey;
        tender.address.area = $('#SelectArea').val();
        tender.address.city = $('#SelectCity').val();

        $.ajax({
            type: "POST",
            url: "/api/rest/tenderService/tender/create/",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(tender),
            dataType: "json",
            statusCode: {
                201: function (data, textStatus, request) {
                    window.location.href = '/tender/' + data.id;
                }
            }
        });
        event.preventDefault();
    });
//---------------------------- END SUBMIT -------------------------------------------------//

// ---------------------------- BEGIN participants -------------------------------------------------//

    var select = $('#selectParticipants');
    select.chosen({width: '545px', display_selected_options: false});

    $('#selectParticipants_chosen').css("display", "none");

    select.on('change', function() {
        select.children('option:not(:selected)').remove();
        select.trigger("chosen:updated");
    });

    var input = $("#selectParticipants_chosen ul.chosen-choices li.search-field input");

    input.autocomplete({
        source: function (request, response) {
            $.getJSON("/search/autocomplete/profile/ids", {
                term: request.term
            }, function(response) {
                var $search_param = input.val();
                select.children('option:not(:selected)').remove();
                for (var key in response) {
                    if(!select.children('option[value="' + response[key].id +'"]').length) {
                        select.append('<option value="' + response[key].id + '">' + response[key].username + ' </option>');
                    }
                }
                select.trigger("chosen:updated");
                input.val($search_param);
            });
        }
    });

//---------------------------- END participants -------------------------------------------------//
}












