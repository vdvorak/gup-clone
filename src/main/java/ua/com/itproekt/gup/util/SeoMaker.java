package ua.com.itproekt.gup.util;


import ua.com.itproekt.gup.model.offer.Offer;

public class SeoMaker {


    //ToDo Make it generic or with interface for all models
    public static Offer makeSeoFields(Offer offer, Long longValueOfSeoKey) {

        String titleInTransliteration = Translit.makeTransliteration(offer.getTitle());

        String base36ValueOfSeoKey = Base36Convertor.encode(longValueOfSeoKey);

        offer.setSeoKey(base36ValueOfSeoKey);

        String seoUrl = titleInTransliteration + "-" + base36ValueOfSeoKey;

        offer.setSeoUrl(seoUrl);

        return offer;
    }
}
