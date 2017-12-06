/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.service.impl;

import org.springframework.core.io.ByteArrayResource;

/**
 * ResourceHttpMessageConverter.getDefaultContentType 
 * using getFilename() to resolve ContentType override it
 */
public class FileMessageResource extends ByteArrayResource {

    private String filename;

    public FileMessageResource(byte[] byteArray, String filename) {
        super(byteArray);
        this.filename = filename;

    }

    @Override
    public String getFilename() {
        return filename;
    }

}
