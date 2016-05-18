/**
 * Created by Юля on 18.05.2016.
 */
'use strict'

//----------------------------------------------------- Load resources -----------------------------------------------
var loadDoer = loadDoerInfo();

function loadDoerInfo() {
    return $.ajax({
        type: "POST",
        url: "/api/rest/doerService/doer/read/id/" + doerId,
        statusCode: {
            200: function (doer) {
                drawDoerInfo(doer);
            }
        }
    });
}

function drawDoerInfo(doer) {
    $('.date-create').text(localDateTime(doer.dateOfCreate));
}

//----------------------------------------------------- Load resources -----------------------------------------------



