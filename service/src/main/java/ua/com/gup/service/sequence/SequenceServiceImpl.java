package ua.com.gup.service.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.sequence.Sequence;
import ua.com.gup.repository.sequence.SequenceRepository;
import ua.com.gup.repository.sequence.SequenceRepositoryCustomer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing Sequences.
 *
 */
@Service
public class SequenceServiceImpl implements SequenceService {


    private final SequenceRepository sequenceRepository;
    private final SequenceRepositoryCustomer sequenceRepositoryCustomer;

    private final ConcurrentHashMap<String, Boolean> sequenceExists = new ConcurrentHashMap<>();

    @Autowired
    public SequenceServiceImpl(SequenceRepository sequenceRepository, SequenceRepositoryCustomer sequenceRepositoryCustomer) {
        this.sequenceRepository = sequenceRepository;
        this.sequenceRepositoryCustomer = sequenceRepositoryCustomer;
    }


    @Override
    public long getNextSequenceValue(String sequenceId) {
        if (!sequenceExists.getOrDefault(sequenceId, false)) {
            if (sequenceRepository.exists(sequenceId)) {
                sequenceExists.put(sequenceId, true);
            } else {
                Sequence sequence = new Sequence();
                sequence.setId(sequenceId);
                sequence.setValue(0);
                sequenceRepository.save(sequence);
                sequenceExists.put(sequenceId, sequenceRepository.exists(sequenceId));
            }
        }
        return sequenceRepositoryCustomer.getNextSequenceValue(sequenceId);
    }
}
