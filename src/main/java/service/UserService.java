package service;

import entity.Email;
import entity.User;

import java.util.List;

public interface UserService {

    public User Add(User t) throws Exception;

    public void Remove(User t);

    public User Edit(User t);

    public User GetById(int id);

    public List<User> GetByEmail(String Email);

    public List<User> GetAll();

    public List<User> getUserByEmail(List<Email> emails);
}
