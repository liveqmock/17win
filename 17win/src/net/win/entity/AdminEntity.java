package net.win.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * 管理员
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "Tb_Admin")
public class AdminEntity extends BaseEntity {
	/**
	 * 基本信息
	 */
	// 用户名
	@Column(name = "Username_", length = 12,  nullable = false)
	private String username;
	// 登陆密码
	@Column(name = "Password_", columnDefinition = "CHAR(24)", nullable = false)
	private String password;
	// 用户
	@OneToOne(optional = false)
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "User_ID_", unique = true)
	private UserEntity user;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
