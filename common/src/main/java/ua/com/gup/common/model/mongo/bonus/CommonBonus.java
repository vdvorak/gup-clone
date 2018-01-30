package ua.com.gup.common.model.mongo.bonus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonBonusScenarios;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Victor Dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class CommonBonus implements Serializable {
    protected Boolean active;
    protected LocalDateTime createDate;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected Integer countUse;
    protected CommonBonusScenarios scenarios;

    public CommonBonus() {

    }
    public CommonBonus(Boolean active, LocalDateTime createDate, LocalDateTime startDate, LocalDateTime endDate, Integer countUse, CommonBonusScenarios scenarios) {
        this.active = active;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.countUse = countUse;
        this.scenarios = scenarios;
    }
}
