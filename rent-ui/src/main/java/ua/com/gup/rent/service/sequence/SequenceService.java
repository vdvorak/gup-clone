package ua.com.gup.rent.service.sequence;

/**
 * Service Interface for managing Sequences.
 */
public interface SequenceService {

    long getNextSequenceValue(String sequenceId);

}
