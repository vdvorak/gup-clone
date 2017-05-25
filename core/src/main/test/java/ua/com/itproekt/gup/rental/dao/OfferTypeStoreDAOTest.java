package ua.com.itproekt.gup.rental.dao;

import ua.com.itproekt.gup.rental.algorithm.type.Types;
import ua.com.itproekt.gup.rental.algorithm.type.OfferType;
import ua.com.itproekt.gup.rental.model.OfferStore;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.algorithm.staff.Customer;
import ua.com.itproekt.gup.rental.domain.RentalTransaction;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.*;


public class OfferTypeStoreDAOTest {

    private OfferStoreDAO offerStoreDAO;
    private OfferStore offerStore;

    @Before
    public void setUp() throws Exception {
        String[] fakeOfferTitles = {"Башенный кран 6 тонн 52 метра", "Продам Toyota Rav 4 2016год", "Toyota Land Cruiser 200 БРОНЬ B6 клас", "Cisco Linksys SPA962 IP Телефон 6 линий"},
                fakeCustomerNames = {"58efaf104c8e83648c2f1e1e", "58f64c754c8e83648c2f1f34"};
        offerStore = new OfferStore(fakeOfferTitles, fakeCustomerNames);

        offerStoreDAO = new OfferStoreDAO(offerStore);
    }

    @Test
    public void testAddOffer() throws Exception {
        for (Types types : Types.values()) {
            String newTitle = "TestOffer" + types.name();
            offerStoreDAO.addOffer(newTitle, types);

            for (OfferType offerType : offerStoreDAO.getOffers()) {
                if (offerType.getTitle().equals(newTitle)) {
                    assertNotNull(offerType);
                    assertEquals(newTitle, offerType.getTitle());
                    break;
                }
            }
        }
    }

    @Test
    public void testRemoveOffer() throws Exception {
        OfferType offerTypeToRemove = getFirstAvailableOffer();
        String offerToRemoveTitle = offerTypeToRemove.getTitle();

        if (offerTypeToRemove != null)
            offerStoreDAO.removeOffer(offerTypeToRemove);

        if (!offerStoreDAO.getOffers().contains(offerTypeToRemove))
            offerTypeToRemove = null;

        assertNull(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offer.should.be.not.existed", offerToRemoveTitle), offerTypeToRemove);
    }

    private OfferType getFirstAvailableOffer() {
        if (offerStoreDAO.getAvailableOffers().size() > 0)
            return offerStoreDAO.getAvailableOffers().get(0);

        return null;
    }

    @Test
    public void testChangeOfferType() throws Exception {
        OfferType offerTypeToChange = getFirstAvailableOffer();
        Types newTypes = Types.NEW_RELEASES;

        if (offerTypeToChange != null)
            offerStoreDAO.changeOfferType(offerTypeToChange, newTypes);

        OfferType changedOfferType = findOffer(offerTypeToChange.getTitle());

        assertNotNull(changedOfferType);
        assertEquals(newTypes, changedOfferType.getType());
    }


    private OfferType findOffer(String title) {
        for (OfferType offerType : offerStoreDAO.getOffers()) {
            if (offerType.getTitle().equals(title)) {
                return offerType;
            }
        }

        return null;
    }

    @Test
    public void testGetOffers() throws Exception {
        assertNotNull(offerStoreDAO.getOffers());
        assertTrue(offerStoreDAO.getOffers().size() > 0);
    }

    @Test
    public void testGetAvailableOffers() throws Exception {
        assertNotNull(offerStoreDAO.getAvailableOffers());
        assertTrue(offerStoreDAO.getAvailableOffers().size() > 0);
    }


    @Test
    public void testCheckRentOffer() throws Exception {
        checkRentOffer(10, 250, true);
        checkRentOffer(10, 1000, true);
        checkRentOffer(10, 1, false);
        checkRentOffer(10, 1000, false);

        /** Uncomment one of the line below to test rentOffer method with wrong parameters **/
//        checkRentOffer(10, 1, true);      // Expected result : Failed
//        checkRentOffer(0, 1, false);      // Expected result : Failed
    }

    private void checkRentOffer(int rentDays, int customerBonusPoints, boolean useBonus) {

        assertTrue(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("days.less.one"), rentDays > 0);

        Customer newCustomer = new Customer("TestCustomer" + customerBonusPoints, customerBonusPoints);
        OfferType offerType;

        if (useBonus) {
            assertTrue(rentDays * RentalTransaction.BONUS_CHARGE_PER_DIEM <= customerBonusPoints);
            offerType = getAvailableNewReleaseOffer();
            if (offerType != null) {
                assertEquals(Types.NEW_RELEASES, offerType.getType());
                offerStoreDAO.rentOffer(newCustomer, offerType, rentDays, useBonus);
            }
        } else {
            offerType = getFirstAvailableOffer();
            if (offerType != null)
                offerStoreDAO.rentOffer(newCustomer, offerType, rentDays, useBonus);
        }

        if (offerType != null) {
            for (RentalTransaction rentalTransaction : offerStoreDAO.getRentalTransactions()) {
                if (rentalTransaction.getCustomer().getName().equals(newCustomer.getName())) {
                    assertEquals(offerType.getTitle(), rentalTransaction.getItem().getTitle());
                    offerType.setAvailability(false);
                    assertEquals(offerType.isAvailable(), rentalTransaction.getItem().isAvailable());

                    if (useBonus) {
                        newCustomer.chargeBonus(rentalTransaction.getItem().getType());
                        assertEquals(newCustomer.getBonus(), rentalTransaction.getCustomer().getBonus());
                    }
                    break;
                }
            }
        }
    }

    private OfferType getAvailableNewReleaseOffer() {
        for (OfferType offerType : offerStoreDAO.getAvailableOffers())
            if (offerType.getType() == Types.NEW_RELEASES)
                return offerType;

        return null;
    }


    @Test
    public void testGetRentalTransactions() throws Exception {
        assertNotNull(offerStoreDAO.getRentalTransactions());
        assertTrue(offerStoreDAO.getRentalTransactions().size() > 0);
    }



    @Test
    public void testFindCustomerRentalTransactions() throws Exception {
        String name = "58f64c754c8e83648c2f1f34";
        List<RentalTransaction> customerTransactions = findCustomerRentalTransactions(name);

        assertNotNull(customerTransactions);
        assertEquals(1, customerTransactions.size());
    }

    private List<RentalTransaction> findCustomerRentalTransactions(String searchedName) {
        List<RentalTransaction> customerTransactions = new LinkedList<>();

        for (RentalTransaction transaction : offerStoreDAO.getRentalTransactions())
            if (transaction.getCustomer().getName().equals(searchedName))
                customerTransactions.add(transaction);

        return customerTransactions;
    }

    @Test
    public void testFindCustomer() throws Exception {
        String name = "58efaf104c8e83648c2f1e1e";
        Customer customer = findCustomer(name);

        assertNotNull(customer);
        assertEquals(name, customer.getName());
    }

    private Customer findCustomer(String searchedName) {
        for (RentalTransaction transaction : offerStoreDAO.getRentalTransactions())
            if (transaction.getCustomer().getName().equals(searchedName))
                return transaction.getCustomer();

        return null;
    }
}