package ua.com.gup.repository.sequence;


public interface SeoSequenceRepository {

    long getNextSequenceId(String key);
}
