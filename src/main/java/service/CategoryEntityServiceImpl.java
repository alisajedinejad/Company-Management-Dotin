package service;

import dao.CategoryEntityDao;
import entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryEntityServiceImpl implements CategoryEntityService {
    @Autowired
    private CategoryEntityDao CategoryEntitydao;

    @Override
    public CategoryEntity Add(CategoryEntity t) throws Exception {
        CategoryEntitydao.Insert(t);
        return t;
    }

    @Override
    public void Remove(CategoryEntity t) {
        CategoryEntitydao.Delete(t);
    }

    @Override
    public CategoryEntity Edit(CategoryEntity t) {
        CategoryEntitydao.Update(t);
        return t;
    }

    @Override
    public List<CategoryEntity> GetAll() {
        return CategoryEntitydao.SelectAll();
    }

    @Override
    public CategoryEntity GetById(int id) {
        return CategoryEntitydao.SelectById(id);
    }
}
