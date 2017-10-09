package ua.com.gup.util;

import ua.com.gup.domain.offer.Offer;

public class SeoUtils {


    //ToDo Make it generic or with interface for all models
    public static Offer makeSeoFieldsForOffer(Offer offer, Long longValueOfSeoKey) {

        String titleInTransliteration = Translit.makeTransliteration(offer.getTitle());

        String base36ValueOfSeoKey = Base36Convertor.encode(longValueOfSeoKey);

       //offer.setSeoKey(base36ValueOfSeoKey);

        String seoUrl = titleInTransliteration + "-" + base36ValueOfSeoKey;

        offer.setSeoUrl(seoUrl);

        return offer;
    }

    public static String getKey(String url) {
        return url.substring(url.lastIndexOf('-') + 1);
    }
}
