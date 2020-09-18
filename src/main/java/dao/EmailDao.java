package dao;

import entity.Email;
import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface EmailDao {
    public Email Insert(Email t);

    public Email Update(Email t);

    public void Delete(Email t);

    public List<Email> SelectAll();

    public Email SelectById(int Id);

    public List<Email> SelectBySenderId(int Id);

    public List<Object> GetAllByRecivers(int Id);

}
