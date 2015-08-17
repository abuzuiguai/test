package com.ht.calltree.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ht.calltree.common.model.MobileReturn;
import com.ht.calltree.sys.model.EnumRoleLevel;
import com.ht.calltree.sys.model.EnumViewByRole;
import com.ht.calltree.sys.model.Role;
import com.ht.calltree.sys.service.RoleService;
import com.ht.calltree.util.CommonHeadJson;

/**
 * 权限控制
 * 
 * @author lianghu
 */
@Controller
@RequestMapping("/sys/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	private Logger log = Logger.getLogger(RoleController.class);

	/**
	 * 移动端查询所有人员信息
	 * 
	 * @param req
	 *            request请求对象
	 * @return 返回所有人员信息
	 */

	@RequestMapping("/MobileGetRoleByStaffId")
	@ResponseBody
	public Object mobileGetRoleByStaffId(HttpServletRequest req, String param) {
		System.out.println("mobileGetRoleByStaffId");
		MobileReturn<List<EnumViewByRole>> mobileReturnRole = new MobileReturn<List<EnumViewByRole>>();
		try {
			if (!StringUtils.hasLength(param)) {
				return CommonHeadJson.failedMessage("传入参数param不能为空");
			}
			JSONObject jsonObject = JSONObject.fromObject(param);
			if (jsonObject == null) {
				return CommonHeadJson.failedMessage("传入参数param格式不正确");
			}
			String staffId = jsonObject.getString("staffId");
			if (!StringUtils.hasLength(staffId)) {
				return CommonHeadJson.failedMessage("传入参数staffId不能为空");
			}
			Role role = roleService.getRoleByStaffId(staffId);
			if (role != null) {
				List<EnumViewByRole> viewByRoles = getViewRole(
						role.getRoleLevel(), role.getBackupRoleLevel());
				mobileReturnRole.setResult(viewByRoles);
				return mobileReturnRole;
			} else {
				return CommonHeadJson.failedMessage("该角色没有设定权限");
			}
		} catch (Exception e) {
			log.info("获取角色列表数据出错", e);
			return CommonHeadJson.failedMessage("获取角色列表数据出错");
		}
	}

	private List<EnumViewByRole> getViewRole(EnumRoleLevel roleLevel,
			EnumRoleLevel backupRoleLevel) {
		List<EnumViewByRole> result = new ArrayList<EnumViewByRole>();
		if (roleLevel != null) {
			switch (roleLevel) {
			case NORMAL:
				result.addAll(Arrays.asList(EnumViewByRole.MYREPLY));
				break;
			case PM:
				result.addAll(Arrays.asList(EnumViewByRole.MYREPLY,
						EnumViewByRole.REPLYDYN, EnumViewByRole.BACKUP));
				break;
			case CREATOR:
				result.addAll(Arrays.asList(EnumViewByRole.CALLTREELIST,
						EnumViewByRole.REPLYDYN, EnumViewByRole.BACKUP));
				break;
			default:
				break;
			}
		}
		if (roleLevel != null && backupRoleLevel != null) {
			switch (backupRoleLevel) {
			case NORMAL:
				result.add(EnumViewByRole.MYREPLY);
				break;
			case PM:
				result.add(EnumViewByRole.REPLYDYN);
				break;
			case CREATOR:
				result.add(EnumViewByRole.CALLTREELIST);
				break;
			default:
				break;
			}
		}
		return removeDuplicate(result);
	}

	/**
	 * 去除重复项
	 * 
	 * @param list
	 * @return
	 */
	private List<EnumViewByRole> removeDuplicate(List<EnumViewByRole> list) {
		// 去重
		Set<EnumViewByRole> set = new HashSet<EnumViewByRole>();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				set.add(list.get(i));
			}
		}
		List<EnumViewByRole> result = null;
		if (set.size() > 0) {
			result = new ArrayList<EnumViewByRole>(set);
		}
		return result;
	}

}
