<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">

		<LINK href="css/center.css" type="text/css" rel="stylesheet">

		<script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
		<script src="user/appeal.js" type="text/javascript"></script>

		<script type="text/javascript">
		</script>
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
											您现在的位置是：个人中心 &gt;&gt;我要申诉 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>我要申诉</strong>
										</div>
										<s:form theme="simple">
											<table>
												<tbody>
													<tr>
														<td height="40" align="right">
															申诉标题：
														</td>
														<td width="490">
															<input type="text">
														</td>
													</tr>
													<tr>
														<td height="40" align="right">
															申述类型：
														</td>
														<td width="490">
															<select>
																<option>
																	信誉互刷
																</option>
																<option>
																	流量互刷
																</option>
																<option>
																	收藏互刷
																</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="40" align="right">
															被申诉人：
														</td>
														<td>
															<input type="text" value="" size="25" id="name"
																name="name">
														</td>
													</tr>
													<tr>
														<td height="40" align="right">
															任&nbsp;务&nbsp;ID：
														</td>
														<td>
															<input type="text" value="" size="25" id="pid" name="pid">
														</td>
													</tr>
													<tr>
														<td height="40" align="right">
															上传凭证：
														</td>
														<td>
															<input type="text" size="25" id="pic" name="pic">
															<input type="button"
																onclick="window.open('../upload_flash.asp?formname=formf&amp;editname=pic&amp;uppath=uploadpic&amp;filelx=jpg','','status=no,scrollbars=no,top=20,left=110,width=420,height=165')"
																value="上传图片" name="Submit">
														</td>
													</tr>
													<tr>
														<td height="40" align="right" valign="top">
															详细描述：
														</td>
														<td>
															<textarea name="editor"></textarea>
														</td>
													</tr>
													<tr>
														<td height="70">
															&nbsp;
														</td>
														<td valign="middle">
															&nbsp;&nbsp;&nbsp;

															<input type="submit" value="提交"
																style="height: 30px; width: 50px;" id="button"
																name="button">

															<input type="reset" value="重置"
																style="height: 30px; width: 50px;" name="Submit">
														</td>
													</tr>
												</tbody>
											</table>
										</s:form>
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
