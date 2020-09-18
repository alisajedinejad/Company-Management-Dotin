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
        m.setActive(false);
        em.merge(m);
    }

    public CategoryEntity SelectById(int id) {
        return em.find(CategoryEntity.class, id);
    }

    @Override
    public List<CategoryEntity> SelectByCode(String code) {
        Query query = em.createQuery("from CategoryEntity where c_code='" + code + "'", CategoryEntity.class);
        return (List<CategoryEntity>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<CategoryEntity> SelectAll() {
        Query query = em.createQuery("FROM CategoryEntity");
        return (List<CategoryEntity>) query.getResultList();
    }
}
