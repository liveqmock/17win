<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/main.css" type=text/css rel=stylesheet>
		<LINK href="css/style.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
	</head>
	<body>
		<s:include value="../common/title.jsp"></s:include>
		<table cellspacing="0" cellpadding="0" width="910" align="center"
			border="0">
			<tr>
				<td height="50">
					<div
						style="CLEAR: both; OVERFLOW: hidden; WIDTH: 910px; HEIGHT: 50px; BACKGROUND-COLOR: #f3f8fe">
						<div
							style="MARGIN: 10px 10px 0px; LINE-HEIGHT: 30px; BORDER-BOTTOM: #cccccc 1px dashed; HEIGHT: 40px">
							<div
								style="CLEAR: both; OVERFLOW: hidden; WIDTH: 910px; BACKGROUND-COLOR: #f3f8fe">

								<div
									style="MARGIN: 10px 10px 0px; LINE-HEIGHT: 30px; BORDER-BOTTOM: #cccccc 1px dashed; HEIGHT: 40px">
									<div>
										亲爱的会员：
										<font color=#ff0000>xgj1988</font>&nbsp;(
										<font color=red>还没有加入部落</font>)&nbsp;您好！您拥有存款：
										<font color=#ff0000> 20.95</font> 元 &nbsp; 发布点：
										<font color=#ff0000> 2.0 </font>点 &nbsp; 积分：
										<font color=#ff0000>260</font>点

										<a href="../user/user.asp"> 站内信(<FONT color=#ff0000>0</FONT>)</a>
									</div>
								</div>
							</div>
						</div>

					</div>
				</td>
			</tr>
		</table>
		<div align="center">
			<DIV
				style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
				<DIV
					style="CLEAR: both; MARGIN-TOP: 2px; WIDTH: 910px; HEIGHT: 25px">
					<DIV style="FLOAT: left; OVERFLOW: hidden; WIDTH: 700px">
						<UL id=task_nav>
							<LI>
								<A href="index.asp">淘宝互动区</A>
							</LI>
							<LI>
								<A
									style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
									href="pushmission.asp">发布任务</A>
							</LI>

							<LI>
								<A href="ReMission.asp">已接任务</A>
							</LI>
							<LI>
								<A href="MyMission.asp">已发任务</A>
							</LI>
							<LI>
								<A href="MyWw.asp">绑定店铺</A>
							</LI>
							<LI>
								<A href="Buyhao.asp">绑定买号</A>
							</LI>
							<LI>
								<A href="MySave.asp">任务仓库</A>
							</LI>
						</UL>
					</DIV>

				</DIV>
				<DIV style="CLEAR: both; WIDTH: 910px">
					<IMG src="images/task_nav_line.gif">
				</DIV>
			</DIV>
		</div>
		<table width="760" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>
					<table width="910" border="0" cellspacing="0" cellpadding="0">
						<tr>

							<td valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">

									<tr height="1">
										<td height="5"></td>
									</tr>
								</table>
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									bgcolor="#FFFFFF" class="bordera margin6">
									<tr>
										<td height="30" class="border-bot">
											&nbsp;&nbsp;&nbsp;
											<span class="font12b">淘宝任务发布区</span>
										</td>

									</tr>
									<tr>
										<td height="140" valign="middle">
											<table width="680" border="0" align="center" cellpadding="0"
												cellspacing="0" bgcolor="#FFFFFF">
												<tr>
													<td width="550">
														<FORM name=formf method=post action="jieducm_faok.asp"
															onSubmit="return save_onclick12()">
															<input name="fa" type="hidden" value="ok">
															<table width="680" border="0" align="center"
																cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
																<tr>
																	<td height="30" colspan="3" align="left"
																		class="font14b4">
																		<table width="100%" border="0" cellpadding="0"
																			cellspacing="0" align="left">
																			<tr>

																				<td class="font14b4">
																					<div align="right">
																						商品任务价：
																					</div>
																				</td>
																				<td colspan="4">
																					<input name=Price1 id=Price1 size="10" maxlength=6
																						onKeyUp="if(isNaN(value))execCommand('undo')">
																					元注：此价格是包含运费的总价格
																					1-40元，扣一个发布点；41-100元扣两个发布点；101-200元，扣三个发布点；
																					<br>
																				</td>
																			</tr>

																			<tr>
																				<td class="font14b4">
																					&nbsp;
																				</td>
																				<td colspan="4">
																					201-500元，扣四个发布点；501-1000元扣五个发布点
																					<br>
																				</td>

																			</tr>
																			<tr>
																				<td class="font14b4">
																					<div align="right">
																						掌&nbsp; 柜&nbsp; 名：
																					</div>
																				</td>
																				<td colspan="4">
																					<label>
																						<input type='radio' name='Shopkeeper'
																							id='nick_name' value='xgj1988'>
																						xgj1988
																					</label>
																				</td>
																			</tr>

																			<tr>
																				<td class="font14b4">
																					<div align="right">
																						商品地址：
																					</div>
																				</td>
																				<td colspan="4">
																					<span class="font12l"> <input id=ProUrl1
																							maxlength=255 name=ProUrl1 onBlur="check(this)">
																						*自动检测宝贝地址和掌柜名是否相符</span>
																				</td>
																			</tr>
																			<tr style="display: none">
																				<td class="font14b4">
																					任务2
																				</td>

																				<td class="font14b4">
																					商品任务价：
																				</td>
																				<td>
																					<input name=Price2 id=Price2 size="10" maxlength=3
																						onKeyUp="if(isNaN(value))execCommand('undo')"
																						onBlur="totalprice(this)">
																				</td>
																				<td class="font14b4">
																					商品地址：
																				</td>
																				<td>
																					<input id=ProUrl2 maxlength=255 name=ProUrl2
																						onblur="check(this)">
																				</td>
																			</tr>
																			<tr style="display: none">
																				<td class="font14b4">
																					任务3
																				</td>

																				<td class="font14b4">
																					商品任务价：
																				</td>
																				<td>
																					<input name=Price3 id=Price3 size="10" maxlength=3
																						onKeyUp="if(isNaN(value))execCommand('undo')"
																						onBlur="totalprice(this)">
																				</td>
																				<td class="font14b4">
																					商品地址：
																				</td>
																				<td>
																					<input id=ProUrl3 maxlength=255 name=ProUrl3
																						onblur="check(this)">
																				</td>
																			</tr>
																			<tr style="display: none">
																				<td class="font14b4">
																					任务4
																				</td>

																				<td class="font14b4">
																					商品任务价：
																				</td>
																				<td>
																					<input name=Price4 id=Price4 size="10" maxlength=3
																						onKeyUp="if(isNaN(value))execCommand('undo')"
																						onBlur="totalprice(this)">
																				</td>
																				<td class="font14b4">
																					商品地址：
																				</td>
																				<td>
																					<input id=ProUrl4 maxlength=255 name=ProUrl4
																						onblur="check(this)">
																				</td>
																			</tr>
																			<tr style="display: none">
																				<td class="font14b4">
																					任务5
																				</td>

																				<td class="font14b4">
																					商品任务价：
																				</td>
																				<td>
																					<input name=Price5 id=Price5 size="10" maxlength=3
																						onKeyUp="if(isNaN(value))execCommand('undo')"
																						onBlur="totalprice(this)">
																				</td>
																				<td class="font14b4">
																					商品地址：
																				</td>
																				<td>
																					<input id=ProUrl5 maxlength=255 name=ProUrl5
																						onblur="check(this)">
																				</td>
																			</tr>

																			<tr style="display: none">
																				<td width="25%" class="font14b4">
																					任务6
																				</td>

																				<td width="8%" class="font14b4">
																					商品任务价：
																				</td>
																				<td width="19%">
																					<input name=Price6 id=Price6 size="10" maxlength=3
																						onKeyUp="if(isNaN(value))execCommand('undo')"
																						onBlur="totalprice(this)">
																				</td>
																				<td width="17%" class="font14b4">
																					商品地址：
																				</td>
																				<td width="31%">
																					<input id=ProUrl6 maxlength=255 name=ProUrl6
																						onblur="check(this)">
																				</td>
																			</tr>

																		</table>
																	</td>
																</tr>

																<tr>
																	<td height="30" align="right">
																		<span class="font14b4">价格是否相等：</span>
																	</td>
																	<td>
																		<label>
																			<input name="isprice" type="radio" id="isprice"
																				value="金额相等" checked>
																			<span class="font12l"> 金额相等</span>
																		</label>
																	</td>
																	<td>
																		<label>
																			<input type="radio" name="isprice" id="isprice"
																				value="需修改价格">
																			<span class="font12l">需修改价格</span>
																		</label>
																	</td>
																</tr>

																<tr>
																	<td height="30" align="right">
																		<span class="font14b4">动态评分：</span>
																	</td>
																	<td>
																		<label>
																			<input name="play" type="radio" value="全部打5分" checked>
																			<span class="font12l">全部打5分</span>
																		</label>
																	</td>
																	<td>
																		<label>
																			<input type="radio" name="play" value="全部不打分">
																			<span class="font12l"> 全部不打分</span>
																		</label>
																	</td>

																</tr>
																<tr>
																	<td height="30" align="right">
																		&nbsp;
																	</td>
																	<td colspan="2">
																		<a href="#" target="_blank"><font color="#FF0000"><strong>*
																					发布延时收货的任务，平台免费提供物流单号，并强制买家延时收货</strong> </font> </a>
																	</td>
																</tr>
																<tr>
																	<td width="129" height="30" align="right">
																		<span class="font14b4">备注：</span>
																	</td>
																	<td width="227">
																		<label>
																			<input name="bei" type="radio" id="bei"
																				value="马上收货好评" />

																			<span class="font12l">马上收货好评</span>
																		</label>
																		<br />
																	</td>
																	<td width="324">
																		<label>
																			<input name="bei" type="radio" id="radio"
																				value="一天后收货好评" />
																			<span class="font12l"> 一天后收货好评</span>
																		</label>
																		(扣x*2个发布点)
																	</td>
																</tr>
																<tr>
																	<td height="30">
																		&nbsp;
																	</td>
																	<td>
																		<label>
																			<input type="radio" name="bei" id="radio2"
																				value="二天后收货好评" />

																			<span class="font12l">二天后收货好评</span>
																		</label>
																		(扣x*2+1个发布点)
																	</td>
																	<td>
																		<label>
																			<input type="radio" name="bei" id="radio3"
																				value="三天后收货好评" />
																			<span class="font12l">三天后收货好评</span>
																		</label>
																		(扣x*2+2个发布点)
																	</td>
																</tr>


																<tr>
																	<td height="30">
																		&nbsp;
																	</td>
																	<td colspan="2">
																		<label>
																			<input name="baohu" type="checkbox" id="baohu"
																				value="1" checked="checked" disabled="disabled" />

																			防来路保护
																		</label>
																		<span class="red-bcolor">*什么是来路保护</span>
																		<label>
																			<input name="baohu3" type="checkbox"
																				disabled="disabled" id="baohu3" value="1" checked />
																			防黄钻保护
																		</label>
																		*
																		<span class="red-bcolor">什么是防黄钻保护 </span>
																	</td>
																</tr>
																<tr>
																	<td>
																		&nbsp;
																	</td>

																	<td colspan="2">
																		<input name="baohu3" type="checkbox"
																			disabled="disabled" id="baohu3" value="1" checked />
																		自动检测宝贝地址和掌柜名
																		<span class="red-bcolor">*什么是自动检测宝贝地址和掌柜名</span>
																	</td>
																</tr>
																<tr>
																	<td height="50">
																		&nbsp;
																	</td>
																	<td colspan="2">
																		<label>
																			<input name="baohu2" type="checkbox" id="baohu2"
																				value="1" />
																			任务保护，接受者接你任务后，要你审核才可以看到商品地址！
																		</label>
																		<br />
																		<span class="red-bcolor">什么是任务保护- &gt;</span>
																	</td>

																</tr>
																<tr>
																	<td height="35">
																		<span class="font14b4">定时发布任务:</span>
																	</td>
																	<td colspan="2">
																		<font color="#FF0000"><input type="text"
																				onClick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')"
																				readonly size="30" name="outtime" />&nbsp;留空则不生效！</font>
																	</td>
																</tr>
																<tr>
																	<td height="35">
																		&nbsp;
																	</td>
																	<td colspan="2">

																		<label>
																			<input name="depot" type="checkbox" id="depot"
																				value="1" />
																			顺便添加到我的任务仓库
																		</label>
																		<span class="red-bcolor">什么是任务仓库- &gt;</span>
																	</td>
																</tr>
																<tr>
																	<td height="35">
																		&nbsp;
																	</td>
																	<td colspan="2">
																		<input type="submit" name="button" id="button"
																			value="发布任务"
																			onClick="this.disabled=true;document.formf.submit();">
																		&nbsp;
																		<input type="reset" name="button2" id="button2"
																			value="重置">
																	</td>

																</tr>
															</table>
														</FORM>


													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</td>
			</tr>
		</table>
		<s:include value="../common/foot.jsp"></s:include>
	</BODY>
</html>
