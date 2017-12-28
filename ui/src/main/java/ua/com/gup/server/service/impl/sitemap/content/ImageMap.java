package ua.com.gup.server.service.impl.sitemap.content;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"imageLoc", "imageCaption", "imageTitle"})
public class ImageMap {

    private String imageLoc;
    private String imageCaption;
    private String imageTitle;

    public String getImageLoc() {
        return imageLoc;
    }

    @XmlElement(name = "image:loc")
    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }

    public String getImageCaption() {
        return imageCaption;
    }

    @XmlElement(name = "image:caption")
    public void setImageCaption(String imageCaption) {
        this.imageCaption = imageCaption;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    @XmlElement(name = "image:title")
    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }


}
