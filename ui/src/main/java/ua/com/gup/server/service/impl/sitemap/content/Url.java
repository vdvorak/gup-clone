package ua.com.gup.server.service.impl.sitemap.content;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"loc", "changefreq", "priority", "imageMap"})
public class Url {

    private String loc;
    private String changefreq;
    private String priority;

    private ImageMap imageMap;


    public String getLoc() {
        return loc;
    }

    @XmlElement
    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getChangefreq() {
        return changefreq;
    }

    @XmlElement
    public void setChangefreq(String changefreq) {
        this.changefreq = changefreq;
    }

    public String getPriority() {
        return priority;
    }

    @XmlElement
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public ImageMap getImageMap() {
        return imageMap;
    }

    @XmlElement(name = "image:image")
    public void setImageMap(ImageMap imageMap) {
        this.imageMap = imageMap;
    }
}
