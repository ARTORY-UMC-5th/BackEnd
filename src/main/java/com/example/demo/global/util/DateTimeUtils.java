package com.example.demo.global.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtils {
    public static LocalDateTime convertToLocalDateTime(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }//시간을 로컬 시간으로 바꿔줌
}
