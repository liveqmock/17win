package net.win.vo;

import net.win.BaseVO;

public class PropertyVO extends BaseVO {
	private String name;
	private Double numberValue;
	private String stringValue;
	private String desc;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getNumberValue() {
		return numberValue;
	}
	public void setNumberValue(Double numberValue) {
		this.numberValue = numberValue;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
