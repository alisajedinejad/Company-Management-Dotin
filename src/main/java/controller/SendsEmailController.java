package controller;

import dao.ProjectConfig;
import entity.Email;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ali on 22/08/2020.
 */

@Controller
public class SendsEmailController {

    @RequestMapping("/sendsMail")
    public ModelAndView sens(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        System.out.println("emailcode " + emails);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllSendsEmail");
        modelAndView.addObject("emails", emails);
        modelAndView.addObject("userName", userName);
        return modelAndView;
    }
}
