package ua.com.itproekt.gup.model.nace;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by Комп2 on 22.10.2015.
 */
@Document(collection = "department")
public class DepartmentOrNace {
    @Id
    String id;
    String name;
    String transcript;
    List<DepartmentOrNace> children;

    public DepartmentOrNace() {
        id = new ObjectId().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTranscript() {
        return transcript;
    }

    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }

    public List<DepartmentOrNace> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentOrNace> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        String tail = "have no child";
        if(children != null){
            for(DepartmentOrNace dn : children){
                tail = dn.toString();
            }
        }
        return "nace{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", transcript='" + transcript + "\' \n CHILDREN: \n" + tail +
                '}';
    }
}
