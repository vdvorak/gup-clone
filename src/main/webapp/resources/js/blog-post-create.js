/**
 * Created by Юля on 02.06.2016.
 */
(function() {
    'use strict';

    var imgsArr = {},
         cities = {};

    var gupValidator = new window.GupValidator.Constructor('blog-post').init();

    initBlogPost();

    function initBlogPost() {
        loadCities();
        initTinymce();
        initEventHandlers();
    }

    function initEventHandlers() {
        $('#region-container').find('li').click(selectRegion);
        $('button.SendEdition').on('click', onSubmitBlogPost);

        $('.blogCreationSubmit').click(function () {$('#photoInput').trigger('click')});
        $('#photoInput').change(onPhotoInputChange);

        initDropZoneEventHandlers();
    }

// ---------------    LOAD RESOURCES    --------------------------//
    function loadCities() {
        return $.ajax({
            type: "GET",
            url: "/resources/json/cities.json",
            dataType: 'json',
            success: function (response) {
                cities = response;
            }
        });
    }

// ---------------   END LOAD RESOURCES    --------------------------//


//----------------------  HTML EDITOR-------------------------------------//
    function initTinymce() {
        tinymce.init({
            selector: '#newsCreationDescription',
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

//---------------------- END  HTML EDITOR-------------------------------------//


// --------------------- MAIN FORM CONSTRUCTION ----------------------//
    function BlogPost(){
        this.blogId = blogId;
    }

    BlogPost.prototype.setBlogPostTitle = setBlogPostTitle;
    BlogPost.prototype.setBlogPostDescription = setBlogPostDescription;
    BlogPost.prototype.setBlogPostImages = setBlogPostImages;
    BlogPost.prototype.setBlogPostAddress = setBlogPostAddress;
    BlogPost.prototype.setBlogPostCategories = setBlogPostCategories;

    function setBlogPostTitle() {
        this.title = $('#newsTitle').val();
        return this;
    }

    function setBlogPostDescription() {
        this.text = tinymce.activeEditor.getContent({format: 'raw'});
        return this;
    }

    function setBlogPostImages() {
        checkMainImg();

        this.imagesIds = imgsArr;
        return this;
    }

    function setBlogPostAddress() {
        var city = $('#text-city').text(),
            area = $('#text-region').text();

        this.address = {};
        this.address.country = 'Украина';
        if (city !== 'Выберите город' && city !== 'Все города') this.address.city = city;
        if (area !== 'Выберите область') this.address.area = area;

        return this;
    }

    function setBlogPostCategories() {
        var inpCategories = [];
        $('.cat input').each(function (index) {
            if ($(this).prop('checked')) {
                inpCategories.push($(this).attr('name'));
            }
        });
        this.categories = inpCategories;

        return this;
    }

    function onSubmitBlogPost(event) {
        event.preventDefault();

        var blogPost = new BlogPost()
            .setBlogPostTitle()
            .setBlogPostDescription()
            .setBlogPostImages()
            .setBlogPostAddress()
            .setBlogPostCategories();

        gupValidator.validate(blogPost);
        if (!gupValidator.isValid) return;

        createBlogPost(blogPost);
    }

    function createBlogPost(blogPost) {
        return $.ajax({
            type: "POST",
            url: "/api/rest/newsService/blogPost/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(blogPost),
            statusCode: {
                201: function (data, textStatus, request) {
                    window.location.href = '/blog-post/' + data.id;
                }
            }
        });
    }

// --------------------- END MAIN FORM CONSTRUCTION ----------------------//


// -------------------------- PHOTO SUBMIT AND DELETE ------------------------------//
    function initDropZoneEventHandlers() {
        var dropZone = document.getElementById('drop_zone');
        dropZone.addEventListener('dragover', handleDragOver, false);
        dropZone.addEventListener('drop', handleFileSelect, false);
    }

    function handleFileSelect(evt) {
        evt.stopPropagation();
        evt.preventDefault();

        var files = evt.dataTransfer.files; // FileList object.
        for (var i = 0, f; f = files[i]; i++) {
            uploadImage(f);
        }
    }

    function handleDragOver(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.dataTransfer.dropEffect = 'copy'; // Explicitly show this is a copy.
    }

    function onPhotoInputChange(event) {
        event.preventDefault();

        var files = event.currentTarget.files;
        for (var i = 0, f; f = files[i]; i++) {
            uploadImage(f);
        }
    }

    function appendImg(id) {
        $(".li-defaultIMG").css("display", "none");
        var cloneImg = $(".li-defaultIMG").clone()
            .removeClass('li-defaultIMG')
            .css("display", "inline-block");
        cloneImg.find('img')
            .attr("alt", "")
            .attr("src", '/api/rest/fileStorage/NEWS/file/read/id/' + id)
            .attr("id", id)
            .click(onClickSetMainImg);
        cloneImg.find('span')
            .click(deleteImg);
        cloneImg.appendTo('.ul-img-container');
    }

    function onClickSetMainImg() {
        var img = $(event.currentTarget);
        var id = img.attr("id");
        var isMain = img.hasClass("mainImg");
        var allImgs = $("#tender-img-block").find("img");
        for (var i = 0; i < allImgs.length; i++) {
            var curImg = $(allImgs[i]);
            if (curImg.hasClass("mainImg")) {
                curImg.removeClass("mainImg");
            }
        }
        if (!isMain) img.addClass("mainImg");

        for (var key in imgsArr) {
            if (imgsArr[key] === "pic1") {
                imgsArr[key] = "image";
            }
        }

        if (img.hasClass("mainImg")) {
            imgsArr[id] = "pic1";
        }
    }

    function deleteImg() {
        var idImg = $(event.currentTarget).parent()
            .find('img')
            .attr('id');
        delete imgsArr[idImg];
        $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/delete/id/" + idImg,
            success: function (data, textStatus, request) {
                $('#' + idImg).parent().remove();

                var numberImg = $(".ul-img-container").find('img').length;
                if (numberImg < 2) {
                    $(".li-defaultIMG").css("display", "inline-block");
                }
            }
        });
    }

    function checkMainImg() {
        var hasMainImg = false;

        for (var key in imgsArr) {
            if (imgsArr[key] === 'pic1') {
                hasMainImg = true;
                break;
            }
        }

        if (!hasMainImg) {
            for (var key in imgsArr) {
                imgsArr[key] = 'pic1';
                break;
            }
        }
    }

    function uploadImage(file) {
        $.when(makeRequestUploadImage(file)).done(function(data) {
            if (Object.keys(imgsArr).length < 15) {
                var id = data.id,
                     isImage = file.type.substring(0, 5) === 'image';
                if (isImage) {
                    imgsArr[id] = "image";
                    appendImg(id);
                }
            }
        })
    }

    function makeRequestUploadImage(file) {
        var fd = new FormData();
        fd.append('file', file);
        return $.ajax({
            type: "POST",
            url: "/api/rest/fileStorage/NEWS/file/upload/",
            data: fd,
            async: false,
            cache: false,
            contentType: false,
            processData: false
        });
    }

// -------------------------- END PHOTO SUBMIT AND DELETE ------------------------------//


//--------------------------- REGIONS LIST --------------------------------------------//

   function selectRegion(event) {
        event.preventDefault();

        var region = $(event.currentTarget).children('a').text();

        $('#text-region').text(region);
        $('#city-container').find('li').remove();
        $('#text-city').text('Выберите город');

        if (region === 'Вся Украина') {
            $('#city-container').css('display', 'none');
        } else {
            drawCities(region);
        }
    }

    function drawCities(area) {
        var citiesArr = cities[area];

        var parentBlock = $('#city-container').find('.multi-column-dropdown').first();
        var li = $('<li><a href="#" style="font-weight: bold">Все города</a></li>').click(selectCity);
        parentBlock.append(li);

        var numInColumn = citiesArr.length / 2 + (citiesArr.length % 2);
        for (var i = 0; i < citiesArr.length; i++) {
            parentBlock = (i + 2 <= numInColumn) ? $('#city-container').find('.multi-column-dropdown').first() : $('#city-container').find('.multi-column-dropdown').last();
            li = $('<li><a href="#">' + citiesArr[i] + '</a></li>').click(selectCity);
            parentBlock.append(li);
        }

        $('#city-container').css('display', 'inline-block');
    }

    function selectCity(event) {
        event.preventDefault();
        var city = $(event.currentTarget).children('a').text();
        $('#text-city').text(city);
    }
//--------------------------- END REGIONS LIST --------------------------------------------//
})();