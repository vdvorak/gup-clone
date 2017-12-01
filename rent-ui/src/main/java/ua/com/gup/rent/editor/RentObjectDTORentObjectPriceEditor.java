package ua.com.gup.rent.editor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.com.gup.rent.service.dto.price.RentPriceDTO;

import java.io.IOException;

@Component
public class RentObjectDTORentObjectPriceEditor extends CreateRentObjectDTOPropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {
            try {
                setValue(mapper.readValue(text, RentPriceDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
