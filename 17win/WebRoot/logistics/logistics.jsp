<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<HTML>
	<HEAD>
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<link href="http://www.17win.net/favicon.ico" rel="shortcut icon" />
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">
		<LINK href="css/center.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/aop.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/utils.js" type=text/javascript></SCRIPT>
		<SCRIPT src="logistics/logistics.js" type=text/javascript></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			defer="defer" type="text/javascript"></script>
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
		<s:form action="logisticsManager/logistics!insertLogistics.php"
			theme="simple" onsubmit="return validateForm()">
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
												您现在的位置是：个人中心 &gt;&gt; 物流管理 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>发送真实物流</strong>
											</div>
											<div>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<font color="red">注意：请认真填写以下信息，如果有人举报您填写的信息与真实信息不符合，我们将严惩！别人使用您的物流信息时，您会获得相应的金额！





												
											</div>

											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td height="10" colspan="3">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															物流公司
															<font color="red">(*)</font>：
														</td>
														<td>
															<select onchange="changeExpressCompany(this)"
																name="logisticsVO.logistics.expressCompany">
																<option value="顺丰快递">
																	顺丰快递
																</option>
																<option value="申通快递">
																	申通快递
																</option>
																<option value="圆通快递">
																	圆通快递
																</option>
																<option value="中通快递">
																	中通快递
																</option>
																<option value="韵达快递">
																	韵达快递
																</option>
																<option value="EMS快递">
																	EMS快递
																</option>
																<option value="德邦快递">
																	德邦快递
																</option>
																<option value="邮政平邮">
																	邮政平邮
																</option>
																<option value="宅急送快递">
																	宅急送快递
																</option>
																<option value="-1">
																	其他快递公司
																</option>
															</select>
															<input name="otherExpress" type="text" maxlength="20"
																style="display: none">
															<input type="hidden" name="win17_token"
																value="<s:property value="#session.win17_token"/>">
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															运货单号
															<font color="red">(*)</font>：
														</td>
														<td>
															<s:textfield name="logisticsVO.logistics.waybill"
																id="waybill" maxlength="25"></s:textfield>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															发货时间
															<font color="red">(*)</font>：
														</td>
														<td align="left" class="red-bcolor">
															<s:textfield readonly="true"
																name="logisticsVO.logistics.sendDate" id="sendDate"></s:textfield>
															<img style="cursor: pointer;"
																onclick="WdatePicker({'minDate':'<s:property value="#request.minDate"/>','maxDate':'<s:property value="#request.maxDate1"/>','alwaysUseStartDate':false,'el':'sendDate','isShowClear':true,startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd','skin':'blue'})"
																src="js/My97DatePicker/skin/datePicker.gif" width="16"
																height="22 align="absmiddle">
															<font color="red" style="font-size: 12px">发货时间的范围是昨天，今天，或则明天的单号</font>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															到达时间
															<font color="red">(*)</font>
															<font color="red"></font>：
														</td>
														<td align="left" class="red-bcolor">
															<s:textfield id="arrivalDate" readonly="true"
																name="logisticsVO.logistics.arrivalDate"
																id="arrivalDate"></s:textfield>
															<img style="cursor: pointer;"
																onclick="WdatePicker({'minDate':'#F{$dp.$D(\'sendDate\')}','maxDate':'<s:property value="#request.maxDate2"/>','alwaysUseStartDate':false,'el':'arrivalDate','isShowClear':true,startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd','skin':'blue'})"
																src="js/My97DatePicker/skin/datePicker.gif" width="16"
																height="22 align="absmiddle">
															<font color="red" style="font-size: 12px">预计时间必须大于等于您的发货时间，小于发货时间+15天的日期</font>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															购买金额
															<font color="red">(*)</font>：
														</td>
														<td align="left" class="red-bcolor">
															<s:textfield maxlength="5" value="0.5"
																name="logisticsVO.logistics.money" id="money"></s:textfield>
															元
														</td>
													</tr>	
													<tr>
														<td height="40" align="right" class="font12h" valign="top">
															发货信息
															<font color="red">(*)</font>：
														</td>
														<td>
															发货地址
															<input type="text" style="width: 316px" id="fhdz"
																maxlength="215">
															<br>
															邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编
															<input type="text" style="width: 60px" id="fhyb"
																maxlength="10">
															&nbsp;联系人
															<input type="text" style="width: 60px" id="fhlxr"
																maxlength="10">
															&nbsp;电话
															<input type="text" style="width: 100px" id="fhdh"
																maxlength="15">
															<s:hidden id="releaseInfo"
																name="logisticsVO.logistics.releaseInfo"></s:hidden>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h" valign="top">
															收货信息
															<font color="red">(*)</font>：
														</td>
														<td>
															收货地址
															<input type="text" style="width: 316px" id="shdz"
																maxlength="215">
															<br>
															邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编
															<input type="text" style="width: 60px" id="shyb"
																maxlength="10">
															&nbsp;联系人
															<input type="text" style="width: 60px" id="shlxr"
																maxlength="10">
															&nbsp;电话
															<input type="text" style="width: 100px" id="shdh"
																maxlength="15">
															<s:hidden id="receieveInfo"
																name="logisticsVO.logistics.receieveInfo"></s:hidden>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															备注：
														</td>
														<td>
															<s:textfield id="remark" cssStyle="width:300px"
																name="logisticsVO.logistics.remark" maxlength="255"></s:textfield>
														</td>
													</tr>
													<tr>
														<td align="center" class="red-bcolor" colspan="2">
															<input type="submit" value="保  存" id="button"
																style="cursor: pointer;" name="button">
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
	</BODY>
</HTML>
