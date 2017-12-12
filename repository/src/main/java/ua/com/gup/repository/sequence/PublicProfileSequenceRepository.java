package ua.com.gup.repository.sequence;

public interface PublicProfileSequenceRepository {

    Long getNextSequenceId(String key);

}
