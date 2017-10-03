package ua.com.gup.service.profile;

import ua.com.gup.model.profiles.verification.VerificationToken;


public interface VerificationTokenService {

    /**
     *
     * @param userId
     * @return
     */
    VerificationToken generateEmailRegistrationToken(String userId);

    VerificationToken generateForgetPasswordToken(String userId);


    void saveToken(VerificationToken token);

    void deleteToken(VerificationToken token);

    VerificationToken sendEmailRegistrationToken2(String userId, String refreshToken);

    /**
     *
     * @param base64EncodedToken
     * @return
     */
    VerificationToken getVerificationToken(String base64EncodedToken);

//    VerificationToken generateEmailRegistrationToken(String userId);



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
