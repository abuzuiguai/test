package com.ht.calltree.inter.mis.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class AuthenticationCredential implements Serializable {
	private static final long serialVersionUID = 1L;
	private String staffId;
	private String token;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(token).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (!(obj instanceof AuthenticationCredential)) {
			return false;
		}
		return new EqualsBuilder().append(token,
				((AuthenticationCredential) obj).getToken()).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append(staffId).append(token)
				.toString();
	}
}
