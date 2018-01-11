package ua.com.gup.rent.model.mongo.complaint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.rent.model.rent.complaint.RentComplaintInitiator;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = RentComplaintOffer.COLLECTION_NAME, language = "russian")
@Getter
@Setter
@ToString
public class RentComplaintOffer implements Serializable {

    public static final String COLLECTION_NAME = "rent.complaint";

    @Id
    private String id;

    private RentComplaintInitiator initiator;

    @Indexed
    private String offerId;

    private String description;

    private List<RentComplaintOfferType> types;

    @Indexed
    private RentComplaintOfferStatus status;

    private Date createdDate; //private ZonedDateTime createdDate;

    private Date lastModifiedDate; //private ZonedDateTime lastModifiedDate;

    public RentComplaintOffer(){
        createdDate = new Date(); //createdDate = ZonedDateTime.now();
        lastModifiedDate = new Date(); //lastModifiedDate = ZonedDateTime.now();
    }
    public void addType(RentComplaintOfferType type) {
        types.add(type);
    }
    public void updateLastModifiedDate() {
        lastModifiedDate =  new Date(); //this.lastModifiedDate =  ZonedDateTime.now();
    }

}
