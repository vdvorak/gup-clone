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
//$(".top-menu-userSection").hover(
//    function () {
//        $('.header-mainMenu').slideDown('medium');
//    },
//    function () {
//        $('.header-mainMenu').slideUp('medium');
//    }
//);

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
            $('.main-news-bottom-menu').show();
        },
        function () {
            $('.main-news-bottom-menu').hide();
        }
    );


});


