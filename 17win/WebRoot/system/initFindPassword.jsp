<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<script src="system/initFindPassword.js" type="text/javascript"></script>
	</HEAD>
	<BODY>
		<s:form action="userInfoManager/info!findPassword.php" theme="simple"
			onsubmit="return validateForm()">
			<s:include value="../common/title.jsp"></s:include>
			<div align="center">
				<DIV
					style="MARGIN-BOTTOM: 10px; WIDTH: 910px; PADDING-TOP: 15px; BACKGROUND-COLOR: #ffffff">
					<DIV></DIV>

					<DIV style="WIDTH: 910px; TEXT-ALIGN: center">
						<DIV
							style="PADDING-RIGHT: 20px; PADDING-LEFT: 20px; PADDING-BOTTOM: 20px; WIDTH: 700px; PADDING-TOP: 20px; TEXT-ALIGN: left">
							<DIV style="FONT-WEIGHT: bolder; FONT-SIZE: 18px; COLOR: red">
							</DIV>
							<BR>
							<DIV style="COLOR: #999900">
								<FONT color=#ff0000>请输入您的用户名，我们会发送邮件到您的邮箱里面。如果有疑问请联系我们的客户。</FONT>
							</DIV>
							<BR>
							<DIV style="COLOR: red">
								<SPAN id=ctl00_AllBaseContentPlaceHolder_MsgLabel
									style="COLOR: red"></SPAN>
							</DIV>
							<BR>
							<DIV>
								<P>
								</P>
								<INPUT id="username" type="text" maxLength="12"
									name="userVO.username">
							</DIV>
							<BR>
							<DIV>
								<INPUT type="submit" value="提&nbsp;&nbsp;交" id="submitBtn">
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
