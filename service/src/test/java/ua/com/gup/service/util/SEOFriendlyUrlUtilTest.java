package ua.com.gup.service.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;


public class SEOFriendlyUrlUtilTest {
    @Test
    public void generateSEOFriendlyUrl() throws Exception {
        String source = "йцукенгшщзхїфівапролджєячсмитьбю.";
        String result = SEOFriendlyUrlUtil.generateSEOFriendlyUrl(source);
        Assert.assertNotEquals(source, result);
    }

}