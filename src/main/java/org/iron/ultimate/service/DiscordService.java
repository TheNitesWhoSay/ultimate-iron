package org.iron.ultimate.service;

import org.iron.ultimate.model.DiscordMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscordService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private String staffDiscordWebhookId;
	
	@Autowired
	private String staffDiscordWebhookToken;
	
	public String sendMessage(String message) {

		DiscordMessage discordMessage = new DiscordMessage(message);
		String webhookUrl = "https://discordapp.com/api/webhooks/" + staffDiscordWebhookId + "/" + staffDiscordWebhookToken;
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<DiscordMessage> discordRequest = new HttpEntity<DiscordMessage>(discordMessage, httpHeaders);
		return restTemplate.postForObject(webhookUrl, discordRequest, String.class);
	}
	
}
