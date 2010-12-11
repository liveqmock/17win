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
		<LINK href="css/vip.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="js/utils.js"></script>
		<script type="text/javascript" src="user/vip.js"></script>
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
											您现在的位置是：个人中心 &gt;&gt; 加入VIP &gt;&gt;
										</div>
										<div class="pp8">
											<strong>加入VIP</strong>
										</div>
										<br>
										<div>
											<s:form theme="simple" action="vipManager/vip!buyVip.php">
												<table style="background: white;" cellpadding="1px"
													width="99%" cellspacing="1px">
													<tr style="background: #EDF6FF;">
														<td colspan="2">
															<s:if test="#session.userLogin.vipEnable">
																<img
																	src="images/vip/<s:property value="@net.win.utils.StrategyUtils@getVipImg(#session.userLogin.vipType,#session.userLogin.vipEnable)" />" />
															到期时间：<s:date name="#session.userLogin.vipEndDate"
																	format="yyyy-MM-dd HH-mm-ss" />
															当前成长值：<s:property value="#session.userLogin.vipGrowValue" />
															</s:if>
															<s:else>
														您还没有加入VIP
														</s:else>
														</td>
													</tr>
													<tr style="background: #EDF6FF;">
														<td align="right">
															购&nbsp;&nbsp;买:
														</td>
														<td>
															<input style="width: 80px" id="monthCount" name="monthC">
															月/
															<s:property value="#request.vipPrice" />
															元(
															<font color="red">注意:输入12个月为年付，打<s:property
																	value="#request.vipYearRebate" />折</font>)
														</td>
													</tr>
													<tr style="background: #EDF6FF;">
														<td align="right">
															操作码:
														</td>
														<td>
															<input style="width: 80px" type="password" maxlength="20"
																id="operationCodeId" name="operationCode">
														</td>
													</tr>
													<tr style="background: #EDF6FF;" align="center">
														<td colspan="2">
															<s:if test="#session.userLogin.vipType!=null">
																<input type="button" id="renewalVipButton"
																	style="cursor: pointer;" value="续费">
															</s:if>
															<s:else>
																<input type="button" id="buyVipButton"
																	style="cursor: pointer;" value="购买">
															</s:else>
														</td>
													</tr>
												</table>
											</s:form>
										</div>
										<br>
										<!-- 我的会员成长类会员功能特权 开始-->
										<div id="table_grow_box" class="full_box">
											<h3 class="title">
												<span class="redarrow">我的会员成长类会员功能特权</span>
											</h3>
											<div class="content">
												<table id="priv_table" class="my_table" cellpadding="0px"
													cellspacing="0px">
													<caption>
														成长类会员功能特权
													</caption>
													<tbody>
														<tr>
															<th>
																功能名称
															</th>
															<th id="vip_0" class="self">
																非会员
															</th>
															<th id="vip_1">
																VIP1特权
															</th>
															<th id="vip_2">
																VIP2特权
															</th>
															<th id="vip_3">
																VIP3特权
															</th>
														</tr>
														<tr>
															<td>
																会员任务每日成长数
															</td>
															<td id="ring_0" class="self">
																不可用
															</td>
															<td id="ring_1">
																接1个任务2点成长值
																<br>
																发1个任务1点成长值
															</td>
															<td id="ring_2">
																接1个任务3点成长值
																<br>
																发1个任务2点成长值
															</td>
															<td id="ring_3">
																接1个任务4点成长值
																<br>
																发1个任务3点成长值
															</td>
														</tr>
														<tr class="double">
															<td>
																会员成长值
															</td>
															<td id="file_0" class="self">
																不可用
															</td>
															<td id="file_1">
																0
															</td>
															<td id="file_2">
																1800
															</td>
															<td id="file_3">
																5400
															</td>
														</tr>
														<tr>
															<td>
																手机短信提醒对方
															</td>
															<td id="group_0" class="self">
																不可用
															</td>
															<td id="group_1">
																5条/月
															</td>
															<td id="group_2">
																10条/月
															</td>
															<td id="group_3">
																20条/月
															</td>
														</tr>
														<tr class="double">
															<td>
																任务仓库批量发布任务
															</td>
															<td id="mail_0" class="self">
																不可用
															</td>
															<td id="mail_1">
																可用
															</td>
															<td id="mail_2">
																可用
															</td>
															<td id="disk_3">
																可用
															</td>
														</tr>
														<tr>
															<td>
																会员功能任务排前且高亮显示
															</td>
															<td id="move_0" class="self">
																不可用
															</td>
															<td id="move_1">
																可用
																<br>
																顺序低于VIP2
															</td>
															<td id="move_2">
																可用
																<br>
																顺序低于VIP3
															</td>
															<td id="move_3">
																可用
															</td>
														</tr>
														<tr class="double">
															<td>
																卖号个数上线人数
															</td>
															<td id="chat_0" class="self">
																2个
															</td>
															<td id="chat_1">
																3个
															</td>
															<td id="chat_2">
																4个
															</td>
															<td id="chat_3">
																不受限制
															</td>
														</tr>
														<tr>
															<td>
																接任务获得的发布点
															</td>
															<td id="chat_0" class="self">
																任务总发布点×
																<s:property value="#request.receieveTaskDotRate" />
																<br>
																<font color="red">(一心以上用户)</font>
															</td>
															<td id="chat_1">
																任务总发布点的
															</td>
															<td id="chat_2">
																任务总发布点的
															</td>
															<td id="chat_3">
																任务总发布点的
															</td>
														</tr>
														<tr class="double">
															<td>
																积分获取
															</td>
															<td id="chat_0" class="self">
																接任务2分
																<br>
																发任务1分
															</td>
															<td id="chat_1">
																接任务3分
																<br>
																发任务2分
															</td>
															<td id="chat_2">
																接任务4分
																<br>
																发任务3分
															</td>
															<td id="chat_3">
																接任务5分
																<br>
																发任务4分
															</td>
														</tr>
														<tr>
															<td>
																每天登录送积分
															</td>
															<td id="chat_0" class="self">
																5分
															</td>
															<td id="chat_1">
																10分
															</td>
															<td id="chat_2">
																20分
															</td>
															<td id="chat_3">
																30分
															</td>
														</tr>
														<tr class="double">
															<td>
																每天登录送成长值
															</td>
															<td id="chat_0" class="self">
																不可用
															</td>
															<td id="chat_1">
																5点
															</td>
															<td id="chat_2">
																10点
															</td>
															<td id="chat_3">
																15点
															</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div class="bottom"></div>
										</div>
										<!-- 我的会员成长类会员功能特权 结束-->
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
