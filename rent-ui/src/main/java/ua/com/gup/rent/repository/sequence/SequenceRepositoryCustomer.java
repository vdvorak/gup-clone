package ua.com.gup.rent.repository.sequence;


public interface SequenceRepositoryCustomer {

    long getNextSequenceValue(String sequenceId);

}
