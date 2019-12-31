<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<!--引入bootstrap的样式-->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<!--引入bootstrap的js-->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.15.0/umd/popper.min.js" integrity="sha384-L2pyEeut/H3mtgCBaUNw7KWzp5n9+4pDQiExs933/5QfaTh8YStYFFkOzSoXjlTb" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" src="/static/js/jquery.js"></script>

<head>
    <title>活动报名用户列表</title>

    <style>

        .content{
            width:90%;
            margin:auto;
            margin-top:10px
        }
        .contitle{
            width:100%;
            height:80px;
            font-size:24px;
            text-align:center;
            line-height:80px
        }


    </style>

</head>
<body>
<div class="content" >
    <div class="contitle" >活动报名用户列表</div>
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col" style="width:30px">id</th>
                <th scope="col" style="width:60px">姓名</th>
                <th scope="col" style="width:60px">手机号</th>
                <th scope="col" style="width:100px">附件展示</th>
                <th scope="col" style="width:120px">参加时间</th>
                <th scope="col" style="width:60px">昵称</th>
                <th scope="col" style="width:100px">微信头像</th>
                <th scope="col" style="width:60px">性别</th>
                <th scope="col" style="width:600px">活动内容</th>
                <th scope="col" style="width:100px">审核状态</th>
                <th scope="col" style="width:100px">操作</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${listUserVO}" var="listUserVO" >
                <tr >
                    <th scope="row">${listUserVO.id}</th>
                    <th scope="row">${listUserVO.username}</th>
                    <th scope="row">${listUserVO.phone}</th>
                    <th scope="row">
                        <c:choose>
                            <c:when test="${listUserVO.urlType=='1'}">
                                <a href="${listUserVO.imageUrl}" target="_blank"> <img src="${listUserVO.imageUrl}" style="width: 80px;height: 50px"></a>
                            </c:when>
                            <c:otherwise>
                                <a href="${listUserVO.imageUrl}" target="_blank"> <img src="/static/image/movie.png" style="width: 80px;height: 50px"></a>
                            </c:otherwise>
                        </c:choose>

                    </th>
                    <th scope="row">${listUserVO.insertDate}</th>
                    <th scope="row">${listUserVO.nickName}</th>
                    <th scope="row"><img src="${listUserVO.avatarUrl}" style="width: 50px;height: 50px"></th>
                    <th scope="row">
                        <c:choose>
                            <c:when test="${listUserVO.gender==0}">男</c:when>
                            <c:otherwise>女</c:otherwise>
                        </c:choose>
                    </th>
                    <th scope="row">${listUserVO.backup}</th>
                    <th scope="row">
                        <c:choose>
                            <c:when test="${listUserVO.valid=='1'}">审核通过</c:when>
                            <c:otherwise>未通过</c:otherwise>
                        </c:choose>
                    </th>
                    <th scope="row">
                        <c:if test="${listUserVO.valid!='1'}">
                            <input class="btn btn-secondary btn-lg" id="${listUserVO.id}" onclick="getValidById(this.id)" type="button" value="审核">
                        </c:if>
                    </th>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>

<script>


    function getValidById(id){



        if(confirm('确定通过审核吗？')){
            $.ajax({
                type:"POST",
                url: "http://192.168.7.17:80/updateValid",
                contentType:"application/x-www-form-urlencoded",
                data:{"id":id},
                xhrFields:{withCredentials:true},
                success:function(data){
                    //alert(data.status);
                    if (data.status=="success"){
                        alert("审核通过");
                    }else{
                        alert("审核失败");
                    }
                    //刷新窗口
                    window.location.reload();
                }
            })
        }

    }



</script>