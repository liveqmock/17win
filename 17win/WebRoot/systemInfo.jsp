<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'systemInfo.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<%
			Runtime lRuntime = Runtime.getRuntime();
			out.println("*** BEGIN MEMORY STATISTICS ***");
			out.println("</br>");
			out.println("JAVA虚拟机空闲内存: " + lRuntime.freeMemory() / 1024 / 1024
					+ "M");
			out.println("</br>");
			out.println("JAVA虚拟机已经使用内存: "
					+ ((lRuntime.totalMemory() / 1024 / 1024) - (lRuntime
							.freeMemory() / 1024 / 1024)) + "M");
			out.println("</br>");
			out.println("JAVA虚拟机从操作系统那里能获取到的最大内存数: " + lRuntime.maxMemory()
					/ 1024 / 1024 + "M");
			out.println("</br>");
			out.println("JAVA虚拟机已经从操作系统获取的最大内存数（JAVA虚拟机专用的所有内存）: " + lRuntime.totalMemory() / 1024 / 1024
					+ "M");
			out.println("</br>");
			out.println("CPU核数: " + lRuntime.availableProcessors() + "");
			out.println("</br>");
			out.println("*** END MEMORY STATISTICS ***");
		%>
	</body>
</html>
