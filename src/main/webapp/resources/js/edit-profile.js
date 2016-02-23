
var loadedProfile = {};
var updatedProfile = {};

var emailCloneCount = 1;
var contactPhoneCloneCount = 1;
var socInputTemplate = $('.soc-input-group').html();

function setValuesForFieldsFromProfile(profile) {
//            alert('loadedProfile: ' + JSON.stringify(loadedProfile));

    if (profile.contact.pic != null) {
        $('.moreInformation-img').css('background',
            'url(/api/rest/fileStorage/profile/file/read/id/' + profile.contact.pic + ') no-repeat center center');
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
        if (i === 0) {
            $('#email-info-1').val(profile.contact.contactEmails[i]);
        } else {
            emailCloneCount++;

            $('<input/>', {
                id: 'email-info-' + (i + 1),
                type: 'email',
                name: 'contactEmail',
                class: 'form-info-input',
                value: profile.contact.contactEmails[i]
            }).appendTo('#contactEmailsBlock');

            $('<div/>', {
                class: 'clearfix'
            }).appendTo('#contactEmailsBlock');
        }
    }

    for (var i = 0; i < profile.contact.contactPhones.length; i++) {
        if (i === 0) {
            $('#tel-info-1').val(profile.contact.contactPhones[i]);
        } else {
            contactPhoneCloneCount++;

            $('<input/>', {
                id: 'tel-info-' + (i + 1),
                type: 'tel',
                name: 'contactTel',
                class: 'input-info-min',
                value: profile.contact.contactPhones[i]
            }).appendTo('#contactPhonesBlock');

            $('<div/>', {
                class: 'clearfix'
            }).appendTo('#contactPhonesBlock');
        }
    }

//                    $('#tel-info').attr("placeholder", profile.contact.O);
}


function initializeProfileEntityForUpdate() {
    updatedProfile.id = loadedProfile.id;

//            if(loadedProfile.username !== $('#userName').val()) {
    updatedProfile.username = $('#userName').val();
//            }

//            if(loadedProfile.contact.type !== $('#select-type').val() && $('#select-type').val() !== "") {
    updatedProfile.contact.type = $('#select-type').val();
//            }

    updatedProfile.contact.position = $('#position').val();
    updatedProfile.contact.companyName = $('#nameCompany').val();


//            if(loadedProfile.contact.aboutUs !== $('#info-about-me').val()) {
    updatedProfile.contact.aboutUs = $('#info-about-me').val();
//            }

//            if(loadedProfile.contact.skypeUserName !== $('#skype-info').val()) {
    updatedProfile.contact.skypeUserName = $('#skype-info').val();
//            }

//            if(loadedProfile.contact.linkToWebSite !== $('#web-addresses').val()) {
    updatedProfile.contact.linkToWebSite = $('#web-addresses').val();
//            }

//            if(loadedProfile.mainPhoneNumber !== $('#main-tel-info').val()) {
    updatedProfile.mainPhoneNumber = $('#main-tel-info').val();
//            }

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


                // --------------------------------------  BEGIN soc network links  ----------------------------
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

                    var tel_max_fields = 3; //maximum input boxes allowed
                    var tel_wrapper = $(".input_fields_wrap"); //Fields wrapper
                    var add_button = $(".add_field_button"); //Add button ID


                    var x = phoneArr.length; //initial text box count

                    $(add_button).click(function (e) { //on add input button click
                        e.preventDefault();
                        if (x < tel_max_fields) { //max input box allowed
                            x++; //text box increment
                            $(tel_wrapper).append('<div><input id="phone' + x + '" type="text"/><a href="#" class="remove_field" required><img src="/resources/img/minus.png"></a></div>'); //add input box
                        }
                    });

                    $(tel_wrapper).on("click", ".remove_field", function (e) { //user click on remove text
                        e.preventDefault();
                        $(this).parent('div').remove();
                        x--;
                    })

// ----------------------------------------------------- Multiply phone numbers -----------------------------------






            }
        }
    });
});


$('#updateProfileBtn').on('click', function () {
    initializeProfileEntityForUpdate();

//            alert('updatedProfile : ' + JSON.stringify(updatedProfile));

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

$('#addEmailImg').on('click', function () {
    if (emailCloneCount < 5) {
        $("#email-info-" + emailCloneCount).clone()
            .attr('id', 'email-info-' + (++emailCloneCount)).val("")
            .insertAfter("#email-info-" + (emailCloneCount - 1));
    } else {
        alert('Максимум 5 контактных email-ов');
    }
});

$('#addPhoneImg').on('click', function () {
    if (contactPhoneCloneCount < 5) {
        $("#tel-info-" + contactPhoneCloneCount).clone()
            .attr('id', 'tel-info-' + (++contactPhoneCloneCount)).val("")
            .insertAfter("#tel-info-" + (contactPhoneCloneCount - 1));
    } else {
        alert('Максимум 5 контактных телефоноов');
    }
});


// ------------------------------------------- Photo upload block ---------------------------------
$('#uploadProfilePhotoInput').on('change', function () {
    $.ajax({
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
    });
});
// ------------------------------------------- End photo upload block ---------------------------------
