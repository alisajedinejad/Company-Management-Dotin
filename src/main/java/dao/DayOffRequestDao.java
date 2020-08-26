package dao;

import entity.DayOffRequest;
import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface DayOffRequestDao {
    public DayOffRequest Insert(DayOffRequest t);

    public DayOffRequest Update(DayOffRequest t);

    public void Delete(DayOffRequest t);

    public List<DayOffRequest> SelectAll();

    public List<DayOffRequest> SelectAllByID(int id);

    public DayOffRequest SelectById(int Id);

    public User SelectByIdUser(int Id);
    public List<User> SelectAllUsers();

}
