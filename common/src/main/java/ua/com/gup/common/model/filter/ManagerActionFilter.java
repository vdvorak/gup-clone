package ua.com.gup.common.model.filter;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.gup.common.model.mongo.profile.manager.event.status.CallStatus;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ManagerActionFilter {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate date;
    @DateTimeFormat(pattern = "dd:MM:yyyy")
    public LocalDate dateStart;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public LocalDate dateEnd;
    @DateTimeFormat(pattern = "HH:mm")
    public LocalTime time;
    @DateTimeFormat(pattern = "HH:mm")
    public LocalTime timeStart;
    @DateTimeFormat(pattern = "HH:mm")
    public LocalTime timeEnd;


    public String clientId;
    public String username;


    public String email;
    public String phone;


    public CallStatus callStatus;
    public String managerPublicId;


}
