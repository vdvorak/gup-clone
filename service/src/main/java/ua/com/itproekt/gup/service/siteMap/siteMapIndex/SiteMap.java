package ua.com.itproekt.gup.service.siteMap.siteMapIndex;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Kobylyatskyy Alexander
 */
@XmlRootElement
public class SiteMap {
    private String loc;

    public String getLoc() {
        return loc;
    }

    @XmlElement(name = "loc")
    public void setLoc(String loc) {
        this.loc = loc;
    }
}
