package controller;

import model.JobModel;
import service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "jobController",urlPatterns = {"/jobs","/jobs/add","/jobs/delete","/jobs/edit"})
public class JobController extends HttpServlet {
    JobService jobService = new JobService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/jobs":{
                getAllJob(req,resp);
                break;
            }
            case "/jobs/add":{
                addJob(req,resp);
                break;
            }
            case "/jobs/delete":{
                deleteJob(req,resp);
                break;
            }
            case "/jobs/edit":{
                editJob(req,resp);
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
            case "/jobs":{
                getAllJob(req,resp);
                break;
            }
            case "/jobs/add":{
                addJob(req,resp);
                break;
            }
            case "/jobs/edit":{
                editJob(req,resp);
                break;
            }
            default:{
                break;
            }
        }
    }

    private void getAllJob(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        List<JobModel> jobModelList = jobService.getAllJob();
        req.setAttribute("jobList",jobModelList);

        req.getRequestDispatcher("groupwork.jsp").forward(req,resp);
    }

    private void getJobById(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));

        JobModel jobModel = jobService.getJobById(id);
        req.setAttribute("jobModel",jobModel);
    }


    private void addJob(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getMethod();
        if(method.equalsIgnoreCase("post")){
            String name = req.getParameter("name");
            Date startDate = Date.valueOf(req.getParameter("start-date"));
            Date endDate = Date.valueOf(req.getParameter("end-date"));

            jobService.insertJob(name,startDate,endDate);
        }
        req.getRequestDispatcher("/groupwork-add.jsp").forward(req,resp);
    }

    private void deleteJob(HttpServletRequest req, HttpServletResponse resp){
        int jobId = Integer.parseInt(req.getParameter("id"));

        boolean isSuccess = jobService.deleteJobById(jobId);
    }

    private void updateJob(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("jobId"));
        String name = req.getParameter("name");
        Date startDate = Date.valueOf(req.getParameter("start-date"));
        Date endDate = Date.valueOf(req.getParameter("end-date"));

        boolean isSuccess = jobService.updateJobById(name,startDate,endDate,id);

    }

    private void editJob(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        getJobById(req,resp);
        if(method.equalsIgnoreCase("post")){
            updateJob(req,resp);
            //Gọi lại để reload dữ liệu mới lên page
            getJobById(req,resp);
        }

        req.getRequestDispatcher("/groupwork-edit.jsp").forward(req,resp);
    }

}
