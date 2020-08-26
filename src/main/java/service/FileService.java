package service;

import entity.File;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface FileService {

    public File Add(File t) throws Exception;

    public void Remove(File t);

    public File Edit(File t);

    public List<File> GetAll();
}
