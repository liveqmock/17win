<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<s:include value="/admin/common/header.jsp"></s:include>

		<SCRIPT type="text/javascript" src="user/userIndex.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminUserManager/adminUser!queryUser.php"
			id="queryForm" onsubmit="return validateForm()" theme="simple">
			<table width="100%" cellpadding="1" cellspacing="1" border="0px"
				style="background: #DDEDFA">
				<tr>
					<td nowrap="nowrap">
						用&nbsp;&nbsp;户&nbsp;&nbsp;名：
						<s:textfield name="adminUserVO.username">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						发&nbsp;&nbsp;&nbsp;布&nbsp;&nbsp;&nbsp;点：
						<s:textfield name="adminUserVO.startReleaseDot" id="startDate"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endReleaseDot" id="endDate"
							cssStyle="width:40px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						余&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：
						<s:textfield name="adminUserVO.startMoney" id="startDate"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endMoney" id="endDate"
							cssStyle="width:40px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						注册日期：
						<s:textfield name="adminUserVO.regeditStartDate" id="startDate"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							readonly="true" cssStyle="width:80px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.regeditEndDate" id="endDate"
							readonly="true"
							onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
							cssStyle="width:80px">
						</s:textfield>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						电子邮件：
						<s:textfield name="adminUserVO.email" cssStyle="width:80px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：
						<s:textfield name="adminUserVO.telphone" maxlength="11"
							cssStyle="width:60px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						推广积分：
						<s:textfield name="adminUserVO.startSpreadScore" id="startDate"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endSpreadScore" id="endDate"
							cssStyle="width:40px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						发任务数：
						<s:textfield name="adminUserVO.startReleaseTaskCount"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endReleaseTaskCount"
							cssStyle="width:40px">
						</s:textfield>
					</td>

				</tr>
				<tr>
					<td nowrap="nowrap">
						接任务数：
						<s:textfield name="adminUserVO.startReceieveTaskCount"
							cssStyle="width:40px">
						</s:textfield>
						至
						<s:textfield name="adminUserVO.endReceieveTaskCount;"
							cssStyle="width:40px">
						</s:textfield>
					</td>
					<td nowrap="nowrap">
						是&nbsp;否&nbsp;VIP：
						<s:select listKey="key" listValue="value"
							name="adminUserVO.vipEnable" headerKey="" headerValue="--请选择--"
							list="#{'true':'是','false':'不是'}">
						</s:select>
					</td>
					<td nowrap="nowrap">
						状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：
						<s:select listKey="key" listValue="value"
							name="adminUserVO.status" headerKey="" headerValue="--请选择--"
							list="#{'0':'未激活','1':'正常','2':'冻结','3':'找密码中'}">
						</s:select>
					</td>
					<td nowrap="nowrap">
						<input type="submit" value="查&nbsp;&nbsp;询"
							style="cursor: pointer;">
						<input type="button" value="删&nbsp;&nbsp;除" onclick="deleteUser()"
							style="cursor: pointer; color: red">
						<input type="button" value="邮&nbsp;&nbsp;件" onclick="openMsgDiv()"
							style="cursor: pointer; color: red">
						<input type="button" value="站内信" onclick="openSmsDiv()"
							style="cursor: pointer; color: red">
					</td>
				</tr>
				<tr>

				</tr>
			</table>
			<br>
			<table width="100%" cellpadding="1" id="myTable" class="tablesorter"
				style="font-size: 12px;" style="table-layout: fixed;">
				<thead>
					<tr>
						<th nowrap="nowrap" style="font-size: 12px;">
							<input type="checkbox" onclick="selectAll(this,'selectUserName')">
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							用户名
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							状态
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							状态描述
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							余额
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发布点
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							注册时间
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							电子邮件
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							手机
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							发任务数
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							接任务数
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							是否VIP
						</th>
						<th nowrap="nowrap" style="font-size: 12px;">
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="#request.result" id="user">
						<tr>
							<td>
								<input name="userIDs" type="checkbox"
									value="<s:property value="#user[12]" />"
									selectUserName="selectUserName" />
							</td>
							<td title="<s:property value="#user[0]" />">
								<s:property value="#user[0]" />
							</td>
							<td
								title="
								<s:if test="#user[11]==0">
									未激活
								</s:if>
								<s:elseif test="#user[11]==1">正常</s:elseif>
								<s:elseif test="#user[11]==2">冻结</s:elseif>
								<s:elseif test="#user[11]==3">找密码</s:elseif>
							">
								<s:if test="#user[11]==0">
									未激活
								</s:if>
								<s:elseif test="#user[11]==1">正常</s:elseif>
								<s:elseif test="#user[11]==2">冻结</s:elseif>
								<s:elseif test="#user[11]==3">找密码</s:elseif>
							</td>
							<td title="<s:property value="#user[13]" />">
								<s:property value="#user[13]" />
							</td>

							<td title="<s:property value="#user[2]" />">
								<s:property value="#user[2]" />
							</td>
							<td title="<s:property value="#user[1]" />">
								<s:property value="#user[1]" />
							</td>
							<td title="<s:date name="#user[3]" format="yyyy-MM-dd" />">
								<s:date name="#user[3]" format="yyyy-MM-dd" />
							</td>
							<td title="<s:property value="#user[4]" />">
								<s:property value="#user[4]" />
							</td>
							<td title="<s:property value="#user[5]" />">
								<s:property value="#user[5]" />
							</td>
							<td title="<s:property value="#user[8]" />">
								<s:property value="#user[8]" />
							</td>
							<td title="<s:property value="#user[9]" />">
								<s:property value="#user[9]" />
							</td>
							<td
								title="
								<s:if test="#user[10]">
									是
								</s:if>
								<s:else>
									否
								</s:else>
							">
								<s:if test="#user[10]">
									是
								</s:if>
								<s:else>
									否
								</s:else>
							</td>
							<td align="center">
								<s:if test="#user[11]==0">
									<a
										href="javascript:updateStatus('<s:property value="#user[12]" />','1')">激活</a>
								</s:if>
								<s:elseif test="#user[11]==2">
									<a
										href="javascript:updateStatus('<s:property value="#user[12]" />','1')">解冻</a>
								</s:elseif>
								<s:elseif test="#user[11]==1">
									<a
										href="javascript:updateStatus('<s:property value="#user[12]" />','2')">冻结</a>
								</s:elseif>
								<br>
								<a
									href="javascript:addMoney('<s:property value="#user[12]" />')">充值金额</a>
								<br>
								<a
									href="javascript:addReleaseDot('<s:property value="#user[12]" />')">充值发布点</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<s:if test="#request.result.size()==0">
					<tr>
						<th colspan="13" align="center">
							没有用户！
						</th>
					</tr>
				</s:if>
				<s:else>
					<tfoot>
						<tr>
							<th colspan="13" style="font-size: 12px;">
								<div style="float: left;">
									<a href="javascript:firstPage()">首页</a>
									<a href="javascript:prevPage()">上一页</a>&nbsp;
									<a href="javascript:nextPage()">下一页</a>&nbsp;
									<a href="javascript:lastPage()">尾页</a>&nbsp; 当前记录数:
									<s:property value="adminUserVO.dataCount" />
									&nbsp;
									<s:property value="adminUserVO.nowPage" />
									/
									<s:property value="adminUserVO.pageCount" />
									&nbsp; 每页显示：
									<s:textfield name="adminUserVO.eachPage" cssStyle="width:40px"></s:textfield>
									&nbsp;
								</div>
								<div style="float: left;">
									跳转到
									<select id='toPageSelect' size='1' onchange="jumpPage()">
										<s:iterator begin="1" end="adminUserVO.pageCount" step="1"
											var="index">
											<option value="<s:property value="#index" />">
												第
												<s:property value="#index" />
												页
											</option>
										</s:iterator>
									</select>
								</div>
								<input type="hidden" name="adminUserVO.nowPage"
									value="<s:property
											value="adminUserVO.nowPage" />"
									id="nowPage">
								<input type="hidden"
									value="<s:property
										value="adminUserVO.pageCount" />"
									id="pageCount">
							</th>
						</tr>
					</tfoot>
				</s:else>
			</table>
		</s:form>
		<div id="updateMoneyDIV" title="充值金额">
			<s:form action="adminUserManager/adminUser!addMoney.php"
				id="moneyForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							金额：
						</td>
						<td>
							<input type="text" name="money" id="moneyId" style="width: 80px">
							<input type="hidden" name="userId" id="moneyUserIdId">
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							描述：
						</td>
						<td>
							<input type="text" name="moneyDesc" id="moneyDescID"  
								maxlength="200" style="width: 400px">
						</td>

					</tr>
				</table>
			</s:form>
		</div>

		<div id="updateReleaseDotDIV" title="充值发布点">
			<s:form action="adminUserManager/adminUser!addReleaseDot.php"
				id="releaseDotForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							发布点：
						</td>
						<td>
							<input type="text" name="releaseDot" id="releaseDotId"
								style="width: 80px">
							<input type="hidden" name="userId" id="releaseDotUserIdId">
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							描述：
						</td>
						<td>
							<input type="text" name="releaseDotDesc" id="releaseDotDescID"
								maxlength="200" style="width: 400px">
						</td>

					</tr>
				</table>
			</s:form>
		</div>

		<div id="updateStatusDIV" title="状态切换">
			<s:form action="adminUserManager/adminUser!updateStatus.php"
				id="statusForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							描述：
						</td>
						<td>
							<input type="text" name="statusDesc" id="statusDescID"
								id" style="width: 200px" maxlength="255">
							<input type="hidden" name="userId" id="useForUpdateStatusId">
							<input type="hidden" name="status" id="statusID">
						</td>
					</tr>
				</table>
			</s:form>
		</div>

		<!--  邮件 -->
		<div id="sendMailDIV" title="发送邮件">
			<s:form action="adminUserManager/adminUser!sendMail.php"
				id="sendMailForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							主题：
						</td>
						<td>
							<input name="mailSubjct" id="mailSubjctID">
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							邮件内容：
						</td>
						<td>
							<textarea name="mailContent" id="mailContentID"></textarea>
							<input type="hidden" name="userIdses" id="userIdsesId">
						</td>
					</tr>
				</table>
			</s:form>
		</div>


		<!--  站内信 -->
		<div id="sendSmsDIV" title="发站内信">
			<s:form action="adminUserManager/adminUser!sendSms.php"
				id="sendSmsForm" theme="simple">
				<table cellpadding="0" cellspacing="0" border="0">
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							标题：
						</td>
						<td>
							<input name="smsTitle" id="smsTitleId" maxlength="50">
						</td>
					</tr>
					<tr class="sellerClass">
						<td valign="middle" nowrap="nowrap">
							内容：
						</td>
						<td>
							<textarea name="smsContent" cols="20" rows="3" id="smsContentID"></textarea>
							<input type="hidden" name="userSmsIdses" id="userIdsesSmsId">
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
