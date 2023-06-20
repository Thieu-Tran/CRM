package repository;

import config.MysqlConfig;
import model.RoleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    //Lấy danh sách tất cả các role trong database
    public List<RoleModel> findAllRole(){

        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();

        try {
            String sql="SELECT * FROM roles";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                RoleModel roleModel = new RoleModel();

                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));

                roleModelList.add(roleModel);
            }

        }catch (Exception e){
            System.out.println("Error findAllRole: " + e.getMessage());
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findAllRole " + e.getMessage());
                }
            }
        }

        return roleModelList;
    }

    public RoleModel findRoleById(int id){
        Connection connection = null;
        RoleModel roleModel = new RoleModel();

        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT * FROM roles r WHERE r.id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
            }

        }catch (Exception e){
            System.out.println("Lỗi thực thi query findRoleById "+e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findRoleById "+e.getMessage());
                }
            }
        }

        return roleModel;
    }

    //Thêm role
    public boolean insertRole(String name, String description){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection=MysqlConfig.getConnection();
            String sql = "INSERT INTO roles( name, description ) VALUES (?,?)";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,description);

            isSuccess=statement.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi query insertRole "+ e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối insertRole "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public boolean deleteRoleById(int id){
        Connection connection = null;
        boolean isSuccess= false;

        try {
            connection =MysqlConfig.getConnection();
            String sql="DELETE FROM roles r WHERE r.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            isSuccess = statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query deleteRoleById "+e.getMessage());

        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối deleteRoleById "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public boolean updateRoleById(String name,String description,int id){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE roles o SET name=?,description =? WHERE o.id =?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setInt(3,id);

            isSuccess=statement.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi query updateRoleById "+e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối updateRoleById "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

}
