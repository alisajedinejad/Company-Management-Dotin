package service;

import entity.Email;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface EmailService {

    public Email Add(Email t) throws Exception;

    public void Remove(Email t);

    public Email Edit(Email t);

    public List<Email> GetAll();

    public List<Email> GetAllBySender(int Id);

    public List<Email> GetAllByRecivers(int Id);
}
