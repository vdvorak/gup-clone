package ua.com.gup.rent.editor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ua.com.gup.rent.dto.rentobject.period.RentPeriodDTO;

import java.io.IOException;

@Component
public class RentObjectDTORentPeriodEditor extends CreateRentObjectDTOPropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (StringUtils.isEmpty(text)) {
            setValue(null);
        } else {

            try {
                setValue(mapper.readValue(text, RentPeriodDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
