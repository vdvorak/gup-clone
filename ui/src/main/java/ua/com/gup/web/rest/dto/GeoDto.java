package ua.com.gup.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoDto {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "level")
    private Integer level;
    @JsonProperty(value = "parent")
    private Long parent;
    @JsonProperty(value = "name")
    private NameDto name;
    @JsonProperty(value = "type")
    private TypeDto type;

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

    public NameDto getName() {
        return name;
    }

    public void setName(NameDto name) {
        this.name = name;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
    }
}
