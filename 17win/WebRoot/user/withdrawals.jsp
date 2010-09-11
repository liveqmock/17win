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
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/center.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/aop.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/withdrawals.js" type="text/javascript"></SCRIPT>

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
											您现在的位置是： 个人中心 &gt;&gt; 申请提现 &gt;&gt;
										</div>
										<div class="pp8">

											<strong>申请提现</strong>
											<input type="hidden"
												value="<s:property value="#session.userLogin.money"/>"
												id="money">
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
										<br>
										<br>
										<!-- xgj -->
										<div>
											<div id="tabs">
												<ul>
													<li>
														<a href="#tabs-1">商品地址提现</a>
													</li>
													<li>
														<a href="#tabs-2">支付宝提现</a>
													</li>
													<li>
														<a href="#tabs-3">财付通提现</a>
													</li>
												</ul>
												<div id="tabs-1">
													<s:form
														action="withdrawalsManager/withdrawals!withdrawals.php"
														theme="simple" onsubmit="return validateForm('1')">
														<table>
															<tr>
																<Td align="right">
																	提现人：
																	<input name="withdrawalsVO.type" type="hidden"
																		value="1">
																	<input name="withdrawalsVO.shopType" id="shopType" type="hidden"
																		value="1">
																</Td>
																<Td>
																	<s:property value="#session.userLogin.username" />
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	提现金额：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.money" id="money_1"></s:textfield>
																	元
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	商品地址：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.realIdentity"
																		id="realIdentity_1"></s:textfield>
																	(淘宝，拍拍，有啊任意一个和提现金额价格相等的商品地址)
																	<span id="errorShop"></span>
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	掌柜：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.realname"  readonly="true"
																		id="realname_1"></s:textfield>
																	(系统自动获取)
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	操作密码：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.operationCode"
																		id="operationCode_1"></s:textfield>
																</Td>
															</tr>
															<tr>
																<Td colspan="2" align="center">
																	<input type="submit" class="buttonFlag"
																		value="提&nbsp;&nbsp;交">
																</Td>

															</tr>
														</table>
													</s:form>
												</div>
												<div id="tabs-2">
													<s:form
														action="withdrawalsManager/withdrawals!withdrawals.php"
														theme="simple" onsubmit="return validateForm('2')">
														<table>
															<tr>
																<Td align="right">
																	提现人：
																	<input name="withdrawalsVO.type" type="hidden"
																		value="2">
																</Td>
																<Td>
																	<s:property value="#session.userLogin.username" />
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	提现金额：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.money" id="money_2"></s:textfield>
																	元
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	您的支付宝账号：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.realIdentity"
																		id="realIdentity_2"></s:textfield>
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	您的真名：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.realName"
																		id="realName_2"></s:textfield>
																</Td>
															</tr>

															<tr>
																<Td align="right">
																	操作密码：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.operationCode"
																		id="operationCode_2"></s:textfield>
																</Td>
															</tr>
															<tr>
																<Td colspan="2" align="center">
																	<input type="submit" value="提&nbsp;&nbsp;交">
																</Td>

															</tr>
														</table>
													</s:form>
												</div>
												<div id="tabs-3">
													<s:form
														action="withdrawalsManager/withdrawals!withdrawals.php"
														theme="simple" onsubmit="return validateForm('3')">
														<table>
															<tr>
																<Td align="right">
																	提现人：
																	<input name="withdrawalsVO.type" type="hidden"
																		value="3">
																</Td>
																<Td>
																	<s:property value="#session.userLogin.username" />
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	提现金额：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.money" id="money_3"></s:textfield>
																	元
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	您的财付通账号：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.realIdentity"
																		id="realIdentity_3"></s:textfield>
																</Td>
															</tr>
															<tr>
																<Td align="right">
																	您的真名：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.realName"
																		id="realName_3"></s:textfield>
																</Td>
															</tr>

															<tr>
																<Td align="right">
																	操作密码：
																</Td>
																<Td>
																	<s:textfield name="withdrawalsVO.operationCode"
																		id="operationCode_3"></s:textfield>
																</Td>
															</tr>
															<tr>
																<Td colspan="2" align="center">
																	<input type="submit" value="提&nbsp;&nbsp;交">
																</Td>

															</tr>
														</table>
													</s:form>
												</div>
											</div>
										</div>
										<!-- end -->
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
