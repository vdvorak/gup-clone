/**
 * Created by Юля on 19.05.2016.
 */
(function (namespace) {

    'use strict';
    var redirect = (window.location.pathname !== '/tenders'),
        param = {};

    init();

    function init() {
        $('.filter-datepicker').datepicker({ dateFormat: 'dd.mm.yy' });

        initNace();
        initTenderNumberAutocomplete();

        if(redirect) $('#btn-tenders-search').click(searchTenders);
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

    function initTenderNumberAutocomplete() {
        $("#tenderNumber").autocomplete({
            source: function (request, response) {
                $.getJSON("/search/autocomplete/tender/number", {
                    term: request.term
                }, response);
            }
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

        param.filter.maxPrice = +$('#tenderMaxSum').val();
        param.filter.minPrice = +$('#tenderMinSum').val();

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

    function fillParametersOnPage() {
        var filterBlock = $('.tenderFilter');

        if (param.filter) {
            $.when(loadNace).done(function(response) {
                if(param.filter.naceIds) $('#filterNACE').val(param.filter.naceIds).trigger("chosen:updated");
            });

            filterBlock.find('#tenderNumber').val(param.filter.tenderNumber);

            if (param.filter.address) {
                $('#region').val(param.filter.address.region);
                $('#city').val(param.filter.address.city);
            }

            if(param.filter.begin) $('#datepicker3').datepicker("setDate", new Date(param.filter.begin));
            if(param.filter.end) $('#datepicker4').datepicker("setDate", new Date(param.filter.end));

            $('#tenderMaxSum').val(param.filter.maxPrice);
            $('#tenderMinSum').val(param.filter.minPrice);
        }
        if (param.status) $('#select-tender-status').val(param.status);
    }

    namespace.parametersURI = param;
    namespace.parseURLParameters = parseURLParameters;
    namespace.fillParametersOnPage = fillParametersOnPage;
    namespace.searchTenders = searchTenders;

})(window.tenderFilter = window.tenderFilter || {});