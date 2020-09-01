package controller;

import dao.ProjectConfig;
import entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.*;

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
    public ModelAndView emailData(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        if(names!=null) {
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
            CategoryEntity categoryEntity =categoryEntityService.GetByCode("middle").get(0);



            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            Email email = new Email();
            email.setSender(user);
            email.setRecivers(users);
            email.setContext(context);
            email.setImportance(categoryEntity);
            emailService.Add(email);
            List<File> files = new ArrayList<>();
            File myFile = new File();
            if (fileName.equals("")) {
                myFile.setLocation("بدونه ضمیمه");
            } else {
                myFile.setLocation("C:\\new\\" + fileName);
            }
            files.add(myFile);
            fileService.Add(myFile);
            myFile.setEmail(email);
            email.setAttachments(files);
            fileService.Edit(myFile);
            emailService.Edit(email);

            List<User> users2 = userService.GetByEmail(request.getParameter("userName"));

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("user");
            CategoryService cs2 = ac.getBean(CategoryService.class);
            DayOffRequestService dors = ac.getBean(DayOffRequestService.class);
            List<DayOffRequest> dayOffRequests = dors.GetAllByManagerId(users2.get(0).getId());
            Category category = cs2.GetAllByCode("role").get(0);
            userService = ac.getBean(UserService.class);
            userService = ac.getBean(UserService.class);
            List<User> AllUsers = userService.GetAll();
            modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
            modelAndView.addObject("requests", dayOffRequests);
            modelAndView.addObject("users", AllUsers);
            modelAndView.addObject("msg", "ایمیل با موفقیت ارسال شد");
            modelAndView.addObject("user", users2.get(0));
            return modelAndView;
        }
        else{

            ApplicationContext ac =
                    new AnnotationConfigApplicationContext(ProjectConfig.class);
            UserService userService = ac.getBean(UserService.class);
            ModelAndView modelAndView = new ModelAndView();
            List<User> users2 = userService.GetByEmail(request.getParameter("userName"));

            modelAndView.setViewName("user");
            CategoryService cs2 = ac.getBean(CategoryService.class);
            DayOffRequestService dors = ac.getBean(DayOffRequestService.class);
            List<DayOffRequest> dayOffRequests = dors.GetAllByManagerId(users2.get(0).getId());
            Category category = cs2.GetAllByCode("role").get(0);
            userService = ac.getBean(UserService.class);
            List<User> AllUsers = userService.GetAll();
            modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
            modelAndView.addObject("requests", dayOffRequests);
            modelAndView.addObject("users", AllUsers);
            modelAndView.addObject("msg", "گیرندکان ایمیل مشخص نشدند");
            modelAndView.addObject("user", users2.get(0));
            return modelAndView;
        }

    }
    @RequestMapping("/sendsMail")
    public ModelAndView send(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password2");
        String userId = request.getParameter("userId");
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(ProjectConfig.class);

        EmailService emailService = ac.getBean(EmailService.class);
        List<Email> emails = emailService.GetAllBySender(Integer.parseInt(userId));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllSendsEmail");
        modelAndView.addObject("emails", emails);
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("password", password);
        return modelAndView;
    }
    @RequestMapping("/recivedMail")
    public ModelAndView inbox(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password2");
        String userId = request.getParameter("userId");
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        EmailService emailService = ac.getBean(EmailService.class);
        UserService userService = ac.getBean(UserService.class);
        List<Email> emails = emailService.GetAllByRecivers(Integer.parseInt(userId));
        List<User> senders = userService.getUserByEmail(emails);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllRecivedEmail");
        modelAndView.addObject("emails", emails);
        modelAndView.addObject("senders", senders);
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("password", password);
        return modelAndView;
    }
}
