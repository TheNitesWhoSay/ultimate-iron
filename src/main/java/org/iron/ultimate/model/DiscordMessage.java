package org.iron.ultimate.model;

import java.io.Serializable;

public class DiscordMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String content;

	public DiscordMessage() {
		super();
	}
	public DiscordMessage(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
