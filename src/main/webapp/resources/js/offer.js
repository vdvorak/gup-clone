
var offer = {};
var phonesSet;
var jsonCategory = '';
var jsonSubcategory = '';

$.ajax({
    type: "GET",
    url: "/resources/json/searchCategories.json",
    dataType: "json",
    async: false,
    success: function (response) {
        jsonCategory = response;
    }
});

$.ajax({
    type: "GET",
    url: "/resources/json/searchSubcategories.json",
    dataType: "json",
    async: false,
    success: function (response) {
        jsonSubcategory = response;
    }
});



//    alert("Перед ажаксом: " + offerId);
$.ajax({
    type: "POST",
    contentType: "application/json; charset=utf-8",
    url: "/api/rest/offersService/offer/id/" + offerId + "/read",
    async: false,
    success: function (response) {
        offer = response;
        //alert("Первый" + JSON.stringify(offer))
    }
});

// ----------- Draw offer -------------------------------------------------------------------------------------------
$('.offer-title').text(offer.title);
$('.offer-price').text(offer.price);
$('.offer-video').append('<iframe width="560" height="315" src="https://www.youtube.com/embed/' + offer.videoUrl.split('=')[1] + '" frameborder="0" allowfullscreen></iframe>');
$('.offer-map').append('<iframe width="300" height="225" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/place?q=place_id:' + offer.address.coordinates +'&key=AIzaSyBTOK35ibuwO8eBj0LTdROFPbX40SWrfww" allowfullscreen></iframe>');


$('.offer-skype').text(offer.userInfo.skypeLogin);
$('.offer-description').text(offer.description);



var currency = $('.currency');
switch(offer.currency) {
    case 'UAH':
        currency.text(' грн.');
        break;
    case 'USD':
        currency.text(' дол.');
        break;
    case 'EUR':
        currency.text(' евро');
        break;
    default: currency.text(' грн.')
}



if (offer.currency === 'UAH'){
    $('.currency').text(' грн.')
}








var breadcrumbs = offer.categories;
if (breadcrumbs[0]) {
    for (var i = 0; i < jsonCategory.length; i++) {
        if (jsonCategory[i].id === +breadcrumbs[0]) {
            $('#breadcrumbs').append('<li><a href="#">' + jsonCategory[i].name + '</a>' + "/" + '</li>');

            if (breadcrumbs[1]) {
                for (var m in jsonCategory[i].children) {
                    if (jsonCategory[i].children[m].id == +breadcrumbs[1]) {
                        $('#breadcrumbs').append('<li><a href="#">' + jsonCategory[i].children[m].name + '</a>' + "/" + '</li>');
                    }
                }
            }
            if (breadcrumbs[2]) {
                var obj1 = +breadcrumbs[1] + "";
                var obj2 = +breadcrumbs[2] + "";
                $('#breadcrumbs').append('<li><a href="#">' + jsonSubcategory[obj1].children[obj2].label + '</a>' + '</li>');
            }
        }
    }
}










$('.show-number').on('click', function () {
    var phoneList = ' ';
    for (var i = 0; i < offer.userInfo.phoneNumbers.length; i++) {
        phoneList = phoneList += offer.userInfo.phoneNumbers[i] + "\n";
    }
    $('.phone-numbers').text(phoneList);
    $('.show-number').remove();
})
// ----------- Draw offer -------------------------------------------------------------------------------------------






