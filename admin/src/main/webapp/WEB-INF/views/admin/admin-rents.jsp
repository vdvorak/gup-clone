<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ru">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Аренда | Панель управления</title>
    <meta charset='utf-8' />

    <!-- Links -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-top-links.jsp"/>
    <!-- Links -->

    <script>
        $(document).ready(function() {
            var initialLocaleCode = 'ru';

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
                    revertDuration: 0              //  original position after the drag
                });
            });

            $('#calendar').fullCalendar({
                theme: true,
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'month,agendaDay,listWeek' ////right: 'month,agendaWeek,agendaDay'
                },
                defaultDate: '2016-11-25',
                locale: initialLocaleCode,
//                buttonIcons: false,      // show the prev/next text
                weekNumbers: false,
                editable: true,
                navLinks: true,          // can click day/week names to navigate views
                eventLimit: true,        // allow "more" link when too many events
                businessHours: true,     // display business hours
                displayEventTime: false, // don't show the time column in list view
                droppable: true,         // this allows things to be dropped onto the calendar
                googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE', // To make your own Google API key, follow the directions here: http://fullcalendar.io/docs/google_calendar/
                events: {
                    url: 'resources/fullcalendar/demos/json/events.json',
                    error: function() {
                        $('#script-warning').show();
                    }
                },
                drop: function() {
                    // is the "remove after drop" checkbox checked?
                    if ($('#drop-remove').is(':checked')) {
                        // if so, remove the element from the "Draggable Events" list
                        $(this).remove();
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
    </script>
    <style>
        #calendar {
            max-width: 1075px;
            margin: 0px auto;
            padding: 0 10px;
            float: right;
        }

        #locale-selector {
            float: left;
            width: 150px;
            background: #3baae3 url("../../../resources/fullcalendar/lib/cupertino/images/ui-bg_glass_50_3baae3_1x400.png") repeat-x scroll 50% 50%;
            border: 1px solid #2694e8;
            border-radius: 5px;
            padding: 0px 8px;
            font-weight: bold;
            font-size: 20px;
            color: #ffffff;
        }

        #external-events {
            float: left;
            width: 150px;
            padding: 0 10px;
            border: 1px solid #aed0ea;
            background: #deedf7;
            text-align: left;
            margin-top: 52px;
            margin-left: -150px;
        }
        #external-events h4 {
            font-size: 16px;
            margin-top: 0;
            padding-top: 1em;
        }
        #external-events .fc-event {
            margin: 10px 0;
            cursor: pointer;
        }
        #external-events p {
            margin: 1.5em 0;
            font-size: 11px;
            color: #666;
        }
        #external-events p input {
            margin: 0;
            vertical-align: middle;
        }
    </style>
    </head>
    <body>

        <div id="wrapper">

            <!-- Navigation -->
            <jsp:include page="/WEB-INF/templates/admin-panel/admin-left-bar.jsp"/>
            <!-- Navigation -->

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Аренда</h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="dataTable_wrapper">
                                    <select id='locale-selector'></select>

                                    <div id='external-events'>
                                        <p><label>Стоимость аренды</label></p>
                                        <div class='fc-event'>$ 1000.00</div>
                                        <div class='fc-event'>$ 1111.00</div>
                                        <div class='fc-event'>$ 1500.00</div>
                                        <div class='fc-event'>$ 2000.00</div>
                                        <div class='fc-event'>$ 2222.00</div>
                                    </div>

                                    <div id='calendar'></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

    <!-- Bottom Links -->
    <jsp:include page="/WEB-INF/templates/admin-panel/admin-bottom-links2.jsp"/>
    <!-- Bottom Links -->
</html>
