package ua.com.gup.service.seosequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.repository.dao.seoSequence.SeoSequenceRepository;

@Service
public class SeoSequenceImpl implements SeoSequenceService {

    @Autowired
    SeoSequenceRepository seoSequenceRepository;

    private static final String SEQ_KEY = "seosequence";

    @Override
    public long getNextSequenceId() {
        return seoSequenceRepository.getNextSequenceId(SEQ_KEY);
    }
}
