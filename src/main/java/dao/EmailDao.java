package dao;

import entity.Email;

import java.util.List;

public interface EmailDao {
    public Email Insert(Email t);

    public Email Update(Email t);

    public void Delete(Email t);

    public List<Email> SelectAll();

    public Email SelectById(int Id);

    public List<Email> SelectBySenderId(int Id);
}
