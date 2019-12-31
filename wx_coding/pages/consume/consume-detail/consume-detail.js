
//引入全局变量
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    user:{},
    uploaderList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var postId = options.id;
    //console.log(postId);

    var nowList = [];//新数据
    var uploaderList = this.data.uploaderList;//原数据

    wx.request({
      url: app.globalData.httpBase + "/activity/user/findUserById"+"?id=" + postId,
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        nowList.push(res.data.imageUrl);
        console.log(res.data);
        that.setData({
          user: res.data,
          uploaderList: nowList
        })
       
      }
    })


  },

 //点击放大预览展示图片
  previewImg(event) {
    var that = this;
    let currentUrl = event.currentTarget.dataset.src
    wx.previewImage({
      urls: that.data.uploaderList
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
      url: app.globalData.httpBase + "/activity/user/findUserById" + "?id=" + postId,
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

 
})