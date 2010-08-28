<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK rev=Stylesheet href="css/global.css" type=text/css
			rel=Stylesheet>
		<LINK href="css/login.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/jieducm_pupu.js" type=text/javascript></SCRIPT>
	</HEAD>
	<BODY>
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

					<TD class=K_mtcontent align=left colSpan=6>
						<FORM name="form" id="form" onSubmit="return save_onclick()"
							method=post>
							<input name="zhuce" type="hidden" value="ok" />
							<table width="900" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="100" colspan="3">
										<span class="font14b2">新用户免费注册-轻松注册10秒钟搞定</span>
										<br />
										请填写以下信息，
										<font color="#FF0000">*</font>为必填内容,<font color="#FF0000">注意：淘宝，有啊，拍拍信息必须填写一个</font>
										<br />
										请认真仔细的填写以下信息，真实的个人信息有助于给你使用的服务带来更多的保障以及便捷！
									</td>

								</tr>
								<tr>
									<td width="146" height="40" align="right" class="font12h">
										用户名：
									</td>
									<td width="230">
										<input name="UserID" type="text" id="UserID"
											onBlur="name_chk(this)" size="30" maxlength="12">
									</td>
									<td width="524">
										<span id="c0"><font color="#FF0000">*</font>4-12个字符字母数字</span>
										<script id="tname"></script>
									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										登录密码：
									</td>
									<td>
										<input name="password" type="password" id="password" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000">*</font>登录时需要使用密码，可以是6至16位数字或字母
									</td>
								</tr>
								<tr>

									<td height="40" align="right">
										<span class="font12h">确认登录密码：</span>
									</td>
									<td>
										<input name="password2" type="password" id="password2"
											size="30" style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000">*</font>重复上面的密码
									</td>
								</tr>

								<tr>
									<td height="40" align="right" class="font12h">
										Q&nbsp;Q:
									</td>

									<td>
										<input name="QQ" type="text" id="QQ" size="30"
											onKeyUp="if(isNaN(value))execCommand('undo')"
											onBlur="xiechuemail()" />
									</td>
									<td>
										<font color="#FF0000">*</font>刷宝时必用
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										旺旺:
									</td>

									<td>
										<input name="QQ" type="text" id="QQ" size="30"
											onKeyUp="if(isNaN(value))execCommand('undo')"
											onBlur="xiechuemail()" />
									</td>
									<td>
										<font color="#FF0000">注意：不要用旺旺发送任何关于刷信誉的内容</font>
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										电子邮箱：
									</td>
									<td>
										<input name="Email" type="text" id="Email" size="30" />
									</td>
									<td>
										<font color="#FF0000">*</font>取回密码使用！
									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										操作密码：
									</td>
									<td>
										<span class="font12h"> <input name="czm"
												type="password" id="czm" size="30" style="width: 213px;" />
										</span>
									</td>
									<td>
										<font color="#FF0000">*</font>互刷时必用 6-16位！
									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										确认操作密码：
									</td>
									<td align="left" class="red-bcolor">
										<span class="font12h"> <input name="czm2"
												type="password" id="czm2" size="30" style="width: 213px;" />
										</span>
									</td>
									<td>
										<font color="#FF0000">*</font>重复上面的操作密码
									</td>

								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										淘宝店铺地址：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>您的淘宝店铺
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										淘宝账号：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>系统自动获取
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										淘宝小号账号：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>购买别人物品的淘宝账号，该必须是黄钻级别以下（如被查出立即封号）
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										拍拍店铺地址：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>您的拍拍店铺
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										拍拍账号：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>系统自动获取
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										拍拍小号账号：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>购买别人物品的拍拍账号，该必须是黄钻级别以下（如被查出立即封号）
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										有啊店铺地址：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>您的有啊店铺
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										有啊账号：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										<font color="#FF0000"></font>系统自动获取
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										有啊小号账号：
									</td>
									<td align="left" class="red-bcolor">
										<input name="czm2" type="password" id="czm2" size="30"
											style="width: 213px;" />
									</td>
									<td>
										购买别人物品的有啊账号，该必须是黄钻级别以下（如被查出立即封号） 
									</td>
								</tr>

								<tr>
									<td height="40" align="right" class="font12h">
										手机号码：
									</td>
									<td align="left" class="red-bcolor">
										<input name="phone" type="text" id="phone" size="30"
											onKeyUp="if(isNaN(value))execCommand('undo')" />
									</td>
									<td>
										<font color="#FF0000">*</font>可凭手机找回密码
									</td>
								</tr>

								<tr>
									<td height="40" align="right" class="font12h">
										选择发货地：
									</td>
									<td align="left" class="red-bcolor">
										<select></select>
										<select></select>
										<select></select>
									</td>
									<td>
										<font color="#FF0000"></font>当发送的是
										<font color="#FF0000">24小时</font>以内的
										<font color="#FF0000">实物任务</font>时，系统可以根据这个地址，生成一个同地区的随机地址。更大的提高真实性！
									</td>
								</tr>


								<tr>

									<td height="40" align="right" class="font12h">
										推荐人：
									</td>
									<td>
										<input name="tjr" type="text" id="tjr" value="" size="30" />
									</td>
									<td>
										没有可留空！对于被推荐人，没有任务损失，推荐人得到积分奖励
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										验证码：
									</td>
									<td align="left" class="red-bcolor">

										<INPUT id=CheckCode maxLength=6 name=CheckCode
											onKeyUp="if(isNaN(value))execCommand('undo')">
										<img src="jieducm_code.asp" alt="验证码"
											onClick="this.src='jieducm_code.asp?rnd=' + Math.random();" />
									</td>
									<td>
										<font color="#FF0000">*</font>为防止恶意注册请输入验证码
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
★⑾.除官方明确规定可罚款解封的情况，其他封停帐号一律不退还存款及发布点。
										</textarea>
									</td>
								</tr>
								<tr>
									<td height="40" colspan="3" align="left" class="font12h">
										<table width="100%" border="0" align="left" cellpadding="0"
											cellspacing="0" id=showinfo_c1 style="display: none">
											<tr>
												<td width="16%" height="40" align="right" class="font14b2">
													选填项目：
												</td>
												<td colspan="2">
													<hr style="color: #FF9933" />
												</td>
											</tr>

											<tr>
												<td height="40" align="right" class="font12h">
													密码问题：
												</td>
												<td>
													<span class="font12h"> <input name="weiti"
															type="text" id="weiti" size="30" /> </span>
												</td>
												<td></td>
											</tr>
											<tr>

												<td height="40" align="right" class="font12h">
													问题答案：
												</td>
												<td>
													<span class="font12h"> <input name="daai"
															type="text" id="daai" size="30" /> </span>
												</td>
												<td></td>
											</tr>
											<tr>
												<td height="40" align="right" class="font12h">
													真实姓名：
												</td>

												<td>
													<input name="rname" type="text" id="rname" size="30" />
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<td height="40" align="right" class="font12h">
													店铺地址：
												</td>
												<td align="left" class="red-bcolor">
													<input name="shopnote" type="text" id="shopnote" size="30" />
												</td>
												<td></td>
											</tr>

											<tr>
												<td height="40" align="right" class="font12h">
													店铺描述：
												</td>
												<td>
													<input name="shopnote" type="text" id="shopnote" size="30" />
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<td height="30" align="right">
													<span class="font12h">性别：</span>
												</td>
												<td>
													<input name="sex" type="radio" id="radio" value="1"
														checked="checked" />

													男士
													<input type="radio" name="sex" id="radio2" value="2" />
													女士
												</td>
												<td>
													&nbsp;
												</td>
											</tr>
											<tr>
												<td height="45" align="right" class="font12h">
													联系地址：
												</td>
												<td>
													<input name="address" type="text" id="address" size="30" />
												</td>

												<td>
													&nbsp;
												</td>
											</tr>

										</table>
										<div align="center">
											<a href="tencent://message/?uin=30756500">联系QQ：30756500
												验证 开通号码</a>
										</div>
									</td>
								</tr>
								<tr>
									<td height="40" align="right" class="font12h">
										&nbsp;
									</td>
									<td align="center" class="red-bcolor">
									</td>
									<td>
										<INPUT
											style="FONT-WEIGHT: bold; WIDTH: 120px; CURSOR: pointer; COLOR: #000000; HEIGHT: 26px"
											type=submit value="同意协议(10)">
									</td>
								</tr>
							</table>
						</form>

					</TD>
				</TR>
			</TBODY>
			<s:include value="../common/footKuan.jsp"></s:include>
	</BODY>
</HTML>