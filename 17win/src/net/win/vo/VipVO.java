package net.win.vo;

import net.win.BaseVO;

public class VipVO extends BaseVO {
	private String type;
	
	
	// 输出现实
	private Integer growValue;
	private String nextType;
	private String nextVipGrowValue;
	private String vipEnable;
	public Integer getGrowValue() {
		return growValue;
	}
	public void setGrowValue(Integer growValue) {
		this.growValue = growValue;
	}
	public String getNextType() {
		return nextType;
	}
	public void setNextType(String nextType) {
		this.nextType = nextType;
	}
 
	public String getNextVipGrowValue() {
		return nextVipGrowValue;
	}
	public void setNextVipGrowValue(String nextVipGrowValue) {
		this.nextVipGrowValue = nextVipGrowValue;
	}
	public String getVipEnable() {
		return vipEnable;
	}
	public void setVipEnable(String vipEnable) {
		this.vipEnable = vipEnable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
