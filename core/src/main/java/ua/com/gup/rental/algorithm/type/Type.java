package ua.com.itproekt.gup.rental.algorithm.type;

public class Type {

    private String title;
    private boolean availability;
    private Types type;

    public Type(String title, Types type) {
        this.title = title;
        this.availability = true;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }
}
