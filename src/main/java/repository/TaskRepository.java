package repository;

import config.MysqlConfig;
import model.TaskModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public List<TaskModel> findAllTask(){
        Connection connection = null;
        List<TaskModel> taskModelList = new ArrayList<>();

        try {
            connection = MysqlConfig.getConnection();
            String sql = "SELECT t.id, t.name as task_name,t.start_date,t.end_date,u.fullname,j.name as job_name,s.name as status_name FROM tasks t INNER JOIN users u ON t.user_id = u.id \n" +
                    "\t\t\t\t\t  INNER JOIN jobs j ON t.job_id = j.id \n" +
                    "\t\t\t\t\t  INNER JOIN status s ON t.status_id = s.id";
            PreparedStatement statement= connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                TaskModel taskModel =  new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("task_name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setUserName(resultSet.getString("fullname"));
                taskModel.setJobName(resultSet.getString("job_name"));
                taskModel.setStatusName(resultSet.getString("status_name"));

                taskModelList.add(taskModel);
            }
        }catch (Exception e){
            System.out.println("Lỗi thực thi query TaskRepository "+e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối TaskRepository "+e.getMessage());
                }
            }
        }
        return taskModelList;
    }

    public TaskModel findTaskById(int id){

        Connection connection= null;
        TaskModel taskModel = new TaskModel();

        try {
            connection = MysqlConfig.getConnection();
            String sql="SELECT * FROM tasks t WHERE t.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getDate("start_date"));
                taskModel.setEndDate(resultSet.getDate("end_date"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setJobId(resultSet.getInt("job_id"));
                taskModel.setStatusId(resultSet.getInt("status_id"));
            }

        }catch (Exception e){
            System.out.println("Lỗi thực thi query findTaskById "+e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findTaskById "+e.getMessage());
                }
            }
        }
        return taskModel;
    }

    public List<TaskModel> findTaskByEmail(String email){
        Connection connection = null;
        List<TaskModel> taskModelList = new ArrayList<>();

        try {
            connection=MysqlConfig.getConnection();
            String sql = "SELECT t.id, t.name as task_name,t.start_date,t.end_date,u.fullname,u.email,j.name as job_name,s.name as status_name FROM tasks t " +
                    "INNER JOIN users u ON t.user_id = u.id" +
                    "\t INNER JOIN jobs j ON t.job_id = j.id" +
                    "\t INNER JOIN status s ON t.status_id = s.id WHERE u.email=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,email);
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
            System.out.println("Lỗi thực thi query findTaskByEmail "+e.getMessage());

        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findTaskByEmail "+e.getMessage());
                }
            }
        }
        return taskModelList;
    }

    public boolean insertTask(String name,int jobId, int userId, Date startDate, Date endDate, int statusId){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into tasks(name,job_id,user_id,start_date,end_date,status_id) Values (?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,name);
            statement.setInt(2,jobId);
            statement.setInt(3,userId);
            statement.setDate(4,startDate);
            statement.setDate(5,endDate);
            statement.setInt(6,statusId);

            isSuccess = statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query insertTask "+e.getMessage());

        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối insertTask "+ e.getMessage());
                }

            }
        }

        return  isSuccess;
    }

    public boolean deleteTaskById(int id){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "DELETE FROM tasks t WHERE t.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            isSuccess= statement.executeUpdate()>0;
        }catch (Exception e){
            System.out.println("Lỗi thực thi query deleteTaskById "+e.getMessage());
        }finally {
            if(connection!=null){
                try {
                   connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối deleteTaskById "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public boolean updateTaskById(String name,Date startDate,Date endDate,int userId,int jobId,int statusId,int id){
        Connection connection = null;
        boolean isSuccess= false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE tasks t SET name=?,start_date=?,end_date=?,user_id=?,job_id=?,status_id=? WHERE t.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1,name);
            statement.setDate(2,startDate);
            statement.setDate(3,endDate);
            statement.setInt(4,userId);
            statement.setInt(5,jobId);
            statement.setInt(6,statusId);
            statement.setInt(7,id);

            isSuccess=statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query updateTaskById "+e.getMessage());
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối updateTaskById "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

}
