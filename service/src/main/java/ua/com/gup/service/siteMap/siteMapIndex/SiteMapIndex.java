package ua.com.gup.service.siteMap.siteMapIndex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Root object for site map index.
 *
 * @author Kobylyatskyy Alexander
 */
@XmlRootElement(name = "sitemapindex")
public class SiteMapIndex {
    List<SiteMap> siteMapList;

    public List<SiteMap> getSiteMapList() {
        return siteMapList;
    }

    @XmlElement(name = "sitemap")
    public void setSiteMapList(List<SiteMap> siteMapList) {
        this.siteMapList = siteMapList;
    }


    @Override
    public String toString() {
        return "SiteMapIndex{" +
                "siteMapList=" + siteMapList +
                '}';
    }
}
