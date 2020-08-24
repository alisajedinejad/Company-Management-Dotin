package dao;

import entity.CategoryEntity;

import java.util.List;

public interface CategoryEntityDao {
    public CategoryEntity Insert(CategoryEntity t);

    public CategoryEntity Update(CategoryEntity t);

    public void Delete(CategoryEntity t);

    public List<CategoryEntity> SelectAll();

    public CategoryEntity SelectById(int Id);
}
