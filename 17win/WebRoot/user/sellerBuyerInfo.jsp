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
		<SCRIPT src="js/aop.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/ajax.js" type=text/javascript></SCRIPT>
		<SCRIPT src="user/updateInfo.js" type=text/javascript></SCRIPT>
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
		<s:form action="userInfoManager/info!updateInfo.php" theme="simple"
			onsubmit="return validateForm()">
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
												您现在的位置是：个人中心 &gt;&gt; 卖号/买号 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>卖号/买号管理</strong>
											</div>

											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td width="16%" height="40" align="right" class="font14b2">
															淘宝资料：
														</td>
														<td colspan="4">
															<hr style="color: rgb(255, 153, 51);">
														</td>
														<td width="20">
															<input type="button" value="新增">
														</td>
													</tr>
													<tr>
														<td height="10">
															&nbsp;
														</td>
														<td height="10" colspan="">
															淘宝店铺地址
														</td>
														<td height="10" colspan="">
															卖号发货地址
														</td>
														<td height="10" colspan="">
															淘宝卖号
														</td>
														<td height="10" colspan="">
															淘宝买号
														</td>
														<td height="10" colspan="">
															操作
														</td>
													</tr>
												</tbody>
											</table>
											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td width="100%" align="left" class="font12b2">
															<font color="red">注意：如果只增加卖家信息，那么品平台(淘宝,拍拍,有啊)小号可以不填写，反之亦然.淘宝账号高扭矩店铺地址自动获取</font>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<s:include value="../common/footDuan.jsp"></s:include>
		</s:form>
		<s:property value="#request.msg" escape="false" />
	</BODY>
</HTML>
