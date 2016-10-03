package ua.com.itproekt.gup.service.transportApiService;


public interface NovaPoshtaService {

    /**
     * Receive tracking information about about shipping
     *
     * @param trackingNumber - track number
     * @param phoneNumber - phone number of sender or recipient
     * @return
     */
    String tracking(String trackingNumber, String phoneNumber);

    /**
     * Receive tracking information about about shipping
     *
     * @param trackingNumber - track number
     * @return
     */
    String tracking (String trackingNumber);
}