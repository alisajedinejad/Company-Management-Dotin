package controller;

import dao.ProjectConfig;
import entity.Category;
import entity.DayOffRequest;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryService;
import service.DayOffRequestService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ali on 21/08/2020.
 */
@Controller
public class LoginController {
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
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user");
                CategoryService cs = context.getBean(CategoryService.class);
                DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                List<DayOffRequest> dayOffRequests = dors.GetAllByManagerId(users.get(0).getId());
                Category category = cs.GetAllByCode("role").get(0);
                us = context.getBean(UserService.class);
                List<User> AllUsers = us.GetAll();
                modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                modelAndView.addObject("requests", dayOffRequests);
                modelAndView.addObject("users", AllUsers);
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
}