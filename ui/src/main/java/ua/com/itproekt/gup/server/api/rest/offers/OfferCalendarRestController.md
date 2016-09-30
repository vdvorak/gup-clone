
Владелец объявления создает календарь с ценой для тех кто будет арендовать:
------------------

* `offer/{offerId}/calendar` получить календарь с указанной ценной-аренды для конкретного объявления
> method = **GET**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /calendar](http://gup.com.ua/api/rest/offersService/offer/{offerId}/calendar)

* `offer/{offerId}/calendar` создать новый календарь с дефолтной-ценной-аренды для конкретного объявления (только для залогиненного как владелец)
> method = **POST**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /calendar](http://gup.com.ua/api/rest/offersService/offer/{offerId}/calendar)
> @RequestBody = **{"scheme1": { "prices": { "weekdays": "10000","weekends": "12000" },"days": ["22.06.2014","31.06.2015"] }}**
> @RequestBody = **{"scheme1": { "price": 13000,"days": ["22.06.2014","31.06.2015"] }}**

* `offer/{offerId}/calendar` отредактировать ценну-аренды в календаре для конкретного объявления (только для залогиненного как владелец)
> method = **PUT**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /calendar](http://gup.com.ua/api/rest/offersService/offer/{offerId}/calendar)
> @RequestBody = **{"scheme1": { "prices": { "weekdays": "20000","weekends": "24000" },"days": ["22.06.2014","31.06.2015"] }}**
> @RequestBody = **{"scheme1": { "price": 26000,"days": ["22.06.2014","31.06.2015"] }}**

* `offer/{offerId}/calendar` удалить календарь с ценной-аренды для конкретного объявления (только для залогиненного как владелец)
> method = **DELETE**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /calendar](http://gup.com.ua/api/rest/offersService/offer/{offerId}/calendar)
> @RequestBody = **{}**
> @RequestBody = **{"scheme": "scheme1"}**
> @RequestBody = **{ "days": ["22.06.2014","31.06.2015"] }**

