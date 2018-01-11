package ua.com.gup.mongo.composition.domain.complaint;

import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.complaint.CommonComplaint;

@Document(collection = ComplaintOffer.COLLECTION_NAME, language = "russian")
public class ComplaintOffer extends CommonComplaint {

    public static final String COLLECTION_NAME = "complaint";

}
