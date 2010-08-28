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
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 密码/操作密码 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>修改资料</strong>
										</div>


										<form action="" method="post" onsubmit="return save_onclick()"
											id="form" name="form">
											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td height="10" colspan="3">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td width="146" height="40" align="right" class="font12h">
															当前操作密码：
														</td>
														<td width="230">
															<input type="password" size="30" id="czm"
																class="text_normal" name="czm">
														</td>
														<td width="524">
															修改密码需要提供当前操作密码
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															登录密码：
														</td>
														<td>
															<input type="password" size="30" id="pwd" name="pwd">
														</td>
														<td>
															留空则不修改
														</td>
													</tr>

													<tr>
														<td height="40" align="right" class="font12h">
															确认登录密码：
														</td>
														<td>
															<span class="font12h"> <input type="password"
																	size="30" id="pwd2" name="pwd2"> </span>
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
														<td>
															&nbsp;
														</td>
													</tr>


													<tr>
														<td height="40" align="right" class="font12h">
															操作密码：
														</td>
														<td align="left" class="red-bcolor">
															<input type="password" size="30" id="eczm" name="eczm">
														</td>
														<td>
															留空则不修改
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															确认操作密码：
														</td>
														<td align="left" class="red-bcolor">
															<input type="password" size="30" id="eczm2" name="eczm2">
														</td>
														<td>
															操作密码不允许和登录密码相同
														</td>
													</tr>
													<tr>
														<td height="40" align="left" class="font12h" colspan="3">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td align="center" class="red-bcolor">
															<input type="submit" value="修  改" id="button"
																name="button">
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
												</tbody>
											</table>
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
