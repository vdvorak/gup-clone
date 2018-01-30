package ua.com.gup.rent.service.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryStatistic {
    private Long code;
    private Long count;
    private String title;

    public CategoryStatistic() {
    }

    public CategoryStatistic(Long code, Long count, String title) {
        this.code = code;
        this.count = count;
        this.title = title;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
