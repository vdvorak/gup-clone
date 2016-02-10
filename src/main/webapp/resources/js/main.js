$(document).ready(function(){

//	$("#ad-caret").click(function(){
//        $('.add-top1:first').clone().insertAfter('.add-top1:last');
//        $('.add-top1:first').clone().insertAfter('.add-top1:last');
//        $('.add-top1:first').clone().insertAfter('.add-top1:last');
//    });
//	
//	$("#tend-caret").click(function(){
//        $('.tend-top1:first').clone().insertAfter('.tend-top1:last');
//        $('.tend-top1:first').clone().insertAfter('.tend-top1:last');
//        $('.tend-top1:first').clone().insertAfter('.tend-top1:last');
//    });
//	
//	$("#proj-caret").click(function(){
//        $('.proj-top1:first').clone().insertAfter('.proj-top1:last');
//        $('.proj-top1:first').clone().insertAfter('.proj-top1:last');
//        $('.proj-top1:first').clone().insertAfter('.proj-top1:last');
//    });
//	
//	$(".tidiDown").click(function(){
//        $('.tidi4:first').clone().insertAfter('.tidi4:last');
//    });

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
});