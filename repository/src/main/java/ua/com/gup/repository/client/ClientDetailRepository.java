package ua.com.gup.repository.client;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.gup.mongo.composition.domain.client.ClientDetail;

import java.util.Optional;

/**
 * @author Victor Dvorak
 * Repository for the oauth2_client  entity.
 **/

public interface ClientDetailRepository extends MongoRepository<ClientDetail, String> {
     Optional<ClientDetail>findByClientId(String clientId);
}
