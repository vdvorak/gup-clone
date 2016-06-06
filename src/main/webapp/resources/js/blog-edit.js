/**
 * Created by Юля on 01.06.2016.
 */
(function () {
    'use strict';

    var imgId = '';
    var oldImgId = '';
    var max_fields = 6;//maximum input boxes allowed
    var cur_fields = 1;

    var gupValidator = new window.GupValidator.Constructor('blog').init();

    initBlog();

    function initBlog() {
        if (typeof loggedInProfile == 'undefined') {
            $('#blog-create-container').empty()
                .append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для редактирования блога вам необходимо зарегистрироваться</i></p></div>');
            return;
        }

        var xhr = loadBlogData();
        $.when(xhr).done(initBlogData).always(countTextLength);

        initEventHandlers();
    }

    function loadBlogData() {
        return $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/id/" + blogId + "/read",
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        });
    }

    function initBlogData(blog) {
        var imgId = blog.imageId,
            oldImgId = imgId,
            socialLinks = blog.socLinks,
            firstFacebookLink = "";
        for (var key in socialLinks) {
            if (!firstFacebookLink && key === 'FACEBOOK') {
                firstFacebookLink = socialLinks[key];
                $('#blogCreationSocial').val(firstFacebookLink);
            } else {
                var newSocLink = addSocialLink(key)
                newSocLink.children('input').val((key === 'SKYPE') ? socialLinks[key].substring(6, socialLinks[key].length - 5) : socialLinks[key]);
            }
        }
    }

    function initEventHandlers() {
        $("#blogCreationDescription").on('keyup', countTextLength);
        $(".img-responsive").click(onClickAddSocial);
        $('button.SendEdition').on('click', editBlog);
        initImagesEventHandlers();
        initDeleteBlogEventHandlers();
    }

    function initDeleteBlogEventHandlers() {
        $('#btn-blog-delete').on('click', function () {
            $("#confirmBlogDelete").show();
        });

        $('#cancelBlogDelBtn').on('click', function () {
            $("#confirmBlogDelete").hide();
        });

        $('#confirmBlogDelBtn').on('click', makeRequestDeleteBlog);
    }

    function makeRequestDeleteBlog() {
        return $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/id/" + blogId + "/delete",
            statusCode: {
                204: function () {
                    window.location.href = '/blogs-and-news';
                }
            }
        });
    }

    function initImagesEventHandlers() {
        var image = document.getElementById('cropper-image');
        var cropper = new Cropper(image, {
            aspectRatio: 1 / 1,
            crop: function (data) {
            }
        });

        $(window).click(hideModalWindow);

        $(".cropper-btn-cancel").click(function () {
            $('#cropperModal').css('display', "none");
        });
        $(".cropper-btn-success").on('click', {cropper: cropper}, cropImage);

        $('#photoInput').on('change', {cropper: cropper}, onChangePhotoInput);
        $('.blogCreationSubmit').click(function () {
            $('#photoInput').trigger('click');
        });

        initDropZone(cropper);
    }

    // --------------------------------------  BEGIN Images  ----------------------------------------------

    function cropImage(event) {
        $('#cropperModal').css('display', "none");

        var cropper = event.data.cropper,
            canvas = cropper.getCroppedCanvas(),
            dataURL = canvas.toDataURL('image/jpeg', 0.5),
            blob = dataURItoBlob(dataURL),
            formData = new FormData();

        cropper.replace(dataURL);
        formData.append('file', blob);
        if (imgId !== '') deleteImg();

        $.when(uploadImage(formData)).done(function (data) {
            imgId = data.id;
            $('.blog-img ul').find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
            $('.blog-img ul li').removeClass('li-defaultIMG');
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

        var files = event.currentTarget.files,
            cropper = event.data.cropper;

        openCropper(cropper, files);
    }

    function openCropper(cropper, files) {
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

    function initDropZone(cropper) {
        var dropZone = document.getElementsByClassName('drop_zone');
        for (var i = 0; i < dropZone.length; i++) {
            dropZone[i].addEventListener('dragover', handleDragOver, false);
            dropZone[i].addEventListener('drop', function (evt) {
                handleFileSelect(evt, cropper)
            }, false);
        }
    }

    function handleFileSelect(evt, cropper) {
        evt.stopPropagation();
        evt.preventDefault();

        var files = evt.dataTransfer.files; // FileList object.
        openCropper(cropper, files);
    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }

    function deleteImgFromDB(picId) {
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + picId,
            method: 'POST'
        });
    }

    function deleteImg() {
        imgId = '';
        $('.blog-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
        $('.blog-img ul li').addClass('li-defaultIMG');
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

    function editBlog(event) {
        event.preventDefault();
        var blog = {};
        blog.id = blogId;
        blog.title = $('#blogTitle').val();
        blog.description = $('#blogCreationDescription').val();
        blog.imageId = imgId;
        if (oldImgId !== imgId) deleteImgFromDB(oldImgId);
        blog.socLinks = getSocLinks();

        gupValidator.validate(blog);
        if (!gupValidator.isValid) return;

        sendUpdatedBlog(blog);
    }

    function sendUpdatedBlog(blog) {
        return $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blog/edit",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blog),
            statusCode: {
                200: function (response) {
                    window.location.href = '/blog/' + blogSeoUrl;
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
