//引入全局变量
var app = getApp();
function send_photo(data, successfun) {
  //console.log(app.globalData.httpBase );
  //console.log(data);
  var that = this
  wx.uploadFile({
    url: app.globalData.httpBase+"/activity/upload/uploadImage", //为请求后端服器上传图片的路径
    filePath: data,
    name: 'file',
    success: function (res) {
      successfun(res)
    }
  })
  
}
module.exports = {
  send_photo: send_photo
}