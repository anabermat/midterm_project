package com.ironhack.midterm_project.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date getDateNow(){
        LocalDateTime now = LocalDateTime.now();
        Date today = java.sql.Timestamp.valueOf(now);
        return today;
    }
}
