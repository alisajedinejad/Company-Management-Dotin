package Exeption_Handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class CheckForbiddenVacationDate extends ForbiddenVacationDate implements Comparator {

    public CheckForbiddenVacationDate() {
    }

    public CheckForbiddenVacationDate(String start1, String end1, String startC1, String endC1, String start2, String end2, String startC2, String endC2) throws ForbiddenVacationDate, ParseException {
        DateFormat dateFormat0 = new SimpleDateFormat("yyyy-MM-dd");
        String[] startsDay = start1.split("-", 3);
        String[] startsClock = startC1.split(":", 2);
        String[] endsDay = end1.split("-", 3);
        String[] endsClock = endC1.split(":", 2);
        int y1 = Integer.parseInt(startsDay[0]);
        int m1 = Integer.parseInt(startsDay[1]);
        int d1 = Integer.parseInt(startsDay[2]);
        int h1 = Integer.parseInt(startsClock[0]);
        int mi1 = Integer.parseInt(startsClock[1]);
        int y2 = Integer.parseInt(endsDay[0]);
        int m2 = Integer.parseInt(endsDay[1]);
        int d2 = Integer.parseInt(endsDay[2]);
        int h2 = Integer.parseInt(endsClock[0]);
        int mi2 = Integer.parseInt(endsClock[1]);
        String[] startsDay2 = start2.split("-", 3);
        String[] startsClock2 = startC2.split(":", 2);
        String[] endsDay2 = end2.split("-", 3);
        String[] endsClock2 = endC2.split(":", 2);
        int y12 = Integer.parseInt(startsDay2[0]);
        int m12 = Integer.parseInt(startsDay2[1]);
        int d12 = Integer.parseInt(startsDay2[2]);
        int h12 = Integer.parseInt(startsClock2[0]);
        int mi12 = Integer.parseInt(startsClock2[1]);
        int y22 = Integer.parseInt(endsDay2[0]);
        int m22 = Integer.parseInt(endsDay2[1]);
        int d22 = Integer.parseInt(endsDay2[2]);
        int h22 = Integer.parseInt(endsClock2[0]);
        int mi22 = Integer.parseInt(endsClock2[1]);
        MyTimeStamp myTimeStampStart1 = new MyTimeStamp(y1, m1, d1, h1, mi1);
        MyTimeStamp myTimeStampEnd1 = new MyTimeStamp(y2, m2, d2, h2, mi2);
        MyTimeStamp myTimeStampStart2 = new MyTimeStamp(y12, m12, d12, h12, mi12);
        MyTimeStamp myTimeStampEnd2 = new MyTimeStamp(y22, m22, d22, h22, mi22);
        if (myTimeStampStart2.getTimeStamp().compareTo(myTimeStampStart1.getTimeStamp()) >= 0) {
            if (myTimeStampStart2.getTimeStamp().compareTo(myTimeStampStart1.getTimeStamp()) != 0 && myTimeStampEnd1.getTimeStamp().compareTo(myTimeStampEnd2.getTimeStamp()) != 0) {
                if (myTimeStampEnd1.getTimeStamp().compareTo(myTimeStampStart2.getTimeStamp()) >= 0) {
                    System.out.println("Not Over Lap");
                } else {
                    throw new ForbiddenVacationDate();
                }
            } else {
                throw new ForbiddenVacationDate();
            }
        } else {
            if (myTimeStampEnd2.getTimeStamp().compareTo(myTimeStampStart1.getTimeStamp()) >= 0) {
                System.out.println("Not Over Lap");
            } else {
                throw new ForbiddenVacationDate();
            }
        }
    }

    @Override
    public int compare(Object o1, Object o2) {
        String d1 = o1.toString();
        String d2 = o2.toString();
        d1 = d1.replace("/", "-");
        d2 = d2.replace("/", "-");
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        if (d1.compareTo(d2) > 0) {
            return 0;
        } else if (d1.compareTo(d2) < 0) {
            return 1;
        } else if (d1.compareTo(d2) == 0) {
            return 2;
        }
        return 10;
    }
}
