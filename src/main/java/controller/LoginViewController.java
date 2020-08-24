package controller;

import dao.ProjectConfig;
import entity.Category;
import entity.CategoryEntity;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryEntityService;
import service.CategoryService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ali on 21/08/2020.
 */
@Controller
public class LoginViewController {

    static boolean singletone = true;

    @RequestMapping("/login")
    public ModelAndView loginView(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (singletone) {
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
            categoryEntity9.setName("توسط");
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
            users.add(user1);
            users.add(user2);
            users.add(user3);
            users.add(user4);
            user0.setEmployies(users);
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
            singletone = false;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
}