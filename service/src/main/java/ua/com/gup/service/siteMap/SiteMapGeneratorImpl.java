package ua.com.gup.service.siteMap;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.offer.OfferModerationReport;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.service.offers.OffersService;
import ua.com.gup.service.siteMap.siteMapContent.ChangeFreq;
import ua.com.gup.service.siteMap.siteMapContent.ImageMap;
import ua.com.gup.service.siteMap.siteMapContent.Url;
import ua.com.gup.service.siteMap.siteMapContent.UrlSet;
import ua.com.gup.service.siteMap.siteMapIndex.SiteMap;
import ua.com.gup.service.siteMap.siteMapIndex.SiteMapIndex;

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

    // FixMe need to be changed
    private final String MAIN_SITEMAP_DESTINATION = "C:\\My Downloads\\file.xml";

    @Autowired
    OffersService offersService;


    @Override
    public void generateSiteMap() {


//        List<Url> urlList = new ArrayList<>();


        // we can show only offers which have Complete status (approve by moderators)
        OfferModerationReport offerModerationReport = new OfferModerationReport();
        //todo vdvorak
        //offerModerationReports.setModerationStatus(ModerationStatus.COMPLETE);

        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        //todo vdvorak
        //offerFilterOptions.setActive(true);
        //offerFilterOptions.setDeleted(false);

        offerFilterOptions.setLastOfferModerationReport(offerModerationReport);



        List<Offer> offerList = offersService.findOffersWihOptions(offerFilterOptions).getEntities();


        // Посчитать кол-во объявлений. Если до 50 000 - делать обычный siteMap, если больше - генерировать
        // обычный, в котором будут ссылки на каждый последующий (в котором не более 50 000 объявлений)


        UrlSet resultUrlSet = prepareResultSet(offerList);


        try {

            File file = new File(MAIN_SITEMAP_DESTINATION);

            JAXBContext jaxbContext = JAXBContext.newInstance(UrlSet.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(resultUrlSet, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }


    private UrlSet prepareResultSet(List<Offer> offerList) {
        UrlSet resultUrlSet = new UrlSet();
        resultUrlSet.setXmlns(xmlns);
        resultUrlSet.setXmlnsImage(xmlnsImage);

        List<Url> urlList = new ArrayList<>();

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

        return resultUrlSet;

    }

    private SiteMapIndex prepareSiteMapIndex(){
        SiteMapIndex siteMapIndex = new SiteMapIndex();
        List<SiteMap> siteMapList = new ArrayList<>();

        for (int i = 0; i < 40000; i++){
            SiteMap siteMap = new SiteMap();
            siteMap.setLoc("azazzaza");
            siteMapList.add(siteMap);
        }


        siteMapIndex.setSiteMapList(siteMapList);
        return siteMapIndex;

    }


}
