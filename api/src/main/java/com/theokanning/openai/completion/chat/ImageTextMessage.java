package com.theokanning.openai.completion.chat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
public class ImageTextMessage {
	final String type = "text";
	@JsonInclude() // content should always exist in the call, even if it is null
	String text;

	public ImageTextMessage(String text) {
		this.text = text;
	}
}
