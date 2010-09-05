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
											您现在的位置是：个人中心 &gt;&gt;官方黑名单 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>官方黑名单</strong>
										</div>
										<div
											style="margin-left: 15px; clear: both; border: 1px solid rgb(171, 190, 200); margin-top: 10px; margin-bottom: 10px; width: 700px; background-color: rgb(243, 248, 254);">
											<div
												style="clear: both; margin-top: 10px; margin-left: 5px; line-height: 200%; text-align: left;">
												<ul>
													<li
														style="list-style-position: inside; list-style-image: url(&quot;../images/list_bg.gif&quot;); border-bottom: 1px dashed rgb(153, 153, 153);">
														1372172092(已有1人列其为黑名单)
													</li>
												</ul>
											</div>
										</div>

										<div
											style="margin-left: 7px; border: 1px solid rgb(171, 190, 200); margin-top: 20px; float: left; width: 320px; line-height: 150%; height: 260px; text-align: center;">
											<div
												style="clear: both; margin-top: 10px; margin-left: 5px; line-height: 200%; text-align: left;">
												<ul>


													暂无信息
													<table width="100%" cellspacing="0" cellpadding="0"
														border="0">
														<tbody>
														</tbody>
													</table>

												</ul>
											</div>
										</div>



										<div
											style="border: 1px solid rgb(171, 190, 200); margin-top: 20px; float: right; width: 350px; line-height: 150%; height: 260px;">
											<div style="width: 320px; line-height: 50px; height: 50px;">
												你要将谁拉入黑名单(对方用户名)：
											</div>
											<form method="post" action="" name="aspnetForm"
												id="aspnetForm">
												<div style="width: 320px; line-height: 80px; height: 80px;">
													<input name="name" id="name">
													<input type="hidden" value="ok" name="action">
													<input type="submit" name="button1" value="加入我的黑名单"
														id="button1">
												</div>
											</form>
											<div
												style="width: 320px; color: red; line-height: 50px; height: 60px;"></div>
										</div>
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
