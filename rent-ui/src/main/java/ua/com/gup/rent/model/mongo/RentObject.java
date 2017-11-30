package ua.com.gup.rent.model.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.rent.model.RentStatus;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "rent.object")
public class RentObject {

    public static final String CLASS_NAME = "ua.com.gup.rent.model.mongo.RentObject";

    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private String ownerId;
    //    private List<RentCategory> categories;
    private RentStatus status;
    private ua.com.gup.rent.model.RentPrice rentPrice;
    private List<ua.com.gup.rent.model.mongo.image.RentImageInfo> images;

    public RentObject() {
        createdDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ua.com.gup.rent.model.mongo.image.RentImageInfo> getImages() {
        return images;
    }

    public void setImages(List<ua.com.gup.rent.model.mongo.image.RentImageInfo> images) {
        this.images = images;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

//    public List<RentCategory> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<RentCategory> categories) {
//        this.categories = categories;
//    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }

    public ua.com.gup.rent.model.RentPrice getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(ua.com.gup.rent.model.RentPrice rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
