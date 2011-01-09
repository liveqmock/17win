<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">

		<LINK href="css/center.css" type="text/css" rel="stylesheet">
		<script src="js/validater.js" type="text/javascript"></script>
		<script src="js/utils.js" type="text/javascript"></script>
		<script src="paid/paid.js" type="text/javascript"></script>
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
											您现在的位置是：个人中心 &gt;&gt; 财务管理 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>账号充值</strong>
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<ul>
												<li>
													* 帐号充值主要用于发布任务，被接手后，这些钱就转回到您的店铺支付宝了，您也可以通过去接任务，获得存款和发布点
												</li>
												<!-- 
												<li>
													* 平台给您提供了以下三种种简单的充值方式，通过任意一种方式均可以实现“充值到平台”！
												</li>
												 -->
											</ul>
										</div>
										<div class="pp8">
											<strong>淘宝店铺充值</strong><font color="red">(只充值1,10,50,100)</font>
										</div>
										<div style="margin-top: 10px; line-height: 200%;">
											<table width="100%" cellspacing="0" cellpadding="0"
												border="0">
												<tbody>
													<tr>
														<td>
															充值中过程中，如有问题，请联系客户！
														</td>
													</tr>
													<tr>
														<td>
															<s:form action="payManager/pay!addPaid.php"  target="_blank"
																onsubmit="return validateForm()" theme="simple">
																<table width="90%" cellspacing="0" cellpadding="0"
																	border="0">
																	<tbody>
																		<tr>
																			<td class="inputtext">
																				金&nbsp;&nbsp;&nbsp;&nbsp;额：
																			</td>
																			<td>
																				<s:select listKey="key" listValue="value" id="money"
																					name="payVO.money"
																					list="#{'1':'1元','10':'10元','50':'50元','100':'100元'}">
																				</s:select>
																				<input type="hidden" name="win17_token"
																					value="<s:property value="#session.win17_token"/>">
																			</td>
																		</tr>
																		<tr>
																			<td class="inputtext">
																				操作码：
																			</td>
																			<td>
																				<input type="password" name="opertationCode"
																					maxlength="20" style="width: 145px; height: 15px;"
																					id="opertationCodeId">
																			</td>
																		</tr>
																		<tr>
																			<td class="inputtext">
																				验证码：
																			</td>
																			<td>
																				<input type="text" name="verificationCode"
																					id="verificationCodeID" maxlength="4"
																					style="width: 40px">
																				<img src="verify/verificationCode.php"
																					onclick="changeValidateCode(this)"
																					title="点击图片刷新验证码" style="cursor: pointer;" />
																			</td>
																		</tr>
																		<tr>
																			<td class="inputtext">
																			</td>
																			<td>
																				<input type="submit" value="提交" id="submitadd">
																			</td>
																		</tr>
																	</tbody>
																</table>
															</s:form>
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
		<s:if test="#request.toTaobao!=null">

			<script language="javascript">
				window.open("<s:property value="#request.toPayPage"/>","_blank");
			</script>
		</s:if>
	</BODY>
</HTML>
