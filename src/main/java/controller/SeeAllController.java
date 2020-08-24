package controller;

import dao.ProjectConfig;
import entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ali on 21/08/2020.
 */
@Controller
public class SeeAllController {

    @RequestMapping("/seeAll")
    public ModelAndView seeAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllClients");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        UserService us = context.getBean(UserService.class);
        List<User> users = us.GetAll();
        modelAndView.addObject("users2", users);
        return modelAndView;
    }
}