package ua.com.gup.common.model.mongo.manager;

import lombok.Data;

@Data
public class RelevancePhone {
    public String number;
    public Boolean relevance;

    public RelevancePhone(String number, Boolean relevance) {
        this.number = number;
        this.relevance = relevance;
    }
}
