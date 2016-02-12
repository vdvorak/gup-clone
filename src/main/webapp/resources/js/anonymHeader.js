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
            'password' : $('#registration-password').val(),
            'point' : 0,
            'profileRating' : [],
            'contactList' : [],
            'userProfile' : {},
            'contact' : {
                'contactEmails' : [],
                'contactPhones' : [],
                'linkToWebSite' : [],
                'socNetLink' : [],
                'naceId' : []
            }
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

    $('#go').click( function(event){ // лoвим клик пo ссылки с id="go"
        event.preventDefault(); // выключaем стaндaртную рoль элементa
        $('#overlay').fadeIn(400, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
            function(){ // пoсле выпoлнения предъидущей aнимaции
                $('#modal_form')
                    .css('display', 'block') // убирaем у мoдaльнoгo oкнa display: none;
                    .animate({opacity: 1, top: '50%'}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
            });
    });
    /* Зaкрытие мoдaльнoгo oкнa, тут делaем тo же сaмoе нo в oбрaтнoм пoрядке */
    $('#modal_close, #overlay').click( function(){ // лoвим клик пo крестику или пoдлoжке
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,  // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
            function(){ // пoсле aнимaции
                $(this).css('display', 'none'); // делaем ему display: none;
                $('#overlay').fadeOut(400); // скрывaем пoдлoжку
            }
        );
    });

    $('#goo').click( function(event){
        event.preventDefault();
        $('#overlay').fadeIn(400,
            function(){
                $('#modal_form')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('#modal_close, #overlay').click( function(){
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,
            function(){
                $(this).css('display', 'none');
                $('#overlay').fadeOut(400);
            }
        );
    });
});