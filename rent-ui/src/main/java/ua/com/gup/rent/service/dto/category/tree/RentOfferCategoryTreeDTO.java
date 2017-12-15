package ua.com.gup.rent.service.dto.category.tree;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;

import java.io.Serializable;
import java.util.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryTreeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private String color;
    private Integer order;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> description = new HashMap<>();
    private SortedSet<RentOfferCategoryAttributeDTO> attrs;
    private SortedSet<RentOfferCategoryTreeDTO> children;
    private boolean privateAttr;

    public RentOfferCategoryTreeDTO(String lang) {
        attrs = new TreeSet<RentOfferCategoryAttributeDTO>(getCategoryAttributeDTOComparator(lang));
        children = new TreeSet<RentOfferCategoryTreeDTO>(getCategoryTreeDTOComparator(lang));
    }

    public static Comparator<RentOfferCategoryTreeDTO> getCategoryTreeDTOComparator(String lang) {
        return Comparator.comparing(RentOfferCategoryTreeDTO::getOrder);
    }

    private Comparator<RentOfferCategoryAttributeDTO> getCategoryAttributeDTOComparator(String lang) {
        return Comparator.comparing(RentOfferCategoryAttributeDTO::getCategory_sort);
    }
}
