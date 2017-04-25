package ua.com.gup.domain;


public class Attribute<V> {

    private String key;

    private V value;
    public Attribute() {
    }
    public Attribute(String key, V value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attribute<?> attribute = (Attribute<?>) o;

        return key != null ? key.equals(attribute.key) : attribute.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
