package ua.com.itproekt.gup.dao.seoSequence;


public interface SeoSequenceRepository {

    long getNextSequenceId(String key);
}
