package ua.com.itproekt.gup.service.profile;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.dao.profile.VerificationTokenRepository;
import ua.com.itproekt.gup.exception.TokenHasExpiredException;
import ua.com.itproekt.gup.exception.TokenNotFoundException;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;
import ua.com.itproekt.gup.model.profiles.verification.VerificationTokenType;
import ua.com.itproekt.gup.service.emailnotification.EmailService;

import javax.swing.*;
import javax.validation.Validator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @version 1.0
 * @author: Iain Porter
 * @since 13/05/2013
 */
@Service("verificationTokenService")
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}$");

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private EmailService mailSenderService;
//    private MailSenderService mailSenderService;

    @Autowired
    private ProfileRepository profileRepository;
//    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${verification.token.emailVerification.timeToLive.inMinutes}")
    private int emailVerificationTokenExpiryTimeInMinutes;

//    @Value("${token.emailRegistration.timeToLive.inMinutes}")
//    private int emailRegistrationTokenExpiryTimeInMinutes;
//
//    @Value("${token.lostPassword.timeToLive.inMinutes}")
//    private int lostPasswordTokenExpiryTimeInMinutes;

    @Value("${hostName.url}")
    private String hostNameUrl;


    @Override
    public VerificationToken sendEmailVerificationToken(String userId) {
        VerificationToken token = new VerificationToken(userId,
                VerificationTokenType.EMAIL_REGISTRATION,
                emailVerificationTokenExpiryTimeInMinutes);
        verificationTokenRepository.save(token);
//        mailSenderService.sendVerificationEmail(new EmailServiceTokenModel(user, token, hostNameUrl));
        Profile profile = ensureUserIsLoaded(userId);
        String message = hostNameUrl + "/registrationConfirm?token=" + token.getToken();
        mailSenderService.sendEmail(profile.getEmail(),
                                    "Подтверждение регистрации",
                                    message);
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
//     * @param lostPasswordRequest
     * @return a token or null if user not found
     */
//    @Transactional
//    @Async
//    public VerificationToken sendLostPasswordToken(LostPasswordRequest lostPasswordRequest) {
//        validate(lostPasswordRequest);
//        VerificationToken token = null;
//        User user = userRepository.findByEmailAddress(lostPasswordRequest.getEmailAddress());
//        if (user != null) {
//            List<VerificationToken> tokens = tokenRepository.findByUserIdAndTokenType(user.getId(), VerificationTokenType.lostPassword);
//            token = getActiveToken(tokens);
//            if (token == null) {
//                token = new VerificationToken(user,
//                VerificationTokenType.lostPassword, lostPasswordTokenExpiryTimeInMinutes);
//                tokenRepository.save(token);
//            }
//            mailSenderService.sendLostPasswordEmail(new EmailServiceTokenModel(user, token, hostNameUrl));
//
//        }
//
//        return token;
//    }

//    @Transactional
//    public VerificationToken verify(String base64EncodedToken) {
//        VerificationToken token = loadToken(base64EncodedToken);
//        User user = userRepository.findOne(token.getUserId());
//        if (token.isVerified() || user.isVerified()) {
//            throw new AlreadyVerifiedException();
//        }
//        token.setVerified(true);
//        user.setVerified(true);
//        userRepository.save(user);
//        tokenRepository.save(token);
//        return token;
//    }

//    @Transactional
//    public VerificationToken generateEmailVerificationToken(String emailAddress) {
//        Assert.notNull(emailAddress);
//        User user = userRepository.findByEmailAddress(emailAddress);
//        if (user == null) {
//            throw new UserNotFoundException();
//        }
//        if (user.isVerified()) {
//            throw new AlreadyVerifiedException();
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

//    @Override
//    public VerificationToken sendEmailRegistrationToken(String userId) {
//        throw new UnsupportedOperationException();
//    }

//    @Transactional
//    public VerificationToken resetPassword(String base64EncodedToken, PasswordRequest passwordRequest) {
//        Assert.notNull(base64EncodedToken);
//        validate(passwordRequest);
//        VerificationToken token = loadToken(base64EncodedToken);
//        if (token.isVerified()) {
//            throw new AlreadyVerifiedException();
//        }
//        token.setVerified(true);
//        User user = userRepository.findOne(token.getUserId());
//        try {
//            user.setHashedPassword(passwordEncoder.encode(passwordRequest.getPassword()));
//        } catch (Exception e) {
//            throw new AuthenticationException();
//        }
//        //set user to verified if not already and authenticated role
//        user.setVerified(true);
//        userRepository.save(user);
//        tokenRepository.save(token);
//        return token;
//    }

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
    public VerificationToken getVerificationToken(String token) {
        Assert.notNull(token);
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null) {
            throw new TokenNotFoundException();
        }
        if (verificationToken.hasExpired()) {
            throw new TokenHasExpiredException();
        }
        return verificationToken;
    }


    private Profile ensureUserIsLoaded(String userId) {
        Profile profile = profileRepository.findProfileById(userId);

//        else {
//            profile = profileRepository.findByEmail(userIdentifier);
//        }
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

    private boolean isValidUuid(String uuid) {
        return UUID_PATTERN.matcher(uuid).matches();
    }

    public void setEmailVerificationTokenExpiryTimeInMinutes(int emailVerificationTokenExpiryTimeInMinutes) {
        this.emailVerificationTokenExpiryTimeInMinutes = emailVerificationTokenExpiryTimeInMinutes;
    }

//    public void setEmailRegistrationTokenExpiryTimeInMinutes(int emailRegistrationTokenExpiryTimeInMinutes) {
//        this.emailRegistrationTokenExpiryTimeInMinutes = emailRegistrationTokenExpiryTimeInMinutes;
//    }
//
//    public void setLostPasswordTokenExpiryTimeInMinutes(int lostPasswordTokenExpiryTimeInMinutes) {
//        this.lostPasswordTokenExpiryTimeInMinutes = lostPasswordTokenExpiryTimeInMinutes;
//    }

//    public void setHostNameUrl(String hostNameUrl) {
//        this.hostNameUrl = hostNameUrl;
//    }
}
