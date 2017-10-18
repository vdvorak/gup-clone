package ua.com.gup.mongo.model.complaint;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

public class ComplaintOfferDescription {

    @NotNull
    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    private String description;

    private ZonedDateTime createdDate = ZonedDateTime.now();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    public String toString() {
        return "ComplaintOfferDescription{" +
                "description='" + description + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
