package com.jack.root.something.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jack
 * On 18-2-6:下午3:19
 * Desc:
 */

public class Util {
    /**
     Desc date 2 string
     18-2-6:下午3:22
     Author jack
    */
    public static String date2Str(Date date) {
        if (date == null) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }
}
