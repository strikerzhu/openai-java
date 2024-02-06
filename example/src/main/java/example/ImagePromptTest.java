package example;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ImageCompletionRequest;
import com.theokanning.openai.completion.chat.ImageMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class ImagePromptTest {
    public static void main(String... args) {
        String token = System.getenv("OPENAI_TOKEN");
        OpenAiService service = new OpenAiService(token, Duration.ofSeconds(30));

        System.out.println("\nCreating completion...");
        ImageMessage imageMessage = new ImageMessage(
            "What’s in this image?",
            "/Users/strikerzhu/Desktop/Work/AI_Image/Selfie_Soul/IMG_1989.JPG"
            //or url:"https://pic.baike.soso.com/ugc/baikepic2/4831/20220119213509-930158171_png_686_1000_225527.jpg/0"
        );

        ImageCompletionRequest imageCompletionRequest = ImageCompletionRequest.builder()
                .model("gpt-4-vision-preview")
                .maxTokens(800)
                .messages(new ArrayList<ImageMessage>() {
                        {
                            add(imageMessage);
                        }
                })
                .build();
        ChatCompletionResult result = service.createImageCompletion(imageCompletionRequest);
        result.getChoices().forEach(System.out::println);
        service.shutdownExecutor();
        List<ChatCompletionChoice> choices = result.getChoices();
        System.out.println(choices.get(0).getMessage().getContent());
    }
}
