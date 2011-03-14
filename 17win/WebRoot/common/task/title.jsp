<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
								<font color=#ff0000>${userLogin.username}</font>&nbsp; 您好！您拥有存款：
								<font color=#ff0000> <s:property
										value="#session.userLogin.money" /> </font> 元
							</div>
						</div>
					</div>
				</div>
			</div>
		</td>
	</tr>
</table>