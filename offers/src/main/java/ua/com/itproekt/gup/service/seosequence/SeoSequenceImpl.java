package ua.com.itproekt.gup.service.seosequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.seoSequence.SeoSequenceRepository;

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
