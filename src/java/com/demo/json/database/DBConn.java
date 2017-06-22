package com.demo.json.database;

import java.sql.*;


/**
 * Created by theking on 2017-03-21.
 */
public class DBConn {


        public static void main(String[] args){

            // 驱动程序名
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

            // URL指向要访问的数据库名scutcs
            String url = "jdbc:sqlserver://localhost:1433/@AAD";

            // MySQL配置时的用户名
            String user = "sa";

            // MySQL配置时的密码
            String password = "HJSPASS#1";

            try {
                // 加载驱动程序
                Class.forName(driver);

                // 连续数据库
                Connection conn = DriverManager.getConnection(url, user, password);

                if(!conn.isClosed())
                    System.out.println("Succeeded connecting to the Database!");

                // statement用来执行SQL语句
                Statement statement = conn.createStatement();

                // 要执行的SQL语句
                String sql = "select * from t_stored_item";

                // 结果集
                ResultSet rs = statement.executeQuery(sql);

                System.out.println("-----------------");
                System.out.println("执行结果如下所示:");
                System.out.println("-----------------");
                System.out.println(" 学号" + "\t" + " 姓名");
                System.out.println("-----------------");

                String name = null;

                while(rs.next()) {

                    // 选择sname这列数据
                    name = rs.getString("sname");

                    // 首先使用ISO-8859-1字符集将name解码为字节序列并将结果存储新的字节数组中。
                    // 然后使用GB2312字符集解码指定的字节数组
                    name = new String(name.getBytes("ISO-8859-1"),"GB2312");

                    // 输出结果
                    System.out.println(rs.getString("sno") + "\t" + name);
                }

                rs.close();
                conn.close();

            } catch(ClassNotFoundException e) {


                System.out.println("Sorry,can\'t find the Driver!");
                e.printStackTrace();


            } catch(SQLException e) {


                e.printStackTrace();


            } catch(Exception e) {


                e.printStackTrace();


            }
        }
    }