package controller;

import dao.ProjectConfig;
import entity.DayOffRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.DayOffRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by ali on 21/08/2020.
 */
@Controller
public class showAllVacationsController {

    @RequestMapping("/showAllVacations")
    public ModelAndView seeAllVacations(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllVacations");
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        String userId = request.getParameter("userId");
        DayOffRequestService dfrs = context.getBean(DayOffRequestService.class);
        List<DayOffRequest> dayOffRequests = dfrs.GetAllDayOffRequestByIdUnChecked(Integer.parseInt(userId));
        modelAndView.addObject("dayOffRequests", dayOffRequests);
        return modelAndView;
    }
}