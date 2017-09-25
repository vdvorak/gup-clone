package ua.com.gup.repository.profile;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.model.profiles.verification.VerificationToken;
import ua.com.gup.model.profiles.verification.VerificationTokenType;

import java.util.List;


public interface VerificationTokenRepository extends MongoRepository<VerificationToken, Long> {

    VerificationToken findById(String id);

    VerificationToken findByToken(String token);

    List<VerificationToken> findByUserId(String userId);

    List<VerificationToken> findByUserIdAndTokenType(String userId, VerificationTokenType tokenType);
}
