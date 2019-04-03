<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="static/css/style.css" />
<script type="text/javascript" src="static/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="static/js/function.js"></script>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="static/images/logo.gif" /></div>
	<div class="help"><a href="/Shop/ShowCarServlet" class="shopping">购物车</a><a href="/Shop/register.html">注册</a><a href="/Shop/LoginOutServlet">安全退出</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="/Shop/product_hall.jsp">首页</a></li>
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
			<li><a href="#">数码相机</a></li>
			<li><a href="#">笔记本</a></li>
			<li><a href="#">羽绒服</a></li>
			<li><a href="#">秋冬靴</a></li>
			<li><a href="#">运动鞋</a></li>
			<li><a href="#">美容护肤</a></li>
			<li><a href="#">家纺用品</a></li>
			<li><a href="#">婴幼奶粉</a></li>
			<li><a href="#">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="/Shop/product_hall.jsp">易买网</a> &gt; 订单历史纪录
</div>
<div class="wrap">
	<div id="history">
			<table>
				<c:choose>
				    <c:when test="${!empty orderList}">
				    <tr>
						<th>订单编号</th>
						<th>商品名称,商品数量</th>
						<th>商品总价</th>
						<th>订单成交时间</th>
					</tr>
				    	<c:forEach items="${orderList}" var="order">
				    		<tr id="product_id_1">
								<td class="orderID">${order.orderID}</td>
								<td class="bookName">
									  <dl>
										<%-- <c:forEach items="${order.bookList}" var="book"> --%>
										    <dd>${order.book.name} &nbsp;&nbsp; ${order.book.shopNums}本</dd>
										<%-- </c:forEach> --%>	
									</dl> 
									
								</td>
								<td class="price" id="price_id_1">
									<span>￥${order.totalPrice}</span>
								</td>
								<td class="orderTime">
									<dl>
										<dt>${order.date}</dt>
									</dl>
								</td>
							</tr>
						</c:forEach>
				    </c:when>
				    <c:otherwise>
				    	<tr><td colspan="4"><h2 align="center" style="color: #cc3300">当前无任何订单记录  , 请前往购物大厅进行选购！！！</h2></td></tr>
				    </c:otherwise>
				</c:choose>	
			</table>
	</div>
</div>
<div id="footer">
	Copyright &copy; 2010  All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
