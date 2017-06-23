package ua.com.gup.web.rest;

import ua.com.gup.domain.complaint.ComplaintOfferStatus;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Aaa {

    public static void main(String[] args) {
        Map<String, String> statuses =
                Arrays.stream(ComplaintOfferStatus.values())
                        .collect(Collectors.toMap(ComplaintOfferStatus::name, ComplaintOfferStatus::toString));

        System.out.println( statuses );

        String status = "NEW";
//        String status = "asadasdasda";
//        String status = null;
//        System.out.println( statuses.containsKey(status) );
////        System.out.println( statuses.get(status) );
//        System.out.println( ComplaintOfferStatus.valueOf(status) );
//
//        ComplaintOfferStatus getStatus = ComplaintOfferStatus.valueOf(status);


        ComplaintOfferStatus testStatus = ComplaintOfferStatus.NEW;
        System.out.println( testStatus );

        if (statuses.containsKey(status)){
            ComplaintOfferStatus getStatus = ComplaintOfferStatus.valueOf(status);
            System.out.println( getStatus );
        } else {

        }
    }

}
