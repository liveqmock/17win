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

		<LINK href="css/blue/style.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery.tablesorter.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/withdrawalsLog.js" type="text/javascript"></SCRIPT>

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
											您现在的位置是：个人中心 &gt;&gt; 提现列表 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>提现列表</strong>
										</div>
										<br>
										<table width="100%" cellpadding="1" cellspacing="1"
											border="0px" style="background: #DDEDFA">
											<tr>
												<td width="100%">
													提现类型：
													<select>
														<option value="">
															--请选择--
														</option>
														<option value="1">
															店铺地址提现
														</option>
														<option value="2">
															支付宝提现
														</option>
														<option value="3">
															财付通提现
														</option>
													</select>
													<span> &nbsp;&nbsp;&nbsp;&nbsp; 状态： <select>
															<option value="">
																--请选择--
															</option>
															<option value="0">
																申请中
															</option>
															<option value="1">
																完成
															</option>
															<option value="2">
																被驳回
															</option>
														</select> </span> &nbsp;&nbsp;&nbsp;&nbsp; 提现金额：
													<input type="text" style="width: 40px" />
													至
													<input type="text" style="width: 40px" />
												</td>
											</tr>
											<tr>
												<td width="100%">
													操作日期：
													<input type="text" />
													至
													<input type="text" />
													&nbsp;&nbsp;&nbsp;&nbsp; 状态：
													<select>
														<option value="">
															--请选择--
														</option>
														<option value="0">
															申请中
														</option>
														<option value="1">
															完成
														</option>
														<option value="2">
															被驳回
														</option>
													</select>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="submit" value="查&nbsp;&nbsp;询"
														style="cursor: pointer;">
												</td>
											</tr>
										</table>
										<br>
										<table id="myTable" class="tablesorter" cellpadding="1">
											<thead>
												<tr>
													<th>
														提现类型
													</th>
													<th>
														平台类型
													</th>
													<th>
														提现金额
													</th>
													<th>
														操作日期
													</th>
													<th>
														状态
													</th>
													<th>
														描述
													</th>
													<th>
														操作
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
