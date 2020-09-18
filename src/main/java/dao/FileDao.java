package dao;

import entity.File;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface FileDao {
    public File Insert(File t);

    public File Update(File t);

    public void Delete(File t);

    public List<File> SelectAll();

    public File SelectById(int Id);

    public List<File> GetByEmailId(int Id);


}
