package service;

import Exeption_Handler.ForbiddenDaysOff;
import entity.DayOffRequest;

import java.util.List;

public interface DayOffRequestService {

    public DayOffRequest Add(DayOffRequest t) throws Exception;

    public void Remove(DayOffRequest t);

    public DayOffRequest Edit(DayOffRequest t);

    public DayOffRequest Get(int id);

    public List<DayOffRequest> GetAll();

    public List<DayOffRequest> GetAllByManagerId(int id);

    public List<DayOffRequest> GetAllDayOffRequestById(int id) throws ForbiddenDaysOff;

    public List<DayOffRequest> GetAllDayOffRequestByIdUnChecked(int id);

    public void AddIfNotOverLap(List<DayOffRequest> dayOffRequests, DayOffRequest dayOffRequest) throws Exception;
}
