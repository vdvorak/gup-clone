package ua.com.gup.service.offers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.offer.Rent2Repository;
import ua.com.gup.model.offer.Rent2;

@Service
public class RentsServiceImpl implements RentsService {

    @Autowired
    private Rent2Repository rentRepository;

    @Override
    public void create(Rent2 rent, String offerId) {
        rent.setId(offerId);
        rent.setOfferId(offerId);
        rent.setRents(new RentsRestore());
        rentRepository.create(rent);
    }

    @Override
    public void update(Rent2 rent) {
        rentRepository.update(rent);
    }

    @Override
    public boolean exist(String id) {
        return rentRepository.exist(id);
    }

    @Override
    public Rent2 findById(String id) {
        return rentRepository.findById(id);
    }

    @Override
    public Rent2 findByOfferId(String offerId) {
        return rentRepository.findByOfferId(offerId);
    }

    @Override
    public int delete(String id) {
        return rentRepository.delete(id);
    }
}
