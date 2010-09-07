package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "TB_RECEIVEINFOTEMPLATE")
public class ReceiveInfoTemplateEntity extends BaseEntity {

	// 信息
	@Column(name = "MSG_", length = 100)
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
