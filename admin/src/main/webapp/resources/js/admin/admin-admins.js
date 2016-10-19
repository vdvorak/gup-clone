let userNames = []; // for Bloodhound suggestion engine
let users;

let urlAdminProfileReadAll = 'http://localhost:8184/api/rest/admin/profile/read/all';
let urlProfilePhoto = 'http://localhost:8184/api/rest/fileStorage/profile/photo/read/id/';
let urlProfileCreate = 'http://localhost:8183/api/oauth/admin/register';
let urlProfileUpdBAdmin = '/api/rest/profilesService/profile/updateByAdmin'; //FixMe add domain
let urlProfileDelete = 'http://localhost:8184/api/rest/admin/profile/admin/admin-delete';
let tagNoPhoto = '<img src="/resources/images/no_photo.jpg" width="100" height="100">';


$(document).ready(function () {
    var data;
    var filterOptions = {};
    filterOptions.skip = 0;
    filterOptions.limit = 20;
    filterOptions.userRoles = ['ROLE_ADMIN', 'ROLE_MODERATOR'];

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

            console.log(response);

            // copy object
            users = jQuery.extend(true, {}, response.entities);

            data = response;

            // push emails for Bloodhound suggestion engine
            for (var k = 0; k < data.length; k++) {
                userNames.push(data[k].email);
            }


            //prepare photo for data table
            for (var i = 0; i < data.length; i++) {
                if (data[i].imgId !== null && data[i].imgId.length > 2) {
                    data[i].imgId = '<img src="' + urlProfilePhoto + data[i].imgId + '?cachedSize=small" width="100" height="100">';
                } else {
                    if (data[i].imgUrl !== null && data[i].imgUrl.length > 2) {
                        data[i].imgId = '<img src="' + data[i].imgUrl + '" width="100" height="100">';
                    } else {
                        data[i].imgId = tagNoPhoto;
                    }
                }
            }


            var admins = [];
            var moderators = [];

            // separate moderators and admins into two tables
            for (var m = 0; m < data.length; m++) {
                if (data[m].userRoles !== undefined && data[m].userRoles !== null) {
                    for (var n = 0; n < data[m].userRoles.length; n++) {
                        if (data[m].userRoles[n] === 'ROLE_ADMIN') {
                            admins.push(data[m]);
                        }
                        if (data[m].userRoles[n] === 'ROLE_MODERATOR') {
                            moderators.push(data[m]);
                        }
                    }
                }
            }

            // prepare humanlike date
            for (var i = 0; i < data.length; i++) {
                data[i].createdDate = new Date(data[i].createdDate).toLocaleDateString();
            }


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

            var tableAdmins = $('#admins').DataTable(dataTableObjPreparator(admins));

            tableAdmins
                .on('select', function (e, dt, type, indexes) {
                    var rowData = tableAdmins.rows(indexes).data().toArray();
                    $("input[name='adminId']").attr("value", rowData[0].id);
                    $('#adminEditHref').attr("href", "http://gup.com.ua/seller/" + rowData[0].id);
                    $('#editAdminProfileButton').attr("class", "btn btn-danger");
                    $('#deleteAdminProfileButton').attr("class", "btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='adminId']").attr("value", "");
                    $('#editAdminProfileButton').attr("class", "btn btn-danger disabled");
                    $('#deleteAdminProfileButton').attr("class", "btn btn-danger disable");
                });

            var tableModerators = $('#moderators').DataTable(dataTableObjPreparator(moderators));

            tableModerators
                .on('select', function (e, dt, type, indexes) {
                    var rowData = tableModerators.rows(indexes).data().toArray();
                    $("input[name='moderatorId']").attr("value", rowData[0].id);
                    $('#moderatorEditHref').attr("href", "http://gup.com.ua/seller/" + rowData[0].id);
                    $('#editModeratorProfileButton').attr("class", "btn btn-danger");
                    $('#deleteModaratorProfileButton').attr("class", "btn btn-danger");
                })
                .on('deselect', function (e, dt, type, indexes) {
                    $("input[name='moderatorId']").attr("value", "");
                    $('#editModeratorProfileButton').attr("class", "btn btn-danger disabled");
                    $('#deleteModaratorProfileButton').attr("class", "btn btn-danger disable");
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
        if ($('#anonymousCheck2').prop("checked")) {
            user.userRoles.push('ROLE_ANONYMOUS');
        }

        $.ajax({
            type: "POST",
            url: urlProfileUpdBAdmin,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(user),
            success: function (response) {
                window.location.href = '/admin-admins';
            }
        });
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


    if (roles.length < 1){
        return alert("Выберите одну роль")
    }

    //user.username = login;
    user.email = login;
    user.password = password;
    user.userRoles = roles;

    $.ajax({
        type: "POST",
        url: urlProfileCreate,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(user),
        statusCode: {
            200: function () {
                window.location.href = '/admin-admins';
            }
        }
    });
});


/**
 * Delete selected user
 */
$('.deleteAdminButton').click(function(){

    let buttonId = event.target.getAttribute('id');

    let deleteProfile = function(profileId){
        $.ajax({
            type: "POST",
            url: urlProfileDelete,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: profileId,
            statusCode: {
                200: function () {
                    window.location.href = '/admin-admins';
                }
            }
        });
    };

    switch (buttonId) {
        case 'deleteAdminProfileButton' : deleteProfile($('#input-admin-id').val());
            break;
        case 'deleteModaratorProfileButton' : deleteProfile($('#input-moderator-id').val());
            break;
    }
});
