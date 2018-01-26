/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.gup.common.util;

import java.awt.image.BufferedImage;
import org.imgscalr.Scalr;

/**
 *
 * @author dimka
 */
public class ImageScaleUtil {

    //image width and height (square) that you wish the image to fit within
    private static final int profileSmallCachedSize = 70; //40
    private static final int profileLargeCachedSize = 175;

    /**
     * @param bufferedImage
     * @return
     */
    public static BufferedImage smallBufferedImageProfilePreparator(BufferedImage bufferedImage) {
        return Scalr.resize(bufferedImage, profileSmallCachedSize);
    }

    /**
     * @param bufferedImage
     * @return
     */
    public static BufferedImage largeBufferedImageProfilePreparator(BufferedImage bufferedImage) {
        return Scalr.resize(bufferedImage, profileLargeCachedSize);
    }

    /**
     * @param inputImage
     * @return
     */
    public static BufferedImage largeBufferedImageOfferPreparator(BufferedImage inputImage) {
        int originHeight = inputImage.getHeight();
        if (originHeight > 600) {
            return Scalr.resize(inputImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, 600);
        } else {
            return Scalr.resize(inputImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_HEIGHT, originHeight);
        }
    }

    /**
     * @param inputImage
     * @return
     */
    public static BufferedImage mediumBufferedImageOfferPreparator(BufferedImage inputImage) {
        return Scalr.resize(inputImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 220, 150);
    }

    /**
     * @param inputImage
     * @return
     */
    public static BufferedImage smallBufferedImageOfferPreparator(BufferedImage inputImage) {
        return Scalr.resize(inputImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.AUTOMATIC, 90, 90);
    }
}
