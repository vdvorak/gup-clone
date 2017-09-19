package ua.com.itproekt.gup;

import com.google.gson.Gson;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ISODateTest {

    public static void main(String[] args) {
        Gson gson = new Gson();

        My my = new My();
        System.out.println( gson.toJson(my) );


        // ISODate("2017-06-06T10:17:20.726Z"),
        DateTime dt = new DateTime( "2017-06-06T10:17:20.726Z" ) ;
        System.out.println( dt );
        System.out.println( gson.toJson(dt) );
        System.out.println( dt.getMillis() );

        System.out.println();

        ZonedDateTime createdDate = ZonedDateTime.now();
        System.out.println( zonedDateTimeToDateTime(createdDate).getMillis() );





//        Admin admin = new Admin("5901b9f18218d6960747599d");
//
//        System.out.println( gson.toJson(admin) );
//        System.out.println( gson.toJson(admins) );

    }

    static DateTime zonedDateTimeToDateTime(final ZonedDateTime zdt) {
        return new DateTime(zdt.toInstant().toEpochMilli(), DateTimeZone.forID(zdt.getOffset().getId()));
    }

    static class My {
        private ZonedDateTime createdDate;
        private long createdDateLong;
        public My(){
            createdDate = ZonedDateTime.now();
            createdDateLong = createdDate.toInstant().toEpochMilli();
        }
        public ZonedDateTime getCreatedDate() {
            return createdDate;
        }
        public void setCreatedDate(ZonedDateTime createdDate) {
            this.createdDate = createdDate;
        }
    }

}
