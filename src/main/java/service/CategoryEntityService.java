package service;

import entity.CategoryEntity;

import java.util.List;

public interface CategoryEntityService {

    public CategoryEntity Add(CategoryEntity t) throws Exception;

    public void Remove(CategoryEntity t);

    public CategoryEntity Edit(CategoryEntity t);

    public List<CategoryEntity> GetAll();

    public CategoryEntity GetById(int id);
}
