<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<link href="css/union.css" type="text/css" rel="stylesheet" />
		<link href="css/unionCss.css" type="text/css" rel="stylesheet" />
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<SCRIPT src="js/jieducm_pupu.js" type="text/javascript"></SCRIPT>
		<style type="text/css">
.STYLE5 {
	font-size: 25px
}

.STYLE6 {
	font-size: 14px;
	font-weight: bold;
}
</style>
	</HEAD>
	<BODY>
		<s:include value="../common/title.jsp"></s:include>
		<table width="960" border="0" align="center">
			<tr>
				<td width="209">
					快速获得推广链接，直接进行推广：
				</td>
				<td width="741">
					<label>
						<input name="page_url" type="text"
							value="http://www.2000w.net/register.asp?promotion=xgj1988"
							size="80" style="height: 20px;" />
						<input type="submit" name="Submit"
							style="height: 30px; width: 50px;" value="复制" />
					</label>
				</td>

			</tr>
		</table>
		<table width="960" height="140" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<img src="images/tui.jpg" width="960" height="176" />
				</td>
			</tr>
		</table>
		<table width="960" height="40" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tbody>
				<tr>
					<th width="157">
						<table width="303" height="31" border="0" cellpadding="0"
							cellspacing="1" bgcolor="#D0DBE3">

							<tr>
								<td bgcolor="#F0F7FB">
									<div align="center">
										文字推广
									</div>
								</td>
								<td bgcolor="#F0F7FB">
									<div align="center">
										图片推广
									</div>
								</td>
								<td bgcolor="#F0F7FB">
									<div align="center">
										邮件推广
									</div>
								</td>
							</tr>
						</table>
					</th>
				</tr>

			</tbody>
		</table>
		<table width="960" border="0" align="center" cellpadding="10"
			cellspacing="0" bgcolor="#F0F7FB" class="border_hui margin-bottom">
			<tbody>
				<tr>
					<th width="157">
						<img height="22" alt="" src="images/union_01.gif" width="25"
							align="absmiddle" />
						<a>文字代码&nbsp;<img src="images/3jiaoxia.gif" border="0" /> </a>
					</th>
					<td width="595">
						适合发给QQ/MSN好友、个人坛签名或个人博客
					</td>
				</tr>

				<tr>
					<td colspan="2">
						<table cellpadding="10" class="getCode" id="type_a">
							<tbody>
								<tr>
									<td class="em" width="31">
										<em>1</em>
									</td>
									<td width="453">
										样式一
									</td>
									<td width="252">
										发给你的QQ/MSN好友
									</td>

								</tr>
								<tr>
									<td class="em"></td>
									<td>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="Input" id="url001" style="width: 400px"
											value="http://www.2000w.net/register.asp?promotion=xgj1988"
											size="46" />
									</td>
									<td>
										<input name="submit" type="submit" class="button"
											onClick=JM_cc( "url001") value="复制" />

									</td>
								</tr>
								<tr>

									<td colspan="3">
										<hr />
									</td>
								</tr>
								<tr>
									<td class="em">
										<em>2</em>
									</td>
									<td>
										样式二
									</td>
									<td>
										适合空间/博客友情链接 或论坛签名
									</td>

								</tr>
								<tr>
									<td class="em"></td>
									<td>
										HTML:
										<input name="Input" id="url002" style="width: 400px"
											value="&lt;a href='http://www.2000w.net/register.asp?promotion=xgj1988' target='_blank'&gt;xgj1988&lt;/a&gt;"
											size="46" />
										<br />
										<br />
										UBB:&nbsp;&nbsp;&nbsp;&nbsp;
										<input name="Input" id="url0022" style="width: 400px"
											value="[url=http://www.2000w.net/register.asp?promotion=xgj1988]xgj1988[/url]"
											size="46" />

										<br />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
									<td valign="top">
										<input name="submit" type="submit" class="button"
											onClick=JM_cc( "url002") value="复制" />
										<br />
										<br />
										<input name="submit" type="submit" class="button"
											onClick=JM_cc( "url0022") value="复制" />
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<hr />
									</td>

								</tr>
								<tr>
									<td class="em">
										<em>3</em>
									</td>
									<td>
										样式三
									</td>
									<td>
										适合空间/博客友情链接 或论坛签名
									</td>
								</tr>
								<tr>

									<td class="em"></td>
									<td>
										HTML:
										<input name="Input" id="url003" style="width: 400px"
											value="&lt;a href='http://www.2000w.net/register.asp?promotion=xgj1988' target='_blank'&gt;xgj1988&lt;/a&gt;"
											size="46" />
										<br />
										<br />
										UBB:&nbsp;&nbsp;&nbsp;&nbsp
										<input name="Input" id="url0032" style="width: 400px"
											value="[url=http://www.2000w.net/register.asp?promotion=xgj1988]xgj1988[/url]"
											size="46" />
										<br />
										&nbsp;&nbsp;&nbsp;
									</td>

									<td valign="top">
										<input name="submit" type="submit" class="button"
											onClick=JM_cc( "url003") value="复制" />
										<br />
										<br />
										<input name="submit" type="submit" class="button"
											onClick=JM_cc( "url0032") value="复制" />
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" height="100" cellspacing="0" cellpadding="0"
			border="0" background="images/tg_bg.jpg">
			<tbody>
				<tr>
					<td valign="top" height="100" class="wenzi">
						<div align="center">
							<a href="../help/about.asp"><font color="#666666">关于我们</font>
							</a> |
							<a href="../help/pay_introduce.asp"><font color="#666666">汇款方式</font>
							</a> |
							<a href="../help/link.asp"><font color="#666666">友情链接</font>
							</a> |
							<a href="../help/lianxi.asp"><font color="#666666">联系我们</font>
							</a>|
							<a href="../help/index.asp"><font color="#666666">帮助中心</font>
							</a>
							<br>
							QQ：
							<a href="tencent://message/?uin=904555181">904555181</a> 电话：
							<br>
							Copyright 2008 - 2009 版权所有 沪ICP备09035337号
							<br>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</BODY>
</HTML>
