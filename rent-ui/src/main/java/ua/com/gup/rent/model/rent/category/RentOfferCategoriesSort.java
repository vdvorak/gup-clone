package ua.com.gup.rent.model.rent.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of={"code_category,order_category"})
public class RentOfferCategoriesSort implements Serializable{
    private Integer code_category;
    private Integer order_category;
}

