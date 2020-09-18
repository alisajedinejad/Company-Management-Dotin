package controller;

import dao.ProjectConfig;
import entity.Category;
import entity.CategoryEntity;
import entity.DayOffRequest;
import entity.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 21/08/2020.
 */
@Controller
public class UserController {

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @RequestMapping("/insertEmployee")
    public ModelAndView insertEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);


        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String manager = request.getParameter("manager");
        String password = request.getParameter("password");
        UserService us = context.getBean(UserService.class);


        if (!name.equals("") && !email.equals("") && !role.equals("نقش کاربر را مشخص کنید") && !manager.equals("") && !password.equals("لطفا مدیر مستقیم این همکار را مشخص کنید")) {
            CategoryEntityService cs = context.getBean(CategoryEntityService.class);
            CategoryEntity categoryEntity = cs.GetById(Integer.parseInt(role));
            User userManager = us.GetById(Integer.parseInt(manager));
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setRole(categoryEntity);
            user.setManager(userManager);
            user.setPassword(password);
            us.Add(user);

            List<User> users = us.GetByEmail(request.getParameter("userName"));

            if (users.size() != 0) {
                if (users.get(0).getPassword().equals(request.getParameter("password2"))) {
                    modelAndView.setViewName("user");
                    CategoryService cs2 = context.getBean(CategoryService.class);
                    DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                    List<DayOffRequest> dayOffRequests = dors.GetAllByManagerId(users.get(0).getId());
                    Category category = cs2.GetAllByCode("role").get(0);
                    us = context.getBean(UserService.class);
                    List<User> AllUsers = us.GetAll();
                    modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                    modelAndView.addObject("requests", dayOffRequests);
                    modelAndView.addObject("users", AllUsers);
                    modelAndView.addObject("msg", "0");
                    modelAndView.addObject("user", users.get(0));
                    modelAndView.addObject("msg", "همکار با موفقیت ثبت شد");
                    return modelAndView;
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("res", "");
                    return modelAndView;
                }
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("res", "");
                return modelAndView;
            }
        } else {
            List<User> users = us.GetByEmail(request.getParameter("userName"));

            if (users.size() != 0) {
                if (users.get(0).getPassword().equals(request.getParameter("password2"))) {
                    modelAndView.setViewName("user");
                    CategoryService cs2 = context.getBean(CategoryService.class);
                    DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                    List<DayOffRequest> dayOffRequests = dors.GetAllByManagerId(users.get(0).getId());
                    Category category = cs2.GetAllByCode("role").get(0);
                    us = context.getBean(UserService.class);
                    List<User> AllUsers = us.GetAll();
                    modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                    modelAndView.addObject("requests", dayOffRequests);
                    modelAndView.addObject("users", AllUsers);
                    modelAndView.addObject("msg", "0");
                    modelAndView.addObject("user", users.get(0));
                    modelAndView.addObject("msg", "لطفا همه ی فیلد ها را پر کنید");
                    return modelAndView;
                } else {
                    modelAndView.setViewName("error");
                    modelAndView.addObject("res", "");
                    return modelAndView;
                }
            } else {
                modelAndView.setViewName("error");
                modelAndView.addObject("res", "");
                return modelAndView;
            }
        }


    }

    @RequestMapping("/checkPassword")
    public ModelAndView checkPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        UserService us = context.getBean(UserService.class);
        List<User> users = us.GetByEmail(userName);


        if (users.size() != 0) {
            if (users.get(0).getPassword().equals(password)) {

                CategoryService cs = context.getBean(CategoryService.class);
                DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                List<DayOffRequest> dayOffRequests = dors.GetAllByManagerId(users.get(0).getId());
                Category category = cs.GetAllByCode("role").get(0);
                us = context.getBean(UserService.class);
                List<User> AllUsers = us.GetAll();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user");
                modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                modelAndView.addObject("requests", dayOffRequests);
                modelAndView.addObject("users", AllUsers);
                modelAndView.addObject("msg", "0");
                modelAndView.addObject("user", users.get(0));
                return modelAndView;
            } else {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("error");
                modelAndView.addObject("res", "");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("error");
            modelAndView.addObject("res", "");
            return modelAndView;
        }
    }

    @RequestMapping("/seeAll")
    public ModelAndView seeAllUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllClients");

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        UserService us = context.getBean(UserService.class);
        List<User> users = us.GetAll();
        modelAndView.addObject("users2", users);
        modelAndView.addObject("userName", request.getParameter("userName"));
        modelAndView.addObject("password", request.getParameter("password2"));
        return modelAndView;
    }

    @RequestMapping("/createSample")
    public String createSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        Category category = new Category();
        category.setCode("role");
        Category category2 = new Category();
        category2.setCode("status");
        Category category3 = new Category();
        category3.setCode("importance");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("برنامه نویس ");
        categoryEntity.setCode("developer");
        categoryEntity.setCategory(category);
        categoryEntities.add(categoryEntity);
        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setName("تستر");
        categoryEntity2.setCode("tester");
        categoryEntity2.setCategory(category);
        categoryEntities.add(categoryEntity2);
        CategoryEntity categoryEntity3 = new CategoryEntity();
        categoryEntity3.setName("مدیر");
        categoryEntity3.setCode("manager");
        categoryEntity3.setCategory(category);
        categoryEntities.add(categoryEntity3);
        CategoryEntity categoryEntity4 = new CategoryEntity();
        categoryEntity4.setName("ادمین");
        categoryEntity4.setCode("admin");
        categoryEntity4.setCategory(category);
        categoryEntities.add(categoryEntity4);
        CategoryEntity categoryEntity5 = new CategoryEntity();
        categoryEntity5.setName("در حال بررسی");
        categoryEntity5.setCode("pending");
        categoryEntity5.setCategory(category2);
        categoryEntities.add(categoryEntity5);
        CategoryEntity categoryEntity6 = new CategoryEntity();
        categoryEntity6.setName("رد شده");
        categoryEntity6.setCode("rejected");
        categoryEntity6.setCategory(category2);
        categoryEntities.add(categoryEntity6);
        CategoryEntity categoryEntity7 = new CategoryEntity();
        categoryEntity7.setName("موافقت شده");
        categoryEntity7.setCode("accepted");
        categoryEntity7.setCategory(category2);
        categoryEntities.add(categoryEntity7);
        CategoryEntity categoryEntity8 = new CategoryEntity();
        categoryEntity8.setName("کم اهمیت");
        categoryEntity8.setCode("less");
        categoryEntity8.setCategory(category3);
        categoryEntities.add(categoryEntity8);
        CategoryEntity categoryEntity9 = new CategoryEntity();
        categoryEntity9.setName("متوسط");
        categoryEntity9.setCode("middle");
        categoryEntity9.setCategory(category3);
        categoryEntities.add(categoryEntity9);
        CategoryEntity categoryEntity10 = new CategoryEntity();
        categoryEntity10.setName("ههم");
        categoryEntity10.setCode("high");
        categoryEntity10.setCategory(category3);
        categoryEntities.add(categoryEntity10);
        category.setCategoryEntities(categoryEntities);
        CategoryService cs = context.getBean(CategoryService.class);
        cs.Add(category);
        cs.Add(category2);
        cs.Add(category3);
        CategoryEntityService ces = context.getBean(CategoryEntityService.class);
        ces.Add(categoryEntity);
        ces.Add(categoryEntity2);
        ces.Add(categoryEntity3);
        ces.Add(categoryEntity4);
        ces.Add(categoryEntity5);
        ces.Add(categoryEntity6);
        ces.Add(categoryEntity7);
        ces.Add(categoryEntity8);
        ces.Add(categoryEntity9);
        ces.Add(categoryEntity10);
        UserService userService = context.getBean(UserService.class);
        User user0 = new User();
        user0.setName("علی");
        user0.setEmail("ali@yahoo");
        user0.setRole(categoryEntity4);
        user0.setPassword("1234");
        User user1 = new User();
        user1.setName("ندا");
        user1.setEmail("neda@yahoo");
        user1.setRole(categoryEntity3);
        user1.setPassword("1234");
        User user2 = new User();
        user2.setName("آرمان");
        user2.setEmail("arman@yahoo");
        user2.setRole(categoryEntity2);
        user2.setPassword("1234");
        User user3 = new User();
        user3.setName("رحمان");
        user3.setEmail("rahman@yahoo");
        user3.setRole(categoryEntity);
        user3.setPassword("1234");
        User user4 = new User();
        user4.setName("اعظم");
        user4.setEmail("azam@yahoo");
        user4.setRole(categoryEntity);
        user4.setPassword("1234");
        List<User> users = new ArrayList<>();
//            users.add(user0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        user0.setEmployies(users);
        userService.Add(user1);
        userService.Add(user2);
        userService.Add(user3);
        userService.Add(user4);
        userService.Add(user0);
        user1.setManager(user0);
        user2.setManager(user0);
        user3.setManager(user0);
        user4.setManager(user0);
        userService.Edit(user1);
        userService.Edit(user2);
        userService.Edit(user3);
        userService.Edit(user4);
        File file = new File("c:\\new\\");
        File file2 = new File("c:\\temp\\");
        file.mkdir();
        file2.mkdir();
        return "login";

    }


    @RequestMapping("/logToSystem")
    public ModelAndView logToSystem(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        FileService fileService = context.getBean(FileService.class);
        List<entity.File> files = fileService.GetAll();
        File someFile = new File(request.getServletContext().getRealPath("/") + File.separator + "test.sql");
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(files.get(0).getBlol());
        fos.flush();
        fos.close();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        modelAndView.addObject("file", files.get(0));
        System.out.println("path is : " + request.getServletContext().getContextPath());
        return modelAndView;
    }
}