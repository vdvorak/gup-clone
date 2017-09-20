package ua.com.gup.server.api.rest.profile;

import com.google.gson.Gson;
import ua.com.gup.model.profiles.phone.PhoneSynhronize;
import ua.com.gup.model.profiles.phone.StorePhones;

import java.util.*;
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
        /////////////////////////////////////////////////////////////////
        Long      searchPhone = 380994444444l;

        List<Long> mainPhones = new ArrayList<>();
        mainPhones.add(380994444444l);

//        Map<Long, Boolean> phones = new HashMap<>();
//        phones.put(380994444444l,false);
//        phones.put(380934311043l,false);
//        phones.put(380970072837l,false);
//        phones.put(380939325476l,false);
//
//        test3(phones,searchPhone);

        /////////////////////////////////////////////////////////////////
        List<PhoneSynhronize> contactPhones = new LinkedList<>();
        contactPhones.add(new PhoneSynhronize(380994444444l,false));
        contactPhones.add(new PhoneSynhronize(380934311043l,false));
        contactPhones.add(new PhoneSynhronize(380970072837l,false));
        contactPhones.add(new PhoneSynhronize(380939325476l,false));

        test4(mainPhones,contactPhones,searchPhone);

        /////////////////////////////////////////////////////////////////
        String storePhones = "{\"mainPhones\":[380994444444],\"contactPhones\":[{\"numberPhone\":380994444444,\"isFound\":false},{\"numberPhone\":380934311043,\"isFound\":false},{\"numberPhone\":380970072837,\"isFound\":false},{\"numberPhone\":380939325476,\"isFound\":false}]}";
        test5(storePhones,searchPhone);

        /////////////////////////////////////////////////////////////////
        String      userId = "58efaf104c8e83648c2f1e1e";
        Gson gson = new Gson();
        StorePhones oStorePhones = gson.fromJson(storePhones, StorePhones.class);
        oStorePhones.setIdUser(userId);
        test6(oStorePhones, searchPhone);
    }

//    public static void test1(){
//        String phone = "+38(099) 444-4444";
//
//        phone = phone.replaceAll("\\D+","");
//        System.out.println(phone);
//
//        int strStart=0, strLast=phone.length();
//        if(0<(phone.length()-10)) strStart = phone.length()-10;
//        phone = phone.substring(strStart,strLast);
//
//        System.out.println(phone);
//
////        long num = Long.parseLong(phone);
////        System.out.println(num);
//
//        System.out.println("////////////////////////////////////////////////////////////");
//
//        Map<String, Boolean> phones = new HashMap<String, Boolean>();
//        phones.put("+38(099) 444-4444",false);
//        phones.put("+38(093) 431-1043",false);
//        phones.put("+38(097) 007-2837",false);
//        phones.put("+38(093) 932-5476",false);
//        phones.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
//
//        System.out.println();
//
//        ////////////////////////////////////////////////////
////        phones.entrySet().stream()
////                .filter(map -> "+38(097) 007-2837".equals(map.getKey()))
////                .collect(Collectors.toMap(p -> p.getKey(), p -> true))
////                .forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
//
////        Map<String, Boolean> newPhones = phones.entrySet().stream()
////                        .filter(map -> "+38(097) 007-2837".equals(map.getKey()))
////                        .collect(Collectors.toMap(p -> p.getKey(), p -> true));
////        System.out.println(newPhones);
//
//
//        ////////////////////////////////////////////////////
//        Map<String,Boolean> newPhones = phones.entrySet().stream()
//                .collect(Collector.of(() -> new HashMap<String, Boolean>(),
//                        (mutableMap, entryItem) -> mutableMap.put(entryItem.getKey(), entryItem.getKey().equals("+38(093) 431-1043") ? true : entryItem.getValue()),
//                        (map1, map2) -> {
//                            map1.putAll(map2);
//                            return map1;
//                        }
//                ));
//        System.out.println(newPhones);
//    }
//
//    public static void test2(){
//        Long                phone = 380994444444l;
//        Map<Long, Boolean> phones = new HashMap<>();
//        phones.put(380994444444l,false);
//        phones.put(380934311043l,false);
//        phones.put(380970072837l,false);
//        phones.put(380939325476l,false);
//        phones.forEach((k, v) -> System.out.println(k + "=" + v));
//
//        Map<Long,Boolean> newPhones = phones.entrySet().stream()
//                .collect(Collector.of(() -> new HashMap<>(),
//                        (mutableMap, entryItem) -> mutableMap.put(entryItem.getKey(), entryItem.getKey().equals(phone)?true:entryItem.getValue()),
//                        (map1, map2) -> {
//                            map1.putAll(map2);
//                            return map1;
//                        }
//                ));
//        System.out.println(newPhones);
//    }
//
//    public static void test3(Map<Long, Boolean> phones, Long searchPhone){
//        List<Long> mainPhones = new ArrayList<>();
//        mainPhones.add(380994444444l);
//
//        Map<Long,Boolean> contactPhones = phones.entrySet().stream()
//                .collect(Collector.of(() -> new HashMap<>(),
//                        (mutableMap, entryItem) -> mutableMap.put(entryItem.getKey(), entryItem.getKey().equals(searchPhone)?true:entryItem.getValue()),
//                        (map1, map2) -> {
//                            map1.putAll(map2);
//                            return map1;
//                        }
//                ));
//
////        StorePhones storePhones = new StorePhones(mainPhones,contactPhones);
////        System.out.println(storePhones);
//
////        ////////////////////////////////////////////////////////////
//////        Map<Integer, String> map = new HashMap<>();
//////        map.put(10, "apple");
//////        map.put(20, "orange");
//////        map.put(30, "banana");
//////        map.put(40, "watermelon");
//////        map.put(50, "dragonfruit");
//////
//////        List<Integer> result = map.entrySet().stream()
//////                .map(x -> x.getKey())
//////                .collect(Collectors.toList());
//////
//////        result.forEach(System.out::println);
////
////        phones.entrySet().stream()
////                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()))
////                .forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
//    }

    public static void test4(List<Long> mainPhones, List<PhoneSynhronize> contactPhones, Long searchPhone){
        StorePhones storePhones = new StorePhones(mainPhones,contactPhones);

        contactPhones = storePhones.getContactPhones().stream()
                .map(x -> x.getNumberPhone().equals(searchPhone) ? (new PhoneSynhronize(searchPhone, true)) : x)
                .collect(Collectors.toList());
        storePhones.setContactPhones(contactPhones);

        System.out.println(storePhones);
    }

    public static void test5(String storePhones, Long searchPhone){
        Gson gson = new Gson();
        StorePhones oStorePhones = gson.fromJson(storePhones, StorePhones.class);

        List<PhoneSynhronize> contactPhones = oStorePhones.getContactPhones().stream()
                .map(x -> x.getNumberPhone().equals(searchPhone) ? (new PhoneSynhronize(searchPhone, true)) : x)
                .collect(Collectors.toList());
        oStorePhones.setContactPhones(contactPhones);

        System.out.println(oStorePhones);
    }

    public static void test6(StorePhones storePhones, Long searchPhone){
        List<PhoneSynhronize> contactPhones = storePhones.getContactPhones().stream()
                .map(x -> x.getNumberPhone().equals(searchPhone) ? (new PhoneSynhronize(searchPhone, true)) : x)
                .collect(Collectors.toList());
        storePhones.setContactPhones(contactPhones);

        System.out.println(storePhones);
    }
}


