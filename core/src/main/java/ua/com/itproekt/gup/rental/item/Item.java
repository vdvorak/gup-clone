package ua.com.itproekt.gup.rental.item;

public class Item {

    private String title;
    private boolean availability;
    private ItemType type;

    public Item(String title, ItemType type) {
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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
