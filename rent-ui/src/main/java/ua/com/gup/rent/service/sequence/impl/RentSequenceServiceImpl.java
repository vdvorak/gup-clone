package ua.com.gup.rent.service.sequence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.sequence.RentOfferSequence;
import ua.com.gup.rent.repository.sequence.RentSequenceRepository;
import ua.com.gup.rent.repository.sequence.RentSequenceRepositoryCustomer;
import ua.com.gup.rent.service.sequence.RentSequenceService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Implementation for managing Sequences.
 *
 */
@Service
public class RentSequenceServiceImpl implements RentSequenceService {

    @Autowired
    private RentSequenceRepository rentSequenceRepository;
    @Autowired
    private RentSequenceRepositoryCustomer rentSequenceRepositoryCustomer;

    private final ConcurrentHashMap<String, Boolean> sequenceExists = new ConcurrentHashMap<>();


    @Override
    public long getNextSequenceValue(String sequenceId) {
        if (!sequenceExists.getOrDefault(sequenceId, false)) {
            if (rentSequenceRepository.exists(sequenceId)) {
                sequenceExists.put(sequenceId, true);
            } else {
                RentOfferSequence rentOfferSequence = new RentOfferSequence();
                rentOfferSequence.setId(sequenceId);
                rentOfferSequence.setValue(0);
                rentSequenceRepository.save(rentOfferSequence);
                sequenceExists.put(sequenceId, rentSequenceRepository.exists(sequenceId));
            }
        }
        return rentSequenceRepositoryCustomer.getNextSequenceValue(sequenceId);
    }
}