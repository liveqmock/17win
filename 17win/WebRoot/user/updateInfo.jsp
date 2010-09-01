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
		<SCRIPT src="js/aop.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/ajax.js" type=text/javascript></SCRIPT>
		<SCRIPT src="user/updateInfo.js" type=text/javascript></SCRIPT>
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
		<s:form action="userInfoManager/info!updateInfo.php"
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
												您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 修改资料 &gt;&gt;
											</div>
											<div class="pp8">
												<strong>修改资料</strong>
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
														<td width="146" height="40" align="right" class="font12h">
															用户名：
														</td>
														<td width="230">
															<s:textfield name="userVO.userEntity.username" size="30"
																disabled="true" cssStyle="width:210px" id="username"
																maxlength="12"></s:textfield>
														</td>
														<td width="524"></td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															Q&nbsp;Q:
														</td>
														<td>
															<s:textfield name="userVO.userEntity.qq" id="qq"
																size="30" disabled="true" cssStyle="width:210px"></s:textfield>
														</td>
														<td>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															电子邮箱：
														</td>
														<td>
															<s:textfield id="email" name="userVO.userEntity.email"
																disabled="true" size="30" cssStyle="width:210px"></s:textfield>
														</td>
														<td>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															手机号码：
														</td>
														<td align="left" class="red-bcolor">
															<s:textfield id="telephone"
																name="userVO.userEntity.telephone" size="30"
																disabled="true" cssStyle="width:210px"></s:textfield>
														</td>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="40" align="left" class="font12h" colspan="3">
															<table width="100%" cellspacing="0" cellpadding="0"
																border="0" align="left" id="showinfo_c1">
																<tbody>
																	<tr>
																		<td width="16%" height="40" align="right"
																			class="font14b2">
																			选填项目：
																		</td>
																		<td colspan="2">
																			<hr style="color: rgb(255, 153, 51);">
																		</td>
																	</tr>
																	<tr>

																		<td height="40" align="right" class="font12h"
																			valign="top">
																			旺旺：
																		</td>

																		<td>
																			<s:textfield id="ww" name="userVO.userEntity.ww"
																				size="30" cssStyle="width:210px"></s:textfield>
																			<br />
																			<font color="#FF0000">注意：非必须，不要用旺旺发送任何关于刷信誉的内容</font>
																		</td>

																		<td valign="top">

																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h"
																			nowrap="nowrap" valign="top">
																			淘宝店铺地址：
																		</td>
																		<td align="left">
																			<s:textfield id="taobaoShopURL"
																				name="userVO.userEntity.taobaoUser.shopURL"
																				size="30" cssStyle="width:300px"></s:textfield>
																			<br />
																			您的淘宝店铺:
																			<font color="#FF0000">如:http://xxxxx.taobao.com</font>
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>

																		<td height="40" align="right" class="font12h"
																			valign="top">
																			淘宝账号：
																		</td>
																		<td align="left">
																			<s:textfield id="taobaoSeller" readonly="true"
																				name="userVO.userEntity.taobaoUser.seller" size="30"
																				cssStyle="width:210px"></s:textfield>
																			<br />
																			<font color="#FF0000"></font>系统自动获取
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h"
																			valign="top">
																			淘宝小号账号：
																		</td>
																		<td align="left">
																			<s:textfield id="taobaoBuyer"
																				name="userVO.userEntity.taobaoUser.buyer" size="30"
																				cssStyle="width:210px"></s:textfield>
																			<br />

																			<font color="#FF0000"></font>购买别人物品的淘宝账号，该必须是黄钻级别以下（如被查出立即封号）
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h"
																			valign="top">
																			拍拍店铺地址：
																		</td>

																		<td align="left">
																			<s:textfield id="paipaiShopURL"
																				name="userVO.userEntity.paipaiUser.shopURL"
																				size="30" cssStyle="width:300px"></s:textfield>
																			<br />
																			<font color="#FF0000"></font>您的拍拍店铺
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>

																		<td height="40" align="right" class="font12h"
																			valign="top">
																			拍拍账号：
																		</td>
																		<td align="left">
																			<s:textfield id="paipaiSeller"
																				name="userVO.userEntity.paipaiUser.seller" size="30"
																				cssStyle="width:210px"></s:textfield>
																			<br />
																			<font color="#FF0000"></font>拍拍账号
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h"
																			valign="top">
																			拍拍小号账号：
																		</td>
																		<td align="left">
																			<s:textfield id="paopaoBuyer"
																				name="userVO.userEntity.paipaiUser.buyer" size="30"
																				cssStyle="width:210px"></s:textfield>
																			<br />

																			<font color="#FF0000"></font>购买别人物品的拍拍账号，该必须是黄钻级别以下（如被查出立即封号）
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h"
																			valign="top">
																			有啊店铺地址：
																		</td>

																		<td align="left">
																			<s:textfield id="youaShopURL"
																				name="userVO.userEntity.youaUser.shopURL" size="30"
																				cssStyle="width:300px"></s:textfield>
																			<br />
																			<font color="#FF0000"></font>您的有啊店铺
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>

																		<td height="40" align="right" class="font12h"
																			valign="top">
																			有啊账号：
																		</td>
																		<td align="left">
																			<s:textfield id="youaSeller"
																				name="userVO.userEntity.youaUser.seller" size="30"
																				cssStyle="width:210px"></s:textfield>
																			<br />
																			<font color="#FF0000"></font>拍拍账号
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																	<tr>
																		<td height="40" align="right" class="font12h"
																			valign="top">
																			有啊小号账号：
																		</td>
																		<td align="left">
																			<s:textfield id="youabuter"
																				name="userVO.userEntity.youaUser.buyer" size="30"
																				cssStyle="width:210px"></s:textfield>

																			<br />

																			购买别人物品的有啊账号，该必须是黄钻级别以下（如被查出立即封号）
																		</td>
																		<td valign="top">

																		</td>
																	</tr>



																	<tr>
																		<td height="40" align="right" class="font12h">

																			选择发货地：
																		</td>
																		<td align="left" nowrap="nowrap">

																			<s:select id="provinceID"
																				name="userVO.userEntity.province.id"
																				list="userVO.provinces" listKey="id"
																				listValue="name" headerKey="" headerValue="--请选择--">
																			</s:select>
																			省
																			<s:select id="cityID"
																				name="userVO.userEntity.city.id"
																				list="userVO.cities" listKey="id" listValue="name"
																				headerKey="" headerValue="请选择">
																			</s:select>
																			市
																			<s:select id="areaID"
																				name="userVO.userEntity.area.id" list="userVO.areas"
																				listKey="id" listValue="name" headerKey=""
																				headerValue="请选择">
																			</s:select>
																			县/区&nbsp;&nbsp;
																			<br />
																			系统会根据此信息，自动帮你生成发货地址
																		</td>
																		<td valign="top">

																		</td>
																	</tr>
																</tbody>
															</table>
														</td>
													</tr>
													<tr>
														<td height="40" align="right" class="font12h">
															&nbsp;
														</td>
														<td align="center" class="red-bcolor">
															<input type="submit" value="修  改" id="button"
																style="cursor: pointer;" name="button">
														</td>
														<td>
															&nbsp;
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
		<s:property value="#request.msg" escape="false"/>
	</BODY>
</HTML>
