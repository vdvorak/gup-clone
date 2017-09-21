package ua.com.gup.repository.dao.sequence;


public interface SeoSequenceRepository {

    long getNextSequenceId(String key);
}
