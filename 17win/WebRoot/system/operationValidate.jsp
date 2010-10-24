<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<script src="system/operationValidate.js" type="text/javascript"></script>
		<SCRIPT src="js/jieducm_pupu.js" type=text/javascript></SCRIPT>
	</HEAD>
	<BODY>
		<s:form action="commonManager/common!activateOperattionCode.php"
			theme="simple" onsubmit="return validateForm()">
			<s:include value="../common/title.jsp"></s:include>
			<div align="center">
				<DIV
					style="MARGIN-BOTTOM: 10px; WIDTH: 910px; PADDING-TOP: 15px; BACKGROUND-COLOR: #ffffff">
					<DIV></DIV>

					<DIV style="WIDTH: 910px; TEXT-ALIGN: center">
						<DIV
							style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 20px; WIDTH: 700px; PADDING-TOP: 20px; TEXT-ALIGN: left">
							<DIV style="FONT-WEIGHT: bolder; FONT-SIZE: 18px; COLOR: red">
								输入操作密码
							</DIV>
							<BR>
							<DIV style="COLOR: #999900">
								<FONT color=#ff0000>你必须输入操作密码才能继续操作，每次登陆都要输入一次才能发布任务，提现，赠送发布点、修改个人资料</FONT>
							</DIV>
							<BR>
							<DIV style="COLOR: red">
								<SPAN id=ctl00_AllBaseContentPlaceHolder_MsgLabel
									style="COLOR: red"></SPAN>
							</DIV>
							<BR>
							<DIV>
								<P>
									操作密码：
								</P>
								<INPUT id="code" type="password" maxLength="16"
									name="opertationCode">
								<input type="hidden" name="preURL"
									value="<s:property value="#request.preURL"/>" />
								<SPAN style="COLOR: red">* 操作密码为6-16为数字,字符！</SPAN>
							</DIV>
							<BR>
							<DIV>
								<INPUT type="submit" value="提&nbsp;&nbsp;交">
							</DIV>
							<BR>
							<BR>
						</DIV>
					</DIV>
					<DIV>
					</DIV>
				</DIV>
			</div>
			<s:include value="../common/footDuan.jsp"></s:include>
		</s:form>
	</BODY>
</HTML>
