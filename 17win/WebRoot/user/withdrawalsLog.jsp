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
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 提现列表 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>提现列表</strong>
										</div>
										<br>
										<br>
										<table width="100%" cellspacing="0" cellpadding="0" border="0"
											bgcolor="#ffffff" align="center">
											<tbody>
												<tr>
													<td width="125" height="26" bgcolor="#fffcd2"
														background="images/luntan03.gif" align="center"
														class="red-bcolor border-bot">
														<div align="center">
															流水号
														</div>
													</td>
													<td width="142" bgcolor="#fffcd2"
														background="images/luntan03.gif" align="center"
														class="red-bcolor  border-bot">
														<div align="center">
															金额
														</div>
													</td>
													<td width="143" bgcolor="#fffcd2"
														background="images/luntan03.gif" align="center"
														class="red-bcolor  border-bot">
														<div align="center">
															提现接收号
														</div>
													</td>
													<td width="143" bgcolor="#fffcd2"
														background="images/luntan03.gif" align="center"
														class="red-bcolor  border-bot">
														<div align="center">
															提现时间
														</div>
													</td>
													<td width="94" bgcolor="#fffcd2"
														background="images/luntan03.gif" align="center"
														class="red-bcolor  border-bot">
														<div align="center">
															状态
														</div>
													</td>
													<td width="111" bgcolor="#fffcd2"
														background="images/luntan03.gif" align="center"
														class="border-bot red-bcolor ">
														<div align="center">
															操作
														</div>
													</td>
												</tr>

												<tr>
													<td height="35" align="center" class="border-botdashed"
														colspan="6"></td>
												</tr>
											</tbody>
										</table>
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
