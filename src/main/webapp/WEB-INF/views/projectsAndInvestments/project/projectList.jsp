<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang=""> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Проекты | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/alster.css">

    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
</head>

<body>
    <!--[if lt IE 8]>
    <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade
        your browser</a> to improve your experience.</p>
    <![endif]-->

    <jsp:include page="/WEB-INF/templates/common-header.jsp"/>

    <jsp:include page="/WEB-INF/templates/logo-section.jsp"/>

    <jsp:include page="/WEB-INF/templates/search-bar.jsp"/>

    <jsp:include page="/WEB-INF/templates/services-menu.jsp"/>

    <body>

        <!--PAGE CONTENT START-->
        <div class="container2">
            <ul class="projectsVSInvestments-block">
                <li class="projectsVSInvestments-btn projects active" data-atab="projects" id="projectsTab"><a href="#tabs1-project">ПРОЕКТЫ</a></li>
                <!--Add "active" class to show this element as selected-->
                <li class="projectsVSInvestments-btn investments" data-atab="investments" id="investmentsTab"><a href="#tabs1-investment">ИНВЕСТИЦИИ</a></li>
            </ul>

            <div class="projectFeedContainer feedContainer contentContainer" id="projectsContainer">
                <div class="projectsVSInvestmentsCats" id="projectTypeBlock">
                    <div class="catContainer" name="RENOVATION">
                        <!--Add "selected" class to show this element as selected-->
                        <div class="catLogo restruct"></div>
                        <div class="catName">Реструктуризация</div>
                    </div>
                    <div class="catContainer" name="PROTOTYPE">
                        <div class="catLogo prototype"></div>
                        <div class="catName">Готовый прототип</div>
                    </div>
                    <div class="catContainer" name="PROJECT_ON_PAPER">
                        <div class="catLogo paper"></div>
                        <div class="catName">Проект на бумаге</div>
                    </div>
                    <div class="catContainer" name="KNOW_HOW">
                        <div class="catLogo nouHau"></div>
                        <div class="catName">Ноу-Хау</div>
                    </div>
                </div>

                <div id="projectsBlock">

                </div>
            </div>

            <div class="feedContainer investmentsFeedContainer contentContainer" id="investmentsContainer" style="display: none">
                <div id="investmentsBlock">
                    <div class="feedItem vip">
                        <!--Add class "vip" to vip-tialize investment-->
                        <div class="publishDate">Опубликовано: 22. 10. 16</div>
                        <div class="photo border-color">
                            <img src="/resources/css/images/profileListLogo.png" alt="user avatar"/>
                        </div>
                        <a href="#" class="content">
                            <div class="title">Заголовок (ФИО/Компании)</div>
                            <div class="desc">Описание</div>
                            <p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                        </a>
                        <div class="cats">
                            <!--Recommended max 14 elements-->
                            <ul>
                                <!--Emmet shortcut-->
                                <!--li*14>a[href="#"]>{IT Ресурсы}-->
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                            </ul>
                        </div>

                        <div class="details">
                            <div class="canInvest">15468900$</div>
                            <div class="alreadyInvested">
                                <div class="desc">Проинвестировано:</div>
                                <div class="total">15468900$</div>
                                <div class="totalProjects">25 проектов</div>
                            </div>
                        </div>
                    </div>
                    <div class="feedItem">
                        <!--Add class "vip" to vip-tialize investment-->
                        <div class="publishDate">Опубликовано: 22. 10. 16</div>
                        <div class="photo">
                            <img src="/resources/css/images/profileListLogo.png" alt="user avatar"/>
                        </div>
                        <a href="#" class="content">
                            <div class="title">Заголовок (ФИО/Компании)</div>
                            <div class="desc">Описание</div>
                            <p class="text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                        </a>
                        <div class="cats">
                            <!--Recommended max 14 elements-->
                            <ul>
                                <!--Emmet shortcut-->
                                <!--li*14>a[href="#"]>{IT Ресурсы}-->
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                                <li><a href="#">IT Ресурсы</a></li>
                            </ul>
                        </div>

                        <div class="details">
                            <div class="canInvest">15468900$</div>
                            <div class="alreadyInvested">
                                <div class="desc">Проинвестировано:</div>
                                <div class="total">15468900$</div>
                                <div class="totalProjects">25 проектов</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contentContainer" style="margin-top: 5px">
                <img class="projAndInvestCaretDown" id="showNext" src="/resources/images/caret.png" alt="caret">
            </div>

            <div class="feedFooter"></div>

        </div>

        <!--PAGE CONTENT END-->

        <sec:authorize access="isAuthenticated()">
            <jsp:include page="/WEB-INF/templates/support-questions.jsp"/>
        </sec:authorize>

        <jsp:include page="/WEB-INF/templates/footer.jsp"/>

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
        <script>window.jQuery || document.write('<script src="/resources/js/vendor/jquery-1.11.2.js"><\/script>')</script>
        <script src="/resources/js/vendor/bootstrap.js"></script>
        <script src="/resources/js/jquery.bxslider.js"></script>
        <script type="text/javascript"
                src="https://cdnjs.cloudflare.com/ajax/libs/jquery.easytabs/3.2.0/jquery.easytabs.min.js"></script>
        <script src="/resources/libs/jquery-ui-1.11.4/jquery-ui.min.js"></script>

        <sec:authorize var="loggedIn" access="isAuthenticated()"/>
        <c:choose>
            <c:when test="${loggedIn}">
                <script src="/resources/js/autorizedHeader.js"></script>
            </c:when>
            <c:otherwise>
                <script src="/resources/js/anonymHeader.js"></script>
            </c:otherwise>
        </c:choose>

        <script src="/resources/js/main.js"></script>
        <script src="/resources/js/logo-section.js"></script>
        <script src="/resources/js/search-bar.js"></script>

        <script>
            $("#selectedService option[value='project']").attr("selected","selected");

            var projectType = (getUrlParam('type') != null ? getUrlParam('type').toUpperCase() : null);
            var projectFO = {type : projectType, searchField : getUrlParam('name'), skip: 0, limit: 10};

            $('[name="' + projectType + '"]').addClass('selected');
            loadAndAppendProjectBlocks(projectFO);

            function loadAndAppendProjectBlocks(projectFO) {
                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "/api/rest/projectsAndInvestmentsService/project/read/all",
                    data: JSON.stringify(projectFO),
                    statusCode: {
                        200: function (responseEntity) {
//                            $('#foundedProjectsNum').text(responseEntity.totalEntities);
                            responseEntity.entities.forEach(function (project) {
                                appendProjectBlock(project);
                            });
                        }
                    }
                });
            }

            function getReadableCreatedDate(timestamp) {
                var createdDate = new Date(timestamp);
                return createdDate.getDate() + '.' + (createdDate.getMonth() + 1) + '.' + createdDate.getFullYear();
            }

            function getUrlForProjectMainPic(imagesIds) {
                for (var id in imagesIds) {
                    if (imagesIds[id] === "1") {
                        return "/api/rest/fileStorage/PROJECTS_AND_INVESTMENTS/file/read/id/" + id;
                    }
                }
                return "/resources/images/no_photo.jpg";
            }

            function getProjectUrl(id) {
                return "/project?id=" + id;
            }

            function getInvertedProgressNum(investedAmount, amountRequested) {
                var invertedProgressNum = (1 -(investedAmount/amountRequested))*100;
                return 5 * Math.ceil(invertedProgressNum/5);
            }

            function appendProjectBlock(project) {
                $('#projectsBlock').append(
                    '<div class="feedItem">' +
                        <!--Add class "vip" to vip-tialize project-->
                        '<div class="preview">' +
                        '<a href="' + getProjectUrl(project.id) + '">' +
                            '<img src="' + getUrlForProjectMainPic(project.imagesIds) + '" alt="project photo" />' +
                        '</a>' +
                            '<div class="likes">' +
                                '<div class="hearthPlace">' +
                                    '<div class="hearth"></div>' +
                                '</div>' +
                                '<div class="number">' + project.totalScore + '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="content">' +
                            '<a href="' + getProjectUrl(project.id) + '">' +
                                '<div class="publishDate">Опубликовано: ' + getReadableCreatedDate(project.createdDate) + '</div>' +
                                '<div class="title">' + project.title + '</div>' +
                            '</a>' +
                            '<div class="text">' + project.description + '</div>' +
                        '</div>' +
                        '<div class="bottomContent">' +
                            '<button type="button" class="abutton invest">Инвестировать</button>' +
                            '<div class="projectProgressBlock">' +
                                '<div class="current elem cash">' + project.investedAmount + ' ₴ </div>' +
                                '<div class="bar elem">' +
                                    '<div class="colored"></div>' +
                                    '<div class="empty" style="width: ' +
                                        getInvertedProgressNum(project.investedAmount, project.amountRequested) + '%;"></div>' +
                                    <!--Change style width percentage to vizualize progress (INVERTED). Допускаються значення квантовані по 5 процентів, тобто типу такі: 0, 5, 10, 15, 20, ...-->
                                '</div>' +
                                '<div class="todo elem cash">' + project.amountRequested + ' ₴ </div>' +
                            '</div>' +
                        '</div>' +
                    '</div>');
            }

            $('#showNext').on('click',function () {
                if($('#projectsTab').hasClass('active')) {
                    projectFO.skip += projectFO.limit;
                    loadAndAppendProjectBlocks(projectFO);
                } else {
                    alert('investmentFO');
                    investmentFO.skip += investmentFO.limit;
                    loadAndAppendInvestmentsBlocks(investmentFO);
                }

            });

            $('#projectsTab').on('click',function () {
                $('#investmentsContainer').hide();
                $('#projectsContainer').show();
                $('.catContainer').removeClass('selected');

                projectFO.type = null;
                projectFO.skip = 0;
                loadAndAppendProjectBlocks(projectFO);
            });

            $('#investmentsTab').on('click',function () {
                $('#investmentsContainer').show();
                $('#projectsContainer').hide();
            });


            $('.projectsVSInvestments-btn').on('click',function () {
                $('.projectsVSInvestments-btn').removeClass('active');
                $(this).addClass('active');
            });

            $('.catContainer').on('click',function () {
                $('.catContainer').removeClass('selected');
                $(this).addClass('selected');
                $('#projectsBlock').empty();
                projectFO.type = $(this).attr('name');
                loadAndAppendProjectBlocks(projectFO);
            });
        </script>
    </body>
</html>
