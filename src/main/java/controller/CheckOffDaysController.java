package controller;

import Exeption_Handler.ForbiddenDaysOff;
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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ali on 22/08/2020.
 */

@Controller
public class CheckOffDaysController {
    @RequestMapping("/checkOffDays")
    public ModelAndView checkOffDays(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String userId = request.getParameter("userId");
        String startTime = request.getParameter("startVacationTime");
        String endTime = request.getParameter("endVacationTime");
        String startClock = request.getParameter("startClock");
        String endClock = request.getParameter("endClock");
        startTime = startTime.replace("/", "-");
        endTime = endTime.replace("/", "-");
        Date dateStart = java.sql.Date.valueOf(startTime);
        Date dateEnd = java.sql.Date.valueOf(endTime);
        java.util.Date dateStartPersian = CalendarService.convertToJalali(dateStart);
        java.util.Date dateEndPersian = CalendarService.convertToJalali(dateEnd);
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        UserService us = context.getBean(UserService.class);
        List<User> users = us.GetByEmail(request.getParameter("userName"));
        if (!startClock.equals(null) && !endClock.equals(null) && !startClock.equals("") && !endClock.equals("")) {
            DateFormat dateFormat0 = new SimpleDateFormat("yyyy-MM-dd");
            String[] startsDay = dateFormat0.format(dateStartPersian).split("-", 3);
            String[] startsClock = startClock.split(":", 2);
            String[] endsDay = dateFormat0.format(dateEndPersian).split("-", 3);
            String[] endsClock = endClock.split(":", 2);
            int y1 = Integer.parseInt(startsDay[0]);
            int m1 = Integer.parseInt(startsDay[1]);
            int d1 = Integer.parseInt(startsDay[2]);
            int h1 = Integer.parseInt(startsClock[0]);
            int mi1 = Integer.parseInt(startsClock[1]);
            int y2 = Integer.parseInt(endsDay[0]);
            int m2 = Integer.parseInt(endsDay[1]);
            int d2 = Integer.parseInt(endsDay[2]);
            int h2 = Integer.parseInt(endsClock[0]);
            int mi2 = Integer.parseInt(endsClock[1]);
            if (dateStart.equals(dateEnd) && h1 == h2 && mi1 == mi2) {
                ModelAndView modelAndView = new ModelAndView();
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
                modelAndView.addObject("msg", "زمان شروع مرخصی و زمان اتمام مرخصی نمی تواند یکسان باشد");
                return modelAndView;
            }
            CategoryEntityService categoryEntityService = context.getBean(CategoryEntityService.class);
            List<CategoryEntity> categoryEntities = categoryEntityService.GetByCode("pending");

            CategoryEntity categoryEntity = categoryEntityService.GetById(categoryEntities.get(0).getId());
            DayOffRequest dayOffRequest = new DayOffRequest();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dayOffRequest.setEnd(dateFormat.format(dateEndPersian));
            dayOffRequest.setStart(dateFormat.format(dateStartPersian));
            dayOffRequest.setStartClock(Integer.toString(h1) + ":" + Integer.toString(mi1));
            dayOffRequest.setEndClock(Integer.toString(h2) + ":" + Integer.toString(mi2));
            dayOffRequest.setUSerId(us.GetById(Integer.parseInt(userId)));
            dayOffRequest.setStatus(categoryEntity);
            DayOffRequestService dayOffRequestService = context.getBean(DayOffRequestService.class);
            try {
                List<DayOffRequest> dayOffRequests = dayOffRequestService.GetAllDayOffRequestById(Integer.parseInt(userId));
                try {
                    dayOffRequestService.AddIfNotOverLap(dayOffRequests, dayOffRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                    ModelAndView modelAndView = new ModelAndView();
                    modelAndView.setViewName("user");
                    CategoryService cs2 = context.getBean(CategoryService.class);
                    DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                    List<DayOffRequest> dayOffRequests2 = dors.GetAllByManagerId(users.get(0).getId());
                    Category category = cs2.GetAllByCode("role").get(0);
                    us = context.getBean(UserService.class);
                    List<User> AllUsers = us.GetAll();
                    modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                    modelAndView.addObject("requests", dayOffRequests2);
                    modelAndView.addObject("users", AllUsers);
                    modelAndView.addObject("msg", "0");
                    modelAndView.addObject("user", users.get(0));
                    modelAndView.addObject("msg", "درخواست مرخصی داده شده با درخواست های قبلی تداخل دارد");
                    return modelAndView;
                }

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user");
                CategoryService cs2 = context.getBean(CategoryService.class);
                DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                List<DayOffRequest> dayOffRequests2 = dors.GetAllByManagerId(users.get(0).getId());
                Category category = cs2.GetAllByCode("role").get(0);
                us = context.getBean(UserService.class);
                List<User> AllUsers = us.GetAll();
                modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                modelAndView.addObject("requests", dayOffRequests2);
                modelAndView.addObject("users", AllUsers);
                modelAndView.addObject("msg", "0");
                modelAndView.addObject("user", users.get(0));
                modelAndView.addObject("msg", "درخواست مرخصی شما با موفقیت ثبت شد");
                return modelAndView;
            } catch (ForbiddenDaysOff forbiddenDaysOff) {
                forbiddenDaysOff.printStackTrace();
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("user");
                CategoryService cs2 = context.getBean(CategoryService.class);
                DayOffRequestService dors = context.getBean(DayOffRequestService.class);
                List<DayOffRequest> dayOffRequests2 = dors.GetAllByManagerId(users.get(0).getId());
                Category category = cs2.GetAllByCode("role").get(0);
                us = context.getBean(UserService.class);
                List<User> AllUsers = us.GetAll();
                modelAndView.addObject("CategoryEntities", category.getCategoryEntities());
                modelAndView.addObject("requests", dayOffRequests2);
                modelAndView.addObject("users", AllUsers);
                modelAndView.addObject("msg", "0");
                modelAndView.addObject("user", users.get(0));
                modelAndView.addObject("msg", "تعداد درخواست های مرخصی شما که در حالت بررسی است محدود است و الان امکان ثبت مرخصی جدید نیست");
                return modelAndView;
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();


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
            modelAndView.addObject("msg", "ساعت شروع و پایان مرخصی مشخص نشده است");
            return modelAndView;
        }
    }

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
        modelAndView.addObject("userName", request.getParameter("userName"));
        modelAndView.addObject("password", request.getParameter("password"));
        return modelAndView;
    }
}
