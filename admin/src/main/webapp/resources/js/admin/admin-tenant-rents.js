var gupEvents = [];
var offerResult;

$(document).ready(function() {

    ///////////////////////////////////////////////// admin-rents.js ///////////////////////////////////////////////////



    /* select offer(s)
     -----------------------------------------------------------------*/
    var emptyObj = {};

    var initialLocaleCode = 'ru';

    var request = $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: 'http://gup.com.ua:8186/swagger/rest/offersService/offer/read/all',
        data: JSON.stringify(emptyObj),
        error: function(offers) {
            console.log(offers.responseText);
        },
        success: function(offers){
            offerResult = JSON.parse( JSON.stringify(offers) );

            // "id":"587659524c8ef1b9713b5ca3"
            offerResult.forEach(function(el) {
                if (el.offer.id  === '587659524c8ef1b9713b5ca3'){
                    $('#offers-selector').append('<option title="' + el.offer.id + '" selected>'+el.offer.title+'</option>')
                }else{
                    $('#offers-selector').append('<option title="' + el.offer.id + '">'+el.offer.title+'</option>')
                }
            })
            //////////////////////////////////////////////////////////////////////////////////////////
            var index = document.getElementById('offers-selector').selectedIndex
//					console.log( parseJsonExpired(offerResult[index].offer.rents) )
//					console.log( parseJsonRented(offerResult[index].offer.rents) )
//					console.log( parseJsonRentedName(offerResult[index].offer.rents) )
            var gupEventExpired = parseJsonExpired(offerResult[index].offer.rents), gupEventRented = parseJsonRented(offerResult[index].offer.rents), gupEventRentedName = parseJsonRentedName(offerResult[index].offer.rents);
            gupEvents = gupEvents.concat(gupEventExpired, gupEventRented, gupEventRentedName);
            $('#offers-result2').html(offerResult[index].offer.id);
            $('#offers-result3').html(offerResult[index].offer.userInfo.contactName);
            $('#offers-result41').html(JSON.stringify(offerResult[index].offer.monthOfPrices) + '<br><br>');
            $('#offers-result42').html(JSON.stringify(offerResult[index].offer.rents) + '<br>');
        }
    }).then(l=> {
        //console.log( gupEvents )

        /* initialize the external events
         -----------------------------------------------------------------*/
        $('#external-events .fc-event').each(function() {
            // store data so the calendar knows to render an event upon drop
            $(this).data('event', {
                title: $.trim($(this).text()), // use the element's text as the event title
                stick: true                    // maintain when user navigates (see docs on the renderEvent method)
            });
            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true,                  // will cause the event to go back to its
                revertDuration: 0              // original position after the drag
            });
        });

        /* initialize the calendar
         -----------------------------------------------------------------*/
        $('#calendar').fullCalendar({
            theme: true,
            header: {
                left: 'prev,today,next',
                center: 'title',
                right: 'agendaDay,month' ////right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2017-05-12',
            locale: initialLocaleCode,
//                buttonIcons: false,      // show the prev/next text
            weekNumbers: false,
            editable: true,
            navLinks: true,          // can click day/week names to navigate views
            eventLimit: true,        // allow "more" link when too many events
            businessHours: true,     // display business hours
            displayEventTime: false, // don't show the time column in list view
            droppable: true,         // this allows things to be dropped onto the calendar
            dragRevertDuration: 0,
            height: "auto",
            googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE', // To make your own Google API key, follow the directions here: http://fullcalendar.io/docs/google_calendar/
            events: gupEvents,
            drop: function() {
                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }
            },
            eventDragStop: function( event, jsEvent, ui, view ) {
                if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                    $('#calendar').fullCalendar('removeEvents', event._id);
                    var el = $( "<div class='fc-event'>" ).appendTo( '#external-events-listing' ).text( event.title );
                    el.draggable({
                        zIndex: 999,
                        revert: true,
                        revertDuration: 0
                    });
                    el.data('event', { title: event.title, id :event.id, stick: true });
                }
            },
            eventClick: function(event) {
                // opens events in a popup window
                window.open(event.url, 'gcalevent', 'width=700,height=600');
                return false;
            },
            loading: function(bool) {
                $('#loading').toggle(bool);
            }
        });

        $( "#addPriceButton" ).bind('keypress', function(e) {
            var code = e.keyCode || e.which;
            if(code == 13) {
                $('#external-events').append('<div class="fc-event">' + $(this).val() + ' $</div>');
            }
        });
    });





    $('#offers-selector').change(function() {
        var index = document.getElementById('offers-selector').selectedIndex
        $('#offers-result2').html(offerResult[index].offer.id);
        $('#offers-result3').html(offerResult[index].offer.userInfo.contactName);

        if(offerResult[index].offer.monthOfPrices === undefined){
            gupEvents = [];
            $('#offers-result41').html('');
        }else{
            $('#offers-result41').html(JSON.stringify(offerResult[index].offer.monthOfPrices) + '<br><br>');

            var gupEventExpired = parseJsonExpired(offerResult[index].offer.rents), gupEventRented = parseJsonRented(offerResult[index].offer.rents), gupEventRentedName = parseJsonRentedName(offerResult[index].offer.rents);
            gupEvents = gupEvents.concat(gupEventExpired, gupEventRented, gupEventRentedName);
        }
        if(offerResult[index].offer.rents === undefined){
            $('#offers-result42').html('');
        }else{
            $('#offers-result42').html(JSON.stringify(offerResult[index].offer.rents) + '<br>');
        }
        //////////////////////////////////////////////////////////////
        //console.log( gupEvents )

        /* change the calendar
         -----------------------------------------------------------------*/

        $('#calendar').fullCalendar('destroy');
        $('#calendar').fullCalendar({
            theme: true,
            header: {
                left: 'prev,today,next',
                center: 'title',
                right: 'agendaDay,month' ////right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2017-05-12',
            locale: initialLocaleCode,
//                buttonIcons: false,      // show the prev/next text
            weekNumbers: false,
            editable: true,
            navLinks: true,          // can click day/week names to navigate views
            eventLimit: true,        // allow "more" link when too many events
            businessHours: true,     // display business hours
            displayEventTime: false, // don't show the time column in list view
            droppable: true,         // this allows things to be dropped onto the calendar
            dragRevertDuration: 0,
            height: "auto",
            googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE', // To make your own Google API key, follow the directions here: http://fullcalendar.io/docs/google_calendar/
            events: gupEvents,
            drop: function() {
                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }
            },
            eventDragStop: function( event, jsEvent, ui, view ) {
                if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                    $('#calendar').fullCalendar('removeEvents', event._id);
                    var el = $( "<div class='fc-event'>" ).appendTo( '#external-events-listing' ).text( event.title );
                    el.draggable({
                        zIndex: 999,
                        revert: true,
                        revertDuration: 0
                    });
                    el.data('event', { title: event.title, id :event.id, stick: true });
                }
            },
            eventClick: function(event) {
                // opens events in a popup window
                window.open(event.url, 'gcalevent', 'width=700,height=600');
                return false;
            },
            loading: function(bool) {
                $('#loading').toggle(bool);
            }
        });
        $('#calendar').fullCalendar('render');
    });





    var isEventOverDiv = function(x, y) {
        var external_events = $( '#external-events' );
        var offset = external_events.offset();
        offset.right = external_events.width() + offset.left;
        offset.bottom = external_events.height() + offset.top;

        // Compare
        if (x >= offset.left
            && y >= offset.top
            && x <= offset.right
            && y <= offset .bottom) { return true; }
        return false;
    }

    // build the locale selector's options
    $.each($.fullCalendar.locales, function(localeCode) {
        $('#locale-selector').append(
            $('<option/>')
                .attr('value', localeCode)
                .prop('selected', localeCode == initialLocaleCode)
                .text(localeCode)
        );
    });
    // when the selected option changes, dynamically change the calendar option
    $('#locale-selector').on('change', function() {
        if (this.value) {
            $('#calendar').fullCalendar('option', 'locale', this.value);
        }
    });
});
