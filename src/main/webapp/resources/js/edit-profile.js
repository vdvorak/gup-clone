var loadedProfile = {};
var updatedProfile = {};

var emailCloneCount = 0;
var contactPhoneCloneCount = 0;
var socInputTemplate = $('.soc-input-group').html();

function setValuesForFieldsFromProfile(profile) {
//            alert('loadedProfile: ' + JSON.stringify(loadedProfile));

    if (profile.imgId != null) {
        $('.moreInformation-img').css('background',
            'url(/api/rest/fileStorage/profile/file/read/id/' + profile.imgId + ') no-repeat center center').css('background-size', 'cover');
    }

    $('#select-type').val(profile.contact.type);
    $('#select-type').change();

    $('#userName').val(profile.username);
    $('#position').val(profile.contact.position);
    $('#nameCompany').val(profile.contact.companyName);
    $('#main-email-info').val(profile.email);
    $('#main-tel-info').val(profile.mainPhoneNumber);
    $('#skype-info').val(profile.contact.skypeUserName);
    $('#info-about-me').val(profile.contact.aboutUs);
    $('#web-addresses').val(profile.contact.linkToWebSite);


    for (var i = 0; i < profile.contact.contactEmails.length; i++) {
        $('.input_email_fields_wrap').append($('.email-input-unit').last().clone())
        $('.email-input-unit input').last().val(profile.contact.contactEmails[i]);
        emailCloneCount++;
    }
    if (profile.contact.contactEmails.length > 0) {
        $('.email-input-unit').first().remove();
    }

    for (var i = 0; i < profile.contact.contactPhones.length; i++) {
        $('.input_tel_fields_wrap').append($('.tel-input-unit').last().clone())
        $('.tel-input-unit input').last().val(profile.contact.contactPhones[i]);
        contactPhoneCloneCount++;
    }

    if (profile.contact.contactPhones.length > 0) {
        $('.tel-input-unit').first().remove();
    }
}

function initializeProfileEntityForUpdate() {
    updatedProfile.id = loadedProfile.id;
    updatedProfile.username = $('#userName').val();
    updatedProfile.contact.type = $('#select-type').val();
    updatedProfile.contact.position = $('#position').val();
    updatedProfile.contact.companyName = $('#nameCompany').val();
    updatedProfile.contact.aboutUs = $('#info-about-me').val();
    updatedProfile.contact.skypeUserName = $('#skype-info').val();
    updatedProfile.contact.linkToWebSite = $('#web-addresses').val();
    updatedProfile.mainPhoneNumber = $('#main-tel-info').val();

    var contactEmails = [];
    $("input[name=myemail]").each(function () {
        if ($(this).val() !== '') {
            contactEmails.push($(this).val());
        }
    });
    updatedProfile.contact.contactEmails = contactEmails;

    var contactPhones = [];
    $("input[name=mytel]").each(function () {
        if ($(this).val() !== '') {
            contactPhones.push($(this).val());
        }
    });

    updatedProfile.contact.contactPhones = contactPhones;

    var socArr = {};
    $(".soc-input").each(function (index) {
        if ($(this).parent().hasClass('show-inp')) {
            var socName = $(this).attr("name");
            if ($(this).val() != '') {
                socArr[socName] = $(this).val();
            }
        }
    });
    updatedProfile.contact.socNetLink = socArr;
}

$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/read/id/" + profileId + "/wholeProfile",
        statusCode: {
            200: function (profile) {
                loadedProfile = profile;
                updatedProfile.contact = loadedProfile.contact;
                updatedProfile.userProfile = loadedProfile.userProfile;

                setValuesForFieldsFromProfile(profile);


// --------------------------------------  BEGIN soc network links  ----------------------------------------------
                // Add/Remove social Input Fields Dynamically with jQuery
                var wrapper = $(".soc-input-group"); //Fields wrapper

                function drawInput(template, name, value) {
                    $('.soc-input-wrap').last().attr('style', 'display:;').addClass('show-inp');
                    $('.soc-input').last().attr('name', name).attr('value', value);
                    $('.soc-input-group').append(socInputTemplate);
                    $('.soc-input-wrap').last().attr('style', 'display: none;').removeClass('show-inp');
                }

// Draw social networks input
                if (loadedProfile.contact.socNetLink) {

                    for (var soc in loadedProfile.contact.socNetLink) {
                        key = loadedProfile.contact.socNetLink[soc];
                        if (soc === "FACEBOOK") {
                            drawInput(socInputTemplate, soc, key)
                        }
                        if (soc === "VKONTAKTE") {
                            drawInput(socInputTemplate, soc, key)
                        }
                        if (soc === "LINKEDIN") {
                            drawInput(socInputTemplate, soc, key)
                        }
                        if (soc === "GOOGLEPLUS") {
                            drawInput(socInputTemplate, soc, key)
                        }
                        if (soc === "TWITTER") {
                            drawInput(socInputTemplate, soc, key)
                        }
                    }
                }

                $(".right-tag a").click(function (e) {
                    e.preventDefault();
                    var socName = $(this).attr("class");
                        if ($('input[name="' + socName +'"]').length){
                            $('input[name="' + socName +'"]').parent('div').remove();
                        }else{
                            $(wrapper).append('<div class="soc-input-wrap show-inp"><input class="soc-input input-info-min" name="' + socName + '" type="text" placeholder = "Страница ' + socName + '"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png" width="15" height="15"></a></div>');
                        }
                });

                $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                    e.preventDefault();
                    $(this).parent('div').remove();
                });
// ---------------------------------------------------- END Soc network links --------------------------------


// ----------------------------------------------------- Multiply phone numbers -----------------------------------
                // Add/Remove phone Input Fields Dynamically with jQuery

                var max_fields_tel = 5; //maximum input boxes allowed + 1
                var tel_wrapper = $(".input_tel_fields_wrap"); //Fields wrapper
                var add_button_tel = $(".add_tel_field_button"); //Add button ID
                var x_tel = 1;

                if (contactPhoneCloneCount > 0) {
                    x_tel = contactPhoneCloneCount; //initial text box count
                }
                $(add_button_tel).click(function (e) { //on add input button click
                    e.preventDefault();
                    if (x_tel < max_fields_tel) { //max input box allowed
                        x_tel++; //text box increment
                        $(tel_wrapper).append('<div class="tel-wrapper-1 tel-input-unit"><input type="tel" name="mytel" class="input-info-min"><a href="#" class="remove_field"><img class="remove_phone" src="/resources/img/minus.png" with="20" height="20"></a><div class="clearfix"></div></div>'); //add input box
                    } else {
                        alert('Максимум 5 контактных телефоноов');
                    }
                });

                $(tel_wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                    e.preventDefault();
                    $(this).parent('div').remove();
                    x_tel--;
                });
// ----------------------------------------------------- Multiply phone numbers -----------------------------------


// ----------------------------------------------------- Multiply emails -----------------------------------
                // Add/Remove phone Input Fields Dynamically with jQuery
                var max_fields_email = 5; //maximum input boxes allowed + 1
                var email_wrapper = $(".input_email_fields_wrap"); //Fields wrapper
                var add_button_email = $(".add_email_field_button"); //Add button ID
                var x_email = 1; // because we have empty default input

                if (emailCloneCount > 0) {
                    x_email = emailCloneCount; //initial text box count
                }
                $(add_button_email).click(function (e) { //on add input button click
                    e.preventDefault();
                    if (x_email < max_fields_email) { //max input box allowed
                        x_email++; //text box increment
                        $(email_wrapper).append('<div class="email-input-unit"><input type="text" name="myemail" class="form-info-input"><a href="#" class="remove_field"><img src="/resources/img/minus.png" with="20" height="20"></a></div>'); //add input box
                    } else {
                        alert('Максимум 5 контактных email-ов');
                    }
                });

                $(email_wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                    e.preventDefault();
                    $(this).parent('div').remove();
                    x_email--;
                });
// ----------------------------------------------------- Multiply emails -----------------------------------
            }
        }
    });
});


$('#updateProfileBtn').on('click', function () {
    initializeProfileEntityForUpdate();

    $.ajax({
        type: "POST",
        url: "/api/rest/profilesService/profile/edit",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(updatedProfile),
        statusCode: {
            200: function () {
                window.location.href = '/profile?id=' + updatedProfile.id;
            }
        }
    });
});

$('#select-type').on('change', function () {
    switch ($('#select-type').val()) {
        case "INDIVIDUAL":
            $('#userNameBlock').show();
            $('#positionBlock').show();
            $('#aboutMe').show();
            $('#companyAddressBlock').hide();
            $('#scopeOfActivityBlock').hide();
            $('#aboutCompany').hide();
            break;
        case "LEGAL_ENTITY":
            $('#aboutMe').hide();
            $('#userNameBlock').hide();
            $('#positionBlock').hide();
            $('#aboutCompany').show();
            $('#nameCompanyBlock').show();
            $('#companyAddressBlock').show();
            $('#scopeOfActivityBlock').show();
            break;
        case "ENTREPRENEUR":
            $('#aboutMe').hide();
            $('#aboutCompany').show();
            $('#positionBlock').show();
            $('#userNameBlock').show();
            $('#scopeOfActivityBlock').show();
            $('#nameCompanyBlock').show();
            $('#companyAddressBlock').show();
            break;

        default:
            $('#userName').show();
            $('#aboutCompany').hide();
            $('#aboutMe').show();
    }
});

$('#addProfileImg').on('click', function () {
    $("#uploadProfilePhotoInput").click();
});

$('#uploadProfilePhotoInput').on('change', function () {
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
// ------------------------------------------- End photo upload block ---------------------------------


//var loadedProfile = {};
//var updatedProfile = {};
//
//var socInputTemplate = $('.soc-input-group').html();
//var contactTelTagHtml = '<input type="tel" name="contactTel" class="input-info-min contactTel">';
//var contactEmailTagHtml = '<input type="email" name="contactEmail" class="form-info-input contactEmail">';
// --------------------------------------  BEGIN cropper  ----------------------------------------------
var image = document.getElementById('cropper-image');
var cropper = new Cropper(image, {
    aspectRatio: 1 / 1,
    crop: function (data) {
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

    $.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/profile/file/upload?cacheImage=1", //
        data: formData,
        enctype: 'multipart/form-data',
//                async: false,
        cache: false,
        contentType: false,
        processData: false,
        statusCode: {
            201: function (data) {
                updatedProfile.imgId = data.id;
                $('.moreInformation-img').css('background',
                    'url(/api/rest/fileStorage/profile/file/read/id/' + updatedProfile.imgId + ') no-repeat center center')
                    .css('background-size', 'cover');
                cropper.replace('/api/rest/fileStorage/profile/file/read/id/' + updatedProfile.imgId);
            },
            400: function () {
                alert('400');
            }
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
