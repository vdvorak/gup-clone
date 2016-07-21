package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.model.offer.*;
import ua.com.itproekt.gup.service.offers.OffersService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class OffersTestController {
    @Autowired
    OffersService offersService;

    @Autowired
    OfferRepository offerRepository;

    @RequestMapping("/addOffer")
     public String addOffer(Model model) {
        OfferUserContactInfo offerUserContactInfo = new OfferUserContactInfo();
        offerUserContactInfo.setContactName("Kiril");
        offerUserContactInfo.setEmail("test@email");
        offerUserContactInfo.setSkypeLogin("sdkfjckdskc");

        Offer offer = new Offer();
        offer.setAuthorId("564214c9b2d9c33c4d3bb31f");
        offer.setTitle("Продам почку");
        offer.setDescription("Почка обычная, 1 штука");
        Address address = new Address();
        address.setCountry("Ukraine");
        address.setCity("Kiev");
        offer.setAddress(address);
//        AuthorInformation authorInformation = new AuthorInformation();
//        authorInformation.setEmail("test@gmail.com");
//        authorInformation.setPhoneNumber("+38 099 666 66 66");
//        authorInformation.setFirstName("Адольф");
//        offer.setAuthorInformation(authorInformation); ///////
        offer.setCanBeReserved(true);
        offer.setPrice(2_000_000);
        offer.setUrgent(true);

        offer.setCanBeRented(true);
            Rent rent = new Rent();
            RentPeriodInfo rentPeriod = new RentPeriodInfo();
            rentPeriod.setFrom(1299110400L);
            rentPeriod.setTo(1301184000L);
            rentPeriod.setPrice(1000);
            HashSet<RentPeriodInfo> rentPeriodIntegerHashSet = new HashSet<>();
        rentPeriodIntegerHashSet.add(rentPeriod);

        rent.setRentPeriodsAndPrices(rentPeriodIntegerHashSet);

        HashSet<RentedOfferPeriodInfo> offerUserContactInfoHashSet = new HashSet<>();

        RentedOfferPeriodInfo rentedOfferPeriodInfo = new RentedOfferPeriodInfo();
        rentedOfferPeriodInfo.setFrom(1299283200L);
        rentedOfferPeriodInfo.setTo(1300579200L);
        rentedOfferPeriodInfo.setContactInfo(offerUserContactInfo);
        offerUserContactInfoHashSet.add(rentedOfferPeriodInfo);

        rent.setRentedOfferPeriodInfo(offerUserContactInfoHashSet);

        offer.setRent(rent);

        offer.setCurrency(Currency.UAH);
//        offer.setCategory("CATEGORY_ID");

        offersService.create(offer);
        model.addAttribute("message", "Offer \"" + offer.getTitle() + "\" is created.");
        return "index";
    }

    @RequestMapping("/addOffers/{numberOfOffers}")
    public String addUser(@PathVariable("numberOfOffers") int numberOfOffers, Model model) {
        for (int i = 0; i < numberOfOffers; i++) {
            Offer offer = new Offer();
            offer.setAuthorId("564214c9b2d9c33c4d3bb31p");
            Address address = new Address();
                address.setCountry("Украина");
            offer.setAddress(address);
                OfferUserContactInfo userContactInfo = new OfferUserContactInfo();
                userContactInfo.setEmail("test@mail." + i % 7);
            offer.setUserInfo(userContactInfo);
            offer.setCanBeReserved(true);
            offer.setCanBeRented(true);

                List<Property> properties = new ArrayList<>();
                properties.add(new Property("1", "" + i % 7));
                properties.add(new Property("2", "" + i % 14));
                properties.add(new Property("3", "" + i));
            offer.setProperties(properties);

                LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
                linkedHashSet.add("" + i % 14);
            offer.setCategories(linkedHashSet);
            offer.setPrice(100+i % 14);
            offer.setUrgent(i % 10 == 1);
            offer.setActive(true);
            offer.setTitle("title title" + i % 7);
            offer.setDescription(" description  description  description  description  description  description  description  description " + i % 7);
            offer.setCurrency(Currency.UAH);

            offersService.create(offer);
        }

        model.addAttribute("message", numberOfOffers + " test offers is created.");
        return "index";
    }
}
