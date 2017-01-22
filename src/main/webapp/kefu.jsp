<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线客服贝翔商城</title>
     <style>
        .demo{
            right: 0;
            bottom: 0;
            width: 700px;
            height: 500px;
        }
    </style>
</head>
<body>
<div class="demo" id="J_demo"></div>
<!--[if lt IE 9]>
<script src="https://g.alicdn.com/aliww/ww/json/json.js" charset="utf-8"></script>
<![endif]-->
<!-- 自动适配移动端与pc端 -->
<script src="https://g.alicdn.com/aliww/??h5.openim.sdk/0.1.4/scripts/wsdk.js,h5.openim.kit/0.0.5/scripts/kit.js" charset="utf-8"></script>

<script>

    window.onload = function(){
        WKIT.init({
            container:document.getElementById('J_demo'),
            uid: '${uid}',
            appkey: '${appkey}',
            credential: '123456',
            touid: '贝翔商城',
            theme: 'red',
            pluginUrl: 'http://www.taobao.com/market/seller/openim/plugindemo.php',
            customUrl: 'http://www.taobao.com/market/seller/openim/customdemo.php'
        });
    }
</script>
</body>
</html>