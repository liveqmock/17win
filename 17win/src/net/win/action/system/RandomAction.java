package net.win.action.system;

import java.io.ByteArrayInputStream;

import net.win.BaseAction;
import net.win.utils.RandomNumUtil;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionContext;

@Namespace("/systemManager")
@Result(name = "stream", type = "stream", params = { "contentType",
		"image/jpeg", "inputName", "inputStream" })
public class RandomAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ByteArrayInputStream inputStream;

	@Action("/verificationCode")
	public String execute() throws Exception {
		RandomNumUtil rdnu = RandomNumUtil.Instance();
		this.setInputStream(rdnu.getImage());// 取得带有随机字符串的图片
		ActionContext.getContext().getSession().put("random", rdnu.getString());// 取得随机字符串放入HttpSession
		return STREAM;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
}