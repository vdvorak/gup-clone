package ua.com.gup.rent.util;


import com.github.slugify.Slugify;

import java.util.HashMap;

public class RentOfferSEOFriendlyUrlUtil {

    private static final Slugify SLUGIFY = new Slugify().withCustomReplacements(new HashMap<String, String>() {{
        put("і", "i");
        put("ї", "yi");
        put("Ї", "YI");
    }});

    public static String generateSEOFriendlyUrl(String source) {
        return SLUGIFY.slugify(source);

    }
}
