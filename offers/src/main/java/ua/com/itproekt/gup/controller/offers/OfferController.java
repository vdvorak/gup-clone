//package ua.com.itproekt.gup.controller.offers;
//
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import ua.com.itproekt.gup.model.offer.Offer;
//import ua.com.itproekt.gup.model.offer.Property;
//import ua.com.itproekt.gup.service.offers.OffersService;
//import ua.com.itproekt.gup.service.profile.ProfilesService;
//import ua.com.itproekt.gup.util.SeoMetaTags;
//import ua.com.itproekt.gup.util.SeoUtils;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class OfferController {
//    @Autowired
//    OffersService offersService;
//    @Autowired
//    ProfilesService profilesService;
//
//
//    //----------------------------------- all offers  ------
//    @RequestMapping(value = "/offers", method = RequestMethod.GET)
//    public String getOffers(Model model) {
//        String flag = "offer-all";
//        model.addAttribute("flag", flag);
//        return "offer/offer-all";
//    }
//
//    //----------------------------------- one certain offer with SEO key  ------
//    @RequestMapping(value = "/obyavlenie/{seoUrl}", method = RequestMethod.GET)
//    public String getOneOfferBySeoKey(Model model, @PathVariable("seoUrl") String seoUrl) {
//
//        String offerSeoKey = SeoUtils.getKey(seoUrl);
//        String flag = "offer";
//
//
//        Offer offer = offersService.findBySeoKey(offerSeoKey);
//
//        SeoMetaTags seoMetaTags = new SeoMetaTags()
//                .setMainImgId(getFistImgFromOffer(offer.getImagesIds()))
//                .setTitle(offer.getTitle())
//                .setSeoCategory(offer.getSeoCategory())
//                .setSeoAdress(getAdressFromOffer(offer))
//                .setSeoUrl(seoUrl)
//                .setPrice(getPriceFromOffer(offer))
//                .setCurrency(getCurrencyFromOffer(offer));
//
//        model.addAttribute("seoMetaTags", seoMetaTags);
//        model.addAttribute("flag", flag);
//        model.addAttribute("offerId", offer.getId());
//        return "offer/offer";
//    }
//
//
//    //----------------------------------- create offer  ------
//
//    @RequestMapping(value = "/create-offer", method = RequestMethod.GET)
//    public String createOffer(Model model) {
//        String flag = "offer";
//        model.addAttribute("flag", flag);
//        return "offer/create-offer";
//    }
//
//
//    //----------------------------------- edit offer  ------
//    @RequestMapping(value = "/edit-offer/{seoUrl}", method = RequestMethod.GET)
//    public String getOfferWithSeo(Model model, @PathVariable("seoUrl") String seoUrl) {
//
//        String offerSeoKey = SeoUtils.getKey(seoUrl);
//
//        Offer offer = new Offer();
//
//        try {
//            offer = offersService.findBySeoKey(offerSeoKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Exception in getOffer method trying receive offer with seoUrl: " + seoUrl);
//        }
//
//
//        String properties = "";
//        String categories = "";
//        String imagesIds = "";
//        ObjectMapper mapper = new ObjectMapper();
//
//
//        try {
//            properties = mapper.writeValueAsString(offer.getProperties());
//            categories = mapper.writeValueAsString(offer.getCategories());
//            imagesIds = mapper.writeValueAsString(offer.getImagesIds());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        String flag = "offer";
//        model.addAttribute("flag", flag);
//        model.addAttribute("imagesIds", imagesIds);
//        model.addAttribute("properties", properties);
//        model.addAttribute("categories", categories);
//        model.addAttribute("offer", offer);
//
//        return "offer/edit-offer";
//    }
//
//    private String getFistImgFromOffer(Map<String, String> imgsMap) {
//        for (String s : imgsMap.keySet()) {
//            if (imgsMap.get(s).equals("pic1")) {
//                return s;
//            }
//        }
//        return "";
//    }
//
//    private String getAdressFromOffer(Offer offer) {
//        String result = "Украина";
//        if (offer.getAddress().getArea() != null) {
//            result = offer.getAddress().getArea();
//        }
//        if (offer.getAddress().getCity() != null) {
//            result = offer.getAddress().getCity();
//        }
//        return result;
//    }
//
//    private String getPriceFromOffer(Offer offer) {
//
//        List<Property> propertyList = offer.getProperties();
//
//        for (Property property : propertyList) {
//            if (property.getKey().equals("price")) {
//
//                if (property.getValue().equals("price")) {
//                    if (offer.getPrice() != null) {
//
//                        return String.valueOf(offer.getPrice());
//                    }
//                }
//
//                switch (property.getValue()) {
//                    case "exchange":
//                        return "Обмен";
//                    case "free":
//                        return "Бесплатно";
//                    case "arranged":
//                        return "Договорная цена";
//                }
//            }
//        }
//        return "";
//    }
//
//
//    private String getCurrencyFromOffer(Offer offer) {
//        if (offer.getCurrency() != null) {
//            switch (offer.getCurrency()) {
//                case UAH:
//                    return " грн.";
//                case USD:
//                    return " дол.";
//                case EUR:
//                    return " евро";
//                default:
//                    return "";
//            }
//        }
//        return "";
//    }
//}
