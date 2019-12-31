var postsData = require('../../../data/posts-data.js')
var app = getApp();
Page({
    data: {
     
    },
    onLoad: function (option) {
        var postId = option.id;
        this.data.currentPostId = postId;
        var postData = postsData.postList[postId];
        this.setData({
            postData: postData
        })
    },


  //点击报名跳转按钮之前需要根据弹框提示进行授权
  getUserInfo: function (e) {
    let that = this;
    // console.log(e)
    // 获取用户信息
    wx.getSetting({
      success(res) {
        // console.log("res", res)
        if (res.authSetting['scope.userInfo']) {
          console.log("已授权=====")
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success(res) {
              console.log("获取用户信息成功", res)
              that.setData({
                nickName: res.userInfo.nickName,
                gender: res.userInfo.gender,
                avatarUrl: res.userInfo.avatarUrl
              })
              wx.navigateTo({
                url: "../../publish/publish"
              })
            },
            fail(res) {
              console.log("获取用户信息失败", res)
            }
          })
         
        } else {
          console.log("未授权=====")
          that.showSettingToast("请授权")
        }
      }
    })
  },

  //打开权限设置页提示框
  showSettingToast: function (e) {
    wx.showModal({
      title: '提示！',
      confirmText: '去设置',
      showCancel: false,
      content: e,
      success: function (res) {
        if (res.confirm) {
          wx.navigateTo({
            url: '../setting/setting',
          })
        }
      }
    })
  },

    // //点击【我要报名】按钮的跳转
    // getUserInfo: function (event) {
    //   wx.navigateTo({
    //     url: "../../publish/publish"
    //   })
    // },
   
})