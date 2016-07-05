package ua.com.itproekt.gup.util;

import java.util.List;

public final class EntityPage<T> {
    private long totalEntities;
    private List<T> entities;

    public EntityPage(long totalEntities, List<T> entities) {
        this.totalEntities = totalEntities;
        this.entities = entities;
    }

    public long getTotalEntities() {
        return totalEntities;
    }

    public void setTotalEntities(long totalEntities) {
        this.totalEntities = totalEntities;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "EntityPage{" +
                "totalEntities=" + totalEntities +
                ", entities=" + entities +
                '}';
    }
}
