package ua.com.gup.server.service.impl.sitemap;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.offer.OfferModerationReport;
import ua.com.gup.server.service.OfferService;
import ua.com.gup.server.service.SiteMapGeneratorService;
import ua.com.gup.server.service.impl.sitemap.content.ChangeFreq;
import ua.com.gup.server.service.impl.sitemap.content.ImageMap;
import ua.com.gup.server.service.impl.sitemap.content.Url;
import ua.com.gup.server.service.impl.sitemap.content.UrlSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SiteMapGeneratorImpl implements SiteMapGeneratorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final String xmlns = "http://www.sitemaps.org/schemas/sitemap/0.9";
    private final String xmlnsImage = "http://www.google.com/schemas/sitemap-image/1.1";
    private final String host = "https://www.dev.gup.ua/";
    private final String imageHost = "https://www.dev.gup.ua/ui/rest/fileStorage/offers/photo/read/id/";
    private final String fileName = "sitemap.xml";

    @Autowired
    private OfferService offerService;


    @Override
    public UrlSet generateSiteMap(HttpServletRequest request, HttpServletResponse response) throws JAXBException {
        OfferModerationReport offerModerationReport = new OfferModerationReport();
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
        offerFilterOptions.setStatus(CommonStatus.ACTIVE);
        offerFilterOptions.setLastOfferModerationReport(offerModerationReport);
        List<Offer> offerList = offerService.findOffersWihOptions(offerFilterOptions).getEntities();
        UrlSet resultUrlSet = prepareResultSet(offerList);
        try {
            File file = new File(request.getContextPath() + "/" + fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(UrlSet.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //   jaxbMarshaller.marshal(resultUrlSet, file);
            logger.info("save file sitemap.xml to path" + file.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
            throw e;
        }
        return resultUrlSet;

    }

    private UrlSet prepareResultSet(List<Offer> offerList) {
        UrlSet resultUrlSet = new UrlSet();
        resultUrlSet.setXmlns(xmlns);
        resultUrlSet.setXmlnsImage(xmlnsImage);
        List<Url> urlList = new ArrayList<>();
        for (Offer offer : offerList) {
            Url url = new Url();
            url.setChangefreq(ChangeFreq.WEEKLY.toString());
            //check priority get bussiness
            Double lastPriority = ThreadLocalRandom.current().nextDouble(0.5D, 0.9D);
            DecimalFormat df = new DecimalFormat("0.0");
            url.setPriority(df.format(lastPriority).toString());
            url.setLoc(host + offer.getSeoUrl());
            urlList.add(url);
            String mainImage = offerService.getMainOfferImage(offer);
            if (!StringUtils.isEmpty(mainImage)) {
                ImageMap imageMap = new ImageMap();
                imageMap.setImageLoc(imageHost + mainImage + ".jpg?cachedSize=large");
                String offerTitle = offer.getTitle();
                if (!StringUtils.isEmpty(offerTitle)) {
                    imageMap.setImageTitle(offerTitle);
                    imageMap.setImageCaption(offerTitle);
                }
                url.setImageMap(imageMap);
            }
        }
        resultUrlSet.setUrlList(urlList);

        return resultUrlSet;
    }
}
