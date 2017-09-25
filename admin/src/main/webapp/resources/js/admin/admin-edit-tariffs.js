$(document).ready(function(){
    var data;
    var offerOptions = {};
    offerOptions.skip = 0;
    offerOptions.limit = 4;

    //var options = [1,3,6,12];

    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: "/swagger/rest/offersService/offer/read/all",
        data: JSON.stringify(offerOptions),

        success: function (response) {
            data = response.entities;

            data[0].month = 3;
            data[1].month = 3;
            data[2].month = 3;
            data[3].month = 3;
            data[0].gup_cost = 15;
            data[1].gup_cost = 15;
            data[2].gup_cost = 15;
            data[3].gup_cost = 15;
            data[0].olx_cost = 20;
            data[1].olx_cost = 20;
            data[2].olx_cost = 20;
            data[3].olx_cost = 20;
            data[0].description = "- Виділені оголошенння відразу помітні серед звичайних. - Спецільній [колір] фон на весь час розміщення - Виділене оголошення в результатах запиту та в рубриках";
            data[1].description = "- Виділені оголошенння відразу помітні серед звичайних. - Спецільній [колір] фон на весь час розміщення - Виділене оголошення в результатах запиту та в рубриках";
            data[2].description = "Розміщення оголошення над результатами запиту в окремому блоці. Кожен побачить ваше оголошення в результатах запиту. В 15 разів більше переглядів оголошення. В середньому в 5 разів більше відгуків";
            data[3].description = "Підняття оголошення на перше місце в списку в своїй категорії згідно оплати Ваше поідомлення будуть бачити в десятки більше людей Прекрасний спосіб збільшити кількість переглядів та відгуків. Одноразовое поднятие вверх списка. равноценно загрузке нового объявления - обновление даты";

            for (var i = 0; i < data.length; i++) {
                data[i].createdDate = new Date(parseInt(data[i].createdDate));
                data[i].createdDate = moment(data[i].createdDate).locale("ru").format('LLL');
                var newRowContent = "<tr>"
                    + "<td>"
                    + "<div>"
                    + "<a href=\"#\" class=\"slct" + i + "\">" + data[i].month + "</a>"
                    + "<ul class=\"drop" + i + "\">"
                    + "<li>1</li>"
                    + "<li>3</li>"
                    + "<li>6</li>"
                    + "<li>12</li>"
                    + "</ul>"
                    + "<input type=\"hidden\" />"
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<div>"
                    + "<input type=\"text\" id=\"text" + i + "\" name=\"text" + i + "\" value=\"" + data[i].gup_cost + "\" />" // data[i].userInfo.contactName
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<div>"
                    + "<input type=\"text\" id=\"email" + i + "\" name=\"email" + i + "\" value=\"" + data[i].olx_cost + "\" />" // data[i].userInfo.email
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<div>"
                    + "<textarea id=\"textarea\">" + data[i].description + "</textarea>" // data[i].createdDate
                    + "</div>"
                    + "</td>"
                    + "</tr>";
                $("#tariff tbody").append(newRowContent);
            }

//			$('.slct0').removeClass('active').html(1);
//			//$('.drop0').find('li').html(3);
//		    $('.drop0',this).each(function(i){ //$('ul li',this).each(function(i){
//				var $select = $(this).find('ul li'); //var $select = $(this).find('select');
//				alert( $select );
//				//if($select.length)
//				//   optionTexts[optionTexts.length-1].push($select.attr('name') => $select.val());
//			    //else {
//				//   count++;
//				//   optionTexts.push(new Array(count,$(this).text()));
//			    //}
//			});

            $('.slct0').click(function(){
                var dropBlock = $(this).parent().find('.drop0');
                if( dropBlock.is(':hidden') ) {
                    dropBlock.slideDown();
                    $(this).addClass('active');
                    $('.drop0').find('li').click(function(){
                        var selectResult = $(this).html();
                        $(this).parent().parent().find('input').val(selectResult);
                        $('.slct0').removeClass('active').html(selectResult);
                        dropBlock.slideUp();
                    });
                } else {
                    $(this).removeClass('active');
                    dropBlock.slideUp();
                }
                return false;
            });
            $('.slct1').click(function(){
                var dropBlock = $(this).parent().find('.drop1');
                if( dropBlock.is(':hidden') ) {
                    dropBlock.slideDown();
                    $(this).addClass('active');
                    $('.drop1').find('li').click(function(){
                        var selectResult = $(this).html();
                        $(this).parent().parent().find('input').val(selectResult);
                        $('.slct1').removeClass('active').html(selectResult);
                        dropBlock.slideUp();
                    });
                } else {
                    $(this).removeClass('active');
                    dropBlock.slideUp();
                }
                return false;
            });
            $('.slct2').click(function(){
                var dropBlock = $(this).parent().find('.drop2');
                if( dropBlock.is(':hidden') ) {
                    dropBlock.slideDown();
                    $(this).addClass('active');
                    $('.drop2').find('li').click(function(){
                        var selectResult = $(this).html();
                        $(this).parent().parent().find('input').val(selectResult);
                        $('.slct2').removeClass('active').html(selectResult);
                        dropBlock.slideUp();
                    });
                } else {
                    $(this).removeClass('active');
                    dropBlock.slideUp();
                }
                return false;
            });
            $('.slct3').click(function(){
                var dropBlock = $(this).parent().find('.drop3');
                if( dropBlock.is(':hidden') ) {
                    dropBlock.slideDown();
                    $(this).addClass('active');
                    $('.drop3').find('li').click(function(){
                        var selectResult = $(this).html();
                        $(this).parent().parent().find('input').val(selectResult);
                        $('.slct3').removeClass('active').html(selectResult);
                        dropBlock.slideUp();
                    });
                } else {
                    $(this).removeClass('active');
                    dropBlock.slideUp();
                }
                return false;
            });
            $('.slct4').click(function(){
                var dropBlock = $(this).parent().find('.drop4');
                if( dropBlock.is(':hidden') ) {
                    dropBlock.slideDown();
                    $(this).addClass('active');
                    $('.drop4').find('li').click(function(){
                        var selectResult = $(this).html();
                        $(this).parent().parent().find('input').val(selectResult);
                        $('.slct4').removeClass('active').html(selectResult);
                        dropBlock.slideUp();
                    });
                } else {
                    $(this).removeClass('active');
                    dropBlock.slideUp();
                }
                return false;
            });
        }
    });
});

/*
 {
 "totalEntities":35,
 "entities":[
 {"id":"573c56601be9a3701d4574b2","authorId":"572368bffb644cbdbcf3cc1c","userInfo":{"contactName":"jhbhjhjvhvh","email":"a@a.com","phoneNumbers":[],"skypeLogin":""},"moderationStatus":"COMPLETE","active":true,"createdDate":1463582864203,"reservation":null,"views":88,"rent":null,"seoUrl":"bjkbkjbkjbjb-19","seoKey":"19","categories":["36","544"],"seoCategory":"Ð¢Ð¾Ð²Ð°Ñ€Ñ‹ Ð´Ð»Ñ ÐºÐ¾Ñ€Ð¼Ð»ÐµÐ½Ð¸Ñ","properties":[{"key":"state","value":"new"},{"key":"price","value":"arranged"},{"key":"blue","value":"true"},{"key":"green","value":"true"}],"imagesIds":{},"videoUrl":"","title":"bjkbkjbkjbjb","description":"hghjghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","price":null,"currency":null,"priceCanBeNegotiated":null,"urgent":null,"used":null,"canBeReserved":false,"canBeRented":null,"address":{"coordinates":"","country":"Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","area":"Ð’ÑÑ Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","city":null,"district":null,"street":null},"moderationMessage":null},
 {"id":"573c56d91be9a3701d4574b3","authorId":"572368bffb644cbdbcf3cc1c","userInfo":{"contactName":"hvhjvjhb","email":"a@a.com","phoneNumbers":[],"skypeLogin":""},"moderationStatus":"COMPLETE","active":true,"createdDate":1463582985143,"reservation":null,"views":60,"rent":null,"seoUrl":"jjhjkhgkjhkhkhkjhjjk-1b","seoKey":"1b","categories":["3","225"],"seoCategory":"ÐÐ²Ñ‚Ð¾Ð±ÑƒÑÑ‹ ","properties":[{"key":"motor_manufacturer","value":"585"},{"key":"price","value":"arranged"}],"imagesIds":{},"videoUrl":"","title":"jjhjkhgkjhkhkhkjhjjk","description":"jhgghhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh","price":null,"currency":null,"priceCanBeNegotiated":null,"urgent":null,"used":null,"canBeReserved":false,"canBeRented":null,"address":{"coordinates":"","country":"Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","area":"Ð’ÑÑ Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","city":null,"district":null,"street":null},"moderationMessage":null},
 {"id":"573c57511be9a3701d4574b9","authorId":"572368bffb644cbdbcf3cc1c","userInfo":{"contactName":"lkjkjhjkhjh","email":"a@a.com","phoneNumbers":[],"skypeLogin":""},"moderationStatus":"COMPLETE","active":true,"createdDate":1463583105486,"reservation":null,"views":4,"rent":null,"seoUrl":"jhkjhjkhjhjk-1c","seoKey":"1c","categories":["36","538"],"seoCategory":"Ð”ÐµÑ‚ÑÐºÐ¸Ðµ Ð°Ð²Ñ‚Ð¾ÐºÑ€ÐµÑÐ»Ð°","properties":[{"key":"state","value":"new"},{"key":"price","value":"free"}],"imagesIds":{},"videoUrl":"","title":"jhkjhjkhjhjk","description":"llllllllkjgkjgjkgkjgjkgjkgkjgkjgjkgjknnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn","price":null,"currency":null,"priceCanBeNegotiated":null,"urgent":null,"used":null,"canBeReserved":false,"canBeRented":null,"address":{"coordinates":"","country":"Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","area":"Ð’ÑÑ Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","city":null,"district":null,"street":null},"moderationMessage":null},
 {"id":"573c50431be94d5140dcee03","authorId":"572368bffb644cbdbcf3cc1c","userInfo":{"contactName":"jfbjfsb","email":"a@a.com","phoneNumbers":[],"skypeLogin":""},"moderationStatus":"COMPLETE","active":true,"createdDate":1463581299387,"reservation":null,"views":3,"rent":null,"seoUrl":"hvhvhjvhjvhjhjv-11","seoKey":"11","categories":["36","539","70"],"seoCategory":"ÐžÐ´ÐµÐ¶Ð´Ð° Ð´Ð»Ñ Ð´ÐµÐ²Ð¾Ñ‡ÐµÐº","properties":[{"key":"state","value":"new"},{"key":"size","value":""},{"key":"price","value":"exchange"}],"imagesIds":{},"videoUrl":"","title":"hvhvhjvhjvhjhjv","description":"jbjkbjkbjkbjbjkbjkbjkbjbjkbkbjkbjkbbkbkjbkjbkjbkjbjkbbkjbjbkjbjkb","price":null,"currency":"UAH","priceCanBeNegotiated":null,"urgent":null,"used":null,"canBeReserved":false,"canBeRented":null,"address":{"coordinates":"","country":"Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","area":"Ð’ÑÑ Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","city":null,"district":null,"street":null},"moderationMessage":null},
 {"id":"573c50e91be94d5140dcee0a","authorId":"572368bffb644cbdbcf3cc1c","userInfo":{"contactName":"jsngjsngjs","email":"a@a.com","phoneNumbers":[],"skypeLogin":""},"moderationStatus":"COMPLETE","active":true,"createdDate":1463581465280,"reservation":null,"views":54,"rent":null,"seoUrl":"jngjkernger-12","seoKey":"12","categories":["36","539","70"],"seoCategory":"ÐžÐ´ÐµÐ¶Ð´Ð° Ð´Ð»Ñ Ð´ÐµÐ²Ð¾Ñ‡ÐµÐº","properties":[{"key":"state","value":"new"},{"key":"size","value":""},{"key":"price","value":"arranged"}],"imagesIds":{},"videoUrl":"","title":"jngjkernger","description":"ndkjgngsdkjgnsgnjfdkghnjdfkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj","price":null,"currency":"UAH","priceCanBeNegotiated":null,"urgent":null,"used":null,"canBeReserved":false,"canBeRented":null,"address":{"coordinates":"","country":"Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","area":"Ð’ÑÑ Ð£ÐºÑ€Ð°Ð¸Ð½Ð°","city":null,"district":null,"street":null},"moderationMessage":null}
 ]
 }
 */
