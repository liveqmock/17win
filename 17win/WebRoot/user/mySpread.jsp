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
								<div
									style="clear: both; width: 730px; background-color: rgb(243, 248, 254);">
									<div style="clear: both; margin-top: 20px; line-height: 150%;">
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<td>
														<table cellspacing="0" cellpadding="0" border="0"
															align="left">
															<tbody>
																<tr>
																	<td width="1">
																		<img width="3" height="25"
																			src="images/mytaobaobj1_3.gif">
																	</td>
																	<td width="120"
																		background="images/mytaobaobj1_4.gif"
																		align="middle">
																		<font color="#ffffff"><span>我推荐的用户</span>
																		</font>
																	</td>
																	<td width="1">
																		<img width="3" height="25"
																			src="images/mytaobaobj1_6.gif">
																	</td>
																</tr>
															</tbody>
														</table>
														<table cellspacing="0" cellpadding="0" border="0"
															align="left">
															<tbody>
																<tr>
																	<td>
																		<span style="padding-left: 20px;">从2010-8-21
																			13:11:52后推荐新人来淘宝刷信誉的</span>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<td height="4" bgcolor="#1e88c1"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div
									style="clear: both; border: 1px solid rgb(171, 190, 200); margin-top: 10px; width: 730px; background-color: rgb(243, 248, 254);">
									<table align="left"
										style="border-width: 0px; margin-left: 20px; width: 90%;">
										<thead
											style="border-bottom: 1px solid rgb(204, 204, 204); height: 40px;">
											<tr>
												<td width="12%" style="font-weight: bolder;">
													用户名
												</td>
												<td width="12%" style="font-weight: bolder;">
													联系方式
												</td>
												<td width="16%" style="font-weight: bolder;">
													发任务数
												</td>
												<td width="16%" style="font-weight: bolder;">
													接任务数
												</td>
												<td width="16%" style="font-weight: bolder;">
													注册时间
												</td>
												<td width="9%" style="font-weight: bolder;">
													现在积分
												</td>
												<td width="19%" style="font-weight: bolder;">
													现在发布点
												</td>
											</tr>
										</thead>
										<tbody>

											<tr>
												<td class="centers">
													qiuqiu
												</td>
												<td class="centers">
													<a href="tencent://message/?uin=380712448"><img
															border="0" src="http://wpa.qq.com/pa?p=1:380712448:4">
													</a>
												</td>
												<td class="centers">
													0个
												</td>
												<td class="centers">
													0个
												</td>
												<td class="centers">
													2010-8-22 14:29:24
												</td>
												<td class="centers">
													60
												</td>
												<td class="centers">
													3
												</td>
											</tr>


											<tr>
												<td class="centers">
													cjh1987
												</td>
												<td class="centers">
													<a href="tencent://message/?uin=909219625"><img
															border="0" src="http://wpa.qq.com/pa?p=1:909219625:4">
													</a>
												</td>
												<td class="centers">
													0个
												</td>
												<td class="centers">
													0个
												</td>
												<td class="centers">
													2010-8-26 8:47:18
												</td>
												<td class="centers">
													60
												</td>
												<td class="centers">
													3
												</td>
											</tr>


											<tr>
												<td align="middle" colspan="6">
													共
													<font color="blue"><b>2</b>
													</font> 条主题&nbsp;&nbsp;&nbsp;首页 上一页&nbsp;下一页 尾页&nbsp;页次：
													<strong><font color="red">1</font>/1</strong>页 &nbsp;
													<b>20</b>条主题/页&nbsp;转到：
													<select
														onchange="javascript:window.location='promotion.asp?PageNo='+this.options[this.selectedIndex].value;"
														size="1" name="page">
														<option selected="" value="1">
															第1页
														</option>
													</select>
												</td>
											</tr>


										</tbody>
									</table>
								</div>
								<div
									style="clear: both; width: 730px; background-color: rgb(243, 248, 254);">
									<div style="clear: both; margin-top: 20px; line-height: 150%;">
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<td>
														<table cellspacing="0" cellpadding="0" border="0"
															align="left">
															<tbody>
																<tr>
																	<td width="1">
																		<img width="3" height="25"
																			src="images/mytaobaobj1_3.gif">
																	</td>
																	<td width="200"
																		background="images/mytaobaobj1_4.gif"
																		align="middle">
																		<font color="#ffffff"><span>推广排行榜（推广积分排列）</span>
																		</font>
																	</td>
																	<td width="1">
																		<img width="3" height="25"
																			src="images/mytaobaobj1_6.gif">
																	</td>
																</tr>
															</tbody>
														</table>
														<table cellspacing="0" cellpadding="0" border="0"
															align="left">
															<tbody>
																<tr>
																	<td>
																		<span style="padding-left: 20px;"></span>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<td height="4" bgcolor="#1e88c1"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div
									style="clear: both; border: 1px solid rgb(171, 190, 200); margin-top: 10px; width: 730px; background-color: rgb(243, 248, 254);">
									<table align="left"
										style="border-width: 0px; margin-left: 20px; width: 90%;">
										<thead
											style="border-bottom: 1px solid rgb(204, 204, 204); height: 40px;">
											<tr>
												<td style="font-weight: bolder; width: 20%;">
													用户名
												</td>
												<td style="font-weight: bolder; width: 20%;">
													推荐人数
												</td>
												<td style="font-weight: bolder; width: 20%;">
													当前推广积分
												</td>
											</tr>
										</thead>
										<tbody>


											<tr>
												<td class="centers">
													ejianqin
												</td>
												<td class="centers">
													38人
												</td>
												<td class="centers">
													380
												</td>
											</tr>

											<tr>
												<td class="centers">
													13472291000
												</td>
												<td class="centers">
													34人
												</td>
												<td class="centers">
													340
												</td>
											</tr>

											<tr>
												<td class="centers">
													0610
												</td>
												<td class="centers">
													28人
												</td>
												<td class="centers">
													280
												</td>
											</tr>

											<tr>
												<td class="centers">
													262605736
												</td>
												<td class="centers">
													25人
												</td>
												<td class="centers">
													250
												</td>
											</tr>

											<tr>
												<td class="centers">
													ke288
												</td>
												<td class="centers">
													12人
												</td>
												<td class="centers">
													120
												</td>
											</tr>

											<tr>
												<td class="centers">
													小梦惠
												</td>
												<td class="centers">
													12人
												</td>
												<td class="centers">
													120
												</td>
											</tr>

											<tr>
												<td class="centers">
													ainidexu
												</td>
												<td class="centers">
													10人
												</td>
												<td class="centers">
													100
												</td>
											</tr>

											<tr>
												<td class="centers">
													婷婷千色坊
												</td>
												<td class="centers">
													10人
												</td>
												<td class="centers">
													100
												</td>
											</tr>

											<tr>
												<td class="centers">
													cuichuyan
												</td>
												<td class="centers">
													8人
												</td>
												<td class="centers">
													80
												</td>
											</tr>

											<tr>
												<td class="centers">
													一夜皇冠
												</td>
												<td class="centers">
													8人
												</td>
												<td class="centers">
													80
												</td>
											</tr>

											<tr>
												<td class="centers">
													VIP会员
												</td>
												<td class="centers">
													7人
												</td>
												<td class="centers">
													70
												</td>
											</tr>

											<tr>
												<td class="centers">
													aoweidongjac
												</td>
												<td class="centers">
													6人
												</td>
												<td class="centers">
													60
												</td>
											</tr>

										</tbody>
									</table>
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
