package repository;

import config.MysqlConfig;
import model.JobModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRepository {

    public List<JobModel> findAllJob(){
        Connection connection = null;
        List<JobModel> jobModelList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM jobs";
            PreparedStatement statement = MysqlConfig.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                JobModel jobModel = new JobModel();

                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setStartDate(resultSet.getDate("start_date"));
                jobModel.setEndDate(resultSet.getDate("end_date"));

                jobModelList.add(jobModel);
            }

        }catch (Exception e){
            System.out.println("Lỗi thực thi query findAllJob "+ e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findAllJob "+ e.getMessage());
                }
            }
        }

        return jobModelList;
    }

    public JobModel findJobById(int id){
        Connection connection = null;
        JobModel jobModel = new JobModel();

        try {
            connection= MysqlConfig.getConnection();
            String sql = "SELECT * FROM jobs j WHERE j.id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setStartDate(resultSet.getDate("start_date"));
                jobModel.setEndDate(resultSet.getDate("end_date"));
            }

        }catch (Exception e){
            System.out.println("Lỗi thực thi query findJobById "+e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findJobById "+e.getMessage());
                }
            }
        }

        return jobModel;
    }

    //Thêm Job
    public boolean insertJob(String name, Date startDate, Date endDate){
        Connection connection = null;
        boolean isSuccess=false;

        try {
            connection=MysqlConfig.getConnection();
            String sql = "INSERT INTO jobs(name,start_date,end_date) VALUES (?,?,?)";
            PreparedStatement statement= connection.prepareStatement(sql);

            statement.setString(1,name);
            statement.setDate(2,startDate);
            statement.setDate(3,endDate);

            isSuccess= statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query insertJob "+e.getMessage());

        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối insertJob "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public boolean deleteJobById(int id){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "DELETE FROM jobs j WHERE j.id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
        }catch (Exception e){
            System.out.println("Lỗi thực thi query deleteJobById "+e.getMessage());
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối deleteJobById "+e.getMessage());
                }
            }
        }

        return isSuccess;
    }

    public boolean updateJobById(String name, Date startDate, Date endDate, int id){
        Connection connection = null;
        boolean isSuccess = false;

        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE jobs j SET name=?,start_date=?,end_date=? WHERE j.id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setDate(2,startDate);
            statement.setDate(3,endDate);
            statement.setInt(4,id);

            isSuccess = statement.executeUpdate()>0;

        }catch (Exception e){
            System.out.println("Lỗi thực thi query updateJobById "+e.getMessage());
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối updateJobById "+e.getMessage());
                }
            }
        }
        return isSuccess;
    }

}
