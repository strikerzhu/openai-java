package com.theokanning.openai.completion.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

/**
 *
 * see <a href="https://platform.openai.com/docs/guides/chat/introduction">OpenAi documentation</a>
 */
@Data
public class ImageUrlMessage {
	final String type = "image_url";
	@JsonInclude() // content should always exist in the call, even if it is null
	ImageUrl image_url;

	public ImageUrlMessage(String image_url) {
		this.image_url = new ImageUrl(image_url);
	}

	@Data
	public static class ImageUrl {
        private String url;

        public ImageUrl(String url) {
            //this.url = url;
			this.url = "data:image/jpeg;base64,"+convertImageToBase64(url);
        }

		/**
		 * 将图片转换为Base64编码字符串。
		 * @param imagePath 图片的URL地址或本地文件路径。
		 * @return 图片的Base64编码字符串。
		 */
		public static String convertImageToBase64(String imagePath) {
			try {
				InputStream inputStream;

				// 检查输入是否为URL
				if (imagePath.startsWith("http://") || imagePath.startsWith("https://")) {
					inputStream = new URL(imagePath).openStream();
				} else {
					// 假定输入为文件路径
					inputStream = new FileInputStream(imagePath);
				}

				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = outputStream.toByteArray();

				// 关闭流
				inputStream.close();
				outputStream.close();

				return Base64.getEncoder().encodeToString(imageBytes);
			} catch (Exception e) {
				e.printStackTrace();
				return null; // 或根据需要处理异常
			}
		}		
    }
}