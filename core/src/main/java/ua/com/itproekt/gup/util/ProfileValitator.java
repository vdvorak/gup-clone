package ua.com.itproekt.gup.util;

import ua.com.itproekt.gup.model.offer.Address;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;

import java.util.List;
import java.util.Set;


public interface ProfileValitator {

    /**
     *
     * @param publicId
     * @return
     */
    boolean checkPublicId(String publicId);

    /**
     *
     * @param idSeoWord
     * @return
     */
    boolean checkIdSeoWord(String idSeoWord);

    /**
     *
     * @param active
     * @return
     */
    boolean checkActive(boolean active);

    /**
     *
     * @param email
     * @return
     */
    boolean checkEmail(String email);

    /**
     *
     * @param socWendor
     * @return
     */
    boolean checkSocWendor(String socWendor);

    /**
     *
     * @param uid
     * @return
     */
    boolean checkUid(String uid);

    /**
     *
     * @param password
     * @return
     */
    boolean checkPassword(String password);

    /**
     *
     * @param passwordRestore
     * @return
     */
    boolean checkPasswordRestore(String passwordRestore);

    /**
     *
     * @param tokenKey
     * @return
     */
    boolean checkTokenKey(String tokenKey);

    /**
     *
     * @param mainPhoneNumber
     * @return
     */
    boolean checkMainPhoneNumber(String mainPhoneNumber);

    /**
     *
     * @param username
     * @return
     */
    boolean checkUsername(String username);

    /**
     *
     * @param executive
     * @return
     */
    boolean checkExecutive(String executive);

    /**
     *
     * @param contactPerson
     * @return
     */
    boolean checkContactPerson(String contactPerson);

    /**
     *
     * @param address
     * @return
     */
    boolean checkAddress(Address address);

    /**
     *
     * @param imgId
     * @return
     */
    boolean checkImgId(String imgId);

    /**
     *
     * @param imgUrl
     * @return
     */
    boolean checkImgUrl(String imgUrl);

    /**
     *
     * @param birthDate
     * @return
     */
    boolean checkBirthDate(Long birthDate);

    /**
     *
     * @param contact
     * @return
     */
    boolean checkContact(Contact contact);

    /**
     *
     * @param contactList
     * @return
     */
    boolean checkContactList(Set<ProfileContactList> contactList);

    /**
     *
     * @param financeInfo
     * @return
     */
    boolean checkFinanceInfo(FinanceInfo financeInfo);

    /**
     *
     * @param favoriteOffers
     * @return
     */
    boolean checkFavoriteOffers(Set<String> favoriteOffers);

    /**
     *
     * @param status
     * @return
     */
    boolean checkStatus(String status);

    /**
     *
     * @param point
     * @return
     */
    boolean checkPoint(Integer point);

    /**
     *
     * @param profileRating
     * @return
     */
    boolean checkProfileRating(Set<ProfileRating> profileRating);

    /**
     *
     * @param confirmModerator
     * @return
     */
    boolean checkConfirmModerator(Boolean confirmModerator);

    /**
     *
     * @param userRoles
     * @return
     */
    boolean checkUserRoles(Set<UserRole> userRoles);

    /**
     *
     * @param createdDate
     * @return
     */
    boolean checkCreatedDate(Long createdDate);

    /**
     *
     * @param lastLoginDate
     * @return
     */
    boolean checkLastLoginDate(Long lastLoginDate);

    /**
     *
     * @param online
     * @return
     */
    boolean checkOnline(boolean online);

    /**
     *
     * @param notCompletedFields
     * @return
     */
    boolean checkNotCompletedFields(int notCompletedFields);

    /**
     *
     * @param orderAddressList
     * @return
     */
    boolean checkOrderAddressList(List<OrderAddress> orderAddressList);

    /**
     *
     * @param offerUserContactInfoList
     * @return
     */
    boolean checkOfferUserContactInfoList(List<OfferUserContactInfo> offerUserContactInfoList);

}
