
//引入全局变量
var app=getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
   
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var that = this
    wx.request({
      url: app.globalData.httpBase +"/activity/user/findListByValid",
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
   * 页面跳转到详情
   * 
   */
  onPostTap: function (event) {
    var userId = event.currentTarget.dataset.postid;
    //console.log(event.currentTarget.dataset);
    wx.navigateTo({
      url: "consume-detail/consume-detail?id=" + userId
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

    var that = this
    wx.request({
      url: app.globalData.httpBase + "/activity/user/findListByValid",
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

  
  
})