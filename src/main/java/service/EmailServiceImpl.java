package service;

import dao.EmailDao;
import dao.ProjectConfig;
import entity.CategoryEntity;
import entity.Email;
import entity.File;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
    @Autowired
    private EmailDao Emaildao;

    @Override
    public Email Add(Email t) throws Exception {
        Emaildao.Insert(t);
        return t;
    }

    @Override
    public void Remove(Email t) {
        Emaildao.Delete(t);
    }

    @Override
    public Email Edit(Email t) {
        Emaildao.Update(t);
        return t;
    }

    @Override
    public List<Email> GetAll() {
        return Emaildao.SelectAll();
    }

    @Override
    public List<Email> GetAllBySender(int Id) {
        return Emaildao.SelectBySenderId(Id);
    }

    @Override
    public List<Email> GetAllByRecivers(int Id) {

        List<Object> result = Emaildao.GetAllByRecivers(Id);

        System.out.println("object is " + result.toString());

        List<Email> returnEmails = new ArrayList<>();


        Iterator itr = result.iterator();

        while (itr.hasNext()) {

            Object[] obj = (Object[]) itr.next();
            Integer id = Integer.parseInt(String.valueOf(obj[0]));
            Integer senderid = Integer.parseInt(String.valueOf(obj[7]));
            Integer importance = Integer.parseInt(String.valueOf(obj[6]));
            String activeString = (String.valueOf(obj[1]));
            boolean active;
            if (activeString.equals("1")) {
                active = true;
            } else {
                active = false;
            }
            String create = (String.valueOf(obj[2]));
            String modify = (String.valueOf(obj[3]));
            String version = (String.valueOf(obj[4]));
            String context = (String.valueOf(obj[5]));
            Email email = new Email();
            ApplicationContext ac =
                    new AnnotationConfigApplicationContext(ProjectConfig.class);
            FileService fileService = ac.getBean(FileService.class);
            UserService userService = ac.getBean(UserService.class);
            CategoryEntityService categoryService = ac.getBean(CategoryEntityService.class);
            User sender = userService.GetById(senderid);
            CategoryEntity categoryEntity = categoryService.GetById(importance);
            File file = fileService.GetByEmailId(id);
            List<File> files = new ArrayList<>();
            files.add(file);
            email.setRecivers(null);
            email.setImportance(categoryEntity);
            email.setAttachments(files);
            email.setActive(active);
            email.setSender(sender);
            email.setVersion(version);
            email.setContext(context);
            email.setModificationdate(modify);
            email.setCreatedate(create);
            returnEmails.add(email);
        }
        return returnEmails;
    }
}
