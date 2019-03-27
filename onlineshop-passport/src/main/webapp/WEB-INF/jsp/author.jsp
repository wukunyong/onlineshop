<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/js/jquery.js"></script>
<style type="text/css">
    .nav{position: absolute;top:100px;background: #eee;color: black; width: 100px;height: 300px;border-radius: 15px;left:0px; text-align: center;}
    .nav span{cursor: pointer; width: 25px;height: 100px;display:block;position: relative;left: 100px;top: 100px;background: orange;text-align: center;border-radius: 5px;}
</style>
<div class="nav">
    <span>点击收起</span>
    <ul>
        <li> 本站作者</li>
        <br/>
        <li> 吴坤永 </li>
        <br/>
        <li> QQ:</li>
        <br/>
        <li> 1606004878</li>
    </ul>
</div>
<script>
     $('.nav span').click(function(){
         var t = $('.nav span').text();
         if(t == "点击展开"){
             $('.nav span').text("点击收起");
             $('.nav').animate({left:"0px"});
         }
         else{
             $('.nav span').text("点击展开");
             $('.nav').animate({left:"-100px"});
         }
     })
</script>
