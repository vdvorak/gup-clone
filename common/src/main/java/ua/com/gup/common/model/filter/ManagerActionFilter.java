package ua.com.gup.common.model.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.gup.common.model.mongo.profile.manager.event.CallStatus;

import java.util.Date;

@Data
public class ManagerActionFilter {

    @JsonFormat(pattern = "dd-MM-yyyy")
    public Date date;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date dateStart;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    public Date dateEnd;

    @JsonFormat(pattern = "HH:mm")
    public Date time;
    @JsonFormat(pattern = "HH:mm")
    public Date timeStart;
    @JsonFormat(pattern = "HH:mm")
    public Date timeEnd;


    public String clientId;
    public String username;


    public String email;
    public String phone;


    public CallStatus callStatus;
    public String managerPublicId;


}
