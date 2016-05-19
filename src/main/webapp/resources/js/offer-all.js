/**
 * Created by Юля on 19.05.2016.
 */
var offers = window.offers;

$(document).ready(function () {
    offers.parseUrlToFilter()
        .readAllByFilter();

    var categories = offers.utils.categories;
    if (categories && categories.length) {
        $.when(window.loadOptions, window.loadParameters).done(function () {
            offers.drawFilterOptions(categories[categories.length - 1]);
        })
        $.when(window.loadSubcategories).done(function () {
            if (categories.length > 1) offers.drawCategories3lvl();
        })
    }

    var address = offers.utils.address;
    if (address.area) $('#input-selected-area').val(address.area);
    if (address.city) $('#input-selected-city').val(address.city);
    offers.generateFilterRegionString();

    $('#btn-offers-more').click(offers.submitFilter);

    $(window).on('scroll', function () {
        if ($(window).scrollTop() >= $('#btn-offers-more').offset().top + $('#btn-offers-more').outerHeight() - window.innerHeight) {
            $('#btn-offers-more').trigger('click', offers.submitFilter);
        }
    });

    $('#select-categories-3lvl').change(offers.selectCategoryLvl3);
    $('#filter-price').change(offers.selectFilterPrice);
});
