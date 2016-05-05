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

    <link rel="shortcut icon" href="/resources/images/favicon.ico" />

    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/bootstrap-theme.css">
    <link rel="stylesheet" href="/resources/css/jquery.bxslider.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.css">
    <link rel="stylesheet" href="/resources/css/media-queries.css">
    <link rel="stylesheet" href="/resources/css/offer-filter-region.css">

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

<div id="adialog"></div>
<button onclick="openDialog();">Open dialog</button>
<div id="dialogs"></div>
<button onclick="ClearCookie();">ClearCookie</button>

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

<jsp:include page="/WEB-INF/templates/libraries-template.jsp"/>

<jsp:include page="/WEB-INF/templates/header-js-template.jsp"/>

<jsp:include page="/WEB-INF/templates/custom-js-template.jsp"/>

<script>
    var profileFO = {skip: 0, limit: 10};
    <c:if test="${profileFO != null}">
    profileFO = ${profileFO};
    </c:if>

    $("#selectedService option[value='profile']").attr("selected", "selected");
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
                '<div class="profileListLogo <%-- vip-color-border --%>" style="background: url(' + getUrlForProfilePic(profile.imgId) + ') no-repeat center center; background-size: cover;"></div>' +
                '<a href="/profile?id=' + profile.id + '" class="profileListName">' +
                (profile.username?profile.username:'Без имени') +
                '</a>' +
                '<button style="display: inline-block;" onclick="openDialog(\'' + profile.id + '\');">Open dialog</button>' +
                '<p class="profileListDescription">' +
                (profile.contact.aboutUs?profile.contact.aboutUs:'Нет описания') +
                '</p></div>');
    }

    $(window).scroll(function () {
        if ($(window).scrollTop() == $(document).height() - $(window).height()) {
            profileFO.skip += profileFO.limit;
            loadAndAppendProfileBlocks(profileFO);
        }
    });
</script>
</body>
<style>
    #adialog {
        position: fixed;
        left: 0;
        top: 0;
        width: 100%;
        height: 0;
        font-size: 0;
    }
    #adialog .dialog {
        position: fixed;
        font-size: 12px;
        border: 1px solid blue;
        display: inline-block;
        width: 200px;
        background-color: white;
        min-height: 300px;
    }
    #adialog .dialog .title {
        background-color: blue;
        cursor: move;
        padding: 10px 5px;
        color: white;
    }
    #adialog .dialog.selected .title {
        background-color: red;
    }
    #adialog .dialog .close {
        position: absolute;
        right: 0;
        top: 0;
        padding: 5px;
        font-size: 15px;
        cursor: pointer;
    }
    #adialog .dialog .message {
        background-color: yellow;
        cursor: pointer;
    }
</style>
<script id="adialogTemplate" type="text/html">
    <div class="dialog">
        <div class="title">Title</div>
        <div class="close">x</div>
        <div class="message">Hello</div>
    </div>
</script>
<script>
    function ClearCookie(){
        Cookies.set('adialogs', '[]')
    }

    /*function guid() {
        function s4() {
            return Math.floor((1 + Math.random()) * 0x10000)
                    .toString(16)
                    .substring(1);
        }
        return s4() + s4() + '-' + s4() + '-' + s4() + '-' +
                s4() + '-' + s4() + s4() + s4();
    }*/

    $( "#adialog .dialog" ).draggable({
        snap: "#adialog .dialog, html",
        handle: ".title",
        snapMode: "outer"
    })

    var ADialog = (function () {
        function ADialog(options) {
            if (ADialog.dialogs.length > 0 && !ADialog.dialogs[0].dragged){
                ADialog.dialogs[0].remove()
            }
            ADialog.dialogs.push(this)
            this.dragged = false
            var self = this
            this.handle = ADialog.dialogTemplate.clone()
            this.applyData(options)
            $('#adialog').append(this.handle)
            this.handle.draggable({
                snap: "#adialog .dialog, html",
                handle: ".title",
                start: function(){
                    self.select()
                },
                stop: function(){
                    self.dragged = true
                    ADialog.save()
                }
            })
            this.handle.click(function(){
                self.select()
                ADialog.save()
            })
            this.handle.find('.close').click(function(){
                self.remove()
            })
        }
        return ADialog
    })()
    ADialog.prototype.setId = function (profileId) {
        this.id = profileId
    }
    ADialog.prototype.select = function () {
        if (ADialog.dialogs[0] == this) return;
        ADialog.dialogs[0].deselect()
        this._select()
        var index = ADialog.dialogs.indexOf(this)
        if (index != -1) ADialog.dialogs.splice(index, 1)
        ADialog.dialogs.unshift(this)

        ADialog.update()
    }
    ADialog.prototype._select = function(){
        this.handle.addClass('selected')
    }
    ADialog.prototype.deselect = function () {
        this.handle.removeClass('selected')
    }
    ADialog.prototype.remove = function(){
        var index = ADialog.dialogs.indexOf(this)
        if (index != -1) ADialog.dialogs.splice(index, 1)

        this.handle.remove()
        ADialog.save()
        ADialog.update()
    }
    ADialog.prototype.serialize = function(){
        var pos = this.handle.position()
        return {
            left: pos.left,
            top: pos.top,
            id: this.id,
            dragged: this.dragged
        }
    }
    ADialog.prototype.apply = function (data) {
        this.handle.css({
            'z-index': data.zIndex
        })
        if (ADialog.dialogs[0] == this){
            this._select()
        }
        this.updateView()
    }
    ADialog.prototype.applyData = function (data) {
        $.extend(this, data)
        this.handle.css({
            top: this.top, left: this.left
        })
        this.updateView()
    }
    ADialog.prototype.updateView = function () {
        this.handle.find('.title').text(this.id)
    }
    ADialog.dialogTemplate = $($("#adialogTemplate").html())
    ADialog.dialogs = []
    ADialog.init = function(options){
        var opts = {}
        $.extend(opts, options)
        var data = JSON.parse(Cookies.get('adialogs'))
        data.forEach(function(e){
            new ADialog(e)
        })
        ADialog.update()

        $(window).focus(ADialog.load)

        $(window).on('adialogs_update', function(){
            alert('LOOOL')
        });
    }
    ADialog.recieveUpdate = function(ev){
        alert('lol')
        if (ev.originalEvent.key != 'adialogs_update') return;
        var message = JSON.parse(ev.originalEvent.newValue)
//            if (!message) return;
        ADialog.update()
    }
    ADialog.save = function(){
        var data = ADialog.dialogs.map(function(e){
            return e.serialize()
        })
        Cookies.set('adialogs', JSON.stringify(data))

        localStorage.setItem('adialogs_update', 'lol')
        localStorage.removeItem('adialogs_update')
    }
    ADialog.load = function(){
        if (ADialog.dialogs.length > 0){
            ADialog.dialogs[0].deselect()
        }
        var data = JSON.parse(Cookies.get('adialogs'))

        var newArray = []
        data.forEach(function(dt){
            var dg = ADialog.getDialogById(dt.id)
            if (dg){
                newArray.push(dg)
                dg.applyData(dt)
            }
            else {
                if (!ADialog.dialogs.some(function(e){
                            e.id === dt.id
                        })){
                    newArray.push(new ADialog(dt))
                }
            }
        })

        for (var i = 0; i < ADialog.dialogs.length; i++){
            if (!data.some(function(e){
                        return e.id === ADialog.dialogs[i].id
                    })){
                ADialog.dialogs[i].remove()
                i--;
            }
        }

        ADialog.dialogs = newArray

        ADialog.update()
    }
    ADialog.getDialogById = function(id){
        for (var i in ADialog.dialogs){
            if (ADialog.dialogs[i].id === id){
                return ADialog.dialogs[i]
            }
        }
    }
    ADialog.update = function(){
        $('#dialogs').text(JSON.stringify(ADialog.dialogs.map(function(e){
            return e.serialize()
        })))
        ADialog.dialogs.forEach(function(e, index){
            e.apply({
                zIndex: ADialog.dialogs.length - index
            })
        })
    }

    function openDialog(profileId){
        var d = new ADialog(profileId)
        d.setId(profileId)
        d.select()
        ADialog.save()
        ADialog.update()
    }

    ADialog.init()
</script>
</html>
