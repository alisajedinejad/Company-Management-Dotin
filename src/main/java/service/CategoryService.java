package service;

import entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface CategoryService {

    public Category Add(Category t) throws Exception;

    public void Remove(Category t);

    public Category Edit(Category t);

    public List<Category> GetAll();

    public List<Category> GetAllByCode(String s);
}
