package service;

import dao.UserDao;
import entity.Email;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userdao;

    @Override
    public User Add(User t) throws Exception {
        userdao.Insert(t);
        return t;
    }

    @Override
    public void Remove(User t) {
        userdao.Delete(t);
    }

    @Override
    public User Edit(User t) {
        userdao.Update(t);
        return t;
    }

    @Override
    public User GetById(int id) {
        return userdao.SelectById(id);
    }

    @Override
    public List<User> GetByEmail(String Email) {
        return userdao.SelectByEmail(Email);
    }

    @Override
    public List<User> GetAll() {
        return userdao.SelectAll();
    }

    @Override
    public List<User> getUserByEmail(List<Email> emails) {
        List<User> users = new ArrayList<>();
        for (Email email : emails) {
            int id = email.getSender();
            users.add(userdao.SelectById(id));
        }
        return users;
    }
}
