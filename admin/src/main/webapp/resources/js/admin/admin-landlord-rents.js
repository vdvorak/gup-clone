var gupEvents = [];
var offerResult;

$(document).ready(function() {

    var zone = "05:30";

    ///////////////////////////////////////////////// admin-rents.js ///////////////////////////////////////////////////


    /* select offer(s)
     -----------------------------------------------------------------*/
    var emptyObj = {};

    var initialLocaleCode = 'ru';

    var request = $.ajax({
        type: 'POST',
        contentType: "application/json; charset=utf-8",
        url: 'http://gup.com.ua:8184/api/rest/offersService/offer/read/all',
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
            var gupEventWeekday = parseJsonWeekday(offerResult[index].offer.monthOfPrices), gupEventWeekend = parseJsonWeekend(offerResult[index].offer.monthOfPrices), gupEventSpecialdays = parseJsonSpecialdays(offerResult[index].offer.monthOfPrices);
            gupEvents = gupEvents.concat(gupEventWeekday, gupEventWeekend, gupEventSpecialdays);
            $('#offers-result2').html(offerResult[index].offer.id);
            $('#offers-result3').html(offerResult[index].offer.userInfo.contactName);
            $('#offers-result41').html(JSON.stringify(offerResult[index].offer.monthOfPrices) + '<br><br>');
            $('#offers-result42').html(JSON.stringify(offerResult[index].offer.rents) + '<br>');

            $('#external-events').html('<p>'
            + '<center><img src="../../../resources/fullcalendar/img/trashcan.png" id="trash" alt=""/></center>'
            + '</p><p style="padding-bottom: 16px; border-bottom:1px solid #aed0ea;">'
            + '<select id="set-price">'
            + '<option></option>'
            + '<option value="single">Единная цена</option>'
            + '<option value="weekend">Цена на выходные</option>'
            + '<option value="weekday">Цена на будние</option>'
            + '<option value="specialdays" selected>Специальная цена</option>'
            + '</select>'
            + ' &nbsp; <input type="checkbox" id="drop-remove" checked="checked" style="float:right; margin-top:2px;" />'
            + '<br/><br/><input type="text" id="addPriceButton" style="width:100%" value="0" />'
            + '</p>');
            $('#external-events').append('<div class="" style="background:#aca; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на выходные">' + offerResult[index].offer.monthOfPrices.weekend.price + '</div>');
            $('#external-events').append('<div class="" style="background:#aba; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на будние">' + offerResult[index].offer.monthOfPrices.weekday.price + '</div>');
            $('#external-events').append('<div class="fc-event" title="Специальная цена">' + offerResult[index].offer.monthOfPrices.specialdays[0].price + '</div>');
        }
    }).then(l=> {
        console.log( gupEvents )

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
                right: 'agendaDay,month,listWeek' ////right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2017-04-12',
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
            googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE', // To make your own Google API key, follow the directions here: http://fullcalendar.io/docs/google_calendar/
            events: gupEvents,
            drop: function() {
                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }
            },
            eventReceive: function(event){
                var title = event.title;
                var start = event.start.format("YYYY-MM-DD[T]HH:mm:SS");
                $.ajax({
                    url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                    data: 'type=new&title='+title+'&startdate='+start+'&zone='+zone,
                    type: 'POST',
                    dataType: 'json',
                    success: function(response){
                        event.id = response.eventid;
                        $('#calendar').fullCalendar('updateEvent',event);
                    },
                    error: function(e){
                        console.log(e.responseText);
                    }
                });
                $('#calendar').fullCalendar('updateEvent',event);
                console.log(event);
            },
            eventDrop: function(event, delta, revertFunc) {
                var title = event.title;
                var start = event.start.format();
                var end = (event.end == null) ? start : event.end.format();
                $.ajax({
                    url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                    data: 'type=resetdate&title='+title+'&start='+start+'&end='+end+'&eventid='+event.id,
                    type: 'POST',
                    dataType: 'json',
                    success: function(response){
                        if(response.status != 'success')
                            revertFunc();
                    },
                    error: function(e){
                        revertFunc();
                        alert('Error processing your request: '+e.responseText);
                    }
                });
            },
            /*
             eventClick: function(event) {
             // opens events in a popup window
             window.open(event.url, 'gcalevent', 'width=700,height=600');
             return false;
             },
             */
            eventClick: function(event, jsEvent, view) {
                console.log(event.id);
                var title = prompt('Event Title:', event.title, { buttons: { Ok: true, Cancel: false} });
                if (title){
                    event.title = title;
                    console.log('type=changetitle&title='+title+'&eventid='+event.id);
                    $.ajax({
                        url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                        data: 'type=changetitle&title='+title+'&eventid='+event.id,
                        type: 'POST',
                        dataType: 'json',
                        success: function(response){
                            if(response.status == 'success')
                                $('#calendar').fullCalendar('updateEvent',event);
                        },
                        error: function(e){
                            alert('Error processing your request: '+e.responseText);
                        }
                    });
                }
            },
            eventResize: function(event, delta, revertFunc) {
                console.log(event);
                var title = event.title;
                var end = event.end.format();
                var start = event.start.format();
                $.ajax({
                    url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                    data: 'type=resetdate&title='+title+'&start='+start+'&end='+end+'&eventid='+event.id,
                    type: 'POST',
                    dataType: 'json',
                    success: function(response){
                        if(response.status != 'success')
                            revertFunc();
                    },
                    error: function(e){
                        revertFunc();
                        alert('Error processing your request: '+e.responseText);
                    }
                });
            },
            /*
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
             */
            eventDragStop: function (event, jsEvent, ui, view) {
                if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                    var con = confirm('Are you sure to delete this event permanently?');
                    if(con == true) {
                        $('#calendar').fullCalendar('removeEvents', event._id);
                        var el = $( "<div class='fc-event'>" ).appendTo( '#external-events-listing' ).text( event.title );
                        el.draggable({
                            zIndex: 999,
                            revert: true,
                            revertDuration: 0
                        });
                        el.data('event', { title: event.title, id :event.id, stick: true });
                        //////////////////////////////////////////////////////////////////////
                        $.ajax({
                            url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                            data: 'type=remove&eventid='+event.id,
                            type: 'POST',
                            dataType: 'json',
                            success: function(response){
                                console.log(response);
                                if(response.status == 'success'){
                                    $('#calendar').fullCalendar('removeEvents');
                                    getFreshEvents();
                                }
                            },
                            error: function(e){
                                alert('Error processing your request: '+e.responseText);
                            }
                        });
                    }
                }
            },
            loading: function(bool) {
                $('#loading').toggle(bool);
            }
        });

        /////////////////////////////////////////////////////////
        $( "#addPriceButton" ).bind('keypress', function(e) {
            var code = e.keyCode || e.which;
            if(code == 13) {
                if (document.getElementById('set-price').value === 'specialdays'){
                    $('#external-events').append('<div class="fc-event" title="Специальная цена">' + $(this).val() + '</div>');
                }
                if (document.getElementById('set-price').value === 'weekday'){
                    $('#external-events').append('<div class="" style="background:#aba; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на будние">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                if (document.getElementById('set-price').value === 'weekend'){
                    $('#external-events').append('<div class="" style="background:#aca; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на выходные">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                if (document.getElementById('set-price').value === 'single'){
                    $('#external-events').append('<div class="" style="background:#ada; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Единная цена">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }

                /////////////////////////////////////////////////
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
            }
        });
    });



    $('#savePriceButton').click(function() {
        console.log('type=resetdate&title=1000&start=2016-11-02&end=2016-11-04&eventid=undefined');
        $.ajax({
            url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
            data: 'type=resetdate&title=1000&start=2016-11-02&end=2016-11-04&eventid=undefined',
            type: 'POST',
            dataType: 'json',
            success: function(response){
                if(response.status != 'success')
                    revertFunc();
            },
            error: function(e){
                revertFunc();
                alert('Error processing your request: '+e.responseText);
            }
        });
        $("#savePriceButton").attr('class', 'btn btn-primary disabled');
        $("#cancelPriceButton").attr('class', 'btn btn-primary disabled');
    });

    $('#cancelPriceButton').click(function() {
        index = document.getElementById('offers-selector').selectedIndex
        $('#external-events').html('<p>'
        + '<center><img src="../../../resources/fullcalendar/img/trashcan.png" id="trash" alt=""/></center>'
        + '</p><p style="padding-bottom: 16px; border-bottom:1px solid #aed0ea;">'
        + '<select id="set-price">'
        + '<option></option>'
        + '<option value="single">Единная цена</option>'
        + '<option value="weekend">Цена на выходные</option>'
        + '<option value="weekday">Цена на будние</option>'
        + '<option value="specialdays" selected>Специальная цена</option>'
        + '</select>'
        + ' &nbsp; <input type="checkbox" id="drop-remove" checked="checked" style="float:right; margin-top:2px;" />'
        + '<br/><br/><input type="text" id="addPriceButton" style="width:100%" value="0" />'
        + '</p>');
        $('#external-events').append('<div class="" style="background:#aca; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на выходные">' + offerResult[index].offer.monthOfPrices.weekend.price + '</div>');
        $('#external-events').append('<div class="" style="background:#aba; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на будние">' + offerResult[index].offer.monthOfPrices.weekday.price + '</div>');
        $('#external-events').append('<div class="fc-event" title="Специальная цена">' + offerResult[index].offer.monthOfPrices.specialdays[0].price + '</div>');
        $("#savePriceButton").attr('class', 'btn btn-primary disabled');
        $("#cancelPriceButton").attr('class', 'btn btn-primary disabled');
        /////////////////////////////////////////////////
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
        /////////////////////////////////////////////////
        $( "#addPriceButton" ).bind('keypress', function(e) {
            var code = e.keyCode || e.which;
            if(code == 13) {
                if (document.getElementById('set-price').value === 'specialdays'){
                    $('#external-events').append('<div class="fc-event" title="Специальная цена">' + $(this).val() + '</div>');
                }
                if (document.getElementById('set-price').value === 'weekday'){
                    $('#external-events').append('<div class="" style="background:#aba; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на будние">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                if (document.getElementById('set-price').value === 'weekend'){
                    $('#external-events').append('<div class="" style="background:#aca; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на выходные">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                if (document.getElementById('set-price').value === 'single'){
                    $('#external-events').append('<div class="" style="background:#ada; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Единная цена">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                /////////////////////////////////////////////////
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
            }
        });
    });



    $('#offers-selector').change(function() {
        var index = document.getElementById('offers-selector').selectedIndex
        $('#offers-result2').html(offerResult[index].offer.id);
        $('#offers-result3').html(offerResult[index].offer.userInfo.contactName);

        $('#external-events').html('<p>'
        + '<center><img src="../../../resources/fullcalendar/img/trashcan.png" id="trash" alt=""/></center>'
        + '</p><p style="padding-bottom: 16px; border-bottom:1px solid #aed0ea;">'
        + '<select id="set-price">'
        + '<option></option>'
        + '<option value="single">Единная цена</option>'
        + '<option value="weekend">Цена на выходные</option>'
        + '<option value="weekday">Цена на будние</option>'
        + '<option value="specialdays" selected>Специальная цена</option>'
        + '</select>'
        + ' &nbsp; <input type="checkbox" id="drop-remove" checked="checked" style="float:right; margin-top:2px;" />'
        + '<br/><br/><input type="text" id="addPriceButton" style="width:100%" value="0" />'
        + '</p>');
        if(offerResult[index].offer.monthOfPrices === undefined){
            gupEvents = [];
            $('#offers-result41').html('');
        }else{
            $('#offers-result41').html(JSON.stringify(offerResult[index].offer.monthOfPrices) + '<br><br>');

            var gupEventWeekday = parseJsonWeekday(offerResult[index].offer.monthOfPrices), gupEventWeekend = parseJsonWeekend(offerResult[index].offer.monthOfPrices), gupEventSpecialdays = parseJsonSpecialdays(offerResult[index].offer.monthOfPrices);
            gupEvents = gupEvents.concat(gupEventWeekday, gupEventWeekend, gupEventSpecialdays);

            $('#external-events').append('<div class="" style="background:#aca; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на выходные">' + offerResult[index].offer.monthOfPrices.weekend.price + '</div>');
            $('#external-events').append('<div class="" style="background:#aba; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на будние">' + offerResult[index].offer.monthOfPrices.weekday.price + '</div>');
            $('#external-events').append('<div class="fc-event" title="Специальная цена">' + offerResult[index].offer.monthOfPrices.specialdays[0].price + '</div>');
        }
        if(offerResult[index].offer.rents === undefined){
            $('#offers-result42').html('');
        }else{
            $('#offers-result42').html(JSON.stringify(offerResult[index].offer.rents) + '<br>');
        }
        $("#savePriceButton").attr('class', 'btn btn-primary disabled');
        $("#cancelPriceButton").attr('class', 'btn btn-primary disabled');
        //////////////////////////////////////////////////////////////
        console.log( gupEvents )

        /* change the calendar
         -----------------------------------------------------------------*/

        $('#calendar').fullCalendar('destroy');
        $('#calendar').fullCalendar({
            theme: true,
            header: {
                left: 'prev,today,next',
                center: 'title',
                right: 'agendaDay,month,listWeek' ////right: 'month,agendaWeek,agendaDay'
            },
            defaultDate: '2017-04-12',
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
            googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE', // To make your own Google API key, follow the directions here: http://fullcalendar.io/docs/google_calendar/
            events: gupEvents,
            drop: function() {
                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                    // if so, remove the element from the "Draggable Events" list
                    $(this).remove();
                }
            },
            eventReceive: function(event){
                var title = event.title;
                var start = event.start.format("YYYY-MM-DD[T]HH:mm:SS");
                $.ajax({
                    url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                    data: 'type=new&title='+title+'&startdate='+start+'&zone='+zone,
                    type: 'POST',
                    dataType: 'json',
                    success: function(response){
                        event.id = response.eventid;
                        $('#calendar').fullCalendar('updateEvent',event);
                    },
                    error: function(e){
                        console.log(e.responseText);
                    }
                });
                $('#calendar').fullCalendar('updateEvent',event);
                console.log(event);
            },
            eventDrop: function(event, delta, revertFunc) {
                var title = event.title;
                var start = event.start.format();
                var end = (event.end == null) ? start : event.end.format();
                $.ajax({
                    url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                    data: 'type=resetdate&title='+title+'&start='+start+'&end='+end+'&eventid='+event.id,
                    type: 'POST',
                    dataType: 'json',
                    success: function(response){
                        if(response.status != 'success')
                            revertFunc();
                    },
                    error: function(e){
                        revertFunc();
                        alert('Error processing your request: '+e.responseText);
                    }
                });
            },
            /*
             eventClick: function(event) {
             // opens events in a popup window
             window.open(event.url, 'gcalevent', 'width=700,height=600');
             return false;
             },
             */
            eventClick: function(event, jsEvent, view) {
                console.log(event.id);
                var title = prompt('Event Title:', event.title, { buttons: { Ok: true, Cancel: false} });
                if (title){
                    event.title = title;
                    console.log('type=changetitle&title='+title+'&eventid='+event.id);
                    $.ajax({
                        url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                        data: 'type=changetitle&title='+title+'&eventid='+event.id,
                        type: 'POST',
                        dataType: 'json',
                        success: function(response){
                            if(response.status == 'success')
                                $('#calendar').fullCalendar('updateEvent',event);
                        },
                        error: function(e){
                            alert('Error processing your request: '+e.responseText);
                        }
                    });
                }
            },
            eventResize: function(event, delta, revertFunc) {
                console.log(event);
                var title = event.title;
                var end = event.end.format();
                var start = event.start.format();
                $.ajax({
                    url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                    data: 'type=resetdate&title='+title+'&start='+start+'&end='+end+'&eventid='+event.id,
                    type: 'POST',
                    dataType: 'json',
                    success: function(response){
                        if(response.status != 'success')
                            revertFunc();
                    },
                    error: function(e){
                        revertFunc();
                        alert('Error processing your request: '+e.responseText);
                    }
                });
            },
            /*
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
             */
            eventDragStop: function (event, jsEvent, ui, view) {
                if(isEventOverDiv(jsEvent.clientX, jsEvent.clientY)) {
                    var con = confirm('Are you sure to delete this event permanently?');
                    if(con == true) {
                        $('#calendar').fullCalendar('removeEvents', event._id);
                        var el = $( "<div class='fc-event'>" ).appendTo( '#external-events-listing' ).text( event.title );
                        el.draggable({
                            zIndex: 999,
                            revert: true,
                            revertDuration: 0
                        });
                        el.data('event', { title: event.title, id :event.id, stick: true });
                        //////////////////////////////////////////////////////////////////////
                        $.ajax({
                            url: 'http://956804.rb242731.web.hosting-test.net/process.php', // !!!!!
                            data: 'type=remove&eventid='+event.id,
                            type: 'POST',
                            dataType: 'json',
                            success: function(response){
                                console.log(response);
                                if(response.status == 'success'){
                                    $('#calendar').fullCalendar('removeEvents');
                                    getFreshEvents();
                                }
                            },
                            error: function(e){
                                alert('Error processing your request: '+e.responseText);
                            }
                        });
                    }
                }
            },
            loading: function(bool) {
                $('#loading').toggle(bool);
            }
        });
        $('#calendar').fullCalendar('render');
        /////////////////////////////////////////////////////////
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
        /////////////////////////////////////////////////////////
        $( "#addPriceButton" ).bind('keypress', function(e) {
            var code = e.keyCode || e.which;
            if(code == 13) {
                if (document.getElementById('set-price').value === 'specialdays'){
                    $('#external-events').append('<div class="fc-event" title="Специальная цена">' + $(this).val() + '</div>');
                }
                if (document.getElementById('set-price').value === 'weekday'){
                    $('#external-events').append('<div class="" style="background:#aba; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на будние">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                if (document.getElementById('set-price').value === 'weekend'){
                    $('#external-events').append('<div class="" style="background:#aca; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Цена на выходные">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }
                if (document.getElementById('set-price').value === 'single'){
                    $('#external-events').append('<div class="" style="background:#ada; margin:0px 0px 10px; padding:0px 3px; color:#fff; font-size:12px;" title="Единная цена">' + $(this).val() + '</div>');
                    $("#savePriceButton").attr('class', 'btn btn-primary');
                    $("#cancelPriceButton").attr('class', 'btn btn-primary');
                }

                /////////////////////////////////////////////////
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
            }
        });
    });







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
