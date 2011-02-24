<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>
<html>
	<head>
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
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/main.css" type=text/css rel=stylesheet>
		<LINK href="css/style.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			defer="defer" type="text/javascript"></script>
		<script type="text/javascript" src="js/aop.js">
		</script>
		<script type="text/javascript" src="js/utils.js">
		</script>
		<script type="text/javascript" src="credit/releaseTask.js">
		</script>
		<script
			src="http://cdn.jquerytools.org/1.2.1/tiny/jquery.tools.min.js"></script>
	</head>
	<style>
.tooltip {
	background-color: #000;
	border: 1px solid #fff;
	padding: 10px 15px;
	width: 200px;
	display: none;
	color: #fff;
	text-align: left;
	font-size: 12px;
	/* outline radius for mozilla/firefox only */
	-moz-box-shadow: 0 0 10px #000;
	-webkit-box-shadow: 0 0 10px #000;
}

.over {
	background-color: #FFFFFF;
}

.out {
	background-color: #EEF7FB;
}
</style>
	<body>
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
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="0" bgcolor="#FFFFFF">
												<tr>
													<td width="100%">
														<s:form action="taskManager/task!releaseTask.php"
															theme="simple" method="post" id="addtaskForm"
															onsubmit="return validateForm()">
															<table width="680" border="0" align="center" height="500"
																cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
																<tr>
																	<td align="right" valign="top">
																		商品地址：
																	</td>
																	<td colspan="4" valign="middle" class="itemClass">
																		<span> <s:textfield name="itemUrls" size="70"
																				title="填写正确的商品地址！" maxlength="100"></s:textfield> <img
																				src="images/add.jpg" style="cursor: pointer;"
																				onclick="addItem(this)" title="添加任务" /> <img
																				src="images/jian.jpg" style="cursor: pointer;"
																				title="删除任务" onclick="removeItem(this)" /> </span>
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		掌柜名：
																	</td>
																	<td colspan="4">
																		<s:if test="#request.sellers!=null">
																			<s:select name="creditTaskVO.sellerID" listKey="id"
																				title="选择掌柜！" listValue="name"
																				list="#request.sellers">
																			</s:select>
																		</s:if>
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		商品任务价：
																	</td>
																	<td colspan="4">
																		<input name="platformType"
																			value="<s:property
						value="#request.platformType" />"
																			id="platformType" type="hidden" />
																		<!-- 取消重填 -->
																		<input value="<s:property value="creditTaskVO.id"/>"
																			id="cancelFlagTaskIdHidden" type="hidden" />
																		<input
																			value="<s:property value="creditTaskVO.goodTimeType"/>"
																			id="cancelFlagGTTHidden" type="hidden" />
																		<input
																			value="<s:property value="creditTaskVO.updatePrice"/>"
																			id="cancelFlagUPHidden" type="hidden" />
																		<input
																			value="<s:property value="creditTaskVO.grade"/>"
																			id="cancelFlagGradeHidden" type="hidden" />
																		<input
																			value="<s:property value="creditTaskVO.intervalHour"/>"
																			id="cancelFlagHourHidden" type="hidden" />
																		<!-- end -->
																		<input type="hidden" name="win17_token"
																			value="<s:property value="#session.win17_token"/>">
																		<s:textfield name="creditTaskVO.money" size="10"
																			title="必须是大于等于1元的金额，单位是（元）！" id="money" maxlength="6"
																			onkeyup="if(isNaN(value))execCommand('undo')"></s:textfield>
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		追加金额：
																	</td>
																	<td colspan="2">
																		<s:textfield name="creditTaskVO.addtionMoney"
																			size="10" title="在现有的金额的基础上，追加金额给接手方！" maxLength="3"
																			id="addtionMoneyId"></s:textfield>
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		追加发布点：
																	</td>
																	<td colspan="2">
																		<s:textfield name="creditTaskVO.addtionReleaseDot"
																			size="10"
																			title="在现有的发布点的基础上，追加发布点给接手方，追加的发布点将全额给接手方，平台不扣取！"
																			maxLength="3" id="addtionReleaseDotId"></s:textfield>
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		特定任务：
																	</td>
																	<td colspan="4">
																		<s:textfield name="creditTaskVO.assignUser"
																			onchange="changeUser(this)"
																			onclick="selectAssignUser(this);"
																			title="输入指定人的17win帐号，只有指定的人才可以接你的任务，发特定任务不需要发布点！"
																			id="assignUserID" maxlength="12"></s:textfield>
																		<img src="images/tdTask.gif">
																		<input type="checkbox" name="addLinkName" value="true" />
																		加入到常用联系人
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		定时任务：
																	</td>
																	<td colspan="2">
																		<s:textfield name="creditTaskVO.timeingTime"
																			title="系统帮你自动发任务，可以把你一天的任务安排好，到时候等人接手即可！"
																			onclick="WdatePicker({'minDate':'%y-%M-%d %H:%m:%s','alwaysUseStartDate':false,'el':'tasktimingDate','isShowClear':true,startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss','skin':'blue'})"
																			readonly="true" id="tasktimingDate"></s:textfield>
																		<img style="cursor: pointer;" title="只精确到分钟"
																			onclick="WdatePicker({'minDate':'%y-%M-%d %H:%m:%s','alwaysUseStartDate':false,'el':'tasktimingDate','isShowClear':true,startDate:'%y-%M-%d %H:%m:%s',dateFmt:'yyyy-MM-dd HH:mm:ss','skin':'blue'})"
																			src="js/My97DatePicker/skin/datePicker.gif"
																			width="16" height="22 align="absmiddle">
																	</td>
																</tr>
																<tr>
																	<td align="right" nowrap="nowrap">
																		是否修改价格：
																	</td>
																	<td>
																		<input type="radio" name="creditTaskVO.updatePrice"
																			title="任务的价格和淘宝上的商品价格（包含邮费）不一致时，需要修改价格！" value="true">
																		需修改价
																	</td>
																	<td>
																		<input name="creditTaskVO.updatePrice" type="radio"
																			title="任务的价格和淘宝上的商品价格（包含邮费）必须一致!" value="false">
																		不需改价
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		任务类型：
																	</td>
																	<td>
																		<input type="radio" name="creditTaskVO.taskType"
																			id="xnTaskType_Id" onclick="hideTaskType(this)"
																			checked="checked"
																			title="任务的价格和淘宝上的商品价格（包含邮费）不一致时，需要修改价格" value="1">
																		虚拟任务
																		<img src="images/xnType.jpg" />
																		&nbsp;&nbsp;
																		<input type="radio" name="creditTaskVO.taskType"
																			id="stTaskType_Id" onclick="hideTaskType(this)"
																			title="任务的价格和淘宝上的商品价格（包含邮费）不一致时，需要修改价格" value="2">
																		实体任务
																		<img src="images/swType.jpg" />
																		&nbsp;&nbsp;
																		<input type="radio" name="creditTaskVO.taskType"
																			id="tcTaskType_Id" onclick="hideTaskType(this)"
																			title="任务的价格和淘宝上的商品价格（包含邮费）不一致时，需要修改价格" value="3">
																		套餐任务
																		<img src="images/tcType.jpg" />
																	</td>
																	<td>
																	</td>
																</tr>
																<tr class="xnTaskType">
																	<td align="right">
																		好评要求：
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				checked="checked" title="收到货后马上好评，并且要写好评内容！"
																				value="马上带字好评" />
																			马上带字好评
																		</label>
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				withodCommmon="withodCommmon"
																				title="收到货后马上好评，不用写好评内容！" value="马上不带字好评" />
																			马上不带字好评
																		</label>

																	</td>
																</tr>
																<tr class="xnTaskType">
																	<td>
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				withodCommmon="withodCommmon"
																				title="即平台完成任务，淘宝不评价等待默认好评！" value="待系统默认好评" />
																			待系统默认好评
																		</label>
																	</td>
																	<td>
																	</td>
																</tr>
																<tr class="stTaskType" style="display: none">
																	<td align="right">
																		好评要求：
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				title="扣X*2个发布点(特定任务除外)！" value="一天后收货好评" />
																			一天后收货好评
																		</label>
																	</td>
																	<td nowrap="nowrap">
																		<input name="creditTaskVO.grade" type="radio"
																			title="扣X*2+1个发布点(特定任务除外)！" value="二天后收货好评" />
																		二天后收货好评
																	</td>
																</tr>
																<tr class="stTaskType" style="display: none">
																	<td>
																		&nbsp;
																	</td>
																	<td nowrap="nowrap">
																		<input name="creditTaskVO.grade" type="radio"
																			title="扣X*2+2个发布点(特定任务除外)" value="三天后收货好评" />
																		三天后收货好评
																	</td>
																	<td>
																		<input name="creditTaskVO.grade" type="radio"
																			onclick="diyCommentTime('intervalHour1')"
																			title="自定义时间好评！" value="自定义时间好评" />
																		<input type="text" maxlength="3" id="intervalHour1"
																			title="0≤h＜24(扣x个发布点)，24≤h＜48(扣x*2个发布点)，48≤h＜72(扣x*2+1个发布点)，h≥72(扣x*2+2个发布点)(特定任务除外)！"
																			name="creditTaskVO.intervalHour" style="width: 40px"
																			disabled="disabled">
																		<span>时后好评</span>
																	</td>
																</tr>
																<tr class="tcTaskType" style="display: none">
																	<td align="right">
																		好评要求：
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				title="收到货后马上好评，并且要写好评内容！" value="马上带字好评" />
																			马上带字好评
																		</label>
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				withodCommmon="withodCommmon"
																				title="即平台完成任务，淘宝不评价等待默认好评！" value="待系统默认好评" />
																			待系统默认好评
																		</label>
																	</td>
																</tr>
																<tr class="tcTaskType" style="display: none">
																	<td align="right">
																	</td>
																	<td nowrap="nowrap">
																		<label>
																			<input name="creditTaskVO.grade" type="radio"
																				title="扣X*2个发布点(特定任务除外)！" value="一天后收货好评" />
																			一天后收货好评
																		</label>
																	</td>
																	<td nowrap="nowrap">
																		<input name="creditTaskVO.grade" type="radio"
																			title="扣X*2+1个发布点(特定任务除外)！" value="二天后收货好评" />
																		二天后收货好评
																	</td>
																</tr>
																<tr class="tcTaskType" style="display: none">
																	<td>
																		&nbsp;
																	</td>
																	<td nowrap="nowrap">
																		<input name="creditTaskVO.grade" type="radio"
																			title="扣X*2+2个发布点(特定任务除外)！" value="三天后收货好评" />
																		三天后收货好评
																	</td>
																	<td>
																		<input name="creditTaskVO.grade" type="radio"
																			onclick="diyCommentTime('intervalHour2')"
																			title="自定义时间好评！" value="自定义时间好评" />
																		<input type="text" maxlength="3"
																			title="0≤h＜24(扣x个发布点)，24≤h＜48(扣x*2个发布点)，48≤h＜72(扣x*2+1个发布点)，h≥72(扣x*2+2个发布点)(特定任务除外)!"
																			name="creditTaskVO.intervalHour" style="width: 40px"
																			id="intervalHour2" disabled="disabled">
																		<span>时后好评</span>
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		任务保护：
																	</td>
																	<td colspan="2">
																		<s:checkbox name="creditTaskVO.protect" value="false"
																			title="接手人需要您审核过后才能发布任务，审核方式可以通过QQ进行一些特定的交流！对方同意你的要求时，你就可以同意他接任务，否则可以不准他接任务!"
																			fieldValue="true" />
																	</td>

																</tr>
																<tr>
																	<td align="right">
																		放入仓库：
																	</td>
																	<td colspan="2">
																		<span style="display: none" id="respositoryName">
																			<s:textfield name="creditTaskVO.respositoryName"
																				title="任务简单描述，默认为任务ID！" maxlength="20"></s:textfield>
																		</span>
																		<s:checkbox name="creditTaskVO.repository"
																			title="放入任务仓库下次方便使用！" id="respository" value="false"
																			fieldValue="true" />
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		收货地址：
																	</td>
																	<td colspan="2">
																		<s:checkbox name="creditTaskVO.address"
																			title="根据你的卖好的地址系统自动生成一个市区的地址，让你24小时收货更真实！"
																			fieldValue="true" />
																	</td>
																</tr>
																<tr>
																	<td align="right">
																		好评短语：
																	</td>
																	<td colspan="2" nowrap="nowrap">
																		<s:textfield maxlength="255"
																			name="creditTaskVO.comment"
																			title="希望接手人在任务完成时对你的好评时的内容！" size="70"></s:textfield>
																		<input value="true" name="commentByJS"
																			id="commentByJSID" title="评论不能为空，要求接手人自己想！"
																			type="checkbox">
																		接手人自己想
																	</td>
																</tr>
																<tr>
																	<td height="35">
																		&nbsp;
																	</td>
																	<td colspan="2">
																		<input type="submit" style="cursor: pointer;"
																			title="发布任务" value="发布任务">
																		&nbsp;
																		<input type="reset" style="cursor: pointer;"
																			title="重置内容" value="重置内容">
																	</td>

																</tr>
															</table>
														</s:form>
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
		<div id="selectUserDiv"
			onmouseout="moveOutCloseDisplay(event,'selectUserDiv');"
			style="display: none; position: absolute; z-index: 999; border: 1px solid; border-color: #000000; background: #EEF7FB; width: 155px; height: 200px; overflow-y: auto; overflow-x: no">
			<table style="width: 99%;" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th align="left">
							选择联系人
						</th>
					</tr>
				</thead>
				<tbody id="assignUserTable">
				</tbody>
			</table>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</html>
