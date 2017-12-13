/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.model.mongo;

import org.springframework.data.annotation.Id;
import ua.com.gup.common.model.image.ImageStorage;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public abstract class CommonRentOffer implements Serializable {

    @Id
    private String id;

    private List<ImageStorage> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ImageStorage> getImages() {
        if (images == null) {
            images = new LinkedList();
        }
        return images;
    }

    public void setImages(List<ImageStorage> images) {
        this.images = images;
    }
}
