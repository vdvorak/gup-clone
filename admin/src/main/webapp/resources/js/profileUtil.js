function loadProfileById(profileId) {
    return $.ajax({
            type: "POST",
            url: "/swagger/rest/profilesService/profile/read/id/" + profileId
    })
}

function getProfileImgUrl(pic) {
    if (pic) {
        return '/swagger/rest/fileStorage/PROFILE/file/read/id/' + pic;
    } else {
        return '/resources/images/no_photo.jpg'
    }
}

function getProfileName(profile) {
    if (profile.contact.type === 'LEGAL_ENTITY' ) {
        return profile.contact.companyName;
    } else if (profile.username) {
        return  profile.username;
    } else {
        return 'Безымянный';
    }
}

