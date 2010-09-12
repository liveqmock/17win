<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
							<A href="?sort=2"><SPAN class=anniu>价低排列</SPAN> </A>
							<A href="?sort=1"><SPAN class=anniu>价高排列</SPAN> </A>
							<A href="?sort=3"><SPAN class=anniu>1-40元区</SPAN> </A>
							<A href="?sort=4"><SPAN class=anniu>40-100元区</SPAN> </A>
							<A href="?sort=5"><SPAN class=anniu>100-200元区</SPAN> </A>
							<A href="?sort=5"><SPAN class=anniu>200-500元区</SPAN> </A>
							<A href="?sort=5"><SPAN class=anniu>500元以上区</SPAN> </A>
						</DIV>
						<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
							自定义时间
							<input type="text" value="" style="width: 40px"
								title="不填写或则0表示不自动刷新" />
							秒
							<A title=点击刷新 href="javascript:location.reload(true);"
								class="yell_font"> <SPAN class=anniu>刷新页面 任务不断跳出</SPAN> </A>
						</DIV>
					</DIV>
				</DIV>
				<DIV style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
					<DIV style="CLEAR: both; WIDTH: 930px; BACKGROUND-COLOR: #ffffff">
						<DIV style="CLEAR: both; WIDTH: 100%">
							<DIV style="CLEAR: both; WIDTH: 100%; HEIGHT: 45px">
								<DIV class=c_01 style="WIDTH: 15%">
									任务编号
								</DIV>
								<DIV class=c_01 style="WIDTH: 15%">
									发布人
								</DIV>
								<DIV class=c_01 style="WIDTH: 15%">
									任务价格
								</DIV>
								<DIV class=c_01 style="WIDTH: 15%">
									评价方式
								</DIV>
								<DIV class=c_01 style="WIDTH: 20%">
									任务状态
								</DIV>
								<DIV class=c_01 style="CLEAR: right; WIDTH: 20%">
									操&nbsp;&nbsp;作
								</DIV>
							</DIV>
						</DIV>
					</DIV>
				</DIV>

				<DIV class="tt5">
					<TABLE style="MARGIN: 3px; background: #FFC278" cellSpacing=2
						cellPadding=0 width="100%" border=0>
						<TBODY>
							<TR>
								<TD width="15%">
									201082722162223014
									<BR>
									2010-8-27 22:36:36
								</TD>
								<TD>
								<TD align=middle width="15%">
									<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
										href="../user/send_message.asp?sendname=shengjun0911"
										title="发送站内信息" target="_blank">shengjun0911</a> </SPAN>(
									<font color=red>在线</font>)

									<BR>
									<img src=images/xin3.gif alt=刷客经验积分：720>
								</TD>
								<TD align=middle width="15%">
									平台担保
									<img src="images/zf.gif" width="13" height="16">
									40元
									<br>
									金额相等
								</TD>
								<TD align=middle width="15%">
									一天后收货好评
									<BR>
									<IMG alt=延迟收货 src=images/shiwu.gif>
									发布点2个
								</TD>
								<TD style="PADDING-LEFT: 50px" width="20%">
									<B style="COLOR: red"> 等待接手人... </B>
								</TD>
								<TD style="PADDING-LEFT: 50px" width="20%">
									<img src="images/online_admin.gif">
									<a title="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
										onClick="showxiao('201082722162223014','2')"> <span
										class="anniu">接手任务</span> </a>
								</TD>
							</TR>
						</TBODY>
					</TABLE>

					<TABLE style="MARGIN: 3px; background: #FFC278" cellSpacing=2
						cellPadding=0 width="100%" border=0>
						<TBODY>
							<TR>
								<TD width="15%">
									201082722162223014
									<BR>
									2010-8-27 22:36:36
								</TD>
								<TD>
								<TD align=middle width="15%">
									<SPAN style="Z-INDEX: 20; POSITION: relative"> <a
										href="../user/send_message.asp?sendname=shengjun0911"
										title="发送站内信息" target="_blank">shengjun0911</a> </SPAN>(
									<font color=red>在线</font>)

									<BR>
									<img src=images/xin3.gif alt=刷客经验积分：720>
								</TD>
								<TD align=middle width="15%">
									平台担保
									<img src="images/zf.gif" width="13" height="16">
									40元
									<br>
									金额相等
								</TD>
								<TD align=middle width="15%">
									一天后收货好评
									<BR>
									<IMG alt=延迟收货 src=images/shiwu.gif>
									发布点2个
								</TD>
								<TD style="PADDING-LEFT: 50px" width="20%">
									<B style="COLOR: red"> 等待接手人... </B>
								</TD>
								<TD style="PADDING-LEFT: 50px" width="20%">
									<img src="images/online_admin.gif">
									<a title="接手，并完成任务可获得存款和发布点" style="CURSOR: pointer"
										onClick="showxiao('201082722162223014','2')"> <span
										class="anniu">接手任务</span> </a>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</DIV>
				<DIV class=tt5>
					共
					<font color=blue><b>100</b> </font> 条主题&nbsp;&nbsp;&nbsp;首页
					上一页&nbsp;
					<a href='index.asp?PageNo=2'>下一页</a>&nbsp;
					<a href='index.asp?PageNo=7'>尾页</a>&nbsp;页次：
					<strong><font color="red">1</font>/7</strong>页 &nbsp;
					<b>15</b>条主题/页&nbsp;转到：
					<select name='page' size='1'
						onchange="javascript:window.location='index.asp?PageNo='+this.options[this.selectedIndex].value;">
						<option value='1'>
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
						<option value='5'>
							第5页
						</option>
						<option value='6'>
							第6页
						</option>
						<option value='7'>
							第7页
						</option>
					</select>
				</div>
			</div>
		</div>
		<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>