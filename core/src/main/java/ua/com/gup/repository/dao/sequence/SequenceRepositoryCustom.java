package ua.com.gup.repository.dao.sequence;


public interface SequenceRepositoryCustom {

    long getNextSequenceValue(String sequenceId);

}
