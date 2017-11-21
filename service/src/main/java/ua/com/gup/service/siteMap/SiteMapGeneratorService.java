package ua.com.gup.service.siteMap;

import ua.com.gup.service.siteMap.siteMapContent.UrlSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

/**
 * Service for generate site map xml.
 *
 * @author Kobylyatskyy Alexander
 */
public interface SiteMapGeneratorService {


    /**
     * Generate simple siteMap file.
     */
    UrlSet generateSiteMap(HttpServletRequest request, HttpServletResponse response)  throws JAXBException;

}
