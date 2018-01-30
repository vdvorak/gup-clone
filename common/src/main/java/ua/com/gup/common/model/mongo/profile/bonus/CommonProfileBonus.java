package ua.com.gup.common.model.mongo.profile.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class CommonProfileBonus implements Serializable {

    protected Boolean active;

    protected ZonedDateTime createDate;

    protected ZonedDateTime startDate;

    protected ZonedDateTime endDate;

    protected Integer countUse;

    protected CommonBonusScenarios scenarios;

    public CommonProfileBonus() {

    }
    public CommonProfileBonus(Boolean active, ZonedDateTime createDate, ZonedDateTime startDate, ZonedDateTime endDate, Integer countUse, CommonBonusScenarios scenarios) {
        this.active = active;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countUse = countUse;
        this.scenarios = scenarios;
    }
}
