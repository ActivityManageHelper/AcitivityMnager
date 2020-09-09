package DataBase;

import java.sql.*;

public class DBAccessBean {
    private String drv="com.mysql.jdbc.Driver";
    String userMySql="root";
    String passwordMySql="Abc906054541.";
    String urlMySql = "jdbc:mysql://47.110.65.172:3306/activity?characterEncoding=UTF-8";

    //String passwordMySql="906054541";
    //String urlMySql = "jdbc:mysql://localhost:3306/activity?characterEncoding=UTF-8";

    private Connection conn=null;
    private Statement stmt=null;
    private ResultSet rs=null;

    public String getDrv(){return drv;}
    public void setDrv(String drv){this.drv=drv;}

    public String getUrl(){return urlMySql;}
    public void setUrl(String url){this.urlMySql=url;}

    public String getUsr(){return userMySql;}
    public void setUsr(String usr){this.userMySql=usr;}

    public String getPwd(){return passwordMySql;}
    public void setPwd(String pwd){this.passwordMySql=pwd;}

    public Connection getConn(){return conn;}
    public void setConn(Connection conn){this.conn=conn;}

    public Statement getStmt(){return stmt;}
    public void setStmt(Statement stmt){this.stmt=stmt;}

    public ResultSet getRs(){return rs;}
    public void setRs(ResultSet rs){this.rs=rs;}

    public boolean createConn(){                                    //创建数据库连接
        boolean b=false;
        try {
            Class.forName(drv);
            conn=DriverManager.getConnection(urlMySql,userMySql,passwordMySql);
            if(conn!=null)
            {
                System.out.println("jdbc driver is loaded");
                b=true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public boolean update(String sql){                                  //更新
        boolean b=false;
        try {
            stmt=conn.createStatement();
            stmt.execute(sql);
            b=true;
            System.out.println("Updata sucess");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return b;
    }

    public ResultSet query(String sql){                                       //查询
        try {
            stmt=conn.createStatement();
            rs=stmt.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public boolean next(){                                                  //验证rs是否为空
        boolean b=false;
        try {
            if(rs.next()) b=true;
        }catch (Exception e){

        }
        return b;
    }

    public String getValue(String field){                                     //取得当前记录的字段的值
        String value=null;
        try {
            if(rs!=null)
            {
                rs.next();
                value = rs.getString(field);
                System.out.println(value);
            }
        } catch (Exception e) {

        }
        return value;
    }

    //关闭
    public void closeConn(){
        try {
            if (conn!=null) conn.close();
        }catch (SQLException e){

        }
    }

    public void closeStmt(){
        try {
            if (stmt!=null)stmt.close();
        } catch (SQLException e) {

        }
    }

    public void closeRs(){
        try {
            if (rs!=null) rs.close();
        } catch (SQLException e) {

        }
    }
}
