package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TB_BUYER")
/**
 * 买家信息
 */
public class BuyerEntity extends BaseEntity {
	// 名字
	@Column(name = "SELLER_", length = 20, nullable = false)
	private String name;
	// 类型(1淘宝,2怕拍,3有啊)
	@Column(name = "TYPE_", columnDefinition = "CHAR(1)", nullable = false, unique = true)
	private String type;
	// 积分
	@Column(name = "LEVEL_", nullable = false)
	private Integer score = 0;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
}
