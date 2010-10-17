package net.win.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Tb_NewsType")
public class NewsTypeEntity extends BaseEntity {
	// 类型 1 网站公告 2 新手入门 3 刷客必读
	// 4卖家必读 5 买家必读 6店铺推广 7钻前秘诀 8网络营销 9服务项目
	// @Column(name = "type_", columnDefinition = "CHAR(1)", nullable = false)
	// private String type;
	// 名字
	@Column(name = "name_", length = 20, nullable = false)
	private String name;

	// 我的申诉
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "Type_ID_")
	@Cascade(CascadeType.ALL)
	private List<NewsEntity> newses;

	// public String getType() {
	// return type;
	// }
	//
	// public void setType(String type) {
	// this.type = type;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NewsEntity> getNewses() {
		return newses;
	}

	public void setNewses(List<NewsEntity> newses) {
		this.newses = newses;
	}

}
