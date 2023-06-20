package repository;

import config.MysqlConfig;
import model.StatusModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {

    public List<StatusModel> findAllStatus(){
        Connection connection = null;
        List<StatusModel> statusModelList = new ArrayList<>();

        try {
            connection = MysqlConfig.getConnection();
            String sql="SELECT * FROM status";
            PreparedStatement statement =  connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                StatusModel statusModel = new StatusModel();
                statusModel.setId(resultSet.getInt("id"));
                statusModel.setName(resultSet.getString("name"));

                statusModelList.add(statusModel);
            }

        }catch (Exception e){
            System.out.println("Lỗi thực thi query findAllStatus "+ e.getMessage());
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (Exception e){
                    System.out.println("Lỗi đóng kết nối findAllStatus "+e.getMessage());
                }
            }
        }

        return statusModelList;
    }
}
