package dao;

import entity.Email;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
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
        m.setActive(false);
        em.merge(m);
    }

    public Email SelectById(int id) {
        return em.find(Email.class, id);
    }

    @Override
    public List<Email> SelectBySenderId(int Id) {
        Query query = em.createQuery("from Email where sender='" + Id + "'", Email.class);
        return (List<Email>) query.getResultList();
    }

    @Override
    public List<Object> GetAllByRecivers(int Id) {
        Query query = em.createNativeQuery("select * from t_email_t_user WHERE recivers_c_user=" + Id);
        List<Object> result = (List<Object>) query.getResultList();
        List<Object> returns = new ArrayList<>();

        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer emailId = Integer.parseInt(String.valueOf(obj[0]));
            Query query2 = em.createNativeQuery("select * from t_email WHERE c_emailId=" + emailId);
            returns.add(query2.getSingleResult());

        }
        return returns;
    }

    @SuppressWarnings("unchecked")
    public List<Email> SelectAll() {
        Query query = em.createQuery("FROM Email");
        return (List<Email>) query.getResultList();
    }
}
