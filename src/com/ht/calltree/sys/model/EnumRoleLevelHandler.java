package com.ht.calltree.sys.model;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 枚举转换
 * 
 * @author lianghu
 */
public class EnumRoleLevelHandler extends BaseTypeHandler<EnumRoleLevel> {
	private EnumRoleLevel[] enums;

	public EnumRoleLevelHandler() {
	}

	public EnumRoleLevelHandler(Class<EnumRoleLevel> type) {
		if (type == null) {
			throw new IllegalArgumentException("Type argument cannot be null");
		}
		this.enums = type.getEnumConstants();
		if (this.enums == null) {
			throw new IllegalArgumentException(type.getSimpleName()
					+ " does not represent an enum type.");
		}
	}

	@Override
	public EnumRoleLevel getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		int i = rs.getInt(columnName);

		if (rs.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值，定位EnumStatus子类
			return EnumRoleLevel.parse(i);
		}
	}

	@Override
	public EnumRoleLevel getNullableResult(ResultSet rs, int columnIndex)
			throws SQLException {
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		int i = rs.getInt(columnIndex);
		if (rs.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值，定位EnumStatus子类
			return EnumRoleLevel.parse(i);
		}
	}

	@Override
	public EnumRoleLevel getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
		int i = cs.getInt(columnIndex);
		if (cs.wasNull()) {
			return null;
		} else {
			// 根据数据库中的code值，定位EnumStatus子类
			return EnumRoleLevel.parse(i);
		}
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i,
			EnumRoleLevel parameter, JdbcType jdbcType) throws SQLException {
		// baseTypeHandler已经帮我们做了parameter的null判断
		ps.setInt(i, parameter.getId());
	}
}