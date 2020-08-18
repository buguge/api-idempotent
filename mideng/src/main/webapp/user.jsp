<%--
  Created by IntelliJ IDEA.
  User: zhangguozhan
  Date: 2020/7/2
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/jquery-1.10.2.js"></script>
</head>
<body>

<form action="/addUser">
    <input type="text" name="ticket" id="ticket">
    <input type="text" name="userName" id="userName" value="1">
    <input type="button" onclick="return doPost()" value="post">
    <input type="button" onclick="return doRedirect()" value="post and redirect">
    <span id="tip"></span>
</form>

<script type="text/javascript">
    $(function () {

        $.ajax({
            type: "GET",
            url: "/user/getTicket",
            success: function (data) {
                $("#ticket").val(data);
            }
        });
    });

    function doPost() {

        $.ajax({
            type: "POST",
            url: "/user/addUser",
            contentType: "application/json;utf-8",
            data: JSON.stringify({'ticket': $("#ticket").val(), 'userName': $("#userName").val()}),
            success: function (data) {
                // data = eval('(' + data + ")");
                if (200 == data.code) {
                    $("#tip").text(data.msg)
                } else {
                    $("#tip").text('failed:' + data.msg)
                }
            }
        });
    }

    function doRedirect() {

        $.ajax({
            type: "POST",
            url: "/user/addUser",
            contentType: "application/json;utf-8",
            data: JSON.stringify({'ticket': $("#ticket").val(), 'userName': $("#userName").val()}),
            success: function (data) {
                // data = eval('(' + data + ")");
                if (200 == data.code) {
                    location.href = "http://www.baidu.com";
                } else {
                    $("#tip").text('failed:' + data.msg)
                }
            }
        });
    }
</script>
</body>
</html>
