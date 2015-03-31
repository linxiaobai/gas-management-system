package com.gms.util.date;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat SDF_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat SDF_SHORTDATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm:ss");
    private static final SimpleDateFormat SDF_SIMPLEDATETIME = new SimpleDateFormat("yyyyMMddHHmm");
    private static final Map<String, SimpleDateFormat> SIMPLE_DATE_FORMATERS = new HashMap();

    static {
        SIMPLE_DATE_FORMATERS.put("yyyy-MM-dd", SDF_DATE);
        SIMPLE_DATE_FORMATERS.put("yyyy-MM-dd HH:mm:ss", SDF_DATETIME);
        SIMPLE_DATE_FORMATERS.put("yyyy-MM-dd HH:mm", SDF_SHORTDATETIME);
        SIMPLE_DATE_FORMATERS.put("HH:mm:ss", SDF_TIME);
        SIMPLE_DATE_FORMATERS.put("yyyyMMddHHmm", SDF_SIMPLEDATETIME);
    }

    private static SimpleDateFormat getCachedDateFormat(String aMask) {
        return (SimpleDateFormat) SIMPLE_DATE_FORMATERS.get(aMask);
    }

    public static String formatDate(Date aDate) {
        return format("yyyy-MM-dd", aDate);
    }
    public static String formatShortDateTime(Date aDate) {
        return format("yyyy-MM-dd HH:mm", aDate);
    }
    public static String format8Date(Date aDate) {
        return format("yyyyMMdd", aDate);
    }

    public static String formatDateTime(Date date) {
        return format("yyyy-MM-dd HH:mm:ss", date);
    }

    public static String formatTime(Date date) {
        return format("HH:mm:ss", date);
    }

    public static String format(String aMask, Date aDate) {
        if (aDate == null) {
            return null;
        }
        SimpleDateFormat sd = getCachedDateFormat(aMask);
        if (sd == null) {
            sd = new SimpleDateFormat(aMask);
            return sd.format(aDate);
        }
        synchronized (sd) {
            return sd.format(aDate);
        }
    }

    public static Date parseDate(String strDate) {
        try {
            return parse("yyyy-MM-dd", strDate);
        } catch (ParseException e) {
            throw new BusinessException(e);
        }
    }

    public static Date parseDateTime(String strDate) {
        try {
            return parse("yyyy-MM-dd HH:mm:ss", strDate);
        } catch (ParseException e) {
            throw new BusinessException(e);
        }
    }

    public static Date parseTime(String strDate) {
        strDate = formatDate(new Date()) + " " + strDate;
        try {
            return parse("yyyy-MM-dd HH:mm:ss", strDate);
        } catch (ParseException e) {
            throw new BusinessException(e);
        }
    }

    public static Date parsDefaultTime(String date) {
        try {
            return parse("yyyy-MM-dd HH:mm:ss", date);
        } catch (ParseException e) {
            throw new BusinessException(e);
        }
    }

    public static Date parse(String aMask, String strDate)
            throws ParseException {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat sd = getCachedDateFormat(aMask);
        if (sd == null) {
            sd = new SimpleDateFormat(aMask);
            return sd.parse(strDate);
        }
        synchronized (sd) {
            return sd.parse(strDate);
        }
    }

    public static String getTimeInteval(Date date) {
        if (null == date) {
            return "";
        }
        long timeMillSeconds = System.currentTimeMillis() - date.getTime();
        int hours = (int) (timeMillSeconds / 3600000L);
        timeMillSeconds -= hours * 60 * 60 * 1000;
        int minutes = (int) (timeMillSeconds / 60000L);
        timeMillSeconds -= minutes * 60 * 1000;
        int seconds = (int) (timeMillSeconds / 1000L);
        String inteval = "";
        if (hours > 0) {
            inteval = hours + "小时" + minutes + "分" + seconds + "秒";
        } else if (minutes > 0) {
            inteval = minutes + "分" + seconds + "秒";
        } else {
            inteval = seconds + "秒";
        }
        return inteval;
    }

    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    public static Date addDays(Date date, int amount) {
        return add(date, 6, amount);
    }

    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static String getTodayString() {
        return format("yyyyMMdd", new Date());
    }

    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static String nextDate(String strdate)
            throws ParseException {
        Date temp = parse("yyyy-MM-dd", strdate);
        Date next = new Date(temp.getTime() + 86400000L);
        return formatDate(next);
    }

    /**
     * 获取传入时间和当前时间的差（单位：秒）
     * @param date
     * @return null 表示异常
     */
    public static Long diffNow(Date date) {
        if(date == null) {
            return null;
        }
        Long nowTime = System.currentTimeMillis();
        return Math.abs(date.getTime() - nowTime)/1000;
    }

    public DateUtils() {
    }

}
