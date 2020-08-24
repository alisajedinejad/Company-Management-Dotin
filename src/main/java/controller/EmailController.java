package controller;

import dao.ProjectConfig;
import entity.CategoryEntity;
import entity.Email;
import entity.File;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryEntityService;
import service.EmailService;
import service.FileService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 22/08/2020.
 */

@Controller
public class EmailController {
    @RequestMapping("/emailData")
    public ModelAndView insertEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String context = request.getParameter("context");
        String subject = request.getParameter("subject");
        String hashFIle = request.getParameter("hashFIle");
        String sendeId = request.getParameter("id");
        String file = request.getParameter("file");
        String names[] = request.getParameterValues("names");
        String fileName = "";
        if (!file.equals("")) {
            String format = "." + file.split("\\.")[1];
            fileName = hashFIle + format;
        }
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        UserService userService = ac.getBean(UserService.class);
        EmailService emailService = ac.getBean(EmailService.class);
        FileService fileService = ac.getBean(FileService.class);
        User user = userService.GetById(Integer.parseInt(sendeId));
        List<User> users = new ArrayList<>();
        for (String name : names) {
            users.add(userService.GetById(Integer.parseInt(name)));
        }
        CategoryEntityService categoryEntityService = ac.getBean(CategoryEntityService.class);
        CategoryEntity categoryEntity = categoryEntityService.GetById(8);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        Email email = new Email();
        email.setSender(user.getId());
        email.setRecivers(users);
        email.setContext(context);
        email.setImportance(categoryEntity);
        email.setCreationTIme(date);
        emailService.Add(email);
        List<File> files = new ArrayList<>();
        File myFile = new File();
        myFile.setLocation("C:\\new\\" + fileName);
        files.add(myFile);
        fileService.Add(myFile);
        myFile.setEmail(email);
        email.setAttachments(files);
        fileService.Edit(myFile);
        emailService.Edit(email);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        modelAndView.addObject("password", request.getParameter("password2"));
        modelAndView.addObject("userName", request.getParameter("userName"));
        return modelAndView;
    }
}
