package service;

import entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public interface CategoryEntityService {

    public CategoryEntity Add(CategoryEntity t) throws Exception;

    public void Remove(CategoryEntity t);

    public CategoryEntity Edit(CategoryEntity t);

    public List<CategoryEntity> GetAll();

    public CategoryEntity GetById(int id);
    public List<CategoryEntity> GetByCode(String code);
}
