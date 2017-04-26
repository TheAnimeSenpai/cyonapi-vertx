package com.cyonakute.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by Opeyemi.Akinnawo on 4/25/2017.
 */
public class Utilities {

    public static Map ObjectToMap(Object obj) {
        try {
            ObjectMapper oMapper = new ObjectMapper();
            Map<String, Object> map = oMapper.convertValue(obj, Map.class);
            return map;
        }
        catch (Exception exception) {
            return null;
        }
    }
}
