
Арендодатели делают заказ на аренду объявления:
------------------

* `offer/{offerId}/rent` получить календарь со всеми датами-аренды для конкретного объявления
> method = **GET**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /rent](http://gup.com.ua/api/rest/offersService/offer/{offerId}/rent)

* `offer/{offerId}/rent/{rentId}` получить публичную-информацию о заказе аренды
> method = **GET**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /rent/ **rentId**](http://gup.com.ua/api/rest/offersService/offer/{offerId}/rent/{rentId})

* `offer/{offerId}/rent/{rentId}` получить персональную-информацию о заказе аренды (только для залогиненного как владелец)
> method = **GET**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /rent/ **rentId**](http://gup.com.ua/api/rest/offersService/offer/{offerId}/rent/{rentId})

* `offer/{offerId}/rent` создать заказ на аренду конкретного объявления (с предоплатой)
> method = **POST**
> value =  [gup.com.ua/api/rest/offersService/offer/ **offerId** /rent](http://gup.com.ua/api/rest/offersService/offer/{offerId}/rent)
> @RequestBody = **{ "days": ["22.06.2014","31.06.2015"] }**
> @RequestBody = **{ "prepayment": 3000,"days": ["22.06.2014","31.06.2015"] }**

* `offer/{offerId}/rent/{rentId}` отменить заказ на аренду конкретного объявления (только для залогиненного либо как владелец либо как арендодатель)
> method = **DELETE**
