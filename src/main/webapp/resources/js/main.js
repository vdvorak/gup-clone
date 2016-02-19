function getUrlParam(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? null : sParameterName[1];
        }
    }
}

$(document).ready(function(){

    $('#socialBtn').click( function(event){
        event.preventDefault();
        $('#overlay').fadeIn(400,
            function(){
                $('#refill')
                    .css('display', 'block')
                    .animate({opacity: 1, top: '50%'}, 200);
            });
    });

    $('#noMoneyClose, #overlay, #noMoneyCloseRich').click( function(){
        $('#refill')
            .animate({opacity: 0, top: '45%'}, 200,
            function(){
                $(this).css('display', 'none');
                $('#overlay').fadeOut(400);
            }
        );
    });

    $(".question-img").click(function(){
        $(".questionForm").slideToggle(1);
    });

    $(".caretContact").click(function(){
        $(".caretContact").toggleClass('lol');
        $(".mapContact").slideToggle();
    });

    $('#tab-container').easytabs({
        animate: false
    });

    $('.bxslider').bxSlider();

    $('#tab-container-news').easytabs({
        animate: false
    });

    $(".listArtist img").click(function(){
        $(".listArtist ul").css("height", "auto");
    });
});