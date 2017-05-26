package com.example.validation;


import com.example.service.AutomobilesService;
import javafx.scene.control.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class ReservationValid {

    @Autowired
    AutomobilesService automobiles;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    private Pattern pattern = Pattern.compile(DATE_PATTERN);
    private Matcher matcher;

    private static final String DATE_PATTERN = "((19|20)\\d\\d)/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])";

    //Date format yyyy/MM/dd

    public boolean validateByStartDate(String reservationStart) throws ParseException {

        if(reservationStart.isEmpty()) {
            return false;
        }

        matcher = pattern.matcher(reservationStart);

        if(!matcher.matches()) {
            return false;
        }

        Date date = new Date();
        Date start1 = dateFormat.parse(reservationStart);

        if (start1.after(date)) {
            matcher.reset();
            return true;
        }

        return false;
    }

    public boolean validateByEndDate(String reservationEnd) throws ParseException {

        if(reservationEnd.isEmpty()) {
            return false;
        }

        matcher = pattern.matcher(reservationEnd);

        if(!matcher.matches()) {
            return false;
        }

        Date date = new Date();
        Date end1 = dateFormat.parse(reservationEnd);

        if ( end1.after(date)) {
            matcher.reset();
            return true;
        }

        return false;
    }


    public boolean validateByComparingStartAndEndDates(String reservationStart, String reservationEnd) throws ParseException {
        Date start = dateFormat.parse(reservationStart);
        Date end = dateFormat.parse(reservationEnd);

        if (start.before(end)) {
            return true;
        }
        return false;
    }

    public boolean validateByExistenceOfAuto(String autoName) {
        if(autoName.isEmpty()){
            return false;
        }
        if(automobiles.findFromModel(autoName) != null ) {
            return true;
        }

        return false;
    }
}
