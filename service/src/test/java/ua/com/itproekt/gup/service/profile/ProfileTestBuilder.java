package ua.com.itproekt.gup.service.profile;

import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.FinanceInfo;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;
import ua.com.itproekt.gup.util.OfferUserContactInfo;
import ua.com.itproekt.gup.util.TransportCompany;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class prepare and build profile instance for tests.
 *
 * @author Kobylyatskyy Alexander
 */
public class ProfileTestBuilder {

    private static final String PROFILE_EMAIL = "test@gmail.com";
    private static final String PROFILE_PASSWORD = "123456";
    private static final String PROFILE_USERNAME = "Valera";
    private static final String PROFILE_ID = "123";
    private static final String PROFILE_USREUO = "412314";
    private static final String PROFILE_BANKCODE = "00000";
    private static final String PROFILE_VATNUMBER = "11111";
    private static final String PROFILE_BENEFICIARYBANK = "Privat Bank";
    private static final String PROFILE_BENEFICIARYACCOUNT = "3242";
    private static final String PROFILE_LEGALENTITYLOCATION = "Kiev";
    private static final String PROFILE_IDSEOWORD = "val";
    private static final String PROFILE_IMGID = "23423534543654654";
    private static final String PROFILE_MAINPHONENUMBER = "38054654689";
    private static final Long PROFILE_BIRTHDAY = 849879846846l;
    private static final String PROFILE_STATUS = "Hello world!";
    private static final int PROFILE_POINT = 8;
    private static final boolean PROFILE_CONFIRMMODERATOR = true;
    private static final Long PROFILE_CREATEDATE = 8498794846844l;

    private static final UserRole PROFIlE_USERROLE = UserRole.ROLE_USER;
    private static final String PROFILE_FAVOURITEOFFER_FIRST = "23432423";
    private static final String PROFILE_FAVOURITEOFFER_SECOND = "768786786";
    private static final String PROFILE_USERCONTACTINFO_CONTACTNAME = "Olga";
    private static final String PROFILE_USERCONTACTINFO_EMAIL = "olga@gmail.com";
    private static final String PROFILE_USERCONTACTINFO_PHONENUMBER = "38012345687";
    private static final String CONTACT_FIRST = "ppp";
    private static final String CONTACT_SECOND = "qqq";
    private static final String PROFILE_ADDRESS_NAME = "home";
    private static final String PROFILE_ADDRESS_ADDRESS = "Kiev";
    private static final String PROFILE_ADDRESS_PHONENUMBER = "380505665793";

    private static final TransportCompany PROFILE_ADDRESS_TRANSPORTCOMPANY = TransportCompany.NOVA_POSHTA;
    static Profile profile;
    private static Set<String> phoneNumbers = new HashSet<>();
    private static Set<String> contactList = new HashSet<>();
    private static FinanceInfo financeInfo = new FinanceInfo();
    private static List<OrderAddress> orderAddressList = new ArrayList<>();
    private static OrderAddress orderAddress = new OrderAddress();
    private static Set<UserRole> userRoleSet = new HashSet<>();
    private static OfferUserContactInfo offerUserContactInfo = new OfferUserContactInfo();
    private static List<OfferUserContactInfo> userContactInfoList = new ArrayList<>();
    private static Set<String> favoriteOffers = new HashSet<>();


    // block of static initialization
    static {
        phoneNumbers.add(PROFILE_USERCONTACTINFO_PHONENUMBER);

        contactList.add(CONTACT_FIRST);
        contactList.add(CONTACT_SECOND);
        financeInfo.setBankCode(PROFILE_BANKCODE)
                .setUsreou(PROFILE_USREUO)
                .setVatNumber(PROFILE_VATNUMBER)
                .setBeneficiaryAccount(PROFILE_BENEFICIARYACCOUNT)
                .setBeneficiaryBank(PROFILE_BENEFICIARYBANK)
                .setLegalEntityLocation(PROFILE_LEGALENTITYLOCATION);

        orderAddress.setName(PROFILE_ADDRESS_NAME)
                .setName(PROFILE_ADDRESS_ADDRESS)
                .setPhoneNumber(PROFILE_ADDRESS_PHONENUMBER)
                .setTransportCompany(PROFILE_ADDRESS_TRANSPORTCOMPANY);

        orderAddressList.add(orderAddress);
        userRoleSet.add(PROFIlE_USERROLE);

        offerUserContactInfo.setEmail(PROFILE_USERCONTACTINFO_EMAIL)
                .setContactName(PROFILE_USERCONTACTINFO_CONTACTNAME)
                .setPhoneNumbers(phoneNumbers);

        userContactInfoList.add(offerUserContactInfo);
        favoriteOffers.add(PROFILE_FAVOURITEOFFER_FIRST);
        favoriteOffers.add(PROFILE_FAVOURITEOFFER_SECOND);
    }

    static Profile buildOneProfile() {

        if (profile == null) {
            profile = new Profile();
            profile.setId(PROFILE_ID)
                    .setPassword(PROFILE_PASSWORD)
                    .setIdSeoWord(PROFILE_IDSEOWORD)
                    .setMainPhoneNumber(PROFILE_MAINPHONENUMBER)
                    .setImgId(PROFILE_IMGID)
                    .setBirthDate(PROFILE_BIRTHDAY)
                    .setStatus(PROFILE_STATUS)
                    .setPoint(PROFILE_POINT)
                    .setConfirmModerator(PROFILE_CONFIRMMODERATOR)
                    .setCreatedDate(PROFILE_CREATEDATE)
                    .setEmail(PROFILE_EMAIL)
                    .setUsername(PROFILE_USERNAME)
                    .setContactList(contactList)
                    .setFinanceInfo(financeInfo)
                    .setOrderAddressList(orderAddressList)
                    .setUserRoles(userRoleSet)
                    .setOfferUserContactInfoList(userContactInfoList)
                    .setFavoriteOffers(favoriteOffers);
            return profile;
        } else {
            return profile;
        }
    }
}
