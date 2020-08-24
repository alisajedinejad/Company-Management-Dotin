package dao;

import entity.Email;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmailDaoImpl implements EmailDao {

    @PersistenceContext
    private EntityManager em;

    public Email Insert(Email t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public Email Update(Email m) {
        return em.merge(m);
    }

    public void Delete(Email m) {
        m = em.merge(m);
        em.remove(m);

    }

    public Email SelectById(int id) {
        return em.find(Email.class, id);
    }

    @Override
    public List<Email> SelectBySenderId(int Id) {
        Query query = em.createQuery("from Email where sender='" + Id + "'", Email.class);
        return (List<Email>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Email> SelectAll() {
        Query query = em.createQuery("FROM Email");
        return (List<Email>) query.getResultList();
    }
}
