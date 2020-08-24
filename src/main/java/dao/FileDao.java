package dao;

import entity.File;

import java.util.List;

public interface FileDao {
    public File Insert(File t);

    public File Update(File t);

    public void Delete(File t);

    public List<File> SelectAll();

    public File SelectById(int Id);


}
