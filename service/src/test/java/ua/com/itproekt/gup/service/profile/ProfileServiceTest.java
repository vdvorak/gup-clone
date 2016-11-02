package ua.com.itproekt.gup.service.profile;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeMethod;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {









    @InjectMocks
    private ProfilesServiceImpl profilesService;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findPublicProfileById_shouldFindProfileAndDeleteSomeFieldsThenWrapItIntoProfileInfo() {


        // given
        Mockito.when(profileRepository.findById("123")).thenReturn(ProfileTestBuilder.buildOneProfile());


        // when





        // then




        // ToDo проверить чтобы балансы всякие там балансы были налами
//        System.err.println("Test: " + profilesService.findById(PROFILE_ID));
    }




}
