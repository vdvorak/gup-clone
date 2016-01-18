$('.bxslider').bxSlider({
  mode: 'fade',
  captions: true
});

$(".ballance a").click(function(e){
    e.preventDefault();
    $(".ballanceAdd-wraper").toggle();
});

$(".top-menu-username a").click(function(e){
    e.preventDefault();
    $(".header-mainMenu").toggle();
});




$(document).ready(function () {
    $(".main-tender-wrap").hover(
        function () {
            $('.main-tender-bottom-menu').slideDown('medium');
        },
        function () {
            $('.main-tender-bottom-menu').slideUp('medium');
        }
    );
    $(".main-project-wrap").hover(
        function () {
            $('.main-project-bottom-menu').slideDown('medium');
        },
        function () {
            $('.main-project-bottom-menu').slideUp('medium');
        }
    );
    $(".main-news-wrap").hover(
        function () {
            $('.main-news-bottom-menu').slideDown('medium');
        },
        function () {
            $('.main-news-bottom-menu').slideUp('medium');
        }
    );


});

$(document).ready(function() {
    $('.popup-with-form').magnificPopup({
        type: 'inline',
        preloader: false,
        focus: '#name',
        callbacks: {
            beforeOpen: function() {
                if($(window).width() < 700) {
                    this.st.focus = false;
                } else {
                    this.st.focus = '#name';
                }
            }
        }
    });
});
