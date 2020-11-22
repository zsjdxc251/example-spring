package org.example.spring;

import com.google.common.io.CharStreams;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.io.Reader;

/**
 *
 *
 *    128 | Spring资源加载器：为什么说Spring应用上下文也是一种Spring资源加载器？
 *       {@link FileSystemResourceLoader}
 *
 * @author zhengshijun
 * @version created on 2020/11/22.
 */
public class EncodedFileSystemResourceLoaderSample {

	public static void main(String[] args) throws IOException {

		String path = System.getProperty("user.dir")+"/resource/src/main/java/org/example/spring/EncodedFileSystemResourceLoaderSample.java";

		FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
		Resource resource = fileSystemResourceLoader.getResource(path);

		EncodedResource encodedResource = new EncodedResource(resource,"UTF-8");

		try (Reader reader = encodedResource.getReader()){

			CharStreams.readLines(reader).forEach(System.out::println);




			System.out.println(reader);
		}
	}
}
