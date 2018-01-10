package ua.com.gup.rent.model.mongo.complaint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.complaint.CommonComplaint;

@Document(collection = RentComplaintOffer.COLLECTION_NAME, language = "russian")
@Getter
@Setter
@ToString
public class RentComplaintOffer extends CommonComplaint {

    public static final String COLLECTION_NAME = "rent.complaint";


}
