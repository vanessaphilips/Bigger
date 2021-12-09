package com.example.project_bigbangk.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * created by Pieter Jan Bleichrodt
 * Creation date 12/8/2021
 */
public class ObjectToJsonHelper {

    private final Logger logger = LoggerFactory.getLogger(ObjectToJsonHelper.class);

    public ObjectToJsonHelper() {
        super();
        logger.info("New ObjectToJsonHelper");
    }

    public static String objectToJson(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
