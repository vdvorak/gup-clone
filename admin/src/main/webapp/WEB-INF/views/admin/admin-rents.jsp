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
            var initialLocaleCode = 'en';

            $('#calendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,agendaWeek,agendaDay,listMonth'
                },
                defaultDate: '2016-09-12',
                locale: initialLocaleCode,
                buttonIcons: false, // show the prev/next text
                weekNumbers: true,
                navLinks: true, // can click day/week names to navigate views
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'All Day Event',
                        start: '2016-09-01'
                    },
                    {
                        title: 'Long Event',
                        start: '2016-09-07',
                        end: '2016-09-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2016-09-09T16:00:00'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2016-09-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2016-09-11',
                        end: '2016-09-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2016-09-12T10:30:00',
                        end: '2016-09-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2016-09-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2016-09-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2016-09-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2016-09-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2016-09-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2016-09-28'
                    }
                ]
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
        body {
            margin: 0;
            padding: 0;
            font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
            font-size: 14px;
        }

        #top {
            background: #eee;
            border-bottom: 1px solid #ddd;
            padding: 0 10px;
            line-height: 40px;
            font-size: 12px;
        }

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
                                    <div id='top'>
                                        Locales:
                                        <select id='locale-selector'></select>
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
