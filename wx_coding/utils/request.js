const fun_md5 = require('./md5.js');
const fun_base64 = require('./base64.js');

//提示
function de() {
  wx.showToast({
    title: '加载中',
    icon: 'loading',
    duration: 10000
  })
  setTimeout(function () {
    wx.hideToast()
  }, 2000)
}
function ok() {
  wx.showToast({
    title: '成功',
    icon: 'success',
    duration: 2000
  })
  setTimeout(function () {
    wx.hideToast()
  }, 1000)
}
function error() {
  wx.showToast({
    title: '失败',
    icon: 'loading',
    duration: 1000
  })
  setTimeout(function () {
    wx.hideToast()
  }, 1000)
}

module.exports = {
  de: de,
  ok: ok,
  error: error,
  requestFormat: requestFormat,
  requestBase: requestBase,
  requestDelete: requestDelete,
  requestPost: requestPost
}

//request 数据格式化
function requestFormat(data) {
  var formatData = {}
  // console.log("requestFormat");
  console.log(data);//传送当前类型type
  formatData.encode = new fun_base64.Base64().encode(data)
  formatData.md5 = fun_md5.hex_md5(formatData.encode + wx.getStorageSync('randomKey'))
  formatData.type = data
  return formatData
}
//基本request请求
function requestBase(url, formatData, func) {
  var that = this
  wx.request({
    url: url,

    data: {
      object: formatData.encode,
      sign: formatData.md5
    },

    header: {
      'Authorization': "Bearer " + wx.getStorageSync('token'),
      'content-type': 'application/json'
    },
    method: 'POST',

    success: function (res) {
      func(res)
    },
    fail: function (res) {
      console.log('submit fail');
    }
  })
}

//post
function requestPost(url, formatData, func) {
  var that = this
  wx.request({
    url: url,
    data: formatData,
    header: {
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'POST',

    success: function (res) {
      func(res)
      // console.log('success')
    },
    fail: function (res) {
      console.log('submit fail');
    }
  })
}
//基本delete请求
function requestDelete(url, formatData, func) {
  var that = this
  wx.request({
    url: url,

    data: {
      object: formatData.encode,
      sign: formatData.md5
    },

    header: {
      'Authorization': "Bearer " + wx.getStorageSync('token'),
      'content-type': 'application/json'
    },
    method: 'DELETE',

    success: function (res) {
      func(res)
      // console.log('success')
    },
    fail: function (res) {
      console.log('submit fail');
    }
  })
}