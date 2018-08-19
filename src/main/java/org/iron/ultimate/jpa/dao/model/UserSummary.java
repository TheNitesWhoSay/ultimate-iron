package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SUMMARY")
public class UserSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Embeddable
	public static class Pk implements Serializable {
		
		private static final long serialVersionUID = 1L;

		@Column(name = "CLAN_MEMBER_ID")
		private Long clanMemberId;
		
		@Column(name = "USER_NAME_ID")
		private Long userNameId;

		public Pk() {
			super();
		}

		public Long getClanMemberId() {
			return clanMemberId;
		}
		public void setClanMemberId(Long clanMemberId) {
			this.clanMemberId = clanMemberId;
		}
		public Long getUserNameId() {
			return userNameId;
		}
		public void setUserNameId(Long userNameId) {
			this.userNameId = userNameId;
		}
		
	}
	
	@EmbeddedId
	private Pk pk;

	@Column(name = "JOIN_DATE")
	private Date joinDate;
	
	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "PAST_USER_NAME")
	private String pastUsername;	

	public UserSummary() {
		super();
	}

	public Pk getPk() {
		return pk;
	}
	public void setPk(Pk pk) {
		this.pk = pk;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPastUsername() {
		return pastUsername;
	}
	public void setPastUsername(String pastUsername) {
		this.pastUsername = pastUsername;
	}

}
