package ua.com.gup.service.sitemap.content;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "urlset")
public class UrlSet {

    private List<Url> urlList;

    private String xmlns; // attribute
    private String xmlnsImage; // attribute

    public List<Url> getUrlList() {
        return urlList;
    }


    @XmlElement(name = "url")
    public void setUrlList(List<Url> urlList) {
        this.urlList = urlList;
    }

    public String getXmlns() {
        return xmlns;
    }

    @XmlAttribute
    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getXmlnsImage() {
        return xmlnsImage;
    }

    @XmlAttribute(name = "xmlns:image")
    public void setXmlnsImage(String xmlnsImage) {
        this.xmlnsImage = xmlnsImage;
    }
}
