package uk.gov.dwp.health.atw.msclaimtopdf.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;

public class TestUtils {

    final static Logger logger = LoggerFactory.getLogger(TestUtils.class);

    public static String getFileAsString(String filePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            logger.error("Unable to read in css file: " + e);
        }
        return IOUtils.toString(fis, Charset.defaultCharset());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
