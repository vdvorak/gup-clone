package ua.com.itproekt.gup.service.profile;

import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
public interface VerificationTokenService {

    VerificationToken sendEmailVerificationToken(String userId);

    VerificationToken getVerificationToken(String token);

//    VerificationToken sendEmailRegistrationToken(String userId);

//    VerificationToken sendLostPasswordToken(LostPasswordRequest lostPasswordRequest);

//    VerificationToken verify(String base64EncodedToken);

//    VerificationToken generateEmailVerificationToken(String emailAddress);

//    VerificationToken resetPassword(String base64EncodedToken, PasswordRequest passwordRequest);
}
