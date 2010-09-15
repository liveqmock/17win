<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<s:include value="../common/task/title.jsp"></s:include>
		<s:form action="taskManager/task!initTask.php" theme="simple"
			onsubmit="return validateForm()">
			<div align="center" id="partdiv">
				<div align="center">
					<DIV
						style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
						<!-- xgj navigation.jsp -->
						<s:include value="../common/task/navigation.jsp"></s:include>
					</DIV>
					<DIV style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
						<DIV
							style="BACKGROUND: url(images/task_bg_01.jpg) repeat-x 50% top; WIDTH: 910px; HEIGHT: 38px">
							<DIV style="MARGIN-TOP: 10px; FLOAT: left">
								<IMG src="images/task_02.gif">
							</DIV>
							<DIV style="MARGIN-TOP: 12px; FLOAT: left; MARGIN-LEFT: 10px">
								<A href="?sort=2"><SPAN class=anniu>全部任务</SPAN> </A>
								<A href="?sort=1"><SPAN class=anniu>等待支付</SPAN> </A>
								<A href="?sort=3"><SPAN class=anniu>等待发货</SPAN> </A>
								<A href="?sort=4"><SPAN class=anniu>等待好评</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>等待完成</SPAN> </A>
								<A href="?sort=5"><SPAN class=anniu>已完成</SPAN> </A>
							</DIV>
							<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
								自定义刷新时间
								<input type="text" value="" style="width: 40px"
									title="不填写或则0表示不自动刷新" />
								秒
								<A title=点击刷新 href="javascript:location.reload(true);"
									class="yell_font"> <SPAN class=anniu>刷新页面 任务不断跳出</SPAN> </A>
							</DIV>
						</DIV>
					</DIV>
					<DIV
						style="CLEAR: both; BORDER-RIGHT: #abbec8 1px solid; BORDER-TOP: #abbec8 1px solid; MARGIN-TOP: 0px; BORDER-LEFT: #abbec8 1px solid; WIDTH: 910px; BORDER-BOTTOM: #abbec8 1px solid; BACKGROUND-COLOR: #e6f7fb">
						<DIV
							style="CLEAR: both; WIDTH: 98%; LINE-HEIGHT: 35px; HEIGHT: 35px">
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 145px; COLOR: #006600; TEXT-ALIGN: center">
								任务编号
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 125px; COLOR: #006600; TEXT-ALIGN: center">
								价格
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 95px; COLOR: #006600; TEXT-ALIGN: center">
								商品信息
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 120px; COLOR: #006600; TEXT-ALIGN: center">
								17win号
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 115px; COLOR: #006600; TEXT-ALIGN: center">
								接受号
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 130px; COLOR: #006600; TEXT-ALIGN: center">
								状&nbsp;&nbsp;态
							</DIV>
							<DIV
								style="FONT-WEIGHT: bold; FONT-SIZE: 14px; FLOAT: left; WIDTH: 150px; COLOR: #006600; TEXT-ALIGN: center">
								操&nbsp;&nbsp;作
							</DIV>
						</DIV>
					</DIV>
					<DIV
						style="CLEAR: both; BORDER-RIGHT: #abbec8 1px solid; BORDER-TOP: #abbec8 1px; BORDER-LEFT: #abbec8 1px solid; WIDTH: 910px; BORDER-BOTTOM: #abbec8 1px solid; BACKGROUND-COLOR: #ffffff">
						<DIV class=missionbg
							style="WIDTH: 98%; PADDING-TOP: 10px; BORDER-BOTTOM: #06314a 1px dashed; HEIGHT: 90px">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td width="145px" align="left">
										2010915238272637
									</td>
									<td width="125px">
										<img src="images/j_z.gif" width="13" height="16">
										35元（需改价格）
									</td>
									<td width="130px" nowrap="nowrap">
										<input name="2010915238272637" type="test"
											title="http://item.taobao.com/item.htm?id=7373791436"
											id="2010915238272637" style="width: 60px"
											value="http://item.taobao.com/item.htm?id=7373791436" />
										<input type="button" value="GO">
									</td>
									<td width="120px" align="left">
										<a href="../user/send_message.asp?sendname=有来有往"
											title="发送站内信息" target="_blank">有来有往（<font color=red>在线</font>）</a>
									</td>
									<td   style="width: 115px">
										<font color="#FF0000">爱上996</font>
									</td>

									<td width="130px">
										等待卖家发货...
									</td>
									<td width="150px">
										<a href="#1"
											onClick="javascript:showDialog('你确认要进行已发货处理吗？\n认真核对支付方是否支付成功,并且你已发货处理！',1,7,'jieducm_fahou.asp?id=2010915238272637')"
											title="认真核对支付方是否支付成功,并且你已发货处理！"> <SPAN class=anniu>我已发货</SPAN>
										</A>
									</td>
								</tr>
								<tr>
									<td width="145px" align="left">
										2010-9-15 23:19:11
									</td>
									<td width="115px">
										<IMG alt=延迟收货 src=images/shiwu.gif>
										发布点2个
									</td>
									<td width="130px" nowrap="nowrap">
										<font color="#FF0000">掌柜:xgj1988</font>
									</td>
									<td width="120px" align="left">
										<a href="tencent://message/?uin=1317225767"><img
												src="http://wpa.qq.com/pa?p=1:1317225767:41" border="0" />
										</a>
									</td>
									<td width="115px">
										<img src="images/xin_1.gif" alt=刷客经验积分：2779>
									</td>
									<td width="130px">

									</td>
									<td width="150px">
										核实后请发货并平台处理！
									</td>
								</tr>
								<tr>
									<td colspan="3" align="left">
										<font color="red" style="font-weight: bold;">快递单号：</font>
									</td>
									<td colspan="4" align="left">
										<font color="red" style="font-weight: bold;">打分/好评：</font>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="left">
										<font color="red" style="font-weight: bold;">收货地址：</font>
									</td>
									<td colspan="4" align="left">
										<font color="red" style="font-weight: bold;">详细描述：</font>
									</td>
								</tr>
							</table>
						</DIV>

						<DIV
							style="WIDTH: 98%; LINE-HEIGHT: 40px; PADDING-TOP: 10px; HEIGHT: 40px; TEXT-ALIGN: center">
							共
							<font color="blue"><b>34</b> </font> 条主题&nbsp;&nbsp;&nbsp;首页
							上一页&nbsp;
							<a href='mymission.asp?PageNo=2'>下一页</a>&nbsp;
							<a href='mymission.asp?PageNo=4'>尾页</a>&nbsp;页次：
							<strong><font color=red>1</font>/4</strong>页 &nbsp;
							<b>10</b>条主题/页&nbsp;转到：
							<select name='page' size='1'
								onchange="javascript:window.location='mymission.asp?PageNo='+this.options[this.selectedIndex].value;">
								<option value='1' selected="selected">
									第1页
								</option>
								<option value='2'>
									第2页
								</option>
								<option value='3'>
									第3页
								</option>
								<option value='4'>
									第4页
								</option>
							</select>
						</DIV>
					</div>
				</DIV>
			</div>
		</s:form>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>