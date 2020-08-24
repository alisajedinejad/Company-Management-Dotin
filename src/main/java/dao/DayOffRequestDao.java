package dao;

import entity.DayOffRequest;
import entity.User;

import java.util.List;

public interface DayOffRequestDao {
    public DayOffRequest Insert(DayOffRequest t);

    public DayOffRequest Update(DayOffRequest t);

    public void Delete(DayOffRequest t);

    public List<DayOffRequest> SelectAll();

    public List<DayOffRequest> SelectAllByID(int id);

    public DayOffRequest SelectById(int Id);

    public User SelectByIdUser(int Id);
}
