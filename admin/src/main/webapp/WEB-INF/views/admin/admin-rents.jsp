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

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,today,next',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay'
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
                googleCalendarApiKey: 'AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE', // To make your own Google API key, follow the directions here: http://fullcalendar.io/docs/google_calendar/
                events: {
                    url: 'resources/fullcalendar/demos/json/events.json',
                    error: function() {
                        $('#script-warning').show();
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
            max-width: 900px;
            margin: 40px auto;
            padding: 0 10px;
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
