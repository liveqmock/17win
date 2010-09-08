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
		<SCRIPT src="js/ajax.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/aop.js" type=text/javascript></SCRIPT>
		<SCRIPT src="user/sellerBuyerInfo.js" type=text/javascript></SCRIPT>


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
		<s:form action="userInfoManager/info!updateSellerAndBuyer.php"
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
												您现在的位置是：个人中心 &gt;&gt; 卖号/买号 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>卖号/买号管理</strong>
												<s:select id="tempProvince" list="#request.provinces"
													cssStyle="display:none" listKey="id" listValue="name"
													headerKey="" headerValue="--请选择--">
												</s:select>
											</div>
											<s:iterator value="#request.sellers.keys" id="type">
												<table width="99%" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody id="sellerTable<s:property value="#type"/>">
														<tr>
															<td width="16%" height="40" align="right" nowrap="nowrap"
																class="font14b2">
																<s:set name="platformName"
																	value="#type==1?'淘宝':#type==2?'拍拍':'有啊'"></s:set>
																<s:property value="#platformName" />
																卖号资料：
															</td>
															<td colspan="4">
																<hr style="color: rgb(255, 153, 51);">
															</td>
															<td width="20">
																<input type="button" value="新增" style="cursor: pointer;"
																	sellerFlag="<s:property value="#type"/>">
															</td>
														</tr>
														<tr style="background: #EDF6FF">
															<th height="10" nowrap="nowrap" align="center">
																店铺地址
															</th>
															<th height="10" nowrap="nowrap" align="center">
																发货地址
															</th>
															<th height="10" nowrap="nowrap" align="center">
																卖号
															</th>
															<th height="10" nowrap="nowrap" align="center">
																操作
															</th>
														</tr>
														<s:iterator value="#request.sellers.get(#type)"
															id="seller">
															<tr class="sellerTr">
																<td height="10">
																	<input type="text" name="userVO.sellers[0].shopURL"
																		value="<s:property value="#seller.shopURL" />" />
																	<input type="hidden" name="userVO.sellers[0].type"
																		value="<s:property value="#type" />">
																</td>
																<td height="10" class="address">
																	省:
																	<s:select id="provinceID" value="#seller.province.id"
																		name="userVO.sellers[0].province.id"
																		list="userVO.provinces" listKey="id" listValue="name"
																		headerKey="" headerValue="--请选择--">
																	</s:select>
																	市:
																	<s:select id="cityID" name="userVO.sellers[0].city.id"
																		value="#seller.city.id" list="userVO.cities"
																		listKey="id" listValue="name" headerKey=""
																		headerValue="请选择">
																	</s:select>
																	县:
																	<s:select id="areaID" name="userVO.sellers[0].area.id"
																		value="#seller.area.id" list="userVO.areas"
																		listKey="id" listValue="name" headerKey=""
																		headerValue="请选择">
																	</s:select>
																</td>
																<td>
																	<input type="text" name="userVO.sellers[0].name"
																		value="<s:property value="#seller.name" />" />
																</td>
																<td>
																	<a href="javascript:void(0)"
																		onclick="deleteSeller(this)">删除</a>
																</td>
															</tr>
														</s:iterator>
													</tbody>
												</table>
											</s:iterator>
											<s:iterator value="#request.buyers.keys" id="type">
												<s:set name="platformName"
													value="#type==1?'淘宝':#type==2?'拍拍':'有啊'"></s:set>
												<table width="99%" cellspacing="0" cellpadding="0"
													border="0" align="center">
													<tbody id="buyerTable<s:property value="#type"/>">
														<tr>
															<td width="16%" height="40" align="right"
																class="font14b2">
																<s:property value="#platformName" />
																买号资料：

															</td>
															<td colspan="4">
																<hr style="color: rgb(255, 153, 51);">
															</td>
															<td width="20">
																<input type="button" value="新增"
																	buyerFlag="<s:property value="#type"/>"
																	style="cursor: pointer;">
															</td>
														</tr>

														<tr style="background: #EDF6FF">
															<th height="10" nowrap="nowrap" align="center">
																买号
															</th>
															<th height="10" nowrap="nowrap" align="center">
																买号积分
															</th>
															<th height="10" nowrap="nowrap" align="center">
																操作
															</th>
														</tr>
														<s:iterator value="#request.buyers.get(#type)" id="buyer">
															<tr>
																<td height="10">
																	<input type="text" name=""
																		value="<s:property value="#buyer.name" />">
																	<input type='hidden' name='userVO.buyers[0].type'
																		value="<s:property value="#type"/>" />
																</td>
																<td align="center">
																	<s:property value="#buyer.score" />
																</td>
																<td align="center">
																	<a href="javascript:void(0)"
																		onclick="deleteBuyer(this)">删除</a>
																</td>
															</tr>
														</s:iterator>
													</tbody>

												</table>
												<hr color="#0082E0">
											</s:iterator>
											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody>
													<tr>
														<td width="100%" align="left" class="font12b2">
															<font color="red">注意：为了您和他人的安全，买好在淘宝上如果是一个黄钻，请不要在使用！</font>
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
