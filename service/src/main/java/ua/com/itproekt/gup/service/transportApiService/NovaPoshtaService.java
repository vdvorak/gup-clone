package ua.com.itproekt.gup.service.transportApiService;


import ua.com.itproekt.gup.service.transportApiService.novaPoshta.requestModels.MethodProperties;

public interface NovaPoshtaService {

    /**
     * Receive tracking information about about several parcels
     *
     * @param methodProperties
     * @return
     */
    String tracking(MethodProperties methodProperties);
}
