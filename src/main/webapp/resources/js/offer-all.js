/**
 * Created by Юля on 19.05.2016.
 */
var offerFilter = window.OfferFilter;

$(document).ready(function () {
    offerFilter.parseUrlToFilter()
        .readAllByFilter();

    var categories = offerFilter.utils.categories;
    if (categories && categories.length) {
        $.when(window.loadOptions, window.loadParameters).done(function () {
            offerFilter.drawFilterOptions(categories[categories.length - 1]);
        })
        $.when(window.loadSubcategories).done(function () {
            if (categories.length > 1) offerFilter.drawCategories3lvl();
        })
    }

    var address = offerFilter.utils.address;
    if (address.area) $('#input-selected-area').val(address.area);
    if (address.city) $('#input-selected-city').val(address.city);
    offerFilter.generateFilterRegionString();

    $('#btn-offers-more').click(offerFilter.submitFilter);

    $(window).on('scroll', function () {
        if ($(window).scrollTop() >= $('#btn-offers-more').offset().top + $('#btn-offers-more').outerHeight() - window.innerHeight) {
            $('#btn-offers-more').trigger('click', offerFilter.submitFilter);
        }
    });

    $('#select-categories-3lvl').change(offerFilter.selectCategoryLvl3);
    $('#filter-price').change(offerFilter.selectFilterPrice);
});
