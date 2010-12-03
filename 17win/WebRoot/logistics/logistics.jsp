<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
		<SCRIPT src="js/utils.js" type=text/javascript></SCRIPT>
		<SCRIPT src="logistics/logistics.js" type=text/javascript></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			type="text/javascript"></script>
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
												<strong>发送物流信息</strong>
											</div>
											<div>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<font color="red">注意：请认真填写以下信息，如果有人举报您填写的信息与真实信息不符合，我们将严惩！</font>
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
															<s:textfield name="logisticsVO.logistics.expressCompany"
																id="expressCompany" maxlength="20"></s:textfield>
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
															<s:textfield    readonly="true"
																name="logisticsVO.logistics.sendDate" id="sendDate"></s:textfield>
															<img style="cursor: pointer;"
																onclick="WdatePicker({'minDate':'%y-%M-%d','alwaysUseStartDate':false,'el':'sendDate','isShowClear':true,startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd','skin':'blue'})"
																src="js/My97DatePicker/skin/datePicker.gif" width="16"
																height="22 align="absmiddle">
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															预计到达时间
															<font color="red"></font>：
														</td>
														<td align="left" class="red-bcolor">
															<s:textfield id="arrivalDate" readonly="true"
																name="logisticsVO.logistics.arrivalDate"
																id="arrivalDate"></s:textfield>
															<img style="cursor: pointer;"
																onclick="WdatePicker({'minDate':'%y-%M-%d','alwaysUseStartDate':false,'el':'arrivalDate','isShowClear':true,startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd','skin':'blue'})"
																src="js/My97DatePicker/skin/datePicker.gif" width="16"
																height="22 align="absmiddle">
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h" valign="top">
															发货信息
															<font color="red">(*)</font>：
														</td>
														<td>
															<s:textfield id="releaseInfo" cssStyle="width:400px"
																name="logisticsVO.logistics.releaseInfo" maxlength="255"></s:textfield>
															<br>
															<font color="red">包括发货物品的价格，发货地等</font>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h" valign="top">
															收货信息
															<font color="red">(*)</font>：
														</td>
														<td>
															<s:textfield id="receieveInfo" cssStyle="width:400px"
																name="logisticsVO.logistics.receieveInfo"
																maxlength="255"></s:textfield>
															<br>
															<font color="red">包括收货人的相信地址，姓名，电话等</font>
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
