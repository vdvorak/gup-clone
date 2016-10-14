package ua.com.itproekt.gup.model.reservationSchedule;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;

@Document(collection = "reservationSchedule")
public class ReservationSchedule {

    @Id
    private String id;

    private LinkedHashMap<String, Long> points;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedHashMap<String, Long> getPoints() {
        return points;
    }

    public void setPoints(LinkedHashMap<String, Long> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "ReservationSchedule{" +
                "id='" + id + '\'' +
                ", points=" + points +
                '}';
    }
}
