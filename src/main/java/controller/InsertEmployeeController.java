package controller;

import dao.ProjectConfig;
import entity.CategoryEntity;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryEntityService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ali on 21/08/2020.
 */
@Controller
public class InsertEmployeeController {
    @RequestMapping("/insertEmployee")
    public ModelAndView insertEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String manager = request.getParameter("manager");
        String password = request.getParameter("password");
        UserService us = context.getBean(UserService.class);
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
        modelAndView.addObject("password", request.getParameter("password2"));
        modelAndView.addObject("userName", request.getParameter("userName"));
        return modelAndView;
    }
}