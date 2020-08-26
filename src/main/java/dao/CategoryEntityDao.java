package dao;

import entity.CategoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface CategoryEntityDao {
    public CategoryEntity Insert(CategoryEntity t);

    public CategoryEntity Update(CategoryEntity t);

    public void Delete(CategoryEntity t);

    public List<CategoryEntity> SelectAll();

    public CategoryEntity SelectById(int Id);
    public List<CategoryEntity> SelectByCode(String code);
}
