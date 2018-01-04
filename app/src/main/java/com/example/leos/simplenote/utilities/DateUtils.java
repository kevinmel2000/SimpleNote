package com.example.leos.simplenote.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Leo on 03/01/2018.
 */

public class DateUtils {
    public static Calendar calendar = Calendar.getInstance();
    public static long getCurrentDateTime(){
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.getTimeInMillis();
    }
    public static String getFormatedDate(long time){
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy");
        calendar.setTimeInMillis(time);
        return dateformat.format(calendar.getTime());
    }
    public static String getFormatedTime(long time){
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss aa");
        calendar.setTimeInMillis(time);
        return timeFormat.format(calendar.getTime());
    }
}
