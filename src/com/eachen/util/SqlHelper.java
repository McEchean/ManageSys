package com.eachen.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SqlHelper {
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static String url = "";
    private static String username = "";
    private static String driver="";
    private static String password="";

    private static Properties pp = null;
    private static InputStream fis = null;


    static {
        try {
            pp = new Properties();
//            fis = new FileInputStream("dbinfo.properties");// Tomcat默认主目录?
            //当使用java web的时候，读取文件要使用类加载器,类加载器默认从src目录下开始
            fis = SqlHelper.class.getClassLoader().getResourceAsStream("dbinfo.properties");
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            password = pp.getProperty("password");
            Class.forName(driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //得到连接
    public static Connection getConnection() throws SQLException {
        Connection connection1 = DriverManager.getConnection(url,username,password);
        return connection1;
    }

    //返回一个result
    public static ArrayList<Object[]> executeQuery(String sql,String[] paramters) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Object[]> list = new ArrayList();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if(paramters != null && !"".equals(paramters)) {
                for(int i = 1; i <= paramters.length; i++) {
                    preparedStatement.setObject(i,paramters[i-1]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int coulmn = resultSetMetaData.getColumnCount();
            int c;
            while (resultSet.next()) {
                Object[] obj = new Object[coulmn];
                for(int i = 0; i < coulmn; i++) {

                    obj[i] = resultSet.getObject(i + 1);
//                    c = (int) obj[i];
                }
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
            preparedStatement.close();
            resultSet.close();
        }
        return list;
    }
    public static void close() {
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeUpdate(String sql, String[] paramters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            if(paramters != null) {
                for(int i = 0; i < paramters.length; i++) {
                    preparedStatement.setString(i + 1,paramters[i]);
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public static PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static Connection getConnection_u() {
        return connection;
    }


}
