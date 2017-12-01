package ua.com.gup.service.sitemap;

import ua.com.gup.service.sitemap.content.UrlSet;

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
     * Generate simple sitemap file.
     */
    UrlSet generateSiteMap(HttpServletRequest request, HttpServletResponse response) throws JAXBException;

}
