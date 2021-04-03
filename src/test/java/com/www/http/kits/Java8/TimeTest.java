package com.www.http.kits.Java8;

import org.junit.Test;

import java.time.LocalDateTime;

public class TimeTest {
    /**
     * 表示时间的:
     *      LocalDate
     *      LocalTime
     *      localDateTime
     *      三者的用法都是一样的
     *     1. LocalDateTime.now()获取当前系统时间
     *     2. LocalDateTime.of() 指定数字,转换为标准的时间格式
     *     3. plusYear(long t) 加t年后的时间
     *     4.minusYear(long t) 减t年后的时间
     *     5.getYear() 获得当前年份
     */
    @Test
    public void test01(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime of = LocalDateTime.of(2012, 12, 23, 2, 2);
        System.out.println(of);

        //plusYear(int t) 加t年后的时间
        LocalDateTime localDateTime1 = localDateTime.plusDays(2);
        System.out.println(localDateTime1);

        //getYear() 获得当前年份 类似的有getMonth(),getHour()...
        System.out.println(localDateTime.getYear());
    }
}
