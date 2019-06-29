package com.th.demo.controller;

import com.google.gson.Gson;
import com.th.demo.model.user;
import com.th.demo.model.volunteer;
import com.th.demo.service.volunteerService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.HandshakeResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class volunteerController {
    @Autowired
    volunteerService vtService;

    /*public List<volunteer> getJsonParam(HttpServletRequest request) {
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

    public List<volunteer> getListByGson(String json) {
        List<volunteer> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    //志愿者列表查询
    @RequestMapping(value = "/selVolunteer")
    public void selVolunteer(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入志愿者表单查询");
        List<volunteer> list = new ArrayList<>();
        list = vtService.selVolunteer();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    //志愿者登录
    @RequestMapping(value = "volunteerLogin", method = RequestMethod.POST)
    public void volunteerLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
        volunteer vt = vtService.selVolunteerById(id);
        PrintWriter out = response.getWriter();
        if (vt == null) {
            out.println("该用户不存在");
        } else if (password.equals(vt.getPassword())) {
            out.println("登录成功");
        } else {
            out.println("密码错误");
        }
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/addVolunteer", method = RequestMethod.POST)
    public void addVolunteer(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("进入志愿者注册");
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        volunteer vt = new volunteer();
        vt.setId(id);
        vt.setPassword(password);
        vt.setSex(sex);
        vtService.addVolunteer(vt);
        out.println("注册成功");
        out.flush();
        out.close();
    }

    //查看志愿者信息
    @RequestMapping(value = "/selVtMessage",method = RequestMethod.POST)
    public void selVtMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入修改信息");
        String id = request.getParameter("id");
        Gson gson = new Gson();
        volunteer vt = vtService.selVolunteerById(id);
        String json = gson.toJson(vt);
        PrintWriter out = response.getWriter();
        out.println(json);
        out.flush();
        out.close();
    }

    //修改志愿者信息
    @RequestMapping(value = "/updateVt",method = RequestMethod.POST)
    public void updateVt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("进入志愿者修改信息");
        String json = request.getParameter("json");
        System.out.println(json);
        Gson gson = new Gson();
        volunteer vt = gson.fromJson(json,volunteer.class);

        int i = vtService.updateVolunteer(vt);
        System.out.println(i);
        PrintWriter out = response.getWriter();
        out.println(i);
        out.flush();
        out.close();
    }
}
