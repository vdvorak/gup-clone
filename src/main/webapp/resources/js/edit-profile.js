var loadedProfile = {};
var updatedProfile = {};

var emailCloneCount = 1;
var contactPhoneCloneCount = 1;
var socInputTemplate = $('.soc-input-group').html();

// --------------------------------------  BEGIN cropper  ----------------------------------------------
var image = document.getElementById('image');
var cropper = new Cropper(image, {
    aspectRatio: 1 / 1,
    crop: function(data) {
        console.log(data.x);
        console.log(data.y);
        console.log(data.width);
        console.log(data.height);
        console.log(data.rotate);
        console.log(data.scaleX);
        console.log(data.scaleY);
    }
});

function dataURItoBlob(dataURI) {
    var binary = atob(dataURI.split(',')[1]);
    var array = [];
    for(var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
    }
    return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
}

$("#btn-cropp-done").click(function() {
    $('#cropperModal').modal('hide');

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
//                        alert('201');
                updatedProfile.contact.pic = data.id;
                $('.moreInformation-img').css('background',
                    'url(/api/rest/fileStorage/profile/file/read/id/' + updatedProfile.contact.pic + ') no-repeat center center')
                    .css("background-size","cover");
                cropper.replace('/api/rest/fileStorage/profile/file/read/id/' + updatedProfile.contact.pic);

            },
            400: function () {
                alert('400');
            }
        }
    });


});

// --------------------------------------  End cropper  ----------------------------------------------

function setValuesForFieldsFromProfile(profile) {
//            alert('loadedProfile: ' + JSON.stringify(loadedProfile));

    if (profile.contact.pic != null) {
        $('.moreInformation-img').css('background',
            'url(/api/rest/fileStorage/profile/file/read/id/' + profile.contact.pic + ') no-repeat center center')
            .css("background-size","cover");
        cropper.replace('/api/rest/fileStorage/profile/file/read/id/' + profile.contact.pic);
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
    $('.email-input-unit').first().remove();


    for (var i = 0; i < profile.contact.contactPhones.length; i++) {
        $('.input_tel_fields_wrap').append($('.tel-input-unit').last().clone())
        $('.tel-input-unit input').last().val(profile.contact.contactPhones[i]);
        contactPhoneCloneCount++;
    }
    $('.tel-input-unit').first().remove();

//                    $('#tel-info').attr("placeholder", profile.contact.O);
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
            socArr[socName] = $(this).val();
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
                var max_fields = 5; //maximum input boxes allowed
                var wrapper = $(".soc-input-group"); //Fields wrapper
                var x = 1;

                function drawInput(template, name, value) {
                    $('.soc-input-wrap').last().attr('style', 'display:;').addClass('show-inp');
                    $('.soc-input').last().attr('name', name).attr('value', value);
                    $('.soc-input-group').append(socInputTemplate);
                    $('.soc-input-wrap').last().attr('style', 'display: none;').removeClass('show-inp');
                }

// Draw social networks input
                if (loadedProfile.contact.socNetLink) {
                    x = Object.keys(loadedProfile.contact.socNetLink).length;

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

                    if (x < max_fields) { //max input box allowed
                        x++; //text box increment
                        $(wrapper).append('<div class="soc-input-wrap show-inp"><input class="soc-input" name="' + socName + '" type="text" placeholder = "Страница ' + socName + '"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png" width="15" height="15"></a></div>');
                    }
                });

                $(wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                    e.preventDefault();
                    $(this).parent('div').remove();
                    x--;
                });
 // ---------------------------------------------------- END Soc network links --------------------------------


 // ----------------------------------------------------- Multiply phone numbers -----------------------------------
                // Add/Remove phone Input Fields Dynamically with jQuery

                var max_fields_tel = 6; //maximum input boxes allowed + 1
                var tel_wrapper = $(".input_tel_fields_wrap"); //Fields wrapper
                var add_button_tel = $(".add_tel_field_button"); //Add button ID

                var x_tel = contactPhoneCloneCount; //initial text box count
                $(add_button_tel).click(function (e) { //on add input button click
                    e.preventDefault();
                    if (x_tel < max_fields_tel) { //max input box allowed
                        x_tel++; //text box increment
                        $(tel_wrapper).append('<div><input type="text" name="mytel"/><a href="#" class="remove_field"><img src="/resources/img/minus.png" with="20" height="20"></a></div>'); //add input box
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

                var max_fields_email = 6; //maximum input boxes allowed + 1
                var email_wrapper = $(".input_email_fields_wrap"); //Fields wrapper
                var add_button_email = $(".add_email_field_button"); //Add button ID

                var x_email = emailCloneCount; //initial text box count
                $(add_button_email).click(function (e) { //on add input button click
                    e.preventDefault();
                    if (x_email < max_fields_email) { //max input box allowed
                        x_email++; //text box increment
                        $(email_wrapper).append('<div><input type="text" name="myemail"/><a href="#" class="remove_field"><img src="/resources/img/minus.png" with="20" height="20"></a></div>'); //add input box
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
                window.location.href = '/profile/id/' + updatedProfile.id;
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

// ------------------------------------------- Photo upload block ---------------------------------
$('#uploadProfilePhotoInput').on('change', function () {
    /*$.ajax({
        type: "POST",
        url: "/api/rest/fileStorage/profile/file/upload?cacheImage=1", //
        data: new FormData($("#uploadProfilePhotoForm")[0]),
        enctype: 'multipart/form-data',
//                async: false,
        cache: false,
        contentType: false,
        processData: false,
        statusCode: {
            201: function (data) {
//                        alert('201');
                updatedProfile.contact.pic = data.id;
                $('.moreInformation-img').css('background',
                    'url(/api/rest/fileStorage/profile/file/read/id/' + updatedProfile.contact.pic + ') no-repeat center center');

            },
            400: function () {
                alert('400');
            }
        }
    });*/

    var files = event.currentTarget.files;

    var reader  = new FileReader();

    reader.addEventListener("load", function () {
        cropper.replace(reader.result);
    }, false);

    if (files[0]) {
        reader.readAsDataURL(files[0]);
    }

    $('#cropperModal').modal('show');
});
// ------------------------------------------- End photo upload block ---------------------------------