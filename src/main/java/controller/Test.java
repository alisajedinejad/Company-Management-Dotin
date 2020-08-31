package controller;

import com.ibm.icu.util.PersianCalendar;
import service.CalendarService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ali on 26/08/2020.
 */
public class Test {
    public static void main(String[] args){

        Date thisTime = CalendarService.convertToJalali(new java.util.Date());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");



       System.out.println(dateFormat.format(thisTime)) ;
       System.out.println((thisTime)) ;


    }

}
