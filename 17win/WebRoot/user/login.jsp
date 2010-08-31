<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/login.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />

		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="user/login.js" type="text/javascript"></SCRIPT>
	</HEAD>
	<BODY>
		<s:form action="userManager/base!login.php" theme="simple"
			onsubmit="return validateForm()">
			<s:include value="../common/title.jsp"></s:include>
			<TABLE cellSpacing=0 cellPadding=0 width=960 align=center border=0>
				<TBODY>
					<TR>
						<TD width=20 height=32>
							<IMG src="images/Top_10.gif">
						</TD>
						<TD align=right width=21 background=images/Top_11.gif>
							<IMG height=32 src="images/Top_9.gif" width=10>
						</TD>
						<TD class=K_mttitle align="center" width=120
							background=images/Top_12.gif>
							会员登陆
						</TD>
						<TD align=left width=47 background=images/Top_11.gif>
							<IMG height=32 src="images/Top_13.gif" width=10>
						</TD>
						<TD align=left width=728 background=images/Top_11.gif></TD>
						<TD align=right width=24 background=images/Top_11.gif>
							<IMG height=32 src="images/Top_14.gif" width=6>
						</TD>
					</TR>
					<TR>

						<TD class=K_mtcontent align=left colSpan=6>
							<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
								<TBODY>
									<TR>
										<TD align="center" width="48%" height=350>
											<table width="90%" border="0" align="right" cellpadding="8"
												cellspacing="0">
												<tr>

													<td colspan="2" bgcolor="#FFFFFF">
														<div align="left">
															<img src="images/login.jpg" width="270" height="57">
														</div>
													</td>
												</tr>
												<tr>
													<td width="25%" bgcolor="#FFFFFF">
														会员账号：
													</td>
													<td width="75%" bgcolor="#FFFFFF">
														<div align="left">
															<tt> <s:textfield name="userVO.userEntity.username"
																	size="30" cssStyle="width:180px" id="username"
																	maxlength="12"></s:textfield> </tt>
														</div>
													</td>
												</tr>

												<tr>
													<td bgcolor="#FFFFFF">
														登录密码：
													</td>
													<td bgcolor="#FFFFFF">
														<div align="left">
															<tt> <s:password id="password" maxlength="20"
																	name="userVO.userEntity.loginPassword" size="30"
																	cssStyle="width:180px"></s:password> </tt>
														</div>
													</td>
												</tr>
												<tr>
													<td bgcolor="#FFFFFF">
														验证码：
													</td>

													<td bgcolor="#FFFFFF">
														<div align="left">
															<tt> <s:textfield id="ww"
																	name="userVO.verificationCode" maxlength="4"
																	id="verificationCode" size="30" cssStyle="width:60px"></s:textfield>
																<img src="verify/verificationCode.php"
																	onclick="changeValidateCode(this)" title="点击图片刷新验证码"
																	style="cursor: pointer;" /> </tt>
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2" bgcolor="#FFFFFF">
														<label>
															<a id="findPWA" href="javascript:void(0);"><img
																	src="images/forgetpw.gif" width="12" height="13"
																	border="0">&nbsp;找回密码</a>&nbsp;
															<a href="userManager/base!initRegister.php">&nbsp;&nbsp;&nbsp;<img
																	style="cursor: pointer;" src="images/register.gif"
																	width="14" height="13" border="0">&nbsp;快速注册</a>
														</label>
													</td>
												</tr>

												<tr>
													<td colspan="2" align="center" bgcolor="#FFFFFF">
														<INPUT
															style="FONT-WEIGHT: bold; WIDTH: 120px; CURSOR: pointer; COLOR: #000000; HEIGHT: 26px"
															type=submit value="登陆服务平台  "
															name=ctl00$AllBaseContentPlaceHolder$ctl00>
														&nbsp; &nbsp;
													</td>
												</tr>
											</table>
										</TD>
										<TD width="1%">

											<DIV
												style="WIDTH: 2px; HEIGHT: 230px; BACKGROUND-COLOR: #3da3e7"></DIV>
										</TD>
										<TD class=riglogin width="51%">
											<B>平台会员同时享受精彩服务</B>
											<BR>
											<IMG src="images/jieducm_b0.gif">
											<TT> 第一步：<b><A
													href="userManager/base!initRegister.php">注册</A> </b>为本站会员，登陆系统。</TT>
											<BR>
											<IMG src="images/jieducm_b1.gif">
											<TT>第二步：到刷钻中心接任务,接一个任务系统会自动为你增加发布点。</TT>
											<BR>
											<IMG src="images/jieducm_b2.gif">
											<TT>&nbsp;第三步：对你刚才购买的宝贝进行"好评"</TT>
											<BR>
											<IMG src="images/jieducm_b3.gif">
											<TT>第四步：拥有发布点后即可发布任务，平台担保价为用户自定。</TT>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<s:include value="../common/footKuan.jsp"></s:include>
		</s:form>
		<div id="findPW" title="找回密码">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						输入用户名:
					</td>
					<td>
						<input type="text" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="button" id="findPWBtn" value="提交" />
					</td>
					<td>
					</td>

				</tr>
			</table>
		</div>
		<s:if test="#request.div!=null">
			<div id="activateDIV" title="激活账号">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>
							手机号码:15112355607
						</td>
						<td>
							<input type="button" id="actioveBtn" value="获取激活码" />
						</td>
					</tr>

				</table>
			</div>
		</s:if>
		<s:property value="#request.msg" escape="false" />
	</BODY>
</HTML>
