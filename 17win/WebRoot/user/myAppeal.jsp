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
											您现在的位置是：个人中心 &gt;&gt;我的申诉 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>我的申诉</strong>
										</div>
										<table width="100%" cellspacing="0" cellpadding="0" border="0"
											class="margin6">
											<tbody>
												<tr>
													<td width="16%" height="20" align="center">
														&nbsp;
														<span class="redcolor">&nbsp;任务编号</span>
													</td>
													<td width="13%">
														&nbsp;
													</td>
													<td width="12%">
														&nbsp;
													</td>
													<td width="15%">
														&nbsp;
													</td>

													<td width="15%">
														&nbsp;
													</td>
													<td width="14%">
														&nbsp;
													</td>
													<td width="15%">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td height="26" align="center" class="font12h">
														标题
													</td>
													<td align="center" class="font12h">
														被申诉人
													</td>
													<td align="center" class="font12h">
														任务ID
													</td>
													<td align="center" class="font12h">
														申诉时间
													</td>
													<td align="center" class="font12h">
														对方是否已辩解
													</td>
													<td align="center" class="font12h">
														官方是否已意见
													</td>
													<td align="center" class="font12h">
														操作
													</td>
												</tr>
											</tbody>
										</table>
										<table width="100%" cellspacing="0" cellpadding="0" border="0"
											id="con_three_1">

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
