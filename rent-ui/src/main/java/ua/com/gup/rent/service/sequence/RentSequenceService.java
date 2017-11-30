package ua.com.gup.rent.service.sequence;

/**
 * Service Interface for managing Sequences.
 */
public interface RentSequenceService {

    long getNextSequenceValue(String sequenceId);

}
