/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.common.model.image.ImageStorage;

/**
 *
 * @author dimka
 */
public interface CommonOfferService {

    public boolean exists(String offerId);

    public List<ImageStorage> getImages(String offerId);

    public ImageStorage getImage(String offerId, String imageId);

    public ImageStorage addImage(String offerId, MultipartFile file) throws IOException;

    public boolean isExistsImage(String offerId, String imageId);

    public void deleteImage(String offerId, String imageId) throws IOException;

}
