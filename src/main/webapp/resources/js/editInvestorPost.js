var investorPostId = getUrlParam('id');
var updatedInvestorPost = {};
var loadedInvestorPost = {};

loadAndAppendInvestorPostInfo(investorPostId);

function loadAndAppendInvestorPostInfo(investorPostId) {
    $.ajax({
        type: "GET",
        url: "/api/rest/projectsAndInvestmentsService/investorPost/id/" + investorPostId + " /read",
        statusCode: {
            200: function (investorPost) {
                loadedInvestorPost = investorPost;
                appendInvestorPostInfo(loadedInvestorPost);
            }
        }
    });
}

function appendInvestorPostInfo(loadedInvestorPost) {
    $('#sum1').val(loadedInvestorPost.minInvestAmount);
    $('#sum2').val(loadedInvestorPost.maxInvestAmount);
    $('#description').val(loadedInvestorPost.description)
}

$('#updateInvestorPost').on('click', function () {
    initializeInvestorPostEntityForUpdate();
    $.ajax({
        type: "POST",
        url: "/api/rest/projectsAndInvestmentsService/investorPost/edit",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(updatedInvestorPost),
        statusCode: {
            200: function () {
                window.location.href = '/project/list';
            }
        }
    });
});

function initializeInvestorPostEntityForUpdate() {
    updatedInvestorPost.id = investorPostId;
    updatedInvestorPost.description = $('#description').val();
    updatedInvestorPost.minInvestAmount = $('#sum1').val();
    updatedInvestorPost.maxInvestAmount=$('#sum2').val();
}
