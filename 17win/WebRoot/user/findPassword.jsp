<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
	<HEAD>
		<s:include value="../common/header.jsp"></s:include>
		<LINK href="css/index.css" type=text/css rel=stylesheet>
		<LINK href="css/Css.css" type=text/css rel=stylesheet>
		<LINK href="css/top_bottom.css" type=text/css rel=stylesheet>
		<script src="user/findPassword.js" type="text/javascript"></script>
	</HEAD>
	<BODY>
		<s:form action="userManager/base!findPassword.php" theme="simple"
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
								找回密码
							</DIV>
							<BR>
							<DIV style="COLOR: #999900">
								<FONT color=#ff0000>请输入6至20位字符的新密码。</FONT>
							</DIV>
							<BR>
							<DIV style="COLOR: red">
								<SPAN id=ctl00_AllBaseContentPlaceHolder_MsgLabel
									style="COLOR: red"></SPAN>
							</DIV>
							<BR>
							<DIV>
								操作密码：
								<s:password id="opertationCode" name="userVO.userEntity.opertationCode"
									maxLength="16"></s:password>
								<br>
								确认密码：
								<input type="password" id="reOpertationCode" maxLength="16">
								<br>登录密码：
								<s:password id="password" name="userVO.userEntity.loginPassword"
									maxLength="16"></s:password>
								<s:hidden name="userVO.userEntity.username"></s:hidden>
								<br>
								确认密码：
								<input type="password" id="rePassword" maxLength="16">
								<input type="hidden" name="preURL" size="30"
									value="<%="?" + request.getQueryString()%>"
									style="width: 210px">

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
