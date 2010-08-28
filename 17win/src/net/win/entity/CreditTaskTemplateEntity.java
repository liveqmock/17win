package net.win.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 信誉任务模板
 * 
 * @author xgj
 * 
 */
@Entity
@Table(name = "TB_CREDITTASKTEMPLATE")
public class CreditTaskTemplateEntity extends BaseEntity {
	// 用户
	private UserEntity entity;
	// 价格
	private Float money;
	// 接受时间
	// 接收人
	// 状态
	// 商品地址
	// 是否修改价格
	// 动态评分
	// 收货时间，除几天外，可以自定义小时。
	// 定时任务时间
}
