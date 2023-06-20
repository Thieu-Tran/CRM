package controller;


import model.RoleModel;
import model.UserModel;
import service.LoginService;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})

public class LoginController extends HttpServlet {

    private LoginService loginService = new LoginService();
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = "";
        String password = "";
        String role = "";

        HttpSession session = req.getSession();
        try {
            email= (String) session.getAttribute("email");
            password = (String) session.getAttribute("password");
            role = (String) session.getAttribute("role");
        }catch (Exception e){
        }

        req.setAttribute("username",email);
        req.setAttribute("password",password);

        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String contextPath = req.getContextPath();

        boolean isSuccess = loginService.checkLogin(email,password);

        if (isSuccess){
            List<UserModel> userModelList = userService.getUserByEmailAndPassword(email,password);
            UserModel userModel = userModelList.get(0);
            int roleId = userModel.getRoleId();

            RoleModel roleModel = roleService.getRoleById(roleId);

            HttpSession session = req.getSession();

            session.setAttribute("email",email);
            session.setAttribute("password",password);
            session.setAttribute("role",roleModel.getName());

            resp.sendRedirect(contextPath);
        }else {
            PrintWriter writer = resp.getWriter();
            writer.println("Login fail");
            writer.close();
        }

    }
}
