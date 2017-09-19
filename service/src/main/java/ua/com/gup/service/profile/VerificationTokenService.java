package ua.com.itproekt.gup.service.profile;

import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;


public interface VerificationTokenService {

    /**
     *
     * @param userId
     * @return
     */
    VerificationToken sendEmailRegistrationToken(String userId);

    VerificationToken sendEmailRegistrationToken2(String userId, String refreshToken);

    /**
     *
     * @param base64EncodedToken
     * @return
     */
    VerificationToken getVerificationToken(String base64EncodedToken);

//    VerificationToken sendEmailRegistrationToken(String userId);

    /**
     *
     * @param email
     * @return
     */
    VerificationToken sendLostPasswordToken(String email);

    /**
     *
     * @param base64EncodedToken
     * @return
     */
    VerificationToken verifyToken(String base64EncodedToken);



//    VerificationToken generateEmailVerificationToken(String emailAddress);

    /**
     *
     * @param base64EncodedToken
     * @param password
     * @return
     */
    VerificationToken resetPassword(String base64EncodedToken, String password);
}
