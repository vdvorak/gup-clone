$(document).ready(function() {
    $("#loginBtn").click(function(){
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

    $("#registrationBtn").click(function(){
        var profile = {
            'email' : $('#registration-email').val(),
            'password' : $('#registration-password').val()
        };

        $.ajax({
            type: 'POST',
            url: '/register',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(profile),
            statusCode: {
                201: function() {
                    alert('Вы зарегистрировались! Вам выслано подтверждение на почту.')
                    window.location.href = '/index';
                },
                409: function() {
                    alert('Пользователь с таким email уже существует!')
                }
            }
        });
    });
});