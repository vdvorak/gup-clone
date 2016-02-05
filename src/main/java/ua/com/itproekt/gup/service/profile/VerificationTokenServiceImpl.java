package ua.com.itproekt.gup.service.profile;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ua.com.itproekt.gup.dao.profile.VerificationTokenRepository;
import ua.com.itproekt.gup.exception.AlreadyVerifiedTokenException;
import ua.com.itproekt.gup.exception.AuthenticationException;
import ua.com.itproekt.gup.exception.TokenHasExpiredException;
import ua.com.itproekt.gup.exception.TokenNotFoundException;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;
import ua.com.itproekt.gup.model.profiles.verification.VerificationTokenType;
import ua.com.itproekt.gup.service.emailnotification.EmailServiceTokenModel;
import ua.com.itproekt.gup.service.emailnotification.MailSenderService;

import java.util.List;


@Service("verificationTokenService")
public class VerificationTokenServiceImpl implements VerificationTokenService {

//    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}$");

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
//    private ProfileRepository profileRepository;
    private ProfilesService profilesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${verification.token.emailVerification.timeToLive.inMinutes}")
    private int emailVerificationTokenExpiryTimeInMinutes;

    @Value("${verification.token.lostPassword.timeToLive.inMinutes}")
    private int lostPasswordTokenExpiryTimeInMinutes;

    @Value("${verification.token.emailRegistration.timeToLive.inMinutes}")
    private int emailRegistrationTokenExpiryTimeInMinutes;

    @Value("${hostName.url}")
    private String hostNameUrl;

    @Transactional
    @Async
    @Override
    public VerificationToken sendEmailRegistrationToken(String userId) {
        VerificationToken token = new VerificationToken(userId, VerificationTokenType.EMAIL_REGISTRATION,
                emailRegistrationTokenExpiryTimeInMinutes);
        verificationTokenRepository.save(token);
        Profile profile = ensureUserIsLoaded(userId);
        mailSenderService.sendRegistrationEmail(new EmailServiceTokenModel(profile.getEmail(), token, hostNameUrl));
        return token;
    }

//    @Transactional
//    @Async
//    public VerificationToken sendEmailRegistrationToken(String userId) {
//        User user = ensureUserIsLoaded(userId);
//        VerificationToken token = new VerificationToken(user,
//                VerificationTokenType.emailRegistration, emailRegistrationTokenExpiryTimeInMinutes);
//        tokenRepository.save(token);
//        mailSenderService.sendRegistrationEmail(new EmailServiceTokenModel(user,
//                token, hostNameUrl));
//        return token;
//    }

    /**
     * generate token if user found otherwise do nothing
     *
//   * @param lostPasswordRequest
     * @return a token or null if user not found
     */
    @Transactional
    @Async
    public VerificationToken sendLostPasswordToken(String email) {
        VerificationToken token = null;
        Profile profile = profilesService.findProfileByEmail(email);

        if (profile != null) {
            List<VerificationToken> tokens = verificationTokenRepository.findByUserIdAndTokenType(profile.getId(), VerificationTokenType.LOST_PASSWORD);
            token = getActiveToken(tokens);
            if (token == null) {
                token = new VerificationToken(profile.getId(),
                VerificationTokenType.LOST_PASSWORD, lostPasswordTokenExpiryTimeInMinutes);
                verificationTokenRepository.save(token);
            }
            mailSenderService.sendLostPasswordEmail(new EmailServiceTokenModel(profile.getEmail(), token, hostNameUrl));
        }

        return token;
    }

    @Override
    @Transactional
    public VerificationToken verifyToken(String base64EncodedToken) {
        VerificationToken token = getVerificationToken(base64EncodedToken);
        if (token.isVerified()) {
            throw new AlreadyVerifiedTokenException();
        }
        token.setVerified(true);
        verificationTokenRepository.save(token);
        return token;
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
//            mailSenderService.sendVerificationEmail(new EmailServiceTokenModel(user, token, hostNameUrl));
//        }
//        return token;
//    }


    @Override
    @Transactional
    public VerificationToken resetPassword(String base64EncodedToken, String password) {
        Assert.notNull(base64EncodedToken);
        VerificationToken token = getVerificationToken(base64EncodedToken);
        if (token.isVerified()) {
            throw new AlreadyVerifiedTokenException();
        }
        token.setVerified(true);
        Profile profile;
        try {
            profile = new Profile()
                .setId(token.getUserId())
                .setPassword(passwordEncoder.encode(password));
        } catch (Exception e) {
            throw new AuthenticationException();
        }

        profilesService.updateProfile(profile);
        verificationTokenRepository.save(token);
        return token;
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
    public VerificationToken getVerificationToken(String base64EncodedToken) {
        Assert.notNull(base64EncodedToken);
        String rawToken = new String(Base64.decodeBase64(base64EncodedToken.getBytes()));
        VerificationToken verificationToken = verificationTokenRepository.findByToken(rawToken);
        if (verificationToken == null) {
            throw new TokenNotFoundException();
        }
        if (verificationToken.hasExpired()) {
            throw new TokenHasExpiredException();
        }
        return verificationToken;
    }


    private Profile ensureUserIsLoaded(String userId) {
        Profile profile = profilesService.findByIdWholeProfile(userId);
        if (profile == null) {
            throw new RuntimeException("Profile not found");
        }

        return profile;
    }

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


    public void setEmailVerificationTokenExpiryTimeInMinutes(int emailVerificationTokenExpiryTimeInMinutes) {
        this.emailVerificationTokenExpiryTimeInMinutes = emailVerificationTokenExpiryTimeInMinutes;
    }

    public void setEmailRegistrationTokenExpiryTimeInMinutes(int emailRegistrationTokenExpiryTimeInMinutes) {
        this.emailRegistrationTokenExpiryTimeInMinutes = emailRegistrationTokenExpiryTimeInMinutes;
    }

    public void setLostPasswordTokenExpiryTimeInMinutes(int lostPasswordTokenExpiryTimeInMinutes) {
        this.lostPasswordTokenExpiryTimeInMinutes = lostPasswordTokenExpiryTimeInMinutes;
    }

    public void setHostNameUrl(String hostNameUrl) {
        this.hostNameUrl = hostNameUrl;
    }
}
