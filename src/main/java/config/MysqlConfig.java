package config;

import java.sql.Connection;
import java.sql.DriverManager;

// pool size
public class MysqlConfig {
    public static final String url = "jdbc:mysql://localhost:3308/crm_app";
    public static final String username = "root";
    public static final String password = "admin123";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Chỉ định driver sử dụng
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Lỗi kết nối tới cơ sở dữ liệu :" + e.getMessage());
        }


        return connection;
    }
}
