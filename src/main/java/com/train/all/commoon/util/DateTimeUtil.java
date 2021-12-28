package com.train.all.commoon.util;

import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

public class DateTimeUtil {

    public static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_DATE2 = "yyyyMMdd";
    public static final String PATTERN_DATE3 = "yyyy年MM月";
    public static final String PATTERN_DATE4 = "yyyyMM";
    public static final String PATTERN_DATE5 = "yyyy-MM";

    public static final String PATTERN_2 = "yyyy/MM/dd";

    /**
     * yyyy-MM-dd
     */
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
    /**
     * yyyyMMdd
     */
    public static final DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern(PATTERN_DATE2);
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);
    /**
     * yyMMdd
     */
    public static final DateTimeFormatter yyMMddFormatter = DateTimeFormatter.ofPattern("yyMMdd");
    /**
     * yyyyMM
     */
    public static final DateTimeFormatter dateFormatter4 = DateTimeFormatter.ofPattern(PATTERN_DATE4);
    /**
     * yyyy-MM
     */
    public static final DateTimeFormatter dateFormatter5 = DateTimeFormatter.ofPattern(PATTERN_DATE5);


    /**
     * 获取当前日期(yyyy/MM/dd)
     *  例子：2021/07/14
     * @return
     */
    public static String getCurrentDateSlash() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_2);
        return formatter.format(LocalDate.now());
    }


    /**
     * 将 yyyy-MM-dd HH:mm:ss 转换为string
     * @return
     */
    public static String changeLocalDateTimeToString(LocalDateTime localDateTime) {
        if(null==localDateTime){
            return "";
        }
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 根据日期获取该日期属于哪个季度
     * @param date  日期（格式为"yyyy-MM-dd"）
     * @return: java.lang.Integer
     * @author: kennys.chai
     * @date: 2021/6/21
     */
    public static Integer getIntegerQuarter(String date) {
        if (StringUtils.hasLength(date)) {
            return 0;
        }
        LocalDate date1 =LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int mouth = date1.getMonth().getValue();
        if (mouth <= 3) {
            return 1;
        } else if (mouth <= 6) {
            return 2;
        } else if (mouth <= 9) {
            return 3;
        } else if (mouth <= 12) {
            return 4;
        }
        return 0;
    }

    private DateTimeUtil() {
        throw new IllegalStateException("DateTimeUtil class");
    }

    /**
     * 获取某月初0时0分0秒 指针正数向前 monthPointer月 指针负数向后 monthPointer月 传0当月
     * @param monthPointer 月份指针
     * @return LocalDateTime
     * @date 21.03.08
     * @auth douhy
     */
    public static LocalDateTime getMonthStartDate(Integer monthPointer) {
        LocalDateTime now = LocalDateTime.now().minusMonths(monthPointer);
        return now.with(TemporalAdjusters.firstDayOfMonth()).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取某月的月末23时59分59秒 指针正数向前 monthPointer月 指针负数向后 monthPointer月 传0当月
     * @param monthPointer 月份指针
     * @return LocalDateTime
     * @date 21.03.08
     * @auth douhy
     */
    public static LocalDateTime getMonthEndDate(Integer monthPointer) {
        LocalDateTime now = LocalDateTime.now().minusMonths(monthPointer);
        return now.with(TemporalAdjusters.lastDayOfMonth()).withHour(23).withMinute(59).withSecond(59).withNano(0);
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     *  获取当前时间对应的 1月指定天
     *  比如：当前时间为：2021-11-16 ，调用天指针为9，则返回数据为：2021-01-09 00：00：00
     * @param dayPointer  天指针
     * @return: java.time.LocalDateTime
     * @author: kennys.chai
     * @date: 2021/11/16
     */
    public static LocalDateTime getFirstMonthPointerTime(Integer dayPointer) {
        return LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()).withDayOfMonth(dayPointer).withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

}
