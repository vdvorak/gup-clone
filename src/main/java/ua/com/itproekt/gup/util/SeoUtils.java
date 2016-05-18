package ua.com.itproekt.gup.util;


import ua.com.itproekt.gup.model.news.Blog;
import ua.com.itproekt.gup.model.offer.Offer;

public class SeoUtils {


    //ToDo Make it generic or with interface for all models
    public static Offer makeSeoFieldsForOffer(Offer offer, Long longValueOfSeoKey) {

        String titleInTransliteration = Translit.makeTransliteration(offer.getTitle());

        String base36ValueOfSeoKey = Base36Convertor.encode(longValueOfSeoKey);

        offer.setSeoKey(base36ValueOfSeoKey);

        String seoUrl = titleInTransliteration + "-" + base36ValueOfSeoKey;

        offer.setSeoUrl(seoUrl);

        return offer;
    }


    public static Blog makeSeoFieldsForBlog(Blog blog, Long longValueOfSeoKey) {

        String titleInTransliteration = Translit.makeTransliteration(blog.getTitle());

        String base36ValueOfSeoKey = Base36Convertor.encode(longValueOfSeoKey);

        blog.setSeoKey(base36ValueOfSeoKey);

        String seoUrl = titleInTransliteration + "-" + base36ValueOfSeoKey;

        blog.setSeoUrl(seoUrl);

        return blog;
    }


    public static String getKey(String url) {
        return url.substring(url.lastIndexOf('-') + 1);
    }
}
