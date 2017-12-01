package ua.com.gup.rent.service.sequence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing Sequences.
 *
 */
@Service
public class RentSequenceServiceImpl implements ua.com.gup.rent.service.sequence.RentSequenceService {

    @Autowired
    private ua.com.gup.rent.repository.sequence.RentSequenceRepository rentSequenceRepository;
    @Autowired
    private ua.com.gup.rent.repository.sequence.RentSequenceRepositoryCustomer rentSequenceRepositoryCustomer;

    private final ConcurrentHashMap<String, Boolean> sequenceExists = new ConcurrentHashMap<>();


    @Override
    public long getNextSequenceValue(String sequenceId) {
        if (!sequenceExists.getOrDefault(sequenceId, false)) {
            if (rentSequenceRepository.exists(sequenceId)) {
                sequenceExists.put(sequenceId, true);
            } else {
                ua.com.gup.rent.model.mongo.sequence.RentSequence rentSequence = new ua.com.gup.rent.model.mongo.sequence.RentSequence();
                rentSequence.setId(sequenceId);
                rentSequence.setValue(0);
                rentSequenceRepository.save(rentSequence);
                sequenceExists.put(sequenceId, rentSequenceRepository.exists(sequenceId));
            }
        }
        return rentSequenceRepositoryCustomer.getNextSequenceValue(sequenceId);
    }
}
