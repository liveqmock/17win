<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; charset=UTF-8">
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
		<LINK rev=Stylesheet href="css/global.css" type=text/css
			rel=Stylesheet>
		<LINK href="css/login.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/aop.js" type=text/javascript></SCRIPT>
		<SCRIPT src="js/utils.js" type=text/javascript></SCRIPT>
		<SCRIPT src="user/register.js" type=text/javascript></SCRIPT>
		<STYLE type="text/css">
td {
	valign: top;
}
</STYLE>
	</HEAD>
	<BODY>
		<s:form action="userManager/base!register.php" theme="simple"
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
						<TD class=K_mttitle align=middle width=120
							background=images/Top_12.gif>
							会员注册
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
						<TD class="K_mtcontent" align=left colSpan=6>
							<table style="width: 900px" border="0" align="center"
								cellpadding="0" cellspacing="0">
								<tr>
									<td height="100" colspan="3">
										<span class="font14b2">新用户免费注册-轻松注册10秒钟搞定</span>
										<br />
										请填写以下信息，
										<font color="#FF0000">*</font>为必填内容
										<br />
										请认真仔细的填写以下信息，真实的个人信息有助于给你使用的服务带来更多的保障以及便捷！
									</td>
								</tr>
								<tr>
									<td align="right" height="40" class="font12h" width="300">
										用户名：
										<font color="#FF0000">*</font>&nbsp;
									</td>
									<td>
										<s:textfield name="userVO.userEntity.username" size="30"
											cssStyle="width:210px" id="username" maxlength="12"></s:textfield>
									</td>
									<td nowrap="nowrap" valign="top" width="400">
									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										登录密码：
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td>
										<s:password id="password" maxlength="20"
											name="userVO.userEntity.loginPassword" size="30"
											cssStyle="width:210px"></s:password>

									</td>
									<td valign="top">

									</td>
								</tr>
								<tr>

									<td height="40" align="right" class="font12h">
										确认登陆密码
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td>
										<input type="password" id="rePassword" size="30"
											maxlength="20" style="width: 210px">
									</td>
									<td valign="top">

									</td>
								</tr>

								<tr>
									<td height="40" align="right" class="font12h">
										操作密码：
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td>
										<s:password id="opertationCode" maxlength="20"
											name="userVO.userEntity.opertationCode" size="30"
											cssStyle="width:210px"></s:password>

									</td>
									<td valign="top">
									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										确认操作密码：
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td align="left" class="red-bcolor">
										<input type="password" id="reOperationCode" size="30"
											maxlength="20" style="width: 210px" />
									</td>
									<td valign="top">

									</td>

								</tr>

								<tr>
									<td height="40" align="right" class="font12h">
										Q&nbsp;Q：
										<font color="#FF0000">*&nbsp;</font>
									</td>

									<td>
										<s:textfield name="userVO.userEntity.qq" id="qq" size="30"
											maxlength="11" cssStyle="width:210px"></s:textfield>
									</td>
									<td valign="top">
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										电子邮箱：
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td>
										<s:textfield id="email" name="userVO.userEntity.email"
											size="30" cssStyle="width:210px"></s:textfield>
										<br />
									</td>
									<td>

									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										手机号码：
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td align="left" class="red-bcolor">
										<s:textfield id="telephone" name="userVO.userEntity.telephone"
											maxlength="11" size="30" cssStyle="width:210px"></s:textfield>
										<br />
									</td>
									<td>

									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										旺旺：
									</td>

									<td>
										<s:textfield id="ww" name="userVO.userEntity.ww" size="30"
											cssStyle="width:210px"></s:textfield>
									</td>
									<td>

									</td>
								</tr>
								<tr>

									<td height="40" align="right" class="font12h">
										推荐人：
									</td>
									<td>
										<input style="width: 210px"
											name="userVO.userEntity.referee.username" size="30"
											value="<s:property value="#request.username" />"
											id="refeereeName">
										<br />
									</td>
									<td>

									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										验证码：
										<font color="#FF0000">*&nbsp;</font>
									</td>
									<td align="left" class="red-bcolor">
										<s:textfield id="ww" name="userVO.verificationCode"
											maxlength="4" id="verificationCode" size="30"
											cssStyle="width:60px"></s:textfield>
										<img id="verificationCodeID"
											onclick="changeValidateCode(this)" title="点击图片刷新验证码"
											style="cursor: pointer;" />
									</td>
									<td>

									</td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<textarea rows="10" cols="100">
所有注册并使用本平台的会员都必须详读并遵守此制度。 
★㈠.用户注册时应填写各项真实资料（姓名.QQ.电话及邮箱）
如资料不真实出现任何问题官方都不予保护及处理。
认证时请填写自己的淘宝大号掌柜名，填写错误官方不予修改。
一旦发生任务纠纷，任务一方电话联系对方如发现其电话是假的，经官方核实将立即封停此帐号！
老会员请及时修改自己的详细资料。（资料修改在平台右上方）
★㈡.所有会员注册并使用达到110积分以后请立即到提现区去申请一次提现（填写真实的支付宝帐号与姓名）绑定自己的提现支付宝帐号，防止他人盗号将存款提走。（请认真填写自己的真实支付宝名与真实姓名，填写错误官方不予修改） 
如出现帐号被人盗取存款被他人提现官方不负任何责任。
★㈢.所有平台会员在平台应当互敬互爱，严禁言语辱骂，恐吓对方。
如有会员发生此的情况可向客服投诉对方。官方予以警告，如果情节严重官方将直接封停此帐号！
★㈣.严禁使用任何手段欺骗，恐吓等办法骗取其他会员资金或存款，一经发现立即封号！
★㈤.任何会员在做任务时严禁拖对方任务。（如遇特殊情况可QQ或电话联系对方）
1.如被投诉6次系统将自动封停此帐号，如须开通可联系官方人员罚款50元清空投诉记录并开通帐号。
2.如出现乱投诉的情况将封停帐号，罚款10元解封。平台首页予以公示！
★㈥.如有任务投诉官方客服都会给您留言，如在三天内不上线有客服联系处理。将被视为默认放弃。客服将按对方所提供证据进行裁定。（请各位会员在下线时检查自己所有任务是否都已完成）
★㈦.官方对任何超速完成任务不予任何处理。
请各位会员认真对待自己的每一个任务，不要拿自己的金钱当作游戏。也不要图一时之快浪费更多的时间。
也不要图一时之快浪费更多的时间。
★㈧.平台禁止黄钻小号接任务，让投诉一次官方核查以后封号，罚款10元解封，平台首页予以公示！ 
★㈨.好评必须带字，如经人举报好评不带字或乱评，立即封号。罚款20解封，平台首页予以公示！
★㈩.禁止任何理由给对方差评，一经发现终身封停其帐号。
★⑾.除官方明确规定可罚款解封的情况，其他封停帐号一律不退还存款。
										</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="center">
										<INPUT id="sumbitBtn"
											style="FONT-WEIGHT: bold; CURSOR: pointer;" timeId="10"
											disabled="true" type="submit" value="同意协议并注册(10)">
										<input type="hidden" name="win17_token"
											value="<s:property value="#session.win17_token"/>">
									</td>
								</tr>
							</table>


						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<s:include value="../common/footKuan.jsp"></s:include>
		</s:form>
	</BODY>
</HTML>