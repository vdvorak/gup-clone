/**
 * Created by Юля on 15.03.2016.
 */
var offerFilter = window.OfferFilter;

var cities = {};
var parameters = [];
var options = [];
var jsonCategory = [];
var jsonSubcategory = {};
var nace = [];

var loadCities =
    $.ajax({
        type: "GET",
        url: "/resources/json/cities.json",
        dataType: "json",
        success: function (response) {
            cities = response;
        }
    });


var loadCategories =
    $.ajax({
        type: "GET",
        url: "/resources/json/searchCategories.json",
        dataType: "json",
        success: function (response) {
            jsonCategory = response;
            offerFilter.drawSubcategories();
        }
    })


var loadSubcategories =
    $.ajax({
        type: "GET",
        url: "/resources/json/searchSubcategories.json",
        dataType: "json",
        success: function (response) {
            jsonSubcategory = response;
        }
    });


var loadOptions =
    $.ajax({
        type: "GET",
        url: "/resources/json/searchValues.json",
        dataType: "json",
        success: function (response) {
            options = response;
        }
    });


var loadParameters =
    $.ajax({
        type: "GET",
        url: "/resources/json/parameters.json",
        dataType: "json",
        success: function (response) {
            parameters = response;
        }
    });

var loadNace =
    $.ajax({
        type: "GET",
        url: "/resources/json/kved.json",
        dataType: "json",
        success: function (response) {
            nace = response;
        }
    });



