package utils;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
 
public class Time {
    public static void main(String[] args) {
        // instant使用
        System.out.println("instant使用————————————");
        System.out.println(Instant.now().toEpochMilli());
        System.out.println(new Date().getTime());
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println("**********************");
       
        //LocalDateTime - > long
        System.out.println("LocalDateTime -> long使用");
        System.out.println(LocalDateTime.now().atZone(Clock.systemDefaultZone().getZone()).toInstant().toEpochMilli());
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli());
        System.out.println("**********************");
       
 
        //long -> LocalDateTime
        System.out.println("long -> LocalDateTime使用");
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        String st = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), ZoneId.of("Asia/Shanghai")));
        System.out.println(st);
        System.out.println("***********************");
       
 
        //ZonedDateTime
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
 
        //TemporalField 和 TemporalAdjusters
        System.out.println("TemporalField 和 TemporalAdjusters使用");
        LocalDateTime localDateTime1 = LocalDateTime.now();
        System.out.println(localDateTime1.plusDays(1));
        System.out.println(localDateTime1.with(ChronoField.YEAR,2013));
        System.out.println(localDateTime1.with(ChronoField.DAY_OF_YEAR,20));
        System.out.println(localDateTime1.with(ChronoField.MONTH_OF_YEAR,12));
        System.out.println(localDateTime1.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(localDateTime1.get(ChronoField.YEAR));
        System.out.println(localDateTime1.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(localDateTime1.get(ChronoField.DAY_OF_YEAR));
        System.out.println(localDateTime1.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(localDateTime1.minusDays(1));
        System.out.println("*************************");
        
 
        //Date -> String
        System.out.println("Date -> String使用");
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String string = df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()),ZoneId.of("Asia/Shanghai")));
        System.out.println(string);
        
    }
}
