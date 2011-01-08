<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
		<META HTTP-EQUIV="Expires" CONTENT="0">
		<s:property value="#request.msg" escape="false" />
		<s:if test="#request.jumpPageType=='selfPage'">
			<script language="javascript"> 
			window.open("<%=basePath%><s:property value="#request.jump" />","_self");
			</script>
		</s:if>
		<s:elseif test="#request.jumpPageType=='outterPage'">
			<script language="javascript">
			window.open("<s:property value="#request.jump" />","_self");
			</script>
		</s:elseif>
		<s:if test="#request.closeed!=null && #request.closeed">
			<script language="javascript">
			window.opener.location.href=window.opener.location.href;window.close();
			window.opener.location.replace(window.opener.document.referrer);window.close();
			window.open('','_parent','');
            window.top.opener = null;
            window.close();
            </script>
		</s:if>
	</head>
</html>

