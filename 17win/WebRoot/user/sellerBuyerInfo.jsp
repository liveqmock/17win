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
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type=text/javascript></SCRIPT>
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

.errorText {
	border: #FF0000 solid;
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
											<input type="button" value="新增" style="height: 25px"
												id="addBtn">
										</div>
										<s:iterator value="#request.sellers.keys" id="type">
											<table width="100%" cellspacing="0" cellpadding="0"
												border="0" align="center">
												<tbody id="sellerTable<s:property value="#type"/>">
													<tr>
														<td colspan="3" height="40" align="left" nowrap="nowrap"
															class="font14b2">
															<s:set name="platformName"
																value="#type==1?'淘宝':#type==2?'拍拍':'有啊'"></s:set>
															<s:property value="#platformName" />
															卖号资料：
														</td>
													</tr>
													<tr style="background: #EDF6FF">
														<th height="10" nowrap="nowrap" align="center" width="30%">
															店铺地址
														</th>
														<th height="10" nowrap="nowrap" align="center" width="20%">
															卖号
														</th>
														<th height="10" nowrap="nowrap" align="center" width="30%">
															发货地址
														</th>
														<th height="10" nowrap="nowrap" align="center" width="20%">
															操作
														</th>
													</tr>
													<s:iterator value="#request.sellers.get(#type)" id="seller">
														<tr class="sellerTr">
															<td align='center'>
																<input type="hidden"
																	platformType="<s:property value="#type"/>"
																	shopUrl="<s:property value="#seller.shopURL" />" />
																<s:property value="#seller.shopURL" />
															</td>
															<td align='center'>
																<input type="hidden"
																	platformType="<s:property value="#type"/>"
																	sellerName="<s:property value="#seller.name" />" />
																<s:property value="#seller.name" />
															</td>
															<td align="center" nowrap="nowrap">
																<s:property value="#seller.address" />
															</td>
															<td align="center" nowrap="nowrap">
																<a
																	href="javascript:showUpdateDIV('<s:property value="#seller.id" />')">修改发货地址</a>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</s:iterator>
										<hr color="#0082E0" style="height: 10px; background: #0082E0">
										<s:iterator value="#request.buyers.keys" id="type">
											<s:set name="platformName"
												value="#type==1?'淘宝':#type==2?'拍拍':'有啊'"></s:set>
											<table width="99%" cellspacing="0" cellpadding="0" border="0"
												align="center">
												<tbody id="buyerTable<s:property value="#type"/>">
													<tr>
														<td width="16%" colspan="2" height="40" align="left"
															class="font14b2">
															<s:property value="#platformName" />
															买号资料：

														</td>
													</tr>
													<tr style="background: #EDF6FF">
														<th height="10" nowrap="nowrap" align="center"
															width="33.3%">
															买号
														</th>
														<th height="10" nowrap="nowrap" align="center"
															width="33.3%">
															信誉值
														</th>
														<th height="10" nowrap="nowrap" align="center"
															width="33.3%">
															是否可用
														</th>
													</tr>
													<s:iterator value="#request.buyers.get(#type)" id="buyer">
														<tr class="buyerTr">
															<td height="10" align="center">
																<input type="hidden"
																	platformType="<s:property value="#type"/>"
																	buyerName="<s:property value="#buyer.name" />" />
																<s:property value="#buyer.name" />
															</td>
															<td height="10" align="center">
																<s:property value="#buyer.score" />
															</td>
															<td height="10" align="center">
																<s:if test="#buyer.enable">
																	是
																</s:if>
																<s:else>
																	否
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</tbody>

											</table>

										</s:iterator>
										<table width="99%" cellspacing="0" cellpadding="0" border="0"
											align="center">
											<tbody>
												<tr>
													<td width="100%" align="left" class="font12b2">
														<font color="red">注意：为了您和他人的安全，买号在淘宝上如果是一个黄钻，请不要在使用！</font>
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
		<div id="addtableDIV" title="卖号/买号增加">
			<s:form action="userInfoManager/info!sellerAndBuyer.php"
				theme="simple" id="addForm">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr>
						<td valign="middle">
							<font color="red">*</font>类型：
						</td>
						<td valign="middle">
							<span> <input type="radio" value="1" name="type"
									checked="checked"> 卖号 <input type="radio" value="2"
									name="type"> 买号 </span>
						</td>
					</tr>
					<tr>
						<td valign="middle">
							<font color="red">*</font>平台：
						</td>
						<td valign="middle">
							<select id="platformType" name="platformTypeParam">
								<option value="1">
									淘宝
								</option>
								<option value="2">
									拍拍
								</option>
								<option value="3">
									有啊
								</option>
							</select>
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle">
							<font color="red">*</font>店铺地址：
						</td>
						<td valign="middle">
							<input type="text" name="userVO.seller.shopURL" id="shopURL"
								onfocus="beforeBlur(this)">
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle">
							<font color="red">*</font>掌柜名字：
						</td>
						<td valign="middle">
							<input type="text" id="sellerName" name="userVO.seller.name"
								readonly="readonly">
							<span id="huoquUser" style="display: none"></span>
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle">
							&nbsp;发货地址：
						</td>
						<td valign="middle">
							<input type="text" name="sheng" style="width: 80px">
							省
							<input type="text" name="shi" style="width: 80px">
							市
							<input type="text" name="qu" style="width: 80px">
							区
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle">
							&nbsp;邮编：
						</td>
						<td valign="middle">
							<input type="text" name="youbian" id="y1" maxlength="6"
								style="width: 80px">
						</td>
					</tr>
					<tr style="display: none" class="buyerClass">
						<td valign="middle">
							<font color="red">*</font>买号：
						</td>
						<td valign="middle">
							<input type="text" id="buyerName" onblur="obtainBuyer(this)"
								name="userVO.buyer.name">
						</td>
					</tr>
					<tr style="display: none" class="buyerClass">
						<td valign="middle" nowrap="nowrap">
							&nbsp;信誉地址：
						</td>
						<td valign="middle">
							<input type="text" id="creditURL" name="userVO.buyer.creditURL">
							<font color="red">(仅支持淘宝,拍拍，有问题联系客户)</font>
						</td>
					</tr>
					<tr>
						<td valign="middle" colspan="2">
							<input type="submit" value="增加">
						</td>
					</tr>
				</table>
			</s:form>
		</div>


		<!-- 
			修改发货地址 xgj
		 -->
		<div id="updateDIV" title="修改发货地址">
			<s:form action="userInfoManager/info!updateSeller.php" theme="simple"
				id="updatewForm">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle">
							发货地址：
						</td>
						<td valign="middle">
							<input type="text" name="sheng" style="width: 80px">
							省
							<input type="text" name="shi" style="width: 80px">
							市
							<input type="text" name="qu" style="width: 80px">
							区
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle">
							邮编：
						</td>
						<td valign="middle">
							<input type="hidden" id="upadteSellerId" name="upadteSeller">
							<input type="text" name="youbian" id="y2" maxlength="6"
								style="width: 80px">
						</td>
					</tr>
					<tr>
						<td valign="middle" colspan="2">
							<input type="submit" value="修改	">
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
