/**
 * Created by Юля on 17.05.2016.
 */
    'use strict'

    if (typeof loggedInProfile == 'undefined') {
        $('#doer-create-container').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для редактирования исполнителя вам необходимо зарегистрироваться</i></p></div>')
    }

        var oldImgId,
            imgId,
            gupValidator = new window.GupValidator.Constructor('doer').init();

        $("textarea").on('keyup', countTextLength);

        function countTextLength() {
            var counter = $("#p-textlength");
            var currentString = $("#doerDescription").val();
            counter.text("Количество символов: " + currentString.length);
            if (currentString.length <= 50) {  /*or whatever your number is*/
                counter.css("color", "red");
            } else {
                if (currentString.length > 4000) {
                    counter.css("color", "red");
                } else {
                    counter.css("color", "green");
                }
            }
        }

        //----------------------------------------------------- Load resources -----------------------------------------------
        var loadDoer = loadDoerInfo();

        $.when(loadNace, loadDoer).done(function (response1, response2) {
            var res1 = response1[0],
                res2 = response2[0];

            var select = $('#doerNaceIds');
            for (var i = 0; i < res1.length; i++) {
                var option = $('<option id="' + res1[i].id + '" value="' + res1[i].id + '">' + res1[i].id + ": " + res1[i].name + '</option>');
                select.append(option);
            }
            select.val(res2.naceIds);

            $("#doerNaceIds").chosen();
        });

        function loadDoerInfo() {
            return $.ajax({
                type: "POST",
                url: "/api/rest/doerService/doer/read/id/" + doerId,
                statusCode: {
                    200: function (doer) {
                        oldImgId = doer.imageId;
                        imgId = oldImgId;

                        if (imgId) {
                            $('.doer-img ul').find('img').attr("src", "/api/rest/fileStorage/DOER/file/read/id/" + imgId);
                            $('.doer-img ul li').removeClass('li-defaultIMG');
                        }
                        $('#doerName').val(doer.title);
                        $('#doerDescription').val(doer.body);

                        countTextLength();
                    }
                }
            });
        }

        //----------------------------------------------------- Load resources -----------------------------------------------

        //----------------------------------------------------- Image form -----------------------------------------------
        $('.doerCreationSubmit').click(function () {
            $('#photoInput').trigger('click');
        });

        $('#photoInput').on('change', function (event) {
            event.preventDefault();

            var files = event.currentTarget.files;
            var formImg = new FormData();
            formImg.append('file', files[0]);

            if (imgId) deleteImg();

            $.ajax({
                type: "POST",
                url: "/api/rest/fileStorage/DOER/file/upload/",
                data: formImg,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data, textStatus, request) {
                    imgId = data.id;
                    $('.doer-img ul').find('img').attr("src", "/api/rest/fileStorage/DOER/file/read/id/" + imgId);
                    $('.doer-img ul li').removeClass('li-defaultIMG');
                }
            });

            $("#photoInput").val("");
        });
        //----------------------------------------------------- Image form -----------------------------------------------

        ///----------------------Delete photo from  DB-----------------------------------------
        function deleteImgFromDB(id) {
            $('.doer-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
            $('.doer-img ul li').addClass('li-defaultIMG');
            $.ajax({
                url: '/api/rest/fileStorage/DOER/file/delete/id/' + id,
                method: 'POST',
                success: function (response) {
                },
                error: function (response) {
                }
            });
        }

        function deleteImg() {
            imgId = '';
            $('.doer-img ul').find('img').attr("src", "/resources/images/no_photo.jpg");
            $('.doer-img ul li').addClass('li-defaultIMG');
        }

        ///----------------------Delete photo from  DB-----------------------------------------

        ///------------------------- Upload Doer -----------------------------------------------
        $('#createDoer').on('click', function (event) {
            event.preventDefault();

            var doer = {};
            doer.id = doerId;
            doer.title = $('#doerName').val();
            doer.body = $('#doerDescription').val();
            doer.imageId = imgId;
            doer.naceIds = $("#doerNaceIds").val();

            if (oldImgId) deleteImgFromDB(oldImgId);

            gupValidator.validate(doer);
            if (!gupValidator.isValid) return;

            $.ajax({
                type: "POST",
                url: "/api/rest/doerService/doer/id/" + doerId + "/update/",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(doer),
                success: function (response) {
                    window.location.href = '/doer/' + response.id;
                }
            });
        });
    ///------------------------- Upload Doer -----------------------------------------------

    //------------------ BEGIN DELETE TENDER ------------------------------------//
    $('#deleteDoer').on('click', function () {
        $("#confirmDoerDelete").show();
    });

    $('#cancelDoerDelBtn').on('click', function () {
        $("#confirmDoerDelete").hide();
    });

    $('#confirmDoerDelBtn').on('click', function (event) {
        event.preventDefault();

        $.ajax({
            type: "POST",
            url: "/api/rest/doerService/doer/delete/" + doerId,
            statusCode: {
                204: function () {
                    window.location.href = '/tenders#tabs1-investment';
                }
            }
        });
    });
    //------------------ END DELETE TENDER ------------------------------------//

