package ua.com.gup.repository.custom;


import org.springframework.data.mongodb.core.aggregation.Aggregation;
import ua.com.gup.domain.Offer;

import java.util.List;

public interface OfferRepositoryCustom {

    List<Offer> findAll(Aggregation aggregation);
}
