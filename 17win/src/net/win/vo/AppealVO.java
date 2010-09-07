package net.win.vo;

import java.util.Date;

import net.win.BaseVO;

public class AppealVO extends BaseVO {
	// 标题
	private String title;
	// 被申诉人
	private String appealedUser;
	// 申述类型(1 信誉 2 流量 3 收藏)
	private String type;
	// 任务ID
	private String taskID;
	// 描述
	private String desc;
	// 申诉时间
	private Date appealDate;
	// 官方意见
	private String officIdea;
	//状态
	private String status;
	
	//文件上传对象
	private UpLoadFileVO upLoadFileVO;
	
 
	public UpLoadFileVO getUpLoadFileVO() {
		return upLoadFileVO;
	}
	public void setUpLoadFileVO(UpLoadFileVO upLoadFileVO) {
		this.upLoadFileVO = upLoadFileVO;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAppealedUser() {
		return appealedUser;
	}
	public void setAppealedUser(String appealedUser) {
		this.appealedUser = appealedUser;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getAppealDate() {
		return appealDate;
	}
	public void setAppealDate(Date appealDate) {
		this.appealDate = appealDate;
	}
	public String getOfficIdea() {
		return officIdea;
	}
	public void setOfficIdea(String officIdea) {
		this.officIdea = officIdea;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
