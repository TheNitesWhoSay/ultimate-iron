package org.iron.ultimate.jpa.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RS_CLAN_MEMBER")
public class RsClanMember implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CLAN_MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long clanMemberId;

	@Column(name = "RANK_NAME")
	private String rankName;
	
	@Column(name = "NOTES")
	private String notes;
	
	public RsClanMember() {
		super();
	}

	public Long getClanMemberId() {
		return clanMemberId;
	}
	public void setClanMemberId(Long clanMemberId) {
		this.clanMemberId = clanMemberId;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
