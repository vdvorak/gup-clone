package ua.com.gup.search.model.filter.rent;


import lombok.Data;

@Data
public class NumericAttributeFilter {

    private String key;
    private Double from;
    private Double to;

}
