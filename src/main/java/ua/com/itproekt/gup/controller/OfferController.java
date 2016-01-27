package ua.com.itproekt.gup.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.dao.offers.OfferRepository;
import ua.com.itproekt.gup.model.offer.Address;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * Created by Optical Illusion on 06.11.2015.
 */
@Controller
public class OfferController {
    @Autowired
    OffersService offersService;
    @Autowired
    ProfilesService profilesService;
    @Autowired
    private OfferRepository offerRepository;


    //----------------------------------- all offers  ------
    @RequestMapping(value = "/offers-old", method = RequestMethod.GET)
    public String getOffers() {
        return "redirect:offers-old/1";
    }

    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public String getAllOffers() {
        return "offers";
    }

    @RequestMapping(value = "/offers-old/{page}", method = RequestMethod.GET)
    public String getOffersPerPage(Model model, HttpServletRequest request,
                                   @PathVariable("page") Integer page,
                                   @RequestParam(name="minPrice",required = false,defaultValue = "0") Integer minPrice,
                                   @RequestParam(name="maxPrice",required = false,defaultValue = "0") Integer maxPrice) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setLimit(5);
        offerFilterOptions.setSkip((page - 1) * 5);
        String search = "";
        String chosenRegion = "";
        String chosenCity = "";
        try {
            if(request!=null && request.getQueryString()!=null && request.getQueryString().contains("&")) {
                search = URLDecoder.decode(request.getQueryString().split("&")[2].substring(7), "UTF-8");
                chosenRegion = URLDecoder.decode(request.getQueryString().split("&")[3].substring(13), "UTF-8");
                chosenCity = URLDecoder.decode(request.getQueryString().split("&")[4].substring(11), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (minPrice != 0)  offerFilterOptions.setFromPrice(minPrice);
        if (maxPrice != 0) offerFilterOptions.setToPrice(maxPrice);
        if (!search.equals("")) offerFilterOptions.setSearchField(search);
        Address address = new Address();
        if (!chosenRegion.equals("")) address.setArea(chosenRegion);
        if (!chosenCity.equals(""))  address.setCity(chosenCity);
        offerFilterOptions.setAddress(address);
        EntityPage<Offer> responseOffers = offerRepository.findOffersWihOptions(offerFilterOptions);
        model.addAttribute("offers", responseOffers);
        model.addAttribute("pageNumber", page);
        if (minPrice != 0) model.addAttribute("minPrice", minPrice);
        if (maxPrice != 0) model.addAttribute("maxPrice", maxPrice);
        if (!search.equals("")) model.addAttribute("search", search);
        if (chosenRegion.equals("")) {
            model.addAttribute("chosenRegion", "Выберите область");
        }else{
            model.addAttribute("chosenRegion", chosenRegion);
        }
        if (chosenCity.equals("")) {
            model.addAttribute("chosenCity", "Выберите город");
        }else{
            model.addAttribute("chosenCity", chosenCity);
        }
        model.addAttribute("pages", Math.round(responseOffers.getTotalEntities() % 5 == 0 ? responseOffers.getTotalEntities() / 5 : responseOffers.getTotalEntities() / 5 + 1));
        System.err.println("объявлений: " + responseOffers.getTotalEntities());
        System.err.println("страниц: " + Math.round(responseOffers.getTotalEntities() % 5 == 0 ? responseOffers.getTotalEntities() / 5 : responseOffers.getTotalEntities() / 5 + 1));
        System.err.println("minPrice: " + minPrice);
        System.err.println("maxPrice: " + maxPrice);
        System.err.println("search: " + search);
        System.err.println("chosenRegion: " + chosenRegion);
        System.err.println("chosenCity: " + chosenCity);
        System.err.println("URL: " + request.getQueryString());
        return "offer/offers-old";
    }

    //----------------------------------- one certain offer  ------
    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    public String offer(Model model, @PathVariable("id") String id) {

        Offer offer = new Offer();
        try {
            offer = offersService.findOfferAndIncViews(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("offer", offer);
        return "offer/offer-old";
    }


    //----------------------------------- create offer  ------
    @RequestMapping(value = "/create-offer", method = RequestMethod.GET)
    public String createOffer(Model model) {
        return "offer/create-offer";
    }

    //----------------------------------- one certain offer  ------
    @RequestMapping(value = "/edit-offer/{id}", method = RequestMethod.GET)
    public String getOffer(Model model, @PathVariable("id") String id) {

        Offer offer = new Offer();
        Profile profile = new Profile();

        try {
            profile = profilesService.findById(id);
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
