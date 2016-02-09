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

    $('#tab-container-profile').easytabs({
        animate: false
    });

    $('.bxslider').bxSlider();

    $('#tab-container-news').easytabs({
        animate: false
    });
});