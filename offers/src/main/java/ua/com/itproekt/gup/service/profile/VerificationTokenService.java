package ua.com.itproekt.gup.service.profile;

import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;


public interface VerificationTokenService {

    VerificationToken sendEmailRegistrationToken(String userId);

    VerificationToken getVerificationToken(String base64EncodedToken);

//    VerificationToken sendEmailRegistrationToken(String userId);

    VerificationToken sendLostPasswordToken(String email);

    VerificationToken verifyToken(String base64EncodedToken);

//    VerificationToken generateEmailVerificationToken(String emailAddress);

    VerificationToken resetPassword(String base64EncodedToken, String password);
}
