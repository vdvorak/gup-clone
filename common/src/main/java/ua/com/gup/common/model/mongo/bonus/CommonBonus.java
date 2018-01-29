package ua.com.gup.common.model.mongo.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author  Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class CommonBonus implements Serializable {
    protected Boolean active;
    protected ZonedDateTime createDate;
    protected ZonedDateTime startDate;
    protected ZonedDateTime endDate;
    protected Integer countUse;
    protected CommonBonusScenarios scenarios;

    public CommonBonus() {

    }
    public CommonBonus(CommonBonus commonBonus) {
        this.active = commonBonus.active;
        this.createDate = commonBonus.createDate;
        this.startDate = commonBonus.startDate;
        this.endDate = commonBonus.endDate;
        this.countUse = commonBonus.countUse;
        this.scenarios = commonBonus.scenarios;
    }
}
