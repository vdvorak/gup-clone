/**
 * Created by Юля on 19.05.2016.
 */
(function (namespace) {

    'use strict';
    var flag = window.flag || "",
        param = {};

    init();

    function init() {

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
            url += key + '=' + param[key] + '&';
        }
        url = url.substring(0, url.length - 1);
        return url;
    }


})(window.tenders = window.tenders || {});
