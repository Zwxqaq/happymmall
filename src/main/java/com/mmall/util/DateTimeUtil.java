package com.mmall.util;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
//todo what is joda why no use calendar or date
public class DateTimeUtil {
    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String dataStr, String format) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
        DateTime dateTime = formatter.parseDateTime(dataStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date data, String format) {
        if (data == null) {
            //todo what is the advantage of stringutil why no just use ""
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(data);
        return dateTime.toString(format);
    }

    public static Date strToDate(String dataStr) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = formatter.parseDateTime(dataStr);
        return dateTime.toDate();
    }

    public static String dateToStr(Date data) {
        if (data == null) {
            //todo what is the advantage of stringutil why no just use ""
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(data);
        return dateTime.toString(STANDARD_FORMAT);
    }

    public static void main(String[] args) {
        System.out.println(DateTimeUtil.dateToStr(new Date()));
        System.out.println(DateTimeUtil.strToDate("2019-04-16 06:10:49"));
    }
}
