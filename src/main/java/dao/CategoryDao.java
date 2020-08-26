package dao;

import entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryDao {
    public Category Insert(Category t);

    public Category Update(Category t);

    public void Delete(Category t);

    public List<Category> SelectAll();

    public List<Category> SelectAllByCode(String s);

    public Category SelectById(int Id);
}
