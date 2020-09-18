package dao;

import entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    public User Insert(User t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public User Update(User m) {
        return em.merge(m);
    }

    public void Delete(User m) {

        m.setActive(false);
        em.merge(m);
    }

    public User SelectById(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> SelectByEmail(String Email) {
        Query query = em.createQuery("from User where c_email='" + Email + "'", User.class);
        return (List<User>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<User> SelectAll() {
        Query query = em.createQuery("from User", User.class);
        return (List<User>) query.getResultList();
    }


}
