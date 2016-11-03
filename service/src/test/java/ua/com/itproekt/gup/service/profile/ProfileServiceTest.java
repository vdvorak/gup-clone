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
import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.service.offers.OffersServiceImpl;
import ua.com.itproekt.gup.service.order.OrderServiceImpl;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @InjectMocks
    private ProfilesServiceImpl profilesService;

    @Mock
    OffersServiceImpl offersService;
    @Mock
    OrderServiceImpl orderService;

    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    // ToDo добавить фидбеки в тестовый профиль

    // TODo добавить Contact в тестовый профиль


    @Test
    public void findPublicProfileById_shouldFindProfileAndDeleteSomeFieldsThenWrapItIntoProfileInfo() {


        // given
        Mockito.when(profileRepository.findById("123")).thenReturn(ProfileTestBuilder.buildOneProfile());
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAuthorId("123");

        Mockito.when(offersService.findOffersWihOptions(offerFilterOptions)).thenReturn(new EntityPage<Offer>());

        List<Offer> offerList = offersService.findOffersWihOptions(offerFilterOptions).getEntities();

        // when
        ProfileInfo profileInfo = profilesService.findPublicProfileById("123");
        // then

        assertNull(profileInfo.getProfile().getPassword());

//        System.err.println("Test: " + profilesService.findById(PROFILE_ID));
    }


}
