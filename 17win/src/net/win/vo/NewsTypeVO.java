package net.win.vo;

import java.util.Date;

import javax.persistence.Column;

import net.win.BaseVO;

public class NewsTypeVO extends BaseVO {
	private String type;
	// 名字
	private String name;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
