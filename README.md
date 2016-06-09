
#Modules:

OAuth-2 WebService
---

1. [ua.com.itproekt.gup.oauth2. **GupOAuth2ExceptionRenderer**](ua.com.itproekt.gup.oauth2.GupOAuth2ExceptionRenderer)
2. [ua.com.itproekt.gup.util. **Oauth2Util**](ua.com.itproekt.gup.util.Oauth2Util)
3. [ua.com.itproekt.gup.util. **SecurityOperations**](ua.com.itproekt.gup.util.SecurityOperations)

4. [ua.com.itproekt.gup.dao.oauth2. **OAuth2AccessTokenRepository**](ua.com.itproekt.gup.dao.oauth2.OAuth2AccessTokenRepository)
5. [ua.com.itproekt.gup.dao.oauth2. **OAuth2RefreshTokenRepository**](ua.com.itproekt.gup.dao.oauth2.OAuth2RefreshTokenRepository)

6. [ua.com.itproekt.gup.model.oauth2. **OAuth2AuthenticationAccessToken**](ua.com.itproekt.gup.model.oauth2.OAuth2AuthenticationAccessToken)
7. [ua.com.itproekt.gup.model.oauth2. **OAuth2AuthenticationRefreshToken**](ua.com.itproekt.gup.model.oauth2.OAuth2AuthenticationRefreshToken)
8. [ua.com.itproekt.gup.model.login. **LoggedUser**](ua.com.itproekt.gup.model.login.LoggedUser)

9. [ua.com.itproekt.gup.mongo.converter. **OAuth2AuthenticationReadConverter**](ua.com.itproekt.gup.mongo.converter.OAuth2AuthenticationReadConverter)

10. [ua.com.itproekt.gup.service.oauth2. **TokenStoreService**](ua.com.itproekt.gup.service.oauth2.TokenStoreService)


* `authenticateByTokensFromCookies` `authenticateByRefreshToken` `doFilter` [ua.com.itproekt.gup.filter.OAuthFilter](ua.com.itproekt.gup.filter.OAuthFilter)
* `LoggedUser` [ua.com.itproekt.gup.model.login.LoggedUser](ua.com.itproekt.gup.model.login.LoggedUser)
* `authenticateByEmailAndPassword` `login` [ua.com.itproekt.gup.api.rest.loginAndSignUp.LoginRestController](ua.com.itproekt.gup.api.rest.loginAndSignUp.LoginRestController)


* [ua.com.itproekt.gup.util.SecurityOperations](ua.com.itproekt.gup.util.SecurityOperations)

* `SecurityOperations` `initDialog` [ua.com.itproekt.gup.controller.DialogueController](ua.com.itproekt.gup.controller.DialogueController)
* `updateCountersWhenRead` `updateCountersWhenAddOneMsg` [ua.com.itproekt.gup.service.privatemessage.DialogueServiceImpl](ua.com.itproekt.gup.service.privatemessage.DialogueServiceImpl)
* `reserveOffer` [ua.com.itproekt.gup.service.offers.OffersServiceImpl](ua.com.itproekt.gup.service.offers.OffersServiceImpl)
* `rentOffer` `deleteRent` [ua.com.itproekt.gup.api.rest.offers.OfferRentRestController](ua.com.itproekt.gup.api.rest.offers.OfferRentRestController)
* `privatOfice` `accountFound` [ua.com.itproekt.gup.controller.AccountController](ua.com.itproekt.gup.controller.AccountController)
* `SecurityOperations` `checkBalance` [ua.com.itproekt.gup.controller.MainController](ua.com.itproekt.gup.controller.MainController)
* `loadUserByUsername` `buildUserForAuthentication` [ua.com.itproekt.gup.service.login.UserDetailsServiceImpl](ua.com.itproekt.gup.service.login.UserDetailsServiceImpl)
* `getUserEvents` `deleteEvent` `setViewed` [ua.com.itproekt.gup.api.rest.activityfeed.ActivityFeedRestController](ua.com.itproekt.gup.api.rest.activityfeed.ActivityFeedRestController)
* `SecurityOperations` [ua.com.itproekt.gup.api.rest.dialogues.SupportDialogueRestController](ua.com.itproekt.gup.api.rest.dialogues.SupportDialogueRestController)
* `reserveOffer` `deleteReservation` [ua.com.itproekt.gup.api.rest.offers.OfferReservationRestController](ua.com.itproekt.gup.api.rest.offers.OfferReservationRestController)
* `getProfileById` `readUserProfile` `getLoggedUser` `updateProfile` `addToMyContactList` `deleteFromMyContactList` `joinToOrganization` [ua.com.itproekt.gup.api.rest.profiles.ProfileRestController](ua.com.itproekt.gup.api.rest.profiles.ProfileRestController)
* `editProfilePage` [ua.com.itproekt.gup.controller.profile.ProfileController](ua.com.itproekt.gup.controller.profile.ProfileController)
* `createOffer` `editOffer` [ua.com.itproekt.gup.api.rest.offers.OfferRestController](ua.com.itproekt.gup.api.rest.offers.OfferRestController)
