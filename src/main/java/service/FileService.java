package service;

import entity.File;

import java.util.List;

public interface FileService {

    public File Add(File t) throws Exception;

    public void Remove(File t);

    public File Edit(File t);

    public List<File> GetAll();
}
