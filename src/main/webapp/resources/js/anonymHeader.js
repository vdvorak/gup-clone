//$(document).ready(function() {
$("#loginBtn").click(function () {
    $.ajax({
        type: 'POST',
        url: '/login?email=' + $('#email').val() + '&password=' + $('#password').val(),
        statusCode: {
            200: function () {
                window.location.href = '/prioffice';
            },
            401: function () {
                alert("Неправильный пароль! Проверьте введенные данные!")
            },
            404: function () {
                alert("Неправильный email! Проверьте введенные данные!")
            }
        }
    });
});

$("#registrationBtn").click(function () {
    var pass = $('#registration-password').val();
    var repeatPass = $('#repeat-registration-password').val();

    var badPassMsg = '';
    if (!isMatchRegEx(pass)) {
        badPassMsg += 'Пароль не соответствует требованиям.'
    }
    if (pass !== repeatPass) {
        badPassMsg += 'Пароли не совпадают'
    }
    if (badPassMsg.length) {
        alert(badPassMsg);
        return;
    }

    var profile = {
        'email': $('#registration-email').val(),
        'password': pass
    };

    $.ajax({
        type: 'POST',
        url: '/register',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(profile),
        statusCode: {
            201: function () {
                alert('Вы зарегистрировались! Вам выслано подтверждение на почту.')
                window.location.href = '/index';
            },
            409: function () {
                alert('Пользователь с таким email уже существует!')
            }
        }
    });
});

$('#registration-email').blur(checkEmail);

function checkEmail() {
    $.ajax({
        type: "POST",
        url: "/login/checkEmail",
        data: $('input[name="registration-email"]').val(),
        cache: false,
        success: function (response) {
            if (response == 'true') {
                $("#responseEmail").text("Такой email уже существует в системе").css("color", "red");
                $('#registrationBtn').attr("disabled", true);
            } else {
                $("#responseEmail").text("email свободен").css("color", "green");
                $('#registrationBtn').removeAttr("disabled");
            }
        }
    });
}

$('#registration-password').change(checkPass);
$('#repeat-registration-password').change(checkPass);

function checkPass() {
    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    var pass1 = $('#registration-password');
    var pass2 = $('#repeat-registration-password');

    var isMatch = isMatchRegEx(pass1.val());

    if (isMatch) {
        pass1.css("color", goodColor);
    } else {
        pass1.css("color", badColor);
    }

    if (pass1.val() === pass2.val()) {
        pass2.css("color", goodColor);
    } else {
        pass2.css("color", badColor);
    }
}

function isMatchRegEx(password) {
    var re = /^[0-9a-zA-Z_]{6,}$/;
    return re.test(password);
}

$('#go').click(function (event) { // лoвим клик пo ссылки с id="go"
    event.preventDefault(); // выключaем стaндaртную рoль элементa
    $('#overlay').fadeIn(400, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
        function () { // пoсле выпoлнения предъидущей aнимaции
            $('#modal_form')
                .css('display', 'block') // убирaем у мoдaльнoгo oкнa display: none;
                .animate({opacity: 1}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
        });
});
/* Зaкрытие мoдaльнoгo oкнa, тут делaем тo же сaмoе нo в oбрaтнoм пoрядке */
$('#modal_close, #overlay').click(function () { // лoвим клик пo крестику или пoдлoжке
    $('#modal_form')
        .animate({opacity: 0}, 200,  // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
        function () { // пoсле aнимaции
            $(this).css('display', 'none'); // делaем ему display: none;
            $('#overlay').fadeOut(400); // скрывaем пoдлoжку
            $('.contactA').show();
            $('.restore').hide();
        }
    );
});


$('#socialBtn').attr('id', 'goo');

$(document).on('click','#goo', function (event) {
    event.preventDefault();
    $('#overlay').fadeIn(400,
        function () {
            $('#modal_form')
                .css('display', 'block')
                .animate({opacity: 1}, 200);
        });
});
//});