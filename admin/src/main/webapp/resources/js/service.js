/**
 * Created by Optical Illusion on 21.01.2016.
 */

// Change all date-long values on the page into human-like form
$(document).ready(function () {
    $( ".date-create").each(function( index ) {
        var date = $(this).text();
        date = new Date(parseInt(date));
        date = moment(date).locale("ru").format('LLL');
        $(this).text(date);
    });
});