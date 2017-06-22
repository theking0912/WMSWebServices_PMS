package com.tools;

/**
 * Created by theking on 2017-03-21.
 */

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class DBHelper {

    private String sql;    //Ҫ�����sql���

    public void setSql(String sql) {
        this.sql = sql;
    }

    private List sqlValues;  //sql���Ĳ���

    public void setSqlValues(List sqlValues) {
        this.sqlValues = sqlValues;
    }

    private Connection con;  //���Ӷ���

    public void setCon(Connection con) {
        this.con = con;
    }

    public DBHelper() {
        this.con = getConnection();  //��Connection�Ķ��󸳳�ֵ
    }

    /**
     * ��ȡ���ݿ�����
     *
     * @return
     */
    private Connection getConnection() {

        String driver_class = null;
        String driver_url = null;
        String database_user = null;
        String database_password = null;
        try {
            InputStream fis = this.getClass().getResourceAsStream("/db.properties");  //�������ݿ������ļ����ڴ���
            Properties p = new Properties();
            p.load(fis);

            driver_class = p.getProperty("driver_class");      //��ȡ���ݿ������ļ�
            driver_url = p.getProperty("driver_url");
            database_user = p.getProperty("database_user");
            database_password = p.getProperty("database_password");


            Class.forName(driver_class);
            con = DriverManager.getConnection(driver_url, database_user, database_password);


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }


    /**
     * �ر����ݿ�
     *
     * @param con
     * @param pst
     * @param rst
     */
    private void closeAll(Connection con, PreparedStatement pst, ResultSet rst) {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    /**
     * ����
     *
     * @return
     */
    public String executeQuery() {
        Result result = null;
        ResultSet rst = null;
        String json = "";
        PreparedStatement pst = null;
        try {

            pst = con.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {  //��sql����д���ռλ��ʱ
                setSqlValues(pst, sqlValues);
            }
            rst = pst.executeQuery();
            json = this.loopMap(rst);
            result = ResultSupport.toResult(rst);  //һ��Ҫ�ڹر����ݿ�֮ǰ���ת��

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.closeAll(con, pst, rst);
        }

        return json;
    }


    /**
     * ��ɾ��
     *
     * @return
     */
    public int executeUpdate() {
        int result = -1;
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {  //��sql����д���ռλ��ʱ
                setSqlValues(pst, sqlValues);
            }
            result = pst.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            this.closeAll(con, pst, null);
        }


        return result;
    }


    /**
     * ��sql����е�ռλ����ֵ
     *
     * @param pst
     * @param sqlValues
     */
    private void setSqlValues(PreparedStatement pst, List sqlValues) {
        for (int i = 0; i < sqlValues.size(); i++) {
            try {
                pst.setObject(i + 1, sqlValues.get(i));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String loopMap(ResultSet resultSet) throws SQLException {
        ResultSetMetaData md = resultSet.getMetaData();
        HashMap<String, String> map = new HashMap<>();
        ArrayList<HashMap<String, String>> al = new ArrayList<HashMap<String, String>>();
        String json = "";
        while (resultSet.next()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {// ���ݿ���� 1 ��ʼ
                String c = md.getColumnName(i);
                String v = resultSet.getString(c);
                map.put(c, v);
            }
            al.add(map);
            json = al.toString();
        }
        return json;
    }
}