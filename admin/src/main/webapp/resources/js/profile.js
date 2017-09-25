var profileId = getUrlParam('id');

$.ajax({
    type: "POST",
    url: "/swagger/rest/profilesService/profile/read/id/" + profileId,
    statusCode: {
        200: function (profile) {
            if (typeof loggedInProfile != 'undefined' && loggedInProfile.id !== profileId) {
                var finded = false;
                for (var ci in loggedInProfile.contactList) {
                    if (loggedInProfile.contactList[ci] === profile.id) {
                        finded = true;
                        break;
                    }
                }
                if (finded) {
                    $('#writeMessageToProfile').show();
                    $('#removeProfileFromContacts').show()
                }
                else {
                    $('#addProfileToContact').show()
                }
            }

            if (profile.imgId) {
                $('#profileImg').attr('src', '/swagger/rest/fileStorage/PROFILE/file/read/id/' + profile.imgId);
            } else {
                $('#profileImg').attr('src', '/resources/images/no_photo.jpg');
            }

            if (profile.contact.member == true) {
                $('.organization-profile-img').show();
                $('.rating-organization').append('<p>' + profile.point + '</p>');
            }


            $('#profileName').text(getProfileName(profile));


            if (profile.contact.aboutUs) {
                $('.AboutMe-p').append(profile.contact.aboutUs);
            } else {
                $('.AboutMe-p').append("Пользователь еще ничего на рассказал о себе.");
            }


            if (profile.contact.contactPhones.length > 0) {
                profile.contact.contactPhones.forEach(function (phoneNumber) {

                    $('.phone').append('<p class="phoneNumber">' + phoneNumber + '</p>');
                });
                $('.phone').show();
            }

            if (profile.contact.skypeUserName != null) {
                $('.skypeContact').append('<p class="skype">Skype: ' + profile.contact.skypeUserName + '</p>');
                $('.skypeContact').show();
            }

            if (profile.contact.contactEmails.length > 0) {
                profile.contact.contactEmails.forEach(function (email) {
                    $('.emailContact').append('<p class="email">' + email + '</p>');
                });
                $('.emailContact').show();
            }

            // Draw social networks input ----------------------------------
            if (profile.contact.socNetLink) {
                for (var soc in profile.contact.socNetLink) {
                    key = profile.contact.socNetLink[soc];
                    if (soc === "FACEBOOK") {
                        $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/facebook.png" alt="Facebook"></a>')
                    }
                    if (soc === "VKONTAKTE") {
                        $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/B.png" alt="Vk"></a>')
                    }
                    if (soc === "LINKEDIN") {
                        $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/in.png" alt="LinkedIn"></a>')
                    }
                    if (soc === "GOOGLEPLUS") {
                        $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/g+.png" alt="Google+"></a>')
                    }
                    if (soc === "TWITTER") {
                        $('.social-icon').append('<a href="' + key + '"><img class="img-responsive" src="/resources/images/twitter.png" alt="Twitter"></a>')
                    }
                }
            }
            // End draw social networks input ----------------------------------


            if (profileId === loggedInProfile.id) {
                $('.contact-btn-group').append('<button class="addToContact" id="editProfileBtn">Редактировать профиль</button>')
            }
            // else{
            //     $('.contact-btn-group').append('<button class="addToContact" id="addProfileToContact">Добавить в контакты</button>')
            // }

        },
        404: function () {
//                        alert('Такого пользователя нет');
            window.location.href = "/profileList";
        }
    }
});

$('#writeMessageToProfile').on('click', function () {
    window.location.href = "/dialogue/create/with/" + profileId;
});

$(document).on('click', '#editProfileBtn', function () {
    window.location.href = '/edit-profile'
});

$(document).on('click', '#addProfileToContact', function () {
    R.Libra().profilesService().profile().id(profileId).myContactList().add(null, function () {
        location.reload()
    }, function () {
        alert('Internal error')
    })
});

$(document).on('click', '#removeProfileFromContacts', function () {
    $.ajax({
        type: "POST",
        url: "/swagger/rest/profilesService/profile/id/" + profileId + "/myContactList/delete",
        cache: false,
        statusCode: {
            200: function () {
                alert('Пользователь удалён из списка вших контактов');
            },
            400: function () {
                alert("Ошибка сервера")
            }
        }
    });
});


