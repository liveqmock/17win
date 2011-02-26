package net.win.service.admin.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.win.BaseService;
import net.win.dao.PropertyDAO;
import net.win.entity.PropertyEntity;
import net.win.utils.Constant;
import net.win.utils.StringUtils;
import net.win.vo.PropertyVO;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings( { "unchecked" })
@Service("adminPropertyService")
public class AdminPropertyService extends BaseService {
	@Resource
	private PropertyDAO propertyDAO;

	/**
	 * 查询
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String queryProperty(PropertyVO propertyVO) throws Exception {
		StringBuffer resultHQL = new StringBuffer(
				"select  _p  from   PropertyEntity as _p  where 1=1");
		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		// 用户名
		if (!StringUtils.isBlank(propertyVO.getName())) {
			resultHQL.append(" and _p.name like :name ");
			paramNames.add("username");
			paramValues.add("%" + propertyVO.getName() + "%");
		}

		List<PropertyVO> result = new ArrayList<PropertyVO>();
		PropertyVO propertyVOTemp = null;
		List<PropertyEntity> entitys = propertyDAO.list(resultHQL.toString(),
				paramNames.toArray(paramNames.toArray(new String[paramNames
						.size()])), paramValues.toArray(new Object[paramValues
						.size()]));
		for (PropertyEntity propertyEntity : entitys) {
			propertyVOTemp = new PropertyVO();
			BeanUtils.copyProperties(propertyVOTemp, propertyEntity);
			result.add(propertyVOTemp);
		}
		putByRequest("result", result);
		return "queryProperty";
	}

	/**
	 * 修改
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String updateProperty(PropertyVO propertyVO) throws Exception {
		PropertyEntity propertyEntity = propertyDAO.get(propertyVO.getId());
		BeanUtils.copyProperties(propertyEntity, propertyVO);
		putAlertMsg("修改成功！");
		putJumpSelfPage("adminPropertyManager/adminProperty!queryProperty.php");
		return JUMP;
	}

	/**
	 * 初始化修改
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String initUpdateProperty(PropertyVO propertyVO) throws Exception {
		PropertyEntity propertyEntity = propertyDAO.get(propertyVO.getId());
		BeanUtils.copyProperties(propertyVO, propertyEntity);
		return "initUpdateProperty";
	}

	/**
	 * 刷新constant
	 * 
	 * @param smsVO
	 * @return
	 * @throws Exception
	 */
	public String refreshConstant(PropertyVO propertyVO) throws Exception {
		Constant.initMetatData();
		putAlertMsg("更新成功！");
		putJumpSelfPage("adminPropertyManager/adminProperty!queryProperty.php");
		return JUMP;
	}
}
