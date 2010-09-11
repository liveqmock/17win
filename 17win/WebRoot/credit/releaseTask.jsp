<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<%
			//让浏览器不缓存jsp页面 
			response.setHeader("Pragma", "No-cache");// http1.0 
			response.setHeader("Cache-Control", "no-store,no-cache"); //http1.1 
			response.setHeader("Expires", "0");
			response.setDateHeader("Expires", 0);// 这个是针对代理的？但我设置后还是没达到效果。不解！！
		%>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/main.css" type=text/css rel=stylesheet>
		<LINK href="css/style.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			type="text/javascript"></script>
		<script type="text/javascript" src="js/aop.js">
		</script>
		<script type="text/javascript" src="js/utils.js">
		</script>
		<script type="text/javascript" src="credit/releaseTask.js">
		</script>

	</head>
	<style>
.errorText {
	border: #FF0000 solid;
}
</style>
	<body>
		<s:form action="taskManager/task!releaseTask.php" theme="simple"
			onsubmit="return validateForm()">
			<s:include value="../common/title.jsp"></s:include>
			<s:include value="../common/task/title.jsp"></s:include>
			<div align="center">
				<DIV
					style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
					<s:include value="../common/task/navigation.jsp"></s:include>
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
												<span class="font12b"><s:property
														value="#request.platform" />任务发布区</span>
											</td>

										</tr>
										<tr>
											<td height="140" valign="middle">
												<table width="100%" border="0" align="center"
													cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
													<tr>
														<td width="100%">

															<table width="680" border="0" align="center"
																cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
																<tr>
																	<td colspan="5" align="center">
																		resultTaskReps
																		<s:if test="#request.resultTaskReps!=null">
																			<s:select id="resultTaskRepsId"
																				list="#request.resultTaskReps"
																				cssStyle="display:none" listKey="id"
																				listValue="name" headerKey=""
																				headerValue="--请选择任务仓库--">
																			</s:select>
																		</s:if>
																		<s:else>
																			<select>
																				<option>
																					您当前任务仓库里面没有任务
																				</option>
																			</select>
																		</s:else>
																		<input value="点击此处从任务仓库中获取" type="button">
																	</td>
																</tr>
																<tr>
																	<td colspan="5">
																		<hr color="#DDEDFA">
																	</td>

																</tr>
																<tr>
																	<td class="font14b4" align="right" valign="top">
																		商品任务价：
																	</td>
																	<td colspan="4">
																		<input name="platformType"
																			value="<s:property
						value="#request.platformType" />"
																			id="platformType" type="hidden" />
																		<input value="#session.userLogin.money" id="currMoney"
																			type="hidden" />
																		<s:textfield name="creditTaskVO.money" size="10"
																			id="money" maxlength="6"
																			onkeyup="if(isNaN(value))execCommand('undo')"></s:textfield>
																		元(最长6位)&nbsp;&nbsp;
																		<font color="red">注意：此价格是包含运费的总价格
																			1-40元，扣一个发布点；41-100元(扣2个发布点；101-200元(扣3个发布点)
																			201-500元(扣4个发布点；501-1000元(扣5个发布点)</font>
																	</td>
																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		商品地址：
																	</td>
																	<td colspan="4">
																		<s:textfield name="creditTaskVO.itemUrl" id="itemUrl"
																			maxlength="255"></s:textfield>
																		<span class="font12l"> *自动检测宝贝地址和掌柜名是否相符</span>
																	</td>
																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		掌柜名：
																	</td>
																	<td colspan="4">
																		<s:radio list="#request.sellers" listKey="id"
																			name="creditTaskVO.sellerID" listValue="name"></s:radio>
																	</td>
																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		价格是否相等：
																	</td>
																	<td>
																		<input name="creditTaskVO.updatePrice" type="radio"
																			value="false" checked>
																		<span class="font12l"> 金额相等</span>
																	</td>
																	<td>
																		<input type="radio" name="creditTaskVO.updatePrice"
																			value="true">
																		<span class="font12l">需修改价格</span>
																	</td>
																</tr>

																<tr>
																	<td class="font14b4" align="right">
																		动态评分：
																	</td>
																	<td>
																		<input name="creditTaskVO.grade" type="radio"
																			value="1" checked>
																		<span class="font12l">全部打5分</span>
																	</td>
																	<td>
																		<input type="radio" name="creditTaskVO.grade"
																			value="2">
																		<span class="font12l"> 全部不打分</span>
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
																		<span class="font14b4">收货时间：</span>
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.goodTimeType" type="radio"
																				checked="checked" value="1" />
																			<span class="font12l">马上收货好评</span>
																		</label>
																		(扣x个发布点)
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.goodTimeType" type="radio"
																				value="2" />
																			<span class="font12l"> 一天后收货好评</span>
																		</label>
																		(扣x*2个发布点)
																	</td>
																</tr>
																<tr>
																	<td height="30">
																		&nbsp;
																	</td>
																	<td nowrap="nowrap">
																		<input name="creditTaskVO.goodTimeType" type="radio"
																			value="3" />
																		<span class="font12l">二天后收货好评</span> (扣x*2+1个发布点)
																	</td>
																	<td nowrap="nowrap">
																		<input name="creditTaskVO.goodTimeType" type="radio"
																			value="4" />
																		<span class="font12l">三天后收货好评</span> (扣x*2+2个发布点)
																	</td>
																</tr>
																<tr>
																	<td height="30">
																		&nbsp;
																	</td>
																	<td>
																		<input name="creditTaskVO.goodTimeType" type="radio"
																			value="5" />
																		<span class="font12l">自定义</span>
																		<input type="creditTaskVO.intervalHour" maxlength="3"
																			style="width: 40px" id="intervalHour"
																			disabled="disabled">
																		<span class="font12l">时后好评</span>
																	</td>
																	<td>
																		0≤h＜24(扣x个发布点)，24≤h＜48(扣x*2个发布点)，
																		，48≤h＜72(扣x*2+1个发布点)，h≥72(扣x*2+2个发布点)
																	</td>
																</tr>
																<!-- 
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
																			<input name="baohu3" type="checkbox" checked="checked"
																				disabled="disabled" id="baohu3" value="1" />
																			防黄钻保护
																		</label>
																		*
																		<span class="red-bcolor">什么是防黄钻保护 </span>
																	</td>
																</tr>
																 -->
																<tr>
																	<td class="font14b4" align="right">
																		任务保护：
																	</td>
																	<td colspan="2">
																		<s:checkbox name="creditTaskVO.protect" value="false"
																			fieldValue="true" />
																		<span class="red-bcolor">*什么是任务保护</span>
																	</td>

																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		定时任务：
																	</td>
																	<td colspan="2">
																		<s:textfield name="creditTaskVO.timeingTime"
																			id="tasktimingDate"></s:textfield>
																		<img style="cursor: pointer;"
																			onclick="WdatePicker({'minDate':'%y-%M-%d %H:%m','alwaysUseStartDate':false,'el':'tasktimingDate','isShowClear':false,startDate:'%y-%M-%d %H:%m',dateFmt:'yyyy-MM-dd HH:mm','skin':'blue'})"
																			src="js/My97DatePicker/skin/datePicker.gif"
																			width="16" height="22" align="absmiddle">
																		<input type="hidden" value="4" name="statu" />
																	</td>
																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		放入仓库：
																	</td>
																	<td colspan="2">
																		<s:checkbox name="creditTaskVO.repository"
																			id="respository" value="false" fieldValue="true" />
																		<span style="display: none" id="respositoryName">
																			<s:textfield name="creditTaskVO.respositoryName"
																				id="itemUrl" maxlength="20"></s:textfield> 默认为任务ID </span>
																		<span class="red-bcolor">*什么是任务仓库</span>
																	</td>
																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		收货地址：
																	</td>
																	<td colspan="2">
																		<s:checkbox name="creditTaskVO.address" value="false"
																			fieldValue="true" />
																		<span class="red-bcolor">*什么是自动生成收货地址信息（实物任务有用）</span>
																	</td>
																</tr>
																<tr>
																	<td class="font14b4" align="right">
																		简单描述：
																	</td>
																	<td colspan="2">
																		<s:textfield maxlength="100" name="creditTaskVO.desc"
																			size="70"></s:textfield>
																	</td>
																</tr>
																<tr>
																	<td height="35">
																		&nbsp;
																	</td>
																	<td colspan="2">
																		<input type="submit" value="发布任务">
																		&nbsp;
																		<input type="reset" name="button2" id="button2"
																			value="重置内容">
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
					</td>
				</tr>
			</table>
			<s:include value="../common/footDuan.jsp"></s:include>
		</s:form>
	</BODY>
</html>
