package com.cnrmall.springboot.controller;

import com.alibaba.druid.util.StringUtils;
import com.cnrmall.springboot.controller.viewobject.UserVO;
import com.cnrmall.springboot.entity.User;
import com.cnrmall.springboot.error.BusinessException;
import com.cnrmall.springboot.error.EmBusinessError;
import com.cnrmall.springboot.response.CommonReturnType;
import com.cnrmall.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/activity")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 查询所有用户信息
     * */
    @RequestMapping("/user/findListByValid")
    public List<UserVO> findListByValid(HttpServletRequest request) throws BusinessException {

        /**
         * 调用service层级方法
         * */
        List<User> listUser = userService.findListByValid();


        //存放用户的集合
        List<UserVO> listUserVO=new ArrayList<>();
        for (int i = 0; i < listUser.size(); i++) {
            User user = listUser.get(i);
            //调用转换对象的方法
            UserVO userVO=convertUserFromUserVO(user);
            listUserVO.add(userVO);
        }
        return listUserVO;
    }







    /**
     * 查询所有用户信息,以及投票的数量，并按照投票的多少进行排序
     *
     * /activity/user/listUserByOrder
     * */
    @RequestMapping("/user/listUserByOrder")
    public List<UserVO> listUserByOrder(HttpServletRequest request) throws BusinessException {

        /**
         * 调用service层级方法
         * */
        List<User> listUser = userService.findListByOrder();


        //存放用户的集合
        List<UserVO> listUserVO=new ArrayList<>();
        for (int i = 0; i < listUser.size(); i++) {
            User user = listUser.get(i);
            //调用转换对象的方法
            UserVO userVO=convertUserFromUserVO(user);
            listUserVO.add(userVO);
        }
        return listUserVO;
    }




    /**
     * 根据id查询用户的所有信息
     * */
    @RequestMapping("/user/findUserById")
    public UserVO findUserById(@RequestParam(name="id") int id) throws BusinessException {

        /**
         * 调用service层级方法
         * */
        User user = userService.findUserById(id);
        if (user==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertUserFromUserVO(user);
        return userVO;
    }


    /**
     * 用户报名插入用户
     * */
    @PostMapping(value="/user/inserUser")
    @ResponseBody
    public CommonReturnType inserUser(@ModelAttribute UserVO userVO,HttpServletRequest request) throws BusinessException {

        if (userVO==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //校验手机号的唯一性，一个手机号只能报名一次
        String phone = userVO.getPhone();
        User userByPhone = userService.findUserByPhone(phone);
        if (userByPhone!=null){
            throw new BusinessException(EmBusinessError.USER_PHONE_ISEXIST);
        }


        //调用转换对象的方法
        User user=convertUserVOFromUser(userVO,request);

        userService.insertUser(user);
        return  CommonReturnType.create(null);
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
        if (!StringUtils.isEmpty(phone)){
            phone=phone.substring(0,4)+"****"+phone.substring(7,phone.length());
        }
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

        userVO.setVoteCount(user.getVoteCount());
        userVO.setUrlType(user.getUrlType());

        return userVO;
    }


    /**
     * UserVO转换用户为User
     * */
    public User convertUserVOFromUser(UserVO userVO,HttpServletRequest request){


        if (userVO==null){
            return null;
        }
        User user=new User();
        user.setUsername(userVO.getUsername());
        user.setPhone(userVO.getPhone());

        //获取文件的路径
        String imageUrl= userVO.getImageUrl();
        int indexD = imageUrl.lastIndexOf(".");
        String fileType=imageUrl.substring(indexD+1,imageUrl.length());
        String fileUrlInfo="";
        if (!StringUtils.isEmpty(imageUrl)){
            //获取当前完整的请求路径
            String  requestURL = request.getRequestURL().toString();
            //获取请求的端口号
            int serverPort = request.getServerPort();
            int indexUrl=requestURL.indexOf("activity");
            requestURL=requestURL.substring(0,indexUrl-1);

            int index=imageUrl.lastIndexOf("/");
            imageUrl=imageUrl.substring(index,imageUrl.length());
            String fileUrlType="";
            if (fileType.equalsIgnoreCase("mp4")){
                fileUrlType="/upload/video";
                fileUrlInfo="2";
            }else{
                fileUrlType="/upload/images";
                fileUrlInfo="1";
            }
            imageUrl=requestURL+":"+serverPort+fileUrlType+imageUrl;
            user.setImageUrl(imageUrl);
        }
        user.setUrlType(fileUrlInfo);
        user.setFileUrl(userVO.getFileUrl());
        user.setBackup(userVO.getBackup());
        user.setAvatarUrl(userVO.getAvatarUrl());
        user.setNickName(userVO.getNickName());
        user.setGender(userVO.getGender());
       // System.out.println("44444443333333333："+user);
        return user;

    }




    /**
     * 上传图片方法
     * */
    @RequestMapping("/upload/uploadImage")
    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        MultipartHttpServletRequest req =(MultipartHttpServletRequest)request;
        //获取微信端传来的图片参数
        MultipartFile multipartFile =  req.getFile("file");
        String originalFilename = multipartFile.getOriginalFilename();//获得文件的全路径
        int indexD = originalFilename.lastIndexOf(".");
        String fileType=originalFilename.substring(indexD+1,originalFilename.length());
        try {
            String fileUrl="";
            if (fileType.equalsIgnoreCase("mp4")){
                fileUrl="/upload/video";
            }else{
                fileUrl="/upload/images";
            }
            //获取文件需要上传到的路径
            String path = request.getRealPath(fileUrl) + "/";
            File realPath = new File(path);
            if (!realPath.exists()) {
                realPath.mkdir();
            }
            File file  =  new File(realPath,originalFilename);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

    }



}
