/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import ua.com.gup.common.model.image.ImageStorage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of = {"id"})
public abstract class CommonRentOffer implements Serializable {

    @Id
    @Getter @Setter
    private String id;

    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    private String title;

    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    private String description;

    private List<Integer> categories;

    private String authorId;

    @Indexed(unique = true)
    private String seoUrl;

    @Setter
    private List<ImageStorage> images;

    public List<ImageStorage> getImages() {
        if (images == null) {
            images = new LinkedList();
        }
        return images;
    }

}
