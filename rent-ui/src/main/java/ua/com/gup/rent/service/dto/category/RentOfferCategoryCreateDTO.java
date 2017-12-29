package ua.com.gup.rent.service.dto.category;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryCreateDTO implements Serializable {
    private int parent;
    private boolean active;
    private String key;
    private String color;
    private Integer order;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> description = new HashMap<>();

}
