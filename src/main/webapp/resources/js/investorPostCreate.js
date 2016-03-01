//$('#tab-container-pVSi').easytabs({
//    animate: false
//});

$("#createInvestorPost").on('click', function (event) {
    var incorrectValuesMsg = '';
    var newInvestorPost = {};

    newInvestorPost.categoriesOfIndustry = $('#main-kvd-info').val().split(" ");
    newInvestorPost.minInvestAmount = +$('#sum1').val();
    newInvestorPost.maxInvestAmount = +$('#sum2').val();
    newInvestorPost.description = $('#description').val();

    if (newInvestorPost.categoriesOfIndustry.length < 1) {incorrectValuesMsg += "Добавьте категории \n";}
    if (newInvestorPost.minInvestAmount < 1) {incorrectValuesMsg += "Минимальная сумма должна быть больше 1 \n";}
    if (newInvestorPost.maxInvestAmount < 1) {incorrectValuesMsg += "Максимальная сумма должна быть больше 1  \n";}
    if (newInvestorPost.minInvestAmount > newInvestorPost.maxInvestAmount) {incorrectValuesMsg += "Максимальная сумма должна быть больше миимальной  \n";}
    if (newInvestorPost.description.length < 50 || newInvestorPost.description.length > 5000) {incorrectValuesMsg += "Добавьте описание \n";}

    if (!incorrectValuesMsg.length) {
        $.ajax({
            type: "POST",
            url: "/api/rest/projectsAndInvestmentsService/investorPost/create",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(newInvestorPost),
            statusCode: {
                201: function (createdProjectId) {
                    window.location.href = '/investorPost?id=' + createdProjectId.id;
                }
            }
        });
    } else {
        alert(incorrectValuesMsg);
    }
});