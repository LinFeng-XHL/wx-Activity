
//引入全局变量
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    flag:true
  },


  /**
   * 此函数是当页面一显示便进行相关的数据加载
   */
  onShow:function(){
    var voteFlagUrl = app.globalData.httpBase + "/vote/vote/selectByCondition";
    this.getVoteIsOrNot(voteFlagUrl);
  },



  /**
   * 获取是否可以投票的标志
   */
  getVoteIsOrNot:function(url){
    var that = this;
    //查看是否授权
    wx.getSetting({
      
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          //已经授权，可以直接调用getUserInfo获取头像昵称
          wx.getUserInfo({
            success: function (res) {
              //console.log(res.userInfo);

              wx.request({
                url: url,
                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },
                data: {
                  'avatarUrl': res.userInfo.avatarUrl,
                  'gender': res.userInfo.gender,
                  'nickname': res.userInfo.nickName,
                },
                method: 'POST',
                success: function (res) {
                  console.log(res.data);
                  that.setData({
                    //第一个data为固定用法
                    flag: res.data
                  })
                }
              })

            }
          })
        }
      }
    })
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var listUser = app.globalData.httpBase + "/activity/user/listUserByOrder";
    this.getVoteData(listUser);
  },


  //后台查询数据方法的封装
  getVoteData:function(url){
    var that=this
    wx.request({
      url: url,
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        console.log(res.data);
        //将获取到的json数据，存在名字叫zhihu的这个数组中
        that.setData({
          //第一个data为固定用法
          list: res.data
        })
      }
    })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

    wx.showNavigationBarLoading() //在标题栏中显示加载
    setTimeout(function () {
      wx.hideNavigationBarLoading(); //完成停止加载
      wx.stopPullDownRefresh(); //停止下拉刷新
    }, 1500);


    //下拉刷新：参与活动人员数据的加载
    var listUser = app.globalData.httpBase + "/activity/user/listUserByOrder";
    this.getVoteData(listUser);

    //下拉刷新是否可投票标志的刷新
    var voteFlagUrl = app.globalData.httpBase + "/vote/vote/selectByCondition";
    this.getVoteIsOrNot(voteFlagUrl);

  },

  getUserInfo: function (event) {
    //获取当前点击事件的
    var voteid=event.currentTarget.dataset.voteid;
    console.log(event.currentTarget.dataset.voteid);
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
              
              wx.request({
                url: app.globalData.httpBase + "/vote/vote/insertVote",
                header: {
                  'content-type': 'application/x-www-form-urlencoded'
                },
                data: {
                  'avatarUrl': res.userInfo.avatarUrl,
                  'gender': res.userInfo.gender,
                  'nickname': res.userInfo.nickName,
                  'voteid': voteid
                },
                method: 'POST',
                success: function (res) {
                  console.log(res.data);
                  if (res.data.status=='fail'){
                    wx.showToast({
                      title: '投票失败，原因为：' + res.data.data.errMsg,
                      icon: 'none',
                      duration: 3000,
                    })
                  }else{
                    wx.showToast({
                      title: '投票成功',
                      icon: 'success',
                      duration: 2000,
                    })
                  }
                  
                }
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

 


})