package ua.com.itproekt.gup.service.transportApiService;


public interface UkrPoshtaService {


    /**
     * Return information about parcel of UkrPoshta
     *
     * @param barcode
     * @return
     */
    String tracking(String barcode);
}
