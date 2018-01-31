package ua.com.gup.rent.model.mongo.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;
import ua.com.gup.common.model.mongo.profile.bonus.CommonProfileBonus;
import ua.com.gup.common.model.object.ObjectType;

import java.time.ZonedDateTime;

/**
 * @author Victor Dvorak
 **/
@Document(collection = ObjectType.BONUS_SCENARIOS)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
@EqualsAndHashCode(of = {"name", "code"})
public class RentOfferProfileBonus extends CommonProfileBonus {
    @Id
    String id;
    @Indexed(unique = true)
    private String name;
    @Indexed(unique = true)
    private String code;
    @CreatedBy
    private GupLoggedUser createdByUser;

    public RentOfferProfileBonus() {
    }

    public RentOfferProfileBonus(String name, String code, CommonBonusScenarios scenarios, ZonedDateTime startDate, ZonedDateTime endDate, Integer countUse, Boolean active) {
        super(active, ZonedDateTime.now(), startDate, endDate, countUse, scenarios);
        this.name = name;
        this.code = code;
    }

}
