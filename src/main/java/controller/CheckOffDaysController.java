package controller;

import Exeption_Handler.ForbiddenDaysOff;
import dao.ProjectConfig;
import entity.CategoryEntity;
import entity.DayOffRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CategoryEntityService;
import service.DayOffRequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

/**
 * Created by ali on 22/08/2020.
 */

@Controller
public class CheckOffDaysController {
    @RequestMapping("/checkOffDays")
    public ModelAndView insertEmployee(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String userId = request.getParameter("userId");
        String startTime = request.getParameter("startVacationTime");
        String endTime = request.getParameter("endVacationTime");
        startTime = startTime.replace("/", "-");
        endTime = endTime.replace("/", "-");
        Date dateStart = java.sql.Date.valueOf(startTime);
        Date dateEnd = java.sql.Date.valueOf(endTime);
        if (dateStart.equals(dateEnd)) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("massage");
            modelAndView.addObject("password", request.getParameter("password2"));
            modelAndView.addObject("userName", request.getParameter("userName"));
            modelAndView.addObject("msg", "زمان شروع مرخصی و زمان اتمام مرخصی نمی تواند یکسان باشد");
            return modelAndView;
        }
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        CategoryEntityService categoryEntityService = context.getBean(CategoryEntityService.class);
        CategoryEntity categoryEntity = categoryEntityService.GetById(5);
        DayOffRequest dayOffRequest = new DayOffRequest();
        dayOffRequest.setEnd(dateEnd);
        dayOffRequest.setStart(dateStart);
        dayOffRequest.setUSerId(Integer.parseInt(userId));
        dayOffRequest.setStatus(categoryEntity);
        DayOffRequestService dayOffRequestService = context.getBean(DayOffRequestService.class);
        try {
            List<DayOffRequest> dayOffRequests = dayOffRequestService.GetAllDayOffRequestById(Integer.parseInt(userId));
            try {
                dayOffRequestService.AddIfNotOverLap(dayOffRequests, dayOffRequest);
            } catch (Exception e) {
                e.printStackTrace();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("massage");
                modelAndView.addObject("password", request.getParameter("password2"));
                modelAndView.addObject("userName", request.getParameter("userName"));
                modelAndView.addObject("msg", "درخواست مرخصی داده شده با درخواست های قبلی تداخل دارد");
                return modelAndView;
            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("success");
            modelAndView.addObject("password", request.getParameter("password2"));
            modelAndView.addObject("userName", request.getParameter("userName"));
            return modelAndView;
        } catch (ForbiddenDaysOff forbiddenDaysOff) {
            forbiddenDaysOff.printStackTrace();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("massage");
            modelAndView.addObject("password", request.getParameter("password2"));
            modelAndView.addObject("userName", request.getParameter("userName"));
            modelAndView.addObject("msg", "تعداد درخواست های مرخصی شما که در حالت بررسی است محدود است و الان امکان ثبت مرخصی جدید نیست");
            return modelAndView;
        }
    }
}
