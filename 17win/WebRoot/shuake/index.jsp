<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:if test="#request.autoRefresh!=null">
			<meta http-equiv="refresh"
				content="
			<s:property value="#request.autoRefresh" />">
		</s:if>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/header.css" type=text/css rel=stylesheet>
		<link href="css/excite-bike/jquery-ui-1.8.4.custom.css"
			rel="stylesheet" type="text/css" />
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type="text/css" rel="stylesheet">
		<link href="css/top.css" type="text/css" rel="stylesheet" />
		<SCRIPT src="js/jquery-ui-1.8.4.custom.min.js" type="text/javascript"></SCRIPT>
		<SCRIPT src="credit/task.js" type="text/javascript"></SCRIPT>
		<script type="text/javascript" src="js/utils.js">
		</script>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<DIV id=box>
			<DIV class=clear></DIV>
			<DIV id=contenter>
				<DIV class=block>
					<DIV class=block_top>
						任务发布排行榜
					</DIV>
					<DIV class=block_contenter>
						<DIV class=grid_box>
							<UL id=ax1>

								<LI>
									<a href="http://huomeng.taobao.com/"><SPAN
										style="text-decoration: underline">kennethlee </SPAN> </a>次数：94248
								</LI>

								<LI>
									<a href="http://gzytsm.taobao.com/"><SPAN
										style="text-decoration: underline">lyf403123</SPAN> </a>次数：89102
								</LI>

								<LI>
									<a href="http://shop33160081.taobao.com/"><SPAN
										style="text-decoration: underline">rickyzhou</SPAN> </a>次数：78992
								</LI>

								<LI>
									<a href="http://duoduoyun.taobao.com/"><SPAN
										style="text-decoration: underline">duoduo</SPAN> </a>次数：74021
								</LI>

								<LI>
									<a href="http://shop1450644.taobao.com/"><SPAN
										style="text-decoration: underline">xinlantou</SPAN> </a>次数：64993
								</LI>

								<LI>
									<a href="http://axixibag.taobao.com//"><SPAN
										style="text-decoration: underline">xiexiaojun</SPAN> </a>次数：63295
								</LI>

								<LI>
									<A href="http://buzhidao.taobao.com/"><SPAN
										style="text-decoration: underline">jackZhou</SPAN> </A>次数：42102
								</LI>

								<LI>
									<A HREF="http://jollyhome.taobao.com/"><SPAN
										style="text-decoration: underline">drg110</SPAN> </A>次数：40023
								</LI>

								<LI>
									<A HREF="http://momobag.taobao.com/"><SPAN
										style="text-decoration: underline">feiyang</SPAN> </A>次数：38304
								</LI>

								<LI>
									<a href="http://shop34246683.taobao.com/"><SPAN
										style="text-decoration: underline">nini4431</SPAN> </a>次数：35308
								</LI>


							</UL>
						</DIV>
						<DIV class=block_bottom></DIV>
					</DIV>
				</DIV>
				<DIV class=block>
					<DIV class=block_top>
						任务充值排行榜
					</DIV>
					<DIV class=block_contenter>
						<DIV class=grid_box>
							<UL id=ax1>

								<LI>
									<a href="http://huomeng.taobao.com/"><SPAN
										style="text-decoration: underline">kennethlee </SPAN> </a>金额：85250
								</LI>

								<LI>
									<a href="http://gzytsm.taobao.com/"><SPAN
										style="text-decoration: underline">lyf403123</SPAN> </a>金额：81230
								</LI>


								<LI> 
									<a href="http://duoduoyun.taobao.com/"><SPAN
										style="text-decoration: underline">duoduo</SPAN> </a>金额：74800
								</LI>

								<LI>
									<a href="http://shop33160081.taobao.com/"><SPAN
										style="text-decoration: underline">rickyzhou</SPAN> </a>金额：73200
								</LI>

								<LI>
									<a href="http://shop1450644.taobao.com/"><SPAN
										style="text-decoration: underline">xinlantou</SPAN> </a>金额：69200
								</LI>

								<LI>
									<a href="http://dytea.taobao.com/ "><SPAN
										style="text-decoration: underline">xwteaf</SPAN> </a>金额：67340
								</LI>
								<LI>
									<a href="http://shop34246683.taobao.com/"><SPAN
										style="text-decoration: underline">nini4431</SPAN> </a>金额：63250
								</LI>

								<LI>
									<A href="http://buzhidao.taobao.com/"><SPAN
										style="text-decoration: underline">jackZhou</SPAN> </A>金额：62050
								</LI>

								<LI>
									<A HREF="http://jollyhome.taobao.com/"><SPAN
										style="text-decoration: underline">drg110</SPAN> </A>金额：60050
								</LI>

								<LI>
									<A HREF="http://momobag.taobao.com/"><SPAN
										style="text-decoration: underline">feiyang</SPAN> </A>金额：43200
								</LI>


							</UL>
						</DIV>
						<DIV class=block_bottom></DIV>
					</DIV>
				</DIV>
				<s:include value="../common/footDuan.jsp"></s:include>
	</BODY>
</HTML>