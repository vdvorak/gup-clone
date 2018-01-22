package ua.com.gup.common.model.security;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document(collection = "function")
@Data
public class Function {
    @Id
    private String id;

    @NotNull
    private String name;

    private String title;

    private List<String> paths;

}
