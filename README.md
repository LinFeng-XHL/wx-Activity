# wx-Activity
微信小程序-活动报名


这是一个有关报名活动的微信小程序。
首先后台是基于springBoot+Mybatis写的后端接口以及一个简单的审核报名活动人员的后台功能
前端便是大家熟知的微信小程序

简单说明：
  【springBoot】是后端程序，使用IDEA或者其他开发工具导入，利用maven下载相应的jar即可
  【wx_coding】是微信小程序，需要使用微信小程序的开发工具进行相应的打开调试，这里不是很熟悉微信小程序的可以去官网简单了解一下。
  【wxdev.sql】是数据库的建表语句

一切环境准别就绪，便可启动看到效果。
实现功能：
  1）活动的展示，这里展示使用了静态文件引入的方式，没有使用数据库表（只是为了刚开始学习微信小程序时候的练习）
  2）查看活动详情
  3）报名参加活动（点击按钮获取微信的个人昵称，头像等基本信息）
  4）跳转具体参加活动页面，添加真实姓名，手机号等关键信息，上传图片或者视频
  5）查看哪些人报名了活动以及查看报名人详情（这里对关键信息进行了简单的加密处理）
  6）对报名活动人员进行投票（一个微信号只可以对一个人投一次票）
  7）后台审核功能，对报名活动的人员进行相关审核，审核通过才可以进行下一步参与被投票环节
  
 下面附上几张效果图：
 
1)首页展示效果图：

![image](https://github.com/LinFeng-XHL/wx-Activity/blob/master/images/index.png)

2)活动详情展示

![image](https://github.com/LinFeng-XHL/wx-Activity/blob/master/images/detail.png)

3)活动报名

![image](https://github.com/LinFeng-XHL/wx-Activity/blob/master/images/bm.png)

4)活动投票

![image](https://github.com/LinFeng-XHL/wx-Activity/blob/master/images/tp.png)

5)后台审核

![image](https://github.com/LinFeng-XHL/wx-Activity/blob/master/images/ht.jpg)
 
 
 git remote add origin https://github.com/LinFeng-XHL/wx.git

 git push -u origin springBoot


