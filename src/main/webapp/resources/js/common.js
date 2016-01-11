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



/* ТЕСТОВЫЙ РАК УДАЛИТЬ НАХРЕН*/
$(document).on('click', '.main-tenderPic-wrap a', function(e){
	e.preventDefault();
	$('.main-tender-bottom-menu ').css('display', 'block');
	$('.main-project-bottom-menu ').css('display', 'none');
	$('.main-news-bottom-menu ').css('display', 'none');
	
});
$(document).on('click', '.main-projectPic-wrap a', function(e){
	e.preventDefault();
	$('.main-project-bottom-menu ').css('display', 'block');
	$('.main-tender-bottom-menu ').css('display', 'none');
	$('.main-news-bottom-menu ').css('display', 'none');
});
$(document).on('click', '.main-newsPic-wrap a', function(e){
	e.preventDefault();
	$('.main-news-bottom-menu ').css('display', 'block');
	$('.main-tender-bottom-menu ').css('display', 'none');
	$('.main-project-bottom-menu ').css('display', 'none');
});


/*$(document).on('click', '.selectedItem', function(){
            $('.selectedItem').removeClass('active');
            $(this).addClass('active');
            $('#myColor').css('display','block');
            $('#line').css('display','block');
            if($(this).attr("id")!="move"){
                fabric.Object.prototype.selectable = false;

            }
            if($(this).attr("id")!="paint"){
                fabric.Object.prototype.selectable = false;
                $('#myColor').css('display','none');
                $('#line').css('display','none');
            }
    });*/
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
