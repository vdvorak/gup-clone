if (typeof loggedInProfile == 'undefined') {
    $('#blog-create-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания объявления вам необходимо зарегистрироваться</i></p></div>')
}else{
    var imgId = '';
    var blog = {};

// --------------------------------------  BEGIN cropper  ----------------------------------------------
    var image = document.getElementById('cropper-image');
    var cropper = new Cropper(image, {
        aspectRatio: 1 / 1,
        crop: function (data) {
            console.log(data.x);
            console.log(data.y);
            console.log(data.width);
            console.log(data.height);
            console.log(data.rotate);
            console.log(data.scaleX);
            console.log(data.scaleY);
        }
    });

    $(".cropper-btn-cancel").click(function () {
        $('#cropperModal').css('display', "none");
    });

    $(window).click(function (event) {
        var modal = document.getElementById('cropperModal');
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });

    $(".cropper-btn-success").click(function () {
        $('#cropperModal').css('display', "none");

        var canvas = cropper.getCroppedCanvas();
        var dataURL = canvas.toDataURL('image/jpeg', 0.5);
        var blob = dataURItoBlob(dataURL);

        cropper.replace(dataURL);

        var formData = new FormData();
        formData.append('file', blob);

        if (imgId !== '') {
            deleteImgFromDB();
        }

        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/upload/",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data, textStatus, request) {
                imgId = data.id;
                $('.blog-img ul').find('img').attr("src", "/api/rest/fileStorage/NEWS/file/read/id/" + imgId);
                $('.blog-img ul li').removeClass('li-defaultIMG');
                cropper.replace('/api/rest/fileStorage/NEWS/file/read/id/' + imgId);
            }
        });
    });

    function dataURItoBlob(dataURI) {
        var binary = atob(dataURI.split(',')[1]);
        var array = [];
        for (var i = 0; i < binary.length; i++) {
            array.push(binary.charCodeAt(i));
        }
        return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
    }
// --------------------------------------  END cropper  ----------------------------------------------

// -------------------------------------------------------BEGIN soc network links --------------------------------------------
// Add/Remove social Input Fields Dynamically with jQuery

    var max_fields = 6; //maximum input boxes allowed
//var wrapper = $(".input_soc_wrap"); //Fields wrapper

    var cur_fields = 1;

    $(".img-responsive").click(function (e) {
        e.preventDefault();
        var el = e.currentTarget;
        var socName = $(el).attr("alt");
        var link = $('div.group-info').find('input[name="' + socName + '"]');
        if(link.length) {
            var linkParent = link.parent('div:not(.group-info)');
            if (linkParent.length) {
                linkParent.remove();
                cur_fields--;
            }
        } else if (cur_fields < max_fields && !link.length) {
            addSocialLink(socName);
        }
    });

    function deleteSocialLink(event) {
        event.preventDefault();
        var soc = $(event.currentTarget).parent().remove();
        cur_fields--;
    }

    function addSocialLink(socName) {
        var placeholder = (socName === "TWITTER") ? "Добавить ссылку на Twitter" :
            (socName === "FACEBOOK") ? "Добавить ссылку на Facebook" :
                (socName === "SKYPE") ? "Добавить ссылку на Skype" :
                    (socName === "VKONTAKTE") ? "Добавить ссылку на Vkontakte" :
                        (socName === "GOOGLEPLUS") ? "Добавить ссылку на Google +" :
                            (socName === "LINKEDIN") ? "Добавить ссылку на LinkedIn" : "Добавить ссылку";


        var social = $(".blog-social-container:first").clone();
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

// ---------------------------------------------------- END Soc network links --------------------------------------

// -------------------------------------------------------BEGIN drop zone ------------------------------------------

    var dropZone = document.getElementsByClassName('drop_zone');
    for (var i = 0; i < dropZone.length; i++) {
        dropZone[i].addEventListener('dragover', handleDragOver, false);
        dropZone[i].addEventListener('drop', handleFileSelect, false);
    }

    function handleFileSelect(evt) {
        evt.stopPropagation();
        evt.preventDefault();

        var files = evt.dataTransfer.files; // FileList object.

        var reader = new FileReader();

        reader.addEventListener("load", function () {
            cropper.replace(reader.result);
        }, false);

        if (files[0]) {
            reader.readAsDataURL(files[0]);
        }
        $('#cropperModal').css('display', "block");

    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }

// ---------------------------------------------------- END Drop zone --------------------------------------

//----------------------------------------------------- Begin Images-----------------------------------------
    $('.blogCreationSubmit').click(function () {
        $('#photoInput').trigger('click');
    });

    $('#photoInput').change(function (event) {
        event.preventDefault();

        var files = event.currentTarget.files;

        var reader = new FileReader();

        reader.addEventListener("load", function () {
            cropper.replace(reader.result);
        }, false);

        if (files[0]) {
            reader.readAsDataURL(files[0]);
        }

        $('#cropperModal').css('display', "block");
    });

    function deleteImgFromDB() {
        $('.blog-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
        $('.blog-img ul li').addClass('li-defaultIMG');
        $.ajax({
            url: '/api/rest/fileStorage/NEWS/file/delete/id/' + imgId,
            method: 'POST',
            success: function (response) {
            },
            error: function (response) {
            }
        });
    }

//----------------------------------------------------- End Images -----------------------------------------------


///------------------------- Upload Blog -----------------------------------------------

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
            return /^(http:\/\/|https:\/\/)?(www\.)?vk\.com\/(\w|\d)+?\/?$/.test(url);
        } else if (socName === "SKYPE") {
            return /[a-zA-Z][a-zA-Z0-9\.,\-_]{5,31}/.test(url);
        } else {
            return false;
        }
    }

    function validateBlog() {
        $('.error-validation').removeClass('error-validation');

        var arrValidate = [];

        var title = $('#blogTitle').val();
        var description = $('#blogCreationDescription').val();

        if (title.length > 70 || title.length < 2)  arrValidate.push($('#blogTitle'));
        if (description.length > 5000 || description.length < 50)  arrValidate.push($('#blogCreationDescription'));

        for (var i = 0; i < arrValidate.length; i++) {
            arrValidate[i].addClass('error-validation');
        }
        if (arrValidate.length) {
            return false;
        } else {
            return true;
        }
    }

    $(document).on('click', 'button.SendEdition', function (event) {
        event.preventDefault();

        if (validateBlog()) {

            blog.title = $('#blogTitle').val();
            blog.description = $('#blogCreationDescription').val();
            blog.imageId = imgId;

            var socArr = {};
            $(".group-info").find('input').each(function (index) {
                var socName = $(this).attr("name");
                var url = $(this).val();
                if (isMatchPatternSocialLinks(socName, url) && url.length) {
                    socArr[socName] = url;
                }
            });

            blog.socLinks = socArr;

            $.ajax({
                type: "POST",
                url: "/api/rest/newsService/blog/create",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(blog),
                success: function (response) {
                    window.location.href = '/blog/' + response.id;
                },
                error: function (response) {
                    alert("Внутренняя ошибка сервера");
                }
            });
        }
    });
///------------------------- Upload Blog -----------------------------------------------
}





