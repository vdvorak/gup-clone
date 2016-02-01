$('#myModal').on('shown.bs.modal', function () {
    $('#myInput').focus()
});

$('#deleteModal').on('shown.bs.modal', function () {
    $('#myInput').focus()
});

$('#amount').change(function(){
    $.ajax({
        url: '/account/getLiqPayParam',
        method: 'POST',
        data: {'amount': $('#amount').val()},
        success: function(response) {
            $('#liq-pay-data').val(response[0]);
            $('#liq-pay-signature').val(response[1]);
        }
    });
});
