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
		<div align="center" id="partdiv">
			<div align="center">
				<DIV
					style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; OVERFLOW: hidden; WIDTH: 910px">
					<DIV
						style="CLEAR: both; MARGIN-TOP: 2px; WIDTH: 910px; HEIGHT: 25px">
						<DIV style="FLOAT: left; OVERFLOW: hidden; WIDTH: 700px">
							<UL id=task_nav>
								<LI>
									<A
										style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
										href="index.asp">淘宝互动区</A>
								</LI>
								<LI>
									<A href="pushmission.asp">发布任务</A>
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
				<DIV style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 910px">
					<DIV
						style="BACKGROUND: url(images/task_bg_01.jpg) repeat-x 50% top; WIDTH: 910px; HEIGHT: 38px">
						<DIV style="MARGIN-TOP: 10px; FLOAT: left">
							<IMG src="images/task_02.gif">
						</DIV>
						<DIV style="MARGIN-TOP: 12px; FLOAT: left; MARGIN-LEFT: 10px">
							<A href="?sort=0"><SPAN class=anniu>全部等待接手任务</SPAN> </A>
							<A href="?sort=1"><SPAN class=anniu>价高排列</SPAN> </A>
							<A href="?sort=2"><SPAN class=anniu>价低排列</SPAN> </A>
							<A href="?sort=3"><SPAN class=anniu>1-30元区</SPAN> </A>
							<A href="?sort=4"><SPAN class=anniu>31-100元区</SPAN> </A>
							<A href="?sort=5"><SPAN class=anniu>101元以上区</SPAN> </A>
						</DIV>
						<DIV style="CLEAR: right; MARGIN-TOP: 12px; FLOAT: right">
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
									<IMG src="images/task_01.gif">
									任务编号
								</DIV>
								<DIV class=c_01 style="WIDTH: 15%">
									<IMG src="images/task_01.gif">
									发布人
								</DIV>
								<DIV class=c_01 style="WIDTH: 15%">
									<IMG src="images/task_01.gif">
									任务价格
								</DIV>
								<DIV class=c_01 style="WIDTH: 15%">
									<IMG src="images/task_01.gif">
									评价方式
								</DIV>
								<DIV class=c_01 style="WIDTH: 20%">
									<IMG src="images/task_01.gif">
									任务状态
								</DIV>
								<DIV class=c_01 style="CLEAR: right; WIDTH: 20%">
									<IMG src="images/task_01.gif">
									操&nbsp;&nbsp;作
								</DIV>
							</DIV>
						</DIV>
					</DIV>
				</DIV>
				<DIV id=missionset
					style="MARGIN-TOP: 0px; BACKGROUND: #ffffff; WIDTH: 950px"></DIV>

				<DIV class=tt5 style="BACKGROUND-COLOR: #cde0ee">
					<TABLE style="MARGIN: 3px" cellSpacing=2 cellPadding=0 width="100%"
						border=0>
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