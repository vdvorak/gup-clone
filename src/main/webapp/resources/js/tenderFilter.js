/**
 * Created by Юля on 19.05.2016.
 */
(function (namespace) {

    'use strict';
    var redirect = (window.location.pathname !== '/tenders'),
        param = {};

    init();

    function init() {
        initNace();

        $('#btn-tenders-search').click(searchTenders);
    }

    function initNace() {
        $.when(loadNace).done(function(response){
            var select = $('#filterNACE');
            for(var i = 0; i < response.length; i++) {
                var option = $('<option id="'+ response[i].id +'" value="'+ response[i].id +'">'+ response[i].id + ": " +response[i].name +'</option>');
                select.append(option);
            }
            $("#filterNACE").chosen({width:'720px', max_selected_options:5});
        });
    }

    function redirectToTenderList() {
        var url = getUrlWithParameters();
        $.get(url, function () {
            window.location.href = url;
        });
    }

    function getUrlWithParameters() {
        var url = '/tenders?';
        for(var key in param) {
            url += key + '=' + ((key === 'filter') ? JSON.stringify(param[key]) : param[key]) + '&';
        }
        url = url.substring(0, url.length - 1);
        return encodeURI(url);
    }

    function setTenderStatus() {
        if ($('#tabs1-tenders').hasClass('active')) {
            var tenderStatus = getUrlParam('status');
            if (tenderStatus) {
                $('#select-tender-status option[value="' + tenderStatus + '"]').prop("selected", true);
            } else {
                $('#select-tender-status option[value="all"]').prop("selected", true);
            }
        } else {
            $('#select-tender-status option[value="all"]').prop("selected", true);
        }
    }

    function getFilterParameters() {
        var filterBlock = $('.tenderFilter'),
            dateBegin = $('#datepicker3').datepicker( 'getDate'),
            dateEnd = $('#datepicker4').datepicker( 'getDate');

        param.filter = {};
        param.filter.tenderNumber = filterBlock.find('#tenderNumber').val();
        param.filter.naceIds = $('#filterNACE').val();
        param.filter.address = {
            region: $('#region').val(),
            city: $('#city').val()
        }
        param.filter.begin = (dateBegin) ? dateBegin.getTime() : null;
        param.filter.end = (dateEnd) ? dateEnd.setHours(23,59,59,999) : null;

        param.status = $('#select-tender-status').val();

        validateParameters(param);
    }

    function parseURLParameters() {
        var searchUrl = decodeURI(window.location.search),
            arrParam = searchUrl.substring(1, searchUrl.length).split('&');

        for(var i = 0; i < arrParam.length; i++) {
            var p = arrParam[i],
                ind = p.indexOf('=');
            if(ind !== -1) {
                var key = p.substring(0, ind);
                param[key] = (key === 'filter') ? JSON.parse(p.substring(ind + 1, p.length)) : p.substring(ind + 1, p.length);
            }
        }
    }

    function searchTenders(event) {
        event.preventDefault();

        $('.projectsVSInvestments-btn.projects a').click();

        getFilterParameters();
        if(redirect) redirectToTenderList();
    }

    function validateParameters(obj) {
        for(var i in obj) {
            var isObj = Object.prototype.toString.call(obj[i]) === '[object Object]';
            if(isObj) validateParameters(obj[i]);
            if(!obj[i] || (isObj && !Object.keys(obj[i]).length)) delete obj[i];
        }
    }

    namespace.parametersURI = param;
    namespace.parseURLParameters = parseURLParameters;

})(window.tenderFilter = window.tenderFilter || {});
