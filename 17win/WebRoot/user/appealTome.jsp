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
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 申请提现 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>申请提现</strong>
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<ul>
												<li>
													* 平台提供三种提现方式，淘宝商品提现，支付宝提现，财富通提现。
													<br>
													* 您也可以在提现列表内撤销提现，将自动为您立即返回金额至您用户名。
													<br>
													* 您的操作记录中也会有相应的记录信息。
													<br>
													*
													由于提现量大，商品链接低于100元、支付宝低于100元的提现，请以发布任务的形式，让别人接手您的任务，存款就转到您的店铺支付宝了！
												</li>
											</ul>
										</div>
										<br>
										<br>
										<form onsubmit="return save_onclick22();" method="post"
											action="" name="formt">
											<div>
												<table width="614" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody>
														<tr>
															<td height="35" align="left">
																&nbsp;
															</td>
															<td>
																&nbsp;
															</td>
														</tr>
														<tr>
															<td height="35" align="right">
																&nbsp;
															</td>
															<td>
																<input type="radio" checked="" value="1"
																	onclick="javascript:location.href='remoney.asp';"
																	name="class">
																淘宝商品地址提现
																<input type="radio"
																	onclick="javascript:location.href='remoney1.asp';"
																	value="2" name="class">
																支付宝提现
																<input type="radio"
																	onclick="javascript:location.href='remoney2.asp';"
																	value="2" name="class">
																财富通提现
															</td>
														</tr>
														<tr>
															<td height="35" align="right">
																提现人：
															</td>
															<td>
																xgj1988
															</td>
														</tr>
														<tr>
															<td width="134" height="35" align="right">
																提现金额：
															</td>
															<td width="480">
																<input onkeyup="if(isNaN(value))execCommand('undo')"
																	name="ReNum1" maxlength="4" id="ReNum1">
																元
															</td>
														</tr>
														<tr>
															<td height="35" align="right">
																掌柜：
															</td>
															<td>
																<input name="ReRName1" maxlength="50" id="ReRName1">
															</td>
														</tr>
														<tr>
															<td height="35" align="right">
																淘宝商品地址：
															</td>
															<td>
																<input maxlength="255" size="35" id="ReZfb1"
																	name="ReZfb1">
																商品价格必须和提现金额相同，否则会撤销提现
															</td>
														</tr>
														<tr>
															<td height="35">
																<div align="right">
																	操作密码：
																</div>
															</td>
															<td>
																<input type="password" id="czm" name="czm">
															</td>
														</tr>
														<tr>
															<td height="35">
																&nbsp;
															</td>
															<td>
																<input type="submit" value="开始提现" id="button"
																	name="button">
																<input type="hidden" value="ok" name="action">
															</td>
														</tr>
													</tbody>
												</table>
											</div>

										</form>
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
