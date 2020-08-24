package dao;

import entity.File;
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
        m = em.merge(m);
        em.remove(m);

    }

    public File SelectById(int id) {
        return em.find(File.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<File> SelectAll() {
        Query query = em.createQuery("FROM File");
        return (List<File>) query.getResultList();
    }

}
