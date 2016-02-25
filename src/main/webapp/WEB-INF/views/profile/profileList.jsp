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
    <title>Список пользователей | GUP</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">

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


    <div class="container2" id="profileListContainer">
        <div>
            <p style="display: inline" class="profileListDescription">Колличество найденых профилей: </p>
            <p style="display: inline" class="profileListDescription" id="foundedProfilesNum">0</p>
        </div>
    </div>

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

    <script src="/resources/js/top-news-block.js"></script>
    <script src="/resources/js/top-offers-block.js"></script>
    <script src="/resources/js/top-tenders-block.js"></script>
    <script src="/resources/js/top-projects-block.js"></script>

    <script>
        var profileFO = {skip: 0, limit: 10};
        <c:if test="${profileFO != null}">
            profileFO = ${profileFO};
        </c:if>

        $("#selectedService option[value='profile']").attr("selected","selected");
        loadAndAppendProfileBlocks(profileFO);

        function loadAndAppendProfileBlocks(profileFO) {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "/api/rest/profilesService/profile/read/all",
                data: JSON.stringify(profileFO),
                statusCode: {
                    200: function (responseEntity) {
                        $('#foundedProfilesNum').text(responseEntity.totalEntities);

                        responseEntity.entities.forEach(function (profile) {
                            appendProfileBlock(profile);
                        });
                    }
                }
            });
        }

        function getUrlForProfilePic(picId) {
            if (picId != null && picId != '') {
                return "/api/rest/fileStorage/PROFILE/file/read/id/" + picId;
            } else {
                return "/resources/images/no_photo.jpg";
            }
        }

        function appendProfileBlock(profile) {
            $('#profileListContainer').append(
                    '<div class="profileList <%-- vip-color-border --%>">' +
                        '<div class="profileListLogo <%-- vip-color-border --%>" style="background: url(' + getUrlForProfilePic(profile.contact.pic) + ') no-repeat center center;"></div>' +
                        '<a href="/profile/id/' + profile.id + '" class="profileListName">' + profile.username + '</a>' +
                        '<p class="profileListDescription">' + profile.contact.aboutUs + '</p>' +
                    '</div>');
        }

        $(window).scroll(function () {
            if ($(window).scrollTop() == $(document).height() - $(window).height()) {
                profileFO.skip += profileFO.limit;
                loadAndAppendProfileBlocks(profileFO);
            }
        });
    </script>
</body>
</html>
