package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @Author KangQi
 * @Date 2020/3/24 9:10
 * @Version 1.0
 */
public class DateUtils {

    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YMDHMSS = "yyyyMMddHHmmssSSS";

    //region 时间戳
    /**
     * 获取当前时间戳（毫秒）
     *
     * @return 时间戳（毫秒）
     */
    public static Long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳（秒）
     *
     * @return 时间戳（秒）
     */
    public static Long getTimestampSeconds() {
        return getTimestamp() / 1000;
    }

    /**
     * 获取指定时间时间戳（秒）
     * 时间格式：yyyy-MM-dd HH:mm:ss
     *
     * @param timeStr 符合格式的时间字符串
     * @return 时间戳（秒）
     */
    public static Long getTimestamp(String timeStr) throws ParseException {
        Date date = getDate(timeStr);
        return date.getTime();
    }
    //endregion

    //region 转date
    public static Date getDate(String dateStr) throws ParseException {
        if (dateStr.indexOf(':') > 0){
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
            return format.parse(dateStr);
        } else {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
            return format.parse(dateStr);
        }
    }
    //endregion

    //region 转string

    //endregion
}
