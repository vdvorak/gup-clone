var loadedProfile = {};
var updatedProfile = {};

var socInputTemplate = $('.soc-input-group').html();
var contactTelTagHtml = '<input type="tel" name="contactTel" class="input-info-min contactTel">';
var contactEmailTagHtml = '<input type="email" name="contactEmail" class="form-info-input contactEmail">';
// --------------------------------------  BEGIN cropper  ----------------------------------------------
var image = document.getElementById('cropper-image');
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

$(".cropper-btn-cancel").click(function() {
    $('#cropperModal').css('display',"none");
});

$(window).click(function(event) {
    var modal = document.getElementById('cropperModal');
    if (event.target == modal) {
        modal.style.display = "none";
    }
});

$(".cropper-btn-success").click(function() {
    $('#cropperModal').css('display',"none");

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
                    .css("background-size","cover");
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
    for(var i = 0; i < binary.length; i++) {
        array.push(binary.charCodeAt(i));
    }
    return new Blob([new Uint8Array(array)], {type: 'image/jpeg'});
}
// --------------------------------------  END cropper  ----------------------------------------------

function setValuesForFieldsFromProfile(profile) {
//            alert('loadedProfile: ' + JSON.stringify(loadedProfile));

    if (profile.imgId != null) {
        $('.moreInformation-img').css('background',
                'url(/api/rest/fileStorage/profile/file/read/id/' + profile.imgId + ') no-repeat center center')
            .css("background-size", "cover");
        cropper.replace('/api/rest/fileStorage/profile/file/read/id/' + profile.imgId);
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
        if (i > 0) {
            $('#contactEmailsBlock').append('<input type="email" name="contactEmail" class="form-info-input contactEmail">');
        }
        $("input[name=contactEmail]").last().val(profile.contact.contactEmails[i]);
    }

    for (var i = 0; i < profile.contact.contactPhones.length; i++) {
        if (i > 0) {
            $('#contactPhonesBlock').append(contactTelTagHtml);
        }
        $("input[name=contactTel]").last().val(profile.contact.contactPhones[i]);
    }

    if (loadedProfile.contact.socNetLink) {
        for (var soc in loadedProfile.contact.socNetLink) {
            drawSocInput(soc, loadedProfile.contact.socNetLink[soc]);
        }
    }
}

function drawSocInput(socName, value) {
    var socNamePlaceholder = socName.charAt(0).toUpperCase() +  socName.substring(1).toLowerCase();
    $(".soc-input-group").append('<input class="input-info-normal socInput" type="text" name="'+ socName +'" ' +
        'placeholder="Ссылка на ' + socNamePlaceholder + '" value="'+ value + '">');
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
    $("input[name=contactEmail]").each(function () {
        if ($(this).val() !== '') {
            contactEmails.push($(this).val());
        }
    });
    updatedProfile.contact.contactEmails = contactEmails;

    var contactPhones = [];
    $("input[name=contactTel]").each(function () {
        if ($(this).val() !== '') {
            contactPhones.push($(this).val());
        }
    });

    updatedProfile.contact.contactPhones = contactPhones;

    var socLinks = {};
    $(".socInput").each(function () {
        var socName = $(this).attr("name");
        socLinks[socName] = $(this).val();
    });

    updatedProfile.contact.socNetLink = socLinks;
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
                var max_soc_fields = 7; //maximum input boxes allowed

                $(".socIcon").click(function () {
                    var socName = $(this).attr("name");

                    if ($(".socInput").length < max_soc_fields) { //max input box allowed
                        drawSocInput(socName, "");
                    } else {
                        alert("Максимум допустимо " + max_soc_fields + "социальных ссылок.")
                    }
                });

 // ---------------------------------------------------- END Soc network links --------------------------------

 // ----------------------------------------------------- Multiply phone numbers -----------------------------------
                // Add/Remove phone Input Fields Dynamically with jQuery

                var max_fields_tel = 5; //maximum input boxes allowed + 1

                $("#addPhoneImg").click(function () { //on add input button click
                    if ($("input[name=contactTel]").length < max_fields_tel) { //max input box allowed
                        $("#contactPhonesBlock").append(contactTelTagHtml); //add input box
                    } else {
                        alert('Максимум ' + max_fields_tel + ' контактных телефоноов');
                    }
                });

                $('#deletePhoneImg').on('click', function () {
                    $('.contactTel').last().remove();
                });

// ----------------------------------------------------- Multiply phone numbers -----------------------------------


// ----------------------------------------------------- Multiply emails -----------------------------------
                // Add/Remove phone Input Fields Dynamically with jQuery

                var max_fields_email = 5; //maximum input boxes allowed + 1

                $("#addEmailImg").click(function () { //on add input button click
                    if ($("input[name=contactEmail]").length < max_fields_email) { //max input box allowed
                        $("#contactEmailsBlock").append(contactEmailTagHtml); //add input box
                    } else {
                        alert('Максимум ' + max_fields_email +' контактных email');
                    }
                });

                $('#deleteEmailImg').on('click', function () {
                    $('.contactEmail').last().remove();
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
    var files = event.currentTarget.files;
    var reader  = new FileReader();

    reader.addEventListener("load", function () {
        cropper.replace(reader.result);
    }, false);

    if (files[0]) {
        reader.readAsDataURL(files[0]);
    }

    $('#cropperModal').css('display',"block");
});
// ------------------------------------------- End photo upload block ---------------------------------