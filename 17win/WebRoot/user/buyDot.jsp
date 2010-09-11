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
		<SCRIPT src="user/buyDot.js" type="text/javascript"></SCRIPT>

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
											您现在的位置是：个人中心 &gt;&gt; 购买发布点 &gt;&gt;
										</div>
										<div class="pp8">
											<strong>购买发布点</strong>
										</div>
										<br>

										<div
											style="clear: both; width: 710px; background-color: rgb(243, 248, 254);">
											<div
												style="clear: both; padding-right: 30px; padding-left: 30px; height: 1280px;">

												<div>
												</div>
												<div
													style="clear: both; padding-left: 30px; background: url(&quot;/images/shangyi.gif&quot;) no-repeat scroll left 50% transparent; padding-bottom: 5px; color: rgb(0, 64, 0); padding-top: 7px; border-bottom: 1px dashed rgb(171, 190, 200);">
													<span style="color: red;">增值服务卡购买(全自动购买获得)
														注：想获得发布点，你也可以去接任务，不一定需要购买的！</span><span
														style="font-size: 20px; color: red;"
														id="ctl00_AllBaseContentPlaceHolder_MsgLabel"></span>
												</div>
												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; color: rgb(0, 64, 0); line-height: 70px; padding-top: 7px; border-bottom: 1px dashed rgb(171, 190, 200);">
													<div align="center">
														<a href="md5_pay.asp"> <img border="0"
																src="images/pay.gif"
																onmouseout="this.src='images/pay.gif'"
																onmouseover="this.src ='images/pay_link.gif'"> </a>
													</div>
												</div>
												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; padding-top: 15px;">
													<div style="float: left; width: 350px;">
														<img src="images/201081115373025060.gif">
													</div>
													<s:form action="userInfoManager/info!buyDot.php?flag=1"
														theme="simple">
														<div
															style="float: left; width: 200px; line-height: 150%; padding-top: 20px;">
															<span style="color: red;">拥有发布点，就可以发布自己的任务，获得好评。<br>想要轻轻松松立即到钻吗？请立即购买发布点！充多少送多少积分!</span>
															<br>
															<br>
															购买数量：
															<s:textfield name="userVO.releaseDot" id="releaseDot"
																value="10" cssStyle="width:40px"></s:textfield>
															个/每个0.5元
															<br>
															操作密码：
															<s:password name="userVO.operationCode"
																cssStyle="width:80px" id="operationCode_1"></s:password>
															<br>
															<input type="image" name="ImageButton1"
																src="images/buy1.png" onclick="return validateForm('1');"
																id="ImageButton1">
														</div>
													</s:form>
												</div>

												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; padding-top: 15px;">
													<div style="float: left; width: 350px;">
														<img src="images/201081115334451026.jpg">
													</div>
													<s:form action="userInfoManager/info!buyDot.php?flag=2"
														theme="simple">
														<div
															style="float: left; width: 200px; line-height: 150%; padding-top: 20px;">
															<span style="color: red;">刷出皇冠，10001个发布点
																至高荣誉，皇冠在手。</span>
															<br>
															<br>
															皇冠卡：5000元
															<br>
															操作密码：
															<s:password name="userVO.operationCode"
																cssStyle="width:80px" id="operationCode_2"></s:password>
															<br>
															<input type="image" name="ImageButton2"
																src="images/buy1.png"
																 onclick="return validateForm('2');"
																id="ImageButton2">
														</div>
													</s:form>
												</div>

												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; padding-top: 15px;">
													<div style="float: left; width: 350px;">
														<img src="images/20108111534180865.jpg">
													</div>
													<s:form action="userInfoManager/info!buyDot.php?flag=3"
														theme="simple">
														<div
															style="float: left; width: 200px; line-height: 150%; padding-top: 20px;">
															<span style="color: red;">刷出二钻，550个发布点，可获得550个好评
																一次投入立即进入双钻卖家的行列！</span>
															<br>
															<br>
															二钻卡：275元
															<br>
															操作密码：
															<s:password name="userVO.operationCode"
																cssStyle="width:80px" id="operationCode_3"></s:password>
															<br>
															<input type="image" name="ImageButton2"
																src="images/buy1.png"
															 onclick="return validateForm('3');"
																id="ImageButton2">
														</div>
													</s:form>
												</div>

												<div
													style="clear: both; padding-left: 30px; padding-bottom: 5px; padding-top: 15px;">
													<div style="float: left; width: 350px;">
														<img src="images/201081115344610581.jpg">
													</div>
													<s:form action="userInfoManager/info!buyDot.php?flag=4"
														theme="simple">
														<div
															style="float: left; width: 200px; line-height: 150%; padding-top: 20px;">
															<span style="color: red;">刷出一钻，270个发布点，可获得270个好评
																一次投入立即进入钻石卖家的行列！</span>
															<br>
															<br>
															一钻卡：135元
															<br>
															操作密码：
															<s:password name="userVO.operationCode"
																cssStyle="width:80px" id="operationCode_3"></s:password>
															<br>
															<input type="image" name="ImageButton3"
																src="images/buy1.png"
																 onclick="return validateForm('4');"
																id="ImageButton2">
														</div>
													</s:form>
												</div>


												<div></div>
											</div>
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

		<s:if test="#request.div!=null">
			<div id="showDIV"
				title="
		<s:if test="#request.executeFlag==success">
	操作成功
</s:if>
 <s:else>
 	操作失败
 </s:else>">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							<s:property value="#request.div" escape="false" />
						</td>
					</tr>
				</table>
			</div>
		</s:if>
	</BODY>
</HTML>
