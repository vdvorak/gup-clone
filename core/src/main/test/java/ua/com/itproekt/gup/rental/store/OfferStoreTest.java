package ua.com.itproekt.gup.rental.store;

import ua.com.itproekt.gup.rental.item.ItemType;
import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.staff.Customer;
import ua.com.itproekt.gup.rental.transaction.RentalTransaction;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.*;

public class OfferStoreTest {

    private OfferStore offerStore;

    @Before
    public void setUp() throws Exception {
        /** We will check test based on predefined fake data **/
        offerStore = new OfferStore();
    }

    @Test
    public void testAddOffer() throws Exception {
        for (ItemType itemType : ItemType.values()) {
            String newTitle = "TestOffer" + itemType.name();
            offerStore.addOffer(newTitle, itemType);

            for (Offer offer : offerStore.getOffers()) {
                if (offer.getTitle().equals(newTitle)) {
                    assertNotNull(offer);
                    assertEquals(newTitle, offer.getTitle());
                    break;
                }
            }
        }
    }

    @Test
    public void testRemoveOffer() throws Exception {
        Offer offerToRemove = getFirstAvailableOffer();
        String offerToRemoveTitle = offerToRemove.getTitle();

        if (offerToRemove != null)
            offerStore.removeOffer(offerToRemove);

        if (!offerStore.getOffers().contains(offerToRemove))
            offerToRemove = null;

        assertNull(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offer.should.be.not.existed", offerToRemoveTitle), offerToRemove);
    }

    private Offer getFirstAvailableOffer() {
        if (offerStore.getAvailableOffers().size() > 0)
            return offerStore.getAvailableOffers().get(0);

        return null;
    }

    @Test
    public void testChangeOfferType() throws Exception {
        Offer offerToChange = getFirstAvailableOffer();
        ItemType newItemType = ItemType.NEW_RELEASES;

        if (offerToChange != null)
            offerStore.changeOfferType(offerToChange, newItemType);

        Offer changedOffer = findOffer(offerToChange.getTitle());

        assertNotNull(changedOffer);
        assertEquals(newItemType, changedOffer.getType());
    }


    private Offer findOffer(String title) {
        for (Offer offer : offerStore.getOffers()) {
            if (offer.getTitle().equals(title)) {
                return offer;
            }
        }

        return null;
    }

    @Test
    public void testGetOffers() throws Exception {
        assertNotNull(offerStore.getOffers());
        assertTrue(offerStore.getOffers().size() > 0);
    }

    @Test
    public void testGetAvailableOffers() throws Exception {
        assertNotNull(offerStore.getAvailableOffers());
        assertTrue(offerStore.getAvailableOffers().size() > 0);
    }

    @Test
    public void testRentOffer() throws Exception {
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
        Offer offer;

        if (useBonus) {
            assertTrue(rentDays * RentalTransaction.BONUS_CHARGE_PER_DIEM <= customerBonusPoints);
            offer = getAvailableNewReleaseOffer();
            if (offer != null) {
                assertEquals(ItemType.NEW_RELEASES, offer.getType());
                offerStore.rentOffer(newCustomer, offer, rentDays, useBonus);
            }
        } else {
            offer = getFirstAvailableOffer();
            if (offer != null)
                offerStore.rentOffer(newCustomer, offer, rentDays, useBonus);
        }

        if (offer != null) {
            for (RentalTransaction rentalTransaction : offerStore.getRentalTransactions()) {
                if (rentalTransaction.getCustomer().getName().equals(newCustomer.getName())) {
                    assertEquals(offer.getTitle(), rentalTransaction.getItem().getTitle());
                    offer.setAvailability(false);
                    assertEquals(offer.isAvailable(), rentalTransaction.getItem().isAvailable());

                    if (useBonus) {
                        newCustomer.chargeBonus(rentalTransaction.getItem().getType());
                        assertEquals(newCustomer.getBonus(), rentalTransaction.getCustomer().getBonus());
                    }
                    break;
                }
            }
        }
    }

    private Offer getAvailableNewReleaseOffer() {
        for (Offer offer : offerStore.getAvailableOffers())
            if (offer.getType() == ItemType.NEW_RELEASES)
                return offer;

        return null;
    }

    @Test
    public void testGetRentalTransactions() throws Exception {
        assertNotNull(offerStore.getRentalTransactions());
        assertTrue(offerStore.getRentalTransactions().size() > 0);
    }

    @Test
    public void testGetRentalTransactionsForCustomer() throws Exception {
        String name = "Kauri";
        List<RentalTransaction> customerTransactions = findCustomerRentalTransactions(name);

        assertNotNull(customerTransactions);
        assertEquals(1, customerTransactions.size());
    }

    private List<RentalTransaction> findCustomerRentalTransactions(String searchedName) {
        List<RentalTransaction> customerTransactions = new LinkedList<>();

        for (RentalTransaction transaction : offerStore.getRentalTransactions())
            if (transaction.getCustomer().getName().equals(searchedName))
                customerTransactions.add(transaction);

        return customerTransactions;
    }

    @Test
    public void testGetCustomer() throws Exception {
        String name = "Kauri";
        Customer customer = findCustomer(name);

        assertNotNull(customer);
        assertEquals(name, customer.getName());
    }

    private Customer findCustomer(String searchedName) {
        for (RentalTransaction transaction : offerStore.getRentalTransactions())
            if (transaction.getCustomer().getName().equals(searchedName))
                return transaction.getCustomer();

        return null;
    }
}