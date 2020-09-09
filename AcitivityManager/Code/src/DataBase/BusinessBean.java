package DataBase;

import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BusinessBean {


    //创建活动，添加到数据库：新增活动
    public boolean add_activity(String user_id,String name,String tel,String aty_name,String date,String place,String content )
    {
        boolean flag = false;
        DBAccessBean db=new DBAccessBean();
        if (db.createConn()){
            String sql="insert into activity ( aty_name, aty_date, aty_place, aty_content, user_id, user_name, user_tel )" +
                    "values ('"+ aty_name+"','"+ date+"','"+ place+"','"+content+"','"+user_id+"','"+name+"','"+tel+"')";
            flag = db.update(sql);
            db.closeRs();db.closeStmt();db.closeConn();
        }
        return flag;
    }



    //通过user_id或者aty_id和表名查询表的信息
    public List<Map<String, String>> getTableInfo(String id,String table_name) {
        DBAccessBean db = new DBAccessBean();
        ResultSet resultSet;
        List<Map<String,String>> list = new ArrayList<>();
        String sql=null;
        if (db.createConn())
        {
            if (table_name.equals("activity"))
            {
                sql = "select * from activity where user_id='"+id+"'";
            }else if (table_name.equals("apply"))
            {
                sql = "select * from apply where aty_id='"+id+"'";
            }
            System.out.println(sql);
            resultSet = db.query(sql);
            try{
                list = convertList(resultSet,table_name);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            db.closeConn();db.closeStmt();db.closeRs();
        }
        return list;
    }


    //把ResultSet的值存到List中
    private static List<Map<String,String>> convertList(ResultSet rs,String table_name) throws SQLException {
        List<Map<String,String>> list = new ArrayList<>();
        ResultSetMetaData md = rs.getMetaData();//获取整个表的信息
        int columnCount = md.getColumnCount();//获取表的字段数量

        //遍历结果集
        while (rs.next()) {
            Map<String,String> rowData = new LinkedHashMap<>();//创建Map 键值对

            //假设字段的数量为7，则从1到7，把每个字段的名字和它对应的取值 按照键值对的形式存入Map
            for (int i = 1; i <= columnCount; i++)
            {
                if ((i==1)&&(table_name.equals("activity")))
                {
                    rowData.put(md.getColumnName(i), String.valueOf(rs.getInt(i)));//若为activity表 获取aty_id为int类型 转成String
                }
                else {
                    rowData.put(md.getColumnName(i), rs.getString(i));
                }
            }
            list.add(rowData);
        }
        return list;
    }

    //通过活动id获取该活动的所有信息
    public List<String> getAtyInfo(int activity_id) {
        DBAccessBean db = new DBAccessBean();
        ResultSet resultSet;
        List<String> list = new ArrayList<>();
        if (db.createConn())
        {
            String sql = "select * from activity where aty_id="+activity_id;
            System.out.println(sql);
            resultSet =db.query(sql);
            try {
                while (resultSet.next())
                {
                    list.add(resultSet.getString(2));
                    list.add(resultSet.getString(3));
                    list.add(resultSet.getString(4));
                    list.add(resultSet.getString(5));
                    list.add(resultSet.getString(7));
                    list.add(resultSet.getString(8));
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            db.closeRs();db.closeStmt();db.createConn();
        }
        return list;
    }

    //查询学号user_id参加的活动信息
    public List<Map<String,String>> getJoinedActivity(String user_id)
    {
        DBAccessBean db = new DBAccessBean();
        ResultSet resultSet;
        List<String> list_of_atyId = new ArrayList<>();
        List<Map<String,String>> list_of_myJoin = new ArrayList<>();

        //获取该学号参加的所有活动的id，存入list
        if (db.createConn())
        {
            String sql = "select aty_id from apply where user_id='"+user_id+"'";
            System.out.println(sql);
            resultSet =db.query(sql);
            try {
                while (resultSet.next())
                {
                    list_of_atyId.add(resultSet.getString(1));
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        //遍历存了活动id的list，取出每一个list，查询数据库，把结果存入 List<Map<String,String>>
        for (String aty_id:list_of_atyId)
        {
            int aty_id_int = Integer.parseInt(aty_id);
            String sql = "select * from activity where aty_id="+aty_id_int;
            System.out.println(sql);
            resultSet = db.query(sql);
            try {
                ResultSetMetaData md = resultSet.getMetaData();//获取resultSet的信息
                int columnCount = md.getColumnCount();//获取resultSet的字段数量

                //遍历resultSet结果集
                while (resultSet.next())
                {
                    Map<String,String> rowData = new LinkedHashMap<>();//创建Map 键值对
                    //假设字段的数量为7，则从1到7，把每个字段的名字和它对应的取值 按照键值对的形式存入Map
                    for (int i = 1; i <= columnCount; i++)
                    {
                        if (i==1)
                        {
                            rowData.put(md.getColumnName(i), String.valueOf(resultSet.getInt(i)));//获取aty_id为int类型 转成String
                        }
                        else {
                            rowData.put(md.getColumnName(i), resultSet.getString(i));
                        }
                    }
                    list_of_myJoin.add(rowData);
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        db.closeRs();db.closeStmt();db.createConn();
        return list_of_myJoin;
    }

    //往apply表中插入一条数据
    public int applyActivity(String aty_id, String user_id, String name, String tel) {
        int back =-1;
        DBAccessBean db = new DBAccessBean();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = formatter.format(date);
        if (db.createConn()){
            String judge = "select * from apply where user_id='"+user_id+"' and aty_id='"+aty_id+"'";
            String sql="insert into apply "+
                    "values ('"+ aty_id+"','"+ user_id+"','"+ name+"','"+tel+"','"+time+"','否')";
            System.out.println(sql);
            try
            {
                if (db.query(judge).next())
                {
                   System.out.println("活动id和用户id已存在，该用户已经报名");
                   back = 0;
                }
                else
                {
                    db.update(sql);
                    System.out.println("插入报名信息成功");
                    back =1;
                }

            }catch (Exception e)
            {
                e.printStackTrace();
            }

            db.closeRs();db.closeStmt();db.closeConn();
        }
        return back;
    }

    //签到
    public void checkin(String aty_id, String user_id) {
        DBAccessBean db = new DBAccessBean();
        if (db.createConn())
        {
            String sql = "UPDATE apply SET `check`='是' WHERE aty_id='"+aty_id+"' and user_id='"+user_id+"'";
            System.out.println(sql);
            db.update(sql);
        }
        db.closeRs();db.closeStmt();db.closeConn();
    }

    public JSONObject getLotteryUserInfo(String aty_id) {
        DBAccessBean db = new DBAccessBean();
        ResultSet resultSet;
        JSONObject jsonObject = new JSONObject();
        if (db.createConn())
        {
            String sql =  "select * from apply where aty_id='"+aty_id+"'";
            System.out.println(sql);
            resultSet = db.query(sql);
            int rowCount=0;
            try {
                while (resultSet.next())
                {
                    rowCount++;
                }
                resultSet = db.query(sql);
                Random random =new Random();
                int user_row_id = random.nextInt(rowCount)+1;
                int k =0;
                while (resultSet.next())
                {
                    k++;
                    if (k==user_row_id)
                    {
                        String user_name = resultSet.getString(3);
                        String user_id = resultSet.getString(2);
                        String tel = resultSet.getString(4);
                        jsonObject.put("user_name",user_name);
                        jsonObject.put("user_id",user_id);
                        jsonObject.put("tel",tel);
                    }
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        db.closeRs();db.closeStmt();db.closeConn();
        return jsonObject;
    }
}
