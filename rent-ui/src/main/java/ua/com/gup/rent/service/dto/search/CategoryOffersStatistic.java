package ua.com.gup.rent.service.dto.search;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryOffersStatistic {
    private Long category;
    private Long count;

    public CategoryOffersStatistic() {
    }

    public CategoryOffersStatistic(Long category, Long count) {
        this.category = category;
        this.count = count;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryOffersStatistic that = (CategoryOffersStatistic) o;

        if (!category.equals(that.category)) return false;
        return count.equals(that.count);
    }

    @Override
    public int hashCode() {
        int result = category.hashCode();
        result = 31 * result + count.hashCode();
        return result;
    }
}
