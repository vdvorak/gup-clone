package ua.com.itproekt.gup.server.api.rest.profile;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


/**
 //        Key : +38(099) 444-4444 Value : false
 //        Key : +38(093) 431-1043 Value : false
 //        Key : +38(097) 007-2837 Value : false
 //        Key : +38(093) 932-5476 Value : false
 //
 //        Key : 0994444444 Value : false
 //        Key : 0934311043 Value : false
 //        Key : 0970072837 Value : false
 //        Key : 0939325476 Value : false

 //        phones.entrySet().stream()
 //            .forEach((k, v) -> System.err.println("Key : " + k + " Value : " + v));
 */

public class StorePhonesTest {

    public static void main(String[] args) {
        String phone = "+38(099) 444-4444";

        phone = phone.replaceAll("\\D+","");
        System.out.println(phone);

        int strStart=0, strLast=phone.length();
        if(0<(phone.length()-10)) strStart = phone.length()-10;
        phone = phone.substring(strStart,strLast);

        System.out.println(phone);

//        long num = Long.parseLong(phone);
//        System.out.println(num);

        System.out.println("////////////////////////////////////////////////////////////");

        Map<String, Boolean> phones = new HashMap<>();
        phones.put("+38(099) 444-4444",false);
        phones.put("+38(093) 431-1043",false);
        phones.put("+38(097) 007-2837",false);
        phones.put("+38(093) 932-5476",false);
        phones.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        System.out.println();

//        phones.entrySet().stream()
//                .filter(map -> "+38(097) 007-2837".equals(map.getKey()))
//                .collect(Collectors.toMap(p -> p.getKey(), p -> true))
//                .forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

//        Map<String, Boolean> newPhones = phones.entrySet().stream()
//                        .filter(map -> "+38(097) 007-2837".equals(map.getKey()))
//                        .collect(Collectors.toMap(p -> p.getKey(), p -> true));
//        System.out.println(newPhones);

        Map<String, Boolean> newPhones = phones.entrySet().stream()
                .map(e -> {
                    if (e.equals("+38(097) 007-2837")) e.setValue(true);
                    return e;
                })
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        System.out.println(newPhones);


    }

}
