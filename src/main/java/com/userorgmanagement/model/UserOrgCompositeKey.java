package com.userorgmanagement.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserOrgCompositeKey implements Serializable {

	private Long userId;
	private Long orgId;

	public UserOrgCompositeKey() {
		super();
	}

	public UserOrgCompositeKey(Long userId, Long organizationId) {
		this.userId = userId;
		this.orgId = organizationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orgId == null) ? 0 : orgId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserOrgCompositeKey other = (UserOrgCompositeKey) obj;
		if (orgId == null) {
			if (other.orgId != null)
				return false;
		} else if (!orgId.equals(other.orgId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
