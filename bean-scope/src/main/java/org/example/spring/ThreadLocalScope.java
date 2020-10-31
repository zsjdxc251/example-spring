package org.example.spring;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhengshijun
 * @version created on 2020/10/31.
 */
public class ThreadLocalScope implements Scope {

	public static final String SCOPE_NAME = "thread-local";

	private NamedThreadLocal<Map<String,Object>> namedThreadLocal = new NamedThreadLocal<Map<String, Object>>("named-thread-local"){
		@Override
		protected Map<String,Object> initialValue() {
			return new HashMap<>(16);
		}
	};

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String,Object> context = namedThreadLocal.get();

		Object object = context.get(name);
		if (Objects.isNull(object)){
			object = objectFactory.getObject();
			context.put(name,object);
		}
		return object;
	}

	@Override
	public Object remove(String name) {
		Map<String,Object> context = namedThreadLocal.get();
		return context.remove(name);
	}

	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {

		System.out.println(name);
		runnable.run();
	}

	@Override
	public Object resolveContextualObject(String name) {
		Map<String,Object> context = namedThreadLocal.get();
		return context.get(name);
	}

	@Override
	public String getConversationId() {
		return String.valueOf(Thread.currentThread().getId());
	}
}
