//引入全局变量
var app = getApp();
const httputil = require("../../utils/httputil.js")  

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },

  formSubmit: function (e) {
    console.log('form发生了submit事件，携带数据为：', e.detail.value);

    var imageUrl = e.detail.value.imageUrl;
    var username = e.detail.value.username;
    var phone = e.detail.value.phone;
    var backup = e.detail.value.backup;
    var avatarUrl = e.detail.value.avatarUrl;
    var gender = e.detail.value.gender;
    var nickName = e.detail.value.nickName;

    if (username==null || username==""){
      wx.showToast({
        title: '用户姓名不能为空',
        icon: 'none',
        duration: 2000
      })
      return ;
    }

    if (phone == null || phone == "") {
      wx.showToast({
        title: '手机号不能为空',
        icon: 'none',
        duration: 2000
      })
      return;
    } 

    if (phone.length != 11) {
      wx.showToast({
        title: '手机号格式错误',
        icon: 'none',
        duration: 2000
      })
      return;
    }

    var that = this
    wx.request({
      url: app.globalData.httpBase + "/activity/user/inserUser",
      method: 'POST',
      header: {
        //'content-type': 'application/json' // 默认值  此头POST无法传值
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        'username': username,
        'phone': phone,
        'backup': backup,
        'imageUrl': imageUrl,
        'avatarUrl': avatarUrl,
        'gender': gender,
        'nickName': nickName
      },
      success(res) {
        
        //执行成功的方法
        if (res.data.status=='success'){
          console.log(222222222222222222222222222);
         //返回上一级页面
          wx.navigateBack({
            delta: 1,
            success() {
              //返回上一级成功进行弹框提示
              wx.showToast({
                title: '报名成功',
                icon: 'success',
                duration: 2000,
              })
            },
            fail(){
              
                wx.showToast({
                  title: '报名失败,原因为' + res.data.errMsg,
                  icon: 'none',
                  duration: 2000,
                })
              
            }
          })
        }else {
          //console.log(res.data);
          if (res.data.status == 'fail') {
            
            wx.showToast({
              title: '报名失败,原因为:' + res.data.data.errMsg,
              icon: 'none',
              duration: 3000,
            })
            
          }
        }
      },
      fail(res){
        console.log("网络延迟");
      }
     
    })
    
    //访问后台图片上传的方法
    httputil.send_photo(imageUrl, function (res) {

    })
    
  },



  // 选择图片或者视频
  uploadFiles: function (e) {
    var that = this;
    wx.showActionSheet({
      itemList: ['选择图片', '选择视频'],
      success: function (res) {
        //   console.log(res.tapIndex)
        let xindex = res.tapIndex;
        if (xindex == 0) {//上传图片
          wx.chooseImage({
            count: 1,
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            success: function (res) {
              that.setData({
                isSrc: true,
                'image.imageUrl': res.tempFilePaths[0]

              })
            }
          })
        } else if (xindex == 1) {//上传视频
          wx.chooseVideo({
            count: 1,
            sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
            sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
            maxDuration: 40,
            compressed: true,
            camera: 'back',
            success: function (res) {
              console.log(res)
              that.setData({
                isSrc: true,
                isMovies:true,
                'image.imageUrl': res.tempFilePath
              })
            }
          })

        }

      },
      fail: function (res) {
        console.log(res.errMsg)
      }
    })
  },


  //活动图片选择--原来旧的方法
  uploadPic: function (res) {//选择图标
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function (res) {
        that.setData({
          isSrc: true,
          'image.imageUrl': res.tempFilePaths[0]
          
        })
      }
    })
  },
  //删除图片
  clearPic: function () {//删除图片
    this.setData({
      isSrc: false,
      'image.imageUrl': ""
    })
  },


  /**
   * 生命周期函数--监听页面加载
   * 主要是获取当前微信用的信息
   */
  onLoad: function (options) {
    var that = this//不要漏了这句，很重要
    // 查看是否授权
    wx.getSetting({
      success(res) {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              //console.log(res.userInfo)
              that.setData({
                userInfo: res.userInfo
              })
            }
          })
        }
      }
    })


  },



  // getUserInfo: function (e) {
  //   let that = this;
  //   // console.log(e)
  //   // 获取用户信息
  //   wx.getSetting({
  //     success(res) {
  //       // console.log("res", res)
  //       if (res.authSetting['scope.userInfo']) {
  //         console.log("已授权=====")
  //         // 已经授权，可以直接调用 getUserInfo 获取头像昵称
  //         wx.getUserInfo({
  //           success(res) {
  //             console.log("获取用户信息成功", res)
  //             that.setData({
  //               nickName: res.userInfo.nickName,
  //               gender: res.userInfo.gender,
  //               avatarUrl: res.userInfo.avatarUrl
  //             })
  //           },
  //           fail(res) {
  //             console.log("获取用户信息失败", res)
  //           }
  //         })
  //       } else {
  //         console.log("未授权=====")
  //         that.showSettingToast("请授权")
  //       }
  //     }
  //   })
  // },

  // //打开权限设置页提示框
  // showSettingToast: function (e) {
  //   wx.showModal({
  //     title: '提示！',
  //     confirmText: '去设置',
  //     showCancel: false,
  //     content: e,
  //     success: function (res) {
  //       if (res.confirm) {
  //         wx.navigateTo({
  //           url: '../setting/setting',
  //         })
  //       }
  //     }
  //   })
  // },


  /**
   * 获取用户信息--是点击按钮触发的获取用户信息的方法
   */
  bindGetUserInfo(e) {
    console.log(e.detail.userInfo)
    this.setData({
      userInfo: e.detail.userInfo
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  
})