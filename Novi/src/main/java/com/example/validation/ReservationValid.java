package com.example.validation;


import com.example.service.AutomobilesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class ReservationValid {

    @Autowired
    AutomobilesService automobiles;

    private  Pattern pattern = Pattern.compile(DATE_PATTERN);
    private Matcher matcher;
    private Matcher matcher2;

    private static final String DATE_PATTERN = "((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";

    //Date format yyyy/MM/dd

    public  boolean validateByStartDate(String reservationStart) {
        Date date = new Date();
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(date);

        Calendar start = Calendar.getInstance();
        matcher = pattern.matcher(reservationStart);
        start.set(Integer.valueOf(matcher.group(1)),
                Integer.valueOf(matcher.group(2)),
                Integer.valueOf(matcher.group(3)));



        if(matcher.matches() && !start.after(calendarNow)) {
            matcher.reset();
            return true;
        }

        return false;
    }

    public  boolean validateByEndDate(String reservationEnd) {

        matcher = pattern.matcher(reservationEnd);

        if(matcher.matches()) {
            matcher.reset();
            return true;
        }

        return false;
    }


    public  boolean validateByComparingStartAndEndDates(String reservationStart, String reservationEnd){

        matcher = pattern.matcher(reservationStart);
        matcher2 = pattern.matcher(reservationEnd);

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(Integer.valueOf(matcher.group(1)),
                Integer.valueOf(matcher.group(2)),
                Integer.valueOf(matcher.group(3)));

        end.set(Integer.valueOf(matcher2.group(1)),
                Integer.valueOf(matcher2.group(2)),
                Integer.valueOf(matcher2.group(3)));

        if(start.before(end)) {
            return true;
        }
        return false;
    }

    public boolean validateByExistenceOfAutomobile(String autoModel) {

        if(automobiles.findByModel(autoModel) != null) {
            return true;
        }
        return false;
    }


}
