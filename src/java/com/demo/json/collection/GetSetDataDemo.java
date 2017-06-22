package com.demo.json.collection;

import java.sql.*;

public class GetSetDataDemo {

    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=AAD";
        //MySQL配置时的用户名
        String user = "sa";
        //MySQL配置时的密码
        String password = "HJSPASS#1";
        String table_name = "t_employee";
        String idString = "stored";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            PreparedStatement psql1;
            //要执行的SQL语句
            psql1 = con.prepareStatement("select item_number,location_id from t_stored_item where item_number = ?");
            psql1.setString(1, "BJGY-4713080");
            psql1.executeQuery();
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = psql1.executeQuery();
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");
            System.out.println("-----------------");
            System.out.println("物料号" + "\t" + "货位");
            System.out.println("-----------------");

            String item_number = null;
            String location_id = null;
            while (rs.next()) {
                //获取item_number这列数据
                item_number = rs.getString("item_number");
                //获取location_id这列数据
                location_id = rs.getString("location_id");

                //输出结果
                System.out.println(item_number + "\t" + location_id);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can\'t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
    }

}