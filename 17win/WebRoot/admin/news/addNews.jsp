<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<s:include value="/admin/common/header.jsp"></s:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<SCRIPT type="text/javascript" src="paid/paidIndex.js"></SCRIPT>
	</head>

	<body>
		<s:form action="adminPaidManager/adminPaid!queryPay.php"
			theme="simple">
			<table cellpadding="1" cellspacing="1" border="0px">
				<tr>
					<td nowrap="nowrap">
						类型：
					</td>
					<td>
						<select>
							<option>
								测试
							</option>
						</select>
						<input type="button" value="类别管理">
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						标题：
					</td>
					<td>
						<select>
							<option>
								测试
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						内容：
					</td>
					<td>
						<textarea rows="" cols=""></textarea>
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap">
						<input type="submit" value="提交">
					</td>
					<td>
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
