

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////// admin-tenant-rents ///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function formattedDate(date) {
    var d = new Date(date || Date.now()),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function parseJsonWeekday(monthOfPrices) {
    var gupEvents2 = [];

    var   START_DATE = monthOfPrices.weekday.days[0].split(".");
    var     END_DATE = monthOfPrices.weekday.days[1].split(".");
    var F_START_DATE = new Date(START_DATE[2], START_DATE[1] - 1, START_DATE[0]);
    var   F_END_DATE = new Date(END_DATE[2], END_DATE[1] - 1, END_DATE[0]);

    var D_START = new Date(F_START_DATE);
    var   D_END = new Date(F_END_DATE);

    var   WEEKDAY = -1;
    var isWEEKDAY = true;
    while(D_START.getDate() < D_END.getDate()){
        if( D_START.getDay()%6 && D_START.getDay()%7 ){
            if( isWEEKDAY ){
                WEEKDAY++;
//				            console.log( '(1) ' + D_START ) //...
                var tmpDate2 = D_START.setDate(D_START.getDate());
                TMP_START2 = new Date(tmpDate2);
                gupEvents2[WEEKDAY] = { title:monthOfPrices.weekday.price, start:formattedDate(TMP_START2), color:'#2980b9' };
                isWEEKDAY = false;
            }
        } else {
            if( !isWEEKDAY ){
                var tmpDate = D_START.setDate(D_START.getDate() - 1);
                TMP_START = new Date(tmpDate);
//				            console.log( '(2) ' + TMP_START ) //...
//				            console.log( ) //...
                //gupEvents2[WEEKDAY].end = formattedDate(TMP_START); //...
                tmpDate2 = TMP_START.setDate(TMP_START.getDate()+1); //...
                TMP_START2 = new Date(tmpDate2); //...
                gupEvents2[WEEKDAY].end = formattedDate(TMP_START2); //...

                isWEEKDAY = true;
            }
        }
        var newDate = D_START.setDate(D_START.getDate() + 1);
        D_START = new Date(newDate);
    }

    if( !isWEEKDAY ){
        if( D_START.getDay()%6 && D_START.getDay()%7 ){
            tmpDate = D_START.setDate(D_START.getDate());
            TMP_START = new Date(tmpDate);
//				        console.log( '(3) ' + TMP_START ) //...
            //gupEvents2[WEEKDAY].end = formattedDate(TMP_START); //...
            tmpDate2 = TMP_START.setDate(TMP_START.getDate()+1); //...
            TMP_START2 = new Date(tmpDate2); //...
            gupEvents2[WEEKDAY].end = formattedDate(TMP_START2); //...
        } else {
            tmpDate = D_START.setDate(D_START.getDate() - 1);
            TMP_START = new Date(tmpDate);
//				        console.log( '(4) ' + TMP_START ) //...
            //gupEvents2[WEEKDAY].end = formattedDate(TMP_START); //...
            tmpDate2 = TMP_START.setDate(TMP_START.getDate()+1); //...
            TMP_START2 = new Date(tmpDate2); //...
            gupEvents2[WEEKDAY].end = formattedDate(TMP_START2); //...
        }
    }
    if( isWEEKDAY ){
        if( D_START.getDay()%6 && D_START.getDay()%7 ){
            WEEKDAY++;
            tmpDate = D_START.setDate(D_START.getDate());
            TMP_START = new Date(tmpDate);
//				        console.log( '(5) ' + TMP_START ) //...
            gupEvents2[WEEKDAY] = { title:monthOfPrices.weekday.price, start:formattedDate(TMP_START), color:'#2980b9' };
        }
    }

//				//console.log( 'WEEKDAY = ' + WEEKDAY )
//				console.log( Object.keys(gupEvents2).length )
//				console.log( gupEvents2 )
    return gupEvents2
}

function parseJsonWeekend(monthOfPrices) {
    var gupEvents2 = [];

    var   START_DATE = monthOfPrices.weekend.days[0].split(".");
    var     END_DATE = monthOfPrices.weekend.days[1].split(".");
    var F_START_DATE = new Date(START_DATE[2], START_DATE[1] - 1, START_DATE[0]);
    var   F_END_DATE = new Date(END_DATE[2], END_DATE[1] - 1, END_DATE[0]);

    var D_START = new Date(F_START_DATE);
    var   D_END = new Date(F_END_DATE);

    var   WEEKEND = -1;
    var isWEEKEND = true;
    while(D_START.getDate() < D_END.getDate()){
        if( !(D_START.getDay()%6 && D_START.getDay()%7) ){
            if( isWEEKEND ){
                WEEKEND++;
//				        	console.log( '(1) ' + D_START ) //...
                var tmpDate2 = D_START.setDate(D_START.getDate());
                TMP_START2 = new Date(tmpDate2);
                gupEvents2[WEEKEND] = { title:monthOfPrices.weekend.price, start:formattedDate(TMP_START2), color:'#2980b9' };
                isWEEKEND = false;
            }
        } else {
            if( !isWEEKEND ){
                var tmpDate = D_START.setDate(D_START.getDate());
                TMP_START = new Date(tmpDate);
//				            console.log( '(2) ' + TMP_START ) //...
//				            console.log( ) //...
                gupEvents2[WEEKEND].end = formattedDate(TMP_START);
                isWEEKEND = true;
            }
        }
        var newDate = D_START.setDate(D_START.getDate() + 1);
        D_START = new Date(newDate);
    }

    if( !isWEEKEND ){
        if( !(D_START.getDay()%6 && D_START.getDay()%7) ){
            tmpDate = D_START.setDate(D_START.getDate() + 1);
            TMP_START = new Date(tmpDate);
//				    	console.log( '(3) ' + TMP_START ) //...
            gupEvents2[WEEKEND].end = formattedDate(TMP_START);
        } else {
            tmpDate = D_START.setDate(D_START.getDate());
            TMP_START = new Date(tmpDate);
//				     	console.log( '(4) ' + TMP_START ) //...
            gupEvents2[WEEKEND].end = formattedDate(TMP_START);
        }
    }
    if( isWEEKEND ){
        if( !(D_START.getDay()%6 && D_START.getDay()%7) ){
            WEEKEND++;
            tmpDate = D_START.setDate(D_START.getDate() + 1);
            TMP_START = new Date(tmpDate);
//				    	console.log( '(5) ' + TMP_START ) //...
            gupEvents2[WEEKEND] = { title:monthOfPrices.weekend.price, start:formattedDate(TMP_START), color:'#2980b9' };
        }
    }

//				//console.log( 'WEEKEND = ' + WEEKEND )
//				console.log( Object.keys(gupEvents2).length )
//				console.log( gupEvents2 )
    return gupEvents2
}

function parseJsonSpecialdays(monthOfPrices) {
    var gupEvents2 = [];

    var SPECIALDAY = -1;
    while(SPECIALDAY < (Object.keys(monthOfPrices.specialdays).length - 1)){
        SPECIALDAY++;

        var   START_DATE = monthOfPrices.specialdays[SPECIALDAY].days[0].split(".");
        var F_START_DATE = new Date(START_DATE[2], START_DATE[1] - 1, START_DATE[0]);

        var      D_START = new Date(F_START_DATE);

//	  			  console.log( '(1) ' + D_START ) //...
        var tmpDate2 = D_START.setDate(D_START.getDate());
        TMP_START2 = new Date(tmpDate2);
        gupEvents2[SPECIALDAY] = { title:monthOfPrices.specialdays[SPECIALDAY].price, start:formattedDate(TMP_START2), color:'#2980b9' };

        var newDate = D_START.setDate(D_START.getDate() + 1);
        D_START = new Date(newDate);
    }

    return gupEvents2
}

function parseJsonExpired(rents) {
    var gupEvents2 = [];

    var EXPIRED = -1;
    while(EXPIRED < (Object.keys(rents.expired).length - 1)){
        EXPIRED++;
        //console.log( formattedDate(rents.expired[EXPIRED].day) )
        gupEvents2[EXPIRED] = { start:formattedDate(rents.expired[EXPIRED].day), overlap:false, rendering:'background', color:'#333333' };
    }

    return gupEvents2
}

function parseJsonRented(rents) {
    var gupEvents2 = [];

    var RENTED = -1;
    while(RENTED < (Object.keys(rents.rented).length - 1)){
        RENTED++;
        //console.log( formattedDate(rents.rented[RENTED].day) )
        gupEvents2[RENTED] = { start:formattedDate(rents.rented[RENTED].day), overlap:false, rendering:'background', color:'#ff9f89' };
    }

    return gupEvents2
}

function parseJsonRentedName(rents) {
    var gupEvents2 = [];

    var RENTED = -1;
    while(RENTED < (Object.keys(rents.rented).length - 1)){
        RENTED++;
        //console.log( rents.rented[RENTED].user.fullName )
        gupEvents2[RENTED] = { title:rents.rented[RENTED].user.fullName, start:formattedDate(rents.rented[RENTED].day), color:'#2980b9' };
    }

    return gupEvents2
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////// admin-landlord-rents //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function formattedDate(date) {
    var d = new Date(date || Date.now()),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function parseJsonWeekday(monthOfPrices) {
    var gupEvents2 = [];

    var   START_DATE = monthOfPrices.weekday.days[0].split(".");
    var     END_DATE = monthOfPrices.weekday.days[1].split(".");
    var F_START_DATE = new Date(START_DATE[2], START_DATE[1] - 1, START_DATE[0]);
    var   F_END_DATE = new Date(END_DATE[2], END_DATE[1] - 1, END_DATE[0]);

    var D_START = new Date(F_START_DATE);
    var   D_END = new Date(F_END_DATE);

    var   WEEKDAY = -1;
    var isWEEKDAY = true;
    while(D_START.getDate() < D_END.getDate()){
        if( D_START.getDay()%6 && D_START.getDay()%7 ){
            if( isWEEKDAY ){
                WEEKDAY++;
//				            console.log( '(1) ' + D_START ) //...
                var tmpDate2 = D_START.setDate(D_START.getDate());
                TMP_START2 = new Date(tmpDate2);
                gupEvents2[WEEKDAY] = { title:monthOfPrices.weekday.price, start:formattedDate(TMP_START2), color:'#2980b9' };
                isWEEKDAY = false;
            }
        } else {
            if( !isWEEKDAY ){
                var tmpDate = D_START.setDate(D_START.getDate() - 1);
                TMP_START = new Date(tmpDate);
//				            console.log( '(2) ' + TMP_START ) //...
//				            console.log( ) //...
                //gupEvents2[WEEKDAY].end = formattedDate(TMP_START); //...
                tmpDate2 = TMP_START.setDate(TMP_START.getDate()+1); //...
                TMP_START2 = new Date(tmpDate2); //...
                gupEvents2[WEEKDAY].end = formattedDate(TMP_START2); //...

                isWEEKDAY = true;
            }
        }
        var newDate = D_START.setDate(D_START.getDate() + 1);
        D_START = new Date(newDate);
    }

    if( !isWEEKDAY ){
        if( D_START.getDay()%6 && D_START.getDay()%7 ){
            tmpDate = D_START.setDate(D_START.getDate());
            TMP_START = new Date(tmpDate);
//				        console.log( '(3) ' + TMP_START ) //...
            //gupEvents2[WEEKDAY].end = formattedDate(TMP_START); //...
            tmpDate2 = TMP_START.setDate(TMP_START.getDate()+1); //...
            TMP_START2 = new Date(tmpDate2); //...
            gupEvents2[WEEKDAY].end = formattedDate(TMP_START2); //...
        } else {
            tmpDate = D_START.setDate(D_START.getDate() - 1);
            TMP_START = new Date(tmpDate);
//				        console.log( '(4) ' + TMP_START ) //...
            //gupEvents2[WEEKDAY].end = formattedDate(TMP_START); //...
            tmpDate2 = TMP_START.setDate(TMP_START.getDate()+1); //...
            TMP_START2 = new Date(tmpDate2); //...
            gupEvents2[WEEKDAY].end = formattedDate(TMP_START2); //...
        }
    }
    if( isWEEKDAY ){
        if( D_START.getDay()%6 && D_START.getDay()%7 ){
            WEEKDAY++;
            tmpDate = D_START.setDate(D_START.getDate());
            TMP_START = new Date(tmpDate);
//				        console.log( '(5) ' + TMP_START ) //...
            gupEvents2[WEEKDAY] = { title:monthOfPrices.weekday.price, start:formattedDate(TMP_START), color:'#2980b9' };
        }
    }

//				//console.log( 'WEEKDAY = ' + WEEKDAY )
//				console.log( Object.keys(gupEvents2).length )
//				console.log( gupEvents2 )
    return gupEvents2
}

function parseJsonWeekend(monthOfPrices) {
    var gupEvents2 = [];

    var   START_DATE = monthOfPrices.weekend.days[0].split(".");
    var     END_DATE = monthOfPrices.weekend.days[1].split(".");
    var F_START_DATE = new Date(START_DATE[2], START_DATE[1] - 1, START_DATE[0]);
    var   F_END_DATE = new Date(END_DATE[2], END_DATE[1] - 1, END_DATE[0]);

    var D_START = new Date(F_START_DATE);
    var   D_END = new Date(F_END_DATE);

    var   WEEKEND = -1;
    var isWEEKEND = true;
    while(D_START.getDate() < D_END.getDate()){
        if( !(D_START.getDay()%6 && D_START.getDay()%7) ){
            if( isWEEKEND ){
                WEEKEND++;
//				        	console.log( '(1) ' + D_START ) //...
                var tmpDate2 = D_START.setDate(D_START.getDate());
                TMP_START2 = new Date(tmpDate2);
                gupEvents2[WEEKEND] = { title:monthOfPrices.weekend.price, start:formattedDate(TMP_START2), color:'#2980b9' };
                isWEEKEND = false;
            }
        } else {
            if( !isWEEKEND ){
                var tmpDate = D_START.setDate(D_START.getDate());
                TMP_START = new Date(tmpDate);
//				            console.log( '(2) ' + TMP_START ) //...
//				            console.log( ) //...
                gupEvents2[WEEKEND].end = formattedDate(TMP_START);
                isWEEKEND = true;
            }
        }
        var newDate = D_START.setDate(D_START.getDate() + 1);
        D_START = new Date(newDate);
    }

    if( !isWEEKEND ){
        if( !(D_START.getDay()%6 && D_START.getDay()%7) ){
            tmpDate = D_START.setDate(D_START.getDate() + 1);
            TMP_START = new Date(tmpDate);
//				    	console.log( '(3) ' + TMP_START ) //...
            gupEvents2[WEEKEND].end = formattedDate(TMP_START);
        } else {
            tmpDate = D_START.setDate(D_START.getDate());
            TMP_START = new Date(tmpDate);
//				     	console.log( '(4) ' + TMP_START ) //...
            gupEvents2[WEEKEND].end = formattedDate(TMP_START);
        }
    }
    if( isWEEKEND ){
        if( !(D_START.getDay()%6 && D_START.getDay()%7) ){
            WEEKEND++;
            tmpDate = D_START.setDate(D_START.getDate() + 1);
            TMP_START = new Date(tmpDate);
//				    	console.log( '(5) ' + TMP_START ) //...
            gupEvents2[WEEKEND] = { title:monthOfPrices.weekend.price, start:formattedDate(TMP_START), color:'#2980b9' };
        }
    }

//				//console.log( 'WEEKEND = ' + WEEKEND )
//				console.log( Object.keys(gupEvents2).length )
//				console.log( gupEvents2 )
    return gupEvents2
}

function parseJsonSpecialdays(monthOfPrices) {
    var gupEvents2 = [];

    var SPECIALDAY = -1;
    while(SPECIALDAY < (Object.keys(monthOfPrices.specialdays).length - 1)){
        SPECIALDAY++;

        var   START_DATE = monthOfPrices.specialdays[SPECIALDAY].days[0].split(".");
        var F_START_DATE = new Date(START_DATE[2], START_DATE[1] - 1, START_DATE[0]);

        var      D_START = new Date(F_START_DATE);

//	  			  console.log( '(1) ' + D_START ) //...
        var tmpDate2 = D_START.setDate(D_START.getDate());
        TMP_START2 = new Date(tmpDate2);
        gupEvents2[SPECIALDAY] = { title:monthOfPrices.specialdays[SPECIALDAY].price, start:formattedDate(TMP_START2), color:'#2980b9' };

        var newDate = D_START.setDate(D_START.getDate() + 1);
        D_START = new Date(newDate);
    }

    return gupEvents2
}

//console.log( parseJsonWeekday(monthOfPrices2) )
//console.log( parseJsonWeekend(monthOfPrices2) )
//console.log( parseJsonSpecialdays(monthOfPrices2) )

var isEventOverDiv = function(x, y) {
    var external_events = $( '#external-events' );
    var offset = external_events.offset();
    offset.right = external_events.width() + offset.left;
    offset.bottom = external_events.height() + offset.top;

    // Compare
    if (x >= offset.left
        && y >= offset.top
        && x <= offset.right
        && y <= offset .bottom) { return true; }
    return false;
}
