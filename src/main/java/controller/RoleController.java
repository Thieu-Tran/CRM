package controller;

import model.RoleModel;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "role",urlPatterns = {"/role","/role/add","/role/delete","/role/edit" })
public class RoleController extends HttpServlet {
    RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                getAllRole(req,resp);
                break;
            case "/role/add":
                addRole(req,resp);
                break;
            case "/role/delete":
                deleteRole(req,resp);
                break;
            case "/role/edit":
                editRole(req,resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path){
            case "/role":
                getAllRole(req,resp);
            case "/role/add":
                addRole(req,resp);
                break;
            case "/role/edit":
                editRole(req,resp);
                break;
            default:
                break;
        }
    }
    private void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> roleModelList = roleService.getAllRole();
        req.setAttribute("listRoles",roleModelList);
        req.getRequestDispatcher("role-table.jsp").forward(req,resp);
    }

    private void getRoleById(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));

        RoleModel roleModel = roleService.getRoleById(id);
        req.setAttribute("roleModel",roleModel);
    }

    private void addRole(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();

        if (method.equalsIgnoreCase("post")){
            String name= req.getParameter("name");
            String description = req.getParameter("description");

            roleService.insertRole(name,description);
        }
        req.getRequestDispatcher("/role-add.jsp").forward(req,resp);
    }

    private void deleteRole(HttpServletRequest req,HttpServletResponse resp){
        int roleId = Integer.parseInt(req.getParameter("id"));

        boolean isSuccess = roleService.deleteRoleById(roleId);
    }

    private void updateRole(HttpServletRequest req,HttpServletResponse resp){
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int id = Integer.parseInt(req.getParameter("roleId"));

        boolean isSuccess = roleService.updateRoleById(name,description,id);
    }

    private void editRole(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        getRoleById(req,resp);

        if(method.equalsIgnoreCase("post")){
            updateRole(req,resp);
            //Gọi lại để reload dữ liệu mới lên page
            getRoleById(req,resp);
        }

        req.getRequestDispatcher("/role-edit.jsp").forward(req,resp);
    }
}
