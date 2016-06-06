
(function () {
    'use strict';

    var cropper,
        imgId = '',
        max_fields = 6,
        cur_fields = 1;

    var gupValidator = new window.GupValidator.Constructor('blog').init();

    initBlog();

    function initBlog() {
        if (typeof loggedInProfile == 'undefined') {
            $('#blog-create-container').empty()
                .append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для редактирования блога вам необходимо зарегистрироваться</i></p></div>');
            return;
        }

        initEventHandlers();
    }

    function initEventHandlers() {
        $("#blogCreationDescription").on('keyup', countTextLength);
        $(".img-responsive").click(onClickAddSocial);
        $('button.SendEdition').on('click', createBlog);
        initImagesEventHandlers();
    }

    function initImagesEventHandlers() {
        var image = document.getElementById('cropper-image');
        cropper = new Cropper(image, {
            aspectRatio: 1 / 1,
            crop: function (data) {
            }
        });

        $(window).click(hideModalWindow);

        $(".cropper-btn-cancel").click(function () {
            $('#cropperModal').css('display', "none");
        });
        $(".cropper-btn-success").on('click', cropImage);

        $('#photoInput').on('change', onChangePhotoInput);
        $('.blogCreationSubmit').click(function () {
            $('#photoInput').trigger('click');
        });

        initDropZone();
    }

    // --------------------------------------  BEGIN Images  ----------------------------------------------

    function cropImage(event) {
        $('#cropperModal').css('display', "none");

        var canvas = cropper.getCroppedCanvas(),
            dataURL = canvas.toDataURL('image/jpeg', 0.5),
            blob = dataURItoBlob(dataURL),
            formData = new FormData();

        cropper.replace(dataURL);
        formData.append('file', blob);
        if (imgId !== '') deleteImgFromDB();

        $.when(uploadImage(formData)).done(function (data) {
            imgId = data.id;

            var block = $('.blog-img ul');
            block.find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
            block.find('li').removeClass('li-defaultIMG');
            cropper.replace('/api/rest/fileStorage/NEWS/file/read/id/' + imgId);
        })
        $("#photoInput").val("");
    }

    function uploadImage(formData) {
        return $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/upload/",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false
        });
    }

    function onChangePhotoInput(event) {
        event.preventDefault();

        openCropper(event.currentTarget.files);
    }

    function openCropper(files) {
        var reader = new FileReader();

        reader.addEventListener("load", function () {
            cropper.replace(reader.result);
        }, false);

        if (files[0]) reader.readAsDataURL(files[0]);

        $('#cropperModal').css('display', "block");
    }

    function hideModalWindow(event) {
        var modal = document.getElementById('cropperModal');
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }

    function dataURItoBlob(dataURI) {
        var binary = atob(dataURI.split(',')[1]);
        var array = [];
        for (var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
    }

    function initDropZone() {
        var dropZone = document.getElementsByClassName('drop_zone');
        for (var i = 0; i < dropZone.length; i++) {
            dropZone[i].addEventListener('dragover', handleDragOver, false);
            dropZone[i].addEventListener('drop', function (evt) {
                handleFileSelect(evt)
            }, false);
        }
    }

    function handleFileSelect(evt) {
        evt.stopPropagation();
        evt.preventDefault();

        openCropper(evt.dataTransfer.files);
    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }

    function deleteImgFromDB() {
        var block = $('.blog-img ul');
        block.find('img').attr("src", "/resources/images/no_photo.jpg");
        block.find('li').addClass('li-defaultIMG');
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + imgId,
            method: 'POST'
        });
    }

    // --------------------------------------  END images  ----------------------------------------------

    // -------------------------------------------------------BEGIN soc network links --------------------------------------------
    function addSocialLink(socName) {
        var placeholder = getSocLinkPlaceHolder(socName),
            social = $(".blog-social-container:first").clone();

        social.css('display', 'block')
            .children('.blog-social-input')
            .attr("placeholder", placeholder)
            .removeAttr("id")
            .attr("name", socName);
        social.children('img')
            .click(deleteSocialLink);
        social.appendTo(".group-info");
        cur_fields++;

        return social;
    }

    function getSocLinkPlaceHolder(socName) {
        return (socName === "TWITTER") ? "Добавить ссылку на Twitter" :
            (socName === "FACEBOOK") ? "Добавить ссылку на Facebook" :
                (socName === "SKYPE") ? "Добавить ссылку на Skype" :
                    (socName === "VKONTAKTE") ? "Добавить ссылку на Vkontakte" :
                        (socName === "GOOGLEPLUS") ? "Добавить ссылку на Google +" :
                            (socName === "LINKEDIN") ? "Добавить ссылку на LinkedIn" : "Добавить ссылку";
    }

    function deleteSocialLink(event) {
        event.preventDefault();
        var soc = $(event.currentTarget).parent().remove();
        cur_fields--;
    }

    function onClickAddSocial(e) {
        e.preventDefault();

        var socName = $(this).attr("alt"),
            link = $('div.group-info').find('input[name="' + socName + '"]');

        if (link.length) {
            var linkParent = link.parent('div:not(.group-info)');
            if (linkParent.length) {
                linkParent.remove();
                cur_fields--;
            }
        } else if (cur_fields < max_fields && !link.length) {
            addSocialLink(socName);
        }
    }

    function getSocLinks() {
        var socArr = {};
        $(".group-info").find('input').each(function (index) {
            var socName = $(this).attr("name"),
                url = $(this).val();
            if (isMatchPatternSocialLinks(socName, url) && url.length) {
                socArr[socName] = (socName === 'SKYPE') ? 'skype:' + url + '?call' : url;
            }
        });
        return socArr;
    }

    function isMatchPatternSocialLinks(socName, url) {
        if (socName === "FACEBOOK") {
            return /^(https?:\/\/)?(www\.)?facebook.com\/[a-zA-Z0-9(\.\?)?]/.test(url);
        } else if (socName === "TWITTER") {
            return /(?:http:\/\/)?(?:www\.)?twitter\.com\/(?:(?:\w)*#!\/)?(?:pages\/)?(?:[\w\-]*\/)*([\w\-]*)/.test(url);
        } else if (socName === "LINKEDIN") {
            return /(http|https):\/\/?(?:www\.)?linkedin.com(\w+:{0,1}\w*@)?(\S+)(:([0-9])+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/.test(url);
        } else if (socName === "GOOGLEPLUS") {
            return /((http|https):\/\/)?(www[.])?plus\.google\.com\/.?\/?.?\/?([0-9]*)/.test(url);
        } else if (socName === "VKONTAKTE") {
            return /^(http:\/\/|https:\/\/)?(www\.)?vk\.com\/(\w|\d|.)+?\/?$/.test(url);
        } else if (socName === "SKYPE") {
            return /[a-zA-Z][a-zA-Z0-9\.,\-_]{5,31}/.test(url);
        } else {
            return false;
        }
    }

    // -------------------------------------------------------END soc network links --------------------------------------------

    ///------------------------- Upload Blog -----------------------------------------------//
    function createBlog(event) {
        event.preventDefault();
        var blog = {};
        blog.title = $('#blogTitle').val();
        blog.description = $('#blogCreationDescription').val();
        blog.imageId = imgId;
        blog.socLinks = getSocLinks();

        gupValidator.validate(blog);
        if (!gupValidator.isValid) return;

        sendCreatedBlog(blog);
    }

    function sendCreatedBlog(blog) {
        return $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blog),
            statusCode: {
                200: function (response) {
                    window.location.href = '/blog/' + response.id;
                }
            }
        });
    }

    ///------------------------- Upload Blog -----------------------------------------------//

    ///------------------------- Other -----------------------------------------------//
    function countTextLength() {
        var counter = $("#p-textlength");
        var currentString = $("#blogCreationDescription").val();
        counter.text("Количество символов: " + currentString.length);
        if (currentString.length <= 50) {  /*or whatever your number is*/
            counter.css("color", "red");
        } else {
            if (currentString.length > 5000) {
                counter.css("color", "red");
            } else {
                counter.css("color", "green");
            }
        }
    }

    ///------------------------- Other -----------------------------------------------//
})();

