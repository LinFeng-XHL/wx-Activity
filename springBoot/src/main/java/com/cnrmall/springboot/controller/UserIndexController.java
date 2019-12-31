package com.cnrmall.springboot.controller;


import com.alibaba.druid.util.StringUtils;
import com.cnrmall.springboot.controller.viewobject.UserVO;
import com.cnrmall.springboot.entity.User;
import com.cnrmall.springboot.response.CommonReturnType;
import com.cnrmall.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class UserIndexController {

    /**
     * 跳转到后台主页面
     * /activity/user/redirctIndex
     * */

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String listUser(Model model) {

        List<User> listUser = userService.listUser();

        //存放用户的集合
        List<UserVO> listUserVO=new ArrayList<>();
        for (int i = 0; i < listUser.size(); i++) {
            User user = listUser.get(i);
            //调用转换对象的方法
            UserVO userVO=convertUserFromUserVO(user);
            listUserVO.add(userVO);
        }

        model.addAttribute("listUserVO", listUserVO);
        return "index";
    }


    /**
     * User转换用户为UserVO
     * */
    public UserVO convertUserFromUserVO(User user){

        if (user==null){
            return null;
        }
        UserVO userVO=new UserVO();
        userVO.setUsername(user.getUsername());

        //手机号隐藏中间四位
        String phone = user.getPhone();
        userVO.setPhone(phone);

        userVO.setNickName(user.getNickName());
        userVO.setAvatarUrl(user.getAvatarUrl());
        userVO.setImageUrl(user.getImageUrl());
        userVO.setGender(user.getGender());
        userVO.setBackup(user.getBackup());
        userVO.setId(user.getId());

        Date insertDate = user.getInsertDate();
        Date date =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (insertDate!=null){
            String formatDate = simpleDateFormat.format(insertDate);
            userVO.setInsertDate(formatDate);
        }

        userVO.setValid(user.getValid());
        userVO.setUrlType(user.getUrlType());


        return userVO;
    }


    @CrossOrigin
    @ResponseBody
    @RequestMapping("/updateValid")
    public CommonReturnType updateValid(@RequestParam(name="id") Integer id) {
            int num = userService.updateUser(id);
            return CommonReturnType.create(null);
    }


}




