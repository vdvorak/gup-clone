package ua.com.gup.rent.editor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.com.gup.rent.service.dto.rent.offer.period.RentOfferPeriodDTO;

import java.io.IOException;

@Component
public class RentObjectDTORentPeriodEditor extends CreateRentObjectDTOPropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {

            try {
                setValue(mapper.readValue(text, RentOfferPeriodDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
