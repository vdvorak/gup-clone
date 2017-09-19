package ua.com.gup.repository.dao.seoSequence;


public interface SeoSequenceRepository {

    long getNextSequenceId(String key);
}
