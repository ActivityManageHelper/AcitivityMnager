package servlet;

public class User {

    //User实体类
    private String user_id;
    private String name;
    private String mobile;  //手机号码

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){this.name = name;}

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
