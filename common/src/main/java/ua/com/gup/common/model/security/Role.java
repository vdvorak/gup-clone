package ua.com.gup.common.model.security;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedHashSet;

import static ua.com.gup.common.model.object.ObjectType.ROLE;

@Document(collection = ROLE)
@Data
public class Role {

    public static  final  String ROLE_ADMIN = "ROLE_ADMIN";
    public static  final  String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static  final  String ROLE_MANAGER = "ROLE_MANAGER";
    public static  final  String ROLE_USER = "ROLE_USER";
    public static  final  String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

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
