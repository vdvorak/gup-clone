package ua.com.gup.rent.service.sequence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.sequence.Sequence;
import ua.com.gup.rent.repository.sequence.SequenceRepository;
import ua.com.gup.rent.repository.sequence.SequenceRepositoryCustomer;
import ua.com.gup.rent.service.sequence.SequenceService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing Sequences.
 *
 */
@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private  SequenceRepository sequenceRepository;
    @Autowired
    private  SequenceRepositoryCustomer sequenceRepositoryCustomer;

    private final ConcurrentHashMap<String, Boolean> sequenceExists = new ConcurrentHashMap<>();


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
