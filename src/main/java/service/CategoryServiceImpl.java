package service;

import dao.CategoryDao;
import entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao Categorydao;

    @Override
    public Category Add(Category t) throws Exception {
        Categorydao.Insert(t);
        return t;
    }

    @Override
    public void Remove(Category t) {
        Categorydao.Delete(t);
    }

    @Override
    public Category Edit(Category t) {
        Categorydao.Update(t);
        return t;
    }

    @Override
    public List<Category> GetAll() {
        return Categorydao.SelectAll();
    }

    @Override
    public List<Category> GetAllByCode(String s) {
        return Categorydao.SelectAllByCode(s);
    }
}
