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
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 账号充值 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>账号充值</strong>
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<ul>
												<li>
													* 帐号充值主要用于发布任务，被接手后，这些钱就转回到您的店铺支付宝了，您也可以通过去接任务，获得存款和发布点
												</li>
												<li>
													* 平台给您提供了以下三种种简单的充值方式，通过任意一种方式均可以实现“充值到平台”！
												</li>
											</ul>
										</div>
										<div class="pp8">
											<strong>在线充值卡：</strong>在线自动充值！（免手续费）
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<table width="100%" cellspacing="0" cellpadding="0"
												border="0">
												<tbody>
													<tr>
														<td>
															第一步：购买官方淘宝店铺的充值卡，一次可购买多张；
															<br>
															点击进入充值卡商品地址 |
															<a target="_blank"
																href="http://item.taobao.com/item.htm?id=6048789884"><span
																style="color: red;">50元</span>
															</a> |
															<a target="_blank"
																href="http://item.taobao.com/item.htm?id=6048810374"><span
																style="color: red;">100元</span>
															</a> |
															<a target="_blank"
																href="http://item.taobao.com/item.htm?id=6048831048"><span
																style="color: red;">300元</span>
															</a> |
															<a target="_blank"
																href="http://item.taobao.com/item.htm?id=6048843306"><span
																style="color: red;">500元</span>
															</a> |
															<a target="_blank"
																href="http://item.taobao.com/item.htm?id=6048856788"><span
																style="color: red;">1000元</span>
															</a> |
															<a target="_blank"
																href="http://item.taobao.com/item.htm?id=6048871746"><span
																style="color: red;">3000元</span>
															</a> &lt;--请点击您要充值的数额
														</td>
													</tr>
													<tr>
														<td>
															第二步：在下面输入购买得到的卡号和密码进行存款充值；
														</td>
													</tr>
													<tr>
														<td>
															<form onsubmit="return CheckForm();" method="post"
																action="" name="form">
																<table width="90%" cellspacing="0" cellpadding="0"
																	border="0">
																	<tbody>
																		<tr>
																			<td class="inputtext">
																				卡号：
																			</td>
																			<td>
																				<input name="num" maxlength="34"
																					style="width: 145px; height: 15px;" id="num">
																			</td>
																		</tr>
																		<tr>
																			<td class="inputtext">
																				密码：
																			</td>
																			<td>
																				<input type="password" name="pwd" maxlength="16"
																					style="width: 145px; height: 15px;" id="pwd">
																			</td>
																		</tr>
																		<tr>
																			<td class="inputtext">
																			</td>
																			<td>
																				<input type="submit" value="充值卡充值" id="submitadd">
																			</td>
																		</tr>
																	</tbody>
																</table>
															</form>

														</td>
													</tr>
													<tr>
														<td>
															注：拍下充值卡商品并支付，在您确认收货后，联系客服获得密码，请拍下、支付、确认收货、平台充值
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="pp8">
											<strong>网上银行在线充值：</strong>立即自动到账
										</div>
										<table width="100%" cellspacing="0" cellpadding="0" border="0"
											bgcolor="#666666" align="center">
											<form onsubmit="return save_onclick()" target="_blank"
												method="post" action="../chinabank/Send.asp" name="formbill"></form>
											<tbody>
												<tr>
													<td bgcolor="#ffffff">
														&nbsp;
													</td>
												</tr>
												<tr>
													<td bgcolor="#ffffff">
														<!--隐藏参数-->
														<!--定单号-->
														<input type="hidden" value="" maxlength="64" name="v_oid">
														<!--收货人姓名-->
														<input type="hidden" value="" name="v_rcvname">
														<!--收货人地址-->
														<input type="hidden" id="v_rcvaddr" name="v_rcvaddr">
														<!--收货人电话-->
														<input type="hidden" id="v_rcvtel" name="v_rcvtel">
														<!--收货人邮编-->
														<input type="hidden" value="" id="v_rcvpost"
															name="v_rcvpost">
														<!--收货人邮件-->
														<input type="hidden" name="v_rcvemail">
														<!--收货人手机号码-->
														<input type="hidden" value="" name="v_rcvmobile">
														<!---->
														<!--订货人姓名-->
														<input type="hidden" value="" name="v_ordername">
														<!--订货人地址-->
														<input type="hidden" value="" id="v_orderaddr"
															name="v_orderaddr">
														<!--订货人电话-->
														<input type="hidden" value="" id="v_ordertel"
															name="v_ordertel">
														<!--订货人邮编-->
														<input type="hidden" value="" id="v_orderpost"
															name="v_orderpost">
														<!--订货人邮件-->
														<input type="hidden" value="" name="v_orderemail">
														<!--订货人手机号码-->
														<input type="hidden" value="" name="v_ordermobile">
														<!--备注2-->
														<input type="hidden" value="" id="remark2" name="remark2">

														<table width="98%" cellspacing="0" cellpadding="4"
															border="1" align="center"
															style="border-collapse: collapse;">
															<tbody>
																<tr>
																	<td height="24" align="right">
																		会员名：
																	</td>
																	<td valign="top">
																		<input type="text" readonly="" value="xgj1988"
																			id="remark1" name="remark1">
																		&nbsp;&nbsp;
																		<font color="#ff0000">//</font>请确认用户名
																	</td>
																</tr>
																<tr>
																	<td height="24" align="right">
																		充值金额（元）：
																	</td>
																	<td height="24">
																		<input type="text"
																			onkeyup="if(isNaN(value))execCommand('undo')"
																			name="v_amount">
																		&nbsp;&nbsp;
																		<font color="#ff0000">*</font>必填项，譬如：
																		<font color="#ff0000">100.00</font>
																		<br>
																	</td>
																</tr>
																<tr>
																	<td height="24" align="right">
																	</td>
																	<td valign="top">
																		<input type="submit" disabled="" value=" 网银在线支付 "
																			name="Submit">
																		&nbsp;
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
										<div class="pp8">
											<strong>支付宝在线充值：</strong>立即自动到账
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<table width="100%" cellspacing="0" cellpadding="0"
												border="0">
												<tbody>
													<tr>
														<td align="middle"></td>
													</tr>
													<tr>
														<td align="middle">
															<table width="98%" cellspacing="0" cellpadding="4"
																border="1" align="center"
																style="border-collapse: collapse;">
																<form onsubmit="return save_onclicka()" target="_blank"
																	method="post" action="../alipay/index.asp" name="forma"></form>

																<tbody>
																	<tr>
																		<td height="24" align="right">
																			会员名：
																		</td>
																		<td valign="top">
																			<div align="left">
																				<input type="text" readonly="" value="xgj1988"
																					id="remark1" name="remark1">
																				&nbsp;&nbsp;
																				<font color="#ff0000">//</font>请确认用户名
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td height="24" align="right">
																			充值金额（元）：
																		</td>
																		<td height="24">
																			<div align="left">
																				<input type="text"
																					onkeyup="if(isNaN(value))execCommand('undo')"
																					name="v_amounta">
																				&nbsp;&nbsp;
																				<font color="#ff0000">*</font>必填项，譬如：
																				<font color="#ff0000">100.00</font>
																				<br>
																			</div>
																		</td>
																	</tr>
																	<tr>
																		<td height="24" align="right">
																			<div align="right"></div>
																		</td>
																		<td valign="top">
																			<div align="left">
																				<input type="submit" disabled="" value=" 支付宝在线支付"
																					name="Submit">
																				<input type="hidden" value="在线充值" id="subject"
																					name="subject">
																			</div>
																		</td>
																	</tr>
																</tbody>

															</table>
														</td>
													</tr>
												</tbody>
											</table>
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
