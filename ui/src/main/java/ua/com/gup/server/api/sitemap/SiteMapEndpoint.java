package ua.com.gup.server.api.sitemap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.service.siteMap.SiteMapGeneratorService;
import ua.com.gup.service.siteMap.siteMapContent.UrlSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

/**
 * @author Victor Dvorak
 **/
@RestController
@RequestMapping("/api")
public class SiteMapEndpoint {
    private final Logger logger = LoggerFactory.getLogger(SiteMapEndpoint.class);
    @Autowired
    private SiteMapGeneratorService siteMapGeneratorService;

    @CrossOrigin
    @RequestMapping(value = "/sitemap", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity geSiteMap(HttpServletRequest request, HttpServletResponse response) throws JAXBException {
       /* if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_ADMIN.name())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert("", "forbidden", "User should be in role 'ROLE_ADMIN'")).body(null);
        }*/
        UrlSet siteMap = siteMapGeneratorService.generateSiteMap(request, response);
        return ResponseEntity.ok().body(siteMap);
    }
}
