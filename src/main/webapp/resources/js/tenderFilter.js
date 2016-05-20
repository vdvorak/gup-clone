/**
 * Created by Юля on 19.05.2016.
 */
(function (namespace) {

    'use strict';
    var flag = window.flag || "",
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
        var url = '/tenders?filter=' + JSON.stringify(param) + '&status=' + $('#select-tender-status').val();
        return encodeURIComponent(url);
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
            dateBegin = $('#tender-datepicker3').datepicker( 'getDate'),
            dateEnd = $('#tender-datepicker4').datepicker( 'getDate'),
            param = {};

        param.tenderNumber = filterBlock.find('#tenderNumber').val();
        param.naceIds = ('#filterNACE').val();
        param.address.region = filterBlock.find('#region').val();
        param.address.city = filterBlock.find('#city').val();
        param.begin = (dateBegin) ? dateBegin.getTime() : null;
        param.end = (dateEnd) ? dateEnd.setHours(23,59,59,999) : null;

        validateParameters(param);
    }

    function parseURLParameters() {
        var searchUrl = decodeURIComponent(window.location.search),
            arrParam = url.substring(1, url.length - 1).split('&');

        for(var i = 0; i < arrParam.length; i++) {
            var p = arrParam[i],
                ind = p.indexOf('=');
            if(ind !== -1) param[p.substring(0, ind)] = p.substring(ind + 1, p.length - 1);
        }
    }

    function searchTenders(event) {
        event.preventDefault();

    }

    function validateParameters(obj) {
        for(var i = 0; i < obj.length; i++) {
            if(Object.prototype.toString.call(obj[i]) === '[object Object]') validateParameters(obj[i]);
            if(!obj[i]) delete obj[i];
        }
    }

    namespace.parametersURI = param;

})(window.tenderFilter = window.tenderFilter || {});
