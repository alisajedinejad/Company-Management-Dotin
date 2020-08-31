package dao;

import entity.DayOffRequest;
import entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DayOffRequestDaoImpl implements DayOffRequestDao {
    @PersistenceContext
    private EntityManager em;

    public DayOffRequest Insert(DayOffRequest t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public DayOffRequest Update(DayOffRequest m) {
        return em.merge(m);
    }

    public void Delete(DayOffRequest m) {
        m = em.merge(m);
        em.remove(m);

    }

    public DayOffRequest SelectById(int id) {
        return em.find(DayOffRequest.class, id);
    }

    public User SelectByIdUser(int id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> SelectAllUsers() {
        Query query = em.createQuery("from User ", User.class);
        return (List<User>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<DayOffRequest> SelectAll() {
        Query query = em.createQuery("from DayOffRequest ", DayOffRequest.class);
        return (List<DayOffRequest>) query.getResultList();
    }

    @Override
    public List<DayOffRequest> SelectAllByID(int id) {
        Query query = em.createQuery("from DayOffRequest where c_USerId='" + id + "'", DayOffRequest.class);
        return (List<DayOffRequest>) query.getResultList();
    }
}
