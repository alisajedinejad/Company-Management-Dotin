package dao;

import entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CategoryEntityDaoImpl implements CategoryEntityDao {
    @PersistenceContext
    private EntityManager em;

    public CategoryEntity Insert(CategoryEntity t) {
        em.persist(t);
        em.flush();
        return t;
    }

    public CategoryEntity Update(CategoryEntity m) {
        return em.merge(m);
    }

    public void Delete(CategoryEntity m) {
        m = em.merge(m);
        em.remove(m);

    }

    public CategoryEntity SelectById(int id) {
        return em.find(CategoryEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<CategoryEntity> SelectAll() {
        Query query = em.createQuery("FROM CategoryEntity");
        return (List<CategoryEntity>) query.getResultList();
    }


}
