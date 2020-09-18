package service;

import Exeption_Handler.CheckForbiddenVacationDate;
import Exeption_Handler.CheckLimitsDaysOff;
import Exeption_Handler.ForbiddenDaysOff;
import dao.DayOffRequestDao;
import entity.DayOffRequest;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DayOffRequestServiceImpl implements DayOffRequestService {
    @Autowired
    private DayOffRequestDao DayOffRequestdao;

    @Override
    public DayOffRequest Add(DayOffRequest t) throws Exception {
        DayOffRequestdao.Insert(t);
        return t;
    }

    @Override
    public void Remove(DayOffRequest t) {
        DayOffRequestdao.Delete(t);
    }

    @Override
    public DayOffRequest Edit(DayOffRequest t) {
        DayOffRequestdao.Update(t);
        return t;
    }

    @Override
    public DayOffRequest Get(int id) {
        return DayOffRequestdao.SelectById(id);
    }

    @Override
    public List<DayOffRequest> GetAll() {
        return DayOffRequestdao.SelectAll();
    }

    @Override
    public List<DayOffRequest> GetAllByManagerId(int id) {

        List<User> users = DayOffRequestdao.SelectAllUsers();

        User userAdmin = new User();

        for (User user : users) {
            if (user.getRole().getCode().equals("admin")) {

                userAdmin = user;
                break;
            }

        }
        List<DayOffRequest> dayOffRequests = DayOffRequestdao.SelectAll();
        List<DayOffRequest> dayOffRequestsProfed = new ArrayList<>();
        for (DayOffRequest dayOffRequest : dayOffRequests) {
            User user = dayOffRequest.getUSerId();


            if (user.getManager() != null) {
                if (user.getManager().getId() == id && dayOffRequest.getStatus().getCode().equals("pending")) {
                    dayOffRequestsProfed.add(dayOffRequest);
                }
            } else {
                if (dayOffRequest.getStatus().getCode().equals("pending") && userAdmin.getId() == id) {
                    dayOffRequestsProfed.add(dayOffRequest);
                }
            }
        }
        return dayOffRequestsProfed;
    }

    @Override
    public List<DayOffRequest> GetAllDayOffRequestById(int id) throws ForbiddenDaysOff {
        List<DayOffRequest> dayOffRequests = DayOffRequestdao.SelectAllByID(id);
        List<DayOffRequest> myReturn = new ArrayList<>();
        for (DayOffRequest dayOffRequest : dayOffRequests) {
            if (dayOffRequest.getStatus().getCode().equals("pending")) {
                myReturn.add(dayOffRequest);
            }
        }
        CheckLimitsDaysOff checkLimitsDaysOff = new CheckLimitsDaysOff(dayOffRequests);
        return myReturn;
    }

    @Override
    public List<DayOffRequest> GetAllDayOffRequestByIdUnChecked(int id) {
        List<DayOffRequest> dayOffRequests = DayOffRequestdao.SelectAllByID(id);
        return dayOffRequests;
    }

    @Override
    public void AddIfNotOverLap(List<DayOffRequest> dayOffRequests, DayOffRequest dayOffRequest) throws Exception {
        if (dayOffRequests.size() > 0) {
            String startTime1 = dayOffRequest.getStart().toString();
            String endTime1 = dayOffRequest.getEnd().toString();
            String startTimeC1 = dayOffRequest.getStartClock().toString();
            String endTimeC1 = dayOffRequest.getEndClock().toString();
            String startTime2 = dayOffRequests.get(0).getStart().toString();
            String endTime2 = dayOffRequests.get(0).getEnd().toString();
            String startTimeC2 = dayOffRequests.get(0).getStartClock().toString();
            String endTimeC2 = dayOffRequests.get(0).getEndClock().toString();
            startTime1 = startTime1.replace("/", "-");
            endTime1 = endTime1.replace("/", "-");
            startTime2 = startTime2.replace("/", "-");
            endTime2 = endTime2.replace("/", "-");
            CheckForbiddenVacationDate checkForbiddenVacationDate =
                    new CheckForbiddenVacationDate(startTime1, endTime1, startTimeC1, endTimeC1, startTime2, endTime2, startTimeC2, endTimeC2);
        }
        Add(dayOffRequest);
    }
}
