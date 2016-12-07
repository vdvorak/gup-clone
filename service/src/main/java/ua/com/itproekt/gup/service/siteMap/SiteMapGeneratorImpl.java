package ua.com.itproekt.gup.service.siteMap;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferModerationReports;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.siteMap.siteMapContent.ChangeFreq;
import ua.com.itproekt.gup.service.siteMap.siteMapContent.ImageMap;
import ua.com.itproekt.gup.service.siteMap.siteMapContent.Url;
import ua.com.itproekt.gup.service.siteMap.siteMapContent.UrlSet;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class SiteMapGeneratorImpl implements SiteMapGeneratorService {

    private final String xmlns = "http://www.sitemaps.org/schemas/sitemap/0.9";
    private final String xmlnsImage = "http://www.google.com/schemas/sitemap-image/1.1";
    private final String host = "http://gup.com.ua/";
    private final String imageHost = "http://gup.com.ua:8084/api/rest/fileStorage/offers/photo/read/id/";


    @Autowired
    OffersService offersService;


    @Override
    public void generateSiteMap() {


        UrlSet resultUrlSet = new UrlSet();
        List<Url> urlList = new ArrayList<>();
        resultUrlSet.setXmlns(xmlns);
        resultUrlSet.setXmlnsImage(xmlnsImage);

        // we can show only offers which have Complete status (approve by moderators)
        OfferModerationReports offerModerationReports = new OfferModerationReports();
        offerModerationReports.setModerationStatus(ModerationStatus.COMPLETE);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setActive(true);
        offerFilterOptions.setDeleted(false);

        offerFilterOptions.setOfferModerationReports(offerModerationReports);

        List<Offer> offerList = offersService.findOffersWihOptions(offerFilterOptions).getEntities();

        for (Offer offer : offerList) {

            Url url = new Url();

            url.setChangefreq(ChangeFreq.WEEKLY.toString());

            // ToDo priority must be calculated
            url.setPriority("1.0");
            url.setLoc(host + offer.getSeoUrl());

            urlList.add(url);

            String mainImage = offersService.getMainOfferImage(offer);

            if (StringUtils.isNotBlank(mainImage)) {
                ImageMap imageMap = new ImageMap();
                imageMap.setImageLoc(imageHost + mainImage + ".jpg?cachedSize=large");

                String offerTitle = offer.getTitle();

                if (StringUtils.isNotBlank(offerTitle)) {
                    imageMap.setImageTitle(offerTitle);
                    imageMap.setImageCaption(offerTitle);
                }
                url.setImageMap(imageMap);
            }
        }


        resultUrlSet.setUrlList(urlList);


        try {

            // ToDo need to be changed
            File file = new File("C:\\My Downloads\\file.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(UrlSet.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(resultUrlSet, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


}
