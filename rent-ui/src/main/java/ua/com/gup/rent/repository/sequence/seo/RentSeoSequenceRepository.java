package ua.com.gup.rent.repository.sequence.seo;


public interface RentSeoSequenceRepository {

    long getNextSequenceId(String key);
}
