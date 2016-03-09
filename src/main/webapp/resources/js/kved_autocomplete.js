/*
 * Created by Комп2 on 04.03.2016.
 * copy from search-bar.js
 */
$(function() {
    $("#searchInputKved").autocomplete({
        source: function (request, response) {
            //var selectedService = $('#selectedService').find(":selected").val();
            $.getJSON("/search/autocomplete/nace" , {
                term: request.term
            }, response);
        }
    });
});