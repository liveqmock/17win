package net.win.action.user;

import java.io.File;

import javax.annotation.Resource;

import net.win.BaseAction;
import net.win.service.user.AppealService;
import net.win.vo.AppealVO;
import net.win.vo.UpLoadFileVO;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("17win-default")
@InterceptorRefs({@InterceptorRef(value = "fileUpload", params = {
		"allowedTypes", "images/bmp,image/png,image/gif,image/jpeg",
		"maximumSize", "200000"})})
@Results({@Result(name = "initAppeal", location = "/user/appeal.jsp"),
		@Result(name = "input", location = "/user/appeal.jsp")

})
@Namespace("/appealActionManager")
public class AppealAction extends BaseAction {
	@Resource
	private AppealService appealService;

	private AppealVO appealVO = new AppealVO();

	/**
	 * 图片
	 */
	private String title;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private String savePath;

	@Action("/appeal")
	public String execute() throws Exception {
		return INPUT;
	}

	/**
	 * 添加申诉
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addAppeal() throws Exception {
		UpLoadFileVO upLoadFileVO = new UpLoadFileVO();
		upLoadFileVO.setTitle(title);
		upLoadFileVO.setFile(image);
		upLoadFileVO.setFileContentType(imageContentType);
		upLoadFileVO.setSavePath(savePath);
		appealVO.setUpLoadFileVO(upLoadFileVO);
		return appealService.insertAppeal(appealVO);
	}
	/**
	 * 初始化申述
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initAppeal() throws Exception {
		return "initAppeal";
	}

	public AppealVO getAppealVO() {
		return appealVO;
	}

	public void setAppealVO(AppealVO appealVO) {
		this.appealVO = appealVO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getSavePath() {
		return ServletActionContext.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
