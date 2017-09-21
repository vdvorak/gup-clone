package ua.com.gup.service.sequence;

/**
 * Service Interface for managing Sequences.
 */
public interface SequenceService {

    long getNextSequenceValue(String sequenceId);

}
