package dao;

import entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface UserDao {
    public User Insert(User t);

    public User Update(User t);

    public void Delete(User t);

    public List<User> SelectAll();

    public User SelectById(int Id);

    public List<User> SelectByEmail(String Email);
}