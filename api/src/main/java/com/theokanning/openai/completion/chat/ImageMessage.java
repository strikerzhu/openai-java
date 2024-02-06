package com.theokanning.openai.completion.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.util.ArrayList;

@Data
@NoArgsConstructor(force = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // Correct use of @JsonInclude
public class ImageMessage {

	/**
	 * Must be either 'system', 'user', 'assistant' or 'function'.<br>
	 * You may use {@link ChatMessageRole} enum.
	 */
    
    final String role = ChatMessageRole.USER.value();
    @SuppressWarnings({ "rawtypes"})
    ArrayList content;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ImageMessage(String text, String url) {
        this.content = new ArrayList();
        this.content.add(new ImageTextMessage(text));
        this.content.add(new ImageUrlMessage(url));
    }
}
