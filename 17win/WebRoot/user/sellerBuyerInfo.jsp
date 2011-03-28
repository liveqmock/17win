<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
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
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">
		<LINK href="css/center.css" type="text/css" rel="stylesheet">

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
												class="classButton" id="addBtn">
										</div>
										<s:iterator value="#request.sellers.keys" id="type">
											<table width="100%" cellspacing="0" cellpadding="0"
												style="table-layout: fixed" border="0" align="center">
												<tbody id="sellerTable<s:property value="#type"/>">
													<tr>
														<td colspan="2" height="40" align="left" nowrap="nowrap">
															<s:set name="platformName" value="#type==1?'淘宝':'拍拍'"></s:set>
															<s:property value="#platformName" />
															卖号资料：
														</td>
													</tr>
													<tr style="background: #EDF6FF">
														<th height="10" nowrap="nowrap" align="center">
															卖号
														</th>
														<th height="10" nowrap="nowrap" align="center">
															是否加入消保
														</th>
														<th height="10" nowrap="nowrap" align="center">
															是否旺铺
														</th>
														<th height="10" nowrap="nowrap" align="center">
															信誉值
														</th>
														<th height="10" nowrap="nowrap" align="center">
															信誉级别
														</th>
														<th height="10" nowrap="nowrap" align="center">
															操作
														</th>
													</tr>
													<s:iterator value="#request.sellers.get(#type)" id="seller">
														<tr class="sellerTr"
															alt="
														<table>
															<tr>
																<Td>
																	发货地址：<s:property value="#seller.address" />
																<td>
															</tr>
														</table>
																												">
															<td align="center" nowrap="nowrap"
																style="overflow: hidden; text-overflow: ellipsis;"
																width="100%">
																<a href="<s:property value="#seller.shopURL" />"
																	target="_blank"> <s:property value="#seller.name" />
																</a>
															</td>
															<td align='center'>
																<s:property value="#seller.ensure?'是':'否'" />
															</td>
															<td align="center">
																<s:property value="#seller.winport?'是':'否'" />
															</td>
															<td align="center">
																<s:property value="#seller.score" />
															</td>
															<td align="center" nowrap="nowrap">
																<s:if test="#seller.img==null || #seller.img==''">
																	你当前的信誉值为0
																</s:if>
																<s:else>
																	<a href="<s:property value="#seller.creditURL" />"
																		target="_blank"> <img
																			src="<s:property value="#seller.img" />" /> </a>
																</s:else>
															</td>
															<td align="center" nowrap="nowrap" title="修改发货地址">
																<a
																	href="javascript:showUpdateDIV('<s:property value="#seller.id" />')">
																	<img src="images/edit_ico.gif" /> </a>
															</td>
														</tr>
													</s:iterator>
												</tbody>
											</table>
										</s:iterator>
										<hr>
										<s:iterator value="#request.buyers.keys" id="type">
											<s:set name="platformName" value="#type==1?'淘宝':'拍拍''"></s:set>
											<table cellspacing="0" cellpadding="0" border="0"
												width="100%" style="table-layout: fixed" align="center">
												<thead id="buyerTable<s:property value="#type"/>">
													<tr>
														<td colspan="4" height="40" align="left">
															<s:set name="platformName" value="#type==1?'淘宝':'拍拍'"></s:set>
															<s:property value="#platformName" />
															买号资料：
														</td>
													</tr>
													<tr style="background: #EDF6FF">
														<th height="10" nowrap="nowrap" align="center">
															买号
														</th>
														<th height="10" nowrap="nowrap" align="center">
															信誉值
														</th>
														<th height="10" nowrap="nowrap" align="center">
															信誉级别
														</th>
														<th height="10" nowrap="nowrap" align="center">
															是否可用
														</th>
													</tr>
												</thead>
												<tbody>
													<s:iterator value="#request.buyers.get(#type)" id="buyer">
														<tr class="buyerTr">
															<td align="center" nowrap="nowrap">
																<s:property value="#buyer.name" />
															</td>
															<td align="center">
																<s:property value="#buyer.score" />
															</td>
															<td align="center">
																<s:if test="#buyer.img==null || #buyer.img==''">
																	你当前的信誉值为0
																</s:if>
																<s:else>
																	<a href="<s:property value="#buyer.creditURL" />"
																		target="_blank"> <img
																			src="<s:property value="#buyer.img" />" /> </a>
																</s:else>

															</td>
															<td align="center">
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
														<font color="red">注意：为了您和他人的安全，淘宝买号信誉在250以上，拍拍买号信誉在100以上的账号，我们将停用！</font>
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
		<div id="addtableDIV" title="卖号/买号增加" style="display: none">
			<s:form action="userInfoManager/info!sellerAndBuyer.php"
				onsubmit="return validateForm();" theme="simple" id="addForm">
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
							</select>
						</td>
					</tr>
					<!-- 
					<tr class="sellerClass">
						<td valign="middle">
							<font color="red">*</font>店铺地址：
						</td>
						<td valign="middle">
							<input type="text" name="userVO.seller.shopURL" id="shopURL"
								onfocus="beforeBlur(this)">
						</td>
					</tr>
					 -->
					<tr class="sellerClass">
						<td valign="middle">
							<font color="red">*</font>掌柜ID：
						</td>
						<td valign="middle">
							<input type="text" id="sellerName" name="userVO.seller.name">
							<span id="huoquUser" style="display: none"></span>
							<font color="red">(QQ为QQ号码)</font>
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
							<font color="red">(拍拍为QQ号码)</font>
						</td>
					</tr>
					<tr>
						<td valign="middle" colspan="2">
							<input type="submit" class="classButton" value="增加">
						</td>
					</tr>
				</table>
			</s:form>
		</div>


		<!-- 
			修改发货地址 xgj
		 -->
		<div id="updateDIV" title="修改发货地址" style="display: none">
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
							<input type="submit" class="classButton" value="修改	">
						</td>
					</tr>
				</table>
			</s:form>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>
