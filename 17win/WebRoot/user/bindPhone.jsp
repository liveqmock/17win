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

							<td valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td height="5">
										</td>
									</tr>

								</table>
								<div class=pp9>
									<div style="PADDING-BOTTOM: 15px; WIDTH: 97%">
										<div class=pp7>
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt;绑定手机&gt;&gt;
										</div>
										<div class="pp8">
											<strong>绑定手机</strong>
										</div>

										<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>

											<TBODY>

												<TR>
													<TD height=600 valign="top" class="border_hui3 wenzi">
														<TABLE width="99%" border=0 align="center" cellPadding=0
															cellSpacing=0 class="padding_left">
															<TBODY>
																<TR>
																	<TD vAlign=top>
																		<TABLE
																			style="MARGIN-TOP: 10px; WIDTH: 100%; BACKGROUND-COLOR: #f3f8fe">
																			<tr>
																				<td height="55" align="center" class="borderc">
																					&nbsp;
																				</td>

																				<td colspan="2" align="left" class="borderc">
																					已绑定的手机号是：*******5607
																				</td>
																			</tr>
																			<tr>
																				<td height="55" align="center" class="borderc">
																					&nbsp;
																				</td>

																				<td colspan="2" align="left" class="borderc">
																					新手机好嘛：
																					<input type="text" />
																					<input type="button" value="提交" />
																					<font color="red">重新绑定需要50个发布点</font>
																				</td>
																			</tr>
																		</TABLE>

																		<!--2项 -->
																	</TD>
																</TR>
														</TABLE>
													</TD>
												</TR>
										</TABLE>

									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
