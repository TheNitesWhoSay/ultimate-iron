package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AR_USER_NAME")
public class ArUserName implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_NAME_ID")
	private Long userNameId;

	@Column(name = "CLAN_MEMBER_ID")
	private Long clanMemberId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	public ArUserName() {
		super();
	}
	public ArUserName(String userName) {
		super();
		this.userName = userName;
	}

	public Long getUserNameId() {
		return userNameId;
	}
	public void setUserNameId(Long userNameId) {
		this.userNameId = userNameId;
	}
	public Long getClanMemberId() {
		return clanMemberId;
	}
	public void setClanMemberId(Long clanMemberId) {
		this.clanMemberId = clanMemberId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
