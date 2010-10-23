import net.win.utils.StringUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String s = "<table width='100%' cellspacing='0' cellpadding='0' border='0' class='LeftNews'>"
				+ "		<tbody><tr> "
				+ "			<td> "
				+ "				用户名： "
				+ "			</td> "
				+ "			<td> "
				+ "				<input type='text' style='width: 120px;' id='username' tabindex='0' value='' maxlength='12' size='30' name='userVO.userEntity.username'> "
				+ "				<span><img src='images/icon_ok.gif' title='验证成功'></span> "
				+ "			</td> "
				+ "		</tr> "
				+ "		<tr> "
				+ "			<td> "
				+ "				密 码： "
				+ "			</td> "
				+ "		<td> "
				+ "				<input type='password' style='width: 120px;' id='password' maxlength='20' size='30' name='userVO.userEntity.loginPassword'> "
				+ "				<span> </span> "
				+ "			</td> "
				+ "		</tr> "
				+ "		<tr> "
				+ "			<td> "
				+ "				验证码： "
				+ "			</td> "
				+ "			<td> "
				+ "				<input type='text' style='width: 60px;' id='verificationCode' value='' maxlength='4' size='30' name='userVO.verificationCode'> "
				+ "				<img style='cursor: pointer;' title='点击图片刷新验证码' onclick='changeValidateCode(this)' src='verify/verificationCode.php'> "
				+ "				<span> </span> "
				+ "			</td> "
				+ "		</tr> "
				+ "	 <tr> "
				+ "	<td nowrap='nowrap' colspan='2'> "
				+ "		<input type='image' border='0' onfocus='this.blur()' src='images/login-q_r4_c3.gif' name='imageField'> "
				+ "		<a href='userManager/base!initRegister.php'><img vspace='0' hspace='5' border='0' src='images/login-q_r4_c31.gif'> </a> "
				+ "		<a border='0' id='findPWA' href='javascript:void(0);'>找回密码</a> "
				+ "	</td> " + "	</tr>  	</tbody></table>";
	}
}
