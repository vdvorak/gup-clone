package ua.com.gup.search.repository;

import ua.com.gup.search.model.search.ESCategory;
import ua.com.gup.search.util.Locale;

import java.io.IOException;

public interface ESCategoryRepository {

    /**
     *
     * @param code - category code
     * @param locale - current locale (language)
     * @return
     * @throws IOException
     *
     * @see Locale
     */
    ESCategory findOneByCode(Long code, Locale locale) throws IOException;

    void createOffer() throws IOException ;
}
