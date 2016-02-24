function loadProfileById(profileId) {
    return $.ajax({
            type: "POST",
            url: "/api/rest/profilesService/profile/read/id/" + profileId
    })
}

function getProfileImgUrl(pic) {
    alert("pic: " + pic);
    if (pic) {
        return '/api/rest/fileStorage/PROFILE/file/read/id/' + pic;
    } else {
        return '/resources/images/no_photo.jpg'
    }
}

