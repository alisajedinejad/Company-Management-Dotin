package service;

import dao.EmailDao;
import entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailDao Emaildao;

    @Override
    public Email Add(Email t) throws Exception {
        Emaildao.Insert(t);
        return t;
    }

    @Override
    public void Remove(Email t) {
        Emaildao.Delete(t);
    }

    @Override
    public Email Edit(Email t) {
        Emaildao.Update(t);
        return t;
    }

    @Override
    public List<Email> GetAll() {
        return Emaildao.SelectAll();
    }

    @Override
    public List<Email> GetAllBySender(int Id) {
        return Emaildao.SelectBySenderId(Id);
    }

    @Override
    public List<Email> GetAllByRecivers(int Id) {
        List<Email> emails = Emaildao.SelectAll();
        List<Email> returnEmails = new ArrayList<>();
        for (Email email : emails) {
            for (int i = 0; i < email.getRecivers().size(); i++) {
                if (email.getRecivers().get(i).getId() == Id) {
                    returnEmails.add(email);
                }
            }

        }
        return returnEmails;
    }
}
