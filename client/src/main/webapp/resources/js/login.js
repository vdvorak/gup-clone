$(document).on("click", "#switchReg", function(){
	$('#switchLogin').removeClass('activeTab');
    $(this).addClass('activeTab');
    $("#login").css('display', 'none');
    $("#registration").css('display', 'block');
           

});
$(document).on("click", "#switchLogin", function(){
	$('#switchReg').removeClass('activeTab');
    $(this).addClass('activeTab');
    $("#registration").css('display', 'none');
    $("#login").css('display', 'block');
});
$(document).ready(function(){
	
});
