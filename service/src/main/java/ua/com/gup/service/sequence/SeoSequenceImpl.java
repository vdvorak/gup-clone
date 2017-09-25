package ua.com.gup.service.sequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.repository.sequence.SeoSequenceRepository;

@Service
public class SeoSequenceImpl implements SeoSequenceService {

    @Autowired
    SeoSequenceRepository seoSequenceRepository;

    private static final String SEQ_KEY = "sequence";

    @Override
    public long getNextSequenceId() {
        return seoSequenceRepository.getNextSequenceId(SEQ_KEY);
    }
}
