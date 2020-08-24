package Exeption_Handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

public class CheckForbiddenVacationDate extends ForbiddenVacationDate implements Comparator {

    public CheckForbiddenVacationDate() {}

    public CheckForbiddenVacationDate(String start1, String end1, String start2, String end2) throws ForbiddenVacationDate, ParseException {
        if ((compare(start1, end2) == 1) && compare(end1, start2) == 0) {
            throw new ForbiddenVacationDate();
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
