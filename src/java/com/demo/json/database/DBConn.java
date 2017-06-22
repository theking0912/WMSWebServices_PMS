package com.demo.json.database;

import java.sql.*;


/**
 * Created by theking on 2017-03-21.
 */
public class DBConn {


        public static void main(String[] args){

            // ����������
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

            // URLָ��Ҫ���ʵ����ݿ���scutcs
            String url = "jdbc:sqlserver://localhost:1433/@AAD";

            // MySQL����ʱ���û���
            String user = "sa";

            // MySQL����ʱ������
            String password = "HJSPASS#1";

            try {
                // ������������
                Class.forName(driver);

                // �������ݿ�
                Connection conn = DriverManager.getConnection(url, user, password);

                if(!conn.isClosed())
                    System.out.println("Succeeded connecting to the Database!");

                // statement����ִ��SQL���
                Statement statement = conn.createStatement();

                // Ҫִ�е�SQL���
                String sql = "select * from t_stored_item";

                // �����
                ResultSet rs = statement.executeQuery(sql);

                System.out.println("-----------------");
                System.out.println("ִ�н��������ʾ:");
                System.out.println("-----------------");
                System.out.println(" ѧ��" + "\t" + " ����");
                System.out.println("-----------------");

                String name = null;

                while(rs.next()) {

                    // ѡ��sname��������
                    name = rs.getString("sname");

                    // ����ʹ��ISO-8859-1�ַ�����name����Ϊ�ֽ����в�������洢�µ��ֽ������С�
                    // Ȼ��ʹ��GB2312�ַ�������ָ�����ֽ�����
                    name = new String(name.getBytes("ISO-8859-1"),"GB2312");

                    // ������
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