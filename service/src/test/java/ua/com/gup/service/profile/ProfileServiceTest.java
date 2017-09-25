package ua.com.gup.service.profile;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeMethod;
import ua.com.gup.dto.ProfileInfo;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.service.offers.OffersServiceImpl;
import ua.com.gup.util.EntityPage;

import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @Mock
    OffersServiceImpl offersService;
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

    // ToDo добавить фидбеки в тестовый профиль

    // TODo добавить Contact в тестовый профиль


    @Test
    public void findPublicProfileById_shouldFindProfileAndDeleteSomeFieldsThenWrapItIntoProfileInfo() {

        // given
        Mockito.when(profileRepository.findById("123")).thenReturn(ProfileTestBuilder.buildOneProfile());
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setAuthorId("123");

        Mockito.when(offersService.findOffersWihOptions(offerFilterOptions)).thenReturn(new EntityPage<>());

        // when
        ProfileInfo profileInfo = profilesService.findPublicProfileById("123");

        // then
        assertNull(profileInfo.getProfile().getPassword());
        assertNull(profileInfo.getProfile().getEmail());
        assertNull(profileInfo.getProfile().getContactList());
        assertNull(profileInfo.getProfile().getFinanceInfo());
        assertNull(profileInfo.getProfile().getOrderAddressList());
        assertNull(profileInfo.getProfile().getUserRoles());
        assertNull(profileInfo.getProfile().getOfferUserContactInfoList());
        assertNull(profileInfo.getProfile().getFavoriteOffers());
        assertNull(profileInfo.getUserBalance());
        assertNull(profileInfo.getUserBonusBalance());
        assertNull(profileInfo.getUnreadMessages());
        assertNull(profileInfo.getUnreadEventsCount());
    }


}
