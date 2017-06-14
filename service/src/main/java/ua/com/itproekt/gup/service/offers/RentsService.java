package ua.com.itproekt.gup.service.offers;


import ua.com.itproekt.gup.model.offer.Rent2;

public interface RentsService {
    public void create(Rent2 rent, String offerId);
    void update(Rent2 rent);
    boolean exist(String id);
    Rent2 findById(String id);
    Rent2 findByOfferId(String offerId);
    int delete(String id);
}
