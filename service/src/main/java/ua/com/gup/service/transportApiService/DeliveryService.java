package ua.com.itproekt.gup.service.transportApiService;


public interface DeliveryService {


    /**
     * Return information about parcel od Delivery transoprt compan. Format - JSON
     *
     * @param trackNumber
     * @return
     */
    String tracking(String trackNumber);


}
