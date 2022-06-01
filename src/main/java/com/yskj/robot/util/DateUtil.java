package com.yskj.robot.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 时间格式化工具类
 */
public class DateUtil {

    public static final String STANDARD = "yyyy-MM-dd HH-mm:ss";

    // 将字符串类型时间转成Date类型
    public static Date string2Date(String strDate){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strDate);
        return dateTime.toDate();
    }

    public static Date string2Date(String strDate,String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = dateTimeFormatter.parseDateTime(strDate);
        return dateTime.toDate();
    }

    //将Date类型时间转成字符串
    public static String Date2String(Date date){
        if (date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(STANDARD);
    }
    public static String Date2String(Date date,String format){
        if (date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }
}
