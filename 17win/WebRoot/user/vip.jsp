<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">

		<LINK href="css/center.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>

		<style type="text/css">
body {
	
}

img {
	vertical-align: bottom;
	border: 0px;
}

.sec_menu {
	border-left: 1px solid white;
	border-right: 1px solid white;
	border-bottom: 1px solid white;
	overflow: hidden;
}

.menu_title {
	
}

.menu_title span {
	position: relative;
	top: 2px;
	left: 28px;
	color: #ffffff;
	font-weight: bold;
}

.menu_title2 {
	
}

.menu_title2 span {
	position: relative;
	top: 2px;
	left: 28px;
	color: #ffffff;
	font-weight: bold;
}
</style>

	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="760" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td>
					<table width="910" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<!-- xgj   user left menu-->
							<s:include value="../common/user/infoMenu.jsp"></s:include>
							<!-- end xgj -->
							<td width="15">
								&nbsp;
							</td>

							<!-- 要插入的 -->
							<td valign="top">
								<table width="100%" cellspacing="0" cellpadding="0" border="0">
									<tbody>
										<tr>
											<td height="5"></td>
										</tr>
									</tbody>
								</table>
								<div class="pp9">
									<div style="padding-bottom: 15px; width: 97%;">
										<div class="pp7">
											您现在的位置是：个人中心 &gt;&gt; 加入VIP &gt;&gt;
										</div>
										<div class="pp8">
											<strong>加入VIP</strong>
										</div>
										<br>

										<div
											style="clear: both; width: 710px; background-color: rgb(243, 248, 254);">
											<div
												style="clear: both; padding-right: 30px; padding-left: 30px; height: 1280px;">

												<div>
												</div>

												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; padding-top: 15px;">
													<div style="float: left; width: 350px;">
														<img src="images/201081115382471373.jpg">
													</div>
													<form id="Form2" action="" method="post" name="Form2">
														<input type="hidden" value="ok" name="action">
														<input type="hidden" value="1" name="id">
														<div
															style="float: left; width: 200px; line-height: 150%; padding-top: 20px;">
															<span style="color: red;">vip会员身份为半个月</span>
															<br>
															有效期：15天
															<br>
															半月卡：15元
															<br>
															<input type="image" name="ImageButton2"
																src="images/buy1.png"
																onclick="return confirm('您确定要购买此卡吗？');"
																id="ImageButton2">
														</div>
													</form>
												</div>

												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; padding-top: 15px;">
													<div style="float: left; width: 350px;">
														<img src="images/201081115382471373.jpg">
													</div>
													<form id="Form2" action="" method="post" name="Form2">
														<input type="hidden" value="ok" name="action">
														<input type="hidden" value="2" name="id">
														<div
															style="float: left; width: 200px; line-height: 150%; padding-top: 20px;">
															<span style="color: red;">vip会员可以任务连发 可以查看接手方ip
																可以使用卖家提示功能。 接手任务成功所得到发布点比例可以和普通会员不一样，
																每日每个淘宝买号最多只能接手同一个店铺5个商品链接 每日每个淘宝买号最多一天只能接20个商品链接
																每日每个IP只能接手一次同一个店铺的商品!</span>
															<br>
															有效期：30天
															<br>
															全月卡：30元
															<br>
															<input type="image" name="ImageButton2"
																src="images/buy1.png"
																onclick="return confirm('您确定要购买此卡吗？');"
																id="ImageButton2">
														</div>
													</form>
												</div>


												<div></div>
											</div>
										</div>
									</div>
								</div>
							</td>
							<!-- end -->
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
