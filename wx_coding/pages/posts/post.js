var postsData = require('../../data/posts-data.js')

Page({
  data: {
    list: []
  },
  onLoad: function () {
    this.setData({
       postList:postsData.postList
      });
  },

  

  onPostTap: function (event) {
    var postId = event.currentTarget.dataset.postid;
    // console.log("on post id is" + postId);
    wx.navigateTo({
      url: "post-detail/post-detail?id=" + postId
    })
  },


  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    wx.showShareMenu({
      withShareTicket: true
    })
  }






})