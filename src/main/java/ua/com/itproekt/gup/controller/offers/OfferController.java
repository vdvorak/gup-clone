package ua.com.itproekt.gup.controller.offers;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SeoUtils;

import java.io.IOException;

@Controller
public class OfferController {
    @Autowired
    OffersService offersService;
    @Autowired
    ProfilesService profilesService;


    //----------------------------------- all offers  ------
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String getOffers(Model model) {
        String flag = "offer-all";
        model.addAttribute("flag", flag);
        return "offer/offer-all";
    }

    //----------------------------------- one certain offer  ------
    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    public String getOneOffer(Model model, @PathVariable("id") String id) {
        String flag = "offer";
        model.addAttribute("flag", flag);
        model.addAttribute("offerId", id);
        return "offer/offer";
    }


    //----------------------------------- one certain offer with SEO key  ------
    @RequestMapping(value = "/obyavlenie/{seoUrl}", method = RequestMethod.GET)
    public String getOneOfferBySeoKey(Model model, @PathVariable("seoUrl") String seoUrl) {

        String offerSeoKey = SeoUtils.getKey(seoUrl);

        Offer offer = offersService.findBySeoKey(offerSeoKey);

        String flag = "offer";
        model.addAttribute("flag", flag);
        model.addAttribute("offerId", offer.getId());
        return "offer/offer";
    }


    //----------------------------------- create offer  ------

    @RequestMapping(value = "/create-offer", method = RequestMethod.GET)
    public String createOffer(Model model) {
        String flag = "offer";
        model.addAttribute("flag", flag);
        return "offer/create-offer";
    }

    //----------------------------------- one certain offer  ------
    @RequestMapping(value = "/edit-offer/{id}", method = RequestMethod.GET)
    public String getOffer(Model model, @PathVariable("id") String id) {

        Offer offer = new Offer();
        Profile profile = new Profile();

        try {
            profile = profilesService.findWholeProfileById(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in getOffer method trying receive profile");
        }

        try {
            offer = offersService.findOfferAndIncViews(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in getOffer method trying receive offer");
        }


        String properties = "";
        String categories = "";
        String imagesIds = "";
        ObjectMapper mapper = new ObjectMapper();


        try {
            properties = mapper.writeValueAsString(offer.getProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            categories = mapper.writeValueAsString(offer.getCategories());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            imagesIds = mapper.writeValueAsString(offer.getImagesIds());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String flag = "offer";
        model.addAttribute("flag", flag);
        model.addAttribute("imagesIds", imagesIds);
        model.addAttribute("properties", properties);
        model.addAttribute("categories", categories);
        model.addAttribute("profile", profile);
        model.addAttribute("offer", offer);

        return "offer/edit-offer";
    }

    //----------------------------------- test page  ------
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "test";
    }


}
