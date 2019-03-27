<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/base-v1.js" charset="utf-8"></script>
<!--shortcut start-->
<jsp:include page="shortcut.jsp" />
<!--shortcut end-->
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<div id="logo-2013" class="ld"><a href="/" hidefocus="true" clstag="homepage|keycount|home2013|02a"><b></b><img src="/images/GdufeBookStore.png" width="270" height="60" alt="广财书苑"></a></div>
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide">
				</ul>
				<div class="form">
					<input type="text" class="text" accesskey="s" id="key" autocomplete="off" onkeydown="javascript:if(event.keyCode==13) search('key');">
					<input type="button" value="搜索" class="button" onclick="javascript:window.location.href='http://localhost:8083/search.html?q=' + encodeURIComponent(document.getElementById('key').value);return false;" clstag="homepage|keycount|home2013|03a">
				</div>
			</div>
			<div id="hotwords" clstag="homepage|keycount|home2013|03b"></div>
		</div>
		
		<!--my360buy end-->
		<div id="settleup-2013" clstag="homepage|keycount|home2013|05a">
			<dl>
				<dt class="ld"><s></s><span class="shopping"><span id="shopping-amount"></span></span><a href="http://localhost:8085/cart/cart.html" id="settleup-url">去购物车结算</a> <b></b> </dt>
<!-- 				<dd>
					<div class="prompt">
						<div class="loading-style1"><b></b>加载中，请稍候...</div>
					</div>
				</dd>
 -->			</dl>
		</div>
		<!--settleup end-->
	</div>
	<!--header end-->
	<div class="w">
		<div id="nav-2013">
			<div id="categorys-2013" class="categorys-2014">
				<div class="mt ld">
					<h2><a href="http://www.jd.com/allSort.aspx" clstag="homepage|keycount|home2013|06a">全部图书分类<b></b></a></h2>
				</div>
				<div id="_JD_ALLSORT" class="mc">
					<div class="item fore1">
						<span data-split="1"><h3>
								<a href="/products/1.html">国学古籍</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore2">
						<span data-split="1"><h3>
								<a href="/products/74.html">文学小说</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore3">
						<span data-split="1"><h3>
								<a href="/products/161.html">文学小说</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore4">
						<span data-split="1"><h3>
								<a href="/products/249.html">历史地理艺术</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore5">
						<span data-split="1"><h3>
								<a href="/products/290.html">历史地理艺术</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore6">
						<span data-split="1"><h3>
								<a href="/products/296.html">哲学心理宗教</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore7">
						<span data-split="1"><h3>
								<a href="/products/378.html">经济社科</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore8">
						<span data-split="1"><h3>
								<a href="/products/438.html">童书生活体育</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore9">
						<span data-split="1"><h3>
								<a href="/products/495.html">互联网技术</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore10">
						<span data-split="1"><h3>
								<a href="/products/558.html">自然科学</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore11">
						<span data-split="1"><h3>
								<a href="/products/580.html">医药卫生</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore12">
						<span data-split="1"><h3>
								<a href="/products/633.html">教辅教材</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore13">
						<span data-split="1"><h3>
								<a href="/products/699.html">红色文献</a>
							</h3>
							<s></s></span>
					</div>
					<div class="item fore14">
						<span data-split="1"><h3>
								<a href="/products/749.html">邮票税票钱币</a>
							</h3>
							<s></s></span>
					</div>
					<div class="extra">
						<a {if="" pageconfig.ishome}clstag="homepage|keycount|home2013|0614a"
							{="" if}="" href="#">全部图书分类</a>
					</div>
				</div>
			</div>
			<div id="treasure" clstag="homepage|keycount|home2013|08a"></div>
				<ul id="navitems-2013">
					<li class="fore1" id="nav-home" clstag="homepage|keycount|home2013|07a"><a href="/">首页</a></li>
					<li class="fore2" id="nav-fashion" clstag="homepage|keycount|home2013|07b"><a href="http://fashion.jd.com/">书店区</a></li>
					<li class="fore3" id="nav-chaoshi" clstag="homepage|keycount|home2013|07c"><a href="http://channel.jd.com/chaoshi.html">书摊区</a></li>
					<li class="fore4" id="nav-tuan" clstag="homepage|keycount|home2013|07d"><a href="http://tuan.jd.com/" target="_blank">新书</a></li>
					<li class="fore5" id="nav-auction" clstag="homepage|keycount|home2013|07e"><a href="http://auction.jd.com/">二手书</a></li>
					<li class="fore6" id="nav-shan" clstag="homepage|keycount|home2013|07f"><a href="http://red.jd.com/" target="_blank">资讯</a></li>
					<li class="fore7" id="nav-jinrong" clstag="homepage|keycount|home2013|07g1"><a href="http://jr.jd.com/" target="_blank">社区</a></li>
				</ul>
		</div>
	</div>
</div>
<script type="text/javascript">
(function(){if(pageConfig.navId){var object=document.getElementById("nav-"+pageConfig.navId);if(object)object.className+=" curr";}})();
</script>