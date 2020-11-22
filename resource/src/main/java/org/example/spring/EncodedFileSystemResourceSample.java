package org.example.spring;

import com.google.common.io.CharStreams;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 *
 *   扩展
 *     使用Files类来执行那些基本的任务，比如：移动或复制文件，或读取文件内容到一个字符串集合
 *
 *     Closer类，提供了一种非常干净的方式，确保Closeable实例被正确的关闭
 *
 *     ByteSource 和 CharSource类，提供了不可变的输入流（Input）和读（Reader）
 *
 *     ByteSink 和 CharSink类，提供了不可变的输出流（Output）和写（Writer）
 *
 *     CharStreams和ByteStreams类，为读Readers、写Writers、输入流InputStreams、输出流OutputStreams 提供了一些静态的实用方法
 *
 *     BaseEncoding类，提供了编码和解码字节序列和ASCII字符的方法
 *
 *
 *    127 | Spring Resource接口扩展：Resource能否支持写入以及字符集编码？
 *       {@link FileSystemResource}
 *
 * @author zhengshijun
 * @version created on 2020/11/22.
 */
public class EncodedFileSystemResourceSample {

	public static void main(String[] args) throws IOException {

		File systemFile = new File(System.getProperty("user.dir")+"/resource/src/main/java/org/example/spring/EncodedFileSystemResourceSample.java");


		FileSystemResource fileSystemResource = new FileSystemResource(systemFile);

		EncodedResource encodedResource = new EncodedResource(fileSystemResource,"UTF-8");

		try (Reader reader = encodedResource.getReader()){

			CharStreams.readLines(reader).forEach(System.out::println);




			System.out.println(reader);
		}
	}
}
