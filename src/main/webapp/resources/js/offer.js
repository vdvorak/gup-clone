
var offer = {};
var phonesSet;

//    alert("Перед ажаксом: " + offerId);
$.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    url: "/api/rest/offersService/offer/id/" + offerId + "/read",
    async: false,
    success: function (response) {
        offer = response;
        alert("Первый" + JSON.stringify(offer))
    }
});

// ----------- Draw offer -------------------------------------------------------------------------------------------
alert("Второй"+ offer.title)
$('.offer-title').text(offer.title);
$('.offer-price').text(offer.price);


$('.show-number').on('click', function () {
    var phoneList = ' ';
    for (var i = 0; i < offer.userInfo.phoneNumbers.length; i++) {
        phoneList = phoneList += offer.userInfo.phoneNumbers[i] + "\n";
    }
    $('.phone-numbers').text(phoneList);
    $('.show-number').remove();
})
// ----------- Draw offer -------------------------------------------------------------------------------------------






