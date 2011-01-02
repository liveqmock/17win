<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<DIV style="CLEAR: both; MARGIN-TOP: 2px; WIDTH: 910px; HEIGHT: 25px">
	<DIV style="FLOAT: left; OVERFLOW: hidden; WIDTH: 800px">
		<UL id=task_nav>
			<LI>
				<A
					<s:if test="#request.showTaskType==1">
					style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
					</s:if>
					href="taskManager/task!initTask.php?platformType=<s:property value="#request.platformType"/>"><s:property
						value="#request.platform" />互动区</A>
			</LI>
			<LI>
				<A
					<s:if test="#request.showTaskType==2">
					style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
					</s:if>
					href="taskManager/task!initReleaseTask.php?platformType=<s:property value="#request.platformType"/>">发布任务</A>
			</LI>
			<LI>
				<A
					<s:if test="#request.showTaskType==3">
					style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
					</s:if>
					href="taskManager/task!initReceivedTast.php?platformType=<s:property value="#request.platformType"/>">已接任务</A>
			</LI>
			<LI>
				<A
					<s:if test="#request.showTaskType==4">
					style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
					</s:if>
					href="taskManager/task!initReleasedTast.php?platformType=<s:property value="#request.platformType"/>">已发任务</A>
			</LI>
			<LI>
				<A
					<s:if test="#request.showTaskType==6">
					style="FONT-SIZE: 16px; BACKGROUND: url(images/task_nav_02.gif) no-repeat 50% bottom; COLOR: #ffffff"
					</s:if>
					href="taskRepositoryManager/taskRepository!queryRepositories.php?platformType=<s:property value="#request.platformType"/>">任务仓库</A>
			</LI>
			<LI>
				<A href="userInfoManager/info!initSellerAndBuyer.php">绑定卖号</A>
			</LI>

			<LI>
				<A href="userInfoManager/info!initSellerAndBuyer.php">绑定买号</A>
			</LI>


		</UL>
	</DIV>
</DIV>
<DIV style="CLEAR: both; WIDTH: 910px">
	<IMG src="images/task_nav_line.gif">
</DIV>