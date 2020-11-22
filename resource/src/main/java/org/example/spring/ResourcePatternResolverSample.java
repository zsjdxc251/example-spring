package org.example.spring;

import org.example.spring.utils.ResourceHelper;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 *    129 | Spring通配路径资源加载器：如何理解路径通配Ant模式？
 *
 *
 *      {@link ResourcePatternResolver}
 *      -- {@link PathMatchingResourcePatternResolver} {@link PathMatchingResourcePatternResolver#getResources(java.lang.String)}
 *
 *
 *    130 | Spring通配路径模式扩展：如何扩展路径匹配的规则？
 *       {@link PathMatcher}
 *       {@link PathMatchingResourcePatternResolver#setPathMatcher(org.springframework.util.PathMatcher)}
 * @author zhengshijun
 * @version created on 2020/11/22.
 */
public class ResourcePatternResolverSample {

	public static void main(String[] args) throws IOException {
		String path = System.getProperty("user.dir")+"/resource/src/main/java/org/example/spring/";

		String locationResource = path+ "*.java";

		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver(new FileSystemResourceLoader());

		pathMatchingResourcePatternResolver.setPathMatcher(new JavaFilePathMatcher());

		Resource[] resource = pathMatchingResourcePatternResolver.getResources(locationResource);

		Stream.of(resource).map(ResourceHelper::getContent).forEach(System.out::println);
	}

	static class JavaFilePathMatcher implements PathMatcher {

		@Override
		public boolean isPattern(String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean match(String pattern, String path) {
			return path.endsWith(".java");
		}

		@Override
		public boolean matchStart(String pattern, String path) {
			return false;
		}

		@Override
		public String extractPathWithinPattern(String pattern, String path) {
			return null;
		}

		@Override
		public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
			return null;
		}

		@Override
		public Comparator<String> getPatternComparator(String path) {
			return null;
		}

		@Override
		public String combine(String pattern1, String pattern2) {
			return null;
		}
	}
}
