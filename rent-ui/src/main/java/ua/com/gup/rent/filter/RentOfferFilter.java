package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.gup.common.model.filter.CommonOfferFilter;

import java.time.LocalDate;

@ApiModel(description = "Rent Offer filter model")
@Getter
@Setter
public class RentOfferFilter extends CommonOfferFilter {

    @ApiModelProperty(value = "Only one category (level not important)", position = 10)
    private Integer category;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty("start rent date (inclusive)")
    private LocalDate dtRentStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @ApiModelProperty("end rent date (inclusive)")
    private LocalDate dtRentEnd;

}
