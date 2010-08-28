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
											您现在的位置是：淘宝刷信誉 &gt;&gt; 会员中心 &gt;&gt; 操作记录 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>*<a href="Record.asp">所有操作记录列表</a> *<a
												href="Record.asp?action=ren">任务操作列表</a> * <a
												href="Record.asp?action=chong">充值列表</a> * <a
												href="Record.asp?action=chongmanage">后台充值列表</a> * <a
												href="Record.asp?action=zeng">增值操作列表</a> * <a
												href="Record.asp?action=ti">提现操作列表</a> * <a
												href="Record.asp?action=manage">管理操作列表</a> </strong>
										</div>

									</div>
									<table width="98%" cellspacing="0" cellpadding="0" border="0"
										bgcolor="#ffffff" align="center">

										<tbody>
											<tr>
												<td width="125" height="26" bgcolor="#fffcd2"
													background="images/luntan03.gif" align="center"
													class="red-bcolor border-bot">
													流水号
												</td>
												<td width="142" bgcolor="#fffcd2"
													background="images/luntan03.gif" align="center"
													class="red-bcolor  border-bot">
													类型
												</td>
												<td width="143" bgcolor="#fffcd2"
													background="images/luntan03.gif" align="center"
													class="red-bcolor  border-bot">
													详细
												</td>
												<td width="80" bgcolor="#fffcd2"
													background="images/luntan03.gif" align="center"
													class="red-bcolor  border-bot">
													金额
												</td>
												<td width="94" bgcolor="#fffcd2"
													background="images/luntan03.gif" align="center"
													class="red-bcolor  border-bot">
													结果
												</td>
												<td width="111" bgcolor="#fffcd2"
													background="images/luntan03.gif" align="center"
													class="border-bot red-bcolor ">
													时间
												</td>
											</tr>

											<tr>
												<td height="35" align="center" class="border-botdashed">
													2010828132913
												</td>
												<td align="center" class="border-botdashed">
													淘宝区任务
												</td>
												<td align="center" class="border-botdashed">
													xgj1988确认接手人meimeng的好评-任务ID:20108261143491983确认已好评对方收到了你的35元担保金和1.6个发布点
												</td>
												<td align="center" class="border-botdashed">
													&nbsp;0
												</td>
												<td align="center" class="border-botdashed">
													&nbsp; 确认已好评任务完成!
												</td>
												<td align="center" class="border-botdashed">
													2010-8-28 13:29:00
												</td>
											</tr>
											<tr>
												<td height="35" align="center" class="border-botdashed"
													colspan="6">
													共
													<font color="blue"><b>166</b> </font>
													条主题&nbsp;&nbsp;&nbsp;首页 上一页&nbsp;
													<a href="record.asp?PageNo=2">下一页</a>&nbsp;
													<a href="record.asp?PageNo=17">尾页</a>&nbsp;页次：
													<strong><font color="red">1</font>/17</strong>页 &nbsp;
													<b>10</b>条主题/页&nbsp;转到：
													<select
														onchange="javascript:window.location='record.asp?PageNo='+this.options[this.selectedIndex].value;"
														size="1" name="page">
														<option selected="" value="1">
															第1页
														</option>
														<option value="2">
															第2页
														</option>
														<option value="3">
															第3页
														</option>
														<option value="4">
															第4页
														</option>
														<option value="5">
															第5页
														</option>
														<option value="6">
															第6页
														</option>
														<option value="7">
															第7页
														</option>
														<option value="8">
															第8页
														</option>
														<option value="9">
															第9页
														</option>
														<option value="10">
															第10页
														</option>
														<option value="11">
															第11页
														</option>
														<option value="12">
															第12页
														</option>
														<option value="13">
															第13页
														</option>
														<option value="14">
															第14页
														</option>
														<option value="15">
															第15页
														</option>
														<option value="16">
															第16页
														</option>
														<option value="17">
															第17页
														</option>
													</select>
												</td>
											</tr>
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
