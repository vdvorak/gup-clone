package ua.com.itproekt.gup.service.transportApiService;


public interface UkrPoshtaService {


    /**
     * @param barcode
     * @return
     */
    String tracking(String barcode);
}
