let userNames = []; // for Bloodhound suggestion engine
let users;

let urlAdminProfileReadAll = 'http://localhost:8184/api/rest/admin/profile/read/short/all';
let urlProfilePhoto = 'http://localhost:8184/api/rest/fileStorage/profile/photo/read/id/';
let urlProfileCreate = 'http://localhost:8183/api/oauth/admin/register';
let urlProfileUpdBAdmin = 'http://localhost:8184/api/rest/profilesService/profile/updateByAdmin';
let urlProfileRoleEdit = 'http://localhost:8184/api/rest/admin/profile/role/edit';
let urlProfileDelete = 'http://localhost:8184/api/rest/admin/profile/admin/admin-delete';
let tagNoPhoto = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';


$(document).ready(function () {
    var data;
    var filterOptions = {
        skip: 0,
        limit: 20,
        userRoles: ['ROLE_ADMIN', 'ROLE_MODERATOR', 'ROLE_SPECTATOR']
    };


    $('#myModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });

    $('#deleteModal').on('shown.bs.modal', function () {
        $('#myInput').focus()
    });

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: urlAdminProfileReadAll,
        data: JSON.stringify(filterOptions),
        success: function (response) {

            // copy object
            users = jQuery.extend(true, {}, response.entities);

            data = response;

            let admins = [];
            let moderators = [];
            let spectators = [];

            //prepare photo for data table
            for (var i = 0; i < data.length; i++) {

                // push emails for Bloodhound suggestion engine
                userNames.push(data[i].email);

                // prepare humanlike date
                data[i].createdDate = new Date(data[i].createdDate).toLocaleDateString();

                if (!data[i].email) {
                    data[i].email = '';
                }
                if (!data[i].username) {
                    data[i].username = '';
                }


                // separate admins, moderators and spectators into three tables
                if (data[i].userRoles !== undefined && data[i].userRoles !== null) {
                    for (var n = 0; n < data[i].userRoles.length; n++) {

                        if (data[i].userRoles[n] === 'ROLE_ADMIN') {
                            admins.push(data[i]);
                        }
                        if (data[i].userRoles[n] === 'ROLE_MODERATOR') {
                            moderators.push(data[i]);
                        }
                        if (data[i].userRoles[n] === 'ROLE_SPECTATOR') {
                            spectators.push(data[i]);
                        }
                    }
                }


                if (data[i].imgId && data[i].imgId.length > 2) {
                    data[i].imgId = '<img src="' + urlProfilePhoto + data[i].imgId + '?cachedSize=small" width="100" height="100">';
                } else {
                    if (data[i].imgUrl && data[i].imgUrl.length > 2) {
                        data[i].imgId = '<img src="' + data[i].imgUrl + '" width="100" height="100">';
                    } else {
                        data[i].imgId = tagNoPhoto;
                    }
                }
            }

            // prepare DataTable object with params
            let dataTableObjPreparator = function (dataName) {
                return {
                    select: {
                        style: 'single'
                    },
                    data: dataName,
                    "columns": [
                        {"data": "imgId"},
                        {"data": "username"},
                        {"data": "email"},
                        {"data": "createdDate"}
                    ],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/1.10.9/i18n/Russian.json"
                    }
                }
            };

            let tableAdmins = $('#admins').DataTable(dataTableObjPreparator(admins));


            // logic for rows click in administrators table
            tableAdmins
                .on('select', function (e, dt, type, indexes) {
                    var rowData = tableAdmins.rows(indexes).data().toArray();
                    $("input[name='adminId']").attr("value", rowData[0].id);
                    $('#adminEditHref').attr("href", "http://gup.com.ua/seller/" + rowData[0].id);
                    $('#editAdminProfileButton').attr("class", "btn btn-danger");
                    $('.adminFastEditRoles input').attr("disabled", false);
                    $('#deleteAdminProfileButton').attr("class", "deleteProfileButton btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='adminId']").attr("value", "");
                    $('#editAdminProfileButton').attr("class", "btn btn-danger disabled");
                    $('#deleteAdminProfileButton').attr("class", "btn btn-danger disable");
                    $('.adminFastEditRoles input').attr("disabled", true);
                });

            let tableModerators = $('#moderators').DataTable(dataTableObjPreparator(moderators));

            // logic for rows click in moderators table
            tableModerators
                .on('select', function (e, dt, type, indexes) {
                    var rowData = tableModerators.rows(indexes).data().toArray();
                    $("input[name='moderatorId']").attr("value", rowData[0].id);
                    $('#moderatorEditHref').attr("href", "http://gup.com.ua/seller/" + rowData[0].id);
                    $('#editModeratorProfileButton').attr("class", "btn btn-danger");
                    $('.moderatorFastEditRoles input').attr("disabled", false);
                    $('#deleteModaratorProfileButton').attr("class", "deleteProfileButton btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='moderatorId']").attr("value", "");
                    $('#editModeratorProfileButton').attr("class", "btn btn-danger disabled");
                    $('#deleteModaratorProfileButton').attr("class", "btn btn-danger disable");
                    $('.moderatorFastEditRoles input').attr("disabled", true);
                });

            let tableSpectators = $('#spectators').DataTable(dataTableObjPreparator(spectators))

            // logic for rows click in spectators table
            tableSpectators
                .on('select', function (e, dt, type, indexes) {
                    var rowData = tableSpectators.rows(indexes).data().toArray();
                    $("input[name='spectatorId']").attr("value", rowData[0].id);
                    $('#spectatorEditHref').attr("href", "http://gup.com.ua/seller/" + rowData[0].id);
                    $('#editSpectatorProfileButton').attr("class", "btn btn-danger");
                    $('.spectatorFastEditRoles input').attr("disabled", false);
                    $('#deleteSpectatorProfileButton').attr("class", "deleteProfileButton btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='spectatorId']").attr("value", "");
                    $('#editSpectatorProfileButton').attr("class", "btn btn-danger disabled");
                    $('#deleteSpectatorProfileButton').attr("class", "btn btn-danger disable");
                    $('.spectatorFastEditRoles input').attr("disabled", true);
                });

            var logins = new Bloodhound({
                datumTokenizer: Bloodhound.tokenizers.whitespace,
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                // `states` is an array of state names defined in "The Basics"
                local: userNames
            });

            $('#scrollable-dropdown-menu .typeahead').typeahead(null, {
                name: 'logins',
                limit: 10,
                source: logins
            });

        }

    });


});


function userPreparator(inputElement, inputName) {
    var user = {};
    user.userRoles = [];
    user.userRoles.push(inputElement.val());
    user.id = $("input[name='" + inputName + "']").val();
    return user
}


$(document).on('click', '.adminFastEditRoles input', function () {
    makePostRequest(urlProfileRoleEdit, JSON.stringify(userPreparator($(this), 'adminId')));
});

$(document).on('click', '.moderatorFastEditRoles input', function () {
    makePostRequest(urlProfileRoleEdit, JSON.stringify(userPreparator($(this), 'moderatorId')));
});

$(document).on('click', '.spectatorFastEditRoles input', function () {
    makePostRequest(urlProfileRoleEdit, JSON.stringify(userPreparator($(this), 'spectatorId')));
});

$('#typeahead').blur(function () {
    var user = {};
    for (var i in users) {
        if (users[i].email === this.value) {
            user = users[i];
            break;
        }
    }

    $('.ch2').prop("checked", false);

    for (var j in user.userRoles) {
        switch (user.userRoles[j]) {
            case 'ROLE_ADMIN':
                $('#adminCheck2').prop("checked", true);
                break;
            case 'ROLE_SUPPORT':
                $('#supportCheck2').prop("checked", true);
                break;
            case 'ROLE_MODERATOR':
                $('#moderatorCheck2').prop("checked", true);
                break;
            case 'ROLE_USER':
                $('#userCheck2').prop("checked", true);
                break;
            case 'ROLE_ANONYMOUS':
                $('#anonymousCheck2').prop("checked", true);
                break;
        }
    }


    $('#update').click(function () {
        user.userRoles = [];

        if ($('#adminCheck2').prop("checked")) {
            user.userRoles.push('ROLE_ADMIN');
        }
        if ($('#supportCheck2').prop("checked")) {
            user.userRoles.push('ROLE_SUPPORT');
        }
        if ($('#moderatorCheck2').prop("checked")) {
            user.userRoles.push('ROLE_MODERATOR');
        }
        if ($('#userCheck2').prop("checked")) {
            user.userRoles.push('ROLE_USER');
        }


        makePostRequest(urlProfileUpdBAdmin, JSON.stringify(user));
    });

});


/**
 * Make request for create new user
 */
$('#create').click(function () {

    var user = {};
    var login;
    var password;
    var roles = [];
    login = $('#newLogin').val();
    password = $('#newPassword').val();
    if ($('#adminCheck').prop("checked")) {
        roles.push('ROLE_ADMIN');
    }
    if ($('#moderatorCheck').prop("checked")) {
        roles.push('ROLE_MODERATOR');
    }
    if ($('#spectatorCheck').prop("checked")) {
        roles.push('ROLE_SPECTATOR');
    }

    // now you can have only one role
    if (roles.length < 1) {
        return alert("Выберите одну роль")
    }

    //user.username = login;
    user.email = login;
    user.password = password;
    user.userRoles = roles;

    makePostRequest(urlProfileCreate, JSON.stringify(user));
});


/**
 * Delete selected user
 */
$('.deleteProfileButton').click(function () {

    let buttonId = event.target.getAttribute('id');

    switch (buttonId) {
        case 'deleteAdminProfileButton' :
            makePostRequest(urlProfileDelete, $('#input-admin-id').val());
            break;
        case 'deleteModaratorProfileButton' :
            makePostRequest(urlProfileDelete, $('#input-moderator-id').val());
            break;
        case 'deleteSpectatorProfileButton' :
            makePostRequest(urlProfileDelete, $('#input-spectator-id').val());
            break;
    }
});


function makePostRequest(url, data) {
    $.ajax({
        type: "POST",
        url: url,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: data,
        statusCode: {
            200: function () {
                window.location.href = '/admin-admins';
            }
        }
    });
}
