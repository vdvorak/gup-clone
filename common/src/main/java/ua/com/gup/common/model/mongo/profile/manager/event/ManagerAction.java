package ua.com.gup.common.model.mongo.profile.manager.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.gup.common.model.object.ObjectType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = ObjectType.ACTION_MANAGER)
@Data
public class ManagerAction {
    @Id
    private String id;
    private ManagerActionType type;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;
    @DateTimeFormat( pattern = "HH:mm")
    private LocalTime time;

    private String clientPublicId;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String comment;

    private String managerPublicId;

}
