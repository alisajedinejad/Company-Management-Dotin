package controller;

import dao.ProjectConfig;
import entity.CategoryEntity;
import entity.DayOffRequest;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CategoryEntityService;
import service.DayOffRequestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Created by ali on 23/08/2020.
 */
@WebServlet("/Ajax")
public class AjaxServlet extends HttpServlet {
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF);
        PrintWriter out = response.getWriter();
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProjectConfig.class);
        String requestId = request.getParameter("requestId");
        String status = request.getParameter("status");
        DayOffRequestService dors = context.getBean(DayOffRequestService.class);
        DayOffRequest dayOffRequest = dors.Get(Integer.parseInt(requestId));
        CategoryEntityService ces = context.getBean(CategoryEntityService.class);
        CategoryEntity categoryEntity = ces.GetById(Integer.parseInt(status));
        dayOffRequest.setStatus(categoryEntity);
        dors.Edit(dayOffRequest);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "done");
        out.print(jsonObject);
    }

    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF); //could be Level.OFF
    }
}
