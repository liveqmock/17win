package net.win;

import net.win.entity.BaseEntity;
import net.win.utils.Constant;

import org.apache.struts2.ServletActionContext;

public class BaseService {
	protected static final String SUCCESS = "success";
	protected static final String INPUT = "input";
	protected static final String INIT = "init";
	protected static final String JSON = "json";
	protected static final String PAGE = "page";
	protected static final String FILEUPLOAD = "fileUpload";
	protected static final String APPLETVIEW = "appletview";
	protected static final String APPLET = "applet";
	protected static final String OUTEXCEL = "outexcel";
	protected static final String DOWNLOAD = "download";

	/**
	 * 把数据存放在request里面
	 * 
	 * @param name
	 * @param value
	 */
	protected void putByRequest(String key, Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}

	/**
	 * 把数据存放在session里面
	 * 
	 * @param name
	 * @param value
	 */
	protected void putBySession(String key, Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}

	/**
	 * 前台Alert提示数据，放在request里面
	 * 
	 * @param message
	 * @throws Exception
	 */
	protected void putAlertMsg(String message) throws Exception {
		message = "<script>alert('" + message + "');</script>";
		putByRequest(Constant.MSG, message);
	}

	/**
	 * 从request取出数据
	 * 
	 * @param name
	 * @param value
	 */
	protected Object getByRequest(String key) {
		return ServletActionContext.getRequest().getAttribute(key);
	}

	/**
	 * 从session取出数据
	 * 
	 * @param name
	 * @param value
	 */
	protected Object getBySession(String key) {
		return ServletActionContext.getRequest().getSession().getAttribute(key);
	}

	protected Boolean nullID(BaseEntity base) {
		return base.getId() == null;
	}
}
