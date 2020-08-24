package service;

import dao.FileDao;
import entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao Filedao;

    @Override
    public File Add(File t) throws Exception {
        Filedao.Insert(t);
        return t;
    }

    @Override
    public void Remove(File t) {
        Filedao.Delete(t);
    }

    @Override
    public File Edit(File t) {
        Filedao.Update(t);
        return t;
    }

    @Override
    public List<File> GetAll() {
        return Filedao.SelectAll();
    }
}
