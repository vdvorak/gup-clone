package ua.com.gup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.gup.mongo.composition.domain.verification.VerificationToken;
import ua.com.gup.mongo.model.enumeration.VerificationTokenType;
import ua.com.gup.repository.profile.VerificationTokenRepository;
import ua.com.gup.service.UserService;
import ua.com.gup.service.VerificationTokenService;

import java.util.List;


@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserService profilesService;


    @Value("${verification.token.lostPassword.timeToLive.inMinutes}")
    private int lostPasswordTokenExpiryTimeInMinutes;

    @Value("${verification.token.emailRegistration.timeToLive.inMinutes}")
    private int emailRegistrationTokenExpiryTimeInMinutes;


    @Override
    public VerificationToken generateEmailRegistrationToken(String userId) {
        return new VerificationToken(userId, VerificationTokenType.EMAIL_REGISTRATION, emailRegistrationTokenExpiryTimeInMinutes);
    }

    @Override
    public VerificationToken generateForgetPasswordToken(String userId) {
        return new VerificationToken(userId, VerificationTokenType.EMAIL_FORGET_PASSWORD, lostPasswordTokenExpiryTimeInMinutes);
    }

    @Override
    public void saveToken(VerificationToken token) {
        verificationTokenRepository.save(token);
    }

    @Override
    public void deleteToken(VerificationToken token) {
        verificationTokenRepository.delete(token);
    }


    @Override
    public VerificationToken sendEmailRegistrationToken2(String userId, String refreshToken) {
        throw new RuntimeException("Not implemented yet");
//        VerificationToken token = new VerificationToken(userId, VerificationTokenType.EMAIL_REGISTRATION, emailRegistrationTokenExpiryTimeInMinutes);
//        verificationTokenRepository.save(token);
//        Profile profile = ensureUserIsLoaded(userId);
//        hostNameUrl = refreshToken;
//        emailService.sendRegistrationEmail(new EmailServiceTokenModel(profile.getEmail(), token, hostNameUrl));
//        return token;
    }

    /**
     * generate token if user found otherwise do nothing
     * <p>
     * //   * @param lostPasswordRequest
     *
     * @return unban token or null if user not found
     */
    public VerificationToken sendLostPasswordToken(String email) {
        throw new RuntimeException("Not implemented yet");
//        VerificationToken token = null;
//        Profile profile = profilesService.findProfileByEmail(email);
//
//        if (profile != null) {
//            List<VerificationToken> tokens = verificationTokenRepository.findByUserIdAndTokenType(profile.getId(), VerificationTokenType.LOST_PASSWORD);
//            token = getActiveToken(tokens);
//            if (token == null) {
//                token = new VerificationToken(profile.getId(),
//                        VerificationTokenType.LOST_PASSWORD, lostPasswordTokenExpiryTimeInMinutes);
//                verificationTokenRepository.save(token);
//            }
//            emailService.sendLostPasswordEmail(new EmailServiceTokenModel(profile.getEmail(), token, hostNameUrl));
//        }
//
//        return token;
    }

    @Override
    public VerificationToken verifyToken(String base64EncodedToken) {
        throw new RuntimeException("Not implemented yet");
//        VerificationToken token = getVerificationToken(base64EncodedToken);
//        if (token.isVerified()) {
//            throw new AlreadyVerifiedTokenException();
//        }
//        token.setVerified(true);
//        verificationTokenRepository.save(token);
//        return token;
    }

//    @Transactional
//    public VerificationToken generateEmailVerificationToken(String emailAddress) {
//        Assert.notNull(emailAddress);
//        User user = userRepository.findByEmailAddress(emailAddress);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        if (user.isVerified()) {
//            throw new AlreadyVerifiedTokenException();
//        }
//        //if token still active resend that
//        VerificationToken token = getActiveToken(tokenRepository.findByUserIdAndTokenType(user.getId(), VerificationTokenType.emailVerification));
//        if (token == null) {
//            token = sendEmailVerificationToken(user);
//        } else {
//            emailService.sendVerificationEmail(new EmailServiceTokenModel(user, token, hostNameUrl));
//        }
//        return token;
//    }


    @Override
    public VerificationToken resetPassword(String base64EncodedToken, String password) {
        throw new RuntimeException("Not implemented yet");
//        Assert.notNull(base64EncodedToken);
//        VerificationToken token = getVerificationToken(base64EncodedToken);
//        if (token.isVerified()) {
//            throw new AlreadyVerifiedTokenException();
//        }
//        token.setVerified(true);
//        Profile profile;
//        try {
//            profile = new Profile()
//                    .setId(token.getUserId())
//                    .setPassword(passwordEncoder.encode(password));
//        } catch (Exception e) {
//            throw new AuthenticationException();
//        }
//
//        profilesService.editProfile(profile);
//        verificationTokenRepository.save(token);
//        return token;
    }

//    private VerificationToken loadToken(String base64EncodedToken) {
//        Assert.notNull(base64EncodedToken);
//        String rawToken = new String(Base64.decodeBase64(base64EncodedToken.getBytes()));
//        VerificationToken token = tokenRepository.findByToken(rawToken);
//        if (token == null) {
//            throw new TokenNotFoundException();
//        }
//        if (token.hasExpired()) {
//            throw new TokenHasExpiredException();
//        }
//        return token;
//    }

    @Override
    public VerificationToken getVerificationToken(String rawToken) {
        return verificationTokenRepository.findByToken(rawToken);
    }


//    private Profile ensureUserIsLoaded(String profileId) {
//        Profile profile = profilesService.findWholeProfileById(profileId);
//        if (profile == null) {
//            throw new RuntimeException("Profile not found");
//        }
//
//        return profile;
//    }

    private VerificationToken getActiveToken(List<VerificationToken> tokens) {
        VerificationToken activeToken = null;
        for (VerificationToken token : tokens) {
            if (!token.hasExpired() && !token.isVerified()) {
                activeToken = token;
                break;
            }
        }
        return activeToken;
    }


    public void setEmailRegistrationTokenExpiryTimeInMinutes(int emailRegistrationTokenExpiryTimeInMinutes) {
        this.emailRegistrationTokenExpiryTimeInMinutes = emailRegistrationTokenExpiryTimeInMinutes;
    }

    public void setLostPasswordTokenExpiryTimeInMinutes(int lostPasswordTokenExpiryTimeInMinutes) {
        this.lostPasswordTokenExpiryTimeInMinutes = lostPasswordTokenExpiryTimeInMinutes;
    }

}
