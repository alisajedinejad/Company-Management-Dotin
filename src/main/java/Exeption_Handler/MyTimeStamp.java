package Exeption_Handler;

import java.math.BigInteger;

/**
 * Created by ali on 12/09/2020.
 */
public class MyTimeStamp {

    BigInteger timeStamp=BigInteger.ZERO;

    public MyTimeStamp(int year,int month,int day,int hour,int minute){
        BigInteger yearB=BigInteger.valueOf(year).multiply(new BigInteger("31104000"));
        BigInteger monthB=BigInteger.valueOf(month).multiply(new BigInteger("2592000"));
        BigInteger dayB=BigInteger.valueOf(day).multiply(new BigInteger("86400"));
        BigInteger hourB=BigInteger.valueOf(hour).multiply(new BigInteger("3600"));
        BigInteger minuteB=BigInteger.valueOf(year).multiply(new BigInteger("60"));
        this.timeStamp=this.timeStamp.add(yearB).add(minuteB).add(dayB).add(monthB).add(hourB);
    }

    public BigInteger getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(BigInteger timeStamp) {
        this.timeStamp = timeStamp;
    }
}
