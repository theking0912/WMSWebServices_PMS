package com.demo.json.collection;

import java.sql.*;

public class GetSetDataDemo {

    public static void main(String[] args) {
        //����Connection����
        Connection con;
        //����������
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //URLָ��Ҫ���ʵ����ݿ���mydata
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=AAD";
        //MySQL����ʱ���û���
        String user = "sa";
        //MySQL����ʱ������
        String password = "HJSPASS#1";
        String table_name = "t_employee";
        String idString = "stored";
        //������ѯ�����
        try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = con.createStatement();
            PreparedStatement psql1;
            //Ҫִ�е�SQL���
            psql1 = con.prepareStatement("select item_number,location_id from t_stored_item where item_number = ?");
            psql1.setString(1, "BJGY-4713080");
            psql1.executeQuery();
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            ResultSet rs = psql1.executeQuery();
            System.out.println("-----------------");
            System.out.println("ִ�н��������ʾ:");
            System.out.println("-----------------");
            System.out.println("���Ϻ�" + "\t" + "��λ");
            System.out.println("-----------------");

            String item_number = null;
            String location_id = null;
            while (rs.next()) {
                //��ȡitem_number��������
                item_number = rs.getString("item_number");
                //��ȡlocation_id��������
                location_id = rs.getString("location_id");

                //������
                System.out.println(item_number + "\t" + location_id);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException e) {
            //���ݿ��������쳣����
            System.out.println("Sorry,can\'t find the Driver!");
            e.printStackTrace();
        } catch (SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            System.out.println("���ݿ����ݳɹ���ȡ����");
        }
    }

}