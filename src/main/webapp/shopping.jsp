<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>商品列表</title>
<link href="css/list.css" rel="stylesheet">
<script src="js/jquery.js" type="text/javascript"></script>
<script type="text/javascript">
	// .前台shopping.jsp展示数据，提供素材,分页组件可用bootstrap,要注意关键代码：
	// 特别留意上一页，下一页的超链接，不但带有分页信息还有查询条件，这个是重点，其他价格，
	// 品牌超链接也要留意，都是带有各种查询条件的。另外搜索框用js代码发送请求去搜索，
	// 这些功能都用同一个Servlet，大大减轻了开发难度。

</script>
</head>
<body>
	<!-- 引入头部 -->
	<jsp:include page="/header.jsp"></jsp:include>
	<!-- 商品信息 -->
	<div class="head">
		<div class="logo fl">
			<a href="index.jsp"><img src="images/paimeng.jpg"
				style="position: relative;top:5px;left:20px;width: 154px;height: 70px"></a>
		</div>
		<div class="search fl" style="position: relative;top: 30px;left: 50px;">
			<div class="sch-form">
				<input type="text" class="stext"> <input type="button"
					class="sbtn" value="搜索" onclick="">
					
			</div>
			<div class="sch-hint">
				<a href="javascript:void(0)">笔记本电脑</a> <a href="javascript:void(0)">|</a>
				<a href="javascript:void(0)">女装</a> <a href="javascript:void(0)">|</a>
				<a href="javascript:void(0)">0元试用</a> <a href="javascript:void(0)">|</a>
				<a href="javascript:void(0)">微波炉</a> <a href="javascript:void(0)">|</a>
				<a href="javascript:void(0)">空调</a> <a href="javascript:void(0)">|</a>
				<a href="javascript:void(0)">厨卫5折秒</a> <a href="javascript:void(0)">|</a>
				<a href="javascript:void(0)">国际米兰</a>
			</div>
		</div>
		<div class="cart fr" style="position: relative;top: 30px;left: -40px;">

			<img alt="砺锋商城" src="images/wy.png" width="100px" ;height="100px";>

		</div>
	</div>
	<br />
	<br />
	<div class="dtw">
		<div class="dtww clear">
			<div class="dt fl">
				<a href="javascript:void(0)">全部商品分类</a>
			</div>
			<ul class="navbar fl">
				<li><a href="index.jsp" class="sy">首页</a></li>
				<li><a href="shopping?cid=32" class="sy">食品饮料</a></li>
				<li><a href="shopping?cid=5" class="sy">特价手机</a></li>
				<li><a href="shopping?cid=11" class="sy">品牌闪购</a></li>
                <li><a href="shopping?cid=8" class="sy">电器城</a></li>
                <li><a href="shopping?cid=31" class="sy">生鲜</a></li>
				<li><a href="http://a2.rabbitpre.com/m2/aUe1ZicePv" class="sy">合伙人招募</a></li>
			</ul>
		</div>
	</div>
	<!-- 详情 -->
	<div class="content clear">
		<div class="left">

			<div class="sidebar">
				<h2>店连店热卖</h2>
				<ul class="hotbox">
					<li class="hotlist">
						<div class="hotpic">
							<a href="#"><img width="150" src="proimages/110.png"></a>
						</div>
						<div class="hotinfo">
							<div class="hot-name">
								<a href="#">地中海灯饰电风扇吊灯餐厅卧室家用电扇灯具</a>
							</div>
							<div class="hot-price">
								<a href="#"><i>¥</i><span>1656.00</span></a>
							</div>
						</div>
					</li>
					<li class="hotlist">
						<div class="hotpic">
							<a href="#"><img width="150" src="proimages/120.png"></a>
						</div>
						<div class="hotinfo">
							<div class="hot-name">
								<a href="#">长方形水晶灯LED吸顶灯大气客厅灯卧室餐厅灯欧式水晶灯</a>
							</div>
							<div class="hot-price">
								<a href="#"><i>¥</i><span>5178.00</span></a>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="right">
			<div class="sidebar rec"></div>
			<div class="filter">
				<dl>
					<dt>购买渠道：</dt>
					<dd class="list">
						<ul>
							<li><a href="">砺锋自营</a></li>
							
						</ul>
					</dd>
				</dl>
				<dl>
					<dt>价格：</dt>
					<dd class="list">
						<ul>
							<li><a href="shopping?maxPrice=50&cid=${cid}&pName=${pName}&pSn=${pSn}">50元以下</a></li>
							<li><a href="shopping?minPrice=50&maxPrice=100&cid=${cid}&pName=${pName}&pSn=${pSn}">50-100元</a></li>
							<li><a href="shopping?minPrice=100&maxPrice=200&cid=${cid}&pName=${pName}&pSn=${pSn}">100-200元</a></li>
							<li><a href="shopping?minPrice=200&maxPrice=1000&cid=${cid}&pName=${pName}&pSn=${pSn}">200-1000元</a></li>
							<li><a href="shopping?minPrice=1000&maxPrice=10000&cid=${cid}&pName=${pName}&pSn=${pSn}">1000-10000元</a></li>
						</ul>
					</dd>
				</dl>
				<dl>
					<dt>品牌：</dt>
					<dd class="list">
						<ul>
							<li>
<%-----------------------------------------------------展示品牌信息：-----------------------------------------------------%>
									
									<c:if test="${not empty pList}">  <%--判断查询结果pList是否为空--%>
										<c:forEach items="${pList}" var="pName"><%--循环输出所有的查询结果pList--%>
											<a href='shopping?cid=${cid}&pName=${pName}&pSn=${pSn}&minPrice=${minPrice}&maxPrice=${maxPrice}'>${pName}</a>
										</c:forEach>
									</c:if>
									

							</li>
						</ul>
					</dd>
				</dl>
			</div>
			<div class="sorbar"></div>
			<br> <br>
<%--**--------------------------------------------------展示商品分页信息：--------------------------------------------------%>
			<div class="goods-list">
				<ul class="list clear">
					<c:forEach items="${page.list}" var="item">
						<li>
							<div class="g">
								<div class="g-pic">
									<a href="proDetail?proid=${item.id}"><img
										src="${pageContext.request.contextPath}/${item.pImg}"></a>
								</div>
								<p class="g-name">
									<a href="proDetail?proid=${item.id}"
										style="color: red">${item.pName}</a> <a
										href="proDetail?proid=${item.id}">${item.pSn}</a>
								</p>
								<p class="g-price">
									<span class="price">￥<em>${item.iPrice}</em></span><span
										class="del-price">￥${item.mPrice}</span>&nbsp;&nbsp;已售：${item.isHot}件
									<%-- <span class="bar">已售：${item.isHot}件</span> --%>
								</p>
								<div class="seller-info">
									<p>
										<span>商品评价数：</span><span><a href="#">999条</a></span>
									</p>
									<p>
										<span>卖家好评率：</span><span>90%</span>
									</p>
									<p>
										<span>累积信用度：</span><span class="heart"></span>
									</p>
									<p>
										<span>描述相符度：</span><span>6分</span>
									</p>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<%-----------------------------------------简单分页控件：----------------------------------------%>

			<div class="pagebox">
				<div class="pagination">
					<tfoot>
						<tr>                                        <%--分页--%>
							<td colspan="4" align="center">
							<a href="shopping?pi=1&cid=${cid}&pName=${pName}&pSn=${pSn}&minPrice=${minPrice}&maxPrice=${maxPrice}">首页</a>
							<a href="shopping?pi=${page.pageNum-1}&cid=${cid}&pName=${pName}&pSn=${pSn}&minPrice=${minPrice}&maxPrice=${maxPrice}"
							onclick="return ${page.pageNum-1>=1}">上一页</a><span>${page.pageNum}/${page.pages}</span>
							<a href="shopping?pi=${page.pageNum+1}&cid=${cid}&pName=${pName}&pSn=${pSn}&minPrice=${minPrice}&maxPrice=${maxPrice}"
							   onclick="return ${page.pageNum+1<=page.pages}">下一页</a>
							<a href="shopping?pi=${page.pages}&cid=${cid}&pName=${pName}&pSn=${pSn}&minPrice=${minPrice}&maxPrice=${maxPrice}">尾页</a>
							</td>
						</tr>
					</tfoot>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			//鼠标经过商品列表出现商家信息
			function ghover(a, b) {
				$(a).children().hover(function() {

					$(this).find(b).css('display', 'block');
				}, function() {

					$(this).find(b).css('display', 'none');
				});
			}
			ghover(".list", ".seller-info");
			//树形结构折叠展开
			$(".category dt").toggle(function() {
				$(this).addClass("on");
				$(".sub").css('display', 'none');
			}, function() {
				$(this).removeClass("on");
				$(".sub").css('display', 'block');
			});
		});
		
		 
			

	</script>
	<!-- 引入底部 -->
	<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>