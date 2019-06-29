package com.th.demo.controller;


import com.google.gson.Gson;
import com.th.demo.model.user;
import com.th.demo.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.HandshakeResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class userController {
    @Autowired
    userService userService;

    //获取android传来的gson串
    /*public List<user> getJsonParam(HttpServletRequest request) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            StringBuffer sf = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sf.append(line);
            }
            return getListByGson(sf.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //转换gson串
    public List<user> getListByGson(String json) {
        List<user> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                user user = new user();
                user.setId(jsonObject.getString("id"));
                user.setName(jsonObject.getString("name"));
                user.setPassword(jsonObject.getString("password"));
                user.setSex(jsonObject.getString("sex"));
                user.setAge(jsonObject.getString("age"));
                user.setAddress(jsonObject.getString("address"));
                user.setjName(jsonObject.getString("jName"));
                user.setjNumber(jsonObject.getString("jNumber"));

                list.add(user);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    //老人列表查询
    @RequestMapping(value = "/selUser")
    public void selUser(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入老人表单查询");
        List<user> list = new ArrayList<>();
        list = userService.selUser();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    //老人用户登录
    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public void userLogin(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入登录");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        user user = userService.selUserById(id);
        PrintWriter out = response.getWriter();
        if (user == null) {
            out.println("该用户不存在");
        } else if (password.equals(user.getPassword())) {
            out.println("登录成功");
        } else {
            out.println("密码错误");
        }
        out.flush();
        out.close();
    }

    //用户注册功能
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入注册功能了");

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String age = request.getParameter("age");

        user user = new user();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setSex(sex);
        user.setAddress(address);
        user.setAge(age);

        user user1 = userService.selUserById(id);
        PrintWriter out = response.getWriter();
        if (user1 == null) {
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            user.setTime(dateFormat.format(date));
            userService.addUser(user);
            out.print("注册成功");
        } else if (user1 != null) {
            out.println("该用户已存在");
        } else {
            out.println("注册失败");
        }
        out.flush();
        out.close();
    }

    //拨号
    @RequestMapping(value = "/selNumber", method = RequestMethod.POST)
    public void selNumber(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        user user = userService.selUserById(id);
        String number = user.getjNumber();
        System.out.println(number);
        PrintWriter out = response.getWriter();
        out.println(number);
        out.flush();
        out.close();
    }

    //查询用户具体信息
    @RequestMapping(value = "/selMessage", method = RequestMethod.POST)
    public void selMessage(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        System.out.println(id);
        user user = userService.selUserById(id);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();

    }

    //修改用户信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入修改信息");
        String json = request.getParameter("json");
        Gson gson = new Gson();
        user user = gson.fromJson(json, com.th.demo.model.user.class);
        System.out.println(user.getId());
        System.out.println(json);
        int i = userService.updateUser(user);
        System.out.println(i);
        PrintWriter out = response.getWriter();
        out.println(i);
        out.flush();
        out.close();

    }
}
