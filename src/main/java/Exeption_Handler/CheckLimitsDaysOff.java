package Exeption_Handler;

import entity.DayOffRequest;

import java.util.List;

/**
 * Created by ali on 22/08/2020.
 */
public class CheckLimitsDaysOff extends ForbiddenDaysOff {

    public CheckLimitsDaysOff(List<DayOffRequest> dayOffRequests) throws ForbiddenDaysOff {
        if (dayOffRequests.size() >= 2) {
            throw new ForbiddenDaysOff();
        }
    }
}
