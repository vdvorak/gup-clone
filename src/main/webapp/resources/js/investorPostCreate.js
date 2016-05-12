if (typeof loggedInProfile == 'undefined') {
    $('#investor-post-create').empty().append('<div class="anonymUser"><p><i class="fa fa-exclamation-circle"> Для создания инвестиции вам необходимо зарегистрироваться</i></p></div>')
}else{

    var gupValidator = new window.GupValidator.Constructor('investorPost').init();

    $("#createInvestorPost").on('click', function (event) {

        var newInvestorPost = {};

        var categoriesText = $('#main-kvd-info').val();
        newInvestorPost.categoriesOfIndustry = (categoriesText) ? categoriesText.split(" ") : [];
        newInvestorPost.minInvestAmount = +$('#sum1').val();
        newInvestorPost.maxInvestAmount = +$('#sum2').val();
        newInvestorPost.description = $('#description').val();

        gupValidator.validate(newInvestorPost);
        if(!gupValidator.isValid) return;

        $.ajax({
            type: "POST",
            url: "/api/rest/projectsAndInvestmentsService/investorPost/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(newInvestorPost),
            statusCode: {
                201: function (createdProjectId) {
                    window.location.href = '/project/list';
                }
            }
        });

    });

}



