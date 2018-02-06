package ua.com.gup.common.model.enumeration;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Victor Dvorak
 **/
@Getter
@ToString
public enum CommonBonusScenarios {
    REFERENCE_MANAGER("REFERENCE_MANAGER","add manager reference  in user"),
    BONUS_MONEY("BONUS_MONEY","add amount in bonus cash");

      private String scenario;
      private String description;

     CommonBonusScenarios(String scenario, String description) {
        this.scenario = scenario;
        this.description = description;
    }

}
