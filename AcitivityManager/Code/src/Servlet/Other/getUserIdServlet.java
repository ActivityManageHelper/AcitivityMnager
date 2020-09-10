package servlet;

import DataBase.BusinessBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import servlet.AccessToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "getUserIdServlet",value = "/getUserIdServlet")
public class getUserIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //OAuth2.0重定向以后，链接里面会带有code参数，直接getParameter获取
        //OAuth2.0重定向以后，链接里面会带有state参数，state是create，则为创建活动，state是my，则为我的活动
        //
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String aty_id = request.getParameter("aty_id");
        System.out.println("code:"+code+" state:"+state+" aty_id="+aty_id);

        AccessToken token = TokenInfo.accessToken;
        String accesstoken = token.getAccessToken();

        String user_id;
        System.out.println("通过access token 和 code 获取 user");
        user_id = getUser(accesstoken, code);
        System.out.println("打印出user_id:"+user_id);
        if (user_id.equals("null"))
        {
            System.out.println("用户未关注企业号");
            response.sendRedirect("html/subscribe.html");
        }
        else
        {
            HttpSession session = request.getSession();
            //TokenInfo.user = getUserInfo(accesstoken, user_id);
            User user = getUserInfo(accesstoken,user_id);
            session.setAttribute("user_id",user.getUser_id());
            session.setAttribute("user_name",user.getName());
            session.setAttribute("user_tel",user.getMobile());
            if(state.equals("create"))
            {
                System.out.println("参数为create，跳转到创建活动界面");
                request.setAttribute("createReturn","default");
                request.getRequestDispatcher( "jsp/createAty.jsp").forward(request,response);
            }
            else if (state.equals("my"))
            {
                System.out.println("参数为my，跳转到我的活动界面");
                response.sendRedirect("jsp/myJoin.jsp");
                //request.getRequestDispatcher( "myActivity.html").forward(request,response);
            }
            else if (state.equals("apply"))
            {
                System.out.println("参数为apply，aty_id为"+aty_id+" 用户想报名活动");
                session.setAttribute("aty_id",aty_id);
                int activity_id =  Integer.parseInt(aty_id);
                BusinessBean businessBean = new BusinessBean();
                List<String> list ;
                list = businessBean.getAtyInfo(activity_id);
                request.setAttribute("aty_name",list.get(0));
                request.setAttribute("aty_date",list.get(1));
                request.setAttribute("aty_place",list.get(2));
                request.setAttribute("aty_content",list.get(3));
                request.setAttribute("user_name",list.get(4));
                request.setAttribute("user_tel",list.get(5));
                request.setAttribute("aty_id",aty_id);
                request.getRequestDispatcher("jsp/applyConfirm.jsp").forward(request,response);
            }
            else if (state.equals("checkin"))
            {
                System.out.println("参数为checkin，aty_id为"+aty_id+" 用户想签到");
                BusinessBean businessBean = new BusinessBean();
                businessBean.checkin(aty_id,user_id);
                response.sendRedirect("html/checkin.html");
            }
        }


    }

    //获取user_id
    private String getUser(String accesstoken, String code) {
        System.out.println("获取user_id的方法被调用");
        NetWorkHelper netHelper = new NetWorkHelper();

        String Url = String.format("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=%s&code=%s", accesstoken, code);
        //{
        //	   "errcode": 0,
        //	   "errmsg": "ok",
        //	   "UserId":"USERID",
        //	   "DeviceId":"DEVICEID"
        //}
        String result = netHelper.getHttpsResponse(Url, "");
        System.out.println("获取到的UserId=" + result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        String UserId = String.valueOf(json.getString("UserId"));
        return UserId;
    }

    //根据user_id获取更多信息
    private User getUserInfo(String accesstoken, String user_id)
    {
        System.out.println("获取user_info的方法被调用");
        NetWorkHelper netHelper = new NetWorkHelper();
        String Url = String.format("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=%s", accesstoken, user_id);
        //   {
        //    "errcode":0,
        //    "errmsg":"ok",
        //    "userid":"zhangsan",
        //    "name":"李四",
        //     "mobile":"13800000000",
        //}
        String result = netHelper.getHttpsResponse(Url, "");
        System.out.println("获取到的用户信息" + result);
        //使用FastJson将Json字符串解析成Json对象
        JSONObject json = JSON.parseObject(result);
        User user = new User();
        user.setUser_id(json.getString("userid"));
        user.setName(json.getString("name"));
        user.setMobile(json.getString("mobile"));
        return user;
    }
}
