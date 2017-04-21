package ua.com.gup.service;

/**
 * Service Interface for managing Sequences.
 */
public interface SequenceService {

    long getNextSequenceValue(String sequenceId);

}
