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

		<LINK href="css/blue/style.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery.tablesorter.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/mySpread.js" type="text/javascript"></SCRIPT>

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
								<div class="pp7">
									您现在的位置是：个人中心 &gt;&gt; 我的推广 &gt;&gt;
								</div>
								<div
									style="clear: both; width: 730px; background-color: rgb(243, 248, 254);">
									<div style="clear: both; margin-top: 20px; line-height: 150%;">
										<font color="red">推荐我的用户是：<b><s:property  value="#request.referee"/></b></font>
									</div>
								</div>
								<div
									style="clear: both; width: 730px; background-color: rgb(243, 248, 254);">


									<div style="clear: both; margin-top: 20px; line-height: 150%;">
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<td>
														<table cellspacing="0" cellpadding="0" border="0"
															align="left">
															<tbody>
																<tr>
																	<td width="1">
																		<img width="3" height="25"
																			src="images/mytaobaobj1_3.gif">

																	</td>
																	<td width="120" background="images/mytaobaobj1_4.gif"
																		align="middle">
																		<font color="#ffffff"><span>我推荐的用户</span> </font>
																	</td>
																	<td width="1">
																		<img width="3" height="25"
																			src="images/mytaobaobj1_6.gif">
																	</td>
																</tr>
															</tbody>
														</table>
														<table cellspacing="0" cellpadding="0" border="0"
															align="left">
															<tbody>
																<tr>
																	<td>

																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
											</tbody>
										</table>
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<td height="4" bgcolor="#1e88c1"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div>
									<table id="myTable" class="tablesorter" cellpadding="1">
										<thead>
											<tr>
												<th>
													用户名
												</th>
												<th>
													联系方式
												</th>
												<th>
													发任务数
												</th>
												<th>
													接任务数
												</th>
												<th>
													注册时间
												</th>
												<th>
													现在积分
												</th>
												<th>
													现在发布点
												</th>
											</tr>
										</thead>
										<tbody>
											<s:if test="#request.myRefees.size()>0">
												<tfoot>
													<tr>
														<th colspan="8">
															首页&nbsp;&nbsp;上一页&nbsp;&nbsp;下一页&nbsp;&nbsp;末页
														</th>
													</tr>
												</tfoot>
												<s:iterator value="#request.myRefees" id="user">
													<tr>
														<td>
															<s:property value="user.username" />
														</td>
														<td>
															<a
																href="tencent://message/?uin=<s:property value="user.qq" />"><img
																	border="0"
																	src="http://wpa.qq.com/pa?p=1:<s:property value="user.qq" />:4">
															</a>
															<s:property value="user.qq" />
														</td>
														<td>
															<s:property value="user.releaseTaskCount" />
														</td>
														<td>
															<s:property value="user.receiveTaskCount" />
														</td>
														<td>
															<s:property value="user.registerTime" />
														</td>
														<td>
															<s:property value="user.convertScore" />
														</td>
														<td>
															<s:property value="user.releaseDot" />
														</td>
													</tr>
												</s:iterator>
											</s:if>
											<s:else>
												<tfoot>
													<tr>
														<th colspan="8">
															<A href="userInfoManager/info!referee.php"
																target="_blank">您当前没有推广:现在马上就去推广吧!</A>
														</th>
													</tr>
												</tfoot>
											</s:else>
										</tbody>
									</table>
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
