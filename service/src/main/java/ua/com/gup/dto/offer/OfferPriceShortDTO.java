package ua.com.gup.dto.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import ua.com.gup.common.model.category.attribute.OfferCategoryAttributeValue;

import java.util.Map;


@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferPriceShortDTO extends OfferPriceDTO {

    private Map<String, String> title;

    private OfferCategoryAttributeValue selected;

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }

    public OfferCategoryAttributeValue getSelected() {
        return selected;
    }

    public void setSelected(OfferCategoryAttributeValue selected) {
        this.selected = selected;
    }


}
