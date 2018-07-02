package com.eachen.servive;

import com.eachen.domain.User;
import com.eachen.util.SqlHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserSevive {

    public boolean modiferUser(User user) {
        boolean b = true;
        String sql = "update users4login set username=\"" + user.getUsername() + "\"," +
                "email=\"" + user.getEmail() + "\"," +
                "grade=" + user.getGrade() + "," +
                "password=\"" + user.getPassowrd() +
                "\" where id=" + user.getId() + ";";
        try {
            SqlHelper.executeUpdate(sql,null);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    public ArrayList getObjectByS(String id,String style) {
        ArrayList list = new ArrayList();
        if("null".equals(style)) {
            String sql = "select * from users4login where id =?";
            String[] params = {id};
            try {
                ArrayList<Object[]> list1 = (ArrayList<Object[]>) SqlHelper.executeQuery(sql,params);
                if(list1 != null) {
                    for (Object[] obj: list1) {
                        User user = new User();
                        user.setId((Integer) obj[0]);
                        user.setUsername((String) obj[1]);
                        user.setEmail((String) obj[2]);
                        user.setGrade((Integer) obj[3]);
                        user.setPassowrd((String) obj[4]);
                        list.add(user);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }else if("on".equals(style)) {
            String sql = "select * from users4login where id like '%" +id+ "%'";
            try {
                ArrayList<Object[]> list1 = (ArrayList<Object[]>) SqlHelper.executeQuery(sql,null);
                if(list1 != null) {
                    for (Object[] obj: list1) {
                        User user = new User();
                        user.setId((Integer) obj[0]);
                        user.setUsername((String) obj[1]);
                        user.setEmail((String) obj[2]);
                        user.setGrade((Integer) obj[3]);
                        user.setPassowrd((String) obj[4]);
                        list.add(user);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }else {
            return list;
        }
    }

    public boolean addUser(User user) {
        boolean b = true;
        String sql = "insert into users4login values ("+user.getId()+",\""+user.getUsername()+"\",\""+user.getEmail()+"\","+user.getGrade()+",\""+user.getPassowrd()+"\");";
        try {
            SqlHelper.executeUpdate(sql,null);
        }catch (Exception e) {
            e.printStackTrace();
            b=false;
        }
        return b;
    }

    public boolean delUser(String id) {
        boolean b = true;
        String sql = "delete from users4login where id = ?";
        String[] params = {id};
        try {
            SqlHelper.executeUpdate(sql,params);
        }catch (Exception e) {
            e.printStackTrace();
            b = false;
        }
        return b;
    }
    //ArrayList中封装User对象
    public ArrayList getUsersByPages(int pageNow,int pageSize) {
        ArrayList arrayList = new ArrayList();
        String sql = "select * from users4login limit " + (pageNow-1) * pageSize + "," + pageSize + ";";
        try {
            ArrayList<Object[]> list1 = (ArrayList<Object[]>) SqlHelper.executeQuery(sql,null);
            if(list1 != null) {
                for (Object[] obj: list1) {
                    User user = new User();
                    user.setId((Integer) obj[0]);
                    user.setUsername((String) obj[1]);
                    user.setEmail((String) obj[2]);
                    user.setGrade((Integer) obj[3]);
                    user.setPassowrd((String) obj[4]);
                    arrayList.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return arrayList;
        /*try {
            ResultSet resultSet = SqlHelper.executeQuery(sql,null);
            while(resultSet.next()) {
                User u = new User();
                u.setId(resultSet.getInt(1));
                u.setUsername(resultSet.getString(2));
                u.setEmail(resultSet.getString(3));
                u.setGrade(resultSet.getInt(4));
                //千万别忘记添加进arraylist
                arrayList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
//        finally {
//            SqlHelper.close();
//        }
        return arrayList;
    }

    public Long getPageCount(int pageSize) {
        Long rowCount = 0L;
        String sql = "select count(*) from users4login;";
        try {
            ArrayList<Object[]> list = SqlHelper.executeQuery(sql,null);
            rowCount = (Long) list.get(0)[0];
//            resultSet.next();
//            rowCount = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            SqlHelper.close();
//        }
        return rowCount % pageSize == 0 ? rowCount/pageSize :rowCount/pageSize + 1;
    }

    public Object getOneObj(User user) {
        String sql = "select * from users4login where id = ?";
        String[] params = {user.getId()+""};
        try {
            ArrayList<Object[]> list1 = (ArrayList<Object[]>) SqlHelper.executeQuery(sql,params);
            if(list1 != null) {
                for (Object[] obj: list1) {
                    user = new User();
                    user.setId((Integer) obj[0]);
                    user.setUsername((String) obj[1]);
                    user.setEmail((String) obj[2]);
                    user.setGrade((Integer) obj[3]);
                    user.setPassowrd((String) obj[4]);
//                    list.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*return list;
        try {
            ResultSet resultSet = SqlHelper.executeQuery(sql,params);
            if(resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setGrade(resultSet.getInt(4));
                user.setPassowrd(resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            SqlHelper.close();
        }*/
        return user;
    }

    public Map checkUser(User user){
        boolean b = false;
        Map<String,String> result = new HashMap<>();
        String sql = "select * from users4login where id = ? and password = ?";
        String[] parsmters = {user.getId() + "",user.getPassowrd() + ""};
        try {
            ArrayList<Object[]> list = SqlHelper.executeQuery(sql,parsmters);
            if(list != null) {
                if(list.size() == 1) {
                    String username = (String) list.get(0)[1];
                    b = true;
                    result.put("username",username);
                    result.put("result",String.valueOf(b));
                }
            }else {
                System.out.println("还是失败了");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*finally {
//            SqlHelper.close();
        }*/
        return result;

       /* Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //连接数据库
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydata?useSSL=false";
            connection = DriverManager.getConnection(url,"root","admin");

            preparedStatement = connection.prepareStatement("select * from users where id=? and password=?;");
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getPassowrd());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                b = true;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null || preparedStatement != null || connection != null) {
                try {
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resultSet = null;
                preparedStatement = null;
                connection = null;
            }
        }
        return b;*/
    }
}
