package org.example.spring.utils;

import com.google.common.io.CharStreams;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;
import java.util.stream.Collectors;

/**
 * @author zhengshijun
 * @version created on 2020/11/22.
 */
public interface ResourceHelper {

	static String getContent(Resource resource) {

		EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");

		try (Reader reader = encodedResource.getReader()){

			return String.join("\n", CharStreams.readLines(reader));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
