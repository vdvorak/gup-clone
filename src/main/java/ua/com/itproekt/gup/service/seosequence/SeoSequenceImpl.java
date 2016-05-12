package ua.com.itproekt.gup.service.seosequence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.seoSequence.SeoSequenceRepository;

@Service
public class SeoSequenceImpl implements SeoSequenceService {

    @Autowired
    SeoSequenceRepository seoSequenceRepository;

    @Override
    public long getNextSequenceId(String key) {
        return seoSequenceRepository.getNextSequenceId(key);
    }
}
