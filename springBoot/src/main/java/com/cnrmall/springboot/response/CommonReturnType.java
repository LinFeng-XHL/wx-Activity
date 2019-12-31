package com.cnrmall.springboot.response;

public class CommonReturnType {

        //表明对应请求的返回处理结果 "success" 或 "fail"
        private String status;

        //若status=success,则data内返回前端需要的json数据
        //若status=fail，则data内使用通用的错误码格式
        private Object data;

        //定义一个通用的创建方法
        public static CommonReturnType create(Object result){
            return CommonReturnType.create(result,"success");
        }

        public static CommonReturnType create(Object result,String status){
            CommonReturnType type = new CommonReturnType();
            type.setStatus(status);
            type.setData(result);
            return type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }


}
