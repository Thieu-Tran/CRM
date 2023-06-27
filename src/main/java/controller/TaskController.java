package controller;

import model.JobModel;
import model.StatusModel;
import model.TaskModel;
import model.UserModel;
import service.JobService;
import service.StatusService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "taskController",urlPatterns = {"/task","/task/add","/task/delete","/task/edit"})
public class TaskController extends HttpServlet {
    TaskService taskService = new TaskService();
    JobService jobService = new JobService();
    UserService userService = new UserService();
    StatusService statusService = new StatusService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/task":{
                getAllTask(req,resp);
                break;
            }
            case "/task/add":{
                addTask(req,resp);
                break;
            }
            case "/task/delete":{
                deleteTask(req,resp);
                break;
            }
            case "/task/edit":{
                editTask(req,resp);
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/task":{
                getAllTask(req,resp);
                break;
            }
            case "/task/add":{
                addTask(req,resp);
                break;
            }
            case "/task/edit":{
                editTask(req,resp);
                break;
            }
            default:{
                break;
            }
        }
    }

    private void getAllTask(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        List<TaskModel> taskModelList = taskService.getAllTask();
        req.setAttribute("taskList",taskModelList);

        req.getRequestDispatcher("task.jsp").forward(req,resp);
    }

    private void getTaskById(HttpServletRequest req,HttpServletResponse resp){

        int id = Integer.parseInt(req.getParameter("id"));
        TaskModel taskModel = taskService.getTaskById(id);

        List<JobModel> jobModelList = jobService.getAllJob();
        List<UserModel> userModelList = userService.getAllUser();
        List<StatusModel> statusModelList = statusService.getAllStatus();

        req.setAttribute("taskModel",taskModel);
        req.setAttribute("jobList",jobModelList);
        req.setAttribute("userList",userModelList);
        req.setAttribute("statusList",statusModelList);
    }

    private void addTask(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        List<JobModel> jobModelList = jobService.getAllJob();
        List<UserModel> userModelList = userService.getAllUser();
        List<StatusModel> statusModelList = statusService.getAllStatus();

        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")){
            String name = req.getParameter("taskName");
            int jobId = Integer.parseInt(req.getParameter("jobId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            Date startDate = Date.valueOf(req.getParameter("startDate"));
            Date endDate = Date.valueOf(req.getParameter("endDate"));
            int statusId = Integer.parseInt(req.getParameter("statusId"));

            taskService.insertTask(name,jobId,userId,startDate,endDate,statusId);
        }
        req.setAttribute("jobList",jobModelList);
        req.setAttribute("userList",userModelList);
        req.setAttribute("statusList",statusModelList);

        req.getRequestDispatcher("/task-add.jsp").forward(req,resp);
    }

    private void deleteTask(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = taskService.deleteTask(id);
    }

    private void updateTask(HttpServletRequest req,HttpServletResponse resp){
        String name = req.getParameter("taskName");
        Date startDate = Date.valueOf(req.getParameter("startDate"));
        Date endDate = Date.valueOf(req.getParameter("endDate"));
        int jobId = Integer.parseInt(req.getParameter("jobId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int statusId = Integer.parseInt(req.getParameter("statusId"));
        int id = Integer.parseInt(req.getParameter("taskId"));

        boolean isSuccess = taskService.updateTask(name,startDate,endDate,userId,jobId,statusId,id);
    }

    private void editTask(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        req.setAttribute("role",role);

        String method = req.getMethod();
        getTaskById(req,resp);

        if(method.equalsIgnoreCase("post")){
            updateTask(req,resp);
            getTaskById(req,resp);
        }
        req.getRequestDispatcher("/task-edit.jsp").forward(req,resp);
    }


}
