package ua.com.gup.search.model.filter.rent;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class AttributeFilter {

    private String key;
    private String vals;

    public List<String> getValuesAsList() {
        return Arrays.asList(vals.split(","));
    }
}
