package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferContactInfoDTO implements Serializable{

    @ApiModelProperty(position = 0)
    private String contactName;
    @ApiModelProperty(position = 20, dataType="java.util.LinkedHashSet<String>", value = "['380501234567','380507654321']")
    private Set<String> phoneNumbers;

}
