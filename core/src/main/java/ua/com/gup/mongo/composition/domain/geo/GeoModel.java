package ua.com.gup.mongo.composition.domain.geo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.com.gup.mongo.model.geo.Common;

@Document(collection = "geo")
public class GeoModel {

    @Id
    @Field(value = "_id")
    private Long id;
    @Field(value = "level")
    private Integer level;
    @Field(value = "parent")
    private Long parent;
    @Field(value = "ancestors")
    private Long[] ancestors;
    @Field(value = "name")
    private Common name;
    @Field(value = "type")
    private Common type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long[] getAncestors() {
        return ancestors;
    }

    public void setAncestors(Long[] ancestors) {
        this.ancestors = ancestors;
    }

    public Common getName() {
        return name;
    }

    public void setName(Common common) {
        this.name = common;
    }

    public Common getType() {
        return type;
    }

    public void setType(Common type) {
        this.type = type;
    }
}
