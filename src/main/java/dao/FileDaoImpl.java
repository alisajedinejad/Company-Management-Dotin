package dao;

import entity.File;
import entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FileDaoImpl implements FileDao {
    @PersistenceContext
    private EntityManager em;

    public File Insert(File t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public File Update(File m) {
        return em.merge(m);
    }

    public void Delete(File m) {
        m.setActive(false);
        em.merge(m);
    }

    public File SelectById(int id) {
        return em.find(File.class, id);
    }

    @Override
    public List<File> GetByEmailId(int Id) {
        Query query = em.createQuery("FROM File where email_c_emailId=" + Id);
        return (List<File>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<File> SelectAll() {
        Query query = em.createQuery("FROM File");
        return (List<File>) query.getResultList();
    }

}
