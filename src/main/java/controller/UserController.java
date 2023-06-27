package controller;

import com.mysql.cj.Session;
import model.RoleModel;
import model.StatusModel;
import model.TaskModel;
import model.UserModel;
import service.RoleService;
import service.StatusService;
import service.TaskService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "userController",urlPatterns = {"/user","/user/add","/user/delete","/user/edit","/user/detail","/user/profile"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();
    private  RoleService roleService = new RoleService();
    private StatusService statusService = new StatusService();
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lấy đường dẫn servlet người dùng gọi trên browser
        String path = req.getServletPath();
        switch (path){
            case "/user":
                getAllUser(req,resp);
                break;
            case "/user/add":
                addUser(req,resp);
                break;
            case "/user/delete":
                deleteUser(req,resp);
                break;
            case "/user/edit":
                editUser(req,resp);
                break;
            case "/user/detail":
                detailUser(req,resp);
                break;
            case "/user/profile":
                profileUser(req,resp);
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lấy đường dẫn servlet người dùng gọi trên browser
        String path = req.getServletPath();
        switch (path){
            case "/user":
                getAllUser(req,resp);
                break;
            case "/user/add":
                addUser(req,resp);
                break;
            case "/user/edit":
                editUser(req,resp);
                break;
            default:
                break;
        }
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUser();
        req.setAttribute("listUsers",list);

        req.getRequestDispatcher("user-table.jsp").forward(req,resp);
    }

    private void getById(HttpServletRequest req,HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        UserModel userModel = userService.getById(id);
        List<RoleModel> roleModelList = roleService.getAllRole();

        req.setAttribute("userModel",userModel);
        req.setAttribute("listRoles",roleModelList);
    }


    private void addUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String method =req.getMethod();
        List<RoleModel> roleModelList = roleService.getAllRole();
        if(method.equalsIgnoreCase("post")){

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("role"));


        userService.insertUser(email,password,fullname,roleId);
        }

        req.setAttribute("listRoles",roleModelList);
        req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp){
        int userId = Integer.parseInt(req.getParameter("id"));

        boolean isSuccess = userService.deleteUser(userId);
    }

    private void updateUser(HttpServletRequest req,HttpServletResponse res){
        String email = req.getParameter("email");
        String password=req.getParameter("password");
        String fullname = req.getParameter("fullname");
        int roleId = Integer.parseInt(req.getParameter("role"));
        int id = Integer.parseInt(req.getParameter("userid"));

        boolean isSuccess = userService.updateUserById(email,password,fullname,roleId,id);
    }

    private void editUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        getById(req,resp);
        if(method.equalsIgnoreCase("post")){
            updateUser(req,resp);
            getById(req,resp);
        }

        req.getRequestDispatcher(("/user-edit.jsp")).forward(req,resp);
    }

    private void detailUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<TaskModel> taskModelList = userService.getTaskByUserId(id);
        List<StatusModel> statusModelList = statusService.getAllStatus();
        //List tổng hợp trạng thái dự án, show progress bar
        List<Integer> countStatusList = taskService.countByStatusName(statusModelList,taskModelList);


        req.setAttribute("taskModelList",taskModelList);
        req.setAttribute("statusModelList",statusModelList);
        req.setAttribute("countStatusList",countStatusList);

        req.getRequestDispatcher(("/user-details.jsp")).forward(req,resp);
    }

    private void profileUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = "";

        if (session.getAttribute("email")!=null){
            email = (String) session.getAttribute("email");
        }

        List<TaskModel> taskModelList = taskService.getTaskByEmail(email);
        List<StatusModel> statusModelList = statusService.getAllStatus();
        //List tổng hợp trạng thái dự án, show progress bar
        List<Integer> countStatusList = taskService.countByStatusName(statusModelList,taskModelList);

        req.setAttribute("taskModelList",taskModelList);
        req.setAttribute("statusModelList",statusModelList);
        req.setAttribute("countStatusList",countStatusList);

        req.getRequestDispatcher("/profile.jsp").forward(req,resp);
    }

}
