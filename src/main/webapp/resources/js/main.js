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

    $(".search-img").click(function(){
        $(".search-img").toggleClass('trolol');
        $("#filterForm").slideToggle();
    });

    $('#tab-container').easytabs({
        animate: false
    });

    $('.bxslider').bxSlider();

    $('#tab-container-news').easytabs({
        animate: false
    });

    $(".NewsTabsFilterItem").on('click', function(){
        $('.intro').removeClass("intro");
        $(this).addClass("intro");
    });

    $('#newsFormComments').keyup(function() {
        var maxLength = 2000;
        var length = $(this).val().length;
        var length = maxLength-length;
        $('#chars').text(length + ' символов осталось');
    });

    $(".downComments").click(function(){
        $(".downComments").hide('slow');
        $(".colNewsComments").show('slow');
        $(".colComments").css("width", "50%");
    });

    $(".comments").click(function(){

        if ($('.backgroundColorComment').is(':visible') ) {
            return $('.backgroundColorComment').removeClass("backgroundColorComment");;
        } else {
            $(this).addClass("backgroundColorComment");
        }
    });
});