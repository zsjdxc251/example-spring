package org.example.spring.ext;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2020/11/19.
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String s, EncodedResource encodedResource) throws IOException {
		Map<String, Object> propertiesSource = new HashMap<>(10);
		propertiesSource.put("user.name", "zsj5566");

		return new MapPropertySource("custom-property-source",propertiesSource);
	}

}
