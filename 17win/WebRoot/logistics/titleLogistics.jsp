<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
	<HEAD>
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
		<LINK href="css/style.css" type="text/css" rel="stylesheet">
		<LINK href="css/index.css" type="text/css" rel="stylesheet">
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<LINK href="css/Css.css" type="text/css" rel="stylesheet">

		<LINK href="css/center.css" type="text/css" rel="stylesheet">

		<LINK href="css/blue/style.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jquery.tablesorter.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/utils.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="js/validater.js" type="text/javascript"></SCRIPT>
		<script src="<%=basePath%>js/My97DatePicker/WdatePicker.js"
			type="text/javascript"></script>
		<SCRIPT src="logistics/titleLogistics.js" type="text/javascript"></SCRIPT>

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
		<s:form action="logisticsManager/logistics!queryLogisticsLog.php"
			onsubmit="return validateForm()" theme="simple">
			<table width="960" border="0" align="center">
				<tbody>
					<tr>
						<td height="5"></td>
					</tr>
				</tbody>
			</table>
			<div style="width: 960px">
				<div style="width: 100%">
					<div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red"><strong>下面的物流信息均是真实的物流信息。当第一次使用下面的物流信息时，将会支付0.2个发布点给提交人。由本站技术人员和淘宝工作的朋友分析得出，淘宝后台监控信誉系统会对一个卖家多个订单的发货信息进行定期到物流公司验证是否存在（相当于17WIN每天晚上会定期更行卖号的信誉一样的技术），所以多个订单最好不要重复使用同一个物流信息，但是不同的卖家可以使用同一个物流信息，因为这个淘宝查起来也不知道谁真谁假，况且目前我们的技术人员分析得出淘宝现在的信誉监控系统没有做到这一步，请大家放心。<br>
								&nbsp;&nbsp;&nbsp;&nbsp;大家如果对0.2发布点价格过高，或则过低，请于我们客户联系。我们会汇总大家的资料。达到一个平衡点，如果您分析很有道理。我们将以有偿的形式来感谢您的建议。</strong>
						</font>
					</div>
					<br>
					<table width="100%" cellpadding="1" cellspacing="1" border="0px"
						style="background: #DDEDFA">
						<tr>
							<td>
								发货日期：
								<s:textfield name="logisticsVO.startDate" id="startDate"
									readonly="true"
									onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
									cssStyle="width:110px">
								</s:textfield>
								至
								<s:textfield name="logisticsVO.endDate" id="endDate"
									readonly="true"
									onclick="WdatePicker({'isShowClear':true,dateFmt:'yyyy-MM-dd','skin':'blue'})"
									cssStyle="width:110px">
								</s:textfield>
							</td>
							<td>
								物流单号：
								<s:textfield name="logisticsVO.waybill"></s:textfield>
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								使用次数：
								<s:textfield name="logisticsVO.startUseCount" id="startUseCount"
									cssStyle="width:80px">
								</s:textfield>
								至
								<s:textfield name="logisticsVO.endUseCount" id="endUseCount"
									cssStyle="width:80px">
								</s:textfield>
							</td>
							<td>
								提&nbsp;&nbsp;交&nbsp;&nbsp;人：
								<s:textfield name="logisticsVO.releaseUsername"></s:textfield>
							</td>
							<td>
								<input type="submit" value="查&nbsp;&nbsp;询"
									style="cursor: pointer;">
							</td>
						</tr>
					</table>
					<br>
					<table id="myTable" class="tablesorter" cellpadding="1">
						<thead>
							<tr>
								<th style="font-size: 12px" nowrap="nowrap">
									提交人
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									物流公司
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									物流单号
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									发货时间
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									预计收货时间
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									发货信息
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									收货信息
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									使用次数
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									备注
								</th>
								<th style="font-size: 12px" nowrap="nowrap">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.result" id="logistics">
								<tr <s:if test="#status.odd"> style="background: #FFC278"</s:if>>
									<td>
										<s:property value="#logistics.releaseUsername" />
									</td>
									<td>
										<s:property value="#logistics.expressCompany" />
									</td>
									<td>
										********
									</td>
									<td>
										<s:date name="#logistics.sendDate" format="yyyy-MM-dd" />
									</td>
									<td>
										<s:date name="#logistics.arrivalDate" format="yyyy-MM-dd" />
									</td>
									<td>
										<s:property value="#logistics.releaseInfo" />
									</td>
									<td>
										<s:property value="#logistics.receieveInfo" />
									</td>
									<td>
										<s:property value="#logistics.useCount" />
									</td>
									<td>
										<s:property value="#logistics.remark" />
									</td>
									<td nowrap="nowrap">
										<s:if test="#session.userLogin==null">
											您还没登陆
										</s:if>
										<s:else>
											<s:if
												test="#session.userLogin.username==#logistics.releaseUsername">
											不能使用您自己的！
										</s:if>
											<s:else>
												<a style="color: red;"
													href="javascript:toUse('<s:property value="#logistics.id" />');">使用</a>
											</s:else>
										</s:else>
									</td>
								</tr>
							</s:iterator>
						</tbody>
						<s:if test="#request.result.size()==0">
							<tr>
								<th colspan="10" align="center">
									没有记录！
								</th>
							</tr>
						</s:if>
						<s:else>
							<tfoot>
								<tr>
									<th colspan="10">
										<div style="float: left;">
											<a href="javascript:firstPage()">首页</a>
											<a href="javascript:prevPage()">上一页</a>&nbsp;
											<a href="javascript:nextPage()">下一页</a>&nbsp;
											<a href="javascript:lastPage()">尾页</a>&nbsp;
										</div>
										<div style="float: left;">
											<s:property value="logisticsVO.nowPage" />
											/
											<s:property value="logisticsVO.pageCount" />
											跳转到
											<select id='toPageSelect' size='1' onchange="jumpPage()">
												<s:iterator begin="1" end="logisticsVO.pageCount" step="1"
													var="index">
													<option value="<s:property value="#index" />">
														第
														<s:property value="#index" />
														页
													</option>
												</s:iterator>
											</select>
										</div>
										<input type="hidden" name="logisticsVO.nowPage"
											value="<s:property
											value="logisticsVO.nowPage" />"
											id="nowPage">
										<input type="hidden"
											value="<s:property
										value="logisticsVO.pageCount" />"
											id="pageCount">
									</th>
								</tr>
							</tfoot>
						</s:else>
					</table>
				</div>
			</div>
			<s:include value="../common/footKuan.jsp"></s:include>
		</s:form>
	</BODY>
</HTML>
