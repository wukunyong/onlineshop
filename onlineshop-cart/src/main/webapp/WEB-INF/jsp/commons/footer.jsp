<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w" clstag="homepage|keycount|home2013|37a">
	<div id="service-2013">
		<dl class="fore1">
			<dt><b></b><strong>购物指南</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-56.html" target="_blank" rel="nofollow">购物流程</a></div>
				<div><a href="http://help.jd.com/help/question-57.html" target="_blank" rel="nofollow">会员介绍</a></div>
				<div><a href="http://help.jd.com/help/question-61.html" target="_blank" rel="nofollow">常见问题</a></div>
				<div><a href="http://help.jd.com/index.html" target="_blank" rel="nofollow">联系客服</a></div>
			</dd>
		</dl>
		<dl class="fore2">		
			<dt><b></b><strong>配送方式</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-64.html" target="_blank" rel="nofollow">上门自提</a></div>
				<div><a href="http://help.jd.com/help/question-360.html" target="_blank" rel="nofollow">211限时达</a></div>
				<div><a href="http://help.jd.com/help/distribution-768.html" target="_blank" rel="nofollow">配送服务查询</a></div>
				<div><a href="http://help.jd.com/help/question-892.html#help2215" target="_blank" rel="nofollow">配送费收取标准</a></div>
			</dd>
		</dl>
		<dl class="fore3">
			<dt><b></b><strong>支付方式</strong></dt>
			<dd>
				<div><a href="http://help.jd.com/help/question-67.html" target="_blank" rel="nofollow">货到付款</a></div>
				<div><a href="http://help.jd.com/help/question-68.html" target="_blank" rel="nofollow">在线支付</a></div>
				<div><a href="http://help.jd.com/help/question-71.html" target="_blank" rel="nofollow">分期付款</a></div>
				<div><a href="http://help.jd.com/help/question-70.html" target="_blank" rel="nofollow">银行转账</a></div>
			</dd>
		</dl>
		<dl class="fore4">		
			<dt><b></b><strong>售后服务</strong></dt>
			<dd>
				<div><a href="http://myjd.jd.com/afs/help/afshelp.action" target="_blank" rel="nofollow">售后政策</a></div>
				<div><a href="http://help.jd.com/help/question-100.html" target="_blank" rel="nofollow">退款说明</a></div>
				<div><a href="http://myjd.jd.com/repair/repairs.action" target="_blank" rel="nofollow">返修/退换货</a></div>
				<div><a href="http://help.jd.com/help/question-881.html" target="_blank" rel="nofollow">取消订单</a></div>
			</dd>
		</dl>
		<dl class="fore5">
			<dt><b></b><strong>特色服务</strong></dt>
			<dd>		
				<div><a href="http://help.jd.com/help/question-86.html" target="_blank">商家中心</a></div>
				<div><a href="http://fuwu.jd.com/" target="_blank" rel="nofollow">延保服务</a></div>
				<div><a href="http://giftcard.jd.com/market/index.action" target="_blank" rel="nofollow">运营服务</a></div>
				<div><a href="http://help.jd.com/help/question-91.html" target="_blank" rel="nofollow">节能补贴</a></div>
			</dd>
		</dl>
		
		<span class="clr"></span>
	</div>
</div>
<div class="w" clstag="homepage|keycount|home2013|38a">
	<jsp:include page="footer-links.jsp"></jsp:include>
</div>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/jquery-extend.js"></script>
<script type="text/javascript" src="/js/lib-v1.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/ego.js" charset="utf-8"></script>
<script type="text/javascript"> (function(){
var A="<strong>热门搜索：</strong><a href='http://localhost:8083/search.html?q=%E4%B8%9C%E9%87%8E%E5%9C%AD%E5%90%BE' target='_blank' style='color:#ff0000' clstag='homepage|keycount|home2013|03b1'>java</a><a href='http://localhost:8083/search.html?q=%E8%A5%BF%E6%B8%B8%E8%AE%B0' target='_blank'>西游记</a><a href='http://localhost:8083/search.html?q=%E6%AF%9B%E6%B3%BD%E4%B8%9C%E9%80%89%E9%9B%86' target='_blank'>毛泽东选集</a><a href='http://localhost:8083/search.html?q=%E5%B9%B3%E5%87%A1%E7%9A%84%E4%B8%96%E7%95%8C' target='_blank'>平凡的世界</a><a href='http://localhost:8083/search.html?q=%E8%AE%A1%E7%AE%97%E6%9C%BA%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F' target='_blank'>计算机操作系统</a><a href='http://localhost:8083/search.html?q=%E9%87%91%E5%BA%B8' target='_blank'>金庸</a><a href='http://localhost:8083/search.html?q=%E5%93%88%E5%88%A9%E6%B3%A2%E7%89%B9' target='_blank'>哈利波特</a><a href='http://sale.jd.com/act/FstSdb2vCOLa8BRi.html' target='_blank'>追风筝的人</a>";
var B=["java","活着","流浪地球","三体","钱钟书","东野圭吾"];
B=pageConfig.FN_GetRandomData(B);
$("#hotwords").html(A);
var _searchValue = "${query}";
if(_searchValue.length == 0){
	_searchValue = B;
}
$("#key").val(_searchValue).bind("focus",function(){if (this.value==B){this.value="";this.style.color="#333"}}).bind("blur",function(){if (!this.value){this.value=B;this.style.color="#999"}});
})();
</script>