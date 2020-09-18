package controller;

import dao.ProjectConfig;
import entity.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 22/08/2020.
 */

@Controller
public class EmailController {

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @RequestMapping("/emailData")
    public ModelAndView emailData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String context = null;
        String subject = null;
        String hashFIle = null;
        String userName = "";
        String sendeId = null;
        byte[] file = null;
        String fileName = "";
        String names[] = new String[50];
        int index = 0;
        String format = null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return null;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new java.io.File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        System.out.println(request.toString());
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        file = item.get();
                        format = item.getName();
                    } else {
                        String name = item.getFieldName();
                        String value = item.getString();
                        if (name.equals("names")) {
                            names[index] = value;
                            index++;
                        }
                        if (name.equals("subject")) {
                            subject = value;
                        }
                        if (name.equals("context")) {
                            context = value;
                        }
                        if (name.equals("id")) {
                            sendeId = value;
                        }
                        if (name.equals("hashFIle")) {
                            hashFIle = value;
                        }
                        if (name.equals("userName")) {
                            userName = value;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("There was an error: " + ex.getMessage());
        }
        if (file.length != 0) {

            fileName = hashFIle + "_" + userName + "_" + sendeId + "_" + format;
        }
        if (names[0] != null) {
            ApplicationContext ac =
                    new AnnotationConfigApplicationContext(ProjectConfig.class);
            UserService userService = ac.getBean(UserService.class);
            EmailService emailService = ac.getBean(EmailService.class);
            FileService fileService = ac.getBean(FileService.class);
            User user = userService.GetById(Integer.parseInt(sendeId));
            List<User> users = new ArrayList<>();
            for (String name : names) {
                if (name != null) {
                    users.add(userService.GetById(Integer.parseInt(name)));
                }
            }
            CategoryEntityService categoryEntityService = ac.getBean(CategoryEntityService.class);
            CategoryEntity categoryEntity = categoryEntityService.GetByCode("middle").get(0);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            Email email = new Email();
            email.setSender(user);
            email.setRecivers(users);
            email.setContext(context);
            email.setImportance(categoryEntity);
            emailService.Add(email);
            List<entity.File> files = new ArrayList<>();
            entity.File myFile = new entity.File();
            if (fileName.equals("")) {
                myFile.setLocation("بدونه ضمیمه");
            } else {
                myFile.setLocation(request.getServletContext().getRealPath("/") + File.separator + fileName);
                File someFile = new File(request.getServletContext().getRealPath("/") + File.separator + fileName);
                FileOutputStream fos = new FileOutputStream(someFile);
                fos.write(file);
                fos.flush();
                fos.close();
            }
            myFile.setBlol(file);
            files.add(myFile);
            fileService.Add(myFile);
            myFile.setEmail(email);
            email.setAttachments(files);
            fileService.Edit(myFile);
            emailService.Edit(email);
            List<User> users2 = userService.GetByEmail(userName);
            ModelAndView modelAndView = new ModelAndView();
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
            modelAndView.addObject("msg", "ایمیل با موفقیت ارسال شد");
            modelAndView.addObject("user", users2.get(0));
            return modelAndView;
        } else {
            ApplicationContext ac =
                    new AnnotationConfigApplicationContext(ProjectConfig.class);
            UserService userService = ac.getBean(UserService.class);
            ModelAndView modelAndView = new ModelAndView();
            List<User> users2 = userService.GetByEmail(userName);
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
        System.out.println("emails is " + emails.get(2).getAttachments().get(0).getLocation());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllRecivedEmail");
        modelAndView.addObject("emails", emails);
        modelAndView.addObject("senders", senders);
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("password", password);
        return modelAndView;
    }
}
