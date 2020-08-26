package controller;

import com.ibm.icu.util.PersianCalendar;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ali on 26/08/2020.
 */
public class Test {
    public static void main(String[] args){




//        Date thisTime = convertToJalali(new java.util.Date());
        System.out.println(convertToJalali(new java.util.Date()));


    }
    public static Date convertToJalali(Date date){

        PersianCalendar persianCalendar=new PersianCalendar(date);
        int y= persianCalendar.get(Calendar.YEAR);
        int m = persianCalendar.get(Calendar.MONTH) + 1;
        int d = persianCalendar.get(Calendar.DAY_OF_MONTH);
        String day="",month="",year=Integer.toString(y);
        if(d<10){
            day="0"+d;
        }
        if(m<10){
            month="0"+m;
        }
        String myDateString=year+"-"+month+"-"+day;
        java.sql.Date myDate = java.sql.Date.valueOf(myDateString);

//        return myDateString;
        return myDate;

    }
}
