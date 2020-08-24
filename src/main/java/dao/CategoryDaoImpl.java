package dao;

import entity.Category;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @PersistenceContext
    private EntityManager em;

    public Category Insert(Category t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public Category Update(Category m) {
        return em.merge(m);
    }

    public void Delete(Category m) {
        m = em.merge(m);
        em.remove(m);

    }

    public Category SelectById(int id) {
        return em.find(Category.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Category> SelectAll() {
        Query query = em.createQuery("from Category", Category.class);
        return (List<Category>) query.getResultList();
    }

    @Override
    public List<Category> SelectAllByCode(String s) {

        Query query = em.createQuery("from Category where Code='" + s + "'", Category.class);

        return (List<Category>) query.getResultList();
    }
}
