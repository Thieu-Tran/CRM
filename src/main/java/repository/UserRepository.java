package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.TaskModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Chứa tất cả các câu query liên quan tới bảng user
 */
public class UserRepository {

    public List<UserModel> findByEmailAndPassword(String email, String password){

        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();


        try {
            String sql="SELECT * FROM users u WHERE u.email =? AND  u.password =?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột chỉ định(id)
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

                userModelList.add(userModel);
            }

        }catch (Exception e){
            System.out.println("Error findByEmailAndPassword: " + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findByEmailAndPassword " + e.getMessage());
                }
            }
        }

        return userModelList;
    }

    public UserModel findById(int id){

        Connection connection = null;
        UserModel userModel = new UserModel();

        try {
            String sql = "SELECT * FROM users u WHERE u.id =?";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));

            }

        }catch (Exception e){
            System.out.println("Lỗi thực thi query findById "+e.getMessage());
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findById "+e.getMessage());
                }
            }
        }

        return userModel;
    }

    public List<UserModel> findAllUser(){
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();

        try {
            String sql="SELECT u.id,u.email,u.fullname,u.avatar ,r.description FROM users u INNER JOIN roles r ON u.role_id = r.id";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserModel userModel = new UserModel();

                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullname(resultSet.getString("fullname"));
                userModel.setDescription(resultSet.getString("description"));

                userModelList.add(userModel);
            }

        }catch (Exception e){
            System.out.println("Error findAllUser: " + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findAllUser " + e.getMessage());
                }
            }
        }

        return userModelList;
    }

    public boolean insertUser(String fullname,String email,String password,int roleId){
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql="INSERT into users(email,password,fullname,role_id) values(?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setInt(4,roleId);

            isSuccess=statement.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi query insertUser " + e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối insertUser " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    //Delete user
    public boolean deleteUserById(int id){
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql="DELETE FROM users u WHERE u.id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            isSuccess=statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query deleteUser " + e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteUser " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean updateUserById(String email,String password, String fullname, int roleId ,int id){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE users u SET email=?,password =?,fullname =?,role_id =? WHERE u.id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,fullname);
            statement.setInt(4,roleId);
            statement.setInt(5,id);

            isSuccess=statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query updateUserById "+e.getMessage());

        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối updateUserById "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public List<TaskModel> findTaskByUserId(int id){
        Connection connection = null;
        List<TaskModel> taskModelList = new ArrayList<>();

        try {
            connection=MysqlConfig.getConnection();
            String sql = "SELECT t.id, t.name as task_name,t.start_date,t.end_date,u.fullname,u.email,j.name as job_name,s.name as status_name FROM tasks t " +
                    "INNER JOIN users u ON t.user_id = u.id" +
                    "\t INNER JOIN jobs j ON t.job_id = j.id" +
                    "\t INNER JOIN status s ON t.status_id = s.id WHERE u.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setEmail(resultSet.getString("email"));
                taskModel.setName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setUserName(resultSet.getString("fullname"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatusName(resultSet.getString("status_name"));

                taskModelList.add(taskModel);
            }
        }catch (Exception e){
            System.out.println("Lỗi thực thi query findTaskByUserId "+e.getMessage());

        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findTaskByUserId "+e.getMessage());
                }
            }
        }
        return taskModelList;
    }



}
