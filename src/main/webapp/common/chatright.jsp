<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style>
        *{margin:0;padding:0;}
        .custom-wrap{
            width: 100%;
            margin: 0 auto;
            font-size: 12px;
        }

        .custom-wrap .tab{
            font-size: 12px;
            font-weight: normal;
            margin-bottom: 10px;
        	height: 30px;
        }
      .custom-wrap .tab span{
        display: block;
        width: 50%;
        height: 30px;
        float: left;
        background: #f6f6f6;
        color: #9b9b9b;
        line-height: 30px;
        text-align: center;
        cursor: pointer;
      }
      .custom-wrap .tab .active{
        background: #fff;
        color: #dc2a2e;
      }
        .custom-wrap a{
            display: block;
            color: #ff6d71;
            line-height: 25px;
        text-decoration:none;
        }
    </style>
</head>
<body><script>
with(document)with(body)with(insertBefore(createElement("script"),firstChild))setAttribute("exparams","category=&userid=&aplus&yunid=&&asid=AQAAAABzXYRY6ysZIwAAAACucJuXluA2/w==",id="tb-beacon-aplus",src=(location>"https"?"//g":"//g")+".alicdn.com/alilog/mlog/aplus_v2.js")
</script>
<div class="custom-wrap">
<h2 class="tab" id="tab"><span class="active" data-index="0">商品信息</span><span data-index="1">常见问题</span></h2>
<div class="container">
<div class="c0" id="c0">
<img src="">
</div>
<div class="c1" id="c1" style="display: none;padding-left:5px;">
<img src="">
</div>
</div>
</div>
<script>
    var tab = document.getElementById('tab'),
        tabs = tab.getElementsByTagName('span'),
        activeIndex = 0;
    document.getElementById('tab').onclick = function(ev){
    	ev = ev || window.event;
      var target = ev.target || ev.srcElement;
      
      if(target.tagName.toUpperCase() == 'SPAN'){
        var index = target.getAttribute('data-index');
        if(activeIndex == index) return;
        target.className = 'active';
        tabs[activeIndex].className = '';
        
        document.getElementById('c' + index).style.display = 'block';
        document.getElementById('c' + activeIndex).style.display = 'none';
        activeIndex = index;
      }
    }
  </script>
</body>
</html>