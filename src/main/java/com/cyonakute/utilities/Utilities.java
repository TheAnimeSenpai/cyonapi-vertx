package com.cyonakute.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Calendar;
import java.util.Date;
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

    public static String BuildISODateString(Date date) {
        try {
            //sample data - 2017-12-19T06:00:00.000Z
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            int second = cal.get(Calendar.SECOND);

            return String.format("%s-%s-%s'T'%s:%s:%s.000'Z'", year, month, day, hour, minute, second);
        }
        catch (Exception exception) {
            return "";
        }
    }

    public static String BuildISODateString() {
        Date today = Calendar.getInstance().getTime();
        return BuildISODateString(today);
    }

    public static String TodaysDate () {
        Date today = Calendar.getInstance().getTime();
        return today.toString();
    }
}
