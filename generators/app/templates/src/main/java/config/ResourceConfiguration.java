package <%=groupId%>.config;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

@Configuration
public class ResourceConfiguration {
	
	ClassLoader cl = ResourceConfiguration.class.getClassLoader();
	
	private String getResourceContents(String string) throws IOException {
		String url = new ClassPathResource(string).getPath();
		InputStream resource = cl.getResourceAsStream(url);
		byte[] contents = FileCopyUtils.copyToByteArray(resource);
		return new String(contents);
	}
}
