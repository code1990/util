import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class DbUtil {

    private static final String MYSQL_URL = "jdbc:mysql://127.0.0.1:3306/report?characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String MYSQL_NAME = "root";
    private static final String MYSQL_PWD = "123456";

    public static Connection getMYSQLConnection(){
        Connection connection = null;
        try{
            // 1.加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("加载驱动成功！");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try {
            // 2.连接
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_NAME, MYSQL_PWD);
            System.out.println("连接数据库成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败！");
        }
        return connection;
    }

    @Test
    public void insertMySql(){
        String sql = "INSERT INTO bo_original_revenue_new_view VALUES (" +
                "'北美','N','0','1','2098.87','0.00','27.54','0.00'" +
                ");\n";
        try {
            PreparedStatement ps = getMYSQLConnection().prepareStatement(sql);
            int xx = ps.executeUpdate();
            System.out.println(xx);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testConnection() {
        List<String[]> result = null;
        Connection connection= getMYSQLConnection();
        try {
            result = CsvReader.read(new FileInputStream(new File("F:\\桌面\\bakup\\导入测试\\201801销售贡献.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i =0;i<1000;i++) {
            String[] array = result.get(i);
            if (array.length>87) {
                StringBuilder sb = new StringBuilder();
                sb.append("'"+array[43].replace(",","")+"',");
                sb.append("'"+array[72].replace(",","")+"',");
                sb.append("'"+array[81].replace(",","")+"',");
                sb.append("'"+"1"+"',");
                sb.append("'"+array[82].replace(",","")+"',");
                sb.append("'"+array[85].replace(",","")+"',");
                sb.append("'"+array[87].replace(",","")+"',");
                sb.append("'"+"0.00"+"'");
                String sql = "INSERT INTO bo_original_revenue_new_view VALUES (" +
                        sb.toString() +
                        ");\n";
                try {
                    PreparedStatement ps = connection.prepareStatement(sql);
                    int xx = ps.executeUpdate();
                    System.out.println(xx);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
