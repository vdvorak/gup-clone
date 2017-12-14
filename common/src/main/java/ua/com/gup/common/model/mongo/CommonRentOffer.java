/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.model.mongo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ua.com.gup.common.model.image.ImageStorage;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CommonRentOffer implements Serializable {

    @Id
    @Getter @Setter
    private String id;

    @Setter
    private List<ImageStorage> images;

    public List<ImageStorage> getImages() {
        if (images == null) {
            images = new LinkedList();
        }
        return images;
    }

}
