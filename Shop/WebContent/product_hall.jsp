<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
 <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<script type="text/javascript" src="http://localhost:8080/Shop/static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="http://localhost:8080/Shop/static/js/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/Shop/ShowCarServlet" class="shopping">购物车</a> <font>欢迎<b style="color:red">${sessionScope.loginUser.name }</b>登录 ,此刻时间为:<b style="color:red">${sessionScope.time }</b></font> &nbsp;&nbsp; <font>当前<b style="color: red">${applicationScope.onLineNum}</b>人在线</font><a href="/Shop/HistoryServlet">订单历史记录</a><a href="/Shop/LoginOutServlet">安全退出</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="#">首页</a></li>
			<li><a href="/Shop/BookListServlet">图书</a></li>
			<li><a href="#">百货</a></li>
			<li><a href="#">品牌</a></li>
			<li><a href="#">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="#">音乐</a></li>
			<li><a href="#">影视</a></li>
			<li><a href="#">少儿</a></li>
			<li><a href="#">动漫</a></li>
			<li><a href="#">小说</a></li>
			<li><a href="#">外语</a></li>
			<li><a href="#">科幻</a></li>
			<li><a href="#">情感</a></li>
			<li><a href="#">教育</a></li>
			<li><a href="#">传记</a></li>
			<li><a href="#">古典哲学</a></li>
			<li><a href="#">名著经典</a></li>
			<li><a href="#">杂志</a></li>
			<li><a href="#">电子书籍</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<dt>图书音像</dt>
				<dd><a href="/Shop/BookListServlet">图书</a></dd>
				<dd><a href="#">音乐</a></dd>
				<dt>百货</dt>
				<dd><a href="#">运动健康</a></dd>
				<dd><a href="#">服装</a></dd>
				<dd><a href="#">家居</a></dd>
				<dd><a href="#">美妆</a></dd>
				<dd><a href="#">母婴</a></dd>
				<dd><a href="#">食品</a></dd>
				<dd><a href="#">手机数码</a></dd>
				<dd><a href="#">家具首饰</a></dd>
				<dd><a href="#">手表饰品</a></dd>
				<dd><a href="#">鞋包</a></dd>
				<dd><a href="#">家电</a></dd>
				<dd><a href="#">电脑办公</a></dd>
				<dd><a href="#">玩具文具</a></dd>
				<dd><a href="#">汽车用品</a></dd>
			</dl>
		</div>
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
			<dl class="clearfix">
				<dt><img src="static/images/0_tiny.gif" /></dt>
				<dd><a href="#">法国德菲丝松露精品巧克力500g/盒</a></dd>
				<dt><img src="static/images/0_tiny.gif" /></dt>
				<dd><a href="#">法国德菲丝松露精品巧克力500g/盒</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<div class="price-off">
			<h2>今日特价</h2>
			<ul class="product clearfix">
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/1.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">法国德菲丝松露精品巧克力500g/盒</a></dd>
						<dd class="price">￥108.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/2.jpg" /></a></dt>
						<dd class="title"><a href="#"  target="_blank">乐扣普通型保鲜盒圣诞7件套</a></dd>
						<dd class="price">￥69.90</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#"  target="_blank"><img src="static/images/product/3.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">欧珀莱均衡保湿四件套</a></dd>
						<dd class="price">￥279.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="/Shop/hall.jsp" target="_blank"><img src="static/images/product/4.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">联想笔记本电脑 高速独立显存</a></dd>
						<dd class="price">￥4199</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="/Shop/hall.jsp" target="_blank"><img src="static/images/product/5.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">法姿韩版显瘦彩边时尚牛仔铅笔裤</a></dd>
						<dd class="price">￥49.00</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/6.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">Genius925纯银施华洛世奇水晶吊坠</a></dd>
						<dd class="price">￥69.90</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/7.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">利仁2018M福满堂电饼铛 好用实惠</a></dd>
						<dd class="price">￥268.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/8.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">达派高档拉杆箱20寸 经典款式</a></dd>
						<dd class="price">￥198.0</dd>
					</dl>
				</li>
			</ul>
		</div>
		<div class="side">
			<div class="news-list">
				<h4>最新公告</h4>
				<ul>
					<li><a href="#" target="_parent">今日特价</a></li>
					<li><a href="#" target="_parent">今日特价</a></li>
					<li><a href="#" target="_parent">今日特价</a></li>
					<li><a href="#" target="_parent">今日特价</a></li>
					<li><a href="#" target="_parent">今日特价</a></li>
					<li><a href="#" target="_parent">今日特价</a></li>
					<li><a href="#" target="_parent">今日特价</a></li>
				</ul>
			</div>
			<div class="spacer"></div>
			<div class="news-list">
				<h4>新闻动态</h4>
				<ul>
					<li><a href="#" target="_parent">热点资讯</a></li>
					<li><a href="#" target="_parent">热点资讯</a></li>
					<li><a href="#" target="_parent">热点资讯</a></li>
					<li><a href="#" target="_parent">热点资讯</a></li>
					<li><a href="#" target="_parent">热点资讯</a></li>
					<li><a href="#" target="_parent">热点资讯</a></li>
					<li><a href="#" target="_parent">热点资讯</a></li>
				</ul>
			</div>
		</div>
		<div class="spacer clear"></div>
		<div class="hot">
			<h2>热卖推荐</h2>
			<ul class="product clearfix">
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/1.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">法国德菲丝松露精品巧克力500g/盒</a></dd>
						<dd class="price">￥108.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/2.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">乐扣普通型保鲜盒圣诞7件套</a></dd>
						<dd class="price">￥69.90</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/3.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">欧珀莱均衡保湿四件套</a></dd>
						<dd class="price">￥279.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/4.jpg" /></a></dt>
						<dd class="title"><a href="#"target="_blank">联想笔记本电脑 高速独立显存</a></dd>
						<dd class="price">￥4199</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/5.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">法姿韩版显瘦彩边时尚牛仔铅笔裤</a></dd>
						<dd class="price">￥49.00</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/6.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">Genius925纯银施华洛世奇水晶吊坠</a></dd>
						<dd class="price">￥69.90</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/7.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">利仁2018M福满堂电饼铛 好用实惠</a></dd>
						<dd class="price">￥268.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/8.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">达派高档拉杆箱20寸 经典款式</a></dd>
						<dd class="price">￥198.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/9.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">爱国者MP4 全屏触摸多格式播放 4G</a></dd>
						<dd class="price">￥289.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_parent"><img src="static/images/product/10.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">多美滋金装金盾3阶段幼儿配方奶粉</a></dd>
						<dd class="price">￥186.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/1.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">法国德菲丝松露精品巧克力500g/盒</a></dd>
						<dd class="price">￥108.0</dd>
					</dl>
				</li>
				<li>
					<dl>
						<dt><a href="#" target="_blank"><img src="static/images/product/2.jpg" /></a></dt>
						<dd class="title"><a href="#" target="_blank">乐扣普通型保鲜盒圣诞7件套</a></dd>
						<dd class="price">￥69.90</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>
    	
</body>
</html>