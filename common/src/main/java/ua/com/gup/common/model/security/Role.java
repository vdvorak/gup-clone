package ua.com.gup.common.model.security;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedHashSet;

@Document(collection = "role")
@Data
public class Role {

    @Id
    private String id;

    @NotNull
    private String name;

    private String title;

    private Collection<Function> functions;

    private Boolean isEditable;

    public Collection<Function> getFunctions() {
        if (functions == null) {
            functions = new LinkedHashSet<>();
        }
        return functions;
    }
}
