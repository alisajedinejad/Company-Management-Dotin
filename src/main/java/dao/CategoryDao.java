package dao;

import entity.Category;

import java.util.List;

public interface CategoryDao {
    public Category Insert(Category t);

    public Category Update(Category t);

    public void Delete(Category t);

    public List<Category> SelectAll();

    public List<Category> SelectAllByCode(String s);

    public Category SelectById(int Id);
}
